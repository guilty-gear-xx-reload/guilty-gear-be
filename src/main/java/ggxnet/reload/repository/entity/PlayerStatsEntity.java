package ggxnet.reload.repository.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.util.UUID;

@Entity
@Table(name = "player_stats")
public class PlayerStatsEntity {
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
  @OneToOne(mappedBy = "stats")
  private PlayerConfigEntity playerConfig;

  public PlayerStatsEntity() {
  }

  public static PlayerStatsEntity of() {
    String id = UUID.randomUUID().toString();
    return PlayerStatsEntityBuilder.builder()
        .id(id)
        .wins(0)
        .rank(0)
        .score(0)
        .totalBattle(0)
        .totalWin(0)
        .totalLose(0)
        .totalDraw(0)
        .totalError(0)
        .build();
  }

  public void addWin() {
    this.wins++;
    this.totalWin++;
    this.totalBattle++;
  }

  public void addLose() {
    this.totalLose++;
    this.totalBattle++;
  }

  public void addDraw() {
    this.totalDraw++;
    this.totalBattle++;
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public int getWins() {
    return wins;
  }

  public void setWins(int wins) {
    this.wins = wins;
  }

  public int getRank() {
    return rank;
  }

  public void setRank(int rank) {
    this.rank = rank;
  }

  public int getScore() {
    return score;
  }

  public void setScore(int score) {
    this.score = score;
  }

  public int getTotalBattle() {
    return totalBattle;
  }

  public void setTotalBattle(int totalBattle) {
    this.totalBattle = totalBattle;
  }

  public int getTotalWin() {
    return totalWin;
  }

  public void setTotalWin(int totalWin) {
    this.totalWin = totalWin;
  }

  public int getTotalLose() {
    return totalLose;
  }

  public void setTotalLose(int totalLose) {
    this.totalLose = totalLose;
  }

  public int getTotalDraw() {
    return totalDraw;
  }

  public void setTotalDraw(int totalDraw) {
    this.totalDraw = totalDraw;
  }

  public int getTotalError() {
    return totalError;
  }

  public void setTotalError(int totalError) {
    this.totalError = totalError;
  }

  public PlayerConfigEntity getPlayerConfig() {
    return playerConfig;
  }

  public void setPlayerConfig(PlayerConfigEntity playerConfig) {
    this.playerConfig = playerConfig;
  }

  public static final class PlayerStatsEntityBuilder {
    private String id;
    private int wins;
    private int rank;
    private int score;
    private int totalBattle;
    private int totalWin;
    private int totalLose;
    private int totalDraw;
    private int totalError;
    private PlayerConfigEntity playerConfig;

    private PlayerStatsEntityBuilder() {
    }

    public static PlayerStatsEntityBuilder builder() {
      return new PlayerStatsEntityBuilder();
    }

    public PlayerStatsEntityBuilder id(String id) {
      this.id = id;
      return this;
    }

    public PlayerStatsEntityBuilder wins(int wins) {
      this.wins = wins;
      return this;
    }

    public PlayerStatsEntityBuilder rank(int rank) {
      this.rank = rank;
      return this;
    }

    public PlayerStatsEntityBuilder score(int score) {
      this.score = score;
      return this;
    }

    public PlayerStatsEntityBuilder totalBattle(int totalBattle) {
      this.totalBattle = totalBattle;
      return this;
    }

    public PlayerStatsEntityBuilder totalWin(int totalWin) {
      this.totalWin = totalWin;
      return this;
    }

    public PlayerStatsEntityBuilder totalLose(int totalLose) {
      this.totalLose = totalLose;
      return this;
    }

    public PlayerStatsEntityBuilder totalDraw(int totalDraw) {
      this.totalDraw = totalDraw;
      return this;
    }

    public PlayerStatsEntityBuilder totalError(int totalError) {
      this.totalError = totalError;
      return this;
    }

    public PlayerStatsEntityBuilder playerConfig(PlayerConfigEntity playerConfig) {
      this.playerConfig = playerConfig;
      return this;
    }

    public PlayerStatsEntity build() {
      PlayerStatsEntity playerStatsEntity = new PlayerStatsEntity();
      playerStatsEntity.setId(id);
      playerStatsEntity.setWins(wins);
      playerStatsEntity.setRank(rank);
      playerStatsEntity.setScore(score);
      playerStatsEntity.setTotalBattle(totalBattle);
      playerStatsEntity.setTotalWin(totalWin);
      playerStatsEntity.setTotalLose(totalLose);
      playerStatsEntity.setTotalDraw(totalDraw);
      playerStatsEntity.setTotalError(totalError);
      playerStatsEntity.setPlayerConfig(playerConfig);
      return playerStatsEntity;
    }
  }
}
