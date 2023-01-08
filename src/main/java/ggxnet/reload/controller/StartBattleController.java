package ggxnet.reload.controller;

import ggxnet.reload.service.StartBattleService;
import ggxnet.reload.service.dto.CommandRunProcess;
import ggxnet.reload.service.dto.PaletteColorsDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/battles")
@RequiredArgsConstructor
public class StartBattleController {

    private final StartBattleService startBattleService;

    @PostMapping("/start")
    public void runGameProcess(@RequestBody CommandRunProcess commandRunProcess) {
        startBattleService.runGameProcess(commandRunProcess);
    }
}
