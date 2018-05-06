package com.kalay.shift.core;

import java.util.Calendar;
import java.util.Date;
import java.util.Random;

public class RandomAlertTime extends AlertTime {
    private Calendar fromTime;
    private Calendar toTime;

    public RandomAlertTime(int fromHours, int fromMin, int toHours, int toMin) {
        fromTime = Calendar.getInstance();
        fromTime.set(Calendar.HOUR, fromHours);
        fromTime.set(Calendar.MINUTE, fromMin);
        toTime = Calendar.getInstance();
        toTime.set(Calendar.HOUR, toHours);
        toTime.set(Calendar.MINUTE, toMin);
    }


    @Override
    public Calendar getAlertTime() {
        int fromMin = fromTime.get(Calendar.HOUR) * 60 + fromTime.get(Calendar.MINUTE);
        int toMin = toTime.get(Calendar.HOUR) * 60 + toTime.get(Calendar.MINUTE);

        int time = fromMin + new Random().nextInt(toMin - fromMin);
        Calendar result = Calendar.getInstance();
        result.set(Calendar.HOUR, time / 60);
        result.set(Calendar.MINUTE, time % 60);
        return result;
    }
}
