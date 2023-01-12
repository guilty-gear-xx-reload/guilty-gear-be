package ggxnet.reload.player;

import ggxnet.reload.controller.command.EnterCommand;
import ggxnet.reload.controller.command.PlayerConfigCommand;
import ggxnet.reload.controller.command.PlayerIdCommand;
import ggxnet.reload.controller.response.EnterResponse;
import ggxnet.reload.player.config.dto.PlayerConfigDto;
import ggxnet.reload.player.config.entity.PlayerConfigEntity;
import ggxnet.reload.player.config.repository.PlayerConfigRepository;
import ggxnet.reload.player.entity.PlayerEntity;
import ggxnet.reload.player.repository.PlayerRepository;
import ggxnet.reload.player.statistic.entity.PlayerStatsEntity;
import ggxnet.reload.player.statistic.repository.PlayerStatRepository;
import java.util.List;
import java.util.UUID;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class PlayerService {
  private final PlayerConfigRepository playerConfigRepository;
  private final PlayerStatRepository playerStatRepository;
  private final PlayerRepository playerRepository;

  public PlayerService(
      PlayerConfigRepository playerConfigRepository,
      PlayerStatRepository playerStatRepository,
      PlayerRepository playerRepository) {
    this.playerConfigRepository = playerConfigRepository;
    this.playerStatRepository = playerStatRepository;
    this.playerRepository = playerRepository;
  }

  public String read(PlayerIdCommand command) {
    StringBuilder playersToString = new StringBuilder();
    List<PlayerEntity> players =
        playerRepository.findAll().stream()
            .filter(playerEntity -> !playerEntity.getId().equals(command.getPlayerId()))
            .filter(playerEntity -> playerEntity.getConfig().isActive())
            .toList();
    for (PlayerEntity playerEntity : players) {
      playersToString.append(playerEntity.getConfig().parseToString(playerEntity.getStats()));
    }
    return playersToString.toString();
  }

  public EnterResponse enter(EnterCommand command) {
    PlayerConfigEntity playerConfigEntity =
        playerConfigRepository.findByPlayerId(command.getPlayerId()).orElseThrow();
    playerConfigEntity.activatePlayer(command.getPort());
    playerConfigRepository.save(playerConfigEntity);
    log.info("Player: {} entered the lobby", playerConfigEntity.getUserName());
    return new EnterResponse(playerConfigEntity.getScriptAddress(), playerConfigEntity.getPort());
  }

  public void leave(PlayerIdCommand command) {
    PlayerConfigEntity playerConfigEntity =
        playerConfigRepository.findByPlayerId(command.getPlayerId()).orElseThrow();
    playerConfigEntity.deactivate();
    playerConfigRepository.save(playerConfigEntity);
  }

  public void createConfig(PlayerConfigCommand command) {
    // PlayerStatsEntity playerStatsEntity = PlayerStatsEntity.of();
    String playerConfigId = UUID.randomUUID().toString();
    PlayerConfigEntity playerConfig = PlayerConfigEntity.of(playerConfigId, command);
    playerConfigRepository.save(playerConfig);
  }

  public PlayerConfigDto getPlayerConfig(String playerId) {
    PlayerConfigEntity playerConfigEntity =
        playerConfigRepository.findByPlayerId(playerId).orElseThrow();
    PlayerStatsEntity playerStatsEntity =
        playerStatRepository.findByPlayerId(playerId).orElseThrow();

    return PlayerConfigDto.of(playerConfigEntity, playerStatsEntity);
  }

  public void addWin(String playerId) {
    PlayerStatsEntity playerStatsEntity =
        playerStatRepository.findByPlayerId(playerId).orElseThrow();
    playerStatsEntity.addWin();
    playerStatRepository.save(playerStatsEntity);
  }

  public void addLose(String playerId) {
    PlayerStatsEntity playerStatsEntity =
        playerStatRepository.findByPlayerId(playerId).orElseThrow();
    playerStatsEntity.addLose();
    playerStatRepository.save(playerStatsEntity);
  }

  public void addDraw(String playerId) {
    PlayerStatsEntity playerStatsEntity =
        playerStatRepository.findByPlayerId(playerId).orElseThrow();
    playerStatsEntity.addDraw();
    playerStatRepository.save(playerStatsEntity);
  }
}
