package ggxnet.reload.player.palette;

import static org.springframework.http.HttpStatus.NOT_FOUND;

import ggxnet.reload.player.palette.dto.SpriteColorIndexesDto;
import java.util.Arrays;
import java.util.Base64;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.ArrayUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
@RequiredArgsConstructor
public class SpriteService {

  private final CharacterRepository characterRepository;
  private final SpriteRepository spriteRepository;

  public SpriteColorIndexesDto getSprite(String characterName, int postureId) {
    var character =
        characterRepository
            .findByName(characterName)
            .orElseThrow(() -> new ResponseStatusException(NOT_FOUND));
    var sprite =
        spriteRepository
            .findByCharacterAndPostureId(character, postureId)
            .orElseThrow(() -> new ResponseStatusException(NOT_FOUND));
    List<Byte> colorIndexes =
        Arrays.asList(ArrayUtils.toObject(Base64.getDecoder().decode(sprite.getColorIndexes())));
    return new SpriteColorIndexesDto(sprite.getWidth(), sprite.getHeight(), colorIndexes);
  }
}
