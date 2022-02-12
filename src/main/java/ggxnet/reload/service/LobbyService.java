package ggxnet.reload.service;

import ggxnet.reload.shared.CommandType;
import ggxnet.reload.shared.ParamType;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Map;

@Slf4j
@Service
public class LobbyService {
    public void processPost(String request) {
        var paramService = new ParamService();
        Map<ParamType, String> paramsMap = paramService.calculate(request);
        var command = CommandType.valueOf(paramsMap.get(ParamType.CMD).toUpperCase());
        log.info("Actual command: {}", command);
    }

    public void processGet(String request) {
    }
}
