package ggxnet.reload.lobby.client;

import ggxnet.reload.lobby.client.command.PlayerIdCommand;
import ggxnet.reload.lobby.domain.port.incoming.PlayerConfigServicePort;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
class LobbyRestController {
    private final PlayerConfigServicePort playerConfigServicePort;

    @GetMapping
    public String handleGet() {
        return "Server works!";
    }

    @PostMapping("/enter")
    public String enter(@RequestBody PlayerIdCommand command) {
        return playerConfigServicePort.enter(command);
    }

    @PostMapping("/leave")
    public String leave(@RequestBody PlayerIdCommand command) {
        return playerConfigServicePort.leave(command);
    }

    @PostMapping("/read")
    public String read(@RequestBody PlayerIdCommand command) {
        return playerConfigServicePort.read(command);
    }


}