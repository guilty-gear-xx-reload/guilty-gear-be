package ggxnet.reload.controller.view;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
  @GetMapping
  public String getStartPage(Model model) {
    model.addAttribute("title", PageTitle.DEFAULT);
    return "index";
  }
}
