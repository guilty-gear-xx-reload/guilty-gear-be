package ggxnet.reload.service;

import ggxnet.reload.repository.CharacterRepository;
import ggxnet.reload.repository.SpriteRepository;
import ggxnet.reload.repository.entity.CharacterEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.StreamSupport;

import static org.springframework.http.HttpStatus.NOT_FOUND;

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
        var character = characterRepository.findByName(characterName).orElseThrow(() -> new ResponseStatusException(NOT_FOUND));
        return spriteRepository.countByCharacter(character);
    }
}
