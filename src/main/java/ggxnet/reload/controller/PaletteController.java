package ggxnet.reload.controller;

import ggxnet.reload.service.PaletteService;
import ggxnet.reload.service.dto.CommandPaletteColorsDto;
import ggxnet.reload.service.dto.PaletteColorsDto;
import java.security.Principal;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/palettes")
@RequiredArgsConstructor
public class PaletteController {

  private final PaletteService paletteService;

  @GetMapping("/default/{characterName}")
  public PaletteColorsDto getPalette(@PathVariable("characterName") String characterName) {
    return paletteService.getPalette(characterName);
  }

  @GetMapping("/custom/characters/{characterName}")
  public Map<Long, String> getPlayerPaletteNames(
      @PathVariable("characterName") String characterName, Principal principal) {
    return paletteService.getPlayerPaletteNames(characterName, principal.getName());
  }

  @GetMapping("/custom/{paletteId}")
  public PaletteColorsDto getPaletteById(@PathVariable("paletteId") Long paletteId) {
    return paletteService.getPaletteById(paletteId);
  }

  @PostMapping("/custom/{characterName}")
  public void savePalette(
      @PathVariable("characterName") String characterName,
      @RequestBody CommandPaletteColorsDto commandPaletteColorsDto,
      Principal principal) {
    paletteService.savePalette(commandPaletteColorsDto, characterName, principal.getName());
  }
}
