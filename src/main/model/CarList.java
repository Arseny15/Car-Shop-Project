package model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class CarList {
    private List<CarSettings> cars;
    private String name;


    // EFFECTS: construct a list of cars, no repeat cars
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

    public int getNumOfCars() {
        return this.cars.size();  // combination of getCars().size()
    }

    // EFFECTS: return list of all cars with same carBrand
    public CarList sameCarBrand(String carBrand) {
        CarList carsWithSameBrand = new CarList("car");
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
        CarList carsWithSameColo = new CarList("car");
        for (CarSettings colorCar : this.cars) {
            if (color.equals(colorCar.getColor())) {
                carsWithSameColo.addCarToList(colorCar);
            }
        }
        return carsWithSameColo;
    }


    // EFFECTS: return list of all cars with given car year
    public CarList carsWithGivenYear(int year) {
        CarList carsSameYear = new CarList("car");
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
        CarList carsUnderMaxKm = new CarList("car");
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
        CarList underCarPrice = new CarList("car");
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

//
//    // MODIFIES: this
//    // EFFECTS: Method to sort the list by kilometers used, with original order stays same
//    public void sortCarsByKmUsed() {
//        Comparator<CarSettings> kmUsedComparator = Comparator.comparingInt(CarSettings::getKmUsed);
//        Collections.sort(cars, kmUsedComparator.thenComparing(Comparator.naturalOrder()));
//    }
}


