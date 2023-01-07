package ggxnet.reload.service;

import ggxnet.reload.repository.CharacterRepository;
import ggxnet.reload.repository.PaletteRepository;
import ggxnet.reload.repository.PlayerPaletteRepository;
import ggxnet.reload.repository.PlayerRepository;
import ggxnet.reload.repository.entity.PaletteEntity;
import ggxnet.reload.repository.entity.PaletteType;
import ggxnet.reload.repository.entity.PlayerPaletteEntity;
import ggxnet.reload.service.dto.CommandPaletteColorsDto;
import ggxnet.reload.service.dto.PaletteColorsDto;
import ggxnet.reload.service.dto.RGBa;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.springframework.http.HttpStatus.NOT_FOUND;

@Service
@RequiredArgsConstructor
public class PaletteService {

    private final CharacterRepository characterRepository;
    private final PaletteRepository paletteRepository;

    private final PlayerRepository playerRepository;
    private final PlayerPaletteRepository playerPaletteRepository;

    public PaletteColorsDto getPalette(String characterName) {
        List<RGBa> rgbaList = paletteRepository.findByCharacterNameAndPaletteType(characterName, PaletteType.DEFAULT).orElseThrow(() -> new ResponseStatusException(NOT_FOUND)).getColors();
        return new PaletteColorsDto(rgbaList);
    }

    public void savePalette(CommandPaletteColorsDto commandPaletteColorsDto, String characterName, String username) {
        var palette = new PaletteEntity();

        var character = characterRepository.findByName(characterName).orElseThrow();
        var header = paletteRepository.findByCharacterAndPaletteType(character, PaletteType.DEFAULT)
                .orElseThrow(() -> new ResponseStatusException(NOT_FOUND)).getHeader();

        palette.setPaletteType(PaletteType.CUSTOM);
        palette.setFileSizeInBytes(0L);
        palette.setName(commandPaletteColorsDto.paletteName());
        palette.setColors(commandPaletteColorsDto.rgba());
        palette.setHeader(header);
        palette.setCharacter(character);
        var persistedPalette = paletteRepository.save(palette);

        var player = playerRepository.findByUserUsername(username).orElseThrow();

        var playerPaletteEntity = new PlayerPaletteEntity();
        playerPaletteEntity.setPalette(persistedPalette);
        playerPaletteEntity.setPlayer(player);
        playerPaletteRepository.save(playerPaletteEntity);
    }

    public Map<Long, String> getPlayerPaletteNames(String characterName, String username) {
        var player = playerRepository.findByUserUsername(username).orElseThrow();
        var palettes = playerPaletteRepository.findAllByPlayerAndPaletteCharacterName(player, characterName).stream()
                .map(PlayerPaletteEntity::getPalette)
                .toList();
        Map<Long, String> paletteNames = new HashMap<>();
        palettes.forEach(playerPalette -> {
            paletteNames.put(playerPalette.getId(), playerPalette.getName());
        });
        return paletteNames;
    }

    public PaletteColorsDto getPaletteById(Long paletteId) {
        PaletteEntity palette = paletteRepository.findById(paletteId).orElseThrow();
        return new PaletteColorsDto(palette.getColors());
    }
}
