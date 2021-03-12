package dev.solar.daymoji;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class DaymojiApplication {

    public static void main(String[] args) {
        SpringApplication.run(DaymojiApplication.class, args);
    }

}
