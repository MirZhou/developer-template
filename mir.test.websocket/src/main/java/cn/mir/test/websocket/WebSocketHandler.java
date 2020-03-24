package cn.mir.test.websocket;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

/**
 * WebSocketHandler 处理类
 * <p>Create time: 2020/3/23 11:22</p>
 *
 * @author 周光兵
 */
@Slf4j
public class WebSocketHandler extends TextWebSocketHandler {
    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        // 接收到的数据
        String payload = message.getPayload();

        log.info("接收到的数据。 message:{}", payload);

        // 向客户端发送数据
        session.sendMessage(new TextMessage("response: " + payload));
    }
}
