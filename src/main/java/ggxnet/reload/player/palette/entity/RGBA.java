package ggxnet.reload.player.palette.entity;

import java.io.Serial;
import java.io.Serializable;
import javax.persistence.Embeddable;

@Embeddable
public class RGBA implements Serializable {
  @Serial private static final long serialVersionUID = 0L;

  public RGBA() {}

  private short R;
  private short G;
  private short B;
  private short A;

  public RGBA(short R, short G, short B, short A) {
    this.R = R;
    this.G = G;
    this.B = B;
    this.A = A;
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
    return A;
  }

  public void setA(short a) {
    this.A = a;
  }
}
