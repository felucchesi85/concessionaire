package com.theGoodCar.concesionarioApplication.domain;

import lombok.*;

import java.util.LinkedList;

@Builder
@AllArgsConstructor
@Getter
@Setter
public class Car {

    private int serialNumber;
    private String brand;
    private String bodyType;
    private String model;
    private int yearManufacturer;
    private String carDetails;
    private LinkedList<String> interiorFeatures;
    private LinkedList<String> exteriorFeatures;
    private LinkedList<String> safetyFeatures;
    private String othersFeatures;
    private String color;
    private String transmision;
    private String description;
    private String price;
    private String image;

}
