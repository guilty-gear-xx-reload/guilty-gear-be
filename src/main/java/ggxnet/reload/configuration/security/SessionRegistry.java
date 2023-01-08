package ggxnet.reload.configuration.security;

import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import org.springframework.stereotype.Component;

@Component
public class SessionRegistry {
  private static final HashMap<String, String> SESSIONS = new HashMap<>();
  private final Map<String, String> redisSessionStorage = new HashMap<>();

  public String registerSession(final String username) {
    if (username == null) {
      throw new RuntimeException("Username needs to be provided");
    }

    final String sessionId = generateSessionId();

    try {
      redisSessionStorage.put(sessionId, username);
    } catch (final Exception e) {
      e.printStackTrace();
      SESSIONS.put(sessionId, username);
    }

    return sessionId;
  }

  public String getUsernameForSession(final String sessionId) {
    try {
      return redisSessionStorage.get(sessionId);
    } catch (final Exception e) {
      e.printStackTrace();
      return SESSIONS.get(sessionId);
    }
  }

  private String generateSessionId() {
    return new String(
        Base64.getEncoder().encode(UUID.randomUUID().toString().getBytes(StandardCharsets.UTF_8)));
  }
}
