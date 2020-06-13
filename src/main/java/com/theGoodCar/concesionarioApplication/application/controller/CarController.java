package com.theGoodCar.concesionarioApplication.application.controller;

import com.theGoodCar.concesionarioApplication.application.configuration.FilesHTML;
import com.theGoodCar.concesionarioApplication.domain.Car;
import com.theGoodCar.concesionarioApplication.domain.actions.CarsAction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.LinkedList;
import java.util.Map;

import static com.theGoodCar.concesionarioApplication.infrastructure.utils.FunctionsUtils.*;

@Controller
@RequestMapping(value="/cars")
public class CarController {

    @Autowired
    private CarsAction carsAction;

    @GetMapping("/pruebas")
    public String pruebas() {
        return carsAction.holaaa();
    }

    @GetMapping("/search")
    public String searchCars(Model model) {
        //carsAction.executeSearchCars(model);
        LinkedList<Car> listCars = paginationCars(1, 6);
        int numberPages = calculateNumberPages(6);
        model.addAttribute("listCars", listCars);
        model.addAttribute("numberPages", numberPages);
        model.addAttribute("page", 1);
        return FilesHTML.SEARCH_CARS;
    }

    @GetMapping("/search/page/{page}")
    public String showCarsPagination(@PathVariable("page") String page, Model model) {
        return carsAction.executeSearchCarsNewPage(model, Integer.parseInt(page), 6);
    }

    @GetMapping("/search/see-details/{serialNumber}")
    public String seeDetails(@PathVariable("serialNumber") String serialNumber, Model model) {
        return carsAction.executeSeeCarDetails(serialNumber, model);
    }

    @GetMapping("/buyCar/{serialNumber}")
    public String formBuyCar(@PathVariable("serialNumber") String serialNumber, Model model) {
        return carsAction.executeFormBuyCar(serialNumber, model);
    }

    @PostMapping("/buyCar/{serialNumber}/buy")
    public ModelAndView buyCar(@RequestParam("name") String name, @RequestParam("lastName") String lastName, @RequestParam("dni") String dni, @RequestParam("agreeTerms") String agreeTerms, @RequestParam("serialNumber") String serialNumber) {
        return carsAction.executeBuyCar(name, lastName, dni, agreeTerms, serialNumber);
    }

    @GetMapping("/sellCar")
    public String formSellCar(Model model) {
        return carsAction.executeFormSellCar(model);
    }

    @PostMapping("/sellCar/sell")
    public ModelAndView sellCar(@RequestParam Map<String, String> allParams) {
        return carsAction.executeSellCar(allParams);
    }
}
