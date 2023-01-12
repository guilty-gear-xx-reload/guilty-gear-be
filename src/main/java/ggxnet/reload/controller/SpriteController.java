package ggxnet.reload.controller;

import ggxnet.reload.service.SpriteService;
import ggxnet.reload.service.dto.SpriteColorIndexesDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/sprites")
@RequiredArgsConstructor
public class SpriteController {

  private final SpriteService spriteService;

  @GetMapping("/{characterName}")
  public SpriteColorIndexesDto getPalette(
      @PathVariable("characterName") String characterName,
      @RequestParam("postureId") int postureId) {
    return spriteService.getSprite(characterName, postureId);
  }
}
