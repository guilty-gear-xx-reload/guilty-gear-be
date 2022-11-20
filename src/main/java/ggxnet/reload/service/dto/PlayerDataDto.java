package ggxnet.reload.service.dto;

import ggxnet.reload.repository.entity.PlayerConfigEntity;
import ggxnet.reload.repository.entity.PlayerStatsEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class PlayerDataDto {

  private String nickname;
  private Boolean status;
  private Integer totalWins;
  private Integer totalLoses;
  private Integer totalDraws;
  private Integer totalGames;
  private Double winToLoses;

  public static PlayerDataDto of(PlayerConfigEntity playerConfig) {
    PlayerStatsEntity playerStats = playerConfig.getStats();
    int totalWin = playerStats.getTotalWin();
    int totalLose = playerStats.getTotalLose();
    return new PlayerDataDto(
        playerConfig.getUserName(),
        playerConfig.isActive(),
        totalWin,
        totalLose,
        playerStats.getTotalDraw(),
        playerStats.getTotalBattle(),
        totalLose > 0 ? (double) totalWin / totalLose : totalWin);
  }
}
