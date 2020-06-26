package com.theGoodCar.concesionarioApplication.application.controller.modelFunctions;

import com.theGoodCar.concesionarioApplication.domain.Car;
import org.springframework.ui.Model;
import org.springframework.web.servlet.ModelAndView;

import java.util.LinkedList;

public class InsertDataModel {

    public static void intertSearchCarsModel(Model model, LinkedList<Car> listCars, int numberPages) {
        model.addAttribute("listCars", listCars);
        model.addAttribute("numberPages", numberPages);
        model.addAttribute("page", 1);
    }

    public static void insertSearchCarsNewPageModel(Model model, LinkedList<Car> listCars, int numberPages, int page) {
        model.addAttribute("view", false);
        model.addAttribute("listCars", listCars);
        model.addAttribute("numberPages", numberPages);
        model.addAttribute("page", page);
    }

    public static void insertCarSeeDetailsModel(Model model, Car car) {
        model.addAttribute("car", car);
    }

    public static void insertCarDetailsModel(ModelAndView modelandView, Car car, boolean showErrorMessage) {
        modelandView.addObject("car", car);
        modelandView.addObject("error", showErrorMessage);
    }

    public static void insertDataDetailsModel(ModelAndView modelAndView, String name, String lastName, String dni, Car car) {
        modelAndView.addObject("name", name);
        modelAndView.addObject("lastName", lastName);
        modelAndView.addObject("dni", dni);
        modelAndView.addObject("car", car);
    }

    public static void insertErrorMessageModel(ModelAndView modelAndView, boolean errorMessage) {
        modelAndView.addObject("error", errorMessage);
    }

    public static void insertCarInSearchCars(ModelAndView modelAndView, Car newCar, LinkedList<Car> listCars, int numberPages) {
        modelAndView.addObject("view", true);
        modelAndView.addObject("newCar", newCar);
        modelAndView.addObject("listCars", listCars);
        modelAndView.addObject("numberPages", numberPages);
        modelAndView.addObject("page", 1);
    }
}
