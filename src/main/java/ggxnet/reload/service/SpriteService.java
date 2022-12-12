package ggxnet.reload.service;

import ggxnet.reload.repository.CharacterRepository;
import ggxnet.reload.repository.SpriteRepository;
import ggxnet.reload.service.dto.SpriteColorIndexesDto;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.ArrayUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Arrays;
import java.util.Base64;
import java.util.List;

import static org.springframework.http.HttpStatus.NOT_FOUND;

@Service
@RequiredArgsConstructor
public class SpriteService {

    private final CharacterRepository characterRepository;
    private final SpriteRepository spriteRepository;

    public SpriteColorIndexesDto getSprite(String characterName, int postureId) {
        var character = characterRepository.findByName(characterName).orElseThrow(() -> new ResponseStatusException(NOT_FOUND));
        var sprite = spriteRepository.findByCharacterAndPostureId(character, postureId).orElseThrow(() -> new ResponseStatusException(NOT_FOUND));
        List<Byte> colorIndexes = Arrays.asList(
                ArrayUtils.toObject(
                        Base64.getDecoder().decode(
                                sprite.getColorIndexes())));
        return new SpriteColorIndexesDto(sprite.getWidth(), sprite.getHeight(), colorIndexes);
    }
}
