package dev.solar.daymoji.web.diary;

import dev.solar.daymoji.service.DiaryService;
import lombok.RequiredArgsConstructor;
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

@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/api/diaries", produces = MediaTypes.HAL_JSON_VALUE)
public class DiaryController {
    private static final Logger log = LoggerFactory.getLogger(DiaryController.class);

    private final DiaryService diaryService;

    @PostMapping
    public ResponseEntity postDiary(@RequestBody PostingRequest request) {
        log.debug("request : {}", request);
        URI createdUri = linkTo(methodOn(DiaryController.class).postDiary(null)).slash("{id}").toUri();
        return ResponseEntity.created(createdUri).body(new DiaryDto(diaryService.save(request.newDiary())));
    }
}