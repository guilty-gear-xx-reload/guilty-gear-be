package ggxnet.reload.client;

import ggxnet.reload.lobby.port.incoming.LobbyServicePort;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

import static ggxnet.reload.shared.RequestUtil.getRequest;

@Slf4j
@RestController
@RequiredArgsConstructor
class LobbyRestController {
    private final LobbyServicePort lobbyServicePort;

    @GetMapping
    public String handleGet(HttpServletRequest httpServletRequest) {
        lobbyServicePort.processGet(getRequest(httpServletRequest));
        return "Server works!";
    }

    @PostMapping
    public String handlePost(HttpServletRequest httpServletRequest) {
        String response = lobbyServicePort.processPost(getRequest(httpServletRequest));
        log.info("\nRESPONSE\n {}", response);
        return response;
    }


}