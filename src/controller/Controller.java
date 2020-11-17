/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import model.VectorNode;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.SelectionMode;
import model.Vector;
import view.VectorView;

/**
 *
 * @author Diego Castillo
 */
public class Controller {
    VectorView view;
    ObservableList<Vector> vectors = FXCollections.observableArrayList();
    VectorNode node;
    
    public Controller(VectorView v) {
        
        this.view = v;
        v.getTableView().getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        
        CreateEventHandler handler = new CreateEventHandler();
        this.view.setCreateHandler(handler);
       
        ClearEventHandler handler2 = new ClearEventHandler();
        this.view.setClearHandler(handler2);
    }
    


    class CreateEventHandler implements EventHandler{

        @Override
        public void handle(Event event) {
           Vector addedVector = new Vector();
            
           try{
               
               addedVector.setXValue(Double.parseDouble(view.getxField().getText()));
               addedVector.setYValue(Double.parseDouble(view.getyField().getText()));
               addedVector = addedVector.multiplyScalar(Double.parseDouble(view.getScalar().getText()));
               
           }catch(NumberFormatException e){
            Alert errorAlert = new Alert(AlertType.ERROR);
            errorAlert.setHeaderText("Input not valid");
            errorAlert.setContentText("Your values must be valid numbers");
            errorAlert.showAndWait();
           }
           
           vectors.add(addedVector);
           view.getTableView().getItems().add(addedVector);
           view.getxField().clear();
           view.getyField().clear();
           view.getScalar().clear();
        }
    
    }
    
    class ClearEventHandler implements EventHandler{
        
        @Override
        public void handle(Event event) {
        view.getTableView().getItems().clear();
        vectors.clear();
        }
        
    }
}