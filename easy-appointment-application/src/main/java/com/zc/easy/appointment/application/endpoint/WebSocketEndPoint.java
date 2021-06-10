package com.zc.easy.appointment.application.endpoint;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.zc.easy.appointment.application.domain.Message;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author lizecheng
 * @date 2021/6/8 14:34
 */
@ServerEndpoint("/test")
@Component
@Slf4j
public class WebSocketEndPoint {
    /**
     * 用于存放所有在线客户端
     */
    private static Map<String, Session> clients = new ConcurrentHashMap<>();

    private ObjectMapper objectMapper = new ObjectMapper();

    @OnOpen
    public void onOpen(Session session) {
        log.info("有新的客户端上线: {}", session.getId());
        clients.put(session.getId(), session);
    }

    @OnClose
    public void onClose(Session session) {
        String sessionId = session.getId();
        log.info("有客户端离线: {}", sessionId);
        clients.remove(sessionId);
    }

    @OnError
    public void onError(Session session, Throwable throwable) {
        throwable.printStackTrace();
        if (clients.get(session.getId()) != null) {
            clients.remove(session.getId());
        }
    }

    @OnMessage
    public void onMessage(String message) {
        log.info("收到客户端发来的消息: {}", message);
        try {
            this.sendTo(objectMapper.readValue(message, Message.class));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            log.error("json字符串转object出错，字符串：{}",message);
        }
    }

    /**
     * 发送消息
     *
     * @param message 消息对象
     */
    private void sendTo(Message message) {
        Session s = clients.get(message.getUserId());
        if (s != null) {
            try {
                s.getBasicRemote().sendText(message.getMessage());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
