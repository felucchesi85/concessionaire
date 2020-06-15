package com.theGoodCar.concesionarioApplication.domain.actions;

import com.theGoodCar.concesionarioApplication.application.configuration.FilesHTML;
import com.theGoodCar.concesionarioApplication.domain.Car;
import com.theGoodCar.concesionarioApplication.infrastructure.utils.ValidateBuyCar;
import com.theGoodCar.concesionarioApplication.infrastructure.utils.ValidateSellCar;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.servlet.ModelAndView;

import java.util.LinkedList;
import java.util.Map;

import static com.theGoodCar.concesionarioApplication.infrastructure.utils.FunctionsUtils.*;

@Service
@NoArgsConstructor
public class CarsAction {

    public LinkedList<Car> createPaginationCars(int numPagination, int numCards) {
        LinkedList<Car> listCars = createDefaultCars();
        LinkedList<Car> carsPagination = new LinkedList<>();

        for (int i = (numPagination-1)*numCards+1; i <= numPagination*numCards; i++) {
            if (i <= listCars.size()) {
                carsPagination.add(listCars.get(i-1));
            }
        }
        return carsPagination;
    }

    public int calculatePages(int numPagination) {
        LinkedList<Car> listCars = createDefaultCars();
        int numberCars = listCars.size();
        if (numberCars%numPagination == 0) {
            return numberCars/numPagination;
        } else {
            return numberCars/numPagination + 1;
        }
    }

    public Car findCarBySerialNumber(String serialNumber) {
        LinkedList<Car> listCars = createDefaultCars();

        for (Car car: listCars) {
            if (car.getSerialNumber() == Integer.parseInt(serialNumber)) {
                return car;
            }
        }
        return null;
    }

    public static Car createCar(Map<String, String> allParams) {
        int newSerialNumber = createDefaultCars().getLast().getSerialNumber() + 1;
        LinkedList<String> interiorFeatures = interiorFeatures();
        LinkedList<String> exteriorFeatures = exteriorFeatures();
        LinkedList<String> safetyFeatures = safetyFeatures();

        Car car = Car.builder()
                .serialNumber(newSerialNumber)
                .brand(allParams.get("brand"))
                .bodyType(allParams.get("bodyType"))
                .model(allParams.get("model"))
                .yearManufacturer(Integer.parseInt(allParams.get("yearManufacturer")))
                .carDetails(allParams.get("carDetails"))
                .interiorFeatures(interiorFeatures)
                .exteriorFeatures(exteriorFeatures)
                .safetyFeatures(safetyFeatures)
                .othersFeatures(allParams.get("otherFeatures"))
                .color(allParams.get("color"))
                .transmision(allParams.get("transmission"))
                .description(allParams.get("description"))
                .price(allParams.get("price"))
                .image("audiA8.jpg")
                .build();

        return car;
    }
}
