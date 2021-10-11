package com.tistory.namocom.words.model;

import lombok.Getter;
import lombok.ToString;

import java.beans.ConstructorProperties;

@Getter
@ToString
public class Word {
    private final String id;
    private final String value;

    @ConstructorProperties({"id", "value"})
    public Word(String id, String value) {
        this.id = id;
        this.value = value;
    }

}
