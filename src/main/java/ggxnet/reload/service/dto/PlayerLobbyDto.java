package ggxnet.reload.service.dto;

import ggxnet.reload.repository.entity.PlayerConfigEntity;
import ggxnet.reload.repository.entity.PlayerStatsEntity;


public record PlayerLobbyDto(
        String id,
        String nickname,
        Boolean status,
        String ping,
        Integer totalWins,
        Integer totalLoses,
        Integer totalDraws,
        Integer totalGames,
        Double winToLoses) {

    public static PlayerLobbyDto of(PlayerConfigEntity playerConfig) {
        PlayerStatsEntity playerStats = playerConfig.getStats();
        int totalWin = playerStats.getTotalWin();
        int totalLose = playerStats.getTotalLose();
        boolean status = playerConfig.isActive();
        return new PlayerLobbyDto(
                playerConfig.getId(),
                playerConfig.getUserName(),
                status,
                !status ? "-" : "...",
                totalWin,
                totalLose,
                playerStats.getTotalDraw(),
                playerStats.getTotalBattle(),
                totalLose > 0 ? (double) totalWin / totalLose : totalWin);
    }
}
