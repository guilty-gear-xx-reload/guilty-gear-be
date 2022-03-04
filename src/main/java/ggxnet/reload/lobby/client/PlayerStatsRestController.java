package ggxnet.reload.lobby.client;

import ggxnet.reload.lobby.client.command.PlayerIdCommand;
import ggxnet.reload.lobby.domain.port.incoming.PlayerStatsServicePort;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
class PlayerStatsRestController {

    private final PlayerStatsServicePort playerStatsServicePort;

    @PostMapping("/win")
    public void addWin(@RequestBody PlayerIdCommand command) {
        playerStatsServicePort.addWin(command.getPlayerId());
    }

    @PostMapping("/lose")
    public void addLose(@RequestBody PlayerIdCommand command) {
        playerStatsServicePort.addLose(command.getPlayerId());
    }

    @PostMapping("/draw")
    public void addDraw(@RequestBody PlayerIdCommand command) {
        playerStatsServicePort.addDraw(command.getPlayerId());
    }

}

