package com.ptitsyn;


/**
 * Класс для операций над объектом класса Account
 */
public class AccountOperations {

    public static void transfer(Account acc1, Account acc2, int transferMoney) {

        acc1.withdraw(transferMoney);
        acc2.deposit(transferMoney);

    }








}
