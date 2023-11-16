package ui;

import model.CarList;
import model.CarSettings;
import persistence.JsonReader;
import persistence.JsonWriter;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

// GUI class creates a running window with different functionalities for car shop application. Mainly with two panels:
// for sellers and buyers, also there is a visual component which you can see by pressing the button in buyer panel.
public class GUI extends JFrame {
    public static final int WIDTH = 1500;
    public static final int HEIGHT = 700;
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

    private JButton showImage = new JButton("SHOW IMAGE of DREAM CAR");

    private JTextField search = new JTextField("", 20);

    private JTextField textBrand = new JTextField("", 10);
    private JTextField textPrice = new JTextField("", 10);
    private JTextField textColor = new JTextField("", 10);
    private JTextField textCarYear = new JTextField("", 10);
    private JTextField textMileage = new JTextField("", 10);

    private CarList sortBy = new CarList();

    private JCheckBox carBrand = new JCheckBox("By car brand");
    private JCheckBox carPriceEqual = new JCheckBox("By same car price");
    private JCheckBox carPrice = new JCheckBox("By under and equal specific price");
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
        setContentPane(desk);
        setSize(WIDTH, HEIGHT);
        add(sellersSection()).setBounds(0, 0, WIDTH, HEIGHT / 2);
        add(usersSection()).setBounds(0, HEIGHT / 2, WIDTH, HEIGHT / 2);

        mainButtonSettings();

        setTitle("Car shop Application");


        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);

    }


    // MODIFIES: this
    // EFFECTS: creates panel for sellers
    public JPanel sellersSection() {
        JPanel panel = new JPanel();
        panel.setBackground(new java.awt.Color(225, 223, 223));
        panel.setForeground(Color.black);
        JLabel label = new JLabel("Car Sellers:  ");
        label.setFont(new Font("Calibre", Font.BOLD, 30));
        panel.add(label).setBounds(550, 0, 100, 50);


        panel.add(carBrandLabel);
        panel.add(textBrand);

        panel.add(priceLabel);
        panel.add(textPrice);

        panel.add(colorLabel);
        panel.add(textColor);

        panel.add(carYearLabel);
        panel.add(textCarYear);

        panel.add(mileageLabel);
        panel.add(textMileage);

        panel.add(addCar);
        panel.add(removeCar);

        panel.add(saveCars);
        panel.add(showAllCars);

        panel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));

        return panel;
    }


    // MODIFIES: this
    // EFFECTS: creates panel for users
    public JPanel usersSection() {
        JPanel panelU = new JPanel();
        panelU.setBackground(new java.awt.Color(207, 217, 255));
        panelU.setForeground(Color.black);
        JLabel label = new JLabel("Potential car buyers:  ");
        label.setFont(new Font("Calibre", Font.BOLD, 30));
        panelU.add(label).setBounds(750, 750, 100, 50);

        panelU.add(carBrand);
        panelU.add(carPriceEqual);
        panelU.add(carPrice);
        panelU.add(carColor);
        panelU.add(carYear);
        panelU.add(mileage);

        panelU.add(search);

        panelU.add(findCar);

        panelU.add(loadCars);

        panelU.add(showAllCars);

        panelU.add(showImage);

        panelU.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));

        return panelU;
    }

    // EFFECTS: set all the buttons to their functions
    public void mainButtonSettings() {
        findCar.addActionListener(new FindCar());

        addCar.addActionListener(new AddCar());

        removeCar.addActionListener(new RemoveCar());

        showAllCars.addActionListener(new ShowAllCars());

        saveCars.addActionListener((new SaveCars()));

        loadCars.addActionListener(new LoadCars());

        showImage.addActionListener(new CreateImage());
    }

    // MODIFIES: this
    // EFFECTS: creates an image
    private class CreateImage implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent evt) {
            if ("SHOW IMAGE of DREAM CAR".equals(evt.getActionCommand())) {
                JFrame imageFrame = new JFrame("Dream car (HOW TO EARN?)");
                imageFrame.setSize(1100, 600);
                imageFrame.setLocationRelativeTo(null);
                imageFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

                JLabel imageLabel = new JLabel();

                try {
                    Image img = ImageIO.read(new File("./data/carPic.jpg"));
                    ImageIcon imageIcon = new ImageIcon(img);
                    imageLabel.setIcon(imageIcon);
                    imageFrame.add(imageLabel);
                    imageFrame.setVisible(true);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
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

    // EFFECTS: check if the car (car settings) is valid for adding
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
                frame.setSize(350, 100);
                frame.setLocationRelativeTo(null);
                frame.setResizable(false);

                if (!addValidNewCar()) {
                    JLabel label = new JLabel("Mission incomplete! Car already exist");
                    label.setFont(new Font("Calibre", Font.BOLD, 17));
                    frame.add(label).setBounds(600, 300, 200, 40);
                } else {
                    JLabel label = new JLabel("Car is added!");
                    label.setFont(new Font("Calibre", Font.BOLD, 24));
                    frame.add(label).setBounds(600, 300, 80, 40);
                }

                frame.setResizable(true);
                frame.setVisible(true);
            }
        }
    }

    // EFFECTS: check if the car (car settings) is valid for removing
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
                frame.setSize(300, 100);
                frame.setLocationRelativeTo(null);
                frame.setResizable(false);

                if (!removeValidCar()) {
                    JLabel label = new JLabel("Mission incomplete!");
                    label.setFont(new Font("Calibre", Font.BOLD, 24));
                    frame.add(label).setBounds(600, 300, 80, 30);
                } else {
                    JLabel label = new JLabel("Car is removed!");
                    label.setFont(new Font("Calibre", Font.BOLD, 24));
                    frame.add(label).setBounds(600, 300, 80, 30);
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
                frame.setSize(300, 100);
                frame.setLocationRelativeTo(null);
                frame.setResizable(false);
                JLabel label = new JLabel("Cars are saved!");

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
                frame.setSize(450, 150);
                frame.setLocationRelativeTo(null);
                frame.setResizable(false);
                JLabel label = new JLabel("Cars are loaded from the file!");

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
        } else if (carPriceEqual.isSelected()) {
            doCarPriceEqual();
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
    // EFFECTS: search by same car price
    private void doCarPriceEqual() {
        int price = Integer.parseInt(search.getText());
        sortBy = carList.sameCarPrice(price);
    }


    // MODIFIES: this
    // EFFECTS: search by car price, equal and under
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

    // Make the mouse work on the desk
    private class DesktopFocusAction extends MouseAdapter {
        @Override
        public void mouseClicked(MouseEvent e) {
            GUI.this.requestFocusInWindow();
        }
    }

    // EFFECTS: starts the application
    public static void main(String[] args) {
        new GUI();
    }
}


