package com.tistory.namocom.words.source.endpoint;

import com.tistory.namocom.words.model.Word;
import com.tistory.namocom.words.source.queue.WordQueue;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
public class WordGenerationEndpoint {

    private final WordQueue wordQueue;

    public WordGenerationEndpoint(WordQueue wordQueue) {
        this.wordQueue = wordQueue;
    }

    @PostMapping(path = "/word")
    public Word generateWord(@RequestParam String text) {
        final Word word = new Word(UUID.randomUUID().toString(), text);
        wordQueue.push(word);
        return word;
    }

}
