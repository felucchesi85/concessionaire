package com.theGoodCar.concesionarioApplication.api;

import com.theGoodCar.concesionarioApplication.application.controller.HomeController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(HomeController.class)
public class HomeControllerShould {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private HomeController controller;

    @Test
    void home_endpoint_should_return_200() throws Exception {

        mvc.perform(get("/home")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
}
