package ggxnet.reload.player.palette.dto;

import ggxnet.reload.player.palette.entity.RGBA;
import java.util.List;

public record CommandPaletteColorsDto(List<RGBA> rgba, String paletteName, String characterName) {}
