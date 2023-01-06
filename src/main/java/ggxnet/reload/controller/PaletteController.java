package ggxnet.reload.controller;

import ggxnet.reload.service.PaletteService;
import ggxnet.reload.service.dto.CommandPaletteColorsDto;
import ggxnet.reload.service.dto.PaletteColorsDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequestMapping("/palettes")
@RequiredArgsConstructor
public class PaletteController {

    private final PaletteService paletteService;

    @GetMapping("/{characterName}")
    public PaletteColorsDto getPalette(@PathVariable("characterName") String characterName) {
        return paletteService.getPalette(characterName);
    }

    @PostMapping("/{characterName}")
    public void savePalette(@PathVariable("characterName") String characterName,
                            @RequestBody CommandPaletteColorsDto commandPaletteColorsDto,
                            Principal principal) {
        paletteService.savePalette(commandPaletteColorsDto, characterName, principal.getName());
    }
}
