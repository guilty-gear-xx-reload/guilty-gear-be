package ggxnet.reload.lobby.client;

import ggxnet.reload.lobby.client.command.PlayerConfigCommand;
import ggxnet.reload.lobby.client.command.PlayerIdCommand;
import ggxnet.reload.lobby.domain.port.incoming.PlayerConfigServicePort;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/rest") // FIXME - czemu tu jest w ten sposób a w LobbyRestController przez alias?
@RequiredArgsConstructor
class PlayerConfigRestController {
    private final PlayerConfigServicePort playerConfigServicePort;


    @PostMapping("/set-config")
    public void setPlayerConfig(@RequestBody PlayerConfigCommand command) {
        playerConfigServicePort.createConfig(command);
    }


    @PostMapping("/get-config")
    public String getPlayerConfig(@RequestBody PlayerIdCommand command) {
        return playerConfigServicePort.getPlayerConfig(command.getPlayerId());
    }
}
