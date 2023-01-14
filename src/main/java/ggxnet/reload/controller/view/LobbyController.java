package ggxnet.reload.controller.view;

import ggxnet.reload.lobby.LobbyService;
import ggxnet.reload.lobby.dto.PlayerLobbyDto;
import ggxnet.reload.utils.PageTitle;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(path = "/lobby")
class LobbyController {

  private final LobbyService lobbyService;

  public LobbyController(LobbyService lobbyService) {
    this.lobbyService = lobbyService;
  }


  @GetMapping
  public List<PlayerLobbyDto> getLobby() {
    return lobbyService.getPlayersLobby();
  }
}
