package ui;

import model.Event;
import model.EventLog;
import model.Item;
import model.WareHouse;
import persistence.JsonReader;
import persistence.JsonWriter;
import ui.histogram.HistogramPanel;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

// Warehouse Management System Gui
// Reference:
// Load or not Frame:
// https://www.tutorialspoint.com/swingexamples/show_confirm_dialog_with_yesno.htm
// SplitDemo2Project:
// https://docs.oracle.com/javase/tutorial/uiswing/examples/components/index.html
// Scroll Panel(ScrollDemo):
// https://docs.oracle.com/javase/tutorial/uiswing/examples/components/index.html
// Bar Chart:
// https://stackoverflow.com/questions/29708147/custom-graph-java-swing
// Image Reference:
// https://www.flaticon.com
// Color:
// https://tool.oschina.net/commons?type=3
public class WarehouseManagementSystemGui extends JFrame {
    // Constant
    private static final String JSON_STORE = "./data/warehouse.json";
    private static final String MY_USERNAME = "Daniel";
    private Color myBlue = new Color(30, 120, 230);
    private ImageIcon  dashBoardLogo = new ImageIcon("/Applications/project_t4h5d/data/DashboardLogo.png");
    private ImageIcon  productsLogo = new ImageIcon("/Applications/project_t4h5d/data/productsLogo.png");
    private ImageIcon  storageRecordLogo = new ImageIcon("/Applications/project_t4h5d/data/StorageLogo.png");
    private ImageIcon  userInformationLogo = new ImageIcon(
            "/Applications/project_t4h5d/data/userInfologo.png");
    private ImageIcon warehouseLogo = new ImageIcon("/Applications/project_t4h5d/data/warehouse.png");
    private ImageIcon userLogo = new ImageIcon("/Applications/project_t4h5d/data/user.png");
    private ImageIcon  companyInfo = new ImageIcon("/Applications/project_t4h5d/data/companyInfo.png");
    private ImageIcon  searchLogo = new ImageIcon("/Applications/project_t4h5d/data/search.png");
    private ImageIcon warehouseLogoIcon = resizeImageIcon(warehouseLogo, 50, 50);
    private ImageIcon resizedDashBoardLogo = resizeImageIcon(dashBoardLogo, 110, 95);
    private ImageIcon resizedProductsLogo = resizeImageIcon(productsLogo, 110, 95);
    private ImageIcon resizedStorageRecordLogo = resizeImageIcon(storageRecordLogo, 110, 95);
    private ImageIcon resizedUserInformationLogo = resizeImageIcon(userInformationLogo, 110, 95);
    private ImageIcon resizedUserLogo = resizeImageIcon(userLogo,40, 40);
    private ImageIcon resizedCompanyImage = resizeImageIcon(companyInfo,581, 625);
    private ImageIcon resizedSearchLogo = resizeImageIcon(searchLogo,40, 40);


    // Field of JsonPackage(database)
    private WareHouse wareHouse;
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;

    // Field of Frame
    protected static JFrame loadOrNotFrame;
    protected static JFrame warehouseFrame;

    // Field of loadOrNotPanel
    JPanel loadOrNotPanel;
    JLabel loadOrNotLabel;
    JPanel buttonPanel;
    JButton yesButton;
    JButton noButton;

    // Field of warehouse system option Panel
    JPanel optionPanel;

    // Field of warehouse display Panel
    JPanel containerPanel;
    JPanel dashBoardPanel;
    JPanel storageRecordPanel;
    JPanel productsPanel;
    JPanel userInfoPanel;

    // Field of option Button
    JButton dashBoardButton;
    JButton productsButton;
    JButton storageRecordButton;
    JButton userInfoButton;
    JLabel ubcLogoLabel;

    // Field of display Panel
    JPanel detailDisplayPanel;
    JPanel emptyPanel;
    JLabel userNameLabel;
    JLabel userLogoLabel;
    JPanel whitePanel;
    JPanel graphPanel;
    HistogramPanel histogramPanel;
    JButton addButton;
    JButton removeButton;
    JTextField searchItemField;
    JButton searchButton;
    JPanel searchPanel;
    JFrame itemInputFrame;
    JButton submitButton;
    JTextField nameField;
    JTextField quantityField;
    JTextField locationField;
    JPanel itemListPanel;
    List<String> percentageList;
    JPanel productsRecordsPanel;
    JButton submitRemoveButton;
    JFrame savOrNotFrame;
    JPanel saveOrNotPanel;
    JLabel saveOrNotLabel;
    JPanel saveButtonPanel;
    JButton saveYesButton;
    JButton saveNoButton;
    JPanel itemRecordListRecordsPanel;
    JPanel itemRecordTitlePanel;
    JPanel itemRecordListPanel;
    JPanel itemRecordMailPanel;
    JPanel itemMailPanel;
    JPanel userInformationPanel;


    // EFFECTS: runs the Login System
    public WarehouseManagementSystemGui() {
        runLoadOrNot();
    }

    // EFFECTS: runs the LoadOrNot Panel GUI
    private void runLoadOrNot() {
        jsonInit();
        loadOrNotInit();
        loadOrNotPanel.add(loadOrNotLabel);
        loadOrNotPanel.add(buttonPanel);

        // user selects Yes
        yesButton.addActionListener(e -> {
            loadWarehouse();
            loadOrNotFrame.dispose();
            runWarehouseManagementGui();
        });

        // user selects No
        noButton.addActionListener(e -> {
            loadOrNotFrame.dispose();
            runWarehouseManagementGui();
        });

        buttonPanel.add(yesButton);
        buttonPanel.add(noButton);
        loadOrNotFrame.setContentPane(loadOrNotPanel);
        loadOrNotFrame.setVisible(true);
    }

    // MODIFIES: this
    // EFFECTS: initializes Json Package
    private void jsonInit() {
        wareHouse = new WareHouse(MY_USERNAME + "s Warehouse");
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);
    }

    // MODIFIES: this
    // EFFECTS: initializes load or not panel
    private void loadOrNotInit() {
        // loadOrNotFrame init
        loadOrNotFrame = new JFrame("Load Database Data");
        loadOrNotFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        loadOrNotFrame.setBackground(myBlue);
        loadOrNotFrame.setSize(400, 200);
        loadOrNotFrame.setResizable(false); // Make the frame non-resizable
        loadOrNotFrame.setLocationRelativeTo(null); // make it in the center of the computer

        // loadOrNotPanel init
        loadOrNotPanel = new JPanel(new GridLayout(2, 1));
        loadOrNotPanel.setBackground(myBlue);

        // loadOrNotLabel init
        loadOrNotLabel = new JLabel("Do you want to load data from the database?");
        loadOrNotLabel.setFont(new Font("Cordia New", Font.BOLD, 15));
        loadOrNotLabel.setHorizontalAlignment(SwingConstants.CENTER);

        // buttonPanel init
        buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        buttonPanel.setBackground(myBlue);
        yesButton = new JButton("Yes");
        yesButton.setFont(new Font("Cordia New", Font.PLAIN, 13));
        noButton = new JButton("No");
        noButton.setFont(new Font("Cordia New", Font.PLAIN, 13));
    }

    // EFFECTS: runs the WarehouseManagement GUI
    public void runWarehouseManagementGui() {
        init();
    }

    // MODIFIES: this
    // EFFECTS: initializes WarehouseManagement Gui
    private void init() {
        warehouseFrame = new JFrame();
        containerPanel = new JPanel();
        optionDisplayInit();
        detailDisplayInit();
        warehouseFrameInit();
    }

    // MODIFIES: this
    // EFFECTS: initializes option part of Gui
    private void optionDisplayInit() {
        optionPanel = new JPanel();
        optionPanel.setPreferredSize(new Dimension(150, 700));
        optionPanel.setLayout(new GridLayout(7,1));
        optionPanel.setBackground(myBlue);
        ubcLogoLabel = new JLabel(warehouseLogoIcon);

        opDashBoard();
        opProducts();
        opStorageRecord();
        opUserInfo();

        optionPanel.add(ubcLogoLabel);
        optionPanel.add(dashBoardButton);
        optionPanel.add(productsButton);
        optionPanel.add(storageRecordButton);
        optionPanel.add(userInfoButton);
    }

    // MODIFIES: this
    // EFFECTS: initializes dashboard button of Gui
    private void opDashBoard() {
        dashBoardButton = new JButton();
        dashBoardButton.setPreferredSize(new Dimension(130, 100));
        dashBoardButton.setBackground(Color.white);
        dashBoardButton.setIcon(resizedDashBoardLogo);
    }

    // MODIFIES: this
    // EFFECTS: initializes products button of Gui
    private void opProducts() {
        productsButton = new JButton();
        productsButton.setPreferredSize(new Dimension(130, 100));
        productsButton.setBackground(Color.white);
        productsButton.setIcon(resizedProductsLogo);
    }

    // MODIFIES: this
    // EFFECTS: initializes StorageRecord button of Gui
    private void opStorageRecord() {
        storageRecordButton = new JButton();
        storageRecordButton.setPreferredSize(new Dimension(130, 100));
        storageRecordButton.setBackground(Color.white);
        storageRecordButton.setIcon(resizedStorageRecordLogo);
    }

    // MODIFIES: this
    // EFFECTS: initializes User Information button of Gui
    private void opUserInfo() {
        userInfoButton = new JButton();
        userInfoButton.setPreferredSize(new Dimension(130, 100));
        userInfoButton.setBackground(Color.white);
        userInfoButton.setIcon(resizedUserInformationLogo);
    }

    // MODIFIES: this
    // EFFECTS: initializes detailDisplay panel of Gui
    private void detailDisplayInit() {
        detailDisplayPanel = new JPanel();
        detailDisplayPanel.setPreferredSize(new Dimension(900, 700));

        createDashBoardPanel();
        createProductsPanel();
        createStorageRecordPanel();
        createUserInfoPanel();
        detailDisplayPanel.add(userInfoPanel);
    }

    // MODIFIES: this
    // EFFECTS: create User Information panel of detailDisplay Gui
    private void createUserInfoPanel() {
        userInfoPanel = new JPanel();
        userInfoPanel.setPreferredSize(new Dimension(900, 700));

        createEmptyPanelOfInformationPanel();
        createUserInformationPanel();
    }

    // MODIFIES: this
    // EFFECTS: create User Information panel of detailDisplay Gui
    private void createUserInformationPanel() {
        userInformationPanel = new JPanel();
        userInformationPanel.setPreferredSize(new Dimension(900, 625));
        userInformationPanel.setBackground(Color.white);

        JLabel userInformationLabel = new JLabel(resizedCompanyImage);
        userInformationPanel.add(userInformationLabel);
        userInfoPanel.add(userInformationPanel);
    }

    // MODIFIES: this
    // EFFECTS: create empty panel  top on the detailDisplay Gui
    private void createEmptyPanelOfInformationPanel() {
        JPanel whitePanel = new JPanel();
        JPanel emptyPanel = new JPanel();
        JLabel userLogoLabel = new JLabel(resizedUserLogo);
        whitePanel.setBackground(Color.white);
        whitePanel.setPreferredSize(new Dimension(100, 50));
        emptyPanel.setBackground(Color.white);
        emptyPanel.setPreferredSize(new Dimension(900, 50));
        emptyPanel.setLayout(new GridLayout(1,9));

        JLabel userNameLabel = new JLabel(MY_USERNAME);
        userNameLabel.setBackground(Color.white);
        userNameLabel.setFont(new Font("Cordia New", Font.BOLD, 15));
        userNameLabel.setForeground(myBlue);
        userNameLabel.setPreferredSize(new Dimension(50, 50));

        whitePanel.setLayout(new GridLayout(1,2));
        whitePanel.add(userNameLabel);
        whitePanel.add(userLogoLabel);

        addEmptyPanel2(whitePanel, emptyPanel);
        userInfoPanel.add(emptyPanel);
    }

    // MODIFIES: this
    // EFFECTS: create StorageRecordPanel on the detailDisplay Gui
    private void createStorageRecordPanel() {
        storageRecordPanel = new JPanel();
        storageRecordPanel.setPreferredSize(new Dimension(900, 700));

        createEmptyPanelItemRecord();
        createItemRecordListPane();
    }

    // MODIFIES: this
    // EFFECTS: create StorageRecordPanel on the detailDisplay Gui
    private void createItemRecordListPane() {
        itemRecordListRecordsPanel = new JPanel();
        itemRecordListRecordsPanel.setBackground(Color.WHITE);
        itemRecordListRecordsPanel.setPreferredSize(new Dimension(900, 4000));

        createItemRecordTitle();
        createItemRecordListPanel();
        itemRecordListRecordsPanel.add(itemRecordMailPanel);
        itemRecordListRecordsPanel.add(itemRecordTitlePanel);
        itemRecordListRecordsPanel.add(itemRecordListPanel);

        JScrollPane itemRecordsScrollPanel = new JScrollPane(itemRecordListRecordsPanel);
        itemRecordsScrollPanel.setBackground(Color.white);
        itemRecordsScrollPanel.setPreferredSize(new Dimension(900, 625));
        itemRecordsScrollPanel.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        itemRecordsScrollPanel.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        JScrollBar verticalScrollBar = itemRecordsScrollPanel.getVerticalScrollBar();
        verticalScrollBar.setUnitIncrement(15);
        storageRecordPanel.add(itemRecordsScrollPanel);
    }

    // MODIFIES: this
    // EFFECTS: create ItemRecordListPanel on the detailDisplay Gui
    private void createItemRecordListPanel() {
        itemRecordListPanel = new JPanel();
        itemRecordListPanel.setBackground(Color.WHITE);
        itemRecordListPanel.setPreferredSize(new Dimension(900, 4000));
        for (Item itemRecord : wareHouse.getItemRecords()) {
            JPanel itemPanel = new JPanel();
            itemPanel.setBackground(Color.WHITE);
            itemPanel.setPreferredSize(new Dimension(900, 30));
            itemPanel.setLayout(new GridLayout(1,3));
            JLabel nameLabel = getNameLabel(itemRecord.getName());
            JLabel quantityLabel = getQuantityLabel(String.valueOf(itemRecord.getQuantity()));
            JLabel addOrRemoveLabel = getAddOrRemoveLabel(itemRecord);
            itemPanel.add(nameLabel);
            itemPanel.add(quantityLabel);
            itemPanel.add(addOrRemoveLabel);
            itemRecordListPanel.add(itemPanel);
        }
    }

    // MODIFIES: this
    // EFFECTS: return AddOrRemoveLabel on the detailDisplay Gui
    private JLabel getAddOrRemoveLabel(Item itemRecord) {
        JLabel addOrRemoveLabel = new JLabel(itemRecord.getLocation());
        addOrRemoveLabel.setPreferredSize(new Dimension(225,30));
        addOrRemoveLabel.setFont(new Font("Cordia New", Font.PLAIN, 25));
        addOrRemoveLabel.setForeground(myBlue);
        addOrRemoveLabel.setBorder(new LineBorder(Color.BLACK));
        if (itemRecord.getLocation().equals("+")) {
            addOrRemoveLabel.setForeground(new Color(0,139,69));
        } else {
            addOrRemoveLabel.setForeground(Color.RED);
        }
        return addOrRemoveLabel;
    }

    // MODIFIES: this
    // EFFECTS: create ItemRecordTitle on the detailDisplay Gui
    private void createItemRecordTitle() {
        itemRecordMailPanel = new JPanel();
        itemRecordMailPanel.setBackground(Color.WHITE);
        itemRecordMailPanel.setPreferredSize(new Dimension(900, 100));
        JLabel titleLabel = new JLabel("Warehouse Storage Record");
        titleLabel.setFont(new Font("Cordia New", Font.PLAIN, 50));
        titleLabel.setForeground(myBlue);
        itemRecordMailPanel.add(titleLabel);

        itemRecordTitlePanel = new JPanel();
        itemRecordTitlePanel.setBackground(Color.WHITE);
        itemRecordTitlePanel.setPreferredSize(new Dimension(900, 30));
        itemRecordTitlePanel.setLayout(new GridLayout(1,3));

        JLabel nameLabel = getNameLabel("Name");
        JLabel quantityLabel = getQuantityLabel("Quantity");
        JLabel percentageLabel = getPercentageLabel("Add or Remove");

        itemRecordTitlePanel.add(nameLabel);
        itemRecordTitlePanel.add(quantityLabel);
        itemRecordTitlePanel.add(percentageLabel);
    }

    // MODIFIES: this
    // EFFECTS: create empty Panel on the top of detailDisplay Gui
    private void createEmptyPanelItemRecord() {
        JPanel whitePanel = new JPanel();
        JPanel emptyPanel = new JPanel();
        JLabel userLogoLabel = new JLabel(resizedUserLogo);
        whitePanel.setBackground(Color.white);
        whitePanel.setPreferredSize(new Dimension(100, 50));
        emptyPanel.setBackground(Color.white);
        emptyPanel.setPreferredSize(new Dimension(900, 50));
        emptyPanel.setLayout(new GridLayout(1,9));

        JLabel userNameLabel = new JLabel(MY_USERNAME);
        userNameLabel.setBackground(Color.white);
        userNameLabel.setFont(new Font("Cordia New", Font.BOLD, 15));
        userNameLabel.setForeground(myBlue);
        userNameLabel.setPreferredSize(new Dimension(50, 50));

        whitePanel.setLayout(new GridLayout(1,2));
        whitePanel.add(userNameLabel);
        whitePanel.add(userLogoLabel);

        addEmptyPanel2(whitePanel, emptyPanel);
        storageRecordPanel.add(emptyPanel);
    }

    // MODIFIES: this
    // EFFECTS: create DashBoardPanel of detailDisplay Gui
    private void createDashBoardPanel() {
        createEmptyPanel();
        dashBoardPanel = new JPanel();
        dashBoardPanel.setPreferredSize(new Dimension(900, 700));
        createGraphPanel();

        dashBoardPanel.add(emptyPanel);
        dashBoardPanel.add(graphPanel);
    }

    // MODIFIES: this
    // EFFECTS: create GraphPanel of detailDisplay Gui
    private void createGraphPanel() {
        graphPanel = new JPanel();
        graphPanel.setBackground(Color.WHITE);
        graphPanel.setPreferredSize(new Dimension(900, 650));
        JPanel gapPanel = new JPanel();
        gapPanel.setPreferredSize(new Dimension(900, 25));
        gapPanel.setBackground(Color.white);

        createInformationPanel();
        createBarGraph();
        graphPanel.add(gapPanel);
        graphPanel.add(histogramPanel);
    }

    // MODIFIES: this
    // EFFECTS: create InformationPanel of detailDisplay Gui
    private void createInformationPanel() {
        JPanel nameOfGraphPanel = new JPanel();
        JPanel gapPanel = new JPanel();
        gapPanel.setPreferredSize(new Dimension(900, 50));
        gapPanel.setBackground(Color.white);
        nameOfGraphPanel.setPreferredSize(new Dimension(900, 100));
        nameOfGraphPanel.setBackground(Color.white);
        JLabel nameLabel = new JLabel("Products In " + wareHouse.getName());
        nameLabel.setForeground(myBlue);
        nameLabel.setFont(new Font("Cordia New", Font.PLAIN, 50));
        nameOfGraphPanel.add(nameLabel);
        graphPanel.add(gapPanel);
        graphPanel.add(nameOfGraphPanel);
    }

    // MODIFIES: this
    // EFFECTS: create BarGraph of detailDisplay Gui
    private void createBarGraph() {
        histogramPanel = new HistogramPanel();
        histogramPanel.setBackground(Color.WHITE);

        for (Item item : wareHouse.getItems()) {
            histogramPanel.addHistogramColumn(item.getName() + "(" + item.getLocation() + ")",
                    item.getQuantity(), myBlue);
        }
        histogramPanel.layoutHistogram();
    }

    // MODIFIES: this
    // EFFECTS: create EmptyPanel on the top detailDisplay Gui
    private void createEmptyPanel() {
        whitePanel = new JPanel();
        emptyPanel = new JPanel();
        userLogoLabel = new JLabel(resizedUserLogo);
        whitePanel.setBackground(Color.white);
        whitePanel.setPreferredSize(new Dimension(100, 50));
        emptyPanel.setBackground(Color.white);
        emptyPanel.setPreferredSize(new Dimension(900, 50));
        emptyPanel.setLayout(new GridLayout(1,9));

        userNameLabel = new JLabel(MY_USERNAME);
        userNameLabel.setBackground(Color.white);
        userNameLabel.setFont(new Font("Cordia New", Font.BOLD, 15));
        userNameLabel.setForeground(myBlue);
        userNameLabel.setPreferredSize(new Dimension(50, 50));

        addEmptyPanel();
    }

    // MODIFIES: this
    // EFFECTS: create EmptyPanel to the top detailDisplay Gui
    private void addEmptyPanel() {
        whitePanel.setLayout(new GridLayout(1,2));
        whitePanel.add(userNameLabel);
        whitePanel.add(userLogoLabel);

        emptyPanel.add(new Panel());
        emptyPanel.add(new Panel());
        emptyPanel.add(new Panel());
        emptyPanel.add(new Panel());
        emptyPanel.add(new Panel());
        emptyPanel.add(new Panel());
        emptyPanel.add(new Panel());
        emptyPanel.add(new Panel());
        emptyPanel.add(whitePanel);
    }

    // MODIFIES: this
    // EFFECTS: create ProductsPanel to the detailDisplay Gui
    private void createProductsPanel() {
        productsPanel = new JPanel();
        productsPanel.setPreferredSize(new Dimension(900, 700));

        createEmptyPanelProduct();
        createItemAddOrRemovePanel();
        createProductsRecordsPanel();
    }

    // MODIFIES: this
    // EFFECTS: create EmptyPanel to the top detailDisplay Gui
    private void createEmptyPanelProduct() {
        JPanel whitePanel = new JPanel();
        JPanel emptyPanel = new JPanel();
        JLabel userLogoLabel = new JLabel(resizedUserLogo);
        whitePanel.setBackground(Color.white);
        whitePanel.setPreferredSize(new Dimension(100, 50));
        emptyPanel.setBackground(Color.white);
        emptyPanel.setPreferredSize(new Dimension(900, 50));
        emptyPanel.setLayout(new GridLayout(1,9));

        JLabel userNameLabel = new JLabel(MY_USERNAME);
        userNameLabel.setBackground(Color.white);
        userNameLabel.setFont(new Font("Cordia New", Font.BOLD, 15));
        userNameLabel.setForeground(myBlue);
        userNameLabel.setPreferredSize(new Dimension(50, 50));

        whitePanel.setLayout(new GridLayout(1,2));
        whitePanel.add(userNameLabel);
        whitePanel.add(userLogoLabel);

        addEmptyPanel2(whitePanel, emptyPanel);
        productsPanel.add(emptyPanel);
    }

    // MODIFIES: this
    // EFFECTS: add EmptyPanel to the top detailDisplay Gui
    private void addEmptyPanel2(JPanel whitePanel, JPanel emptyPanel) {
        emptyPanel.add(new Panel());
        emptyPanel.add(new Panel());
        emptyPanel.add(new Panel());
        emptyPanel.add(new Panel());
        emptyPanel.add(new Panel());
        emptyPanel.add(new Panel());
        emptyPanel.add(new Panel());
        emptyPanel.add(new Panel());
        emptyPanel.add(whitePanel);
    }

    // MODIFIES: this
    // EFFECTS: add ProductsRecordsPanel to the detailDisplay Gui
    private void createProductsRecordsPanel() {
        productsRecordsPanel = new JPanel();
        productsRecordsPanel.setBackground(Color.WHITE);
        productsRecordsPanel.setPreferredSize(new Dimension(900, 4000));

        JPanel titlePanel = getTitlePanel();
        createItemListPanel();
        productsRecordsPanel.add(itemMailPanel);
        productsRecordsPanel.add(titlePanel);
        productsRecordsPanel.add(itemListPanel);

        JScrollPane productsRecordsScrollPanel = new JScrollPane(productsRecordsPanel);
        productsRecordsScrollPanel.setBackground(Color.white);
        productsRecordsScrollPanel.setPreferredSize(new Dimension(900, 525));
        productsRecordsScrollPanel.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        productsRecordsScrollPanel.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        JScrollBar verticalScrollBar = productsRecordsScrollPanel.getVerticalScrollBar();
        verticalScrollBar.setUnitIncrement(15);
        productsPanel.add(productsRecordsScrollPanel);
    }

    // MODIFIES: this
    // EFFECTS: create AddOrRemovePanel to the detailDisplay Gui
    private void createItemAddOrRemovePanel() {
        JPanel itemAddOrRemovePanel = new JPanel();
        itemAddOrRemovePanel.setPreferredSize(new Dimension(900, 100));
        itemAddOrRemovePanel.setLayout(new GridLayout(2,4));

        addButton = new JButton("Add Item +");
        addButton.setPreferredSize(new Dimension(225, 50));
        addButton.setFont(new Font("Cordia New", Font.BOLD, 20));
        addButton.setForeground(new Color(0, 139, 69));

        removeButton = new JButton("Remove Item -");
        removeButton.setPreferredSize(new Dimension(225, 50));
        removeButton.setFont(new Font("Cordia New", Font.BOLD, 20));
        removeButton.setForeground(Color.RED);

        createSearchPanel();

        itemAddOrRemovePanel.add(new JPanel());
        itemAddOrRemovePanel.add(new JPanel());
        itemAddOrRemovePanel.add(new JPanel());
        itemAddOrRemovePanel.add(new JPanel());
        itemAddOrRemovePanel.add(removeButton);
        itemAddOrRemovePanel.add(addButton);
        itemAddOrRemovePanel.add(new JPanel());
        itemAddOrRemovePanel.add(searchPanel);
        productsPanel.add(itemAddOrRemovePanel);
    }

    // MODIFIES: this
    // EFFECTS: create SearchPanel to the detailDisplay Gui
    private void createSearchPanel() {
        searchItemField = new JTextField(10);
        searchItemField.setPreferredSize(new Dimension(170, 40));
        searchItemField.setFont(new Font("Cordia New", Font.PLAIN, 15));
        searchButton = new JButton(resizedSearchLogo);
        searchButton.setPreferredSize(new Dimension(50,50));
        searchPanel = new JPanel();
        searchPanel.setPreferredSize(new Dimension(225,50));
        searchPanel.setBackground(Color.WHITE);
        searchPanel.add(searchItemField);
        searchPanel.add(searchButton);
        searchButtonAction();
    }

    // MODIFIES: this
    // EFFECTS: create ItemListPanel to the detailDisplay Gui
    private void createItemListPanel() {
        percentageList = getPercentageList();
        createItemListPanel(percentageList);
    }

    // MODIFIES: this
    // EFFECTS: create ItemListPanel to the detailDisplay Gui
    private void createItemListPanel(List<String> percentageList) {
        itemListPanel = new JPanel();
        itemListPanel.setBackground(Color.WHITE);
        itemListPanel.setPreferredSize(new Dimension(900, 4000));
        int count = 0;
        for (Item item : wareHouse.getItems()) {
            JPanel itemPanel = new JPanel();
            itemPanel.setBackground(Color.WHITE);
            itemPanel.setPreferredSize(new Dimension(900, 30));
            itemPanel.setLayout(new GridLayout(1,4));
            JLabel locationLabel = getLocationLabel(item.getLocation());
            JLabel nameLabel = getNameLabel(item.getName());
            JLabel quantityLabel = getQuantityLabel(String.valueOf(item.getQuantity()));
            JLabel percentageLabel = getPercentageLabel(percentageList.get(count));

            itemPanel.add(locationLabel);
            itemPanel.add(nameLabel);
            itemPanel.add(quantityLabel);
            itemPanel.add(percentageLabel);
            itemListPanel.add(itemPanel);
            count += 1;
        }
    }

    // MODIFIES: this
    // EFFECTS: return PercentageList to the detailDisplay Gui
    private List<String> getPercentageList() {
        percentageList = new ArrayList<>();
        int totalQuantity = 0;
        for (Item item : wareHouse.getItems()) {
            totalQuantity += item.getQuantity();
        }

        for (Item item : wareHouse.getItems()) {
            percentageList.add(Math.round(100.0 * item.getQuantity() / totalQuantity)
                    + "%");
        }
        return percentageList;
    }

    // MODIFIES: this
    // EFFECTS: return TitlePanel to the detailDisplay Gui
    private JPanel getTitlePanel() {
        itemMailPanel = new JPanel();
        itemMailPanel.setBackground(Color.WHITE);
        itemMailPanel.setPreferredSize(new Dimension(900, 100));
        JLabel titleLabel = new JLabel("Items In the Warehouse");
        titleLabel.setFont(new Font("Cordia New", Font.PLAIN, 50));
        titleLabel.setForeground(myBlue);
        itemMailPanel.add(titleLabel);

        JPanel titlePanel = new JPanel();
        titlePanel.setBackground(Color.WHITE);
        titlePanel.setPreferredSize(new Dimension(900, 30));
        titlePanel.setLayout(new GridLayout(1,4));

        JLabel locationLabel = getLocationLabel("Location");
        JLabel nameLabel = getNameLabel("Name");
        JLabel quantityLabel = getQuantityLabel("Quantity");
        JLabel percentageLabel = getPercentageLabel("Percentage");

        titlePanel.add(locationLabel);
        titlePanel.add(nameLabel);
        titlePanel.add(quantityLabel);
        titlePanel.add(percentageLabel);
        return titlePanel;
    }

    // MODIFIES: this
    // EFFECTS: return PercentageLabel to the detailDisplay Gui
    private JLabel getPercentageLabel(String percentage) {
        JLabel percentageLabel = new JLabel(percentage);
        percentageLabel.setPreferredSize(new Dimension(225,30));
        percentageLabel.setFont(new Font("Cordia New", Font.PLAIN, 15));
        percentageLabel.setForeground(myBlue);
        percentageLabel.setBorder(new LineBorder(Color.BLACK));
        return percentageLabel;
    }

    // MODIFIES: this
    // EFFECTS: return QuantityLabel to the detailDisplay Gui
    private JLabel getQuantityLabel(String quantity) {
        JLabel quantityLabel = new JLabel(quantity);
        quantityLabel.setPreferredSize(new Dimension(225,30));
        quantityLabel.setFont(new Font("Cordia New", Font.PLAIN, 15));
        quantityLabel.setForeground(myBlue);
        quantityLabel.setBorder(new LineBorder(Color.BLACK));
        return quantityLabel;
    }

    // MODIFIES: this
    // EFFECTS: return NameLabel to the detailDisplay Gui
    private JLabel getNameLabel(String name) {
        JLabel nameLabel = new JLabel(name);
        nameLabel.setPreferredSize(new Dimension(225,30));
        nameLabel.setFont(new Font("Cordia New", Font.PLAIN, 15));
        nameLabel.setForeground(myBlue);
        nameLabel.setBorder(new LineBorder(Color.BLACK));
        return nameLabel;
    }

    // MODIFIES: this
    // EFFECTS: return LocationLabel to the detailDisplay Gui
    private JLabel getLocationLabel(String location) {
        JLabel locationLabel = new JLabel(location);
        locationLabel.setPreferredSize(new Dimension(225,30));
        locationLabel.setFont(new Font("Cordia New", Font.PLAIN, 15));
        locationLabel.setForeground(myBlue);
        locationLabel.setBorder(new LineBorder(Color.BLACK));
        return locationLabel;
    }

    // MODIFIES: this
    // EFFECTS: initialise warehouseFrame
    private void warehouseFrameInit() {
        containerPanel.add(optionPanel);
        containerPanel.add(detailDisplayPanel);
        warehouseFrame.add(containerPanel);
        warehouseFrame.setBackground(myBlue);
        warehouseFrame.setVisible(true); // Make the frame visible
        warehouseFrame.setResizable(false); // Make the frame non-resizable
        warehouseFrame.pack();
        warehouseFrame.setLocationRelativeTo(null); // make it in the center of the computer
        closeFrameAction();
        dashBoardButtonAction();
        productsButtonAction();
        storageRecordButtonAction();
        userInfoButtonAction();
        addItemButtonAction();
        removeItemButtonAction();
    }

    // MODIFIES: this
    // EFFECTS: create the action to the dashboard panel
    private void dashBoardButtonAction() {
        dashBoardButton.addActionListener(e -> {
            if (detailDisplayPanel.getComponentCount() > 0) {
                detailDisplayPanel.remove(0);
            }
            detailDisplayPanel.add(dashBoardPanel);
            warehouseFrame.revalidate();
            warehouseFrame.repaint();
        });
    }

    // MODIFIES: this
    // EFFECTS: create the action to the products Panel
    private void productsButtonAction() {
        productsButton.addActionListener(e -> {
            if (detailDisplayPanel.getComponentCount() > 0) {
                detailDisplayPanel.remove(0);

            }
            detailDisplayPanel.add(productsPanel);
            // refresh the display
            warehouseFrame.revalidate();
            warehouseFrame.repaint();
        });
    }

    // MODIFIES: this
    // EFFECTS: create the action to the  storage Record Panel
    private void storageRecordButtonAction() {
        storageRecordButton.addActionListener(e -> {
            if (detailDisplayPanel.getComponentCount() > 0) {
                detailDisplayPanel.remove(0);
                detailDisplayPanel.add(storageRecordPanel);
            }
            detailDisplayPanel.add(storageRecordPanel);
            // refresh the display
            warehouseFrame.revalidate();
            warehouseFrame.repaint();
        });
    }

    // MODIFIES: this
    // EFFECTS: create the action to the user Information Panel
    private void userInfoButtonAction() {
        userInfoButton.addActionListener(e -> {
            if (detailDisplayPanel.getComponentCount() > 0) {
                detailDisplayPanel.remove(0);
            }
            detailDisplayPanel.add(userInfoPanel);
            // refresh the display
            warehouseFrame.revalidate();
            warehouseFrame.repaint();
        });
    }

    // MODIFIES: this
    // EFFECTS: create the action to the add item
    private void addItemButtonAction() {
        addButton.addActionListener(e -> {
            createItemInput();
            itemInputFrame.setResizable(false); // Make the frame non-resizable
            itemInputFrame.setLocationRelativeTo(null); // make it in the center of the computer
            itemInputFrame.setVisible(true);
            submitButtonAction();
        });
    }

    // MODIFIES: this
    // EFFECTS: create the action to the remove item
    private void removeItemButtonAction() {
        removeButton.addActionListener(e -> {
            createRemoveItemInput();
            itemInputFrame.setResizable(false); // Make the frame non-resizable
            itemInputFrame.setLocationRelativeTo(null); // make it in the center of the computer
            itemInputFrame.setVisible(true);
            submitRemoveButton();
        });
    }

    // MODIFIES: this
    // EFFECTS: create the action to the search Button
    private void searchButtonAction() {
        searchButton.addActionListener(e -> {
            if (searchItemField.getText().isEmpty()) {
                productsRecordsPanel.remove(2);
                percentageList = getPercentageList();
                createItemListPanel(percentageList);
                productsRecordsPanel.add(itemListPanel,2);
                warehouseFrame.revalidate();
                warehouseFrame.repaint();
            } else if (!isInWarehouse()) {
                JOptionPane.showMessageDialog(null,
                        "There's no " + searchItemField.getText() + " in the warehouse");
                searchItemField.setText("");
            } else {
                productsRecordsPanel.remove(2);
                percentageList = getPercentageList();
                createSearchItemPanel(searchItemField.getText());
                productsRecordsPanel.add(itemListPanel,2);
                warehouseFrame.revalidate();
                warehouseFrame.repaint();
                searchItemField.setText("");
            }
        });
    }

    // MODIFIES: this
    // EFFECTS: create SearchItemPanel to the detailDisplay Gui
    private void createSearchItemPanel(String searchName) {
        itemListPanel = new JPanel();
        itemListPanel.setBackground(Color.WHITE);
        itemListPanel.setPreferredSize(new Dimension(900, 4000));
        int count = 0;
        for (Item item : wareHouse.getItems()) {
            String itemName = item.getName();
            if (searchName.equals(itemName)) {
                JPanel itemPanel = new JPanel();
                itemPanel.setBackground(Color.WHITE);
                itemPanel.setPreferredSize(new Dimension(900, 30));
                itemPanel.setLayout(new GridLayout(1,4));
                JLabel locationLabel = getLocationLabel(item.getLocation());
                JLabel nameLabel = getNameLabel(item.getName());
                JLabel quantityLabel = getQuantityLabel(String.valueOf(item.getQuantity()));
                JLabel percentageLabel = getPercentageLabel(percentageList.get(count));

                itemPanel.add(locationLabel);
                itemPanel.add(nameLabel);
                itemPanel.add(quantityLabel);
                itemPanel.add(percentageLabel);
                itemListPanel.add(itemPanel);
            }
            count += 1;
        }
    }

    // MODIFIES: this
    // EFFECTS: produce true if the name in the searchItemField is in the warehouse
    private boolean isInWarehouse() {
        String searchName = searchItemField.getText();
        for (Item itemInWarehouse : wareHouse.getItems()) {
            String itemName = itemInWarehouse.getName();
            if (searchName.equals(itemName)) {
                return true;
            }
        }
        return false;
    }

    // MODIFIES: this
    // EFFECTS: create RemoveItemInput frame
    private void createRemoveItemInput() {
        createItemRemove();
    }

    // MODIFIES: this
    // EFFECTS: create ItemRemove Panel
    private void createItemRemove() {
        itemInputFrame = new JFrame();
        itemInputFrame.setTitle("Item Remove");
        itemInputFrame.setSize(400, 200);
        itemInputFrame.setLayout(new GridLayout(4, 2));
        itemInputFrame.setBackground(myBlue);
        itemInputFrame.getContentPane().setBackground(myBlue);

        createNameLocationQuantityText();
        itemInputFrame.remove(submitButton);
        submitRemoveButton = new JButton("Submit");
        itemInputFrame.add(submitRemoveButton);
    }

    // MODIFIES: this
    // EFFECTS: create ItemInput Panel
    private void createItemInput() {
        itemInputFrame = new JFrame();
        itemInputFrame.setTitle("Item Input");
        itemInputFrame.setSize(400, 200);
        itemInputFrame.setLayout(new GridLayout(4, 2));
        itemInputFrame.setBackground(myBlue);
        itemInputFrame.getContentPane().setBackground(myBlue);
        createNameLocationQuantityText();
    }

    // MODIFIES: this
    // EFFECTS: create NameLocationQuantityText Panel
    private void createNameLocationQuantityText() {
        JLabel itemNameLabel = new JLabel("Item Name:");
        itemNameLabel.setFont(new Font("Cordia New", Font.BOLD, 20));
        nameField = new JTextField();
        JLabel quantityLabel = new JLabel("Quantity:");
        quantityLabel.setFont(new Font("Cordia New", Font.BOLD, 20));
        quantityField = new JTextField();
        JLabel locationLabel = new JLabel("Location:");
        locationLabel.setFont(new Font("Cordia New", Font.BOLD, 20));
        locationField = new JTextField();
        itemInputFrame.add(itemNameLabel);
        itemInputFrame.add(nameField);
        itemInputFrame.add(quantityLabel);
        itemInputFrame.add(quantityField);
        itemInputFrame.add(locationLabel);
        itemInputFrame.add(locationField);

        submitButton = new JButton("Submit");
        itemInputFrame.add(new JLabel());
        itemInputFrame.add(submitButton);
    }

    // MODIFIES: this
    // EFFECTS: create action to submit
    private void submitButtonAction() {
        submitButton.addActionListener(e -> {
            addItem(nameField.getText(), Integer.parseInt(quantityField.getText()),  locationField.getText());
        });
    }

    // MODIFIES: this
    // EFFECTS: create action to close frame
    private void closeFrameAction() {
        warehouseFrame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                for (Event event: EventLog.getInstance()) {
                    System.out.println("");
                    System.out.println(event.toString());
                }
                runSaveOrNot();
            }
        });
    }

    // MODIFIES: this
    // EFFECTS: Add item to the warehouse. If the item with the same name,
    // and same location, add quantity to the original one, else add new item.
    private void addItem(String name, int quantity, String location) {
        Item item = new Item(name, quantity, location);
        Item itemRecord = new Item(name, quantity, "+");

        if (findOrNot(name, location)) {
            addToTheOriginalOne(name, quantity, location);
        } else {
            wareHouse.addItem(item);
        }
        wareHouse.addItemRecord(itemRecord);

        freshJFrame();
        JOptionPane.showMessageDialog(null,
                (quantity + " " + name + " added to the warehouse in area " + location + "."));
    }

    // MODIFIES: this
    // EFFECTS: fresh the warehouse frame
    private void freshJFrame() {
        itemInputFrame.dispose();
        createBarGraph();
        graphPanel.remove(3);
        graphPanel.add(histogramPanel, 3);
        productsRecordsPanel.remove(2);
        percentageList = getPercentageList();
        createItemListPanel(percentageList);
        productsRecordsPanel.add(itemListPanel,2);

        itemRecordListRecordsPanel.remove(1);
        createItemRecordListPanel();
        itemRecordListRecordsPanel.add(itemRecordListPanel,1);
        warehouseFrame.revalidate();
        warehouseFrame.repaint();
    }

    // MODIFIES: this
    // EFFECTS: Adds the given quantity of the item with the given name and location in the warehouse,
    // which the item is already in the warehouse.
    public void addToTheOriginalOne(String name, int quantity, String location) {
        for (Item itemInWareHouse : wareHouse.getItems()) {
            if (itemInWareHouse.getName().equals(name)
                    && itemInWareHouse.getLocation().equals(location)) {
                itemInWareHouse.addQuantity(quantity);
            }
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
    // EFFECTS: create the action to submit the remove item
    private void submitRemoveButton() {
        submitRemoveButton.addActionListener(e -> {
            removeItem();
        });
    }

    // MODIFIES: this
    // EFFECTS: Decrease the quantity to the item in warehouse with the given quantity.
    // If there is no more that item, remove it from the list.
    public void removeItem() {
        String name = nameField.getText();
        String location = locationField.getText();
        int quantity = Integer.parseInt(quantityField.getText());

        if (findOrNot(name, location)) {
            reduceOrRemoveItem(name, quantity, location);

        } else {
            freshJFrame();
            JOptionPane.showMessageDialog(null,
                    ("There is no " + name + " in our warehouse. Please try again!"));
        }
    }

    // MODIFIES: this
    // EFFECTS: Decrease the quantity to the item in warehouse with the given quantity.
    // If there is no more that item, remove it from the list.
    public void reduceOrRemoveItem(String name, int quantity, String location) {
        Item itemRecord = new Item(name, quantity, "-");
        for (int i = 0; i < wareHouse.numItems(); i++) {
            Item itemInWareHouse = wareHouse.getItems().get(i);
            if (itemInWareHouse.getName().equals(name)
                    && itemInWareHouse.getLocation().equals(location)) {
                if (itemInWareHouse.isNoMore(quantity)) {
                    totalRemove(name, itemRecord, i);
                } else if (itemInWareHouse.isEnough(quantity)) {
                    removeSome(name, quantity, location, itemRecord, itemInWareHouse);
                } else {
                    itemInputFrame.dispose();
                    JOptionPane.showMessageDialog(null,
                            ("We only have " + itemInWareHouse.getQuantity() + " "
                                    + name + " in the warehouse. " + "Please try again!"));
                }
            }
        }
    }

    // MODIFIES: this
    // EFFECTS: reduce a number of a product but all the quantity
    private void removeSome(String name, int quantity, String location, Item itemRecord, Item itemInWareHouse) {
        itemInWareHouse.reduceQuantity(quantity);
        wareHouse.addItemRecord(itemRecord);
        freshJFrame();
        JOptionPane.showMessageDialog(null,
                ("Reduce " + quantity + " of " + name + " in " + location + " to "
                        + itemInWareHouse.getQuantity() + "."));
    }

    // MODIFIES: this
    // EFFECTS: remove the product since we use all of it
    private void totalRemove(String name, Item itemRecord, int i) {
        wareHouse.removeItem(i);
        wareHouse.addItemRecord(itemRecord);
        freshJFrame();
        JOptionPane.showMessageDialog(null, ("You already removed all "
                + name + " in the warehouse."));
    }

    // EFFECTS: runs the LoadOrNot Panel GUI
    private void runSaveOrNot() {
        saveOrNotInit();
        saveOrNotPanel.add(saveOrNotLabel);
        saveOrNotPanel.add(saveButtonPanel);

        // user selects Yes
        saveYesButton.addActionListener(e -> {
            saveWareHouse();
            warehouseFrame.dispose();
            savOrNotFrame.dispose();
            JOptionPane.showMessageDialog(null,
                    "Save the data Successfully");
        });
        // user selects No
        saveNoButton.addActionListener(e -> {
            warehouseFrame.dispose();
            savOrNotFrame.dispose();
        });

        saveButtonPanel.add(saveYesButton);
        saveButtonPanel.add(saveNoButton);
        savOrNotFrame.setContentPane(saveOrNotPanel);
        savOrNotFrame.setVisible(true);
    }

    // MODIFIES: this
    // EFFECTS: initializes load or not panel
    private void saveOrNotInit() {
        savOrNotFrame = new JFrame("Save Database Data");
        savOrNotFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        savOrNotFrame.setBackground(myBlue);
        savOrNotFrame.setSize(400, 200);
        savOrNotFrame.setResizable(false); // Make the frame non-resizable
        savOrNotFrame.setLocationRelativeTo(null); // make it in the center of the computer

        saveOrNotPanel = new JPanel(new GridLayout(2, 1));
        saveOrNotPanel.setBackground(myBlue);

        saveOrNotLabel = new JLabel("Do you want to save data to the database?");
        saveOrNotLabel.setFont(new Font("Cordia New", Font.BOLD, 15));
        saveOrNotLabel.setHorizontalAlignment(SwingConstants.CENTER);

        saveButtonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        saveButtonPanel.setBackground(myBlue);
        saveYesButton = new JButton("Yes");
        saveYesButton.setFont(new Font("Cordia New", Font.PLAIN, 13));
        saveNoButton = new JButton("No");
        saveNoButton.setFont(new Font("Cordia New", Font.PLAIN, 13));
    }

    // MODIFIES: this
    // EFFECTS: loads warehouse from file
    private void loadWarehouse() {
        try {
            wareHouse = jsonReader.read();
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + JSON_STORE);
        }
    }

    // EFFECTS: saves the warehouse to file
    private void saveWareHouse() {
        try {
            jsonWriter.open();
            jsonWriter.write(wareHouse);
            jsonWriter.close();
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file: " + JSON_STORE);
        }
    }

    // MODIFIES: this
    // EFFECT: Produce the resized imageIcon.
    public ImageIcon resizeImageIcon(ImageIcon imageIcon, int width, int height) {
        return  new ImageIcon(imageIcon.getImage().getScaledInstance(
                width, height, Image.SCALE_SMOOTH));
    }
}
