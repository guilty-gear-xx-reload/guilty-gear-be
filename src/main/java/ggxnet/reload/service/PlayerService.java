package ggxnet.reload.service;

import ggxnet.reload.controller.command.EnterCommand;
import ggxnet.reload.controller.command.PlayerConfigCommand;
import ggxnet.reload.controller.command.PlayerIdCommand;
import ggxnet.reload.repository.PlayerConfigMongoRepository;
import ggxnet.reload.repository.entity.PlayerConfigEntity;
import ggxnet.reload.repository.entity.PlayerStatsEntity;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@RequiredArgsConstructor
@Service
public class PlayerService {
  private final PlayerConfigMongoRepository playerConfigMongoRepository;

  public String read(PlayerIdCommand command) {
    StringBuilder playersToString = new StringBuilder();
    List<PlayerConfigEntity> playersConfig =
        playerConfigMongoRepository.findAll().stream()
            .filter(playerConfigEntity -> !playerConfigEntity.getId().equals(command.getPlayerId()))
            .filter(PlayerConfigEntity::isActive)
            .collect(Collectors.toList());
    for (PlayerConfigEntity playerConfigEntity : playersConfig) {
      playersToString.append(playerConfigEntity.parseToString());
    }
    return playersToString.toString();
  }

  public String enter(EnterCommand command) {
    PlayerConfigEntity playerConfigEntity =
        playerConfigMongoRepository.findById(command.getPlayerId()).orElseThrow();
    playerConfigEntity.activatePlayer(command.getPort());
    playerConfigMongoRepository.save(playerConfigEntity);
    log.info("Player: {} entered the lobby", playerConfigEntity.getUserName());
    return playerConfigEntity
        .getScriptAddress()
        .concat(":")
        .concat(String.valueOf(playerConfigEntity.getPort()));
  }

  public void leave(PlayerIdCommand command) {
    PlayerConfigEntity playerConfigEntity =
        playerConfigMongoRepository.findById(command.getPlayerId()).orElseThrow();
    playerConfigEntity.deactivate();
    playerConfigMongoRepository.save(playerConfigEntity);
  }

  public void createConfig(PlayerConfigCommand command) {
    PlayerStatsEntity playerStatsEntity = PlayerStatsEntity.of();
    String playerConfigId = UUID.randomUUID().toString();
    PlayerConfigEntity playerConfig =
        PlayerConfigEntity.of(playerConfigId, command, playerStatsEntity);
    playerConfigMongoRepository.save(playerConfig);
  }

  public String getPlayerConfig(String playerId) {
    PlayerConfigEntity playerConfigEntity =
        playerConfigMongoRepository.findById(playerId).orElseThrow();

    return playerConfigEntity.parseToConfigString();
  }

  public void addWin(String playerId) {
    PlayerConfigEntity playerConfigEntity =
        playerConfigMongoRepository.findById(playerId).orElseThrow();
    playerConfigEntity.getStats().addWin();
    playerConfigMongoRepository.save(playerConfigEntity);
  }

  public void addLose(String playerId) {
    PlayerConfigEntity playerConfigEntity =
        playerConfigMongoRepository.findById(playerId).orElseThrow();
    playerConfigEntity.getStats().addLose();
    playerConfigMongoRepository.save(playerConfigEntity);
  }

  public void addDraw(String playerId) {
    PlayerConfigEntity playerConfigEntity =
        playerConfigMongoRepository.findById(playerId).orElseThrow();
    playerConfigEntity.getStats().addDraw();
    playerConfigMongoRepository.save(playerConfigEntity);
  }
}
