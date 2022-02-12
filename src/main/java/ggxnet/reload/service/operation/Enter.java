package ggxnet.reload.service.operation;

import ggxnet.reload.service.AddressService;
import ggxnet.reload.shared.ParamType;

import java.util.Map;

import static ggxnet.reload.shared.ParamType.*;

public class Enter implements Operation {

    private final AddressService addressService = new AddressService();

    @Override
    public void process(Map<ParamType, String> params) {
        var ownAddress = addressService.getOwnAddress(params.get(PORT));
        if(ownAddress.equals("0")) {
            return;
        }
        //addressService.lockDat();
        //addressService.readNodeList();

        // 140, 141 ?
    }
}
