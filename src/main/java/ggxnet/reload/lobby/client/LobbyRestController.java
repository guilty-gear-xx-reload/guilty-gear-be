package ggxnet.reload.lobby.client;

import ggxnet.reload.lobby.client.command.EnterCommand;
import ggxnet.reload.lobby.client.command.PlayerIdCommand;
import ggxnet.reload.lobby.domain.port.incoming.PlayerConfigServicePort;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping(path = "/rest") // FIXME - czytaj PlayerConfigRestController
@RequiredArgsConstructor
class LobbyRestController {
    private final PlayerConfigServicePort playerConfigServicePort;

    @GetMapping
    public String handleGet() {
        return "Server works!";
    }

    @PostMapping("/enter")
    public String enter(@RequestBody EnterCommand command) {
        return playerConfigServicePort.enter(command);
    }

    @PostMapping("/leave")
    public void leave(@RequestBody PlayerIdCommand command) {
        playerConfigServicePort.leave(command);
    }

    @PostMapping("/read")
    public String read(@RequestBody PlayerIdCommand command) {
        return playerConfigServicePort.read(command);
    }


}