package ggxnet.reload.lobby.port.outgoing;

import ggxnet.reload.lobby.PlayerData;

import java.util.List;

public interface PlayerRepositoryPort {
    PlayerData findByName(String name);

    boolean existsByAddress(String address);

    List<PlayerData> findAll();

    void save(PlayerData playerData);
}
