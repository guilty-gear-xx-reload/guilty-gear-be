package ggxnet.reload.controller.view;

import ggxnet.reload.player.palette.PaletteConverter;
import ggxnet.reload.utils.PageTitle;
import java.io.IOException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
class HomeController {
  private final PaletteConverter paletteConverter;

  @GetMapping
  public String getStartPage(Model model) {
    model.addAttribute("title", PageTitle.DEFAULT);
    return "index";
  }

  @GetMapping("/import")
  public void importAll() throws IOException {
    paletteConverter.importAll();
  }
}
