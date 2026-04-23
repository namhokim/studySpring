package com.tistory.namocom.words.source.endpoint;

import com.tistory.namocom.words.model.Word;
import com.tistory.namocom.words.source.queue.WordQueue;
import org.springframework.lang.Nullable;
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
    public Word generateWord(@RequestParam(required = false) @Nullable String id, @RequestParam String text) {
        final String wordId = generateIfNotSupported(id);
        final Word word = new Word(wordId, text);
        wordQueue.push(word);
        return word;
    }

    private String generateIfNotSupported(@Nullable String parameterId) {
        if (parameterId == null) {
            return UUID.randomUUID().toString();
        }
        return parameterId;
    }

}
