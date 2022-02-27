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
    public String addWin(@RequestBody PlayerIdCommand command) {
        playerStatsServicePort.addWin(command.getPlayerId());
        return "";
    }

    @PostMapping("/lose")
    public String addLose(@RequestBody PlayerIdCommand command) {
        playerStatsServicePort.addLose(command.getPlayerId());
        return "";
    }

    @PostMapping("/draw")
    public String addDraw(@RequestBody PlayerIdCommand command) {
        playerStatsServicePort.addDraw(command.getPlayerId());
        return "";
    }

}

