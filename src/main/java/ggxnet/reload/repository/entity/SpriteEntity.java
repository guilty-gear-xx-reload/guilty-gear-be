package ggxnet.reload.repository.entity;

import javax.persistence.*;

@Entity
@Table(name = "sprite")
public class SpriteEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sprite_seq")
  @SequenceGenerator(name = "sprite_seq", allocationSize = 1)
  private long id;

  private int postureId;
  private int width;
  private int height;

  @Lob private String colorIndexes;
  private long fileSizeInBytes;

  public SpriteEntity() {}

  @ManyToOne
  @JoinColumn(name = "character_id")
  private CharacterEntity character;

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public int getPostureId() {
    return postureId;
  }

  public void setPostureId(int postureId) {
    this.postureId = postureId;
  }

  public int getWidth() {
    return width;
  }

  public void setWidth(int width) {
    this.width = width;
  }

  public int getHeight() {
    return height;
  }

  public void setHeight(int height) {
    this.height = height;
  }

  public String getColorIndexes() {
    return colorIndexes;
  }

  public void setColorIndexes(String colorIndexes) {
    this.colorIndexes = colorIndexes;
  }

  public long getFileSizeInBytes() {
    return fileSizeInBytes;
  }

  public void setFileSizeInBytes(long fileSizeInBytes) {
    this.fileSizeInBytes = fileSizeInBytes;
  }

  public CharacterEntity getCharacter() {
    return character;
  }

  public void setCharacter(CharacterEntity character) {
    this.character = character;
  }
}
