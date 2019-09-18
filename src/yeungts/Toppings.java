/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package yeungts;

import javafx.geometry.Insets;
import javafx.scene.control.CheckBox;
import javafx.scene.layout.VBox;

/**
 * Class name: Toppings
 * Author: Kendrick, Tsz-Kin Yeung
 * Date: 13 Mar 2019
 *
 * Description: Toppings class creates a Toppings type that will be used in other
 * class
 */
public class Toppings extends VBox {

    private CheckBox chkCheese;
    private CheckBox chkMushrooms;
    private CheckBox chkOlives;
    private CheckBox chkPepperoni;
    private double price = 0;
    private String output;

    private final double CHEESE = 1.00;
    private final double MUSHROOMS = 1.25;
    private final double OLIVES = 1.50;
    private final double PEPPERONI = 1.75;

    /**
     * The default constructor 
     */
    public Toppings() {
        super(15);
        super.setPadding(new Insets(10));
        getComponents();
    }

    /**
     * A method that shows all the JavaFX components
     */
    public void getComponents() {
        chkCheese = new CheckBox("Cheese");
        chkMushrooms = new CheckBox("Mushrooms");
        chkOlives = new CheckBox("Olives");
        chkPepperoni = new CheckBox("Pepperoni");
        this.getChildren().addAll(chkCheese, chkPepperoni, chkMushrooms, chkOlives);
    }

    /**
     * Accessor for getting the total price of the selected toppings, and add to
     * a string that contains selected toppings
     * @return total price of selected toppings
     */
    public double getPrice() {
        price = 0;
        String toppings = "";
        if (chkCheese.isSelected() || chkMushrooms.isSelected()
                || chkOlives.isSelected() || chkPepperoni.isSelected()) {
            if (chkCheese.isSelected()) {
                price += CHEESE;
                toppings += String.format("\n %2d %-9s @ %1.2f", 1,
                        chkCheese.getText(), CHEESE);
            }
            if (chkMushrooms.isSelected()) {
                price += MUSHROOMS;
                toppings += String.format("\n %2d %-9s @ %1.2f", 1,
                        chkMushrooms.getText(), MUSHROOMS);
            }
            if (chkOlives.isSelected()) {
                price += OLIVES;
                toppings += String.format("\n %2d %-9s @ %1.2f", 1,
                        chkOlives.getText(), OLIVES);
            }
            if (chkPepperoni.isSelected()) {
                price += PEPPERONI;
                toppings += String.format("\n %2d %-9s @ %1.2f", 1,
                        chkPepperoni.getText(), PEPPERONI);
            }
            output = String.format("\n%-9s\t\t\t%3.2f", "Toppings:", price)
                    + toppings;
        }
        return price;
    }

    /**
     * A clear method to reset selection
     */
    public void clear() {
        chkCheese.setSelected(false);
        chkMushrooms.setSelected(false);
        chkOlives.setSelected(false);
        chkPepperoni.setSelected(false);
    }

    /**
     * Overrides the toString method inherited from object class by
     * concatenating the toppings order
     * @return a String that contains the topping order
     */
    @Override
    public String toString() {
        if (getPrice() != 0) {
            return output;
        } else {
            return "";
        }
    }

}
