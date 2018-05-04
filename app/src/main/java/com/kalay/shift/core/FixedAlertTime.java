package com.kalay.shift.core;

import java.util.Calendar;
import java.util.Date;

public class FixedAlertTime extends AlertTime {
    Calendar alertTime;

    public FixedAlertTime(int hour,int minutes){
        alertTime = Calendar.getInstance();
        alertTime.set(Calendar.HOUR, hour);
        alertTime.set(Calendar.MINUTE, minutes);
    }

    @Override
    public Calendar getAlertTime() {
        return alertTime;
    }
}
