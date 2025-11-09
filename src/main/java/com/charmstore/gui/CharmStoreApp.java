package com.charmstore.gui;

import com.charmstore.logic.*;
import com.charmstore.model.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.List;

/**
 * Main GUI application for the Charm Bar Store
 * Demonstrates: Modularity, Event Handling, GUI Programming
 */
public class CharmStoreApp extends JFrame {
    // Business logic layer
    private CharmBarLogic logic;
    
    // Current accessory being assembled
    private Accessory currentAccessory;
    
    // GUI Components
    private JPanel mainPanel;
    private JTextArea displayArea;
    private JButton createAccessoryBtn;
    private JButton addCharmBtn;
    private JButton viewAccessoryBtn;
    private JButton completeOrderBtn;
    private JButton saveToFileBtn;
    private JLabel statusLabel;
    
    /**
     * Constructor
     */
    public CharmStoreApp() {
        super("Charm Bar Store - Custom Accessory Designer");
        logic = new CharmBarLogic();
        initializeGUI();
    }
    
    /**
     * Initialize the GUI components
     * Demonstrates: Modularity, Encapsulation
     */
    private void initializeGUI() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);
        setLocationRelativeTo(null);
        
        // Create main panel with BorderLayout
        mainPanel = new JPanel(new BorderLayout(10, 10));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        
        // Title panel
        JPanel titlePanel = new JPanel();
        titlePanel.setBackground(new Color(100, 150, 200));
        JLabel titleLabel = new JLabel("✨ Charm Bar Store ✨");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setForeground(Color.WHITE);
        titlePanel.add(titleLabel);
        mainPanel.add(titlePanel, BorderLayout.NORTH);
        
        // Display area
        displayArea = new JTextArea();
        displayArea.setEditable(false);
        displayArea.setFont(new Font("Monospaced", Font.PLAIN, 12));
        displayArea.setText("Welcome to the Charm Bar Store!\n\n" +
                          "Start by creating a new accessory base (bracelet or necklace).\n" +
                          "Then add your favorite charms to customize it!\n");
        JScrollPane scrollPane = new JScrollPane(displayArea);
        mainPanel.add(scrollPane, BorderLayout.CENTER);
        
        // Button panel
        JPanel buttonPanel = new JPanel(new GridLayout(3, 2, 10, 10));
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));
        
        createAccessoryBtn = new JButton("Create New Accessory");
        createAccessoryBtn.addActionListener(e -> createNewAccessory());
        
        addCharmBtn = new JButton("Add Charm");
        addCharmBtn.setEnabled(false);
        addCharmBtn.addActionListener(e -> addCharm());
        
        viewAccessoryBtn = new JButton("View Current Accessory");
        viewAccessoryBtn.setEnabled(false);
        viewAccessoryBtn.addActionListener(e -> viewCurrentAccessory());
        
        completeOrderBtn = new JButton("Complete Order");
        completeOrderBtn.setEnabled(false);
        completeOrderBtn.addActionListener(e -> completeOrder());
        
        saveToFileBtn = new JButton("Save to File");
        saveToFileBtn.setEnabled(false);
        saveToFileBtn.addActionListener(e -> saveToFile());
        
        JButton exitBtn = new JButton("Exit");
        exitBtn.addActionListener(e -> System.exit(0));
        
        buttonPanel.add(createAccessoryBtn);
        buttonPanel.add(addCharmBtn);
        buttonPanel.add(viewAccessoryBtn);
        buttonPanel.add(completeOrderBtn);
        buttonPanel.add(saveToFileBtn);
        buttonPanel.add(exitBtn);
        
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);
        
        // Status bar
        statusLabel = new JLabel("Ready");
        statusLabel.setBorder(BorderFactory.createEtchedBorder());
        mainPanel.add(statusLabel, BorderLayout.PAGE_END);
        
        add(mainPanel);
    }
    
    /**
     * Create a new accessory
     * Demonstrates: Exception Handling, Polymorphism
     */
    private void createNewAccessory() {
        try {
            // Ask for customer name
            String customerName = JOptionPane.showInputDialog(this, 
                "Enter customer name:", 
                "Customer Information", 
                JOptionPane.QUESTION_MESSAGE);
            
            if (customerName == null) return; // cancelled
            
            // Validate name using business logic
            logic.validateCustomerName(customerName);
            
            // Choose base type
            String[] baseTypes = {"Bracelet", "Necklace"};
            String baseType = (String) JOptionPane.showInputDialog(this,
                "Select accessory type:",
                "Accessory Base",
                JOptionPane.QUESTION_MESSAGE,
                null,
                baseTypes,
                baseTypes[0]);
            
            if (baseType == null) return;
            
            // Choose material
            String[] materials = {"Silver", "Gold"};
            String material = (String) JOptionPane.showInputDialog(this,
                "Select material:",
                "Material Selection",
                JOptionPane.QUESTION_MESSAGE,
                null,
                materials,
                materials[0]);
            
            if (material == null) return;
            
            // Create base using polymorphism
            AccessoryBase base = logic.createBase(baseType, material);
            
            // Create accessory with composition
            currentAccessory = new Accessory(base);
            currentAccessory.setCustomerName(customerName);
            
            // Enable buttons
            addCharmBtn.setEnabled(true);
            viewAccessoryBtn.setEnabled(true);
            completeOrderBtn.setEnabled(true);
            saveToFileBtn.setEnabled(true);
            
            displayArea.setText("New accessory created!\n\n" + 
                              currentAccessory.getDetailedDescription());
            statusLabel.setText("Accessory created for " + customerName);
            
        } catch (InvalidSelectionException e) {
            // Exception handling
            JOptionPane.showMessageDialog(this, 
                e.getMessage(), 
                "Invalid Selection", 
                JOptionPane.ERROR_MESSAGE);
            statusLabel.setText("Error: " + e.getMessage());
        }
    }
    
    /**
     * Add a charm to the current accessory
     * Demonstrates: Exception Handling, Polymorphism
     */
    private void addCharm() {
        if (currentAccessory == null) return;
        
        try {
            // Check if can add more charms
            if (!currentAccessory.canAddCharm()) {
                throw new InvalidSelectionException(
                    "Maximum charm capacity reached!");
            }
            
            // Choose charm type
            String[] charmTypes = {"Heart", "Star", "Animal"};
            String charmType = (String) JOptionPane.showInputDialog(this,
                "Select charm type:",
                "Charm Selection",
                JOptionPane.QUESTION_MESSAGE,
                null,
                charmTypes,
                charmTypes[0]);
            
            if (charmType == null) return;
            
            // Choose material
            String[] materials = {"Silver", "Gold"};
            String material = (String) JOptionPane.showInputDialog(this,
                "Select charm material:",
                "Material Selection",
                JOptionPane.QUESTION_MESSAGE,
                null,
                materials,
                materials[0]);
            
            if (material == null) return;
            
            String animalType = null;
            if ("Animal".equals(charmType)) {
                String[] animals = {"Cat", "Dog", "Butterfly", "Dolphin"};
                animalType = (String) JOptionPane.showInputDialog(this,
                    "Select animal type:",
                    "Animal Selection",
                    JOptionPane.QUESTION_MESSAGE,
                    null,
                    animals,
                    animals[0]);
                if (animalType == null) return;
            }
            
            // Create charm using polymorphism
            Charm charm = logic.createCharm(charmType, material, animalType);
            
            // Add customization options
            customizeCharm(charm, charmType);
            
            // Add charm to accessory
            logic.addCharmToAccessory(currentAccessory, charm);
            
            displayArea.setText("Charm added!\n\n" + 
                              currentAccessory.getDetailedDescription());
            statusLabel.setText("Charm added: " + charm.getName());
            
        } catch (InvalidSelectionException e) {
            JOptionPane.showMessageDialog(this, 
                e.getMessage(), 
                "Cannot Add Charm", 
                JOptionPane.ERROR_MESSAGE);
            statusLabel.setText("Error: " + e.getMessage());
        }
    }
    
    /**
     * Customize a charm based on its type
     * Demonstrates: Polymorphism, Type checking
     */
    private void customizeCharm(Charm charm, String charmType) {
        // Apply customization based on charm type
        if (charm instanceof HeartCharm) {
            int result = JOptionPane.showConfirmDialog(this,
                "Would you like to add engraving?",
                "Engraving Option",
                JOptionPane.YES_NO_OPTION);
            
            if (result == JOptionPane.YES_OPTION) {
                String engraving = JOptionPane.showInputDialog(this,
                    "Enter engraving text (max 20 chars):",
                    "Engraving");
                if (engraving != null && !engraving.isEmpty()) {
                    ((HeartCharm) charm).setEngravingText(engraving);
                }
            }
        } else if (charm instanceof StarCharm) {
            int result = JOptionPane.showConfirmDialog(this,
                "Would you like to add a gemstone center?",
                "Gemstone Option",
                JOptionPane.YES_NO_OPTION);
            
            if (result == JOptionPane.YES_OPTION) {
                ((StarCharm) charm).setHasGemstone(true);
            }
            
            // Choose star points
            String[] points = {"5-point", "6-point"};
            String pointChoice = (String) JOptionPane.showInputDialog(this,
                "Select star style:",
                "Star Points",
                JOptionPane.QUESTION_MESSAGE,
                null,
                points,
                points[0]);
            
            if (pointChoice != null && pointChoice.startsWith("6")) {
                ((StarCharm) charm).setNumberOfPoints(6);
            }
        } else if (charm instanceof AnimalCharm) {
            int result = JOptionPane.showConfirmDialog(this,
                "Would you like to add colored enamel details?",
                "Enamel Option",
                JOptionPane.YES_NO_OPTION);
            
            if (result == JOptionPane.YES_OPTION) {
                ((AnimalCharm) charm).setHasColorEnamel(true);
            }
        }
        
        // Apply finish
        String[] finishes = {"Polished", "Matte", "Antique"};
        String finish = (String) JOptionPane.showInputDialog(this,
            "Select finish:",
            "Finish Selection",
            JOptionPane.QUESTION_MESSAGE,
            null,
            finishes,
            finishes[0]);
        
        if (finish != null) {
            charm.applyFinish(finish.toLowerCase());
        }
    }
    
    /**
     * View the current accessory details
     */
    private void viewCurrentAccessory() {
        if (currentAccessory != null) {
            displayArea.setText(currentAccessory.getDetailedDescription());
            statusLabel.setText("Viewing current accessory");
        }
    }
    
    /**
     * Complete the current order
     */
    private void completeOrder() {
        if (currentAccessory == null) return;
        
        int result = JOptionPane.showConfirmDialog(this,
            "Complete this order?\n\n" + currentAccessory.toString(),
            "Complete Order",
            JOptionPane.YES_NO_OPTION);
        
        if (result == JOptionPane.YES_OPTION) {
            logic.completeOrder(currentAccessory);
            
            JOptionPane.showMessageDialog(this,
                "Order completed!\n\nTotal: $" + 
                String.format("%.2f", currentAccessory.calculatePrice()),
                "Order Complete",
                JOptionPane.INFORMATION_MESSAGE);
            
            displayArea.setText("Order completed and saved to history!\n\n" +
                              currentAccessory.getDetailedDescription() +
                              "\n\n--- Ready for next customer ---");
            
            // Reset for next customer
            currentAccessory = null;
            addCharmBtn.setEnabled(false);
            viewAccessoryBtn.setEnabled(false);
            completeOrderBtn.setEnabled(false);
            saveToFileBtn.setEnabled(false);
            statusLabel.setText("Order completed - ready for next customer");
        }
    }
    
    /**
     * Save the current accessory to a file
     * Demonstrates: Exception Handling
     */
    private void saveToFile() {
        if (currentAccessory == null) return;
        
        try {
            String filename = JOptionPane.showInputDialog(this,
                "Enter filename (without extension):",
                "Save to File",
                JOptionPane.QUESTION_MESSAGE);
            
            if (filename != null && !filename.isEmpty()) {
                logic.saveAccessoryToFile(currentAccessory, filename + ".txt");
                JOptionPane.showMessageDialog(this,
                    "Accessory saved to " + filename + ".txt",
                    "Save Successful",
                    JOptionPane.INFORMATION_MESSAGE);
                statusLabel.setText("Saved to " + filename + ".txt");
            }
        } catch (CharmStoreException e) {
            JOptionPane.showMessageDialog(this,
                "Failed to save file:\n" + e.getMessage(),
                "Save Error",
                JOptionPane.ERROR_MESSAGE);
            statusLabel.setText("Error saving file");
        }
    }
    
    /**
     * Main method to launch the application
     */
    public static void main(String[] args) {
        // Set look and feel
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            // Use default if system look and feel fails
        }
        
        // Launch GUI on Event Dispatch Thread
        SwingUtilities.invokeLater(() -> {
            CharmStoreApp app = new CharmStoreApp();
            app.setVisible(true);
        });
    }
}
