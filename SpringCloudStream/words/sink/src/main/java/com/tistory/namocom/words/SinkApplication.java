package com.tistory.namocom.words;

import com.tistory.namocom.words.model.Word;
import com.tistory.namocom.words.sink.repository.WordRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.stream.config.ListenerContainerCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.listener.AbstractMessageListenerContainer;
import org.springframework.kafka.listener.SeekToCurrentErrorHandler;
import org.springframework.util.backoff.FixedBackOff;

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

	@Bean
	public ListenerContainerCustomizer<AbstractMessageListenerContainer<?, ?>> getErrorHandler() {
		FixedBackOff backOff = new FixedBackOff(FixedBackOff.DEFAULT_INTERVAL, 0);
		SeekToCurrentErrorHandler handler = new SeekToCurrentErrorHandler(null, backOff);
		return (container, dest, grp) -> container.setErrorHandler(handler);
	}

}
