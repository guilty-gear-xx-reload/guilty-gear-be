package ggxnet.reload.controller;

import ggxnet.reload.player.palette.PaletteService;
import ggxnet.reload.player.palette.dto.CommandPaletteColorsDto;
import ggxnet.reload.player.palette.dto.PaletteColorsDto;
import ggxnet.reload.player.palette.dto.PaletteColorsWithNameDto;
import java.security.Principal;
import java.util.List;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/palettes")
@RequiredArgsConstructor
class PaletteController {

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

  @GetMapping("/customm/{characterName}")
  public List<PaletteColorsWithNameDto> getCustomPalettes(
      @PathVariable("characterName") String characterName, Principal principal) {
    return paletteService.getCustomPalettes(characterName, principal.getName());
  }

  @PostMapping("/custom")
  public Long savePalette(
      @RequestBody CommandPaletteColorsDto commandPaletteColorsDto, Principal principal) {
    return paletteService.savePalette(commandPaletteColorsDto, principal.getName());
  }

  @PutMapping("/custom/{id}")
  public void updatePalette(
      @PathVariable("id") Long id, @RequestBody CommandPaletteColorsDto commandPaletteColorsDto) {
    paletteService.updatePalette(id, commandPaletteColorsDto);
  }

  @DeleteMapping("/custom/{id}")
  public void deletePalette(@PathVariable("id") Long id) {
    paletteService.deletePalette(id);
  }
}
