package com.webservice.MatchCraft.service;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.stereotype.Service;

@Service
public class WebSocketSessionService {
    private final Map<Long, String> userSessions = new ConcurrentHashMap<>();

    public void userConnected(Long userId, String sessionId) {
        userSessions.put(userId, sessionId);
    }

    public void userDisconnected(Long userId) {
        userSessions.remove(userId);
    }
}
