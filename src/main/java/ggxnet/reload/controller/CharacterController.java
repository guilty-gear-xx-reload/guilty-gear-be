package ggxnet.reload.controller;

import ggxnet.reload.service.CharacterService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/characters")
@RequiredArgsConstructor
public class CharacterController {

  private final CharacterService characterService;

  @GetMapping("/names")
  public List<String> getCharacterNames() {
    return characterService.getCharacterNames();
  }

  @GetMapping("/postures/{characterName}/total-elements")
  public int getNumberOfPostures(@PathVariable("characterName") String characterName) {
    return characterService.getNumberOfPostures(characterName);
  }
}
