package com.tistory.namocom.words;

import com.tistory.namocom.words.error.DuplicationWordException;
import com.tistory.namocom.words.model.Word;
import com.tistory.namocom.words.sink.repository.WordRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.stream.annotation.StreamRetryTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.retry.support.RetryTemplate;
import org.springframework.retry.support.RetryTemplateBuilder;

import java.util.function.Consumer;

@SpringBootApplication
public class SinkApplication {

	public static void main(String[] args) {
		SpringApplication.run(SinkApplication.class, args);
	}

	@Bean
	public Consumer<Word> wordToConsole(WordRepository wordRepository) {
		return word -> {
			wordRepository.add(word);
			System.out.println(word);
		};
	}

//	@StreamRetryTemplate
//	public RetryTemplate myRetryTemplate() {
//		final RetryTemplateBuilder builder = RetryTemplate.builder();
//		builder.notRetryOn(DuplicationWordException.class);
//		return builder.build();
//	}

}
