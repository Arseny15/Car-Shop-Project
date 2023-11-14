package ui;

import model.CarList;
import model.CarSettings;
import persistence.JsonReader;
import persistence.JsonWriter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.FileNotFoundException;
import java.io.IOException;


public class GUI extends JFrame {
    public static final int WIDTH = 1500;
    public static final int HEIGHT = 1500;
    private JDesktopPane desk;

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
    private JButton saveCars = new JButton("Save cars to the file");
    private JButton loadCars = new JButton("Load cars from the file");

    private JTextField search = new JTextField("", 20);

    private JTextField textBrand = new JTextField("", 10);
    private JTextField textPrice = new JTextField("", 10);
    private JTextField textColor = new JTextField("", 10);
    private JTextField textCarYear = new JTextField("", 10);
    private JTextField textMileage = new JTextField("", 10);

    private CarList sortBy = new CarList();

    private JCheckBox carBrand = new JCheckBox("By car brand");
    private JCheckBox carPrice = new JCheckBox("By price");
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

        mainButtonSettings();

        setContentPane(desk);
        setTitle("Car shop Application");
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
        JLabel label = new JLabel("Car Sellers:  ");
        label.setFont(new Font("Calibre", Font.BOLD, 30));
        panel.add(label).setBounds(550, 0, 100, 50);


        panel.add(carBrandLabel).setBounds(100, 70, 160, 20);
        panel.add(textBrand).setBounds(140, 70, 160, 20);

        panel.add(priceLabel).setBounds(180, 100, 160, 20);
        panel.add(textPrice).setBounds(220, 100, 160, 20);

        panel.add(colorLabel).setBounds(260, 130, 160, 20);
        panel.add(textColor).setBounds(280, 130, 160, 20);

        panel.add(carYearLabel).setBounds(300, 160, 160, 20);
        panel.add(textCarYear).setBounds(320, 160, 160, 20);

        panel.add(mileageLabel).setBounds(340, 190, 160, 20);
        panel.add(textMileage).setBounds(360, 190, 160, 20);

        panel.add(addCar).setBounds(380, 220, 160, 30);
        panel.add(removeCar).setBounds(400, 250, 160, 30);

        panel.add(saveCars).setBounds(480, 400, 100, 50);
//        panel.add(loadCars).setBounds(540, 440, 100, 50);

        panel.add(showAllCars).setBounds(500, 420, 100, 50);

        return panel;
    }

    // MODIFIES: this
    // EFFECTS: creates panel for users
    public JPanel usersSection() {
        JPanel panelU = new JPanel();
        panelU.setBackground(new java.awt.Color(139, 159, 232));
        panelU.setForeground(Color.black);
        JLabel label = new JLabel("Potential car buyers:  ");
        label.setFont(new Font("Calibre", Font.BOLD, 30));
        panelU.add(label).setBounds(750, 750, 100, 50);

        panelU.add(carBrand).setBounds(440, 270, 100, 20);
        panelU.add(carPrice).setBounds(440, 270, 100, 20);
        panelU.add(carColor).setBounds(440, 270, 100, 20);
        panelU.add(carYear).setBounds(440, 270, 100, 20);
        panelU.add(mileage).setBounds(440, 270, 100, 20);

        panelU.add(search).setBounds(460, 300, 160, 30);

        panelU.add(findCar).setBounds(480, 400, 100, 50);

        panelU.add(loadCars).setBounds(420, 230, 100, 50);

        panelU.add(showAllCars).setBounds(450, 260, 100, 50);

        return panelU;
    }

    public void mainButtonSettings() {
        findCar.setActionCommand("Find car");
        findCar.addActionListener(new FindCar());

        addCar.setActionCommand("Add car for sale");
        addCar.addActionListener(new AddCar());

        removeCar.setActionCommand("Remove car from sale list");
        removeCar.addActionListener(new RemoveCar());

        showAllCars.setActionCommand("Show list of all cars");
        showAllCars.addActionListener(new ShowAllCars());

        saveCars.setActionCommand("Save cars to the file");
        saveCars.addActionListener((new SaveCars()));

        loadCars.setActionCommand("Load cars from the file");
        loadCars.addActionListener(new LoadCars());
    }


    // MODIFIES: this
    // EFFECTS: shows all cars in the list
    private class ShowAllCars implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent evt) {
            if ("Show list of all cars".equals(evt.getActionCommand())) {
                JFrame frame = new JFrame("All cars available: ");
                frame.setSize(850, 300);
                frame.setLocationRelativeTo(null);
                frame.setResizable(false);

                DefaultListModel<String> listModel = new DefaultListModel<>();

                for (CarSettings car : carList.getCars()) {
                    listModel.addElement(searchResult(car));
                }

                JList<String> jlist = new JList<>(listModel);

                JScrollPane scrollPane = new JScrollPane(jlist);
                scrollPane.setPreferredSize(new Dimension(200, 100));

                frame.add(scrollPane);

                frame.setVisible(true);
            }
        }
    }


    // MODIFIES: this
    // EFFECTS: finds car by different settings, no duplicates
    private class FindCar implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent evt) {
            if ("Find car".equals(evt.getActionCommand())) {
                JFrame frame = new JFrame("Search Result of Car Data: ");
                frame.setSize(850, 300);
                frame.setLocationRelativeTo(null);
                frame.setResizable(false);

                processCommandSearch();

                DefaultListModel<String> listModel = new DefaultListModel<>();

                for (CarSettings car : sortBy.getCars()) {
                    listModel.addElement(searchResult(car));
                }

                JList<String> jlist = new JList<>(listModel);

                JScrollPane scrollPane = new JScrollPane(jlist);
                scrollPane.setPreferredSize(new Dimension(200, 100));

                frame.add(scrollPane);

                frame.setVisible(true);
            }
        }
    }

    public boolean addValidNewCar() {
        CarSettings car;
        String carBrand = textBrand.getText();
        int price = Integer.parseInt(textPrice.getText());
        String color = textColor.getText();
        int year = Integer.parseInt(textCarYear.getText());
        int mileage = Integer.parseInt(textMileage.getText());

        if (carList.carExists(carBrand, price, color, year, mileage)) {
            return false;
        }

        car = new CarSettings(carBrand, price, color, year, mileage);
        carList.addCarToList(car);
        return true;
    }



    // MODIFIES: this
    // EFFECTS: adds a new car to sale list
    private class AddCar implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent evt) {
            if ("Add car for sale".equals(evt.getActionCommand())) {
                JFrame frame = new JFrame("Result: ");
                frame.setSize(300, 100);
                frame.setLocationRelativeTo(null);
                frame.setResizable(false);

                if (!addValidNewCar()) {
                    JLabel label = new JLabel("Mission incomplete! Car already exist");
                    label.setFont(new Font("Calibre", Font.BOLD, 24));
                    frame.add(label).setBounds(600, 300, 150, 40);
                } else {
                    JLabel label = new JLabel("Mission completed!");
                    label.setFont(new Font("Calibre", Font.BOLD, 24));
                    frame.add(label).setBounds(600, 300, 80, 40);
                }

                frame.setResizable(true);
                frame.setVisible(true);
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
    private class RemoveCar implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent evt) {
            if ("Remove car from sale list".equals(evt.getActionCommand())) {
                JFrame frame = new JFrame("Result: ");
                frame.setSize(300, 150);
                frame.setLocationRelativeTo(null);
                frame.setResizable(false);

                if (!removeValidCar()) {
                    JLabel label = new JLabel("Mission incomplete!");
                    label.setFont(new Font("Calibre", Font.BOLD, 24));
                    frame.add(label).setBounds(600, 300, 80, 40);
                } else {
                    JLabel label = new JLabel("Mission completed!");
                    label.setFont(new Font("Calibre", Font.BOLD, 24));
                    frame.add(label).setBounds(600, 300, 80, 40);
                }
                frame.setResizable(true);
                frame.setVisible(true);
            }
        }
    }


    // MODIFIES: this
    // EFFECTS: save newly added cars to the file
    private class SaveCars implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent evt) {
            if ("Save cars to the file".equals(evt.getActionCommand())) {
                JFrame frame = new JFrame("Result: ");
                frame.setSize(300, 150);
                frame.setLocationRelativeTo(null);
                frame.setResizable(false);
                JLabel label = new JLabel("Mission completed!");

                try {
                    jsonWriter.open();
                    jsonWriter.write(carList);
                    jsonWriter.close();
                    System.out.println("Saved car list to " + JSON_STORE);
                } catch (FileNotFoundException e) {
                    System.out.println("Unable to write to file: " + JSON_STORE);
                }
                label.setFont(new Font("Calibre", Font.BOLD, 24));
                frame.add(label).setBounds(600, 300, 80, 40);
                frame.setResizable(true);
                frame.setVisible(true);
            }
        }
    }

    // MODIFIES: this
    // EFFECTS: load cars from the file
    private class LoadCars implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent evt) {
            if ("Load cars from the file".equals(evt.getActionCommand())) {
                JFrame frame = new JFrame("Result: ");
                frame.setSize(300, 150);
                frame.setLocationRelativeTo(null);
                frame.setResizable(false);
                JLabel label = new JLabel("Mission completed!");

                try {
                    carList = jsonReader.read();
                    System.out.println("Loaded car list from " + JSON_STORE);
                } catch (IOException e) {
                    System.out.println("Unable to read from file: " + JSON_STORE);
                }
                label.setFont(new Font("Calibre", Font.BOLD, 24));
                frame.add(label).setBounds(600, 300, 80, 40);
                frame.setResizable(true);
                frame.setVisible(true);
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
        sortBy = carList.sameCarBrand(search.getText());
    }

    // MODIFIES: this
    // EFFECTS: search by car price
    private void doCarPrice() {
        int price = Integer.parseInt(search.getText());
        sortBy = carList.underCarPrice(price);
    }

    // MODIFIES: this
    // EFFECTS: search by car color
    private void doCarColor() {
        sortBy = carList.sameCarColor(search.getText());
    }

    // MODIFIES: this
    // EFFECTS: search by car year
    private void doYear() {
        int year = Integer.parseInt(search.getText());
        sortBy = carList.carsWithGivenYear(year);
    }

    // MODIFIES: this
    // EFFECTS: search by mileage
    private void doMileage() {
        int mileage = Integer.parseInt(search.getText());
        sortBy = carList.underKmUse(mileage);
    }

    // EFFECTS: show search result
    private String searchResult(CarSettings car) {
        return ("The brand of car: " + car.getCarBrand() + "   The price of car: "
                + car.getPrice() + "   The color of car: " + car.getColor() + "   The year of car: "
                + car.getCarYear() + "   The mileage of car: " + car.getKmUsed());
    }


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


