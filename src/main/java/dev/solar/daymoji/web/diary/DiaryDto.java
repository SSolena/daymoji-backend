package dev.solar.daymoji.web.diary;

import dev.solar.daymoji.domain.diary.Diary;
import lombok.*;

import java.time.LocalDateTime;

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
    private Long representativeEmoji;
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
