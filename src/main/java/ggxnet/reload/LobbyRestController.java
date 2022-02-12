package ggxnet.reload;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.stream.Collectors;

@Slf4j
@RestController
class LobbyRestController {

    @GetMapping
    public void handleGet(HttpServletRequest httpServletRequest) throws IOException {
        // var input = httpServletRequest.getReader().lines().collect(Collectors.joining(System.lineSeparator()));
        log.debug("GET");
    }

    @PostMapping
    public void handlePost(HttpServletRequest httpServletRequest) throws IOException {
        var input = httpServletRequest.getReader().lines().collect(Collectors.joining(System.lineSeparator()));
        log.info("POST Input {}", input);
        var lobbyParamService = new LobbyService();
        lobbyParamService.processInput(input);
    }

}