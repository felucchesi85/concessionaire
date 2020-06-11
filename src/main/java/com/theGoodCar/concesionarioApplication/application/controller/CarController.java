package com.theGoodCar.concesionarioApplication.application.controller;

import com.theGoodCar.concesionarioApplication.application.configuration.FilesHTML;
import com.theGoodCar.concesionarioApplication.domain.Car;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.LinkedList;

import static com.theGoodCar.concesionarioApplication.infrastructure.utils.FunctionsUtils.*;

@Controller
@RequestMapping(value="/cars")
public class CarController {

    @GetMapping("/search")
    public String searchCars(Model model) {
        LinkedList<Car> listCars = paginationCars(1, 6);
        int numberPages = calculateNumberPages(6);
        model.addAttribute("listCars", listCars);
        model.addAttribute("numberPages", numberPages);
        model.addAttribute("page", 1);
        return FilesHTML.SEARCH_CARS;
    }

    @GetMapping("/search/page/{page}")
    public String showCarsPagination(@PathVariable("page") String page, Model model) {
        LinkedList<Car> listCars = paginationCars(Integer.parseInt(page), 6);
        int numberPages = calculateNumberPages(6);
        model.addAttribute("listCars", listCars);
        model.addAttribute("numberPages", numberPages);
        model.addAttribute("page", Integer.parseInt(page));
        return FilesHTML.SEARCH_CARS;
    }

    @GetMapping("/search/see-details/{serialNumber}")
    public String seeDetails(@PathVariable("serialNumber") String serialNumber, Model model) {
        Car car = findCarBySerialNumber(serialNumber);
        model.addAttribute("car", car);
        return FilesHTML.SEE_DETAILS;
    }

    @GetMapping("/buyCar/{serialNumber}")
    public String buyFormCar(@PathVariable("serialNumber") String serialNumber, Model model) {
        Car car = findCarBySerialNumber(serialNumber);
        model.addAttribute("car", car);
        return FilesHTML.BUY_CAR;
    }

    @PostMapping("/buyCar/{serialNumber}/buy")
    public String buyCar(Model model) {

        // Quiero que me retorne, o bien al formulario si
        // algo no está bien, o bien un mensaje de éxito (y luego me envie al menú principal).
        return FilesHTML.INDEX;
    }

    @GetMapping("/sellCar")
    public String sellCar(Model model) {
        return FilesHTML.SELL_CAR;
    }

    // Método POST de vender coche de que se ha vendido, o bien recargar el formulario
}
