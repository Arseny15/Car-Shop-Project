package persistence;

import model.CarSettings;
import model.CarList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class JsonTest {
    protected void checkCarSettings(String carBrand, int price, String color, int carYear,
                                    int kmUsed, CarSettings carSettings) {
        assertEquals(carBrand, carSettings.getCarBrand());
        assertEquals(price, carSettings.getPrice());
        assertEquals(color, carSettings.getColor());
        assertEquals(carYear, carSettings.getCarYear());
        assertEquals(kmUsed, carSettings.getKmUsed());
    }
}
