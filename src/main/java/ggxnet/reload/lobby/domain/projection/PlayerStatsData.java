package ggxnet.reload.lobby.domain.projection;

public interface PlayerStatsData {

    String getId();

    int getWins();

    int getRank();

    int getScore();

    int getTotalBattle();

    int getTotalWin();

    int getTotalLose();

    int getTotalDraw();

    int getTotalError();
}
