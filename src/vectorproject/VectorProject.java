/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vectorproject;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import view.VectorView;

/**
 *
 * @author 
 */
public class VectorProject extends Application {
    
    @Override
    public void start(Stage primaryStage) {
       VectorView v = new VectorView();
        StackPane root = new StackPane();
        root.getChildren().add(v);
        
        Scene scene = new Scene(root);
            
        primaryStage.setTitle("Vectors");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
