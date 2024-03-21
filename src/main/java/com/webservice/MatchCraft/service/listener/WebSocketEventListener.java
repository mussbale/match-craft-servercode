package com.webservice.MatchCraft.service.listener;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.messaging.SessionConnectedEvent;
import org.springframework.web.socket.messaging.SessionDisconnectEvent;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;

@Component
public class WebSocketEventListener {

    private final Map<String, Long> onlineUsers = new ConcurrentHashMap<>();

    @Autowired
    private SimpMessageSendingOperations messagingTemplate;

    @EventListener
    public void handleWebSocketConnectListener(SessionConnectedEvent event) {
        SimpMessageHeaderAccessor headers = SimpMessageHeaderAccessor.wrap(event.getMessage());
        Long userId = (Long) headers.getSessionAttributes().get("userId");
        String sessionId = headers.getSessionId();

        if (userId != null) {
            onlineUsers.put(sessionId, userId);
            broadcastUserStatus(userId, true);
            // Add logging to debug the session and user ID mapping
            System.out.println("Connection established: Session ID = " + sessionId + ", User ID = " + userId);
        } else {
            // Log when userId is null to debug
            System.out.println("Connection established but userId is null: Session ID = " + sessionId);
        }
    }

    @EventListener
    public void handleWebSocketDisconnectListener(SessionDisconnectEvent event) {
        Long userId = onlineUsers.remove(event.getSessionId());
        if (userId != null) {
            broadcastUserStatus(userId, false);
        }
    }

    private void broadcastUserStatus(Long userId, boolean isOnline) {
        Map<String, Object> statusUpdate = Map.of(
            "userId", userId,
            "isOnline", isOnline
        );
        messagingTemplate.convertAndSend("/topic/userStatus", statusUpdate);
    }
    public boolean isUserOnline(Long userId) {
        return onlineUsers.containsValue(userId);
    }
}
