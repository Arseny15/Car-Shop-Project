package model;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

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
        assertTrue(testCarListEmpty.getCars().isEmpty());
        assertEquals(0, testCarListEmpty.getNumOfCars());
        assertEquals(1, testCarListOnlyOne.getNumOfCars());
        assertEquals(car1, testCarListOnlyOne.getCars().get(0));
        assertEquals("BMW", car3.getCarBrand());
        assertFalse(testCarListAll.getCars().isEmpty());
    }


    @Test
    void testAddCarToListFail() {
        testCarListEmpty.addCarToList(car1);
        testCarListEmpty.addCarToList(car1);
        testCarListEmpty.addCarToList(car1);
        List<CarSettings> cars = testCarListEmpty.getCars();
        assertEquals(1, cars.size());
        assertEquals(car1, cars.get(0));
    }

    @Test
    void testAddCarToList() {
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
    void testAddCarToListMore() {
        testCarListAll.addCarToList(new CarSettings("Lada", 11, "blue", 2000, 10));
        assertEquals(5,testCarListAll.getNumOfCars());
    }


    @Test
    void testFindSpecCarFailed() {
        assertEquals(null, testCarListOnlyOne.findSpecCar("Lada",
                100, "green", 200, 1000));

        assertEquals(null, testCarListAll.findSpecCar("BMW",
                100, "green", 200, 1000));

        assertEquals(null, testCarListOnlyOne.findSpecCar("La",
                100, "blue", 2000, 10000));

        assertEquals(null, testCarListOnlyOne.findSpecCar("Lada copeyka",
                100, "blue", 2000, 10000));
        assertEquals(null, testCarListOnlyOne.findSpecCar("Lada copeyka",
                1000, "green", 2000, 10000));
        assertEquals(null, testCarListOnlyOne.findSpecCar("Lada copeyka",
                1000, "blue", 20, 10000));
        assertEquals(null, testCarListOnlyOne.findSpecCar("Lada copeyka",
                1000, "blue", 2000, 100));

    }

    @Test
    void testCarExists() {
        CarSettings car1 = new CarSettings("Lada copeyka",
                100, "blue", 2000, 10000);
        CarSettings car2 = new CarSettings("BMW",
                100, "green", 200, 1000);

        testCarListEmpty.addCarToList(car1);

        assertTrue(testCarListEmpty.carExists("Lada copeyka",
                100, "blue", 2000, 10000));
        assertFalse(testCarListEmpty.carExists("BMW",
                100, "green", 200, 1000));
    }

    @Test
    void testCarExistsM() {
        CarSettings car1 = new CarSettings("Lada copeyka",
                100, "blue", 2000, 10000);
        CarSettings car2 = new CarSettings("BMW",
                100, "green", 200, 1000);

        testCarListEmpty.addCarToList(car1);
        testCarListEmpty.addCarToList(car2);

        assertTrue(testCarListEmpty.carExists("Lada copeyka",
                100, "blue", 2000, 10000));
        assertTrue(testCarListEmpty.carExists("BMW",
                100, "green", 200, 1000));
    }

    @Test
    void testCarExistsF() {
        CarSettings car2 = new CarSettings("BMW",
                100, "green", 200, 1000);

        testCarListEmpty.addCarToList(car2);

        assertEquals(false, testCarListEmpty.carExists("Lada copeyka",
                10, "blue", 2000, 10000));
    }

    @Test
    void testCarExistsFF() {
        CarSettings car2 = new CarSettings("BMW",
                100, "green", 200, 1000);

        testCarListEmpty.addCarToList(car2);

        assertEquals(false, testCarListEmpty.carExists("A",
                100, "green", 200, 1000));
    }

    @Test
    void testCarExistsFFF() {
        CarSettings car2 = new CarSettings("BMW",
                100, "green", 200, 1000);

        testCarListEmpty.addCarToList(car2);

        assertEquals(false, testCarListEmpty.carExists("BMW",
                0, "green", 200, 1000));
    }

    @Test
    void testCarExistsFFFF() {
        CarSettings car2 = new CarSettings("BMW",
                100, "green", 200, 1000);

        testCarListEmpty.addCarToList(car2);

        assertEquals(false, testCarListEmpty.carExists("BMW",
                100, "blue", 200, 1000));
    }

    @Test
    void testCarExistsFFFFF() {
        CarSettings car2 = new CarSettings("BMW",
                100, "green", 200, 1000);

        testCarListEmpty.addCarToList(car2);

        assertEquals(false, testCarListEmpty.carExists("BMW",
                100, "green", 0, 1000));
    }

    @Test
    void testCarExistsFFFFFF() {
        CarSettings car2 = new CarSettings("BMW",
                100, "green", 200, 1000);

        testCarListEmpty.addCarToList(car2);

        assertEquals(false, testCarListEmpty.carExists("BMW",
                100, "green", 200, 0));
    }


    @Test
    void testFindSpecCar() {
        CarSettings specCarFound = testCarListOnlyOne.findSpecCar("Lada copeyka",
                1000, "blue", 2000, 10000);
        assertEquals("Lada copeyka", specCarFound.getCarBrand());
        assertEquals(1000, specCarFound.getPrice());
        assertEquals("blue", specCarFound.getColor());
        assertEquals(2000, specCarFound.getCarYear());
        assertEquals(10000, specCarFound.getKmUsed());
    }

    @Test
    void testFindSpecCarConstants() {
        assertEquals(car2, testCarListAll.findSpecCar("Koenigsegg", 4000000,
                "carbon", 2023, 1));

        assertEquals(car1, testCarListOnlyOne.findSpecCar("Lada copeyka", 1000,
                "blue", 2000, 10000));
    }


    @Test
    void testRemoveCarFromListFailed() {
        testCarListOnlyOne.removeCarFromList("Lada ", 1000,
                "blue", 2000, 10000);
        assertEquals(1, testCarListOnlyOne.getCars().size());
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
    void testSameCarsByBrandFailed() {
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
    void testSameCarColorContainsFailed() {
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
        CarList sameColor = new CarList();
        testCarListEmpty = testCarListAll.sameCarColor("blue");
        sameColor.addCarToList(car1);
        sameColor.addCarToList(car4);
        assertEquals(sameColor.getCars(), testCarListEmpty.getCars());
        assertEquals(2, testCarListEmpty.getNumOfCars());
        assertEquals(car1, testCarListEmpty.getCars().get(0));
        assertEquals(car4, testCarListEmpty.getCars().get(1));
    }


    @Test
    void testCarsWithGivenYearContainsFailed() {
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
        CarList sameYear = new CarList();
        testCarListEmpty = testCarListAll.carsWithGivenYear(2000);
        sameYear.addCarToList(car1);
        sameYear.addCarToList(car3);
        assertEquals(sameYear.getCars(), testCarListEmpty.getCars());
        assertEquals(2, testCarListEmpty.getNumOfCars());
        assertEquals(car1, testCarListEmpty.getCars().get(0));
        assertEquals(car3, testCarListEmpty.getCars().get(1));
    }


    @Test
    void testUnderKmContainFailed() {
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

        assertEquals(3, result.getNumOfCars());
    }

    @Test
    void testUnderKmUseBoundary() {
        // use constants
        CarList sameKm = new CarList();
        testCarListEmpty = testCarListAll.underKmUse(15000);
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
    void testUnderCarPriceContainFailed() {
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
        CarList sameUnderPrice = new CarList();
        testCarListEmpty = testCarListAll.underCarPrice(130000);
        sameUnderPrice.addCarToList(car1);
        sameUnderPrice.addCarToList(car3);
        assertEquals(sameUnderPrice.getCars(), testCarListEmpty.getCars());
        assertEquals(2, testCarListEmpty.getNumOfCars());
        assertEquals(car1, testCarListEmpty.getCars().get(0));
        assertEquals(car3, testCarListEmpty.getCars().get(1));

    }

    @Test
    void testSameCarPriceFailed() {
        testCarListEmpty = testCarListAll.sameCarPrice(1);
        assertEquals(0, testCarListEmpty.getNumOfCars());
    }

    @Test
    void testSameCarPriceNewVar() {
        CarList carList = new CarList();
        carList.addCarToList(new CarSettings("CarA", 20000, "blue", 2000, 10000));
        carList.addCarToList(new CarSettings("CarB", 25000, "blue", 2000, 10000));
        carList.addCarToList(new CarSettings("CarC", 12000, "blue", 2000, 10000));

        CarList result = carList.sameCarPrice(12000);

        assertEquals(1, result.getNumOfCars());
    }

    @Test
    void testSameCarPriceMax() {
        CarList samePrice = new CarList();
        testCarListEmpty = testCarListAll.sameCarPrice(1000);
        samePrice.addCarToList(car1);
        samePrice.addCarToList(car3);
        assertEquals(1, testCarListEmpty.getNumOfCars());
        assertEquals(car1, testCarListEmpty.getCars().get(0));
    }
}
