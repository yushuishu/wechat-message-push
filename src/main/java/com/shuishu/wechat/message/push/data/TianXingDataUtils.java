package com.shuishu.wechat.message.push.data;


import cn.hutool.http.HttpUtil;
import com.alibaba.fastjson2.JSONArray;
import com.alibaba.fastjson2.JSONObject;
import com.shuishu.wechat.message.push.constants.MessageConstants;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;

/**
 * @author ：谁书-ss
 * @date ：2023-03-23 20:38
 * @IDE ：IntelliJ IDEA
 * @Motto ：ABC(Always Be Coding)
 * <p></p>
 * @description ：天行数据 获取
 * <p></p>
 */
@Slf4j
public class TianXingDataUtils {
    /**
     * 早安心语
     *
     * @return -
     */
    public static String getGoodMorning() {
        String body = HttpUtil
                .createGet(MessageConstants.TIAN_XING_API + MessageConstants.GOOD_MORNING + "?key=" + MessageConstants.TIAN_XING_KEY)
                .execute()
                .body();
        JSONObject jsonObject = JSONObject.parseObject(body);
        Integer code = jsonObject.getInteger("code");
        if (code != HttpStatus.OK.value()) {
            log.error("请求【早安心语】出错：" + body);
            return "一切都是最好的安排~";
        }
        log.info("请求【早安心语】接口成功：" + body);
        JSONObject resultObject = jsonObject.getJSONObject("result");
        return resultObject.getString("content");
    }

    /**
     * 晚安心语
     *
     * @return -
     */
    public static String getGoodNight() {
        String body = HttpUtil
                .createGet(MessageConstants.TIAN_XING_API + MessageConstants.GOOD_NIGHT + "?key=" + MessageConstants.TIAN_XING_KEY)
                .execute()
                .body();
        JSONObject jsonObjectResult = JSONObject.parseObject(body);
        Integer code = jsonObjectResult.getInteger("code");
        if (code != HttpStatus.OK.value()) {
            log.error("请求【晚安心语】接口出错，出错信息：" + body);
            return "一切都是最好的安排~";
        }
        JSONObject resultObject = jsonObjectResult.getJSONObject("result");
        log.info("请求【晚安心语】接口成功：" + resultObject);
        return resultObject.getString("content");
    }

    /**
     * 土味情话
     *
     * @return -
     */
    public static String getLoveWords() {
        String body = HttpUtil
                .createGet(MessageConstants.TIAN_XING_API + MessageConstants.LOVE_WORDS + "?key=" + MessageConstants.TIAN_XING_KEY)
                .execute()
                .body();
        JSONObject jsonObjectResult = JSONObject.parseObject(body);
        Integer code = jsonObjectResult.getInteger("code");
        if (code != HttpStatus.OK.value()) {
            log.error("请求【土味情话】接口出错，出错信息：" + body);
            return "一切都是最好的安排~";
        }
        JSONObject resultObject = jsonObjectResult.getJSONObject("result");
        log.info("请求【土味情话】接口成功：" + resultObject);
        return resultObject.getString("content");
    }

    /**
     * 天气
     *
     * @return -
     */
    public static JSONObject getWeather() {
        //调用每日简报的接口
        String body = HttpUtil
                .createGet(MessageConstants.TIAN_XING_API + MessageConstants.WEATHER + "?key=" + MessageConstants.TIAN_XING_KEY + "&type=1&city=天河")
                .execute()
                .body();
        JSONObject jsonObjectResult = JSONObject.parseObject(body);
        Integer code = jsonObjectResult.getInteger("code");
        if (code != HttpStatus.OK.value()) {
            log.error("请求【天气】接口出错，出错信息：" + body);
            return null;
        }
        return jsonObjectResult.getJSONObject("result");
        /*
        * {
          "code": 200,
          "msg": "success",
          "result": {
            "date": "2022-12-19",
            "week": "星期一",
            "province": "上海",
            "area": "上海",
            "areaid": "101020100",
            "weather": "晴",
            "weatherimg": "qing.png",
            "weathercode": "qing",
            "real": "3℃",
            "lowest": "2℃",
            "highest": "9℃",
            "wind": "北风",
            "windspeed": "0",
            "windsc": "0级",
            "sunrise": "06:47",
            "sunset": "16:54",
            "moonrise": "",
            "moondown": "",
            "pcpn": "0",
            "uv_index": "0",
            "aqi": "54",
            "quality": "良",
            "vis": "18",
            "humidity": "48",
            "alarmlist": [

            ],
            "tips": "晴天紫外线等级较高，外出注意补水防晒。天气凉，适宜着一到两件羊毛衫、大衣、毛套装、皮夹克等春秋着装；年老体弱者宜着风衣加羊毛衫等厚型春秋着装。空气质量较好，可以参与各类户外活动。疫情防控不松懈，出门请佩戴口罩。"
              }
            }
        * */
    }


    /**
     * 社会新闻
     *
     * @param num 条数
     * @return -
     */
    public static JSONObject getCityNews(int num) {
        num = num < 0 || num > 20 ? 5 : num;
        String body = HttpUtil
                .createGet(MessageConstants.TIAN_XING_API + MessageConstants.SOCIAL_NEWS + "?key=" + MessageConstants.TIAN_XING_KEY + "&num=" + num)
                .execute()
                .body();
        JSONObject jsonObject = JSONObject.parseObject(body);
        Integer code = jsonObject.getInteger("code");
        if (code != HttpStatus.OK.value()) {
            log.error("请求【社会新闻】接口出错，出错信息：" + body);
            return null;
        }
        JSONObject resultObject = jsonObject.getJSONObject("result").getJSONObject("newslist");
        log.info("请求【社会新闻】接口成功：" + resultObject);
        return resultObject;
    }


    /**
     * 全网热搜榜
     *
     * @return -
     */
    public static JSONObject getNetWorkHot() {
        String body = HttpUtil
                .createGet(MessageConstants.TIAN_XING_API + MessageConstants.NETWORK_HOT + "?key=" + MessageConstants.TIAN_XING_KEY)
                .execute()
                .body();
        JSONObject jsonObject = JSONObject.parseObject(body);
        Integer code = jsonObject.getInteger("code");
        if (code != HttpStatus.OK.value()) {
            log.error("请求【全网热搜榜】接口出错，出错信息：" + body);
            return null;
        }
        JSONObject resultObject = jsonObject.getJSONObject("result").getJSONObject("list");
        log.info("请求【全网热搜榜】接口成功：" + resultObject);
        return resultObject;
    }

    /**
     * 每日简报
     *
     * @return -
     */
    public static JSONArray getBulletin() {
        String body = HttpUtil
                .createGet(MessageConstants.TIAN_XING_API + MessageConstants.BULLETIN + "?key=" + MessageConstants.TIAN_XING_KEY)
                .execute()
                .body();
        JSONObject jsonObject = JSONObject.parseObject(body);
        Integer code = jsonObject.getInteger("code");
        if (code != HttpStatus.OK.value()) {
            log.error("请求【每日简报】接口出错，出错信息：" + body);
            return null;
        }
        JSONArray jsonArray = jsonObject.getJSONObject("result").getJSONArray("list");
        log.info("请求【每日简报】接口成功：" + jsonArray);
        return jsonArray;
    }


    /**
     * 云音乐热评
     *
     * @return -
     */
    public static JSONObject getHotReview() {
        String body = HttpUtil
                .createGet(MessageConstants.TIAN_XING_API + MessageConstants.HOT_REVIEW + "?key=" + MessageConstants.TIAN_XING_KEY)
                .execute()
                .body();
        JSONObject jsonObject = JSONObject.parseObject(body);
        Integer code = jsonObject.getInteger("code");
        if (code != HttpStatus.OK.value()) {
            log.error("请求【云音乐热评】接口出错，出错信息：" + body);
            return null;
        }
        JSONObject resultObject = jsonObject.getJSONObject("result");
        log.info("请求【云音乐热评】接口成功：" + resultObject);
        return resultObject;
    }

}
