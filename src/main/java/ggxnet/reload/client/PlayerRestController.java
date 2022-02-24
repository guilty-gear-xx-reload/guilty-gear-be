package ggxnet.reload.client;

import ggxnet.reload.client.command.GameCommand;
import ggxnet.reload.infrastructure.PlayerSettingsEntity;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
public class PlayerRestController {

    @PostMapping("/set-config")
    public String setPlayerConfig(@RequestBody PlayerSettingsEntity playerSettingsEntity){
        return "";
    }
    @PostMapping("/get-config")
    public String getPlayerConfig(GameCommand command) {
        return PlayerSettingsEntity.builder()
                .ver(120)
                .userName("ArekTest")
                .scriptAddress("26.68.204.99")
                .enableNet(1)
                .port(10000)
                .delay(4)
                .ignoreMisNode(1)
                //.wait()
                .useEx(1)
                .dispInvCombo(1)
                .showfps(1)
                .wins(10)
                .rank(6)
                .score(3600)
                .totalBattle(13)
                .totalWin(6)
                .totalLose(10)
                .totalDraw(0)
                .slowRate(0)
                .rounds(5)
                .msg("TEST")
                .watchBroadcast(1)
                .watchIntrusion(0)
                .watchSaveReplay(0)
                .watchMaxNodes(1)
                .ignoreSlow(1)
                .build()
                .toString();
    }
}
