package ggxnet.reload.lobby.client.menu;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(path = "/statistics")
public class StatisticsController {
    @GetMapping
    public String getStatistics(Model model) {
        model.addAttribute("title", PageTitle.STATISTICS);
        return "statistics";
    }
}
