/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Helper;

import javafx.scene.layout.BorderPane;

/**
 *
 * @author anonymous588
 */
public abstract class ViewAbstract {
    
    protected BorderPane mainLayout;
    
    public ViewAbstract(BorderPane mainLayout) {
        this.mainLayout=mainLayout;
    }
    public ViewAbstract(){}
    
    public void setPane(BorderPane mainLayout){
        this.mainLayout=mainLayout;
    }

    /**
     * get fxml file to draw
     */
    public abstract void draw();
    
}
