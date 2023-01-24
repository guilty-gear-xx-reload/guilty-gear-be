package ggxnet.reload.lobby.dto;

import ggxnet.reload.player.config.entity.PlayerConfigEntity;
import ggxnet.reload.player.entity.PlayerEntity;
import ggxnet.reload.player.statistic.entity.PlayerStatsEntity;
import java.util.Objects;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class PlayerLobbyDto {
  private String id;
  private String nickname;
  private Boolean status;
  private String ping;
  private Integer totalWins;
  private Integer totalLoses;
  private Integer totalDraws;
  private Integer totalGames;
  private Double winToLoses;

  public PlayerLobbyDto(
      String id,
      String nickname,
      Boolean status,
      String ping,
      Integer totalWins,
      Integer totalLoses,
      Integer totalDraws,
      Integer totalGames,
      Double winToLoses) {
    this.id = id;
    this.nickname = nickname;
    this.status = status;
    this.ping = ping;
    this.totalWins = totalWins;
    this.totalLoses = totalLoses;
    this.totalDraws = totalDraws;
    this.totalGames = totalGames;
    this.winToLoses = winToLoses;
  }

  public static PlayerLobbyDto of(PlayerEntity playerEntity) {
    PlayerConfigEntity playerConfig = playerEntity.getConfig();
    PlayerStatsEntity playerStats = playerEntity.getStats();
    int totalWin = playerStats.getTotalWin();
    int totalLose = playerStats.getTotalLose();
    boolean status = playerConfig.isActive();
    return new PlayerLobbyDto(
        playerConfig.getId(),
        playerConfig.getUserName(),
        status,
        !status ? "-" : "...",
        totalWin,
        totalLose,
        playerStats.getTotalDraw(),
        playerStats.getTotalBattle(),
        totalLose > 0 ? getWinPerLoses(totalWin, totalLose) : totalWin);
  }

  private static double getWinPerLoses(double totalWin, double totalLose) {
    return (Math.round((totalWin / totalLose) * 100) / 100.0);
  }

  public String id() {
    return id;
  }

  public String nickname() {
    return nickname;
  }

  public Boolean status() {
    return status;
  }

  public String ping() {
    return ping;
  }

  public Integer totalWins() {
    return totalWins;
  }

  public Integer totalLoses() {
    return totalLoses;
  }

  public Integer totalDraws() {
    return totalDraws;
  }

  public Integer totalGames() {
    return totalGames;
  }

  public Double winToLoses() {
    return winToLoses;
  }

  @Override
  public boolean equals(Object obj) {
    if (obj == this) return true;
    if (obj == null || obj.getClass() != this.getClass()) return false;
    var that = (PlayerLobbyDto) obj;
    return Objects.equals(this.id, that.id)
        && Objects.equals(this.nickname, that.nickname)
        && Objects.equals(this.status, that.status)
        && Objects.equals(this.ping, that.ping)
        && Objects.equals(this.totalWins, that.totalWins)
        && Objects.equals(this.totalLoses, that.totalLoses)
        && Objects.equals(this.totalDraws, that.totalDraws)
        && Objects.equals(this.totalGames, that.totalGames)
        && Objects.equals(this.winToLoses, that.winToLoses);
  }

  @Override
  public int hashCode() {
    return Objects.hash(
        id, nickname, status, ping, totalWins, totalLoses, totalDraws, totalGames, winToLoses);
  }

  @Override
  public String toString() {
    return "PlayerLobbyDto["
        + "id="
        + id
        + ", "
        + "nickname="
        + nickname
        + ", "
        + "status="
        + status
        + ", "
        + "ping="
        + ping
        + ", "
        + "totalWins="
        + totalWins
        + ", "
        + "totalLoses="
        + totalLoses
        + ", "
        + "totalDraws="
        + totalDraws
        + ", "
        + "totalGames="
        + totalGames
        + ", "
        + "winToLoses="
        + winToLoses
        + ']';
  }
}
