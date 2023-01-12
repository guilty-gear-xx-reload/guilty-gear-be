package ggxnet.reload.repository.entity;

import javax.persistence.*;

@Entity
@Table(name = "player_palette")
public class PlayerPaletteEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "player_palette_seq")
  @SequenceGenerator(name = "player_palette_seq", allocationSize = 1)
  private long id;

  @ManyToOne
  @JoinColumn(name = "player_id")
  private PlayerEntity player;

  @ManyToOne
  @JoinColumn(name = "palette_id")
  private PaletteEntity palette;

  private boolean active;

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public PlayerEntity getPlayer() {
    return player;
  }

  public void setPlayer(PlayerEntity player) {
    this.player = player;
  }

  public PaletteEntity getPalette() {
    return palette;
  }

  public void setPalette(PaletteEntity palette) {
    this.palette = palette;
  }

  public boolean isActive() {
    return active;
  }

  public void setActive(boolean active) {
    this.active = active;
  }
}
