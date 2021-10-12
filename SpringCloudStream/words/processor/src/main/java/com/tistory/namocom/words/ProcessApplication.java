package com.tistory.namocom.words;

import com.tistory.namocom.words.model.Word;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.function.Function;

@SpringBootApplication
public class ProcessApplication {

    public static void main(String[] args) {
        SpringApplication.run(ProcessApplication.class, args);
    }

    @Bean
    public Function<Word, Word> wordToUpperCase() {
        return originalWord -> {
            final Word upperWord = new Word(originalWord.getId(), originalWord.getValue().toUpperCase());
            System.out.printf("[%s] %s -> %s\n",
                    originalWord.getId(), originalWord.getValue(), upperWord.getValue());
            return upperWord;
        };
    }

}
