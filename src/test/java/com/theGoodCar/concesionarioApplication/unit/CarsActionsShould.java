package com.theGoodCar.concesionarioApplication.unit;

import com.theGoodCar.concesionarioApplication.domain.Car;
import com.theGoodCar.concesionarioApplication.domain.actions.CarsAction;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import java.util.*;

import static com.theGoodCar.concesionarioApplication.infrastructure.utils.FunctionsUtils.*;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class CarsActionsShould {

    @Autowired
    private CarsAction carsAction;

    @Test
    void create_pagination_with_six_cars(){
        // GIVEN
        int numberOfCarsForPage = 6;
        int page = 1;
        LinkedList<Car> cars = carsAction.createPaginationCars(page, numberOfCarsForPage);

        // THEN
        assertThat(cars.size()).isEqualTo(numberOfCarsForPage);
        assertThat(cars.getFirst()).hasNoNullFieldsOrProperties();
    }

    @Test
    void create_pagination_out_bounds(){
        // GIVEN
        int numberOfCarsForPage = 6;
        int page = carsAction.calculatePages(numberOfCarsForPage) + 1;
        LinkedList<Car> cars = carsAction.createPaginationCars(page, numberOfCarsForPage);

        // THEN
        assertThat(cars.size()).isEqualTo(0);
    }

    @Test
    void calculate_number_of_pages_according_number_of_cars_for_page(){
        // GIVEN
        int numberOfCardsForPage = 6;
        LinkedList<Car> listCars = createDefaultCars();
        int numberPagesWithListCars = (int) Math.ceil(listCars.size()/(double) numberOfCardsForPage);

        int numberPages = carsAction.calculatePages(numberOfCardsForPage);

        // THEN
        assertThat(numberPages).isEqualTo(numberPagesWithListCars);
    }

    @Test
    void find_car_by_serial_number(){
        // GIVEN
        Car wantedCar = carsAction.findCarBySerialNumber("1");

        // THEN
        assertThat(wantedCar.getBrand()).isEqualTo("Audi");
        assertThat(wantedCar.getYearManufacturer()).isEqualTo(2015);
        assertThat(wantedCar.getModel()).isEqualTo("A8 L");
    }

    @Test
    void find_car_by_wrong_serial_number(){
        // GIVEN
        Car wantedCar = carsAction.findCarBySerialNumber("12523");

        // THEN
        assertThat(wantedCar).isEqualTo(null);
    }

    @Test
    void create_a_car(){
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
        Car newCar = carsAction.createCar(allParams);

        // THEN
        assertThat(newCar.getSerialNumber()).isEqualTo(10);
    }

}
