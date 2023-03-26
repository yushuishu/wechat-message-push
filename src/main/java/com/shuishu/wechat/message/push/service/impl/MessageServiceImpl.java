package com.shuishu.wechat.message.push.service.impl;


import cn.hutool.core.date.DatePattern;
import cn.hutool.core.date.DateUtil;
import cn.hutool.http.HttpUtil;
import com.alibaba.fastjson2.JSONArray;
import com.alibaba.fastjson2.JSONObject;
import com.shuishu.wechat.message.push.constants.MessageConstants;
import com.shuishu.wechat.message.push.data.TianXingDataUtils;
import com.shuishu.wechat.message.push.entity.MessageContentDto;
import com.shuishu.wechat.message.push.entity.WeChatUser;
import com.shuishu.wechat.message.push.service.MessageService;
import com.shuishu.wechat.message.push.utils.DateUtils;
import com.shuishu.wechat.message.push.utils.WeChatUserUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Date;
import java.util.List;


/**
 * @author ：谁书-ss
 * @date ：2023-03-23 22:08
 * @IDE ：IntelliJ IDEA
 * @Motto ：ABC(Always Be Coding)
 * <p></p>
 * @description ：消息推送服务
 * <p></p>
 */
@Slf4j
@Service
public class MessageServiceImpl implements MessageService {

    /**
     * 推送到微信个人
     */
    @Override
    public void sendWechatToUser() {
        String url = MessageConstants.WEI_MI_SHU_API + "openapi/v1/chat/contact";
        MessageContentDto messageContentDto = new MessageContentDto();
        //发送文字信息
        messageContentDto.setType(1);

        try {
            StringBuilder sb = new StringBuilder();

            //今天的阳历 yyyy年MM月dd日
            String toDateStr = DateUtil.format(new Date(), DatePattern.CHINESE_DATE_FORMAT);
            if (StringUtils.hasText(toDateStr)) {
                String chinaWeek = DateUtils.getChinaWeek();
                String lunarDateStr = DateUtils.getLunarDateStr();
                if (StringUtils.hasText(chinaWeek) && StringUtils.hasText(lunarDateStr)) {
                    sb.append("\n\n今天是：").append(toDateStr).append("，星期").append(chinaWeek).append("，").append(lunarDateStr);
                } else {
                    return;
                }
            } else {
                return;
            }

            JSONObject weather = TianXingDataUtils.getWeather();
            if (weather != null) {
                String province = weather.getString("province");
                String area = weather.getString("area");
                String region = province.equals(area) ? province : province + area;
                sb.append("\n\n当前").append(region).append("天气：").append(weather.getString("weather"))
                        .append("\n").append("当前气温：").append(weather.get("real"))
                        .append("\n").append("气温范围：").append(weather.get("real")).append("~").append(weather.get("highest"))
                        .append("\n").append("风向：").append(weather.get("wind"))
                        .append("\n").append("风速：").append(weather.get("windspeed"))
                        .append("\n").append("空气质量：").append(weather.get("quality"))
                        .append("\n").append("温馨提示：").append(weather.get("tips"))
                ;
            }

            JSONArray bulletin = TianXingDataUtils.getBulletin();
            if (bulletin != null && bulletin.size() > 0) {
                sb.append("\n\n").append("今日简报：");
                for (int i = 0; i < bulletin.size(); i++) {
                    if (i > 9) {
                        break;
                    }
                    JSONObject bulletinJsonObj = (JSONObject) bulletin.get(i);
                    sb.append("\n").append(i + 1).append("、").append(bulletinJsonObj.getString("digest"));
                }

            }

            String goodMorning = TianXingDataUtils.getGoodMorning();
            if (StringUtils.hasText(goodMorning)) {
                sb.append("\n\n").append("【早安寄语】").append(goodMorning);
            }

            messageContentDto.setContent(sb.toString());


            List<WeChatUser> weChatUserList = WeChatUserUtils.getWeChatUserList();
            log.info("微秘书发送URL：{}", url);
            for (WeChatUser weChatUser : weChatUserList) {
                JSONObject object = new JSONObject();
                object.put("apiKey", MessageConstants.WEI_MI_SHU_KEY);
                //好友昵称
                object.put("name", weChatUser.getNickName());
                //好友备注
                object.put("alias", weChatUser.getRemarkName());
                object.put("message", messageContentDto);
                String body = HttpUtil.createPost(url)
                        .body(JSONObject.toJSONString(object))
                        .execute()
                        .body();
                log.info("发送微信用户：{} 内容是：{}", weChatUser.getRemarkName(), JSONObject.toJSONString(object));
                log.info("发送消息结果：{}", body);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
