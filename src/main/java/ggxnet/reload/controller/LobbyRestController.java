package ggxnet.reload.controller;

import ggxnet.reload.controller.command.EnterCommand;
import ggxnet.reload.controller.command.PlayerConfigCommand;
import ggxnet.reload.controller.command.PlayerIdCommand;
import ggxnet.reload.service.PlayerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/rest")
@RequiredArgsConstructor
class LobbyRestController {
  private final PlayerService playerService;

  @PostMapping("/set-config")
  public void setPlayerConfig(@RequestBody PlayerConfigCommand command) {
    playerService.createConfig(command);
  }

  @PostMapping("/get-config")
  public String getPlayerConfig(@RequestBody PlayerIdCommand command) {
    return playerService.getPlayerConfig(command.getPlayerId());
  }

  @GetMapping
  public String handleGet() {
    return "Server works!";
  }

  @PostMapping("/enter")
  public String enter(@RequestBody EnterCommand command) {
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

  // todo add to c++
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
}
