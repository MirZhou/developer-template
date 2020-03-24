package cn.mir.test.websocket.controller;

import cn.mir.common.utilities.ResponseResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>Create time: 2020/3/23 14:45</p>
 *
 * @author 周光兵
 */
@Slf4j
@RestController
public class SendController {
    /**
     * 消息发送模板
     */
    private final SimpMessagingTemplate simpMessagingTemplate;

    /**
     * 构造方法注入对象
     * @param simpMessagingTemplate 消息发送模板
     */
    public SendController(SimpMessagingTemplate simpMessagingTemplate) {
        this.simpMessagingTemplate = simpMessagingTemplate;
    }

    @GetMapping(value = "/test/send")
    public ResponseResult<Void> send() {
        log.info("method:testSend \t message:");

        this.simpMessagingTemplate.convertAndSend("/topic/notice","服务器推送消息");

        return new ResponseResult<>(true, "推送成功");
    }
}
