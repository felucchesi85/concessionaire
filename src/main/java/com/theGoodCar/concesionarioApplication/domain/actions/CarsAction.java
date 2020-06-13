package com.theGoodCar.concesionarioApplication.domain.actions;

import com.theGoodCar.concesionarioApplication.application.configuration.FilesHTML;
import com.theGoodCar.concesionarioApplication.domain.Car;
import com.theGoodCar.concesionarioApplication.infrastructure.utils.ValidateBuyCar;
import com.theGoodCar.concesionarioApplication.infrastructure.utils.ValidateSellCar;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.servlet.ModelAndView;

import java.util.LinkedList;
import java.util.Map;

import static com.theGoodCar.concesionarioApplication.infrastructure.utils.FunctionsUtils.*;

@Service
public class CarsAction {

    //private Car findCar

    public String executeSearchCars(Model model) {
        LinkedList<Car> listCars = paginationCars(1, 6);
        int numberPages = calculateNumberPages(6);
        model.addAttribute("listCars", listCars);
        model.addAttribute("numberPages", numberPages);
        model.addAttribute("page", 1);
        return FilesHTML.SEARCH_CARS;
    }


    public String executeSearchCarsNewPage(Model model, int page, int numberCards) {
        LinkedList<Car> listCars = paginationCars(page, numberCards);
        int numberPages = calculateNumberPages(numberCards);
        model.addAttribute("view", false);
        model.addAttribute("listCars", listCars);
        model.addAttribute("numberPages", numberPages);
        model.addAttribute("page", page);
        return FilesHTML.SEARCH_CARS;
    }

    public String executeSeeCarDetails(String serialNumber, Model model) {
        Car car = findCarBySerialNumber(serialNumber);
        model.addAttribute("car", car);
        return FilesHTML.SEE_DETAILS;
    }

    public String executeFormBuyCar(String serialNumber, Model model) {
        Car car = findCarBySerialNumber(serialNumber);
        model.addAttribute("car", car);
        model.addAttribute("error", false);
        return FilesHTML.BUY_CAR;
    }

    public ModelAndView executeBuyCar(String name, String lastName, String dni, String agreeTerms, String serialNumber) {
        Car car = findCarBySerialNumber(serialNumber);
        if (ValidateBuyCar.validationBuy(name, lastName, dni, agreeTerms)) {
            return viewTicketBuyCar(name, lastName, dni, car);
        } else {
            return viewBuyCarErrorMessage(car, true);
        }
    }

    private ModelAndView viewBuyCarErrorMessage(Car car, boolean errorMessage) {
        ModelAndView modelAndView = new ModelAndView(FilesHTML.BUY_CAR);
        modelAndView.addObject("car", car);
        modelAndView.addObject("error", true);
        return modelAndView;
    }

    private ModelAndView viewTicketBuyCar(String name, String lastName, String dni, Car car) {
        ModelAndView modelAndView = new ModelAndView(FilesHTML.TICKET_BUY_CAR);
        modelAndView.addObject("name", name);
        modelAndView.addObject("lastName", lastName);
        modelAndView.addObject("dni", dni);
        modelAndView.addObject("car", car);
        return modelAndView;
    }

    public String executeFormSellCar(Model model) {
        model.addAttribute("error", false);
        return FilesHTML.SELL_CAR;
    }

    public ModelAndView executeSellCar(Map<String, String> allParams) {
        if (ValidateSellCar.validationSell(allParams)) {
            LinkedList<Car> listCars = paginationCars(1, 6);
            Car newCar = createCar(allParams);
            return viewSearchCar(listCars, newCar);
        } else {
            ModelAndView modelAndView = new ModelAndView(FilesHTML.SELL_CAR);
            modelAndView.addObject("error", true);
            return modelAndView;
        }
    }

    private ModelAndView viewSearchCar(LinkedList<Car> listCars, Car newCar) {
        ModelAndView modelAndView = new ModelAndView(FilesHTML.SEARCH_CARS);
        int numberPages = calculateNumberPages(6);
        modelAndView.addObject("view", true);
        modelAndView.addObject("newCar", newCar);
        modelAndView.addObject("listCars", listCars);
        modelAndView.addObject("numberPages", numberPages);
        modelAndView.addObject("page", 1);
        return modelAndView;
    }
}
