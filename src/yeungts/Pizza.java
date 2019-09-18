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
 * Class name: Pizza
 * Author: Kendrick, Tsz-Kin Yeung 
 * Date: 13 Mar 2019
 *
 * Description: Pizza class creates a pizza type that will be used in other class
 */
public class Pizza extends VBox {

    private RadioButton rbtS, rbtM, rbtL;
    private Label lblNum;
    private TextField txtNumber;
    private Button btnAdd, btnMinus;
    private String size;
    private Integer amt;
    private double price;

    private final double SMALL_PIZZA = 5.25;
    private final double MEDIUM_PIZZA = 7.50;
    private final double LARGE_PIZZA = 9.95;

    /**
     * The default constructor 
     */
    public Pizza() {
        super(5);
        super.setPadding(new Insets(10));
        getComponents();
    }
    
    /**
     * A method that shows all the JavaFX components
     */
    public void getComponents() {
        final ToggleGroup sizes = new ToggleGroup();
        rbtS = new RadioButton("Small");
        rbtS.setToggleGroup(sizes);
        rbtM = new RadioButton("Medium");
        rbtM.setToggleGroup(sizes);
        rbtL = new RadioButton("Large");
        rbtL.setToggleGroup(sizes);
        
        lblNum = new Label("Number of Pizzas:");
        txtNumber = new TextField("0");
        txtNumber.setMouseTransparent(true);
        txtNumber.setEditable(false);
        txtNumber.setPrefWidth(50);
        txtNumber.setMinWidth(0);
        btnAdd = new Button("+");
        btnAdd.setPrefWidth(30);
        btnAdd.setMinWidth(0);
        btnAdd.setOnAction(e -> {
            Integer newNum = Integer.parseInt(txtNumber.getText()) + 1;
            txtNumber.setText(newNum.toString());
        });
        btnMinus = new Button("-");
        btnMinus.setPrefWidth(30);
        btnMinus.setMinWidth(0);
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
        this.getChildren().addAll(rbtS, rbtM, rbtL, lblNum, buttons);
    }

    /**
     * A method to check if there's any item was selected
     * @return whether any of the button is selected
     */
    public boolean isSelected() {
        boolean selected = false;
        if (rbtS.isSelected()) {
            selected = true;
        } else if (rbtM.isSelected()) {
            selected = true;
        } else if (rbtL.isSelected()) {
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
            if (rbtS.isSelected()) {
                size = "Small";
                price = SMALL_PIZZA;
            } else if (rbtM.isSelected()) {
                size = "Medium";
                price = MEDIUM_PIZZA;
            } else if (rbtL.isSelected()) {
                size = "Large";
                price = LARGE_PIZZA;
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
    public double getPrice() {
        return amt * price;
    }

    /**
     * A clear method to reset selection
     */
    public void clear() {
        rbtS.setSelected(false);
        rbtM.setSelected(false);
        rbtL.setSelected(false);
        txtNumber.setText("0");
    }

    /**
     * Overrides the toString method inherited from object class by
     * concatenating the pizza order
     * @return a String that contains the pizza order
     */
    @Override
    public String toString() {
        return String.format("%-9s\t\t\t%3.2f\n %2d %-6s @ %1.2f", "Pizza:",
                getPrice(), amt, size, getSinglePrice());
    }
}
