package com.tistory.namocom.mvc.java;

public class TelecomTester {
    public static void main(String[] args) {
        TelecomFactory factory = new TelecomFactory();
        TelecomHandler telecomHandler = factory.create(null);
        System.out.println(telecomHandler);
    }
}

class SktHandler implements TelecomHandler{
    @Override
    public void handle() {

    }
}

class KtHandler implements TelecomHandler{
    @Override
    public void handle() {

    }
}
class TelecomFactory {
    public TelecomHandler create(Telecom telecom) {
        switch (telecom) {
            case KT:
                return new SktHandler();
            case SKT:
                return new KtHandler();
            default:
                throw new IllegalStateException();
        }
    }
}
