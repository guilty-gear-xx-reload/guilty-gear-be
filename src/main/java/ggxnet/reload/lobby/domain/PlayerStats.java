package ggxnet.reload.lobby.domain;

import ggxnet.reload.lobby.domain.projection.PlayerStatsData;
import lombok.Builder;
import lombok.Getter;
import org.springframework.data.annotation.Id;

@Getter
@Builder
class PlayerStats implements PlayerStatsData {
    @Id
    String id;
    int wins;
    int rank;
    int score;
    int totalBattle;
    int totalWin;
    int totalLose;
    int totalDraw;
    int totalError;

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

    void addWin() {
        this.wins++;
        this.totalWin++;
        this.totalBattle++;
    }

    void addLose() {
        this.totalLose++;
        this.totalBattle++;
    }

    void addDraw() {
        this.totalDraw++;
        this.totalBattle++;
    }
}
