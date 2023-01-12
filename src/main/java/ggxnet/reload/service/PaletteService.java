package ggxnet.reload.service;

import static org.springframework.http.HttpStatus.NOT_FOUND;

import ggxnet.reload.repository.CharacterRepository;
import ggxnet.reload.repository.PaletteRepository;
import ggxnet.reload.repository.PlayerPaletteRepository;
import ggxnet.reload.repository.PlayerRepository;
import ggxnet.reload.repository.entity.PaletteEntity;
import ggxnet.reload.repository.entity.PaletteType;
import ggxnet.reload.repository.entity.PlayerPaletteEntity;
import ggxnet.reload.service.dto.CommandPaletteColorsDto;
import ggxnet.reload.service.dto.PaletteColorsDto;
import ggxnet.reload.service.dto.PaletteRGBa;
import ggxnet.reload.service.dto.RGBa;
import java.util.*;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.web.server.ResponseStatusException;

@Service
@RequiredArgsConstructor
public class PaletteService {

  private final CharacterRepository characterRepository;
  private final PaletteRepository paletteRepository;

  private final PlayerRepository playerRepository;
  private final PlayerPaletteRepository playerPaletteRepository;

  private final PaletteConverter paletteConverter;

  public PaletteColorsDto getPalette(String characterName) {
    List<RGBa> rgbaList =
        paletteRepository
            .findByCharacterNameAndPaletteType(characterName, PaletteType.DEFAULT)
            .orElseThrow(() -> new ResponseStatusException(NOT_FOUND))
            .getColors();
    return new PaletteColorsDto(rgbaList);
  }

  public void savePalette(
      CommandPaletteColorsDto commandPaletteColorsDto, String characterName, String username) {
    var palette = new PaletteEntity();

    var character = characterRepository.findByName(characterName).orElseThrow();
    var header =
        paletteRepository
            .findByCharacterAndPaletteType(character, PaletteType.DEFAULT)
            .orElseThrow(() -> new ResponseStatusException(NOT_FOUND))
            .getHeader();

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
    var palettes =
        playerPaletteRepository
            .findAllByPlayerAndPaletteCharacterName(player, characterName)
            .stream()
            .map(PlayerPaletteEntity::getPalette)
            .toList();
    Map<Long, String> paletteNames = new HashMap<>();
    palettes.forEach(
        playerPalette -> paletteNames.put(playerPalette.getId(), playerPalette.getName()));
    return paletteNames;
  }

  public PaletteColorsDto getPaletteById(Long paletteId) {
    PaletteEntity palette = paletteRepository.findById(paletteId).orElseThrow();
    return new PaletteColorsDto(palette.getColors());
  }

  public Map<String, byte[]> getBinaryPalettesByPlayerId(String playerId) {
    var player = playerRepository.findById(playerId).orElseThrow();
    var customPalettes =
        playerPaletteRepository.findAllByPlayer(player).stream()
            .map(PlayerPaletteEntity::getPalette)
            .toList();
    List<Long> characterIds =
        customPalettes.stream().map(palette -> palette.getCharacter().getId()).toList();
    List<PaletteEntity> defaultPalettesWithoutCustoms =
        CollectionUtils.isEmpty(customPalettes)
            ? paletteRepository.findByPaletteType(PaletteType.DEFAULT)
            : paletteRepository.findDefaultPalettesIfCustomNotExist(characterIds);
    defaultPalettesWithoutCustoms.addAll(customPalettes);
    return defaultPalettesWithoutCustoms.stream()
        .collect(Collectors.toMap(this::getCharacterName, this::convertToByte));
  }

  private byte[] convertToByte(PaletteEntity palette) {
    byte[] bytes = Base64.getDecoder().decode(palette.getHeader().getBytes());
    List<Byte> headersByte = toByteList(bytes);
    var paletteRGBa = new PaletteRGBa(headersByte, palette.getColors());
    return paletteConverter.convertPaletteFromRgbToBinary(paletteRGBa);
  }

  private List<Byte> toByteList(byte[] bytes) {
    List<Byte> headersByte = new ArrayList<>();
    for (byte aByte : bytes) {
      headersByte.add(aByte);
    }
    return headersByte;
  }

  private String getCharacterName(PaletteEntity palette) {
    return palette.getCharacter().getName();
  }
}
