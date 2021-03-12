package dev.solar.daymoji.web.diary;

import com.fasterxml.jackson.databind.ObjectMapper;
import dev.solar.daymoji.domain.diary.*;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.hateoas.MediaTypes;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.filter.CharacterEncodingFilter;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class DiaryControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private DiaryRepository diaryRepository;

    @Autowired
    private EmojiRepository emojiRepository;

    @Autowired
    private WebApplicationContext ctx;

    @Before
    public void setUp() throws Exception {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(ctx)
                .addFilters(new CharacterEncodingFilter("UTF-8", true))
                .alwaysDo(print())
                .build();
    }

    @Test
    @DisplayName("다이어리 생성")
    public void createDiary() throws Exception {
        Emoji representativeEmoji = emojiRepository.getOne(1L);
        List<Long> todayEmojis = new ArrayList<>();
        todayEmojis.add(1L);
        todayEmojis.add(2L);
        todayEmojis.add(3L);

        PostingRequest request = PostingRequest.builder()
                .title("Today's Diary")
                .contents("Sunny Day")
                .representativeEmoji(representativeEmoji.getId())
                .todayEmojiIds(todayEmojis)
                .nameOfLocation("친구집")
                .longitude(14.123)
                .latitude(23.123)
                .opened(true)
                .build();


        //@formatter:off
        MvcResult result = mockMvc.perform(post("/api/diaries")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaTypes.HAL_JSON)
                .content(objectMapper.writeValueAsString(request)))
            .andDo(print())
            .andExpect(jsonPath("id").exists())
            .andExpect(header().exists(HttpHeaders.LOCATION))
            .andExpect(header().string(HttpHeaders.CONTENT_TYPE, MediaTypes.HAL_JSON_VALUE+";charset=UTF-8"))
            .andExpect(status().isCreated())// 201 Status
            .andReturn();

        String content = result.getResponse().getContentAsString();
        DiaryDto diaryDto = objectMapper.readValue(content, DiaryDto.class);
        assertThat(diaryDto.getRepresentativeEmoji().getId()).isEqualTo(representativeEmoji.getId());
    }

    @Test
    @DisplayName("기존의 다이어리를 하나 조회")
    public void getDiary() throws Exception {
        // given
        Diary diary = this.buildDiary(100);
        diary = diaryRepository.save(diary);

        // when & then
        mockMvc.perform(get("/api/diaries/{id}", diary.getId()))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("id").exists())
                .andExpect(jsonPath("title").exists())
                .andExpect(jsonPath("representativeEmoji").exists())
                .andExpect(jsonPath("todayEmojis").exists())
                .andExpect(jsonPath("latitude").exists())
                .andExpect(jsonPath("longitude").exists())
                .andExpect(jsonPath("nameOfLocation").value(diary.getNameOfLocation()))
                .andExpect(jsonPath("opened").value(diary.isOpened()));
    }

    @Test
    @DisplayName("없는 일기 조회시 404 응답받기")
    public void getDiary404()throws Exception {
        // when & then
        mockMvc.perform(get("/api/diaries/999999"))
                .andExpect(status().isNotFound());
    }

    private Diary buildDiary(int index) {
    Emoji representativeEmoji = emojiRepository.getOne(1L);
    List<TodayEmoji> todayEmojis = new ArrayList<>();

        return Diary.builder()
                .title("Diary" + index)
                .contents("테스트 다이어리 내용")
                .representativeEmoji(representativeEmoji)
                .todayEmojis(todayEmojis)
                .nameOfLocation("친구집")
                .longitude(14.123)
                .latitude(23.123)
                .opened(true)
                .build();
    }
}
