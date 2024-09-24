package com.thxforservice.global.configs;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Component
public class ChatHandler extends TextWebSocketHandler {

    private static Map<String, List<WebSocketSession>> roomSessions = new HashMap<>();

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        String roomNo = getRoomNo(session);
        roomSessions.putIfAbsent(roomNo, new ArrayList<>());
        roomSessions.get(roomNo).add(session);
        log.info(session.toString() + "가 " + roomNo + " 방에 접속");
    }

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        String roomNo = getRoomNo(session);
        log.info("roomNo: " + roomNo + ", message: " + message.getPayload());

        for (WebSocketSession s : roomSessions.get(roomNo)) {
            s.sendMessage(message);
        }
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        String roomNo = getRoomNo(session);
        log.info(session.toString() + "가 " + roomNo + " 방에서 해제");
        roomSessions.get(roomNo).remove(session);
    }

    private String getRoomNo(WebSocketSession session) {
        String path = session.getUri().getPath();
        return path.split("/")[3];
    }
}
