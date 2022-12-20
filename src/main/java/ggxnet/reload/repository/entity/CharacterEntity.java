package ggxnet.reload.repository.entity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "character")
public class CharacterEntity {
  @Id
  @GeneratedValue(
      strategy = GenerationType.SEQUENCE,
      generator = "character_seq"
  )
  @SequenceGenerator(
      name = "character_seq",
      allocationSize = 1
  )
  private long id;

  private String name;
  @OneToMany(mappedBy = "character")
  private List<SpriteEntity> sprites;
  @OneToOne
  @JoinColumn(name = "palette_id")
  private PaletteEntity palette;

  public CharacterEntity() {
  }

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public List<SpriteEntity> getSprites() {
    return sprites;
  }

  public void setSprites(List<SpriteEntity> sprites) {
    this.sprites = sprites;
  }

  public PaletteEntity getPalette() {
    return palette;
  }

  public void setPalette(PaletteEntity palette) {
    this.palette = palette;
  }
}
