package ggxnet.reload.service;

import ggxnet.reload.controller.command.EnterCommand;
import ggxnet.reload.controller.command.PlayerConfigCommand;
import ggxnet.reload.controller.command.PlayerIdCommand;
import ggxnet.reload.repository.PlayerConfigRepository;
import ggxnet.reload.repository.entity.PlayerConfigEntity;
import ggxnet.reload.repository.entity.PlayerStatsEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
@Slf4j
@Service
public class PlayerService {
  private final PlayerConfigRepository playerConfigRepository;

  public PlayerService(PlayerConfigRepository playerConfigRepository) {
    this.playerConfigRepository = playerConfigRepository;
  }

  public String read(PlayerIdCommand command) {
    StringBuilder playersToString = new StringBuilder();
    List<PlayerConfigEntity> playersConfig =
        playerConfigRepository.findAll().stream()
            .filter(playerConfigEntity -> !playerConfigEntity.getId().equals(command.getPlayerId()))
            .filter(PlayerConfigEntity::isActive)
            .collect(Collectors.toList());
    for (PlayerConfigEntity playerConfigEntity : playersConfig) {
      playersToString.append(playerConfigEntity.parseToString());
    }
    return playersToString.toString();
  }

  public String enter(EnterCommand command) {
    PlayerConfigEntity playerConfigEntity = playerConfigRepository.findById(command.getPlayerId()).orElseThrow();
    playerConfigEntity.activatePlayer(command.getPort());
    playerConfigRepository.save(playerConfigEntity);
    log.info("Player: {} entered the lobby", playerConfigEntity.getUserName());
    return playerConfigEntity
        .getScriptAddress()
        .concat(":")
        .concat(String.valueOf(playerConfigEntity.getPort()));
  }

  public void leave(PlayerIdCommand command) {
    PlayerConfigEntity playerConfigEntity =
        playerConfigRepository.findById(command.getPlayerId()).orElseThrow();
    playerConfigEntity.deactivate();
    playerConfigRepository.save(playerConfigEntity);
  }

  public void createConfig(PlayerConfigCommand command) {
    PlayerStatsEntity playerStatsEntity = PlayerStatsEntity.of();
    String playerConfigId = UUID.randomUUID().toString();
    PlayerConfigEntity playerConfig =
        PlayerConfigEntity.of(playerConfigId, command, playerStatsEntity);
    playerConfigRepository.save(playerConfig);
  }

  public String getPlayerConfig(String playerId) {
    PlayerConfigEntity playerConfigEntity =
        playerConfigRepository.findById(playerId).orElseThrow();

    return playerConfigEntity.parseToConfigString();
  }

  public void addWin(String playerId) {
    PlayerConfigEntity playerConfigEntity =
        playerConfigRepository.findById(playerId).orElseThrow();
    playerConfigEntity.getStats().addWin();
    playerConfigRepository.save(playerConfigEntity);
  }

  public void addLose(String playerId) {
    PlayerConfigEntity playerConfigEntity =
        playerConfigRepository.findById(playerId).orElseThrow();
    playerConfigEntity.getStats().addLose();
    playerConfigRepository.save(playerConfigEntity);
  }

  public void addDraw(String playerId) {
    PlayerConfigEntity playerConfigEntity =
        playerConfigRepository.findById(playerId).orElseThrow();
    playerConfigEntity.getStats().addDraw();
    playerConfigRepository.save(playerConfigEntity);
  }
}
