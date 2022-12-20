package ggxnet.reload.service.dto;

import java.io.Serializable;
import java.util.List;


public record PaletteRGBa(List<Byte> header, List<RGBa> rgba) implements Serializable {
}