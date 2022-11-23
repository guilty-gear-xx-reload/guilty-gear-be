package ggxnet.reload.repository.entity;

import ggxnet.reload.controller.command.PlayerConfigCommand;
import java.time.Instant;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "config")
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PlayerConfigEntity {
  @Id private String id;
  @DBRef private PlayerStatsEntity stats;
  private int version;
  private String scriptAddress;
  private String userName;
  private String trip;
  private Integer port;
  private Integer delay;
  private Integer wait;
  private Integer dispInvCombo;
  private Integer slowRate;
  private Integer rounds;
  private String message;
  private long lastActivity;
  private boolean enableNet;
  private boolean ignoreMissNode;
  private boolean ignoreSlow;
  private boolean useEx;
  private boolean fpsEnable;
  private boolean broadcastEnable;
  private boolean intrusionEnable;
  private boolean watchReplayEnable;
  private boolean watchMaxNodesEnable;
  private boolean active;

  public static PlayerConfigEntity of(
      String id, PlayerConfigCommand command, PlayerStatsEntity playerStatsEntity) {
    return PlayerConfigEntity.builder()
        .id(id)
        .stats(playerStatsEntity)
        .version(120)
        .scriptAddress(command.getScriptAddress())
        .userName(command.getUserName())
        .trip(command.getTrip())
        .port(command.getPort())
        .delay(command.getDelay())
        .wait(command.getWait())
        .dispInvCombo(command.getDispInvCombo())
        .slowRate(command.getSlowRate())
        .rounds(command.getRounds())
        .message(command.getMsg())
        .enableNet(true)
        .ignoreSlow(command.isIgnoreSlow())
        .useEx(command.isUseEx())
        .fpsEnable(command.isShowfps())
        .broadcastEnable(command.isWatchBroadcast())
        .intrusionEnable(command.isWatchIntrusion())
        .watchMaxNodesEnable(command.isWatchMaxNodes())
        .active(false)
        .lastActivity(0)
        .build();
  }

  public String parseToConfigString() {
    return version
        + "|"
        + scriptAddress
        + "|"
        + userName
        + "|"
        + trip
        + "|"
        + getBooleanValue(enableNet)
        + "|"
        + delay
        + "|"
        + getBooleanValue(ignoreMissNode)
        + "|"
        + getBooleanValue(ignoreSlow)
        + "|"
        + 3
        + "|"
        + getBooleanValue(useEx)
        + "|"
        + dispInvCombo
        + "|"
        + getBooleanValue(fpsEnable)
        + "|"
        + stats.getWins()
        + "|"
        + stats.getRank()
        + "|"
        + stats.getScore()
        + "|"
        + stats.getTotalBattle()
        + "|"
        + stats.getTotalWin()
        + "|"
        + stats.getTotalLose()
        + "|"
        + stats.getTotalDraw()
        + "|"
        + stats.getTotalError()
        + "|"
        + slowRate
        + "|"
        + rounds
        + "|"
        + message
        + "|"
        + getBooleanValue(broadcastEnable)
        + "|"
        + getBooleanValue(intrusionEnable)
        + "|"
        + getBooleanValue(watchReplayEnable)
        + "|"
        + getBooleanValue(watchMaxNodesEnable)
        + "|";
  }

  public String parseToString() {
    return userName
        .concat("@")
        .concat(scriptAddress)
        .concat(":")
        .concat(String.valueOf(port))
        .concat("%")
        .concat(createParam())
        .concat("#")
        .concat(String.valueOf(stats.getWins()))
        .concat("\r\n");
  }

  private String createParam() {
    return "0"
        . // p_busy
        concat("06") // lobby version
        .concat(getBooleanValue(useEx))
        .concat("00000")
        .concat("F")
        .concat(String.valueOf(delay));
  }

  private String getBooleanValue(boolean value) {
    return value ? "1" : "0";
  }

  public void activatePlayer(Integer port) {
    this.active = true;
    this.lastActivity = Instant.now().getEpochSecond();
    this.port = port;
  }

  public void deactivate() {
    this.active = false;
  }
}
