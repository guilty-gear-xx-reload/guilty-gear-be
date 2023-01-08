package ggxnet.reload.player.palette.dto;

import ggxnet.reload.player.palette.entity.RGBA;
import java.io.Serializable;
import java.util.List;

public record PaletteRGBA(List<Byte> header, List<RGBA> rgba) implements Serializable {}
