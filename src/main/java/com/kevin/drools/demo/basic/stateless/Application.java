package com.kevin.drools.demo.basic.stateless;

import java.util.Date;

/**
 * @author Kevin
 * @date 2014/4/19.
 */
public class Application {
    private Date dateApplied;
    private boolean valid;


    public Date getDateApplied() {
        return dateApplied;
    }

    public void setDateApplied(Date dateApplied) {
        this.dateApplied = dateApplied;
    }

    public boolean isValid() {
        return valid;
    }

    public void setValid(boolean valid) {
        this.valid = valid;
    }
}
