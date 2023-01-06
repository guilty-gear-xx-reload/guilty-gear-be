package ggxnet.reload.service.dto;

import ggxnet.reload.repository.entity.PlayerConfigEntity;
import ggxnet.reload.repository.entity.PlayerStatsEntity;

import java.io.Serializable;

public record PlayerConfigDto(
    int version,
    String scriptAddress,
    String userName,
    String trip,
    Integer port,
    Integer delay,
    Integer waitTime,
    Integer dispInvCombo,
    Integer slowRate,
    Integer rounds,
    String message,
    long lastActivity,
    boolean enableNet,
    boolean ignoreMissNode,
    boolean ignoreSlow,
    boolean useEx,
    boolean fpsEnable,
    boolean broadcastEnable,
    boolean intrusionEnable,
    boolean watchReplayEnable,
    boolean watchMaxNodesEnable,
    boolean active,
    int wins,
    int rank,
    int score,
    int totalBattle,
    int totalWin,
    int totalLose,
    int totalDraw,
    int totalError
) implements Serializable {

  public static PlayerConfigDto of(PlayerConfigEntity playerConfigEntity) {
    PlayerStatsEntity playerStats = playerConfigEntity.getStats();
    return new PlayerConfigDto(playerConfigEntity.getVersion(), playerConfigEntity.getScriptAddress(), playerConfigEntity.getUserName(),
        playerConfigEntity.getTrip(), playerConfigEntity.getPort(), playerConfigEntity.getDelay(), playerConfigEntity.getWait(),
        playerConfigEntity.getDispInvCombo(),
        playerConfigEntity.getSlowRate(), playerConfigEntity.getRounds(), playerConfigEntity.getMessage(), playerConfigEntity.getLastActivity(),
        playerConfigEntity.isEnableNet(),
        playerConfigEntity.isIgnoreMissNode(), playerConfigEntity.isUseEx(), playerConfigEntity.isFpsEnable(), playerConfigEntity.isBroadcastEnable(),
        playerConfigEntity.isIntrusionEnable(),
        playerConfigEntity.isWatchReplayEnable(), playerConfigEntity.isWatchReplayEnable(), playerConfigEntity.isWatchMaxNodesEnable(),
        playerConfigEntity.isActive(), playerStats.getWins(), playerStats.getRank(), playerStats.getScore(), playerStats.getTotalBattle(),
        playerStats.getTotalWin(), playerStats.getTotalLose(), playerStats.getTotalDraw(), playerStats.getTotalError());
  }
}
