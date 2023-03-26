package com.shuishu.wechat.message.push.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author ：谁书-ss
 * @date ：2023-03-24 8:17
 * @IDE ：IntelliJ IDEA
 * @Motto ：ABC(Always Be Coding)
 * <p></p>
 * @description ：微信用户po
 * <p></p>
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
public class WeChatUser {
    /**
     * 好友的昵称
     */
    private String nickName;
    /**
     * 备注的名字
     */
    private String remarkName;
}
