package ggxnet.reload.controller;

import ggxnet.reload.service.PaletteService;
import ggxnet.reload.service.dto.PaletteColorsDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/palettes")
@RequiredArgsConstructor
public class PaletteController {

    private final PaletteService paletteService;

    @GetMapping("/{characterName}")
    public PaletteColorsDto getPalette(@PathVariable("characterName") String characterName) {
        return paletteService.getPalette(characterName);
    }

}
