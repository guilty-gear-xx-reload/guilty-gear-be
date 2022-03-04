package ggxnet.reload.lobby.domain;

import ggxnet.reload.lobby.client.command.PlayerConfigCommand;
import ggxnet.reload.lobby.domain.projection.PlayerConfigData;
import lombok.Builder;
import lombok.Getter;

import java.time.Instant;

@Getter
@Builder
class PlayerConfig implements PlayerConfigData {
    String id;
    PlayerStats stats;
    int version;
    String scriptAddress;
    String userName;
    String trip;
    Integer port;
    Integer delay;
    Integer wait;
    Integer dispInvCombo;
    Integer slowRate;
    Integer rounds;
    String message;
    long lastActivity;
    boolean enableNet;
    boolean ignoreMissNode;
    boolean ignoreSlow;
    boolean useEx;
    boolean fpsEnable;
    boolean broadcastEnable;
    boolean intrusionEnable;
    boolean watchReplayEnable;
    boolean watchMaxNodesEnable;
    boolean active;

    static PlayerConfig of(PlayerConfigData playerConfigData) {
        return PlayerConfig.builder()
                .id(playerConfigData.getId())
                .stats(PlayerStats.of(playerConfigData.getStats()))
                .version(playerConfigData.getVersion())
                .scriptAddress(playerConfigData.getScriptAddress())
                .userName(playerConfigData.getUserName())
                .trip(playerConfigData.getTrip())
                .port(playerConfigData.getPort())
                .delay(playerConfigData.getDelay())
                .wait(playerConfigData.getWait())
                .dispInvCombo(playerConfigData.getDispInvCombo())
                .slowRate(playerConfigData.getSlowRate())
                .rounds(playerConfigData.getRounds())
                .message(playerConfigData.getMessage())
                .enableNet(playerConfigData.isEnableNet())
                .ignoreSlow(playerConfigData.isIgnoreSlow())
                .useEx(playerConfigData.isUseEx())
                .fpsEnable(playerConfigData.isFpsEnable())
                .broadcastEnable(playerConfigData.isBroadcastEnable())
                .intrusionEnable(playerConfigData.isIntrusionEnable())
                .watchReplayEnable(playerConfigData.isWatchReplayEnable())
                .watchMaxNodesEnable(playerConfigData.isWatchMaxNodesEnable())
                .active(playerConfigData.isActive())
                .lastActivity(playerConfigData.getLastActivity())
                .build();
    }

    public static PlayerConfig of(PlayerConfigCommand command) {
        return PlayerConfig.builder()
                .version(120)
                .scriptAddress(command.getScriptAddress())
                .userName(command.getUserName())
                .trip(command.getTrip())
                .port(command.getPort())
                .delay(command.getDelay())
                .ignoreMissNode(command.isIgnoreMisNode())
                .ignoreSlow(command.isIgnoreSlow())
                .wait(command.getWait())
                .useEx(command.isUseEx())
                .dispInvCombo(command.getDispInvCombo())
                .fpsEnable(command.isShowfps())
                .slowRate(command.getSlowRate())
                .rounds(command.getRounds())
                .message(command.getMsg())
                .enableNet(true)
                .broadcastEnable(command.isWatchBroadcast())
                .intrusionEnable(command.isWatchIntrusion())
                .watchReplayEnable(command.isWatchSaveReplay())
                .watchMaxNodesEnable(command.isWatchMaxNodes())
                .build();
    }

    public String parseToString() {
        return userName.concat("@")
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
        return "0".             //p_busy
                concat("06")    //lobby version
                .concat(getBooleanValue(useEx))
                .concat("00000")
                .concat("F")
                .concat(String.valueOf(delay));
    }


    public String parseToConfigString() {
        return version +
                "|" + scriptAddress +
                "|" + userName +
                "|" + trip +
                "|" + getBooleanValue(enableNet) +
                "|" + delay +
                "|" + getBooleanValue(ignoreMissNode) +
                "|" + getBooleanValue(ignoreSlow) +
                "|" + 3 +
                "|" + getBooleanValue(useEx) +
                "|" + dispInvCombo +
                "|" + getBooleanValue(fpsEnable) +
                "|" + stats.getWins() +
                "|" + stats.getRank() +
                "|" + stats.getScore() +
                "|" + stats.getTotalBattle() +
                "|" + stats.getTotalWin() +
                "|" + stats.getTotalLose() +
                "|" + stats.getTotalDraw() +
                "|" + stats.getTotalError() +
                "|" + slowRate +
                "|" + rounds +
                "|" + message +
                "|" + getBooleanValue(broadcastEnable) +
                "|" + getBooleanValue(intrusionEnable) +
                "|" + getBooleanValue(watchReplayEnable) +
                "|" + getBooleanValue(watchMaxNodesEnable)
                + "|";

    }

    private String getBooleanValue(boolean value) {
        return value ? "1" : "0";
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setPlayerStats(PlayerStats stats) {
        this.stats = stats;
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
