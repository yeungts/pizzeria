/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package yeungts;

import java.util.GregorianCalendar;
import javafx.application.Application;
import javafx.event.*;
import javafx.geometry.*;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;

/**
 * Author: Kendrick, Tsz-Kin Yeung 
 * Program: Pizzeria
 * Date: 13 Mar 19
 *
 * Description: The main class for pizzeria program with using other classes
 */
public class Pizzeria extends Application implements EventHandler {

    private MenuBar menuBar;
    private Menu fileMenu;
    private MenuItem exitItem;
    private Pizza pizza;
    private Drinks drinks;
    private Toppings toppings;
    private TextArea txaOrdered;
    private PaneWithTitledBorder PTBpizza, PTBdrinks, PTBtoppings, PTBoutput;
    private Button btnOK, btnCancel;
    private final double TAX_RATE = 0.13;

    @Override
    public void start(Stage primaryStage) {
        VBox root = new VBox(10);
        root.setPrefSize(550, 475);

        fileMenu = new Menu("_File");
        exitItem = new MenuItem("Exi_t");
        fileMenu.setMnemonicParsing(true);
        exitItem.setMnemonicParsing(true);
        exitItem.setAccelerator(new KeyCodeCombination(KeyCode.T, KeyCodeCombination.CONTROL_DOWN));
        exitItem.setOnAction(this);
        fileMenu.getItems().add(exitItem);
        menuBar = new MenuBar(fileMenu);
        
        pizza = new Pizza();
        toppings = new Toppings();
        drinks = new Drinks();
        
        pizza.setMinSize(0, 0);
        toppings.setMinSize(0, 0);
        drinks.setMinSize(0, 0);
        
        PTBpizza = new PaneWithTitledBorder("Pizza", pizza);
        PTBtoppings = new PaneWithTitledBorder("Toppings", toppings);
        PTBdrinks = new PaneWithTitledBorder("Drinks", drinks);
        
        HBox pnlOptions = new HBox(15, PTBpizza, PTBtoppings, PTBdrinks);
        pnlOptions.setAlignment(Pos.CENTER);

        txaOrdered = new TextArea();
        txaOrdered.setEditable(false);
        txaOrdered.setMinSize(0, 0);
        
        PTBoutput = new PaneWithTitledBorder("Ordered Items", txaOrdered);
        HBox pnlOutput = new HBox(PTBoutput);
        pnlOutput.setPadding(new Insets(5));
        pnlOutput.setAlignment(Pos.CENTER);

        btnOK = new Button("_OK");
        btnOK.setPrefWidth(70);
        btnOK.setMnemonicParsing(true);
        btnOK.setOnAction(this);
        btnCancel = new Button("_Cancel");
        btnCancel.setPrefWidth(70);
        btnCancel.setMnemonicParsing(true);
        btnCancel.setOnAction(this);

        HBox pnlButtons = new HBox(10, btnOK, btnCancel);
        pnlButtons.setAlignment(Pos.BOTTOM_CENTER);
        pnlButtons.setPadding(new Insets(5));

        root.getChildren().addAll(menuBar, pnlOptions, pnlOutput, pnlButtons);
        Scene scene = new Scene(root);
        scene.getStylesheets().add("styles.css");
        
        primaryStage.setTitle("My Pizzeria");
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

    public double getSubtotal(){
        if (pizza.isSelected()) {
            return pizza.getPrice() + toppings.getPrice() + drinks.getPrice();
        } else {
            return drinks.getPrice();
        }
    }
    
    public double getHST(){
        return getSubtotal() * TAX_RATE;
    }
    
    public double getTotal(){
        return getSubtotal() + getHST();
    }
    
    @Override
    public void handle(Event e) {
        GregorianCalendar calendar = new GregorianCalendar();
        if (e.getSource() == exitItem) {
            System.exit(0);
        } else if (e.getSource() == btnOK) {
            String output = "    " + calendar.getTime() + "\n\t\tReceipt\n";
            if (pizza.isSelected() || drinks.isSelected()) {
                if (pizza.getSinglePrice() != 0 && drinks.getSinglePrice() != 0) {
                    output += pizza.toString() + toppings.toString() +
                            "\n" + drinks.toString() + getReceipt();
                } else if (pizza.getSinglePrice() != 0 ) {
                    output += pizza.toString() + toppings.toString() 
                           + getReceipt();
                } else if (drinks.getSinglePrice() != 0) {
                    output += drinks.toString() + getReceipt();
                } else {
                    output = "Please enter the amount for selected items";
                }
            } else {
                output = "Please select a pizza size and/or a drink.";
            }
            txaOrdered.setText(output);
        } else if (e.getSource() == btnCancel) {
            pizza.clear();
            toppings.clear();
            drinks.clear();
            txaOrdered.clear();
        }
    }
    
    public String getReceipt(){
        return String.format("\n====================================="
                + "\n%-9s\t\t\t%4.2f\n%-9s\t\t\t%4.2f\n%-9s\t\t\t%4.2f",
                "Subtotal:", getSubtotal(), "HST:", getHST(),
                "Total:", getTotal());
    }
}
