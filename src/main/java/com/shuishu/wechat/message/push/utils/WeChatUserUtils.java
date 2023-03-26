package com.shuishu.wechat.message.push.utils;


import com.shuishu.wechat.message.push.entity.WeChatUser;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ：谁书-ss
 * @date ：2023-03-24 8:16
 * @IDE ：IntelliJ IDEA
 * @Motto ：ABC(Always Be Coding)
 * <p></p>
 * @description ：微信用户
 * <p></p>
 */
public class WeChatUserUtils {

    public static List<WeChatUser> getWeChatUserList() {
        List<WeChatUser> weChatUserList = new ArrayList<>();
        weChatUserList.add(new WeChatUser("Slave2", "Slave2"));
        return weChatUserList;
    }

}
