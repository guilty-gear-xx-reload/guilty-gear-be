package ggxnet.reload.service;

import ggxnet.reload.repository.PlayerConfigRepository;
import ggxnet.reload.service.dto.PlayerLobbyDto;
import org.springframework.stereotype.Service;

import java.util.List;

import static java.util.stream.Collectors.toList;

@Service
public class LobbyService {

  public final PlayerConfigRepository playerConfigRepository;

  public LobbyService(PlayerConfigRepository playerConfigRepository) {
    this.playerConfigRepository = playerConfigRepository;
  }

  public List<PlayerLobbyDto> getPlayersLobby() {
    return playerConfigRepository.findAll().stream()
        .map(PlayerLobbyDto::of)
        .collect(toList());
  }
}
