package ggxnet.reload.repository.entity;

import ggxnet.reload.controller.command.PlayerConfigCommand;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.time.Instant;

@Entity
@Table(name = "player_config")
public class PlayerConfigEntity {
  @Id
  private String id;
  @OneToOne(mappedBy = "config")
  private PlayerEntity player;
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

  public PlayerConfigEntity() {
  }

  public static PlayerConfigEntity of(String id, PlayerConfigCommand command) {
    return PlayerConfigEntityBuilder.builder()
        .id(id)
     //   .player() todo
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

  public String parseToString(PlayerStatsEntity stats) {
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

  public PlayerEntity getPlayer() {
    return player;
  }

  public void setPlayer(PlayerEntity player) {
    this.player = player;
  }

  public void deactivate() {
    this.active = false;
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public int getVersion() {
    return version;
  }

  public void setVersion(int version) {
    this.version = version;
  }

  public String getScriptAddress() {
    return scriptAddress;
  }

  public void setScriptAddress(String scriptAddress) {
    this.scriptAddress = scriptAddress;
  }

  public String getUserName() {
    return userName;
  }

  public void setUserName(String userName) {
    this.userName = userName;
  }

  public String getTrip() {
    return trip;
  }

  public void setTrip(String trip) {
    this.trip = trip;
  }

  public Integer getPort() {
    return port;
  }

  public void setPort(Integer port) {
    this.port = port;
  }

  public Integer getDelay() {
    return delay;
  }

  public void setDelay(Integer delay) {
    this.delay = delay;
  }

  public Integer getWait() {
    return wait;
  }

  public void setWait(Integer wait) {
    this.wait = wait;
  }

  public Integer getDispInvCombo() {
    return dispInvCombo;
  }

  public void setDispInvCombo(Integer dispInvCombo) {
    this.dispInvCombo = dispInvCombo;
  }

  public Integer getSlowRate() {
    return slowRate;
  }

  public void setSlowRate(Integer slowRate) {
    this.slowRate = slowRate;
  }

  public Integer getRounds() {
    return rounds;
  }

  public void setRounds(Integer rounds) {
    this.rounds = rounds;
  }

  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }

  public long getLastActivity() {
    return lastActivity;
  }

  public void setLastActivity(long lastActivity) {
    this.lastActivity = lastActivity;
  }

  public boolean isEnableNet() {
    return enableNet;
  }

  public void setEnableNet(boolean enableNet) {
    this.enableNet = enableNet;
  }

  public boolean isIgnoreMissNode() {
    return ignoreMissNode;
  }

  public void setIgnoreMissNode(boolean ignoreMissNode) {
    this.ignoreMissNode = ignoreMissNode;
  }

  public boolean isIgnoreSlow() {
    return ignoreSlow;
  }

  public void setIgnoreSlow(boolean ignoreSlow) {
    this.ignoreSlow = ignoreSlow;
  }

  public boolean isUseEx() {
    return useEx;
  }

  public void setUseEx(boolean useEx) {
    this.useEx = useEx;
  }

  public boolean isFpsEnable() {
    return fpsEnable;
  }

  public void setFpsEnable(boolean fpsEnable) {
    this.fpsEnable = fpsEnable;
  }

  public boolean isBroadcastEnable() {
    return broadcastEnable;
  }

  public void setBroadcastEnable(boolean broadcastEnable) {
    this.broadcastEnable = broadcastEnable;
  }

  public boolean isIntrusionEnable() {
    return intrusionEnable;
  }

  public void setIntrusionEnable(boolean intrusionEnable) {
    this.intrusionEnable = intrusionEnable;
  }

  public boolean isWatchReplayEnable() {
    return watchReplayEnable;
  }

  public void setWatchReplayEnable(boolean watchReplayEnable) {
    this.watchReplayEnable = watchReplayEnable;
  }

  public boolean isWatchMaxNodesEnable() {
    return watchMaxNodesEnable;
  }

  public void setWatchMaxNodesEnable(boolean watchMaxNodesEnable) {
    this.watchMaxNodesEnable = watchMaxNodesEnable;
  }

  public boolean isActive() {
    return active;
  }

  public void setActive(boolean active) {
    this.active = active;
  }

  public static final class PlayerConfigEntityBuilder {
    private String id;
    private PlayerEntity player;
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

    private PlayerConfigEntityBuilder() {
    }

    public static PlayerConfigEntityBuilder builder() {
      return new PlayerConfigEntityBuilder();
    }

    public PlayerConfigEntityBuilder id(String id) {
      this.id = id;
      return this;
    }

    public PlayerConfigEntityBuilder player(PlayerEntity player) {
      this.player = player;
      return this;
    }

    public PlayerConfigEntityBuilder version(int version) {
      this.version = version;
      return this;
    }

    public PlayerConfigEntityBuilder scriptAddress(String scriptAddress) {
      this.scriptAddress = scriptAddress;
      return this;
    }

    public PlayerConfigEntityBuilder userName(String userName) {
      this.userName = userName;
      return this;
    }

    public PlayerConfigEntityBuilder trip(String trip) {
      this.trip = trip;
      return this;
    }

    public PlayerConfigEntityBuilder port(Integer port) {
      this.port = port;
      return this;
    }

    public PlayerConfigEntityBuilder delay(Integer delay) {
      this.delay = delay;
      return this;
    }

    public PlayerConfigEntityBuilder wait(Integer wait) {
      this.wait = wait;
      return this;
    }

    public PlayerConfigEntityBuilder dispInvCombo(Integer dispInvCombo) {
      this.dispInvCombo = dispInvCombo;
      return this;
    }

    public PlayerConfigEntityBuilder slowRate(Integer slowRate) {
      this.slowRate = slowRate;
      return this;
    }

    public PlayerConfigEntityBuilder rounds(Integer rounds) {
      this.rounds = rounds;
      return this;
    }

    public PlayerConfigEntityBuilder message(String message) {
      this.message = message;
      return this;
    }

    public PlayerConfigEntityBuilder lastActivity(long lastActivity) {
      this.lastActivity = lastActivity;
      return this;
    }

    public PlayerConfigEntityBuilder enableNet(boolean enableNet) {
      this.enableNet = enableNet;
      return this;
    }

    public PlayerConfigEntityBuilder ignoreMissNode(boolean ignoreMissNode) {
      this.ignoreMissNode = ignoreMissNode;
      return this;
    }

    public PlayerConfigEntityBuilder ignoreSlow(boolean ignoreSlow) {
      this.ignoreSlow = ignoreSlow;
      return this;
    }

    public PlayerConfigEntityBuilder useEx(boolean useEx) {
      this.useEx = useEx;
      return this;
    }

    public PlayerConfigEntityBuilder fpsEnable(boolean fpsEnable) {
      this.fpsEnable = fpsEnable;
      return this;
    }

    public PlayerConfigEntityBuilder broadcastEnable(boolean broadcastEnable) {
      this.broadcastEnable = broadcastEnable;
      return this;
    }

    public PlayerConfigEntityBuilder intrusionEnable(boolean intrusionEnable) {
      this.intrusionEnable = intrusionEnable;
      return this;
    }

    public PlayerConfigEntityBuilder watchReplayEnable(boolean watchReplayEnable) {
      this.watchReplayEnable = watchReplayEnable;
      return this;
    }

    public PlayerConfigEntityBuilder watchMaxNodesEnable(boolean watchMaxNodesEnable) {
      this.watchMaxNodesEnable = watchMaxNodesEnable;
      return this;
    }

    public PlayerConfigEntityBuilder active(boolean active) {
      this.active = active;
      return this;
    }

    public PlayerConfigEntity build() {
      PlayerConfigEntity playerConfigEntity = new PlayerConfigEntity();
      playerConfigEntity.setId(id);
      playerConfigEntity.setPlayer(player);
      playerConfigEntity.setVersion(version);
      playerConfigEntity.setScriptAddress(scriptAddress);
      playerConfigEntity.setUserName(userName);
      playerConfigEntity.setTrip(trip);
      playerConfigEntity.setPort(port);
      playerConfigEntity.setDelay(delay);
      playerConfigEntity.setWait(wait);
      playerConfigEntity.setDispInvCombo(dispInvCombo);
      playerConfigEntity.setSlowRate(slowRate);
      playerConfigEntity.setRounds(rounds);
      playerConfigEntity.setMessage(message);
      playerConfigEntity.setLastActivity(lastActivity);
      playerConfigEntity.setEnableNet(enableNet);
      playerConfigEntity.setIgnoreMissNode(ignoreMissNode);
      playerConfigEntity.setIgnoreSlow(ignoreSlow);
      playerConfigEntity.setUseEx(useEx);
      playerConfigEntity.setFpsEnable(fpsEnable);
      playerConfigEntity.setBroadcastEnable(broadcastEnable);
      playerConfigEntity.setIntrusionEnable(intrusionEnable);
      playerConfigEntity.setWatchReplayEnable(watchReplayEnable);
      playerConfigEntity.setWatchMaxNodesEnable(watchMaxNodesEnable);
      playerConfigEntity.setActive(active);
      return playerConfigEntity;
    }
  }
}
