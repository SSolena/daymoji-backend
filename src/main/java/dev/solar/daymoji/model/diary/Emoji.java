package dev.solar.daymoji.model.diary;

import lombok.*;

@Getter
@Setter
@EqualsAndHashCode(of = "id")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Emoji {

    private Long id;
    private String codeOfKorean;
    private String codeOfEnglish;

}
