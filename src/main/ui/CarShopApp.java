package ui;

import model.CarList;
import model.CarSettings;

import java.util.Scanner;
// add class level comments
public class CarShopApp {
    private Scanner input;
    private CarSettings car1;
    private CarSettings car2;
    private CarSettings car3;
    private CarSettings car4;
    private CarList carList;
    private String info;
    private int num;


    // EFFECTS: runs the car shop application
    public CarShopApp() {
        runCarShop();
    }

    // MODIFIES: this
    // EFFECTS: initializes car settings
    private void init() {
        this.car1 = new CarSettings("Lada copeyka", 1000, "blue", 2000, 10000);
        this.car2 = new CarSettings("Koenigsegg", 4000000, "carbon", 2023, 0);
        this.car3 = new CarSettings("BMW", 120000, "race green", 2000, 100);
        this.car4 = new CarSettings("Porsche 911 GT3 RS", 300000,
                "blue", 2022, 4000);
        input = new Scanner(System.in);
        carList = new CarList();
        input.useDelimiter("\n");
        carList.addCarToList(car1);
        carList.addCarToList(car2);
        carList.addCarToList(car3);
        carList.addCarToList(car4);
    }


    // EFFECTS: displays menu: to choose: you are user or seller
    private void displayMenu() {
        System.out.println("\nSelect from:");
        System.out.println("\tu -> users");
        System.out.println("\ts -> sellers");
        System.out.println("\tq -> quit");
    }

    // EFFECTS: displays menu of options to user
    private void displayMenuUser() {
        System.out.println("\nSelect from:");
        System.out.println("\tb -> Find a car by car brand");
        System.out.println("\tp -> Find a car by price");
        System.out.println("\tc -> Find a car by color");
        System.out.println("\ty -> Find a car by car year");
        System.out.println("\tk -> Find a car by mileage");
        System.out.println("\ts -> Show list of all cars");
        System.out.println("\tq -> quit");
    }


    // EFFECTS: displays menu of options to sellers
    private void displayMenuSellers() {
        System.out.println("\nSelect from:");
        System.out.println("\ta -> Add cars for sale");
        System.out.println("\tr -> Remove cars available for sale");
        System.out.println("\ts -> Show list of all cars");
        System.out.println("\tq -> quit");
    }

    // MODIFIES: this
    // EFFECTS: processes user input
    private void runCarShop() {
        boolean keepGoing = true;
        String command = null;

        init();

        while (keepGoing) {
            displayMenu();
            command = input.next();
            command = command.toLowerCase();
            if (command.equals("q")) {
                keepGoing = false;
            } else if (command.equals("u")) {
                displayMenuUser();
                processCommandUser();
            } else if (command.equals("s")) {
                displayMenuSellers();
                processCommandSeller();
            } else {
                keepGoing = false;
            }
            System.out.println("\nBye!");
        }
    }


    // EFFECTS: show the list of all cars
    public void listAllCars() {
        showFinal(carList);
        System.out.println("All options presented!");
    }


    // EFFECTS: show final list of car
    public void showFinal(CarList cars) {
        for (CarSettings car : cars.getCars()) {
            System.out.println("Car data: " + "The brand of car: " + car.getCarBrand() + "\t" + "The price of car: "
                    + car.getPrice() + "\t" + "The color of car: " + car.getColor() + "\t" + "The year of car: "
                    + car.getCarYear() + "\t" + "The mileage of car: " + car.getKmUsed() + "\t");
        }
    }



    // EFFECTS: seller adds/removes using CarBrand
    public String getCarBrand() {
        System.out.println("Car brand:");
        input = new Scanner(System.in); // allows to type car brand
        info = input.nextLine(); // type car brand(info-string)
        return info;
    }

    // EFFECTS: seller adds/removes using price
    public int getPrice() {
        System.out.println("Price:");
        input = new Scanner(System.in);
        num = input.nextInt();
        return num;
    }

    // EFFECTS: seller adds/removes using color
    public String getColor() {
        System.out.println("Color:");
        input = new Scanner(System.in);
        info = input.nextLine();
        return info;
    }

    // EFFECTS: seller adds/removes using car year
    public int getCarYear() {
        System.out.println("Car year:");
        input = new Scanner(System.in);
        num = input.nextInt();
        return num;
    }

    // EFFECTS: seller adds/removes using how much km car ran
    public int getKmUsed() {
        System.out.println("Km used:");
        input = new Scanner(System.in);
        num = input.nextInt();
        return num;
    }


    // EFFECTS: seller add cars for sale
    // (must be different car settings)
    public void addCar() {
        CarSettings car;
        String carBrand = getCarBrand();
        int price = getPrice();
        String color = getColor();
        int carYear = getCarYear();
        int kmUsed = getKmUsed();
        int numbCars = carList.getNumOfCars();
        car = new CarSettings(carBrand, price, color, carYear, kmUsed);
        carList.addCarToList(car);
        showFinal(carList);
        if (numbCars == carList.getNumOfCars()) { // check if there is such car in the list
            System.out.println("Cannot post :(");
        } else {
            System.out.println("Added successfully :)");
        }
    }


    // EFFECTS: seller removes a cars from list
    // (to remove car settings should be same as in the list)
    public void removeCar() {
        int numbCars = carList.getNumOfCars();
        String carBrand = getCarBrand();
        int price = getPrice();
        String color = getColor();
        int carYear = getCarYear();
        int kmUsed = getKmUsed();
        carList.removeCarFromList(carBrand, price, color, carYear, kmUsed);
        showFinal(carList);
        if (numbCars == carList.getNumOfCars()) { // check if there is such car in the list
            System.out.println("This car wasn't found in the list!");
        } else {
            System.out.println("Car was removed from your list!");
        }
    }

    // MODIFIES: this
    // EFFECTS: processes seller command
    private void processCommandSeller() {
        String command = input.next();
        command = command.toLowerCase();
        if (command.equals("a")) {
            addCar();
        } else if (command.equals("r")) {
            removeCar();
        } else if (command.equals("s")) {
            listAllCars();
        } else {
            System.out.println("Selection failed");
        }
    }

    // EFFECTS: user settings by Car Brand
    private void doCarBrand() {
        System.out.println("Which car brand are you looking for? (Enter String)");
        input = new Scanner(System.in);
        info = input.nextLine();
        CarList carsBrand = carList.sameCarBrand(info);
        showFinal(carsBrand);
        System.out.println("All options presented!");
    }

    // EFFECTS: user settings by Car Price
    private void doCarPrice() {
        System.out.println("Which car price are you looking for? (Enter Integer)");
        input = new Scanner(System.in);
        num = input.nextInt();
        CarList carsUnderPrice = carList.underCarPrice(num);
        showFinal(carsUnderPrice);
        System.out.println("All options presented!");
    }

    // EFFECTS: user settings by Car Color
    private void doColor() {
        System.out.println("Which color of car are you looking for? (Enter String)");
        input = new Scanner(System.in);
        info = input.nextLine();
        CarList carsColor = carList.sameCarColor(info);
        showFinal(carsColor);
        System.out.println("All options presented!");
    }

    // EFFECTS: user settings by Car Year
    private void doYear() {
        System.out.println("Which year of car are you looking for? (Enter Integer)");
        input = new Scanner(System.in);
        num = input.nextInt();
        CarList carsYear = carList.carsWithGivenYear(num);
        showFinal(carsYear);
        System.out.println("All options presented!");
    }

    // EFFECTS: user settings by mileage
    private void doKm() {
        System.out.println("Which mileage are you looking for? (Enter Integer)");
        input = new Scanner(System.in);
        num = input.nextInt();
        CarList carsKm = carList.underKmUse(num);
        showFinal(carsKm);
        System.out.println("All options presented!");
    }


    // MODIFIES: this
    // EFFECTS: processes user command
    private void processCommandUser() {
        String command = input.next();
        command = command.toLowerCase();
        if (command.equals("b")) {
            doCarBrand();
        } else if (command.equals("p")) {
            doCarPrice();
        } else if (command.equals("l")) {
            listAllCars();
        } else if (command.equals("c")) {
            doColor();
        } else if (command.equals("y")) {
            doYear();
        } else if (command.equals("k")) {
            doKm();
        } else if (command.equals("s")) {
            listAllCars();
        } else {
            System.out.println("Selection failed");
        }
    }
}



