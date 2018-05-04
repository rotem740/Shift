package com.kalay.shift.core;

import java.util.Calendar;
import java.util.List;

public abstract class AlertTime {
    private List<Day> alertDays;

   abstract public Calendar getAlertTime();

    public void setAlertDays(List<Day> alertDays) {
        this.alertDays = alertDays;
    }
}
