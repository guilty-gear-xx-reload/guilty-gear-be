package ggxnet.reload.controller.view;

import ggxnet.reload.repository.CharacterRepository;
import ggxnet.reload.repository.PaletteRepository;
import ggxnet.reload.repository.SpriteRepository;
import ggxnet.reload.repository.entity.CharacterEntity;
import ggxnet.reload.repository.entity.PaletteEntity;
import ggxnet.reload.repository.entity.PaletteType;
import ggxnet.reload.repository.entity.SpriteEntity;
import ggxnet.reload.service.dto.PaletteRGBa;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Collections;
import java.util.List;

import static ggxnet.reload.service.PaletteConverter.readData;

@Controller
@RequiredArgsConstructor
public class HomeController {

    private final CharacterRepository characterRepository;
    private final PaletteRepository paletteRepository;
    private final SpriteRepository spriteRepository;

    @GetMapping
    public String getStartPage(Model model) throws Exception {
        model.addAttribute("title", PageTitle.DEFAULT);
        return "index";
    }

    @GetMapping("/import")
    public void importAll() throws IOException {
        String palettePath = "C:\\Users\\Alfu\\IdeaProjects\\guilty-gear-xx-reload\\server-spring\\default_palettes";
        File paletteFolder = new File(palettePath);
        File[] listOfFilesPalette = paletteFolder.listFiles();
        for (File paletteFile : listOfFilesPalette) {
            PaletteEntity paletteEntity = new PaletteEntity();

            CharacterEntity characterEntity = new CharacterEntity();
            CharacterEntity savedCharacterEntity = characterRepository.save(characterEntity);
            String characterName = paletteFile.getName().split("_")[0];
            savedCharacterEntity.setName(characterName);
            String filename = paletteFile.getName();
            PaletteRGBa paletteRGBa = null;
            try {
                paletteRGBa = readData(palettePath + "\\" + filename);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
            byte[] header = new byte[paletteRGBa.header().size()];
            for (int i = 0; i < paletteRGBa.header().size(); i++) {
                header[i] = paletteRGBa.header().get(i);
            }
            String byteHeader = Base64.getEncoder().encodeToString(header);
            paletteEntity.setHeader(byteHeader);
            paletteEntity.setColors(paletteRGBa.rgba());
            paletteEntity.setFileSizeInBytes(paletteFile.length());
            paletteEntity.setPaletteType(PaletteType.DEFAULT);
            paletteEntity.setCharacter(characterEntity);
            paletteRepository.save(paletteEntity);
            savedCharacterEntity.setPalettes(Collections.singletonList(paletteEntity));
            characterEntity.setPalettes(Collections.singletonList(paletteEntity));

            List<SpriteEntity> spriteEntities = new ArrayList<>();
            String path = "C:\\Users\\Alfu\\IdeaProjects\\ggxxreload-matrix\\scrap\\" + characterName;
            File binFolder = new File(path);
            for (File innerFile : binFolder.listFiles()) {
                String binFile = innerFile.getName();
                int postureId = Integer.parseInt(binFile.split("_")[0].split(".bin")[0]);
                int width = Integer.parseInt(binFile.split("_")[1].split(".bin")[0]);
                int height = Integer.parseInt(binFile.split("_")[2].split(".bin")[0]);

                SpriteEntity spriteEntity = new SpriteEntity();
                spriteEntity.setWidth(width);
                spriteEntity.setHeight(height);
                spriteEntity.setFileSizeInBytes(innerFile.length());
                spriteEntity.setPostureId(postureId);

                byte[] bytes = Files.readAllBytes(Path.of(innerFile.getAbsolutePath()));
                String base64 = Base64.getEncoder().encodeToString(bytes);
                spriteEntity.setColorIndexes(base64);
                spriteEntities.add(spriteEntity);
                spriteEntity.setCharacter(characterEntity);
            }
            spriteRepository.saveAll(spriteEntities);
            savedCharacterEntity.setSprites(spriteEntities);

        }

    }

}
