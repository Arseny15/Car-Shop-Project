package persistence;

import org.junit.jupiter.api.Test;

import model.CarList;
import model.CarSettings;
import java.io.IOException;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class JsonReaderTest extends JsonTest {

    @Test
    void testReaderNonExistentFile() {
        JsonReader reader = new JsonReader("./data/noSuchFile.json");
        try {
            CarList cars = reader.read();
            fail("IOException expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testReaderEmptyCarList() {
        JsonReader reader = new JsonReader("./data/testReaderEmptyCarList.json");
        try {
            CarList cars = reader.read();
            assertEquals(0, cars.getNumOfCars());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    @Test
    void testReaderGeneralCarList() {
        JsonReader reader = new JsonReader("./data/testReaderGeneralCarList.json");
        try {
            CarList cars = reader.read();
            List<CarSettings> car = cars.getCars();
            assertEquals(4, car.size());
            checkCarSettings("Lada copeyka", 1000, "blue", 2000, 10000, car.get(0));
            checkCarSettings("Koenigsegg", 4000000, "carbon", 2023, 0, car.get(1));
            checkCarSettings("BMW", 120000, "race green", 2000, 100, car.get(2));
            checkCarSettings("Porsche 911 GT3 RS", 300000, "blue", 2022, 4000,
                    car.get(3));
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }
}
