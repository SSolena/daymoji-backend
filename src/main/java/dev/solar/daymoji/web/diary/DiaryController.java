package dev.solar.daymoji.web.diary;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import dev.solar.daymoji.service.DiaryService;
import dev.solar.daymoji.service.EmojiService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.hateoas.MediaTypes;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.util.List;
import java.util.Map;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/api/diaries", produces = MediaTypes.HAL_JSON_VALUE)
public class DiaryController {
    private static final Logger log = LoggerFactory.getLogger(DiaryController.class);

    private final DiaryService diaryService;

    private final EmojiService emojiService;

    private final ModelMapper modelMapper;

    @PostMapping
    public ResponseEntity postDiary(@RequestBody Map<String, Object> requestBody) {
        log.debug("request : {}", requestBody);

        Gson gson = new Gson();
        JsonParser jsonParser = new JsonParser();
        JsonElement elementTodayEmojiIds = jsonParser.parse(requestBody.get("todayEmojiIds").toString());
        List<Long> todayEmojiIds = gson.fromJson(elementTodayEmojiIds, (new TypeToken<List<Long>>() {
        }).getType());

        PostingRequest request = modelMapper.map(requestBody, PostingRequest.class);
        request.setTodayEmojiIds(todayEmojiIds);

        URI createdUri = linkTo(methodOn(DiaryController.class).postDiary(null)).slash("{id}").toUri();
        return ResponseEntity.created(createdUri).body(new DiaryDto(diaryService.save(request.newDiary(), request.getRepresentativeEmoji(), request.getTodayEmojiIds())));
    }
}
