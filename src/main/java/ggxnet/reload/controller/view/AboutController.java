package ggxnet.reload.controller.view;

import ggxnet.reload.utils.PageTitle;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(path = "/about")
class AboutController {
  @GetMapping
  public String getAbout(Model model) {
    model.addAttribute("title", PageTitle.ABOUT);
    return "about";
  }
}
