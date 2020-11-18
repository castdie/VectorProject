/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.SelectionMode;
import model.Vector;
import model.VectorNode;
import view.VectorView;

/**
 *
 * @author Diego Castillo
 */
public class VectorController{
    VectorView view;
    ObservableList<VectorNode> gridVectorsList = FXCollections.observableArrayList();
    ObservableList<Vector> tableVectorsList = FXCollections.observableArrayList();
    VectorNode resultGridVector;
    
    public VectorController(VectorView v) {
        
        this.view = v;
        v.getTableView().getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        
        CreateEventHandler handler = new CreateEventHandler();
        this.view.setCreateHandler(handler);
       
        ClearEventHandler handler2 = new ClearEventHandler();
        this.view.setClearHandler(handler2);
        
        CalculateEventHandler handler3 = new CalculateEventHandler();
        this.view.setCalculateHandler(handler3);
    }
    


    class CreateEventHandler implements EventHandler{

        @Override
        public void handle(Event event) {
           
           Vector tableVector = new Vector();
           VectorNode gridVector = new VectorNode();
           try{
               //Defining the vector's components for the TableView
               tableVector.setXValue(Double.parseDouble(view.getxField().getText()));
               tableVector.setYValue(Double.parseDouble(view.getyField().getText()));
               tableVector = tableVector.multiplyScalar(Double.parseDouble(view.getScalar().getText()));
               
               //Defining the vector's compononents for the grid
               gridVector.setXValue(Double.parseDouble(view.getxField().getText()));
               gridVector.setYValue(Double.parseDouble(view.getyField().getText()));
               gridVector = gridVector.multiplyScalar(Double.parseDouble(view.getScalar().getText()));
               
           }catch(NumberFormatException e){
            Alert errorAlert = new Alert(AlertType.ERROR);
            errorAlert.setHeaderText("Input not valid");
            errorAlert.setContentText("Your values must be valid numbers");
            errorAlert.showAndWait();
            Vector.restartCounter();
            return;
           }
           
           tableVectorsList.add(tableVector);
           view.getTableView().getItems().add(tableVector);
           gridVectorsList.add(gridVector);
           view.getLeftPane().getChildren().add(gridVector);
          
           view.getxField().clear();
           view.getyField().clear();
           view.getScalar().clear();
        }
    
    }
    
    class ClearEventHandler implements EventHandler{
        
        @Override
        public void handle(Event event) {
        view.getTableView().getItems().clear();
        view.getLeftPane().getChildren().removeAll(gridVectorsList);
        view.getLeftPane().getChildren().remove(resultGridVector);
        view.getResVecXY().setText("");
        gridVectorsList.clear();
        tableVectorsList.clear();
        Vector.restartCounter();
        
        }
        
    }
    
    class CalculateEventHandler implements EventHandler{
        
         @Override
        public void handle(Event event) {
            Vector resultTableVector = Vector.add(tableVectorsList);
            view.getResVecXY().setText(resultTableVector.toString());
            
            resultGridVector = VectorNode.add(gridVectorsList);
            view.getLeftPane().getChildren().add(resultGridVector);
        }
    }
}