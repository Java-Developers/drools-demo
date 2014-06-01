package com.kevin.drools.demo.agenda;

/**
 * @author Kevin
 * @date 2014/5/10.
 */
public class Account {
    private int   accountNo;
    private double balance;

    public Account(int accountNo, double balance) {
        this.accountNo = accountNo;
        this.balance = balance;
    }

    public int getAccountNo() {
        return accountNo;
    }

    public void setAccountNo(int accountNo) {
        this.accountNo = accountNo;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("Account: [AccountNo = ");
        builder.append(accountNo);
        builder.append(", Balance = ");
        builder.append(balance);
        builder.append(']');
        return builder.toString();
    }
}
