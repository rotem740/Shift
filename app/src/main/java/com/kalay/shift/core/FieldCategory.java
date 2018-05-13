package com.kalay.shift.core;

import java.util.List;

public enum FieldCategory {
    FOOD, SPORTS, CULTURE, TRAVEL, PERSONAL;

    public static FieldCategory[] getOptionalCategories(){
        return FieldCategory.values();
    }
}
