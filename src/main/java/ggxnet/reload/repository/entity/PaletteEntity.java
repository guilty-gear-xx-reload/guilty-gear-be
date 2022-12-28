package ggxnet.reload.repository.entity;

import ggxnet.reload.service.dto.RGBa;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "palette")
public class PaletteEntity {
  @Id
  @GeneratedValue(
      strategy = GenerationType.SEQUENCE,
      generator = "palette_seq"
  )
  @SequenceGenerator(
      name = "palette_seq",
      allocationSize = 1
  )
  private long id;

  @Lob
  private String header;

  @ElementCollection
  @CollectionTable(name = "palette_colors", joinColumns = @JoinColumn(name = "palette_id"))
  private List<RGBa> colors;
  private long fileSizeInBytes;

  @Enumerated(EnumType.STRING)
  private PaletteType paletteType;

  @OneToOne
  @JoinColumn(name = "character_id")
  private CharacterEntity character;


  public PaletteEntity() {
  }

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public String getHeader() {
    return header;
  }

  public void setHeader(String header) {
    this.header = header;
  }

  public List<RGBa> getColors() {
    return colors;
  }

  public void setColors(List<RGBa> colors) {
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
}
