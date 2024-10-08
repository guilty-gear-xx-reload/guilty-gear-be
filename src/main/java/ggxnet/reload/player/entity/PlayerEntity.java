package ggxnet.reload.player.entity;

import ggxnet.reload.configuration.security.entity.UserEntity;
import ggxnet.reload.player.config.entity.PlayerConfigEntity;
import ggxnet.reload.player.palette.entity.PlayerPaletteEntity;
import ggxnet.reload.player.statistic.entity.PlayerStatsEntity;
import java.util.List;
import javax.persistence.*;

@Entity
@Table(name = "player")
public class PlayerEntity {
  @Id private String id;
  private String nickname;

  @OneToOne
  @JoinColumn(name = "player_stats_id")
  private PlayerStatsEntity stats;

  @OneToOne
  @JoinColumn(name = "player_config_id")
  private PlayerConfigEntity config;

  @OneToOne
  @JoinColumn(name = "user_id")
  private UserEntity user;

  @OneToMany(mappedBy = "player")
  private List<PlayerPaletteEntity> playerPalettes;

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getNickname() {
    return nickname;
  }

  public void setNickname(String nickname) {
    this.nickname = nickname;
  }

  public PlayerStatsEntity getStats() {
    return stats;
  }

  public void setStats(PlayerStatsEntity stats) {
    this.stats = stats;
  }

  public PlayerConfigEntity getConfig() {
    return config;
  }

  public void setConfig(PlayerConfigEntity config) {
    this.config = config;
  }

  public UserEntity getUser() {
    return user;
  }

  public void setUser(UserEntity user) {
    this.user = user;
  }

  public List<PlayerPaletteEntity> getPlayerPalettes() {
    return playerPalettes;
  }

  public void setPlayerPalettes(List<PlayerPaletteEntity> playerPalettes) {
    this.playerPalettes = playerPalettes;
  }
}
