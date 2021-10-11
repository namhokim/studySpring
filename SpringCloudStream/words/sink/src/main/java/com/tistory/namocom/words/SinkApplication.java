package com.tistory.namocom.words;

import com.tistory.namocom.words.model.Word;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.function.Consumer;

@SpringBootApplication
public class SinkApplication {

	public static void main(String[] args) {
		SpringApplication.run(SinkApplication.class, args);
	}

	@Bean
	public Consumer<Word> wordToConsole() {
		return System.out::println;
	}

}
