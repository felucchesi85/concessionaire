package com.theGoodCar.concesionarioApplication.infrastructure.utils;

import org.springframework.util.MultiValueMap;

import java.util.Calendar;
import java.util.Map;

public class ValidateSellCar {
    private final static String ONLY_CHARACTERS_NUMBERS = "[A-Za-z0-9, ]*";
    private final static String ONLY_FORMAT_YEAR = "[0-9]{4}";
    private final static String ONLY_FORMAT_PRICE = "[0-9]*[,0-9]*?";

    public static boolean validationSell (Map<String, String> allParams) {
        for (Map.Entry<String, String> value : allParams.entrySet()) {
            if (areNotWellFormed(value.getKey(), value.getValue())) {
                return false;
            }
        }
        return true;
    }

    public static boolean areNotWellFormed (String key, String value) {
        switch (key){
            case "yearManufacturer":
                return validation(value, ONLY_FORMAT_YEAR) || yearIsMinorThanToday(value);
            case "price":
                return validation(value, ONLY_FORMAT_PRICE);
            default:
                return validation(value, ONLY_CHARACTERS_NUMBERS);
        }
    }

    public static boolean validation (String validateValue, String pattern) {
        boolean isNotEmpty = validateValue.isEmpty();
        if (isNotEmpty) {
            return true;
        }
        if (!validateValue.matches(pattern)) {
            return true;
        }
        return false;
    }

    public static boolean yearIsMinorThanToday(String value) {
        int year = Integer.parseInt(value);
        int yearToday = Calendar.getInstance().get(Calendar.YEAR);
        if (yearToday >= year && year >= yearToday - 20) {
            return false;
        }
        return true;
    }
}
