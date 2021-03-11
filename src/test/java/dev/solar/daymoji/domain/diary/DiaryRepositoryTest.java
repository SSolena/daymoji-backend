package dev.solar.daymoji.domain.diary;

import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DiaryRepositoryTest {

    @Autowired
    DiaryRepository diaryRepository;

    @After
    public void cleanup() throws Exception {
        diaryRepository.deleteAll();
    }

    @Test
    public void 게시글저장_불러오기() {
        //given
        String title = "run day";
        String contents = "so goooooooood.";
        double latitue = 12.5;
        double longitude = 16.3;
        String nameOfLocation = "My House";

        diaryRepository.save(Diary.builder()
                .title(title)
                .contents(contents)
                .isOpened(true)
                .latitude(latitue)
                .longitude(longitude)
                .nameOfLocation(nameOfLocation)
                .build());

        //when
        List<Diary> diaries = diaryRepository.findAll();

        //then
        assertThat(diaryRepository.count()).isNotEqualTo(0);
        Diary diary = diaries.get(0);
        assertThat(title).isEqualTo(diary.getTitle());
        assertThat(contents).isEqualTo(diary.getContents());
        assertThat(true).isEqualTo(diary.isOpened());
        assertThat(latitue).isEqualTo(diary.getLatitude());
        assertThat(longitude).isEqualTo(diary.getLongitude());
        assertThat(nameOfLocation).isEqualTo(diary.getNameOfLocation());
    }
}
