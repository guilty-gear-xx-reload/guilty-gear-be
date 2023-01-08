package ggxnet.reload.player.palette.dto;

import ggxnet.reload.player.palette.entity.RGBA;
import java.util.List;

public record PaletteColorsWithNameDto(long id, List<RGBA> rgba, String paletteName) {}
