package ggxnet.reload.lobby;

public interface PlayerData {
    String getId();

    String getName();

    String getAddress();

    String getPort();

    String getParam();

    boolean isStatus();

    String getWin();

    long getTime();
}
