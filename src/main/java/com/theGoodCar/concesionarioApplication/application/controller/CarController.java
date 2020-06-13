package com.theGoodCar.concesionarioApplication.application.controller;

import com.theGoodCar.concesionarioApplication.application.configuration.FilesHTML;
import com.theGoodCar.concesionarioApplication.domain.Car;
import com.theGoodCar.concesionarioApplication.infrastructure.utils.ValidateBuyCar;
import com.theGoodCar.concesionarioApplication.infrastructure.utils.ValidateSellCar;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

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
        model.addAttribute("error", false);
        return FilesHTML.BUY_CAR;
    }

    @PostMapping("/buyCar/{serialNumber}/buy")
    public ModelAndView buyCar(@RequestParam("name") String name, @RequestParam("lastName") String lastName, @RequestParam("dni") String dni, @RequestParam("agreeTerms") String agreeTerms, @RequestParam("serialNumber") String serialNumber, Model model) {
        Car car = findCarBySerialNumber(serialNumber);
        if (ValidateBuyCar.validationBuy(name, lastName, dni, agreeTerms)) {
            ModelAndView modelAndView = new ModelAndView(FilesHTML.TICKET_BUY_CAR);
            modelAndView.addObject("name", name);
            modelAndView.addObject("lastName", name);
            modelAndView.addObject("dni", name);
            modelAndView.addObject("car", car);
            return modelAndView;
        } else {
            ModelAndView modelAndView = new ModelAndView(FilesHTML.BUY_CAR);
            modelAndView.addObject("car", car);
            modelAndView.addObject("error", true);
            return modelAndView;
        }
    }

    @GetMapping("/sellCar")
    public String sellCarForm(Model model) {
        model.addAttribute("error", false);
        return FilesHTML.SELL_CAR;
    }

    @PostMapping("/sellCar/sell")
    public ModelAndView sellCar(@RequestParam Map<String, String> allParams) {
        if (ValidateSellCar.validationSell(allParams)) {
            ModelAndView modelAndView = new ModelAndView(FilesHTML.SEARCH_CARS);
            LinkedList<Car> listCars = paginationCars(1, 6);
            listCars.add(createCar(allParams));
            int numberPages = calculateNumberPages(6);
            modelAndView.addObject("listCars", listCars);
            modelAndView.addObject("numberPages", numberPages);
            modelAndView.addObject("page", 1);
            return modelAndView;
        } else {
            System.out.println("ALGO FUE MAL");
            ModelAndView modelAndView = new ModelAndView(FilesHTML.SELL_CAR);
            modelAndView.addObject("error", true);
            return modelAndView;
        }
    }
}
