package gg.reload.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.stream.Collectors;

@RestController
public class Lobby {

    @GetMapping
    public void get(HttpServletRequest httpServletRequest) throws IOException {
        var data = httpServletRequest.getReader().lines().collect(Collectors.joining(System.lineSeparator()));
        System.out.println(data);
    }

    @PostMapping
    public void send(HttpServletRequest httpServletRequest) throws IOException {
        var data = httpServletRequest.getReader().lines().collect(Collectors.joining(System.lineSeparator()));
        System.out.println(data);
    }

}