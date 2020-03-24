package cn.mir.test.websocket.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * 控制类-用户聊天
 * <p>Create time: 2020/3/20 16:17</p>
 *
 * @author 周光兵
 */
@Slf4j
@RestController
public class UserChatController {
    /**
     * 消息发送模板
     */
    private final SimpMessagingTemplate simpMessagingTemplate;

    /**
     * 构造方法注入对象
     *
     * @param simpMessagingTemplate 消息发送模板
     */
    public UserChatController(SimpMessagingTemplate simpMessagingTemplate) {
        this.simpMessagingTemplate = simpMessagingTemplate;
    }

    /**
     * 一对一单聊
     */
    @MessageMapping(value = "/chat")
    public void chat(String message) {
        log.info("message:{}", message);
        this.simpMessagingTemplate.convertAndSendToUser("anoy", "/topic/chat", message);
    }

    /**
     * 群聊
     */
    @MessageMapping(value = "/notice")
    public void groupChat(String message) {
        log.info("message:{}", message);
        this.simpMessagingTemplate.convertAndSend("/topic/notice", message);
    }

    @MessageMapping(value = "hello")
    @SendTo(value = "/topic/test")
    public Map<String, String> test(String message) {
        log.info("method:test \t message:{}", message);
        Map<String, String> map = new HashMap<>(1);
        map.put("message", message);

        return map;
    }
}
