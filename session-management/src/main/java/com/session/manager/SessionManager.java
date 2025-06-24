package com.session.manager;

import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

public class SessionManager {
    private final Map<String, String> sessions = new ConcurrentHashMap<>();

    public synchronized String login(String userId) {
        return sessions.computeIfAbsent(userId, k -> "SESSION_" + UUID.randomUUID())
                       .equals(sessions.get(userId)) 
                       ? "Login successful. Session ID: " + sessions.get(userId) 
                       : "User already logged in.";
    }

    public String logout(String userId) {
        if (sessions.remove(userId) != null) {
            return "Logout successful.";
        }
        return "User not logged in.";
    }

    public String getSessionDetails(String userId) {
        String session = sessions.get(userId);
        if (session == null) {
            return "Session not found for user " + userId;
        }
        return "Session ID for user " + userId + ": " + session;
    }
}