package ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Scanner;

// Login System
public class LoginSystem {
    private static final String MY_USERNAME = "Daniel";
    private static final String MY_PASSWORD = "123";

    private Scanner input;
    private String username;
    private String password;



    // EFFECTS: runs the Login System
    public LoginSystem() {
        runLoginSystem();
    }

    // MODIFIES: this
    // EFFECTS: initializes LogInSystem
    private void init() {
        input = new Scanner(System.in);
        input.useDelimiter("\n");
    }

    // MODIFIES: this
    // EFFECTS: processes user login
    public void runLoginSystem() {
        init();

        System.out.print("Enter username: ");
        username = input.nextLine();
        System.out.print("Enter password: ");
        password = input.nextLine();
        if (verifyLogin(username, password)) {
            System.out.println("Login successful.");
            System.out.println("--------------------------------"
                    + "---------------------");
        } else {
            System.out.println("Incorrect username or password!");
            System.out.println("Try it again!");
            runLoginSystem();
        }
    }

    // MODIFIES: this
    // EFFECT: Check if username and password are correct
    boolean verifyLogin(String username, String password) {
        if (username.equals(MY_USERNAME) && password.equals(MY_PASSWORD)) {
            return true;
        } else {
            return false;
        }
    }
}
