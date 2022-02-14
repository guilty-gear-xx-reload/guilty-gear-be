package ggxnet.reload.service.operation;

import ggxnet.reload.service.FileService;
import ggxnet.reload.shared.ParamType;
import lombok.extern.slf4j.Slf4j;

import java.util.Map;

@Slf4j
public class Read implements Operation {

    private final FileService fileService = new FileService();
    private final String configFile;
    private final Object object = new Object();

    public Read(String configFile) {
        this.configFile = configFile;
    }

    @Override
    public String process(Map<ParamType, String> params) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("##head##\r\n");
        synchronized (object) {
            var dataFile = fileService.readNodeList(configFile);
            for (String record : dataFile) {
                var dollarIndex = record.indexOf("$");
                if (dollarIndex != -1) {
                    var playerData = record.substring(dollarIndex + 1);
                    log.info("Readed: " + playerData);
                    stringBuilder.append(playerData + "\r\n");
                } else {
                    log.error("Player isn't exist");
                }
            }
            stringBuilder.append("##foot##\r\n");
            return stringBuilder.toString();
        }

    }
}
