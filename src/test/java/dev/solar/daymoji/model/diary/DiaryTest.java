package dev.solar.daymoji.model.diary;

import org.junit.Test;
import org.junit.jupiter.api.DisplayName;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class DiaryTest {

    @Test
    @DisplayName("빌더가 있는지 확인")
    public void builder() {
        Diary diary = Diary.builder()
                .title("Test용 일기")
                .contents("Test용 내용")
                .build();
        assertThat(diary).isNotNull();
    }

    @Test
    @DisplayName("자바빈 스팩에 준하는지 - 기본 생성자로 객체를 만들 수 있어야 함")
    public void javaBean() {
        // Given
        String title = "2021.03.04 Diary";
        String contents = "Today's Emotion is ...";

        // When
        Diary diary = new Diary();
        diary.modifyTitle(title);
        diary.modifyContents(contents);

        // Then
        assertThat(diary).isNotNull();
        assertEquals(title, diary.getTitle());
        assertEquals(contents, diary.getContents());
    }

}
