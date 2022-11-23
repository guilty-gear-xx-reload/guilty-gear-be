package ggxnet.reload.service;

import ggxnet.reload.repository.PlayerConfigMongoRepository;
import ggxnet.reload.service.dto.PlayerLobbyDto;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;

@Service
public class LobbyService {

  private final PlayerConfigMongoRepository playerConfigMongoRepository;

  public LobbyService(PlayerConfigMongoRepository playerConfigMongoRepository) {
    this.playerConfigMongoRepository = playerConfigMongoRepository;
  }

  public List<PlayerLobbyDto> getPlayersLobby() {
    return playerConfigMongoRepository.findAll().stream()
        .map(PlayerLobbyDto::of)
        .collect(Collectors.toList());
  }
}
