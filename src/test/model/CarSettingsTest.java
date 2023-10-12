package model;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class CarSettingsTest {
    private CarSettings car1;
    private CarSettings car2;


    @BeforeEach
    void runBefore() {
        this.car1 = new CarSettings("Lada copeyka", 1000, "blue", 2000, 10000);
        this.car2 = new CarSettings("Koenigsegg", 4000000, "carbon", 2023, 1);
    }

    @Test
    void testConstructor() {
        assertEquals("Lada copeyka", car1.getCarBrand());
        assertEquals(1000, car1.getPrice());
        assertEquals("blue", car1.getColor());
        assertEquals(2000, car1.getCarYear());
        assertEquals(10000, car1.getKmUsed());

        assertEquals("Koenigsegg", car2.getCarBrand());
        assertEquals(4000000, car2.getPrice());
        assertEquals("carbon", car2.getColor());
        assertEquals(2023, car2.getCarYear());
        assertEquals(1, car2.getKmUsed());
    }
}

