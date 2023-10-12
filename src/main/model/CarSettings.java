package model;

public class CarSettings {
    private String carBrand;
    private int price;
    private String color;
    private int carYear;
    private int kmUsed;

    //EFFECTS: make a car with settings (carBrand, color, carYear, price, kilometers)
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
}



