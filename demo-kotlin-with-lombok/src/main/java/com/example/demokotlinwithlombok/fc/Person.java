package com.example.demokotlinwithlombok.fc;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@EqualsAndHashCode
@ToString
@Getter
@Setter
public class Person {
    private String name;
    private int age;

    public static void main(String[] args) {
        Person person = new Person();
        person.setName("Martin");
        person.setAge(59);
        System.out.println(person);
    }
}
