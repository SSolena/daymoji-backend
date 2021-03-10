package dev.solar.daymoji.domain.diary;

import org.springframework.data.jpa.repository.JpaRepository;

public interface TodayEmojiRepository extends JpaRepository<TodayEmoji, Long> {
}
