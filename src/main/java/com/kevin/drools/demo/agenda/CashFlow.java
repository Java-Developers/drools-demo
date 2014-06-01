package com.kevin.drools.demo.agenda;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author Kevin
 * @date 2014/5/10.
 */
public class CashFlow {
    private Date date;
    private double amount;
    private CashFlowType type;
    private int accountNo;
    private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

    public CashFlow(Date date, double amount, CashFlowType type, int accountNo) {
        this.date = date;
        this.amount = amount;
        this.type = type;
        this.accountNo = accountNo;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public CashFlowType getType() {
        return type;
    }

    public void setType(CashFlowType type) {
        this.type = type;
    }

    public int getAccountNo() {
        return accountNo;
    }

    public void setAccountNo(int accountNo) {
        this.accountNo = accountNo;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("CashFlow: [Date = ");
        builder.append(sdf.format(date));
        builder.append(", Amount = ");
        builder.append(amount);
        builder.append(", Type = ");
        builder.append(type);
        builder.append(", AccountNo = ");
        builder.append(accountNo);
        builder.append(']');
        return builder.toString();
    }
}
