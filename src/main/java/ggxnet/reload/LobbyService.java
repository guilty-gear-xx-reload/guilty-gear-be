package ggxnet.reload;

import lombok.extern.slf4j.Slf4j;

import java.util.Map;

@Slf4j
class LobbyService {
    void processInput(String input) {
        var paramService = new ParamService();
        Map<ParamType, String> paramsMap = paramService.calculate(input);
        var state = State.valueOf(paramsMap.get(ParamType.CMD).toUpperCase());
        log.info("Actual state: {}", state);
    }
}
