package com.ptitsyn.util;

import java.security.SecureRandom;

/**
 * Класс для генерации ID аккаунта
 */
public class RandomAccountId {
    public static String generateRandomString(int length) {
        final String CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        final SecureRandom random = new SecureRandom();

        StringBuilder sb = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            int randomIndex = random.nextInt(CHARACTERS.length());
            char randomChar = CHARACTERS.charAt(randomIndex);
            sb.append(randomChar);
        }
        return sb.toString();
    }
}
