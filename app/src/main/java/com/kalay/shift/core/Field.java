package com.kalay.shift.core;

public enum Field {
    TENNIS, SWIMMING, GOLF, ITALIAN_FOOD, CHINESE_FOOD;

    public static Field[] getOptionalFields(FieldCategory category){
        switch (category){
            case FOOD:
                return new Field[] {ITALIAN_FOOD,CHINESE_FOOD,};
            case SPORTS:
                return new Field[] {TENNIS, SWIMMING, GOLF};
            case TRAVEL:
                return new Field[] {};
            case CULTURE:
                return new Field[] {};
            case PERSONAL:
                return new Field[] {};
        }
        return new Field[]{};
    }
}
