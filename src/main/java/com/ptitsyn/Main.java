package com.ptitsyn;

import com.ptitsyn.util.Info;
import com.ptitsyn.util.RandomAccountId;
import com.ptitsyn.util.RandomAmountOfMoney;
import com.ptitsyn.util.RandomSleepTime;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;


/**
 * Класс Main внутри содержит внутренний статический класс для генерации транзакций.
 * Содержит переменную transfersCount - для слежения за счётчиком количества транзакций.
 * Содержит метод заполнения листа accounts.
 * В моём примере создано 4 нити и лист с аккаунтами accounts, заполненный 10-тью аккаунтами.
 */

public class Main {
    private static List<Account> accounts = new ArrayList<>();
    private static AtomicInteger transfersCount = new AtomicInteger(1);

    //Метод для заполнения аккаунтами листа accounts
    private static void createAccounts(int count){
        for(int i = 0; i < count; i++){
            accounts.add(new Account(RandomAccountId.generateRandomString(Info.ID_LENGTH), Info.MONEY));
        }
    }

    //Метод для выдачи случайного аккаунта из списка
    private static Account randomAccount() {
        int max = accounts.size();
        Random random = new Random();
        return accounts.get(random.nextInt(max));
    }


    public static void main(String[] args) throws InterruptedException {
        createAccounts(10);
        ThreadMoneyTransfers thread1 = new ThreadMoneyTransfers();
        ThreadMoneyTransfers thread2 = new ThreadMoneyTransfers();
        ThreadMoneyTransfers thread3 = new ThreadMoneyTransfers();
        ThreadMoneyTransfers thread4 = new ThreadMoneyTransfers();

        thread1.start();
        thread2.start();
        thread3.start();
        thread4.start();
        thread1.join();
        thread2.join();
        thread3.join();
        thread4.join();

        //вывод в консоль состояния аккаунтов(после всех операций)
        System.out.println(accounts);

        //вывод в консоль суммы всех балансов аккаунтов(проверяем сумму денег)
        int sum = 0;
        for (Account acc: accounts) {
            sum += acc.getMoney();
        }
        System.out.println(sum);

    }



    static class ThreadMoneyTransfers extends Thread {
        @Override
        public void run() {
            while (transfersCount.get() <= Info.MAX_TRANSFERS) {
                    Account accountThatSends = randomAccount();
                    Account accountThatReceives = randomAccount();
                    try {
                        if (accountThatSends != accountThatReceives && accountThatSends.getMoney() > 0) {
                            int AmountOfMoneyToTransfer = RandomAmountOfMoney.RandomAmountOfMoneyToTransfer(accountThatSends);
                            AccountOperations.transfer(accountThatSends, accountThatReceives, AmountOfMoneyToTransfer);
                            System.out.println(transfersCount.getAndIncrement());
                            Thread.sleep(RandomSleepTime.randomIntBetween1000and2000());
                        }
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                        throw new RuntimeException(e);
                    }
            }
        }
    }
}
