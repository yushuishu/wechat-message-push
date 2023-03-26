package com.shuishu.wechat;

import com.shuishu.wechat.message.push.service.MessageService;
import jakarta.annotation.Resource;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class WechatMessagePushApplicationTests {
    @Resource
    private MessageService messageService;

    @Test
    void contextLoads() {
        messageService.sendWechatToUser();
    }

}
