package com.theGoodCar.concesionarioApplication.infrastructure.utils;

public class ValidateBuyCar {
    private final static String ONLY_CHARACTERS = "[A-Za-z ]*";
    private final static String ONLY_DNI = "[0-9]{8}[A-Z]{1}";
    private final static String ONLY_AGREE = "agree";

    public static boolean validationBuy (String name, String lastName, String dni, String agree) {
        if (areWellFormed(name, lastName, dni, agree)) {
            return true;
        }
        return false;
    }

    public static boolean areWellFormed (String name, String lastName, String dni, String agree) {
        return validation(name, ONLY_CHARACTERS) &&
                validation(lastName, ONLY_CHARACTERS) &&
                validation(dni, ONLY_DNI) &&
                validation(agree, ONLY_AGREE);
    }

    public static boolean validation (String validateValue, String pattern) {
        boolean isNotEmpty = !validateValue.isEmpty();
        if (isNotEmpty) {
            if (validateValue.matches(pattern)) {
                return true;
            }
        }
        return false;
    }
}
