package dev.solar.daymoji.domain.diary;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Emoji {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String codeOfKorean;

    private String codeOfEnglish;

    @Builder.Default
    @JsonIgnore
    @OneToMany(mappedBy = "emoji")
    private List<TodayEmoji> todayEmojis = new ArrayList<>();
}
