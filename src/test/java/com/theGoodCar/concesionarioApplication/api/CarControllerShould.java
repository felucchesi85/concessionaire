package com.theGoodCar.concesionarioApplication.api;

import com.theGoodCar.concesionarioApplication.application.configuration.FilesHTML;
import com.theGoodCar.concesionarioApplication.application.controller.CarController;
import com.theGoodCar.concesionarioApplication.domain.Car;
import com.theGoodCar.concesionarioApplication.domain.actions.CarsAction;
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

import java.util.LinkedList;

import static com.theGoodCar.concesionarioApplication.infrastructure.utils.FunctionsUtils.calculateNumberPages;
import static com.theGoodCar.concesionarioApplication.infrastructure.utils.FunctionsUtils.paginationCars;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.InstanceOfAssertFactories.list;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
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
        LinkedList<Car> listCars = paginationCars(1, 6);
        int numberPages = calculateNumberPages(6);

        when(carsAction.createPaginationCars(1, 6)).thenReturn(listCars);
        when(carsAction.calculatePages(6)).thenReturn(numberPages);

        mvc.perform(get("/cars/search")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
}
