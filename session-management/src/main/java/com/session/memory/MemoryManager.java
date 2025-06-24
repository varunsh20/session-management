package com.session.memory;

import java.util.Map;
import java.util.concurrent.*;

public class MemoryManager {
    private static final Map<String, byte[]> sessionCache = new ConcurrentHashMap<>();
    private static final ScheduledExecutorService cleaner = Executors.newScheduledThreadPool(1);
    private static final long SESSION_TTL_MS = 10 * 60 * 1000;

    private static final Map<String, Long> timestamps = new ConcurrentHashMap<>();

    static {
        cleaner.scheduleAtFixedRate(() -> {
            long now = System.currentTimeMillis();
            timestamps.forEach((sessionId, time) -> {
                if (now - time > SESSION_TTL_MS) {
                    sessionCache.remove(sessionId);
                    timestamps.remove(sessionId);
                }
            });
        }, 1, 1, TimeUnit.MINUTES);
    }

    public static void addSessionData(String sessionId) {
        sessionCache.put(sessionId, new byte[10 * 1024 * 1024]);
        timestamps.put(sessionId, System.currentTimeMillis());
    }

    public static void removeSessionData(String sessionId) {
        sessionCache.remove(sessionId);
        timestamps.remove(sessionId);
    }
}