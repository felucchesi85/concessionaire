package com.theGoodCar.concesionarioApplication.unit;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static com.theGoodCar.concesionarioApplication.infrastructure.utils.ValidateBuyCar.validationBuy;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class ValidateBuyCar {

    @Test
    void validate_success_form_buy_car(){
        // GIVEN
        String name = "foo";
        String lastName = "foo";
        String dni = "00000000A";
        String agree = "agree";

        // WHEN
        boolean isValidate = validationBuy(name, lastName, dni, agree);

        // THEN
        assertThat(isValidate).isEqualTo(true);
    }

    @Test
    void validate_form_buy_car_wrong_name(){
        // GIVEN
        String name = "foo1";
        String lastName = "foo";
        String dni = "00000000A";
        String agree = "agree";

        // WHEN
        boolean isValidate = validationBuy(name, lastName, dni, agree);

        // THEN
        assertThat(isValidate).isEqualTo(false);
    }

    @Test
    void validate_form_buy_car_wrong_last_name(){
        // GIVEN
        String name = "foo";
        String lastName = "foo1";
        String dni = "00000000A";
        String agree = "agree";

        // WHEN
        boolean isValidate = validationBuy(name, lastName, dni, agree);

        // THEN
        assertThat(isValidate).isEqualTo(false);
    }

    @Test
    void validate_form_buy_car_wrong_dni(){
        // GIVEN
        String name = "foo";
        String lastName = "foo";
        String dni1 = "000000000A";
        String dni2 = "0000000A";
        String dni3 = "000000000";
        String dni4 = "00000A00A";
        String agree = "agree";

        // WHEN
        boolean isValidate1 = validationBuy(name, lastName, dni1, agree);
        boolean isValidate2 = validationBuy(name, lastName, dni2, agree);
        boolean isValidate3 = validationBuy(name, lastName, dni3, agree);
        boolean isValidate4 = validationBuy(name, lastName, dni4, agree);

        // THEN
        assertThat(isValidate1).isEqualTo(false);
        assertThat(isValidate2).isEqualTo(false);
        assertThat(isValidate3).isEqualTo(false);
        assertThat(isValidate4).isEqualTo(false);
    }

    @Test
    void validate_form_buy_car_not_agree(){
        // GIVEN
        String name = "foo";
        String lastName = "foo";
        String dni = "00000000A";
        String agree = "notagree";

        // WHEN
        boolean isValidate = validationBuy(name, lastName, dni, agree);

        // THEN
        assertThat(isValidate).isEqualTo(false);
    }
}
