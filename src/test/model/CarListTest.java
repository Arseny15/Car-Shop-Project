package model;

import model.CarList;
import model.CarSettings;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class CarListTest {
    private CarList testCarListAll;
    private CarList testCarListOnlyOne;
    private CarList testCarListEmpty;
    private CarSettings car1;
    private CarSettings car2;
    private CarSettings car3;
    private CarSettings car4;



    @BeforeEach
    void runBefore() {
        this.car1 = new CarSettings("Lada copeyka", 1000, "blue", 2000, 10000);
        this.car2 = new CarSettings("Koenigsegg", 4000000, "carbon", 2023, 1);
        this.car3 = new CarSettings("BMW", 120000, "race green", 2000, 100);
        this.car4 = new CarSettings("Porsche 911 GT3 RS", 300000,
                "blue", 2022, 4000);

        testCarListEmpty = new CarList();

        testCarListOnlyOne = new CarList();
        testCarListOnlyOne.addCarToList(car1);

        testCarListAll = new CarList();
        testCarListAll.addCarToList(car1);
        testCarListAll.addCarToList(car2);
        testCarListAll.addCarToList(car3);
        testCarListAll.addCarToList(car4);
    }

    @Test
    void testConstructor() {
        assertEquals(0, testCarListEmpty.getNumOfCars());
        assertEquals(car1, testCarListOnlyOne.getCars().get(0));
        assertEquals("BMW", car3.getCarBrand());
    }

    @Test
    void testAddCarToList() {
        assertEquals(0, testCarListEmpty.getNumOfCars());
        assertTrue(testCarListEmpty.getCars().isEmpty());

        testCarListEmpty.addCarToList(car1);
        assertEquals("Lada copeyka", car1.getCarBrand());
        assertEquals(1000, car1.getPrice());
        assertEquals("blue", car1.getColor());
        assertEquals(2000, car1.getCarYear());
        assertEquals(10000, car1.getKmUsed());
        List<CarSettings> cars = testCarListEmpty.getCars();
        assertEquals(1, cars.size());
        assertEquals(car1, cars.get(0));
    }

    @Test
    void testFindSpecCar() {
        assertEquals(car2, testCarListAll.findSpecCar("Koenigsegg", 4000000,
                "carbon", 2023, 1));

        CarSettings specCarFound = testCarListOnlyOne.findSpecCar("Lada copeyka",
                1000, "blue", 2000, 10000);
        assertEquals("Lada copeyka", specCarFound.getCarBrand());
        assertEquals(1000, specCarFound.getPrice());
        assertEquals("blue", specCarFound.getColor());
        assertEquals(2000, specCarFound.getCarYear());
        assertEquals(10000, specCarFound.getKmUsed());

        assertEquals(car1, testCarListOnlyOne.findSpecCar("Lada copeyka", 1000,
                "blue", 2000, 10000));
    }


    @Test
    void testRemoveCarFromList() {
        assertEquals(1, testCarListOnlyOne.getNumOfCars());

        testCarListOnlyOne.removeCarFromList("Lada copeyka", 1000,
                "blue", 2000, 10000);
        assertEquals(0, testCarListOnlyOne.getCars().size());

        testCarListAll.removeCarFromList("Porsche 911 GT3 RS", 300000,
                "blue", 2022, 4000);
        assertEquals(3, testCarListAll.getNumOfCars());
    }

    @Test
    void testSameCarsByBrandNone() {
        testCarListEmpty = testCarListAll.sameCarBrand("ajjdbndj");
        assertEquals(0, testCarListEmpty.getNumOfCars());
    }

    @Test
    void testSameCarsByBrandNewVar() {
        CarList brand = new CarList();
        brand.addCarToList(new CarSettings("CarA", 20000, "blue", 2000, 10000));
        brand.addCarToList(new CarSettings("CarB", 25000, "blue", 2000, 9000));
        brand.addCarToList(new CarSettings("CarA", 12000, "blue", 2000, 10000));

        CarList result = brand.sameCarBrand("CarA");

        assertEquals(2, result.getNumOfCars());
    }

    @Test
    void testSameCarsByBrand() {
        CarList sameBrands = new CarList();
        testCarListEmpty = testCarListAll.sameCarBrand("BMW");
        sameBrands.addCarToList(car3);
        assertEquals(1, testCarListEmpty.getNumOfCars());
        assertEquals(car3, testCarListEmpty.getCars().get(0));
    }


    @Test
    void testSameCarColorContainsNone() {
        testCarListEmpty = testCarListAll.sameCarColor("yellow");
        assertEquals(0, testCarListEmpty.getNumOfCars());
    }

    @Test
    void testSameColorNewVar() {
        CarList color = new CarList();
        color.addCarToList(new CarSettings("CarA", 20000, "white", 2000, 10000));
        color.addCarToList(new CarSettings("CarB", 25000, "blue", 2000, 9000));
        color.addCarToList(new CarSettings("CarA", 12000, "blue", 2000, 10000));

        CarList result = color.sameCarColor("blue");

        assertEquals(2, result.getNumOfCars());
    }

    @Test
    void testSameCarColor() {
        testCarListEmpty = testCarListAll.sameCarColor("blue");
        CarList sameColor = new CarList();
        sameColor.addCarToList(car1);
        sameColor.addCarToList(car4);
        assertEquals(sameColor.getCars(), testCarListEmpty.getCars());
        assertEquals(2, testCarListEmpty.getNumOfCars());
        assertEquals(car1, testCarListEmpty.getCars().get(0));
        assertEquals(car4, testCarListEmpty.getCars().get(1));
    }


    @Test
    void testCarsWithGivenYearContainsNone() {
        testCarListEmpty = testCarListAll.carsWithGivenYear(2029);
        assertEquals(0, testCarListEmpty.getNumOfCars());
    }

    @Test
    void testCarsWithGivenYearNewVar() {
        CarList year = new CarList();
        year.addCarToList(new CarSettings("CarA", 20000, "white", 2002, 10000));
        year.addCarToList(new CarSettings("CarB", 25000, "blue", 2000, 9000));
        year.addCarToList(new CarSettings("CarA", 12000, "blue", 2001, 10000));

        CarList result = year.carsWithGivenYear(2000);

        assertEquals(1, result.getNumOfCars());
    }

    @Test
    void testCarsWithGivenYear() {
        testCarListEmpty = testCarListAll.carsWithGivenYear(2000);
        CarList sameYear = new CarList();
        sameYear.addCarToList(car1);
        sameYear.addCarToList(car3);
        assertEquals(sameYear.getCars(), testCarListEmpty.getCars());
        assertEquals(2, testCarListEmpty.getNumOfCars());
        assertEquals(car1, testCarListEmpty.getCars().get(0));
        assertEquals(car3, testCarListEmpty.getCars().get(1));
    }


    @Test
    void testUnderKmContainNone() {
        testCarListEmpty = testCarListAll.underKmUse(0);
        assertEquals(0, testCarListEmpty.getNumOfCars());
    }

    @Test
    void testUnderCarKmNewVar() {
        CarList carList = new CarList();
        carList.addCarToList(new CarSettings("CarA", 20000, "blue", 2000, 10000));
        carList.addCarToList(new CarSettings("CarB", 25000, "blue", 2000, 9000));
        carList.addCarToList(new CarSettings("CarC", 12000, "blue", 2000, 10000));

        CarList result = carList.underKmUse(10000);

        assertEquals(3, carList.getNumOfCars());
    }

    @Test
    void testUnderKmUseBoundary() {
        // use constants
        testCarListEmpty = testCarListAll.underKmUse(15000);
        CarList sameKm = new CarList();
        sameKm.addCarToList(car1);
        sameKm.addCarToList(car2);
        sameKm.addCarToList(car3);
        sameKm.addCarToList(car4);
        assertEquals(sameKm.getCars(), testCarListEmpty.getCars());
        assertEquals(4, testCarListEmpty.getNumOfCars());
        assertEquals(car1, testCarListEmpty.getCars().get(0));
        assertEquals(car2, testCarListEmpty.getCars().get(1));
        assertEquals(car3, testCarListEmpty.getCars().get(2));
        assertEquals(car4, testCarListEmpty.getCars().get(3));
    }


    @Test
    void testUnderCarPriceContainNone() {
        testCarListEmpty = testCarListAll.underCarPrice(1);
        assertEquals(0, testCarListEmpty.getNumOfCars());
    }

    @Test
    void testUnderCarPriceNewVar() {
        CarList carList = new CarList();
        carList.addCarToList(new CarSettings("CarA", 20000, "blue", 2000, 10000));
        carList.addCarToList(new CarSettings("CarB", 25000, "blue", 2000, 10000));
        carList.addCarToList(new CarSettings("CarC", 12000, "blue", 2000, 10000));

        CarList result = carList.underCarPrice(15000);

        assertEquals(1, result.getNumOfCars());
    }

    @Test
    void testUnderCarPriceMax() {
        testCarListEmpty = testCarListAll.underCarPrice(130000);
        CarList samePrice = new CarList();
        samePrice.addCarToList(car1);
        samePrice.addCarToList(car3);
        assertEquals(samePrice.getCars(), testCarListEmpty.getCars());
        assertEquals(2, testCarListEmpty.getNumOfCars());
        assertEquals(car1, testCarListEmpty.getCars().get(0));
        assertEquals(car3, testCarListEmpty.getCars().get(1));
    }
}
