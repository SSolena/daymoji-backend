package dev.solar.daymoji.web.diary;

import dev.solar.daymoji.domain.diary.Diary;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class PostingRequest {

    private Long id;
    private String title;
    private String contents;
    private Long representativeEmoji;
    private List<Long> todayEmojiIds = new ArrayList<>();
    private double latitude;
    private double longitude;
    private String nameOfLocation;
    private boolean opened;

    public Diary newDiary() {
        Diary diary = new Diary(id, title, contents, latitude, longitude, nameOfLocation, opened);
        return diary;
    }
}
