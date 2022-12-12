package ggxnet.reload.service;

import ggxnet.reload.repository.CharacterRepository;
import ggxnet.reload.repository.PaletteRepository;
import ggxnet.reload.service.dto.PaletteColorsDto;
import ggxnet.reload.service.dto.RGBa;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

import static org.springframework.http.HttpStatus.*;

@Service
@RequiredArgsConstructor
public class PaletteService {

    private final CharacterRepository characterRepository;
    private final PaletteRepository paletteRepository;

    public PaletteColorsDto getPalette(String characterName) {
        var character = characterRepository.findByName(characterName).orElseThrow(() -> new ResponseStatusException(NOT_FOUND));
        List<RGBa> rgbaList = paletteRepository.findByCharacter(character).orElseThrow(() -> new ResponseStatusException(NOT_FOUND)).getColors();
        return new PaletteColorsDto(rgbaList);
    }
}
