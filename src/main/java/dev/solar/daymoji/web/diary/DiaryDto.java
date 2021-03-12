package dev.solar.daymoji.web.diary;

import dev.solar.daymoji.domain.diary.Diary;
import dev.solar.daymoji.domain.diary.Emoji;
import dev.solar.daymoji.domain.diary.TodayEmoji;
import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.springframework.beans.BeanUtils.copyProperties;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class DiaryDto {

    private Long id;
    private String title;
    private String contents;
    private Emoji representativeEmoji;
    private List<TodayEmoji> todayEmojis = new ArrayList<>();
    private double latitude;
    private double longitude;
    private String nameOfLocation;
    private boolean isOpened;
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;

    public DiaryDto(Diary source) {
        copyProperties(source, this);
    }
}
