package ggxnet.reload.lobby.domain.projection;

public interface PlayerConfigData {
    String getId();

    int getVersion();

    String getScriptAddress();

    String getUserName();

    String getTrip();

    Integer getPort();

    Integer getDelay();

    Integer getWait();

    Integer getDispInvCombo();

    PlayerStatsData getStats();

    Integer getSlowRate();

    Integer getRounds();

    String getMessage();

    long getLastActivity();

    boolean isIgnoreSlow();

    boolean isIgnoreMissNode();

    boolean isUseEx();

    boolean isEnableNet();

    boolean isFpsEnable();

    boolean isBroadcastEnable();

    boolean isIntrusionEnable();

    boolean isWatchReplayEnable();

    boolean isWatchMaxNodesEnable();

    boolean isActive();
}


