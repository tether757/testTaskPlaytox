package com.ptitsyn;


/**
 * Класс для операций над объектом класса Account
 */
public class AccountOperations {

    public static boolean transfer(Account acc1, Account acc2, int transferMoney) {
        if(acc1.withdraw(transferMoney)){
            acc2.deposit(transferMoney);
            return true;
        }
        return false;


    }








}
