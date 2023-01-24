package ggxnet.reload.player.palette;

import static org.springframework.http.HttpStatus.NOT_FOUND;

import ggxnet.reload.player.palette.dto.CommandPaletteColorsDto;
import ggxnet.reload.player.palette.dto.PaletteColorsDto;
import ggxnet.reload.player.palette.dto.PaletteColorsWithNameDto;
import ggxnet.reload.player.palette.dto.PaletteRGBA;
import ggxnet.reload.player.palette.entity.PaletteEntity;
import ggxnet.reload.player.palette.entity.PaletteType;
import ggxnet.reload.player.palette.entity.PlayerPaletteEntity;
import ggxnet.reload.player.palette.entity.RGBA;
import ggxnet.reload.player.palette.repository.CharacterRepository;
import ggxnet.reload.player.palette.repository.PaletteRepository;
import ggxnet.reload.player.palette.repository.PlayerPaletteRepository;
import ggxnet.reload.player.repository.PlayerRepository;
import java.util.*;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
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
    List<RGBA> rgbaList =
        paletteRepository
            .findByCharacterNameAndPaletteType(characterName, PaletteType.DEFAULT)
            .orElseThrow(() -> new ResponseStatusException(NOT_FOUND))
            .getColors();
    return new PaletteColorsDto(rgbaList);
  }

  public Long savePalette(CommandPaletteColorsDto commandPaletteColorsDto, String username) {
    var palette = new PaletteEntity();

    var character =
        characterRepository.findByName(commandPaletteColorsDto.characterName()).orElseThrow();
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
    return playerPaletteRepository.save(playerPaletteEntity).getId();
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
    var paletteRGBA = new PaletteRGBA(headersByte, palette.getColors());
    return paletteConverter.convertPaletteFromRgbToBinary(paletteRGBA);
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

  public List<PaletteColorsWithNameDto> getCustomPalettes(String characterName, String username) {
    var player = playerRepository.findByUserUsername(username).orElseThrow();
    return playerPaletteRepository
        .findAllByPlayerAndPaletteCharacterName(player, characterName)
        .stream()
        .map(
            playerPalette ->
                new PaletteColorsWithNameDto(
                    playerPalette.getId(),
                    playerPalette.getPalette().getColors(),
                    playerPalette.getPalette().getName()))
        .toList();
  }

  @Transactional
  public void updatePalette(Long id, CommandPaletteColorsDto commandPaletteColorsDto) {
    var playerPalette = playerPaletteRepository.findById(id).orElseThrow(RuntimeException::new);
    var palette = playerPalette.getPalette();
    palette.setColors(commandPaletteColorsDto.rgba());
    palette.setName(commandPaletteColorsDto.paletteName());
  }

  @Transactional
  public void deletePalette(Long id) {
    var playerPalette = playerPaletteRepository.findById(id).get();
    var palette = playerPalette.getPalette();
    playerPaletteRepository.deleteById(id);
    paletteRepository.delete(palette);
  }
}
