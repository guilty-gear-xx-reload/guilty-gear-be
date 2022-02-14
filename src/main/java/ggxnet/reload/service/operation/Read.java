package ggxnet.reload.service.operation;

import ggxnet.reload.service.FileService;
import ggxnet.reload.shared.ParamType;
import lombok.extern.slf4j.Slf4j;

import java.util.Map;

@Slf4j
public class Read implements Operation {

    private final FileService fileService = new FileService();
    private final String configFile;

    public Read(String configFile) {
        this.configFile = configFile;
    }

    @Override
    public void process(Map<ParamType, String> params) {
        var dataFile = fileService.readNodeList(configFile);
        for (String record : dataFile) {
            var dollarIndex = record.indexOf("$");
            if (dollarIndex != -1) {
                var playerData = record.substring(dollarIndex + 1);
                log.info("Readed: " + playerData);
            } else {
                log.error("Player isn't exist");
            }
        }
    }
}
