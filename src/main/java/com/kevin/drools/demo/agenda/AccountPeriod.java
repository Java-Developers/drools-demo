package com.kevin.drools.demo.agenda;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author Kevin
 * @date 2014/5/10.
 */
public class AccountPeriod {
    private Date start;
    private Date end;
    private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

    public AccountPeriod(Date start, Date end) {
        this.start = start;
        this.end = end;
    }

    public Date getStart() {
        return start;
    }

    public void setStart(Date start) {
        this.start = start;
    }

    public Date getEnd() {
        return end;
    }

    public void setEnd(Date end) {
        this.end = end;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("AccountPeriod: [Start = ");
        builder.append(sdf.format(start));
        builder.append(", End = ");
        builder.append(sdf.format(end));
        builder.append(']');
        return builder.toString();
    }
}
