package com.kalay.shift.core;

public enum Day {
    SUNDAY(0, "א"), MONDAY(1, "ב"), TUESDAY(2,"ג"), WEDNESDAY(3,"ד"), THURSDAY(4,"ה"), FRIDAY(5,"ו"), SATURDAY(6,"ש");
    int dayNum;
    String displayName;

    Day(int dayNum, String displayName) {
        this.dayNum = dayNum;
        this.displayName = displayName;
    }

    public int getDayNum() {
        return dayNum;
    }

    public String getDisplayName() {
        return displayName;
    }
}
