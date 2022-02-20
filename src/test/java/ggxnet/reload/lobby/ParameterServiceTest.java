package ggxnet.reload.lobby;

import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

class ParameterServiceTest {

    private ParameterService sut = new ParameterService();

    @Test
    public void properly_parse_input_with_all_parameters() {
        //given
        String request = "cmd=enter|port=10000|name=Arek|param=006100000F4|win=2";
        //when
        Map<Parameter, String> params = sut.calculate(request);
        //then
        assertThat(params.get(Parameter.CMD)).contains("enter");
        assertThat(params.get(Parameter.PORT)).contains("10000");
        assertThat(params.get(Parameter.NAME)).contains("Arek");
        assertThat(params.get(Parameter.PARAM)).contains("006100000F4");
        assertThat(params.get(Parameter.WIN)).contains("2");
    }


    @Test
    public void properly_parse_empty_string() {
        //given
        String request = "";
        //when
        Map<Parameter, String> params = sut.calculate(request);
        //then
        assertThat(params).isEmpty();
    }
}