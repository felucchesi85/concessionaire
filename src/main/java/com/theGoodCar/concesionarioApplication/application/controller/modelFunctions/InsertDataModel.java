package com.theGoodCar.concesionarioApplication.application.controller.modelFunctions;

import com.theGoodCar.concesionarioApplication.domain.Car;
import org.springframework.ui.Model;

import java.util.LinkedList;

public class InsertDataModel {

    public static void foo(Model model, LinkedList<Car> listCars, int numberPages) {
        model.addAttribute("listCars", listCars);
        model.addAttribute("numberPages", numberPages);
        model.addAttribute("page", 2);
    }
}
