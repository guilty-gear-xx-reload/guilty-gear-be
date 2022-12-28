package ggxnet.reload.repository.entity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "player")
public class PlayerEntity {
    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "player_seq"
    )
    @SequenceGenerator(
            name = "player_seq",
            allocationSize = 1
    )
    private long id;
    private String nickname;


    @OneToOne
    @JoinColumn(name = "user_id")
    private UserEntity user;

    @OneToMany(mappedBy = "player")
    private List<PlayerPaletteEntity> playerPalettes;
}