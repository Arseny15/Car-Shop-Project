package model;

import java.util.ArrayList;
import java.util.List;

public class CarList {
    private List<CarSettings> cars;
    private String name;

    //EFFECTS: construct a list of cars, no repeat cars
    public CarList(String name) {
        this.cars = new ArrayList<>();
        this.name = name;
    }

    public String getCarBrand() {
        return this.name;
    }

    public List<CarSettings> getCars() {
        return this.cars;
    }

    public CarSettings findSpecCar(String carBrand, int price, int color, int carYear, int kilometers) {

    }
}


