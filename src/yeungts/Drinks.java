/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package yeungts;

import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.*;

/**
 * Class name: Drinks
 * Author: Kendrick, Tsz-Kin Yeung 
 * Date: 13 Mar 2019
 *
 * Description:
 */
public class Drinks extends VBox {

    private RadioButton rbtCoke, rbtJuice, rbtMilk;
    private Label lblNum;
    private TextField txtNumber;
    private Button btnAdd, btnMinus;
    private String drink;
    private Integer amt;
    private double price;

    private final double COKE = 1.25;
    private final double JUICE = 1.95;
    private final double MILK = 2.25;

    /**
     * The default constructor 
     */
    public Drinks() {
        super(5);
        super.setPadding(new Insets(10));
        getComponents();
    }

    /**
     * A method that shows all the JavaFX components
     */
    public void getComponents() {
        final ToggleGroup drinks = new ToggleGroup();
        rbtCoke = new RadioButton("Coke");
        rbtCoke.setToggleGroup(drinks);
        rbtJuice = new RadioButton("Juice");
        rbtJuice.setToggleGroup(drinks);
        rbtMilk = new RadioButton("Milk");
        rbtMilk.setToggleGroup(drinks);

        lblNum = new Label("Number of Drinks:");
        txtNumber = new TextField("0");
        txtNumber.setMouseTransparent(true);
        txtNumber.setEditable(false);
        txtNumber.setPrefWidth(50);
        btnAdd = new Button("+");
        btnAdd.setPrefWidth(30);
        btnAdd.setOnAction(e -> {
            Integer newNum = Integer.parseInt(txtNumber.getText()) + 1;
            txtNumber.setText(newNum.toString());
        });
        btnMinus = new Button("-");
        btnMinus.setPrefWidth(30);
        btnMinus.setOnAction(e -> {
            Integer newNum = Integer.parseInt(txtNumber.getText());
            if (newNum > 0) {
                newNum -= 1;
                txtNumber.setText(newNum.toString());
            }
        });
        HBox buttons = new HBox(txtNumber, btnAdd, btnMinus);
        buttons.setMinWidth(0);
        buttons.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        this.getChildren().addAll(rbtCoke, rbtJuice, rbtMilk, lblNum, buttons);
    }

    /**
     * A method to check if there's any item was selected
     * @return whether any of the button is selected
     */
    public boolean isSelected() {
        boolean selected = false;
        if (rbtCoke.isSelected()) {
            selected = true;
        } else if (rbtJuice.isSelected()) {
            selected = true;
        } else if (rbtMilk.isSelected()) {
            selected = true;
        }
        return selected;
    }

    /**
     * Accessor for getting the single price of a selected item
     * @return price of the selected item
     */
    public double getSinglePrice() {
        amt = Integer.parseInt(txtNumber.getText());
        if (amt != 0) {
            if (rbtCoke.isSelected()) {
                drink = "Coke";
                price = COKE;
            } else if (rbtJuice.isSelected()) {
                drink = "Juice";
                price = JUICE;
            } else if (rbtMilk.isSelected()) {
                drink = "Milk";
                price = MILK;
            }
        } else {
            price = 0;
        }
        return price;
    }
    
    /**
     * Accessor for getting the total price of the selected item
     * @return total price of the selected item
     */
    public double getPrice(){
        return amt * price;
    }
    
    /**
     * A clear method to reset selection
     */
    public void clear(){
        rbtCoke.setSelected(false);
        rbtJuice.setSelected(false);
        rbtMilk.setSelected(false);
        txtNumber.setText("0");
    }

    /**
     * Overrides the toString method inherited from object class by
     * concatenating the pizza order
     * @return a String that contains the pizza order
     */
    @Override
    public String toString() {
        return String.format("%-9s\t\t\t%3.2f\n %2d %-6s @ %1.2f", "Drinks:",
                getPrice(), amt, drink, getSinglePrice());
    }
}
