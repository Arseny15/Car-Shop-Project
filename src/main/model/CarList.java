package model;

import java.util.ArrayList;
import java.util.List;

public class CarList {
    private List<CarSettings> cars;


    // EFFECTS: construct a list of cars, no repeat cars
    public CarList() {
        this.cars = new ArrayList<>();
    }

    public List<CarSettings> getCars() {
        return this.cars;
    }

    // MODIFIES: this
    // EFFECTS: add car to the list, no duplicates
    public void addCarToList(CarSettings car) {
        if (!this.cars.contains(car)) {
            this.cars.add(car);
        }
    }

    // EFFECTS: find the same car in the list
    public CarSettings findSpecCar(String carBrand, int price, String color, int carYear, int kmUsed) {
        for (CarSettings car : this.cars) {
            if (carBrand.equals(car.getCarBrand()) // string
                    &&
                    price == car.getPrice()
                    &&
                    color.equals(car.getColor()) //string
                    &&
                    carYear == car.getCarYear()
                    &&
                    kmUsed == car.getKmUsed()) {
                return car;
            }
        }
        return null;
    }

    // MODIFIES: this
    // EFFECTS: remove car from the list of cars
    public void removeCarFromList(String carBrand, int price, String color, int carYear, int kmUsed) {
        CarSettings specCar = findSpecCar(carBrand, price, color, carYear, kmUsed);
        this.cars.remove(specCar);
    }

    //EFFECTS: return a size of the list = return how many cars in the list
    public int getNumOfCars() {
        return this.cars.size();  // combination of getCars().size()
    }

    // EFFECTS: return list of all cars with same carBrand
    public CarList sameCarBrand(String carBrand) {
        CarList carsWithSameBrand = new CarList();
        for (CarSettings car : this.cars) {
            if (carBrand.equals(car.getCarBrand())) {
                carsWithSameBrand.addCarToList(car);
            }
        }
        return carsWithSameBrand;
    }

//    public CarList sameCarBrand(String carBrand) {
//        List<CarSettings> carsWithSameBrand = new ArrayList<>();
//        for (CarSettings car : this.cars) {
//            if (carBrand.equals(car.getCarBrand())) {
//                carsWithSameBrand.add(car);
//            }
//        }
//        return carsWithSameBrand;
//    }


//    public CarList sameCarColor(String color) {
//        List<CarSettings> carsWithSameColor = new ArrayList<>();
//        for (CarSettings car : this.cars) {
//            if (color.equals(car.getColor())) {
//                carsWithSameColor.add(car);
//            }
//        }
//        return carsWithSameColor;
//    }


    // EFFECTS: return list of all cars with same carColor
    public CarList sameCarColor(String color) {
        CarList carsWithSameColo = new CarList();
        for (CarSettings colorCar : this.cars) {
            if (color.equals(colorCar.getColor())) {
                carsWithSameColo.addCarToList(colorCar);
            }
        }
        return carsWithSameColo;
    }


    // EFFECTS: return list of all cars with given car year
    public CarList carsWithGivenYear(int year) {
        CarList carsSameYear = new CarList();
        for (CarSettings car : this.cars) {
            if (car.getCarYear() == year) {
                carsSameYear.addCarToList(car);
            }
        }
        return carsSameYear;
    }

//    public List<CarSettings> carsWithGivenYear(int year) {
//        List<CarSettings> carsSameYear = new ArrayList<>();
//        for (CarSettings car : this.cars) {
//            if (car.getCarYear() == year) {
//                carsSameYear.add(car);
//            }
//        }
//        return carsSameYear;
//    }


    // EFFECTS: return list of all cars under or equal given km used
    public CarList underKmUse(int km) {
        CarList carsUnderMaxKm = new CarList();
        for (CarSettings car : this.cars) {
            if (car.getKmUsed() <= km) {
                carsUnderMaxKm.addCarToList(car);
            }
        }
        return carsUnderMaxKm;
    }


//    public List<CarSettings> underKmUse(int km) {
//        List<CarSettings> carsUnderMaxKm = new ArrayList<>();
//        for (CarSettings car : this.cars) {
//            if (car.getKmUsed() <= km) {
//                carsUnderMaxKm.add(car);
//            }
//        }
//        return carsUnderMaxKm;
//    }


    // EFFECTS: return list of all cars under or equal given price
    public CarList underCarPrice(int carPrice) {
        CarList underCarPrice = new CarList();
        for (CarSettings car : this.cars) {
            if (car.getPrice() <= carPrice) {
                underCarPrice.addCarToList(car);
            }
        }
        return underCarPrice;
    }



//    public List<CarSettings> underCarPrice(int carPrice) {
//        List<CarSettings> underCarPrice = new ArrayList<>();
//        for (CarSettings car : this.cars) {
//            if (car.getPrice() <= carPrice) {
//                underCarPrice.add(car);
//            }
//        }
//        return underCarPrice;
//    }

}


