package com.theGoodCar.concesionarioApplication.api;

import com.theGoodCar.concesionarioApplication.application.configuration.FilesHTML;
import com.theGoodCar.concesionarioApplication.application.controller.CarController;
import com.theGoodCar.concesionarioApplication.domain.Car;
import com.theGoodCar.concesionarioApplication.domain.actions.CarsAction;
import com.theGoodCar.concesionarioApplication.infrastructure.utils.ValidateBuyCar;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.ui.Model;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import java.util.Collections;
import java.util.LinkedList;
import java.util.Map;
import java.util.TreeMap;

import static com.theGoodCar.concesionarioApplication.infrastructure.utils.FunctionsUtils.calculateNumberPages;
import static com.theGoodCar.concesionarioApplication.infrastructure.utils.FunctionsUtils.paginationCars;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.InstanceOfAssertFactories.list;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(CarController.class)
@AutoConfigureMockMvc
public class CarControllerShould {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private CarsAction carsAction;

    private Model model;

    public Car testCar() {
        LinkedList<String> interiorFeatures = new LinkedList<>();
        LinkedList<String> exteriorFeatures = new LinkedList<>();
        LinkedList<String> safetyFeatures = new LinkedList<>();

        interiorFeatures.add("test");
        exteriorFeatures.add("test");
        safetyFeatures.add("test");

        Car car = Car.builder()
                .serialNumber(1)
                .brand("Audi")
                .bodyType("Sedan")
                .model("A8 L")
                .yearManufacturer(2015)
                .carDetails("test")
                .interiorFeatures(interiorFeatures)
                .exteriorFeatures(exteriorFeatures)
                .safetyFeatures(safetyFeatures)
                .othersFeatures("")
                .color("Black")
                .transmision("Manual")
                .description("")
                .price("$37.700,00")
                .image("audiA8.jpg")
                .build();

        return car;
    }

    @Test
    void search_car_endpoint_should_return_200() throws Exception {
        LinkedList<Car> listCars = paginationCars(1, 6);
        int numberPages = calculateNumberPages(6);

        when(carsAction.createPaginationCars(1, 6)).thenReturn(listCars);
        when(carsAction.calculatePages(6)).thenReturn(numberPages);

        mvc.perform(get("/cars/search")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    void search_car_other_page_endpoint_should_return_200() throws Exception {
        LinkedList<Car> listCars = paginationCars(2, 6);
        int numberPages = calculateNumberPages(6);

        when(carsAction.createPaginationCars(2, 6)).thenReturn(listCars);
        when(carsAction.calculatePages(6)).thenReturn(numberPages);

        mvc.perform(get("/cars/search/page/2")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    void see_details_car_endpoint_should_return_200() throws Exception {
        //GIVEN
        Car car = testCar();

        //WHEN
        when(carsAction.findCarBySerialNumber("1")).thenReturn(car);

        //THEN
        mvc.perform(get("/cars/search/see-details/1")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    void see_form_to_buy_car_endpoint_should_return_200() throws Exception {
        //GIVEN
        Car car = testCar();

        //WHEN
        when(carsAction.findCarBySerialNumber("1")).thenReturn(car);

        //THEN
        mvc.perform(get("/cars/buyCar/1")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    void to_buy_car_good_validation_endpoint_should_return_200() throws Exception {
        //GIVEN
        Car car = testCar();

        //WHEN
        when(carsAction.findCarBySerialNumber("1")).thenReturn(car);

        //THEN
        mvc.perform(post("/cars/buyCar/1/buy")
                .param("name", "foo")
                .param("lastName", "foo")
                .param("dni", "00000000X")
                .param("agreeTerms", "agree")
                .param("serialNumber", "1")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    // Probar los casos infructuosos
    // En otro fichero de test, probar ese if.

    @Test
    void see_form_sell_car_endpoint_should_return_200() throws Exception {
        mvc.perform(get("/cars/sellCar")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    // Acabar este test... falta los when
    @Test
    void to_sell_car_endpoint_should_return_200() throws Exception {
        //GIVEN
        Car car = testCar();
        LinkedList<Car> listCars = paginationCars(2, 6);
        int numberPages = calculateNumberPages(6);
        MultiValueMap<String, String> allParams = new LinkedMultiValueMap<>();
        //Map<String, String> allParams = new TreeMap<>();
        allParams.put("brand", Collections.singletonList("foo"));
        allParams.put("bodyType", Collections.singletonList("foo"));
        allParams.put("model", Collections.singletonList("foo"));
        allParams.put("yearManufacturer", Collections.singletonList("2000"));
        allParams.put("carDetails", Collections.singletonList("foo"));
        allParams.put("otherFeatures", Collections.singletonList("foo"));
        allParams.put("color", Collections.singletonList("foo"));
        allParams.put("transmission", Collections.singletonList("foo"));
        allParams.put("description", Collections.singletonList("foo"));
        allParams.put("price", Collections.singletonList("2000"));

        //TAMBIÉN PUEDO HACERLO LAS FUNCIONES, NO EL TEST, CON MultiValueMap, incluso la entrada de parámetros... aunque no olvidar el singletonList y el toSingleValueMap() para hacerlo Map

        //WHEN
        when(carsAction.createPaginationCars(1, 6)).thenReturn(listCars);
        when(carsAction.calculatePages(6)).thenReturn(numberPages);
        when(carsAction.createCar(allParams.toSingleValueMap())).thenReturn(car);

        //THEN
        mvc.perform(post("/cars/sellCar/sell")
                .params(allParams)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }


}
