package ui;

import model.WareHouse;
import model.Item;
import persistence.JsonReader;
import persistence.JsonWriter;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

// Warehouse Management System
// This class uses a template created by CPSC210
// (https://github.students.cs.ubc.ca/CPSC210/TellerApp).
// The original code has been modified for our specific use case.
public class WarehouseManagementSystem {
    private static final String JSON_STORE = "./data/warehouse.json";
    private static final String MY_USERNAME = "Daniel";
    private static final String MY_COMPANY_NAME = "Daniel's Pipe Shop";
    private static final String MY_PHONE_NUM = "7789295227";
    private static final String MY_COMPANY_ADDRESS = "The address of Dongyong "
            + "Plastic Pipe Fittings Business Department, "
            + "Jinniu District, Chengdu City is located at No.85, No.5 Parts, "
            + "Fumechanical City, Jinniu District, Chengdu City, Sichuan Province"
            + "(postcode: 610000).";

    private Scanner input;
    private WareHouse wareHouse;
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;

    // EFFECTS: runs the Warehouse Management System
    public WarehouseManagementSystem() {
        init();
        runWarehouseManagementSystem();
    }

    // MODIFIES: this
    // EFFECTS: initializes WarehouseSystem
    private void init() {
        input = new Scanner(System.in);
        input.useDelimiter("\n");
        wareHouse = new WareHouse(MY_USERNAME + "'s Warehouse");
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);
    }


    // MODIFIES: this
    // EFFECTS: processes user input in main menu
    public void runWarehouseManagementSystem() {
        String command = null;

        displayMenu();
        System.out.print("Enter your choice: ");
        command = input.nextLine();
        command = command.toLowerCase();
        processCommand(command);
    }


    // MODIFIES: this
    // EFFECTS: processes user command
    private void processCommand(String command) {
        if (command.equals("1")) {
            basicInformation();
        } else if (command.equals("2")) {
            storageRecord();
        } else if (command.equals("3")) {
            companyInformation();
        } else if (command.equals("4")) {
            saveWareHouse();
            runWarehouseManagementSystem();
        } else if (command.equals("5")) {
            loadWarehouse();
            basicInformation();
        } else if (command.equals("q")) {
            System.out.println("\nGoodbye!");
        } else {
            System.out.println("Selection not valid...");
            runWarehouseManagementSystem();
        }
    }

    // EFFECTS: displays menu of options to user in main menu
    private void displayMenu() {
        System.out.println("\nWarehouse Management System:");
        System.out.println("\t1. -> Basic Information");
        System.out.println("\t2. -> Storage Record");
        System.out.println("\t3. -> Company Information");
        System.out.println("\t4. -> save warehouse to file");
        System.out.println("\t5. -> load warehouse from file");
        System.out.println("\tq. -> quit");
    }


    // MODIFIES: this
    // EFFECTS: Calculates and shows the percentage of each item
    // of the total quantity of items.
    private void basicInformation() {
        int totalQuantity = 0;
        for (Item item : wareHouse.getItems()) {
            totalQuantity += item.getQuantity();
        }
        System.out.println("Name; Quantity; Percentage; Location");
        for (Item item : wareHouse.getItems()) {
            System.out.println(item.getName()
                    + "; " + item.getQuantity()
                    + "; " + Math.round(100.0 * item.getQuantity() / totalQuantity)
                    + "%" + "; " + item.getLocation());
        }
        runWarehouseManagementSystem();
    }

    // EFFECTS: runs the Storage Record Menu
    private void storageRecord() {
        runStorageRecord();
    }

    // EFFECTS: displays menu of options to user in storage record
    private void storageRecordDisplayMenu() {
        System.out.println("\nStorage Record:");
        System.out.println("\t1. -> Add Item");
        System.out.println("\t2. -> Remove Item");
        System.out.println("\t3. -> View All Items");
        System.out.println("\t4. -> Check Storage Record");
        System.out.println("\tq. -> Back To Main Menu");
    }

    // MODIFIES: this
    // EFFECTS: processes user input in storage record menu
    private void runStorageRecord() {
        String command = null;

        storageRecordDisplayMenu();
        System.out.print("Enter your choice: ");
        command = input.nextLine();
        command = command.toLowerCase();

        storageRecordCommand(command);
    }

    // MODIFIES: this
    // EFFECTS: processes storageRecord command
    private void storageRecordCommand(String command2) {
        if (command2.equals("1")) {
            addItem();
        } else if (command2.equals("2")) {
            removeItem();
        } else if (command2.equals("3")) {
            viewAllItem();
        } else if (command2.equals("4")) {
            checkStorageRecord();
        } else if (command2.equals("q")) {
            runWarehouseManagementSystem();
        } else {
            System.out.println("Selection not valid...");
            runStorageRecord();
        }
    }

    // MODIFIES: this
    // EFFECTS: Add item to the warehouse. If the item with the same name,
    // and same location, add quantity to the original one, else add new item.
    private void addItem() {
        System.out.print("Enter item name: ");
        String name = input.nextLine();
        System.out.print("Enter quantity: ");
        int quantity = Integer.parseInt(input.nextLine());
        System.out.print("Enter storage location: ");
        String location = input.nextLine();

        Item item = new Item(name, quantity, location);
        Item itemRecord = new Item(name, quantity, "+");

        if (findOrNot(name, location)) {
            addToTheOriginalOne(name, quantity, location);
        } else {
            wareHouse.addItem(item);
            System.out.println(quantity + " " + name + " added to the warehouse in area " + location + ".");
        }
        wareHouse.addItemRecord(itemRecord);
        runStorageRecord();
    }

    // MODIFIES: this
    // EFFECTS: Adds the given quantity of the item with the given name and location in the warehouse,
    // which the item is already in the warehouse.
    public void addToTheOriginalOne(String name, int quantity, String location) {
        for (Item itemInWareHouse : wareHouse.getItems()) {
            if (itemInWareHouse.getName().equals(name)
                    && itemInWareHouse.getLocation().equals(location)) {
                itemInWareHouse.addQuantity(quantity);
                System.out.println(quantity + " " + name + " added to the warehouse in area " + location + ".");
                System.out.println("Now you have " + itemInWareHouse.getQuantity() + " in total.");
            }
        }
    }

    // MODIFIES: this
    // EFFECTS: Decrease the quantity to the item in warehouse with the given quantity.
    // If there is no more that item, remove it from the list.
    public void removeItem() {
        System.out.print("Enter item name: ");
        String name = input.nextLine();
        System.out.print("Enter quantity: ");
        int quantity = Integer.parseInt(input.nextLine());
        System.out.print("Enter storage location: ");
        String location = input.nextLine();

        if (findOrNot(name, location)) {
            reduceOrRemoveItem(name, quantity, location);
            runStorageRecord();
        } else {
            System.out.println("There is no " + name + " in our warehouse. Please try again!");
            runStorageRecord();
        }
    }

    // MODIFIES: this
    // EFFECTS: Determines if there is an item in the warehouse with the given name and location.
    // Returns "true" if found, "false" otherwise.
    private boolean findOrNot(String name,String location) {
        for (Item itemInWareHouse : wareHouse.getItems()) {
            if (itemInWareHouse.getName().equals(name)
                    && itemInWareHouse.getLocation().equals(location)) {
                return true;
            }
        }
        return false;
    }

    // MODIFIES: this
    // EFFECTS: Decrease the quantity to the item in warehouse with the given quantity.
    // If there is no more that item, remove it from the list.
    private void reduceOrRemoveItem(String name, int quantity, String location) {
        Item itemRecord = new Item(name, quantity, "-");

        for (int i = 0; i < wareHouse.numItems(); i++) {
            Item itemInWareHouse = wareHouse.getItems().get(i);
            if (itemInWareHouse.getName().equals(name)
                    && itemInWareHouse.getLocation().equals(location)) {
                if (itemInWareHouse.isNoMore(quantity)) {
                    wareHouse.removeItem(i);
                    wareHouse.addItemRecord(itemRecord);
                    System.out.println("You already removed all " + name + " in the warehouse.");
                } else if (itemInWareHouse.isEnough(quantity)) {
                    itemInWareHouse.reduceQuantity(quantity);
                    wareHouse.addItemRecord(itemRecord);
                    System.out.println("Reduce " + quantity + " of " + name + " in " + location + " to "
                            + itemInWareHouse.getQuantity() + ".");
                } else {
                    System.out.println("We only have " + itemInWareHouse.getQuantity() + " " + name
                            + " in the warehouse. " + "Please try again!");
                }
            }
        }
    }

    // MODIFIES: this
    // EFFECTS: Shows all the items in the warehouse with name, quantity, and location.
    private void viewAllItem() {
        System.out.println("Name; Quantity; Location");
        for (Item item : wareHouse.getItems()) {
            System.out.println(item.getName()
                    + "; " + item.getQuantity()
                    + "; " + item.getLocation());
        }
        runStorageRecord();
    }

    // MODIFIES: this
    // EFFECTS: Shows all the storage recorde in the warehouse
    // with name, quantity, and remove or add.
    private void checkStorageRecord() {
        System.out.println("Name; Quantity; Add or Remove");
        for (Item item : wareHouse.getItemRecords()) {
            System.out.println(item.getName()
                    + "; " + item.getQuantity()
                    + "; " + item.getLocation());
        }
        runStorageRecord();
    }


    // MODIFIES: this
    // EFFECTS: Present the basic information of the company
    private void companyInformation() {
        System.out.println(MY_COMPANY_NAME);
        System.out.println(MY_USERNAME + ":" + MY_PHONE_NUM);
        System.out.println("Company address:" + MY_COMPANY_ADDRESS);
        runWarehouseManagementSystem();
    }

    // EFFECTS: saves the warehouse to file
    private void saveWareHouse() {
        try {
            jsonWriter.open();
            jsonWriter.write(wareHouse);
            jsonWriter.close();
            System.out.println("--------------------------------"
                    + "---------------------");
            System.out.println("Saved " + wareHouse.getName() + " to " + JSON_STORE);
            System.out.println("--------------------------------"
                    + "---------------------");
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file: " + JSON_STORE);
        }
    }

    // MODIFIES: this
    // EFFECTS: loads warehouse from file
    private void loadWarehouse() {
        try {
            wareHouse = jsonReader.read();
            System.out.println("--------------------------------"
                    + "---------------------");
            System.out.println("Loaded " + wareHouse.getName() + " from " + JSON_STORE);
            System.out.println("--------------------------------"
                    + "---------------------");
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + JSON_STORE);
        }
    }
}