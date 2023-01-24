package ggxnet.reload.player.palette.entity;

import java.util.List;
import javax.persistence.*;

@Entity
@Table(name = "palette")
public class PaletteEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "palette_seq")
  @SequenceGenerator(name = "palette_seq", allocationSize = 1)
  private long id;

  private String name;

  @Lob private String header;

  @ElementCollection
  @CollectionTable(name = "palette_colors", joinColumns = @JoinColumn(name = "palette_id"))
  private List<RGBA> colors;

  private long fileSizeInBytes;

  @Enumerated(EnumType.STRING)
  private PaletteType paletteType;

  @ManyToOne
  @JoinColumn(name = "character_id")
  private CharacterEntity character;

  public PaletteEntity() {}

  public long getId() {
    return id;
  }

  public String getHeader() {
    return header;
  }

  public void setHeader(String header) {
    this.header = header;
  }

  public List<RGBA> getColors() {
    return colors;
  }

  public void setColors(List<RGBA> colors) {
    this.colors = colors;
  }

  public long getFileSizeInBytes() {
    return fileSizeInBytes;
  }

  public void setFileSizeInBytes(long fileSizeInBytes) {
    this.fileSizeInBytes = fileSizeInBytes;
  }

  public PaletteType getPaletteType() {
    return paletteType;
  }

  public void setPaletteType(PaletteType paletteType) {
    this.paletteType = paletteType;
  }

  public CharacterEntity getCharacter() {
    return character;
  }

  public void setCharacter(CharacterEntity character) {
    this.character = character;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }
}
