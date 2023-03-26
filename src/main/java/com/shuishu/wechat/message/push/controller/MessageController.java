package com.shuishu.wechat.message.push.controller;


import com.shuishu.wechat.message.push.service.MessageService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author ：谁书-ss
 * @date ：2023-03-24 12:38
 * @IDE ：IntelliJ IDEA
 * @Motto ：ABC(Always Be Coding)
 * <p></p>
 * @description ：发送controller
 * <p></p>
 */
@RequiredArgsConstructor
@RestController
public class MessageController {
    private final MessageService messageService;

    @GetMapping("send")
    public ResponseEntity<String> sendWechatToUser(){
        messageService.sendWechatToUser();
        return ResponseEntity.ok("发送成功");
    }
}
