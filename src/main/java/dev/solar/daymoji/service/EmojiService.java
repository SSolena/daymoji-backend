package dev.solar.daymoji.service;

import dev.solar.daymoji.domain.diary.Emoji;
import dev.solar.daymoji.domain.diary.EmojiRepository;
import dev.solar.daymoji.domain.diary.TodayEmoji;
import dev.solar.daymoji.error.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class EmojiService {

    private final EmojiRepository emojiRepository;

    public Emoji findById(Long id) {
        return emojiRepository.findById(id).orElseThrow(() -> new NotFoundException("Not founded Emoji."));
    }

    public List<TodayEmoji> findAll(List<Long> todayEmojiIds) {
        List<TodayEmoji> todayEmojis = new ArrayList<>();
        for (Long todayEmojiId : todayEmojiIds) {
            todayEmojis.add(
                    new TodayEmoji(findById(todayEmojiId))
            );
        }
        return todayEmojis;
    }
}
