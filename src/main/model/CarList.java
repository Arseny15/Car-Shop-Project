package model;


import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;


import java.util.ArrayList;
import java.util.List;

// CarList class represents the list of cars for user and seller. It allows for both of them different functionality.
// User can sort by: car brand, price, color, car year, km used. Also, user can see all options presented.
// Car seller can: add cars for sale using Car settings, and he can remove their posts.
public class CarList implements Writable {
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
            EventLog provider = EventLog.getInstance();
            provider.logEvent(new Event("A car -> " + car.getCarBrand() + ", " + car.getPrice()
                    + " car price, " + car.getColor() + ", "
                    + car.getCarYear() + " car year, " + car.getKmUsed() + " mileage, was added."));
        }
    }

    // EFFECTS: find the same car in the list
    public CarSettings findSpecCar(String carBrand, int price, String color, int carYear, int kmUsed) {
        for (CarSettings car : cars) {
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

    // EFFECTS: checking if the car with specific settings exists
    public boolean carExists(String carBrand, int price, String color, int year, int mileage) {
        for (CarSettings car : cars) {
            if (car.getCarBrand().equals(carBrand) && car.getPrice() == price && car.getColor().equals(color)
                    && car.getCarYear() == year && car.getKmUsed() == mileage) {
                return true;
            }
        }
        return false;
    }

    // MODIFIES: this
    // EFFECTS: remove cars from the list of cars
    public void removeCarFromList(String carBrand, int price, String color, int carYear, int kmUsed) {
        CarSettings specCar = findSpecCar(carBrand, price, color, carYear, kmUsed);
        this.cars.remove(specCar);
        EventLog provider = EventLog.getInstance();
        provider.logEvent(new Event("A car -> " + carBrand + ", " + price
                + " car price, " + color + ", "
                + carYear + " car year, " + kmUsed + " mileage, was removed."));
    }

    //EFFECTS: return a size of the list = return how many cars in the list
    public int getNumOfCars() {
        return this.cars.size();  // combination of getCars().size()
    }

    // EFFECTS: return list of all cars with same carBrand
    public CarList sameCarBrand(String carBrand) {
        CarList carsWithSameBrand = new CarList();
        for (CarSettings car : cars) {
            if (carBrand.equals(car.getCarBrand())) {
                carsWithSameBrand.addCarToList(car);
            }
        }
        EventLog provider = EventLog.getInstance();
        provider.logEvent(new Event("Cars were filtered by the same car brand -> " + carBrand
                + " were found."));
        return carsWithSameBrand;
    }


    // EFFECTS: return list of all cars with same carColor
    public CarList sameCarColor(String color) {
        CarList carsWithSameColo = new CarList();
        for (CarSettings colorCar : cars) {
            if (color.equals(colorCar.getColor())) {
                carsWithSameColo.addCarToList(colorCar);
            }
        }
        EventLog provider = EventLog.getInstance();
        provider.logEvent(new Event("Cars were filtered by the same color of the car -> "
                + color + " were found."));
        return carsWithSameColo;
    }


    // EFFECTS: return list of all cars with given car year
    public CarList carsWithGivenYear(int year) {
        CarList carsSameYear = new CarList();
        for (CarSettings car : cars) {
            if (car.getCarYear() == year) {
                carsSameYear.addCarToList(car);
            }
        }
        EventLog provider = EventLog.getInstance();
        provider.logEvent(new Event("Cars were filtered by the same year of the car -> " + year
                + " this year car was found."));
        return carsSameYear;
    }


    // EFFECTS: return list of all cars under or equal given km used
    public CarList underKmUse(int km) {
        CarList carsUnderMaxKm = new CarList();
        for (CarSettings car : cars) {
            if (car.getKmUsed() <= km) {
                carsUnderMaxKm.addCarToList(car);
            }
        }
        EventLog provider = EventLog.getInstance();
        provider.logEvent(new Event("Cars were filtered by mileage -> " + km
                + " a car with such mileage was found."));
        return carsUnderMaxKm;
    }


    // EFFECTS: return list of all cars under and equal given price
    public CarList underCarPrice(int carPrice) {
        CarList underCarPrice = new CarList();
        for (CarSettings car : cars) {
            if (car.getPrice() <= carPrice) {
                underCarPrice.addCarToList(car);
            }
        }
        EventLog provider = EventLog.getInstance();
        provider.logEvent(new Event("Cars were filtered by under and equal the price of the car -> "
                + carPrice + " under or equal such price cars were found"));
        return underCarPrice;
    }

    // EFFECTS: return list of all cars equal given price
    public CarList sameCarPrice(int carPrice) {
        CarList underCarPrice = new CarList();
        for (CarSettings car : cars) {
            if (car.getPrice() == carPrice) {
                underCarPrice.addCarToList(car);
            }
        }
        EventLog provider = EventLog.getInstance();
        provider.logEvent(new Event("Cars were filtered by the same price of the car -> " + carPrice
                + " equal such price cars were found"));
        return underCarPrice;
    }

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("returns cars", carsToJson());
        return json;
    }

    // EFFECTS: returns car settings in this carList as a JSON array
    private JSONArray carsToJson() {
        JSONArray jsonArray = new JSONArray();
        for (CarSettings t : cars) {
            jsonArray.put(t.toJson());
        }
        return jsonArray;
    }

}




