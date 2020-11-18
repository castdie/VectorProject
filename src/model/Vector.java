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
 public class Vector {
    private double XValue;
    private double YValue;
    private String ID;
    private static int counter = 0;

    public Vector(double XValue, double YValue, String ID) {
        this.XValue = XValue;
        this.YValue = YValue;
        this.ID = ID;
    }
    
    public Vector(double XValue, double YValue){
        this.XValue = XValue;
        this.YValue = YValue;
        this.ID = "V" + counter;          
    }

    public Vector() {
        this.XValue = 1;
        this.YValue = 1;
        counter++;
        this.ID = "V" + counter;

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
    
    public static int getCounter(){
        return counter;
    }
    public Vector add(Vector v1){
        
        Vector v2 = new Vector(this.getXValue() + v1.getXValue(), this.getYValue() + v1.getYValue());       
        return v2;        
    }
    
    public static Vector add(Vector v1, Vector v2){
         
         Vector v3 = new Vector(v1.getXValue() + v2.getXValue(), v1.getYValue() + v2.getYValue());
         return v3;
    }
    
    public static Vector add(List<Vector> list){
        
        double totalX = 0;
        double totalY = 0;
        
        for(int i = 0 ; i < list.size() ; i++){
            totalX += list.get(i).getXValue();
            totalY += list.get(i).getYValue();
        }
        
        Vector v = new Vector(totalX,totalY);
        return v;
    }
    
    /*public static Vector add(List<Vector> list, double scale){
        
        double totalX = 0;
        double totalY = 0;
        
        for(int i = 0 ; i < list.size() ; i++){
            totalX += list.get(i).getXValue();
            totalY += list.get(i).getYValue();
        }
        
        Vector v = new Vector(totalX,totalY);
        v = multiplyScalar(v,scale);
        
        
        return v;
    }*/
     
    public Vector multiplyScalar(double scalar){
        
         Vector v2 = new Vector(this.getXValue() * scalar, this.getYValue()* scalar);
         
         return v2;
    } 

    
    public static Vector multiplyScalar(Vector v, double scalar){
        
        Vector v2 = new Vector( v.getXValue() * scalar , v.getYValue() * scalar );
        
        return v2;
    }
    
    public static void restartCounter(){
         counter = 0;
    }
    
    public String toString(){
         return "V" + "< " + Math.round(this.XValue) + " , " + Math.round(this.YValue) + " >";
    }
    
}
