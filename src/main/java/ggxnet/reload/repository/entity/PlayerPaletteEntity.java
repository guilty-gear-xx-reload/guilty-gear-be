package ggxnet.reload.repository.entity;

import javax.persistence.*;
import java.util.List;


@Entity
@Table(name = "player_palette")
public class PlayerPaletteEntity {
    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "player_palette_seq"
    )
    @SequenceGenerator(
            name = "player_palette_seq",
            allocationSize = 1
    )
    private long id;

    @ManyToOne
    @JoinColumn(name = "player_id")
    private PlayerEntity player;

    @ManyToOne
    @JoinColumn(name = "palette_id")
    private PaletteEntity palette;
}