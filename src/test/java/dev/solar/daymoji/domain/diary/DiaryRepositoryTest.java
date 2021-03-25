package dev.solar.daymoji.domain.diary;

import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@ActiveProfiles("test")
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
                .opened(true)
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

    @Test
    public void BaseTimeEntity_등록() {
        //given
        LocalDateTime now = LocalDateTime.of(2021,3,12,6,30,0);
        String title = "Cafe Tour";
        String contents = "so goooooooood.";
        double latitue = 12.5;
        double longitude = 16.3;
        String nameOfLocation = "My House";

        diaryRepository.save(Diary.builder()
                .title(title)
                .contents(contents)
                .opened(true)
                .latitude(latitue)
                .longitude(longitude)
                .nameOfLocation(nameOfLocation)
                .build());

        //when
        List<Diary> diaries = diaryRepository.findAll();

        //then
        Diary diary = diaries.get(0);

        System.out.println(">>>>>>>>> createdDate : " + diary.getCreatedDateTime() +", modifiedDate : " + diary.getModifiedDateTime());

        assertThat(diary.getCreatedDateTime()).isAfter(now);
        assertThat(diary.getModifiedDateTime()).isAfter(now);
    }
}
