package com.ptitsyn;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Objects;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;



/**
    Класс аккаунта
    Создан объект ReentrantLock для лока на самом объекте.
    Создан объект Logger для логирования изменений аккаунта.
 */

public class Account {
    private final String ID;
    private int money;
    private final Lock lock = new ReentrantLock();
    private static final Logger logger = LogManager.getLogger(Account.class);


    public Account(String ID, int money) {
        this.ID = ID;
        this.money = money;
    }

    public void deposit(int amount) {
        lock.lock();
        try {
            this.setMoney(getMoney() + amount);

            logger.info("Пополнение средств аккаунта с id=" + this.getID() + " в размере " + amount);
        }
        catch (Exception e) {
            logger.error( "При пополнении средств аккаунта с id=" + this.getID() + " произошла ошибка", e);
        }
        finally {
            lock.unlock();
        }
    }
    public void withdraw(int amount) {
        lock.lock();
        try {
            if(this.getMoney() >= amount){
                this.setMoney(getMoney() - amount);

                logger.info("Снятие средств с аккаунта с id=" + this.getID() + " в размере " + amount);
            }

        } catch (Exception e) {
            logger.error( "При снятии средств с аккаунта с id=" + this.getID() + " произошла ошибка", e);
        } finally {
            lock.unlock();
        }
    }




    public String getID() {
        return ID;
    }



    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Account account = (Account) o;
        return money == account.money && Objects.equals(ID, account.ID);
    }

    @Override
    public int hashCode() {
        return Objects.hash(ID, money);
    }

    @Override
    public String toString() {
        return "Account{" +
                "ID='" + ID + '\'' +
                ", money=" + money +
                '}';
    }
}
