package com.tistory.namocom.words.error;

import com.tistory.namocom.words.model.Word;

public class DuplicationWordException extends RuntimeException {
    public DuplicationWordException(Word word) {
        super(String.format("Word '%s' is already exists with ID: %s", word.getValue(), word.getId()));
    }
}
