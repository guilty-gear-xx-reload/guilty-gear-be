package ggxnet.reload.controller;

import ggxnet.reload.configuration.security.SessionRegistry;
import ggxnet.reload.configuration.security.dto.SessionId;
import ggxnet.reload.configuration.security.dto.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping
public class AuthenticationController {
  @Autowired
  public AuthenticationManager manager;
  @Autowired
  public SessionRegistry sessionRegistry;

  @PostMapping("/login")
  public SessionId login(@RequestBody UserDto user) {
    manager.authenticate(
        new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword())
    );

    final String sessionId = sessionRegistry.registerSession(user.getUsername());
    SessionId response = new SessionId();
    response.setSessionId(sessionId);

    return response;
  }
}