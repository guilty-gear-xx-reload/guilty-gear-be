package ggxnet.reload.shared;

import lombok.extern.slf4j.Slf4j;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.stream.Collectors;

@Slf4j
public class RequestUtil {
    private RequestUtil() {
    }

    public static String getRequest(HttpServletRequest httpServletRequest) {
        var httpMethodName = httpServletRequest.getMethod();
        log.info("--------" + httpMethodName + "--------");
        BufferedReader reader;
        try {
            reader = httpServletRequest.getReader();
        } catch (IOException e) {
            throw new RuntimeException("", e);
        }
        var request = reader
                .lines()
                .collect(Collectors.joining(System.lineSeparator()));
        log.info("Request: {}", request);
        return request;
    }
}
