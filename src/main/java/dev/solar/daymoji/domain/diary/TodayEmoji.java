package dev.solar.daymoji.domain.diary;

import lombok.*;

import javax.persistence.*;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class TodayEmoji {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "EMOJI_ID")
    private Emoji emoji;

    public TodayEmoji(Emoji emoji) {
        this.emoji = emoji;
    }
}
