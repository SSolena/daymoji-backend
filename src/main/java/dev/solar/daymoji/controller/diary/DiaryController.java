package dev.solar.daymoji.controller.diary;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.hateoas.MediaTypes;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping(value = "/api/diaries", produces = MediaTypes.HAL_JSON_VALUE)
public class DiaryController {
    private static final Logger log = LoggerFactory.getLogger(DiaryController.class);

    @PostMapping
    public ResponseEntity createDiary(@RequestBody DiaryDto diaryDto) {
        log.debug("diaryDto : {}", diaryDto);
        URI createdUri = linkTo(methodOn(DiaryController.class).createDiary(null)).slash("{id}").toUri();
        diaryDto.setId(10L);
        return ResponseEntity.created(createdUri).body(diaryDto);
    }
}
