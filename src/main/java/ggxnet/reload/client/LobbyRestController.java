package ggxnet.reload.client;

import ggxnet.reload.client.command.GameCommand;
import ggxnet.reload.lobby.Parameters;
import ggxnet.reload.lobby.port.incoming.LobbyServicePort;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
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

    @PostMapping("/enter")
    public String enter(@RequestBody GameCommand command) {
        return lobbyServicePort.enter(command);
    }

    @PostMapping("/leave")
    public String leave(@RequestBody GameCommand command) {
        return lobbyServicePort.leave(command);
    }

    @PostMapping("/read")
    public String read(@RequestBody GameCommand command) {
        return lobbyServicePort.read(command);
    }

    @PostMapping
    public String handlePost(HttpServletRequest httpServletRequest, @RequestBody Parameters command) {
        String response = lobbyServicePort.processPost(command);
        log.info("\nRESPONSE\n {}", response);
        return response;
    }


}