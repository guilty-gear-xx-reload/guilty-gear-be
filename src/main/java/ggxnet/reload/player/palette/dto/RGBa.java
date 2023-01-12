package ggxnet.reload.player.palette.dto;

import java.io.Serial;
import java.io.Serializable;
import javax.persistence.Embeddable;

@Embeddable
public class RGBa implements Serializable {
  @Serial private static final long serialVersionUID = 0L;

  public RGBa() {}

  private short R;
  private short G;
  private short B;
  private short a;

  public RGBa(short R, short G, short B, short a) {
    this.R = R;
    this.G = G;
    this.B = B;
    this.a = a;
  }

  public short getR() {
    return R;
  }

  public void setR(short r) {
    R = r;
  }

  public short getG() {
    return G;
  }

  public void setG(short g) {
    G = g;
  }

  public short getB() {
    return B;
  }

  public void setB(short b) {
    B = b;
  }

  public short getA() {
    return a;
  }

  public void setA(short a) {
    this.a = a;
  }
}
