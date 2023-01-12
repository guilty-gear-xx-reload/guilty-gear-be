package ggxnet.reload.player.palette.dto;

import java.io.Serializable;
import java.util.List;

public record PaletteRGBa(List<Byte> header, List<RGBa> rgba) implements Serializable {}
