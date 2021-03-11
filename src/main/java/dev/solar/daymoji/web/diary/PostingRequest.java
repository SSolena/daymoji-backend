package dev.solar.daymoji.web.diary;

import dev.solar.daymoji.domain.diary.Diary;
import lombok.*;

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
    //    private List<Long> todayEmojis = new ArrayList<>();
    private double latitude;
    private double longitude;
    private String nameOfLocation;
    private boolean isOpened;

    public Diary newDiary() {
        return new Diary(id, title, contents, latitude, longitude, nameOfLocation, isOpened);
    }
}
