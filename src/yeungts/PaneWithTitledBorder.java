/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package yeungts;

import javafx.geometry.*;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.*;

/**
*  Class name:
*  Author: Kendrick, Tsz-Kin Yeung
*  Date:
*
*  Description:
*/
public class PaneWithTitledBorder extends StackPane{

        private Pane pane;
    public PaneWithTitledBorder(String strTitle, Node node){
        Label lblTitle = new Label(" " + strTitle + " ");
        lblTitle.getStyleClass().add("bordered-titled-title");
        setAlignment(Pos.TOP_CENTER);
        
        pane = new Pane();
        node.getStyleClass().add("bordered-titled-content");
        pane.getChildren().add(node);
        this.getStyleClass().add("bordered-titled-border");
        this.getChildren().addAll(lblTitle, pane);
        this.setMaxWidth(500);
        this.setMinSize(0, 0);
    }
    
}
