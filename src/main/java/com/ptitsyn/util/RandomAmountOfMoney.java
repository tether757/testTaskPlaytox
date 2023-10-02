package com.ptitsyn.util;

import com.ptitsyn.Account;

import java.util.Random;
/**
    Класс для выдачи случайного количества денег, которое можно перевести с аккаунта
 */

public class RandomAmountOfMoney {
    public static int RandomAmountOfMoneyToTransfer(Account accountThatSends) {
        int min = 1;
        int max = accountThatSends.getMoney();
        Random random = new Random();
        return random.nextInt(max - min + 1) + min;
    }
}
