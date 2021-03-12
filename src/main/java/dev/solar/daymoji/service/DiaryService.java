package dev.solar.daymoji.service;

import dev.solar.daymoji.domain.diary.Diary;
import dev.solar.daymoji.domain.diary.DiaryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class DiaryService {

    private final DiaryRepository diaryRepository;

    private final EmojiService emojiService;

    public Diary save(Diary diary, Long emojiId, List<Long> todayEmojiIds) {
        diary.postRepresentativeEmoji(emojiService.findById(emojiId));
        diary.postTodayEmojis(emojiService.findAll(todayEmojiIds));
        return diaryRepository.save(diary);
    }
}
