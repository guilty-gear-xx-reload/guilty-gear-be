package ggxnet.reload.controller;

import ggxnet.reload.controller.command.EnterCommand;
import ggxnet.reload.controller.command.PlayerConfigCommand;
import ggxnet.reload.controller.command.PlayerIdCommand;
import ggxnet.reload.service.PaletteService;
import ggxnet.reload.service.PlayerService;
import ggxnet.reload.service.dto.PlayerConfigDto;
import java.util.Map;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/rest")
class LobbyRestController {
  private final PlayerService playerService;
  private final PaletteService paletteService;

  LobbyRestController(PlayerService playerService, PaletteService paletteService) {
    this.playerService = playerService;
    this.paletteService = paletteService;
  }

  @PostMapping("/set-config")
  public void setPlayerConfig(@RequestBody PlayerConfigCommand command) {
    playerService.createConfig(command);
  }

  @PostMapping("/get-config")
  public PlayerConfigDto getPlayerConfig(@RequestBody PlayerIdCommand command) {
    return playerService.getPlayerConfig(command.getPlayerId());
  }

  @GetMapping
  public String handleGet() {
    return "Server works!";
  }

  @PostMapping("/enter")
  public EnterResponse enter(@RequestBody EnterCommand command) {
    return playerService.enter(command);
  }

  @PostMapping("/leave")
  public void leave(@RequestBody PlayerIdCommand command) {
    playerService.leave(command);
  }

  @PostMapping("/read")
  public String read(@RequestBody PlayerIdCommand command) {
    return playerService.read(command);
  }

  @PostMapping("/win")
  public void addWin(@RequestBody PlayerIdCommand command) {
    playerService.addWin(command.getPlayerId());
  }

  @PostMapping("/lose")
  public void addLose(@RequestBody PlayerIdCommand command) {
    playerService.addLose(command.getPlayerId());
  }

  @PostMapping("/draw")
  public void addDraw(@RequestBody PlayerIdCommand command) {
    playerService.addDraw(command.getPlayerId());
  }

  @PostMapping("/palettes")
  public Map<String, byte[]> getBinaryPalettesByPlayerId(@RequestBody PlayerIdCommand command) {
    return paletteService.getBinaryPalettesByPlayerId(command.getPlayerId());
  }
}
