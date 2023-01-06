package ggxnet.reload.service;

import ggxnet.reload.repository.PlayerConfigRepository;
import ggxnet.reload.repository.PlayerRepository;
import ggxnet.reload.service.dto.PlayerLobbyDto;
import org.springframework.stereotype.Service;

import java.util.List;

import static java.util.stream.Collectors.toList;

@Service
public class LobbyService {

  public final PlayerConfigRepository playerConfigRepository;
  public final PlayerRepository playerRepository;
  public LobbyService(PlayerConfigRepository playerConfigRepository, PlayerRepository playerRepository) {
    this.playerConfigRepository = playerConfigRepository;
    this.playerRepository = playerRepository;
  }

  public List<PlayerLobbyDto> getPlayersLobby() {
    return playerRepository.findAll().stream()
        .map(PlayerLobbyDto::of)
        .collect(toList());
  }
}
