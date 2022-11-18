package ggxnet.reload.lobby.client.menu;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(path = "/lobby")
public class LobbyController {
    @GetMapping
    public String getLobby(Model model) {
        model.addAttribute("title", PageTitle.LOBBY);
        return "lobby";
    }
}
