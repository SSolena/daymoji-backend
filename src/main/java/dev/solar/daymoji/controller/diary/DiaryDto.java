package dev.solar.daymoji.controller.diary;

import lombok.*;

import java.time.LocalDateTime;

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
}
