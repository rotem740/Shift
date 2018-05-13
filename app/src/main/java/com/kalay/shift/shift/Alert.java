package com.kalay.shift.shift;

import java.io.Serializable;
import java.util.Arrays;

/**
 * Created by romdolinger on 5/6/18.
 */

public class Alert implements Serializable {
    private String text;
    private boolean [] days;
    private String [] hours;

    public Alert(String text, boolean[] days, String[] hours) {
        this.text = text;
        this.days = days;
        this.hours = hours;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public boolean[] getDays() {
        return days;
    }

    public void setDays(boolean [] days) {
        this.days = days;
    }

    public String[] getHours() {
        return hours;
    }

    public void setHours(String[] hours) {
        this.hours = hours;
    }

    @Override
    public String toString() {
        return "Alert{" +
                "text='" + text + '\'' +
                ", days=" + Arrays.toString(days) +
                ", hours=" + Arrays.toString(hours) +
                '}';
    }
}
