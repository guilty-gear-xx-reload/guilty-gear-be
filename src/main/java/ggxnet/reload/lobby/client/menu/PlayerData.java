package ggxnet.reload.lobby.client.menu;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class PlayerData {

    private String nickname;
    private Boolean status;
    private Integer totalWins;
    private Integer totalLoses;
    private Integer totalDraws;
    private Integer totalGames;
    private Double winToLoses;

}
