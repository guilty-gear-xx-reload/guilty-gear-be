package ggxnet.reload.client;

import ggxnet.reload.service.LobbyService;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

@Slf4j
@RestController
@RequiredArgsConstructor
class LobbyRestController {

    private final LobbyService lobbyService;
    private Map<String, String> mapAddress = new HashMap<>() {{
        put("194.181.134.122", "26.68.204.99");
        put("88.156.215.206", "26.39.40.108");
    }};


    @GetMapping
    public void handleGet(HttpServletRequest httpServletRequest) {
        lobbyService.processGet(getRequest(httpServletRequest));
    }

    @PostMapping
    public String handlePost(HttpServletRequest httpServletRequest) throws UnknownHostException {
        var ip = Objects.isNull(httpServletRequest.getHeader("x-real-ip")) ? httpServletRequest.getRemoteAddr()
                : httpServletRequest.getHeader("x-real-ip");

        String response = lobbyService.processPost(getRequest(httpServletRequest), mapAddress.get(ip));
        log.info("RESPONSE {}", response);
        return response;
    }

    @SneakyThrows
    private String getRequest(HttpServletRequest httpServletRequest) {
        var httpMethodName = httpServletRequest.getMethod();
        log.info("--------" + httpMethodName + "--------");
        var request = httpServletRequest.getReader()
                .lines()
                .collect(Collectors.joining(System.lineSeparator()));
        log.info("Request: {}", request);
        return request;
    }

}