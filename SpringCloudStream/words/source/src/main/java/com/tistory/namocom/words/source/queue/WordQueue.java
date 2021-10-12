package com.tistory.namocom.words.source.queue;

import com.tistory.namocom.words.model.Word;
import org.springframework.stereotype.Service;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

@Service
public class WordQueue {

    private final Queue<Word> queue = new ConcurrentLinkedQueue<>();

    public void push(Word word) {
        this.queue.add(word);
    }

    public Word pop() {
        if (!this.queue.isEmpty()) {
            System.out.println(this.queue.peek());
        }
        return this.queue.poll();
    }

}
