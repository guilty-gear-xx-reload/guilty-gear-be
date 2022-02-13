package ggxnet.reload.service.operation;

import ggxnet.reload.service.FileService;
import ggxnet.reload.shared.ParamType;
import lombok.extern.slf4j.Slf4j;

import java.util.Map;

import static ggxnet.reload.shared.ParamType.*;

@Slf4j
public class Leave implements Operation {

    private final FileService fileService = new FileService();
    private final String configFile;

    public Leave(String configFile) {
        this.configFile = configFile;
    }

    @Override
    public void process(Map<ParamType, String> params) {
        String address = params.get(REMOTE_ADDRESS).concat(":").concat(params.get(PORT));
        if(address.equals("0")) {
            return;
        }

        //lock
        var dataFile = fileService.readNodeList(configFile);

        while (true) {
            int index = fileService.findNode(params.get(NAME).concat("@").concat(address), dataFile);
            if (index == -1) {
                break;
            }
            dataFile.remove(index);
        }
        fileService.saveAll(dataFile, configFile);
        //unlock
    }
}
