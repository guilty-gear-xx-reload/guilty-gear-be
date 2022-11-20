package ggxnet.reload.lobby.client.menu;

import ggxnet.reload.lobby.infrastructure.PlayerConfigEntity;
import ggxnet.reload.lobby.infrastructure.PlayerStatsEntity;

public class LobbyMapper {

    public static PlayerData toPlayerData(PlayerConfigEntity playerConfig) {
        PlayerStatsEntity playerStats = playerConfig.getStats();
        int totalWin = playerStats.getTotalWin();
        int totalLose = playerStats.getTotalLose();
        return new PlayerData(
                playerConfig.getUserName(),
                playerConfig.isActive(),
                totalWin,
                totalLose,
                playerStats.getTotalDraw(),
                playerStats.getTotalBattle(),
                totalLose > 0 ? (double) totalWin / totalLose : totalWin
        );
    }
}
