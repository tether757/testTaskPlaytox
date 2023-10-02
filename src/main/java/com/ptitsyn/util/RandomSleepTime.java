package com.ptitsyn.util;

import java.util.Random;

/**
 * Класс для выдачи случайного числа сна для метода Thread.sleep(number)
 */
public class RandomSleepTime {
    public static int randomIntBetween1000and2000() {
        int min = 1000;
        int max = 2000;
        Random random = new Random();
        return random.nextInt(max - min + 1) + min;
    }
}
