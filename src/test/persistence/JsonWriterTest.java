package persistence;

import model.CarList;
import model.CarSettings;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class JsonWriterTest extends JsonTest {

    @Test
    void testWriterInvalidFile() {
        try {
            CarList cars = new CarList();
            JsonWriter writer = new JsonWriter("./data/my\0illegal:fileName.json");
            writer.open();
            fail("IOException was expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testWriterEmptyCarList() {
        try {
            CarList cars = new CarList();
            JsonWriter writer = new JsonWriter("./data/testWriterEmptyCarList.json");
            writer.open();
            writer.write(cars);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterEmptyCarList.json");
            cars = reader.read();
            assertEquals(0, cars.getNumOfCars());
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }


    @Test
    void testWriterGeneralCarList() {
        try {
            CarList cars = new CarList();
            CarSettings car1 = new CarSettings("Lada copeyka", 1000,
                    "blue", 2000, 10000);
            CarSettings car2 = new CarSettings("Koenigsegg", 4000000,
                    "carbon", 2023, 1);
            cars.addCarToList(car1);
            cars.addCarToList(car2);

            JsonWriter writer = new JsonWriter("./data/testWriterGeneralCarList.json");
            writer.open();
            writer.write(cars);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterGeneralCarList.json");
            cars = reader.read();
            List<CarSettings> car = cars.getCars();
            assertEquals(2, car.size());
            checkCarSettings("Lada copeyka", 1000, "blue", 2000, 10000,
                    car.get(0));
            checkCarSettings("Koenigsegg", 4000000, "carbon", 2023, 1,
                    car.get(1));

        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

}


