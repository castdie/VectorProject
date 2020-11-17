/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.List;

/**
 *
 * @author Diego
 */
public class AlgebraicVector {
    private double XValue;
    private double YValue;
    private String ID;
    private static int counter = 0;

    public AlgebraicVector(double XValue, double YValue, String ID) {
        this.XValue = XValue;
        this.YValue = YValue;
        counter++;
        this.ID = "V" + counter;
    }
    
    public AlgebraicVector(double XValue, double YValue){
        super();
        this.ID = "";            
    }

    public AlgebraicVector() {
        super();
        this.XValue = 1;
        this.YValue = 1;
        
    }

    public double getXValue() {
        return this.XValue;
    }

    public void setXValue(double XValue) {
        this.XValue = XValue;
    }

    public double getYValue() {
        return this.YValue;
    }

    public void setYValue(double YValue) {
        this.YValue = YValue;
    }

    public String getID() {
        return this.ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }
    
    public AlgebraicVector add(AlgebraicVector v1){
        
        AlgebraicVector v2 = new AlgebraicVector(this.getXValue() + v1.getXValue(), this.getYValue() + v1.getYValue());       
        return v2;        
    }
    
    public static AlgebraicVector add(AlgebraicVector v1, AlgebraicVector v2){
         
         AlgebraicVector v3 = new AlgebraicVector(v1.getXValue() + v2.getXValue(), v1.getYValue() + v2.getYValue());
         return v3;
    }
    
    public static AlgebraicVector add(List<AlgebraicVector> list){
        
        double totalX = 0;
        double totalY = 0;
        
        for(int i = 0 ; i < list.size() ; i++){
            totalX += list.get(i).getXValue();
            totalY += list.get(i).getYValue();
        }
        
        AlgebraicVector v = new AlgebraicVector(totalX,totalY);
        return v;
    }
    
    public static AlgebraicVector add(List<AlgebraicVector> list, double scale){
        
        double totalX = 0;
        double totalY = 0;
        
        for(int i = 0 ; i < list.size() ; i++){
            totalX += list.get(i).getXValue();
            totalY += list.get(i).getYValue();
        }
        
        AlgebraicVector v = new AlgebraicVector(totalX,totalY);
        v = multiplyScalar(v,scale);
        
        
        return v;
    }
     
    public AlgebraicVector multiplyScalar(double scalar){
        
         AlgebraicVector v2 = new AlgebraicVector(this.getXValue() * scalar, this.getYValue()* scalar);
         
         return v2;
    } 

    
    public static AlgebraicVector multiplyScalar(AlgebraicVector v, double scalar){
        
        AlgebraicVector v2 = new AlgebraicVector( v.getXValue() * scalar , v.getYValue() * scalar );
        
        return v2;
    }
    
    
}
