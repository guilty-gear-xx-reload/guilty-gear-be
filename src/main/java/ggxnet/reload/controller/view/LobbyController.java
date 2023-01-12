package ggxnet.reload.controller.view;

import ggxnet.reload.lobby.LobbyService;
import ggxnet.reload.utils.PageTitle;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(path = "/lobby")
class LobbyController {

  private final LobbyService lobbyService;

  public LobbyController(LobbyService lobbyService) {
    this.lobbyService = lobbyService;
  }

  @GetMapping
  public String getLobby(Model model) {
    model.addAttribute("title", PageTitle.LOBBY);
    model.addAttribute("playersLobbyData", lobbyService.getPlayersLobby());
    return "lobby";
  }
}
