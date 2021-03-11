package dev.solar.daymoji.domain.diary;

import lombok.*;

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

    @OneToMany
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

    public void modifyTitle(String title) {
        this.title = title;
    }

    public void modifyContents(String contents) {
        this.contents = contents;
    }
}
