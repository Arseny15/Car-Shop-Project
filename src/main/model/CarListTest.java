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

        testCarListEmpty = new CarList("nb");


        testCarListOnlyOne = new CarList("Poccia");
        testCarListOnlyOne.addCarToList(car1);

        testCarListAll = new CarList("Ars");
        testCarListAll.addCarToList(car1);
        testCarListAll.addCarToList(car2);
        testCarListAll.addCarToList(car3);
        testCarListAll.addCarToList(car4);
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
    void testSameCarsByBrand() {
        testCarListEmpty = testCarListAll.sameCarBrand("BMW");
        CarList sameBrands = new CarList("vodka");
        sameBrands.addCarToList(car3);
        assertEquals(1, testCarListEmpty.getNumOfCars());
        assertEquals(car3, testCarListEmpty.getCars().get(0));
    }


//    @Test
//    void testSameCarsByBrandNewVar() {
//        CarSettings car1 = new CarSettings("BMW", 1000, "Black", 2000, 10000);
//        CarSettings car2 = new CarSettings("Uas", 100, "Red", 2003, 800);
//        CarSettings car3 = new CarSettings("BMW", 1200, "Green", 2001, 20000);
//        testCarListEmpty.addCarToList(car1);
//        testCarListEmpty.addCarToList(car2);
//        testCarListEmpty.addCarToList(car3);
//
//        List<CarSettings> sameBrandCars = testCarListEmpty.sameCarBrand("BMW");
//        assertEquals(2, sameBrandCars.size());
//        assertTrue(sameBrandCars.contains(car1));
//        assertTrue(sameBrandCars.contains(car3));
//
//        List<CarSettings> oneBrand = testCarListEmpty.sameCarBrand("Uas");
//        assertEquals(1, oneBrand.size());
//        assertTrue(oneBrand.contains(car2));
//
//        List<CarSettings> sameBrand = testCarListAll.sameCarBrand("Uas");
//        assertEquals(0, sameBrand.size());
//    }


    @Test
    void testSameCarColor() {
        testCarListEmpty = testCarListAll.sameCarColor("blue");
        CarList sameColor = new CarList("car");
        sameColor.addCarToList(car1);
        sameColor.addCarToList(car4);
        assertEquals(sameColor.getCars(), testCarListEmpty.getCars());
        assertEquals(2, testCarListEmpty.getNumOfCars());
        assertEquals(car1, testCarListEmpty.getCars().get(0));
        assertEquals(car4, testCarListEmpty.getCars().get(1));
    }

    @Test
    void testSameCarColorContainsNone() {
        testCarListEmpty = testCarListAll.sameCarColor("yellow");
        assertEquals(0,testCarListEmpty.getNumOfCars());
        assertEquals("car", testCarListEmpty.getCarBrand());
    }

//    @Test
//    void testSameCarColorNewVar() {
//        CarSettings car1 = new CarSettings("BMW", 1000, "Black", 2000, 10000);
//        CarSettings car2 = new CarSettings("Uas", 100, "Black", 2003, 800);
//        CarSettings car3 = new CarSettings("BMW", 1200, "Green", 2001, 20000);
//        testCarListEmpty.addCarToList(car1);
//        testCarListEmpty.addCarToList(car2);
//        testCarListEmpty.addCarToList(car3);
//
//        List<CarSettings> sameCarColor = testCarListEmpty.sameCarColor("Black");
//        assertEquals(2, sameCarColor.size());
//        assertTrue(sameCarColor.contains(car1));
//        assertTrue(sameCarColor.contains(car2));
//
//        List<CarSettings> oneColor = testCarListEmpty.sameCarColor("Green");
//        assertEquals(1, oneColor.size());
//        assertTrue(oneColor.contains(car3));
//    }


    @Test
    void testCarsWithGivenYear() {
        testCarListEmpty = testCarListAll.carsWithGivenYear(2000);
        CarList sameYear = new CarList("car");
        sameYear.addCarToList(car1);
        sameYear.addCarToList(car3);
        assertEquals(sameYear.getCars(), testCarListEmpty.getCars());
        assertEquals(2, testCarListEmpty.getNumOfCars());
        assertEquals(car1, testCarListEmpty.getCars().get(0));
        assertEquals(car3, testCarListEmpty.getCars().get(1));
    }

    @Test
    void testCarsWithGivenYearContainsNone() {
        testCarListEmpty = testCarListAll.carsWithGivenYear(2029);
        assertEquals(0, testCarListEmpty.getNumOfCars());
    }

//
//    @Test
//    void testCarsWithGivenYearNewVar() {
//        CarSettings car1 = new CarSettings("Marusa", 1000, "Black", 2000, 10000);
//        CarSettings car2 = new CarSettings("Uas", 100, "Black", 2000, 800);
//        CarSettings car3 = new CarSettings("Marusa", 1200, "Green", 2001, 20000);
//        testCarListEmpty.addCarToList(car1);
//        testCarListEmpty.addCarToList(car2);
//        testCarListEmpty.addCarToList(car3);
//
//        List<CarSettings> carsYear = testCarListEmpty.carsWithGivenYear(2000);
//        assertEquals(2, carsYear.size());
//        assertTrue(carsYear.contains(car1));
//        assertTrue(carsYear.contains(car2));
//
//        List<CarSettings> carsYear1 = testCarListEmpty.carsWithGivenYear(2001);
//        assertEquals(1, carsYear1.size());
//        assertTrue(carsYear1.contains(car3));
//    }


    @Test
    void testUnderKmUseBoundary() {
        // use constants
        testCarListEmpty = testCarListAll.underKmUse(15000);
        CarList sameKm = new CarList("car");
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
    void testUnderKmContainNone() {
        testCarListEmpty = testCarListAll.underKmUse(0);
        assertEquals(0, testCarListEmpty.getNumOfCars());
    }


//    @Test
//    void testUnderKmUseNewVar() {
//        CarSettings car1 = new CarSettings("Marusa", 1000, "Black", 2000, 10000);
//        CarSettings car2 = new CarSettings("Uas", 100, "Black", 2000, 800);
//        CarSettings car3 = new CarSettings("Marusa", 1200, "Green", 2001, 20000);
//        testCarListEmpty.addCarToList(car1);
//        testCarListEmpty.addCarToList(car2);
//        testCarListEmpty.addCarToList(car3);
//
//        List<CarSettings> carsKm = testCarListEmpty.underKmUse(19999); // on border
//        assertEquals(2, carsKm.size());
//        assertTrue(carsKm.contains(car1));
//        assertTrue(carsKm.contains(car2));
//
//        assertFalse(carsKm.contains(car3));
//
//        List<CarSettings> carsKm1 = testCarListEmpty.underKmUse(20000); // contain all
//        assertEquals(3, carsKm1.size());
//        assertTrue(carsKm1.contains(car1));
//        assertTrue(carsKm1.contains(car2));
//        assertTrue(carsKm1.contains(car3));
//
//        List<CarSettings> carsKm2 = testCarListEmpty.underKmUse(799); // contain none
//        assertEquals(0, carsKm2.size());
//        assertFalse(carsKm2.contains(car1));
//        assertFalse(carsKm2.contains(car2));
//        assertFalse(carsKm2.contains(car3));
//    }


    @Test
    void testUnderCarPriceMax() {
        testCarListEmpty = testCarListAll.underCarPrice(130000);
        CarList samePrice = new CarList("car");
        samePrice.addCarToList(car1);
        samePrice.addCarToList(car3);
        assertEquals(samePrice.getCars(), testCarListEmpty.getCars());
        assertEquals(2, testCarListEmpty.getNumOfCars());
        assertEquals(car1, testCarListEmpty.getCars().get(0));
        assertEquals(car3, testCarListEmpty.getCars().get(1));
    }


    @Test
    void testUnderCarPriceContainNone() {
        testCarListEmpty = testCarListAll.underCarPrice(1);
        assertEquals(0, testCarListEmpty.getNumOfCars());
    }


//    @Test
//    void testUnderCarPriceNewVar() {
//        CarSettings car1 = new CarSettings("Marusa", 1000, "Black", 2000, 10000);
//        CarSettings car2 = new CarSettings("Uas", 100, "Black", 2000, 800);
//        CarSettings car3 = new CarSettings("Marusa", 1200, "Green", 2001, 20000);
//        testCarListEmpty.addCarToList(car1);
//        testCarListEmpty.addCarToList(car2);
//        testCarListEmpty.addCarToList(car3);
//
//        List<CarSettings> carsPrice = testCarListEmpty.underCarPrice(1199); // on border
//        assertEquals(2, carsPrice.size());
//        assertTrue(carsPrice.contains(car1));
//        assertTrue(carsPrice.contains(car2));
//
//        assertFalse(carsPrice.contains(car3));
//
//        List<CarSettings> carsP = testCarListEmpty.underCarPrice(1200); // contains all
//        assertEquals(3, carsP.size());
//        assertTrue(carsP.contains(car1));
//        assertTrue(carsP.contains(car2));
//        assertTrue(carsP.contains(car3));
//
//        List<CarSettings> carsP1 = testCarListEmpty.underCarPrice(99); // contains none
//        assertEquals(0, carsP1.size());
//        assertFalse(carsP1.contains(car1));
//        assertFalse(carsP1.contains(car2));
//        assertFalse(carsP1.contains(car3));
//    }


}
