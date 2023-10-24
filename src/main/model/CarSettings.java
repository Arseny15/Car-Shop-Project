package model;

import org.json.JSONObject;
import persistence.Writable;

// Car setting class creates a car with it is car Setting.
// Such as: car brand, price, color, car year, km used.
public class CarSettings implements Writable {
    private String carBrand;
    private int price;
    private String color;
    private int carYear;
    private int kmUsed;

    //EFFECTS: construct a car with settings (carBrand, color, carYear, price, kilometers)
    public CarSettings(String carBrand, int price, String color, int carYear, int kmUsed) {
        this.carBrand = carBrand;
        this.price = price;
        this.color = color;
        this.carYear = carYear;
        this.kmUsed = kmUsed;
    }

    public String getCarBrand() {
        return this.carBrand;
    }

    public int getPrice() {
        return this.price;
    }

    public String getColor() {
        return this.color;
    }

    public int getCarYear() {
        return this.carYear;
    }

    public int getKmUsed() {
        return this.kmUsed;
    }

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("carBrand", carBrand);
        json.put("price", price);
        json.put("color", color);
        json.put("carYear", carYear);
        json.put("mileage", kmUsed);
        return json;
    }
}



