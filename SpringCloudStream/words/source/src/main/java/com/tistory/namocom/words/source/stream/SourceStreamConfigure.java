package com.tistory.namocom.words.source.stream;

import com.tistory.namocom.words.model.Word;
import com.tistory.namocom.words.source.queue.WordQueue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.function.Supplier;

@Configuration
public class SourceStreamConfigure {

    @Bean
    public Supplier<Word> source(WordQueue wordQueue) {
        return wordQueue::pop;
    }

}
