package dev.solar.daymoji.model.diary;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@EqualsAndHashCode(of = "id")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Diary {

    private Long id;
    private String title;
    private String contents;
    private Emoji representativeEmoji;
    private double latitude;
    private double longitude;
    private String nameOfLocation;
    private boolean isOpened;
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;

    public void modifyTitle(String title) {
        this.title = title;
    }

    public void modifyContents(String contents) {
        this.contents = contents;
    }
}
