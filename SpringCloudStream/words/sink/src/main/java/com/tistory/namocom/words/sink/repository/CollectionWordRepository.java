package com.tistory.namocom.words.sink.repository;

import com.tistory.namocom.words.error.DuplicationWordException;
import com.tistory.namocom.words.model.Word;
import org.springframework.stereotype.Repository;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public class CollectionWordRepository implements WordRepository {

    private final Map<String, Word> storage = new ConcurrentHashMap<>();

    @Override
    public void add(Word word) {
        if (storage.containsKey(word.getId())) {
            throw new DuplicationWordException(word);
        }
        storage.put(word.getId(), word);
    }

}
