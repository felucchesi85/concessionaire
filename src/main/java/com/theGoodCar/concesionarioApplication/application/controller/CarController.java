package com.theGoodCar.concesionarioApplication.application.controller;

import com.theGoodCar.concesionarioApplication.application.configuration.FilesHTML;
import com.theGoodCar.concesionarioApplication.domain.Car;
import com.theGoodCar.concesionarioApplication.domain.actions.CarsAction;
import com.theGoodCar.concesionarioApplication.infrastructure.utils.ValidateBuyCar;
import com.theGoodCar.concesionarioApplication.infrastructure.utils.ValidateSellCar;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.LinkedList;
import java.util.Map;

import static com.theGoodCar.concesionarioApplication.application.controller.modelFunctions.InsertDataModel.*;

@Controller
@RequestMapping(value="/cars")
public class CarController {

    @Autowired
    private CarsAction carsAction;

    @GetMapping("/search")
    public String searchCars(Model model) {
        LinkedList<Car> listCars = carsAction.createPaginationCars(1, 6);
        int numberPages = carsAction.calculatePages(6);
        intertSearchCarsModel(model, listCars, numberPages);
        return FilesHTML.SEARCH_CARS;
    }

    @GetMapping("/search/page/{page}")
    public String showCarsPagination(@PathVariable("page") String page, Model model) {
        LinkedList<Car> listCars = carsAction.createPaginationCars(Integer.parseInt(page), 6);
        int numberPages = carsAction.calculatePages(6);
        insertSearchCarsNewPageModel(model, listCars, numberPages, Integer.parseInt(page));
        return FilesHTML.SEARCH_CARS;
    }

    @GetMapping("/search/see-details/{serialNumber}")
    public String seeDetails(@PathVariable("serialNumber") String serialNumber, Model model) {
        Car car = carsAction.findCarBySerialNumber(serialNumber);
        insertCarSeeDetailsModel(model, car);
        return FilesHTML.SEE_DETAILS;
    }

    @GetMapping("/buyCar/{serialNumber}")
    public ModelAndView formBuyCar(@PathVariable("serialNumber") String serialNumber, Model model) {
        Car car = carsAction.findCarBySerialNumber(serialNumber);
        ModelAndView modelAndView = new ModelAndView(FilesHTML.BUY_CAR);
        insertCarDetailsModel(modelAndView, car, false);
        return modelAndView;
    }

    @PostMapping("/buyCar/{serialNumber}/buy")
    public ModelAndView buyCar(@RequestParam("name") String name, @RequestParam("lastName") String lastName, @RequestParam("dni") String dni, @RequestParam("agreeTerms") String agreeTerms, @RequestParam("serialNumber") String serialNumber) {
        Car car = carsAction.findCarBySerialNumber(serialNumber);
        ModelAndView modelAndView = new ModelAndView();
        if (ValidateBuyCar.validationBuy(name, lastName, dni, agreeTerms)) {
            modelAndView.setViewName(FilesHTML.TICKET_BUY_CAR);
            insertDataDetailsModel(modelAndView, name, lastName, dni, car);
        } else {
            modelAndView.setViewName(FilesHTML.BUY_CAR);
            insertCarDetailsModel(modelAndView, car, true);
        }
        return modelAndView;
    }

    @GetMapping("/sellCar")
    public ModelAndView formSellCar() {
        ModelAndView modelAndView = new ModelAndView(FilesHTML.SELL_CAR);
        insertErrorMessageModel(modelAndView, false);
        return modelAndView;
    }

    @PostMapping("/sellCar/sell")
    public ModelAndView sellCar(@RequestParam Map<String, String> allParams) {
        ModelAndView modelAndView = new ModelAndView();
        if (ValidateSellCar.validationSell(allParams)) {
            LinkedList<Car> listCars = carsAction.createPaginationCars(1, 6);
            int numberPages = carsAction.calculatePages(6);
            Car newCar = carsAction.createCar(allParams);
            modelAndView.setViewName(FilesHTML.SEARCH_CARS);
            insertCarInSearchCars(modelAndView, newCar, listCars, numberPages);
        } else {
            modelAndView.setViewName(FilesHTML.SELL_CAR);
            insertErrorMessageModel(modelAndView, true);
        }
        return modelAndView;
    }
}
