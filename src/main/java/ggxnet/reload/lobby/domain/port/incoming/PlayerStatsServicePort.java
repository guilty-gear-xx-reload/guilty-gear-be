package ggxnet.reload.lobby.domain.port.incoming;

public interface PlayerStatsServicePort {
    void addWin(String playerId);

    void addLose(String playerId);

    void addDraw(String playerId);
}
