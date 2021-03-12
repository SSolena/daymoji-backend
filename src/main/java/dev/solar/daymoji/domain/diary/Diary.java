package dev.solar.daymoji.domain.diary;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Diary {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private String contents;

    @ManyToOne
    @JoinColumn(name = "EMOJI_ID")
    private Emoji representativeEmoji;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "DIARY_ID")
    private List<TodayEmoji> todayEmojis = new ArrayList<>();

    private double latitude;

    private double longitude;

    private String nameOfLocation;

    private boolean isOpened;

    private LocalDateTime createdDate;

    private LocalDateTime modifiedDate;

    public Diary(Long id, String title, String contents, double latitude, double longitude, String nameOfLocation, boolean isOpened) {
        this.id = id;
        this.title = title;
        this.contents = contents;
        this.latitude = latitude;
        this.longitude = longitude;
        this.nameOfLocation = nameOfLocation;
        this.isOpened = isOpened;
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
