package ggxnet.reload.lobby;

import ggxnet.reload.shared.ParamType;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

class ParamServiceTest {

    private ParamService sut = new ParamService();

    @Test
    public void properly_parse_input_with_all_parameters() {
        //given
        String request = "cmd=enter|port=10000|name=Arek|param=006100000F4|win=2";
        //when
        Map<ParamType, String> params = sut.calculate(request);
        //then
        assertThat(params.get(ParamType.CMD)).contains("enter");
        assertThat(params.get(ParamType.PORT)).contains("10000");
        assertThat(params.get(ParamType.NAME)).contains("Arek");
        assertThat(params.get(ParamType.PARAM)).contains("006100000F4");
        assertThat(params.get(ParamType.WIN)).contains("2");
    }


    @Test
    public void properly_parse_empty_string() {
        //given
        String request = "";
        //when
        Map<ParamType, String> params = sut.calculate(request);
        //then
        assertThat(params).isEmpty();
    }
}