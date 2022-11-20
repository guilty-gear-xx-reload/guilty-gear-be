package ggxnet.reload.service;

import ggxnet.reload.repository.PlayerConfigMongoRepository;
import ggxnet.reload.service.dto.PlayerDataDto;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;

@Service
public class LobbyService {

  private final PlayerConfigMongoRepository playerConfigMongoRepository;

  public LobbyService(PlayerConfigMongoRepository playerConfigMongoRepository) {
    this.playerConfigMongoRepository = playerConfigMongoRepository;
  }

  public List<PlayerDataDto> getPlayersData() {
    return playerConfigMongoRepository.findAll().stream()
        .map(PlayerDataDto::of)
        .collect(Collectors.toList());
  }
}
