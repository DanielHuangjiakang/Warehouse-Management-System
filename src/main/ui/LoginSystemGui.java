package ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// Login System Gui
// Reference:
// UserName Field and Password Field:
// https://docs.oracle.com/javase/tutorial/uiswing/examples/zipfiles/components-PasswordDemoProject.zip
// Login Button:
// https://stackoverflow.com/questions/6578205/swing-jlabel-text-change-on-the-running-application
// WareHouse Logo:
// https://www.designcrowd.com/design/21668220
// UBC Logo:
// https://www.treehousemedia.ca/our-work/attachment/ubc-logo/
// Display Gif:
// https://giphy.com
// Color:
// https://tool.oschina.net/commons?type=3
public class LoginSystemGui {
    // Constant
    private static final String MY_USERNAME = "Daniel";
    private static final String MY_PASSWORD = "123";
    private Color myBlue = new Color(30, 120, 230);
    private Color mySubTitleColor = new Color(176,226,255);
    private ImageIcon warehouseLogo = new ImageIcon("/Applications/project_t4h5d/data/warehouse.png");
    private ImageIcon ubcLogo = new ImageIcon("/Applications/project_t4h5d/data/ubc-logo.png");
    private ImageIcon displayGif = new ImageIcon("/Applications/project_t4h5d/data/displayPicture.gif");
    private ImageIcon resizedUbcLogo = resizeImageIcon(ubcLogo, 90, 70);
    private ImageIcon warehouseLogoIcon = resizeImageIcon(warehouseLogo, 50, 50);

    // Field of frame
    protected static JFrame loginFrame;
    private JPanel containerPanel;

    // Field of displayPanel
    private JPanel displayPanel;
    private JLabel gifLabel;
    private JLabel ubcLogoLabel;

    // Field of loginPanel
    private JPanel loginPanel;
    private JPanel titlePanel;
    private JPanel subTitlePanel;
    private JLabel usernameLabel;
    private JLabel passwordLabel;
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JPanel loginButtonPanel;
    private JButton loginButton;
    private JLabel logoLabel;
    private JPanel incorrectPanel;
    private JLabel incorrectLabel;


    // EFFECTS: runs the Login System
    public LoginSystemGui() {
        runLoginSystemGui();
    }

    // MODIFIES: this
    // EFFECTS: initializes LogInSystem Gui
    private void init() {
        loginFrame = new JFrame();
        displayInit();
        loginInit();
        containerPanel = new JPanel();
    }

    // MODIFIES: this
    // EFFECTS: initializes display panel
    private void displayInit() {
        displayPanel = new JPanel();
        gifLabel = new JLabel(displayGif);
        displayPanel.setPreferredSize(new Dimension(600, 500));
        displayPanel.setBackground(Color.WHITE);
        ubcLogoLabel = new JLabel(resizedUbcLogo);
    }

    // MODIFIES: this
    // EFFECTS: initializes login panel
    private void loginInit() {
        loginPanel = new JPanel();
        loginPanel.setBackground(myBlue);
        loginPanel.setPreferredSize(new Dimension(400, 500));
        loginPanel.setLayout(new GridLayout(10,1));

        titlePanel = new JPanel(new GridLayout(1, 3));
        titlePanel.setBackground(myBlue);

        subTitlePanel = new JPanel(new GridLayout(1, 3));
        subTitlePanel.setBackground(myBlue);

        usernameLabel = new JLabel("Username :");
        passwordLabel = new JLabel("Password :");
        usernameField = new JTextField();
        passwordField = new JPasswordField();

        loginButtonPanel = new JPanel(new GridLayout(2, 3));
        loginButton = new JButton();
        logoLabel = new JLabel(warehouseLogoIcon);
        incorrectPanel = new JPanel();
        incorrectLabel = new JLabel();
    }

    // MODIFIES: this
    // EFFECTS: processes user login Gui
    public void runLoginSystemGui() {
        init();

        // initializes displayPanel and loginPanel
        displayPanel();
        loginPanel();

        // initializes the frame
        frameInit();

        // Add action listener to login button
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = usernameField.getText();
                String password = new String(passwordField.getPassword());
                if (verifyLogin(username, password)) {
                    incorrectPanel.setVisible(false);
                    // JOptionPane.showMessageDialog(null, "Login successful.");
                    loginFrame.dispose();
                    new WarehouseManagementSystemGui();
                } else {
                    passwordField.setText("");
                    incorrectPanel.setVisible(true);
                }
            }
        });
    }

    // MODIFIES: this
    // EFFECTS: initializes frame of login Gui
    private void frameInit() {
        containerPanel.add(displayPanel);
        containerPanel.add(loginPanel);

        loginFrame.add(containerPanel);
        loginFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        loginFrame.setVisible(true); // Make the frame visible
        loginFrame.setResizable(false); // Make the frame non-resizable
        loginFrame.pack();
        loginFrame.setLocationRelativeTo(null); // make it in the center of the computer
    }

    // MODIFIES: this
    // EFFECTS: initializes displayPanel of login Gui
    public void displayPanel() {
        // empty panel
        Panel emptyPanel = new Panel();
        emptyPanel.setPreferredSize(new Dimension(600, 50));

        // Add the JLabel to the display panel
        displayPanel.add(emptyPanel);
        displayPanel.add(gifLabel);
        displayPanel.add(ubcLogoLabel);
    }

    public void loginPanel() {
        createTitle();
        createSubTitle();
        createInputUserPassword();
        createLoginButton();
        createIncorrectRemind();

        loginPanel.add(new Panel());
        loginPanel.add(titlePanel);
        loginPanel.add(subTitlePanel);
        loginPanel.add(usernameLabel);
        loginPanel.add(usernameField);
        loginPanel.add(passwordLabel);
        loginPanel.add(passwordField);
        loginPanel.add(loginButtonPanel);
        loginPanel.add(logoLabel);
        loginPanel.add(incorrectPanel);
        incorrectPanel.setVisible(false);
    }

    // MODIFIES: this
    // EFFECTS: initializes the Title of the login system
    private void createTitle() {
        JLabel titleLabel = new JLabel("Login");
        titleLabel.setFont(new Font("Cordia New", Font.PLAIN, 43));
        titleLabel.setForeground(Color.white);

        titlePanel.add(new Panel());
        titlePanel.add(titleLabel);
        titlePanel.add(new Panel());
    }

    // MODIFIES: this
    // EFFECTS: initializes the subTitle of the login system
    private void createSubTitle() {
        JLabel subTitleLabel = new JLabel("Optimize Your Warehouse!");
        subTitleLabel.setFont(new Font("Cordia New", Font.PLAIN, 10));
        subTitleLabel.setForeground(mySubTitleColor);
        subTitlePanel.add(new Panel());
        subTitlePanel.add(subTitleLabel);
        subTitlePanel.add(new Panel());
    }

    // MODIFIES: this
    // EFFECTS: initializes the place to input username and password of the login system
    private void createInputUserPassword() {
        // The usernameLabel
        usernameLabel.setFont(new Font("Cordia New", Font.PLAIN, 20));
        usernameLabel.setForeground(Color.WHITE);

        // UsernameField
        usernameField = new JTextField(1);
        usernameField.setFont(new Font("Cordia New", Font.PLAIN, 15));


        // The passwordLabel
        passwordLabel.setFont(new Font("Cordia New", Font.PLAIN, 20));
        passwordLabel.setForeground(Color.WHITE);

        // passwordField
        passwordField = new JPasswordField(1);
        passwordField.setFont(new Font("Cordia New", Font.PLAIN, 15));
    }

    // MODIFIES: this
    // EFFECTS: initializes login button of the login system
    private void createLoginButton() {
        // Add login button to login panel
        loginButton = new JButton("LOGIN");
        loginButton.setForeground(myBlue);
        loginButton.setBackground(Color.white);
        loginButton.setFont(new Font("Cordia New", Font.BOLD, 20));
        loginButtonPanel.setBackground(myBlue);
        loginButtonPanel.add(new Panel());
        loginButtonPanel.add(new Panel());
        loginButtonPanel.add(new Panel());
        loginButtonPanel.add(new Panel());
        loginButtonPanel.add(loginButton);
        loginButtonPanel.add(new Panel());
    }

    // incorrect remind panel
    private void createIncorrectRemind() {
        incorrectLabel = new JLabel("Incorrect username or password! Try again!");
        incorrectLabel.setFont(new Font("Cordia New", Font.PLAIN, 12));
        incorrectLabel.setForeground(Color.red);
        incorrectPanel.setBackground(myBlue);
        incorrectPanel.add(incorrectLabel);
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

    // MODIFIES: this
    // EFFECT: Produce the resized imageIcon.
    public ImageIcon resizeImageIcon(ImageIcon imageIcon, int width, int height) {
        return  new ImageIcon(imageIcon.getImage().getScaledInstance(
                width, height, Image.SCALE_SMOOTH));
    }
}
