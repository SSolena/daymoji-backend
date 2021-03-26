package dev.solar.daymoji.domain.diary;

import dev.solar.daymoji.domain.BaseTimeEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Diary extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private String contents;

    @ManyToOne
    @JoinColumn(name = "EMOJI_ID")
    private Emoji representativeEmoji;

    @Builder.Default
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "DIARY_ID")
    private List<TodayEmoji> todayEmojis = new ArrayList<>();

    private double latitude;

    private double longitude;

    private String nameOfLocation;

    private boolean opened;

    public Diary(String title, String contents, double latitude, double longitude, String nameOfLocation, boolean opened) {
        this.title = title;
        this.contents = contents;
        this.latitude = latitude;
        this.longitude = longitude;
        this.nameOfLocation = nameOfLocation;
        this.opened = opened;
    }

    public void postRepresentativeEmoji(Emoji emoji) {
        this.representativeEmoji = emoji;
    }

    public void postTodayEmojis(List<TodayEmoji> todayEmojis) {
        this.todayEmojis = todayEmojis;
    }

    public void modifyTitle(String title) {
        this.title = title;
    }

    public void modifyContents(String contents) {
        this.contents = contents;
    }
}
