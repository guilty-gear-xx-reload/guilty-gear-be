package ggxnet.reload.lobby.domain;

import ggxnet.reload.lobby.domain.projection.PlayerStatsData;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
class PlayerStats implements PlayerStatsData {
    String id;
    int wins;
    int rank;
    int score;
    int totalBattle;
    int totalWin;
    int totalLose;
    int totalDraw;
    int totalError;

    static PlayerStats of(String id) {
        return PlayerStats.builder()
                .id(id)
                .build();
    }

    static PlayerStats of(PlayerStatsData stats) {
        return PlayerStats.builder()
                .id(stats.getId())
                .wins(stats.getWins())
                .rank(stats.getRank())
                .score(stats.getScore())
                .totalBattle(stats.getTotalBattle())
                .totalWin(stats.getTotalWin())
                .totalLose(stats.getTotalLose())
                .totalDraw(stats.getTotalDraw())
                .totalError(stats.getTotalError())
                .build();
    }

    void win() {
        this.wins++;
        this.totalWin++;
        this.totalBattle++;
    }

    void lose() {
        this.totalLose++;
        this.totalBattle++;
    }

    void draw() {
        this.totalDraw++;
        this.totalBattle++;
    }
}
