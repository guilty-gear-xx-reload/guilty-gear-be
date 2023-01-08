package ggxnet.reload.service;

import ggxnet.reload.service.dto.CommandRunProcess;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class StartBattleService {
    public void runGameProcess(CommandRunProcess gameLocationPath) {
        try {
            Process p = Runtime.getRuntime().exec(gameLocationPath.gameLocationPath());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
