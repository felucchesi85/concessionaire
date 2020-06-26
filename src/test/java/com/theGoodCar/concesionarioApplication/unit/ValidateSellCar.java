package com.theGoodCar.concesionarioApplication.unit;

import com.theGoodCar.concesionarioApplication.domain.Car;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Map;
import java.util.TreeMap;

import static com.theGoodCar.concesionarioApplication.infrastructure.utils.ValidateBuyCar.validationBuy;
import static com.theGoodCar.concesionarioApplication.infrastructure.utils.ValidateSellCar.validation;
import static com.theGoodCar.concesionarioApplication.infrastructure.utils.ValidateSellCar.validationSell;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class ValidateSellCar {

    private final static String ONLY_CHARACTERS_NUMBERS = "[A-Za-z0-9, ]*";
    private final static String ONLY_FORMAT_YEAR = "[0-9]{4}";
    private final static String ONLY_FORMAT_PRICE = "[0-9]*[,0-9]*?";

    @Test
    void validate_success_form_buy_car(){
        //GIVEN
        Map<String, String> allParams = new TreeMap<>();
        allParams.put("brand", "foo");
        allParams.put("bodyType", "foo");
        allParams.put("model", "foo");
        allParams.put("yearManufacturer", "2000");
        allParams.put("carDetails", "foo");
        allParams.put("otherFeatures", "foo");
        allParams.put("color", "foo");
        allParams.put("transmission", "foo");
        allParams.put("description", "foo");
        allParams.put("price", "2000");

        // WHEN
        boolean isValidate = validationSell(allParams);

        // THEN
        assertThat(isValidate).isEqualTo(true);
    }

    @Test
    void validate_fail_form_buy_car_wrong_year_manufacturer(){
        //GIVEN
        Map<String, String> allParams = new TreeMap<>();
        allParams.put("brand", "foo");
        allParams.put("bodyType", "foo");
        allParams.put("model", "foo");
        allParams.put("yearManufacturer", "3000");
        allParams.put("carDetails", "foo");
        allParams.put("otherFeatures", "foo");
        allParams.put("color", "foo");
        allParams.put("transmission", "foo");
        allParams.put("description", "foo");
        allParams.put("price", "2000");

        // WHEN
        boolean isValidate = validationSell(allParams);

        // THEN
        assertThat(isValidate).isEqualTo(false);
    }

    @Test
    void validate_fail_form_buy_car_wrong_price(){
        //GIVEN
        Map<String, String> allParams = new TreeMap<>();
        allParams.put("brand", "foo");
        allParams.put("bodyType", "foo");
        allParams.put("model", "foo");
        allParams.put("yearManufacturer", "2000");
        allParams.put("carDetails", "foo");
        allParams.put("otherFeatures", "foo");
        allParams.put("color", "foo");
        allParams.put("transmission", "foo");
        allParams.put("description", "foo");
        allParams.put("price", "23.21");

        // WHEN
        boolean isValidate = validationSell(allParams);

        // THEN
        assertThat(isValidate).isEqualTo(false);
    }

    @Test
    void validate_fail_form_buy_car_wrong_other_fields(){
        //GIVEN
        Map<String, String> allParams = new TreeMap<>();
        //Wrong field
        allParams.put("brand", "foo#");
        allParams.put("bodyType", "foo");
        allParams.put("model", "foo");
        allParams.put("yearManufacturer", "2000");
        allParams.put("carDetails", "foo");
        allParams.put("otherFeatures", "foo");
        allParams.put("color", "foo");
        allParams.put("transmission", "foo");
        allParams.put("description", "foo");
        allParams.put("price", "2000");

        // WHEN
        boolean isValidate = validationSell(allParams);

        // THEN
        assertThat(isValidate).isEqualTo(false);
    }

    @Test
    void validation_not_empty(){
        //GIVEN +  WHEN
        boolean isValidate1 = validation("", ONLY_FORMAT_YEAR);
        boolean isValidate2 = validation("2000", ONLY_FORMAT_YEAR);
        boolean isValidate3 = validation("", ONLY_FORMAT_PRICE);
        boolean isValidate4 = validation("12100,30", ONLY_FORMAT_PRICE);
        boolean isValidate5 = validation("", ONLY_CHARACTERS_NUMBERS);
        boolean isValidate6 = validation("foo", ONLY_CHARACTERS_NUMBERS);

        // THEN
        assertThat(true).isEqualTo(isValidate1);
        assertThat(false).isEqualTo(isValidate2);
        assertThat(true).isEqualTo(isValidate3);
        assertThat(false).isEqualTo(isValidate4);
        assertThat(true).isEqualTo(isValidate5);
        assertThat(false).isEqualTo(isValidate6);
    }

    @Test
    void validation_with_pattern(){
        //GIVEN +  WHEN
        boolean isValidate1 = validation("2000", ONLY_FORMAT_YEAR);
        boolean isValidate2 = validation("1900", ONLY_FORMAT_YEAR);
        boolean isValidate3 = validation("2100", ONLY_FORMAT_YEAR);
        boolean isValidate4 = validation("9999", ONLY_FORMAT_YEAR);
        boolean isValidate5 = validation("1111", ONLY_FORMAT_YEAR);
        boolean isValidate6 = validation("10000", ONLY_FORMAT_YEAR);
        boolean isValidate7 = validation("999", ONLY_FORMAT_YEAR);

        boolean isValidate8 = validation("12100,30", ONLY_FORMAT_PRICE);
        boolean isValidate9 = validation("0,30", ONLY_FORMAT_PRICE);
        boolean isValidate10 = validation("12100.30", ONLY_FORMAT_PRICE);
        boolean isValidate11 = validation("121a00,30", ONLY_FORMAT_PRICE);
        boolean isValidate12 = validation("12100", ONLY_FORMAT_PRICE);

        boolean isValidate13 = validation("foo", ONLY_CHARACTERS_NUMBERS);
        boolean isValidate14 = validation("foo foo", ONLY_CHARACTERS_NUMBERS);
        boolean isValidate15 = validation("foo foo 5", ONLY_CHARACTERS_NUMBERS);
        boolean isValidate16 = validation("foo, foo 5", ONLY_CHARACTERS_NUMBERS);

        // THEN
        assertThat(false).isEqualTo(isValidate1);
        assertThat(false).isEqualTo(isValidate2);
        assertThat(false).isEqualTo(isValidate3);
        assertThat(false).isEqualTo(isValidate4);
        assertThat(false).isEqualTo(isValidate5);
        assertThat(true).isEqualTo(isValidate6);
        assertThat(true).isEqualTo(isValidate7);

        assertThat(false).isEqualTo(isValidate8);
        assertThat(false).isEqualTo(isValidate9);
        assertThat(true).isEqualTo(isValidate10);
        assertThat(true).isEqualTo(isValidate11);
        assertThat(false).isEqualTo(isValidate12);

        assertThat(false).isEqualTo(isValidate13);
        assertThat(false).isEqualTo(isValidate14);
        assertThat(false).isEqualTo(isValidate15);
        assertThat(false).isEqualTo(isValidate16);
    }
}
