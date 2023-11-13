package ui;

import model.CarList;
import model.CarSettings;
import persistence.JsonReader;
import persistence.JsonWriter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.FileNotFoundException;
import java.io.IOException;


public class GUI extends JFrame {
    public static final int WIDTH = 1500;
    public static final int HEIGHT = 1500;
    private JDesktopPane desk;
    private JInternalFrame additionalPanel;

    private CarList carList = new CarList();

    private static final String JSON_STORE = "./data/carList.json";
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;

    private JLabel carBrandLabel = new JLabel("Car brand");
    private JLabel priceLabel = new JLabel("Car price");
    private JLabel colorLabel = new JLabel("Car color");
    private JLabel carYearLabel = new JLabel("Car year");
    private JLabel mileageLabel = new JLabel("Mileage");

    private JButton findCar = new JButton("Find car");
    private JButton addCar = new JButton("Add car for sale");
    private JButton removeCar = new JButton("Remove car from sale list");
    private JButton showAllCars = new JButton("Show list of all cars");

    private JTextField search = new JTextField("Enter ...", 20);

    private JTextField textBrand = new JTextField(" ", 20);
    private JTextField textPrice = new JTextField(" ", 10);
    private JTextField textColor = new JTextField(" ", 10);
    private JTextField textCarYear = new JTextField(" ", 4);
    private JTextField textMileage = new JTextField(" ", 10);

    private JCheckBox carBrand = new JCheckBox("By car brand");
    private JCheckBox carPrice = new JCheckBox("By price", true);
    private JCheckBox carColor = new JCheckBox("By color");
    private JCheckBox carYear = new JCheckBox("By car year");
    private JCheckBox mileage = new JCheckBox("By mileage");


    // MODIFIES: this
    // EFFECTS: constructs a window for car shop application
    public GUI() {
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);

        desk = new JDesktopPane();
        desk.setBackground(new java.awt.Color(255, 255, 255));
        desk.addMouseListener(new DesktopFocusAction());


        additionalPanel = new JInternalFrame("Main buttons", false, false,
                false, false);
        additionalPanel.setLayout(new BorderLayout());
        additionalPanel.setSize(80,40);

        additionalPanel.pack();
        additionalPanel.setVisible(true);
        desk.add(additionalPanel);

        mainButtonSettings();
        buttonPanel();

        setContentPane(desk);
        setTitle("Car shop application");
        setSize(WIDTH, HEIGHT);

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);

        add(sellersSection()).setBounds(0, 0, WIDTH, HEIGHT / 2);
        add(usersSection()).setBounds(0, HEIGHT / 2, WIDTH, HEIGHT / 2);
    }

    // MODIFIES: this
    // EFFECTS: creates panel for sellers
    public JPanel sellersSection() {
        JPanel panel = new JPanel();
        panel.setBackground(new java.awt.Color(165, 168, 180));
        panel.setForeground(Color.black);
        JLabel label = new JLabel("Car Sellers");
        label.setFont(new Font("Calibre", Font.BOLD, 30));
        panel.add(label).setBounds(750, 0, 100, 50);


        panel.add(carBrandLabel).setBounds(180, 70, 160, 20);
        panel.add(textBrand).setBounds(220, 70, 160, 20);

        panel.add(priceLabel).setBounds(200, 100, 160, 20);
        panel.add(textPrice).setBounds(240, 100, 160, 20);

        panel.add(colorLabel).setBounds(260, 130, 160, 20);
        panel.add(textColor).setBounds(280, 130, 160, 20);

        panel.add(carYearLabel).setBounds(300, 160, 160, 20);
        panel.add(textCarYear).setBounds(320, 160, 160, 20);

        panel.add(mileageLabel).setBounds(340, 190, 160, 20);
        panel.add(textMileage).setBounds(360, 190, 160, 20);

        panel.add(addCar).setBounds(380, 220, 160, 30);
        panel.add(removeCar).setBounds(400, 250, 160, 30);

        return panel;
    }

    // MODIFIES: this
    // EFFECTS: creates panel for users
    public JPanel usersSection() {
        JPanel panelU = new JPanel();
        panelU.setBackground(new java.awt.Color(139, 159, 232));
        panelU.setForeground(Color.black);
        JLabel label = new JLabel("Potential car buyers");
        label.setFont(new Font("Calibre", Font.BOLD, 30));
        panelU.add(label).setBounds(750, 750, 100, 50);

        panelU.add(carBrand).setBounds(440, 270, 100, 20);
        panelU.add(carPrice).setBounds(440, 270, 100, 20);
        panelU.add(carColor).setBounds(440, 270, 100, 20);
        panelU.add(carYear).setBounds(440, 270, 100, 20);
        panelU.add(mileage).setBounds(440, 270, 100, 20);

        panelU.add(search).setBounds(460, 300, 160, 30);

        panelU.add(findCar).setBounds(480, 400, 300, 80);

        return panelU;
    }

    public void mainButtonSettings() {
        findCar.addActionListener(new FindCar());
        addCar.addActionListener(new AddCar());
        removeCar.addActionListener(new RemoveCar());
        showAllCars.addActionListener(new ShowAllCars());
    }


    public void buttonPanel() {
        JPanel buttons = new JPanel();
        buttons.setLayout(new GridLayout(1, 2));

        buttons.add(new JButton(new SaveCars()));
        buttons.add(new JButton(new LoadCars()));

        additionalPanel.add(buttons, BorderLayout.CENTER);
    }

    // MODIFIES: this
    // EFFECTS: finds car by different settings
    private class ShowAllCars extends AbstractAction {

        ShowAllCars() {
            super("Show list of all cars");
        }

        @Override
        public void actionPerformed(ActionEvent evt) {
            JTextArea resultArea = new JTextArea();
            resultArea.setEditable(false);

            showFinal(carList);

            JFrame resultFrame = new JFrame("List of All Cars: ");
            resultFrame.setSize(600, 400);
            resultFrame.add(new JScrollPane(resultArea));

            resultFrame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
            resultFrame.setVisible(true);
        }
    }


    // MODIFIES: this
    // EFFECTS: finds car by different settings
    private class FindCar extends AbstractAction {

        FindCar() {
            super("Find car");
        }

        @Override
        public void actionPerformed(ActionEvent evt) {
            processCommandSearch();
        }
    }

    public boolean addValidNewCar() {
        CarSettings car;
        int mileage = Integer.parseInt(textMileage.getText());
        int year = Integer.parseInt(textCarYear.getText());
        String color = textColor.getText();
        int price = Integer.parseInt(textPrice.getText());
        String carBrand = textBrand.getText();
        int numbCars = carList.getNumOfCars();

        car = new CarSettings(carBrand, price, color, year, mileage);
        carList.addCarToList(car);

        if (numbCars == carList.getNumOfCars()) { // check if there is such car in the list
            return false;
        } else {
            return true;
        }
    }

    // MODIFIES: this
    // EFFECTS: adds a new car to sale list
    private class AddCar extends AbstractAction {
        AddCar() {
            super("Add car for sale");
        }

        @Override
        public void actionPerformed(ActionEvent evt) {
            if (!addValidNewCar()) {
                JFrame result = new JFrame("Displayed result:(");
                result.setSize(200, 100);
                result.setVisible(true);
                result.add((new JLabel("Mission incomplete"))).setBounds(400, 200, 120, 60);
            } else {
                JFrame result = new JFrame("Displayed result:)");
                result.setSize(200, 100);
                result.setVisible(true);
                result.add(new JLabel("Mission completed")).setBounds(400, 200, 120, 60);
            }
        }
    }


    public boolean removeValidCar() {
        int mileage = Integer.parseInt(textMileage.getText());
        int year = Integer.parseInt(textCarYear.getText());
        String color = textColor.getText();
        int price = Integer.parseInt(textPrice.getText());
        String carBrand = textBrand.getText();
        int numbCars = carList.getNumOfCars();

        carList.removeCarFromList(carBrand, price, color, year, mileage);

        if (numbCars == carList.getNumOfCars()) { // check if there is such car in the list
            return false;
        } else {
            return true;
        }
    }

    // MODIFIES: this
    // EFFECTS: removes car from the sale list
    private class RemoveCar extends AbstractAction {
        RemoveCar() {
            super("Remove car from sale list");
        }

        @Override
        public void actionPerformed(ActionEvent evt) {
            if (!removeValidCar()) {
                JFrame result = new JFrame("Displayed result:(");
                result.setSize(200, 100);
                result.setVisible(true);
                result.add(new JLabel("Mission incomplete")).setBounds(400, 200, 120, 60);
            } else {
                JFrame result = new JFrame("Displayed result:)");
                result.setSize(200, 100);
                result.setVisible(true);
                result.add(new JLabel("Mission completed")).setBounds(400, 200, 120, 60);
            }
        }
    }


    // MODIFIES: this
    // EFFECTS: save newly added cars to the file
    private class SaveCars extends AbstractAction {
        SaveCars() {
            super("Save cars to the file");
        }

        @Override
        public void actionPerformed(ActionEvent evt) {
            try {
                jsonWriter.open();
                jsonWriter.write(carList);
                jsonWriter.close();
                System.out.println("Saved car list to " + JSON_STORE);
            } catch (FileNotFoundException e) {
                System.out.println("Unable to write to file: " + JSON_STORE);
            }
        }
    }

    // MODIFIES: this
    // EFFECTS: load cars from the file
    private class LoadCars extends AbstractAction {
        LoadCars() {
            super("Load cars from the file");
        }

        @Override
        public void actionPerformed(ActionEvent evt) {
            try {
                carList = jsonReader.read();
                System.out.println("Loaded car list from " + JSON_STORE);
            } catch (IOException e) {
                System.out.println("Unable to read from file: " + JSON_STORE);
            }
        }
    }


    // MODIFIES: this
    // EFFECTS: processes user command
    private void processCommandSearch() {
        if (carBrand.isSelected()) {
            doCarBrand();
        } else if (carPrice.isSelected()) {
            doCarPrice();
        } else if (carColor.isSelected()) {
            doCarColor();
        } else if (carYear.isSelected()) {
            doYear();
        } else if (mileage.isSelected()) {
            doMileage();
        }
    }

    // MODIFIES: this
    // EFFECTS: search by car brand
    private void doCarBrand() {
//        carList.sameCarBrand(search.getText());
        String brandToFind = search.getText();
        CarList resultCars = carList.sameCarBrand(brandToFind);
        displaySearchResult(resultCars);
    }

    // MODIFIES: this
    // EFFECTS: search by car price
    private void doCarPrice() {
//        int price = Integer.parseInt(search.getText());
//        carList.underCarPrice(price);
        int priceToFind = Integer.parseInt(search.getText());
        CarList resultCars = carList.underCarPrice(priceToFind);
        displaySearchResult(resultCars);
    }

    // MODIFIES: this
    // EFFECTS: search by car color
    private void doCarColor() {
//        carList.sameCarColor(search.getText());
        String colorToFind = search.getText();
        CarList resultCars = carList.sameCarColor(colorToFind);
        displaySearchResult(resultCars);
    }

    // MODIFIES: this
    // EFFECTS: search by car year
    private void doYear() {
//        int year = Integer.parseInt(search.getText());
//        carList.carsWithGivenYear(year);
        int yearToFind = Integer.parseInt(search.getText());
        CarList resultCars = carList.carsWithGivenYear(yearToFind);
        displaySearchResult(resultCars);
    }

    // MODIFIES: this
    // EFFECTS: search by mileage
    private void doMileage() {
//        int mileage = Integer.parseInt(search.getText());
//        carList.underKmUse(mileage);
        int mileageToFind = Integer.parseInt(search.getText());
        CarList resultCars = carList.underKmUse(mileageToFind);
        displaySearchResult(resultCars);
    }

    // EFFECTS: display the result
    private void displaySearchResult(CarList resultCars) {
        JTextArea resultArea = new JTextArea();
        resultArea.setEditable(false);

        for (CarSettings car : resultCars.getCars()) {
            resultArea.append("Car data: The brand of car: " + car.getCarBrand() + "\t" + "The price of car: "
                    + car.getPrice() + "\t" + "The color of car: " + car.getColor() + "\t" + "The year of car: "
                    + car.getCarYear() + "\t" + "The mileage of car: " + car.getKmUsed() + "\t");
        }

        JFrame resultFrame = new JFrame("Search Result");
        resultFrame.setSize(600, 400);
        resultFrame.add(new JScrollPane(resultArea));

        resultFrame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        resultFrame.setVisible(true);
    }


    // EFFECTS: show final list of car
    public void showFinal(CarList cars) {
        for (CarSettings car : cars.getCars()) {
            System.out.println("Car data: " + "The brand of car: " + car.getCarBrand() + "\t" + "The price of car: "
                    + car.getPrice() + "\t" + "The color of car: " + car.getColor() + "\t" + "The year of car: "
                    + car.getCarYear() + "\t" + "The mileage of car: " + car.getKmUsed() + "\t");
        }
    }


    /**
     * Represents action to be taken when user clicks desktop
     * to switch focus. (Needed for key handling.)
     */
    private class DesktopFocusAction extends MouseAdapter {
        @Override
        public void mouseClicked(MouseEvent e) {
            GUI.this.requestFocusInWindow();
        }
    }

    // starts the application
    public static void main(String[] args) {
        new GUI();
    }
}


