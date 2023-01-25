package ggxnet.reload.player.palette;

import static org.springframework.http.HttpStatus.NOT_FOUND;

import ggxnet.reload.player.palette.entity.CharacterEntity;
import java.util.List;
import java.util.stream.StreamSupport;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
@RequiredArgsConstructor
public class CharacterService {

  private final CharacterRepository characterRepository;
  private final SpriteRepository spriteRepository;

  public List<String> getCharacterNames() {
    return StreamSupport.stream(characterRepository.findAll().spliterator(), false)
        .map(CharacterEntity::getName)
        .toList();
  }

  public int getNumberOfPostures(String characterName) {
    var character =
        characterRepository
            .findByName(characterName)
            .orElseThrow(() -> new ResponseStatusException(NOT_FOUND));
    return spriteRepository.countByCharacter(character);
  }
}
