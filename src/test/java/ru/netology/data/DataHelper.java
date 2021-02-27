package ru.netology.data;

import lombok.Value;

public class DataHelper {
    private DataHelper() {
    }

    @Value
    public static class AuthInfo {
        private String login;
        private String password;

    }

    public static AuthInfo getAuthInfo() {
        return new AuthInfo("vasya", "qwerty123");
    }

    public static AuthInfo getOtherAuthInfo(AuthInfo original) {
        return new AuthInfo("petya", "123qwerty");
    }

    @Value
    public static class VerificationCode {
        private String code;
    }

    public static VerificationCode getVerificationCodeFor(AuthInfo authInfo) {
        return new VerificationCode("12345");
    }

    @Value
    public static class CardNumber1 {
        private String Card01;

    }

    public static CardNumber1 getCatdNumber1() {
        return new CardNumber1("5559 0000 0000 0001");
    }

    @Value
    public static class CardNumber2 {
        private String Card02;
    }

    public static CardNumber2 getCardNumber2() {
        return new CardNumber2("5559 0000 0000 0002");
    }
}