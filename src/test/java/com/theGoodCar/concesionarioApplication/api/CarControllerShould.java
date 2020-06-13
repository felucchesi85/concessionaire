package com.theGoodCar.concesionarioApplication.api;

import com.theGoodCar.concesionarioApplication.application.configuration.FilesHTML;
import com.theGoodCar.concesionarioApplication.application.controller.CarController;
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

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(CarController.class)
@AutoConfigureMockMvc
public class CarControllerShould {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private CarController controller;

    @MockBean
    private CarsAction carsAction;

    private Model model;


    // Le mola al test que no vaya a la función servicios las cosas... excepto si está vacio el método, ahi genial
    @Test
    void search_car_endpoint_should_return_200() throws Exception {
        //String body = objectMapper.writeValueAsString()

        /*mvc.perform(get("/cars/search")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());*/

        /*mvc.perform(get("/search"))
            .andExpect(status().is(HttpStatus.OK.value()));*/

        //when(carsAction.executeSearchCars(model)).thenReturn(FilesHTML.INDEX);

        /*mvc.perform(get("/cars/search")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());*/
        //assertThat(carsAction.executeSearchCars(model)).isNull();

        //System.out.println(carsAction.executeSearchCars(this.model));

        mvc.perform(get("/cars/search")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        assertThat(carsAction.executeSearchCars(model)).isNull();

        /*mvc.perform(get("/cars/pruebas")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());*/
    }
}
