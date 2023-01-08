package ggxnet.reload.controller;

import ggxnet.reload.player.palette.SpriteService;
import ggxnet.reload.player.palette.dto.SpriteColorIndexesDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/sprites")
@RequiredArgsConstructor
class SpriteController {

  private final SpriteService spriteService;

  @GetMapping("/{characterName}")
  public SpriteColorIndexesDto getSprite(
      @PathVariable("characterName") String characterName,
      @RequestParam("postureId") int postureId) {
    return spriteService.getSprite(characterName, postureId);
  }
}
