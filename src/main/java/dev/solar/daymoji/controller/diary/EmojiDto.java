package dev.solar.daymoji.controller.diary;

import lombok.*;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class EmojiDto {

    private Long id;
    private String codeOfKorean;
    private String codeOfEnglish;
}
