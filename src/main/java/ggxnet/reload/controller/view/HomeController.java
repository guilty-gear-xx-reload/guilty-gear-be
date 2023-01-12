package ggxnet.reload.controller.view;

import ggxnet.reload.service.PaletteConverter;
import java.io.IOException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class HomeController {
  private final PaletteConverter paletteConverter;

  @GetMapping
  public String getStartPage(Model model) throws Exception {
    model.addAttribute("title", PageTitle.DEFAULT);
    return "index";
  }

  @GetMapping("/import")
  public void importAll() throws IOException {
    paletteConverter.importAll();
  }
}
