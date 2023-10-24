package persistence;


import model.CarList;
import model.CarSettings;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

import org.json.*;

// Represents a reader that reads carList from JSON data stored in file
public class JsonReader {
    private String source;

    // EFFECTS: constructs reader to read from source file
    public JsonReader(String source) {
        this.source = source;
    }

    // EFFECTS: reads car list from file and returns it;
    // throws IOException if an error occurs reading data from file
    public CarList read() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseCarList(jsonObject);
    }

    // EFFECTS: reads source file as string and returns it
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }

        return contentBuilder.toString();
    }

    // EFFECTS: parses cars from JSON object and returns it
    private CarList parseCarList(JSONObject jsonObject) {
        CarList cl = new CarList();
        addCars(cl, jsonObject);
        return cl;
    }

    // EFFECTS: parses cars from JSON object and adds them to car list
    private void addCars(CarList cl, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("returns cars");
        for (Object json : jsonArray) {
            JSONObject nextThingy = (JSONObject) json;
            addCarSettings(cl, nextThingy);
        }
    }

    // MODIFIES: cl
    // EFFECTS: parses thingy from JSON object and adds it to car list
    private void addCarSettings(CarList cl, JSONObject jsonObject) {
        String carBrand = jsonObject.getString("carBrand");
        int price = jsonObject.getInt("price");
        String color = jsonObject.getString("color");
        int carYear = jsonObject.getInt("carYear");
        int kmUsed = jsonObject.getInt("mileage");
        CarSettings car = new CarSettings(carBrand, price, color, carYear, kmUsed);
        cl.addCarToList(car);
    }
}
