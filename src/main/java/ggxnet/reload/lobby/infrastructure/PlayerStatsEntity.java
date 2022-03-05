package ggxnet.reload.lobby.infrastructure;

import ggxnet.reload.lobby.domain.projection.PlayerStatsData;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "stats")
@NoArgsConstructor
@Getter
@Builder
@AllArgsConstructor
class PlayerStatsEntity implements PlayerStatsData {
    @Id
    private String id;
    private int wins;
    private int rank;
    private int score;
    private int totalBattle;
    private int totalWin;
    private int totalLose;
    private int totalDraw;
    private int totalError;

    public static PlayerStatsEntity of(PlayerStatsData playerStats) {
        return PlayerStatsEntity.builder()
                .id(playerStats.getId())
                .wins(playerStats.getWins())
                .rank(playerStats.getRank())
                .score(playerStats.getScore())
                .totalBattle(playerStats.getTotalBattle())
                .totalWin(playerStats.getTotalWin())
                .totalLose(playerStats.getTotalLose())
                .totalDraw(playerStats.getTotalDraw())
                .totalError(playerStats.getTotalError())
                .build();
    }

}
