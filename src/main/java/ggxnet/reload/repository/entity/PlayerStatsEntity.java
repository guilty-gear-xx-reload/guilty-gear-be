package ggxnet.reload.repository.entity;

import java.util.UUID;
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
public class PlayerStatsEntity {
  @Id private String id;
  private int wins;
  private int rank;
  private int score;
  private int totalBattle;
  private int totalWin;
  private int totalLose;
  private int totalDraw;
  private int totalError;

  public static PlayerStatsEntity of() {
    String id = UUID.randomUUID().toString();
    return PlayerStatsEntity.builder()
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
}
