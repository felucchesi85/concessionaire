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

    //Funciona bien si le quitas los GIVEN y los WHEN.
    @Test
    void pruebas_api() throws Exception {
        //when(carsAction.servicioPrueba()).thenReturn("Hello, Mock");
        LinkedList<Car> listCars = paginationCars(1, 6);
        int numberPages = calculateNumberPages(6);

        /*when(carsAction.servicioPrueba(model)).thenReturn(
                model.addAttribute("listCars", listCars)
                        .addAttribute("numberPages", numberPages)
                        .addAttribute("page", 1));*/
        when(carsAction.servicioPrueba1()).thenReturn(listCars);
        when(carsAction.servicioPrueba2()).thenReturn(numberPages);
        //when(carsAction.servicioPrueba(model, listCars, numberPages)).thenCa
        mvc.perform(get("/cars/pruebas")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    /*@Test
    void pruebas_api() throws Exception {
        //when(carsAction.servicioPrueba()).thenReturn("Hello, Mock");
        LinkedList<Car> listCars = paginationCars(1, 6);
        int numberPages = calculateNumberPages(6);
        //when(carsAction.servicioPrueba(model, listCars, numberPages)).thenReturn(model.addAttribute("page", 1));
        when(carsAction.servicioPrueba()).thenReturn(listCars);
        //when(carsAction.servicioPrueba(model, listCars, numberPages)).thenCa
        mvc.perform(get("/cars/pruebas")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }*/

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

        // De aqui abajo funciona

        mvc.perform(get("/cars/search")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        /*assertThat(carsAction.executeSearchCars(model)).isNull();*/

        /*mvc.perform(get("/cars/pruebas")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());*/
    }
}
