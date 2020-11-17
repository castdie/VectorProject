/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

//import java.awt.Color; 
import java.util.ArrayList;
import javafx.scene.control.Tooltip;
import javafx.scene.paint.Color;
import javafx.scene.shape.LineTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;

/**
 *
 * @author Enrique Arsenio Rodriguez Rodriguez  
 */
public class VectorNode extends Path implements Comparable<VectorNode>{
    
    private static final double defaultArrowHeadSize = 5.0;
    private static final double defaultStartX = 550.0;
    private static final double defaultStartY = 400.0;
    private static final double defaultScale = 1;
    private static int counter = 0;
    private double startX;
    private double startY;
    private double XValue;
    private double YValue;
    private double endX;
    private double endY;
    private String ID;
    //private double scale;
    
    public VectorNode(double startX, double startY, double X, double Y, double arrowHeadSize){
        
        super();
        
        strokeProperty().bind(fillProperty());
        setFill(Color.BLACK);
        
        //double number = Y * scale;
        double endX = startX + X; 
        double endY = startY - Y;;
            
        this.startX = startX;
        this.startY = startY;
        this.XValue = X;
        this.YValue = Y; 
        this.endX = endX;
        this.endY = endY;
        this.ID = "V" + counter;
        //this.scale = scale;
        counter++;

        //Line
        getElements().add(new MoveTo(startX, startY));
        getElements().add(new LineTo(endX, endY));
        
        //VectorHead
        double angle = Math.atan2((endY - startY), (endX - startX)) - Math.PI / 2.0;
        double sin = Math.sin(angle);
        double cos = Math.cos(angle);
        
        //point1
        double x1 = (- 1.0 / 2.0 * cos + Math.sqrt(3) / 2 * sin) * arrowHeadSize + endX;
        double y1 = (- 1.0 / 2.0 * sin - Math.sqrt(3) / 2 * cos) * arrowHeadSize + endY;
        
        //point2
        double x2 = (1.0 / 2.0 * cos + Math.sqrt(3) / 2 * sin) * arrowHeadSize + endX;
        double y2 = (1.0 / 2.0 * sin - Math.sqrt(3) / 2 * cos) * arrowHeadSize + endY;
        
        getElements().add(new LineTo(x1, y1));
        getElements().add(new LineTo(x2, y2));
        getElements().add(new LineTo(endX, endY));
        
        Tooltip t = new Tooltip(this.ID);
        t.setStyle("-fx-background-color: yellow;" + "-fx-text-fill: black;");
        Tooltip.install(this , t);
        this.setStrokeWidth(3);
        this.setOpacity(1);
    }
    
    public VectorNode(double startX, double startY, double endX, double endY){
        
        this(startX, startY, endX, endY, defaultArrowHeadSize);
        
    }
    
    /*public VectorNode(double X, double Y, double scale){
        
        this(defaultStartX, defaultStartY, X, Y, defaultArrowHeadSize, scale);
        
    }*/
    
    public VectorNode(double X, double Y){
         
        this(defaultStartX, defaultStartY, X, Y, defaultArrowHeadSize);
        
    }
    public VectorNode(){
        this(defaultStartX, defaultStartY, 1, 1, defaultArrowHeadSize);
    }

    public static double getDefaultStartX() {
        return defaultStartX;
    }

    public static double getDefaultStartY() {
        return defaultStartY;
    }

    public double getStartX() {
        return this.startX;
    }

    public double getStartY() {
        return this.startY;
    }

    public double getX() {
        return this.XValue;
    }

    public double getY() {
        return this.YValue;
    }

    public double getEndX() {
        return this.endX;
    }

    public double getEndY() {
        return this.endY;
    }

    public String getName() {
        return this.ID;
    }
    
    //public double getScale(){
    //    return this.scale;
    //}
    
    public void setXValue(double d){
        this.XValue = d;
    }
    
    public void setYValue(double d){
        this.YValue = d;
    }
     
    public VectorNode add(VectorNode v1){
        
        VectorNode v2 = new VectorNode(this.getStartX(), this.getStartY(), this.getX() + v1.getX(), this.getY() + v1.getY());
        v2.setFill(Color.RED);
                
        return v2;        
    }
    
    public static VectorNode add(VectorNode v1, VectorNode v2){
         
         VectorNode v3 = new VectorNode(v1.getStartX(), v1.getStartY(), v1.getX() + v2.getX(), v1.getY() + v2.getY());
         v3.setFill(Color.RED);
         
         return v3;
    }
    
    public static VectorNode add(ArrayList<VectorNode> list){
        
        double totalX = 0;
        double totalY = 0;
        
        for(int i = 0 ; i < list.size() ; i++){
            totalX += list.get(i).getX();
            totalY += list.get(i).getY();
        }
        
        VectorNode v = new VectorNode(totalX,totalY);
        v.setFill(Color.RED);
        
        return v;
    }
    
    public static VectorNode add(ArrayList<VectorNode> list, double scale){
        
        double totalX = 0;
        double totalY = 0;
        
        for(int i = 0 ; i < list.size() ; i++){
            totalX += list.get(i).getX();
            totalY += list.get(i).getY();
        }
        
        VectorNode v = new VectorNode(totalX,totalY);
        v = applyScale(v,scale);
        v.setFill(Color.RED); 
        
        return v;
    }
     
    public VectorNode multiplyScalar(double d){
        
         VectorNode v2 = new VectorNode(this.getStartX(), this.getStartY(), this.getX() * d, this.getY()* d);
         
         return v2;
    } 
    
    public static VectorNode multiplyScalar(VectorNode v, double d){
         
         VectorNode v2 = new VectorNode(v.getStartX(), v.getStartY(), v.getX() * d, v.getY()* d);
         
         return v2;
     }
    
    public double getNorm(){
        return Math.sqrt(this.getX() * this.getX() + this.getY() * this.getY());
    }
    
    public static double getNorm(VectorNode v){
        return Math.sqrt(v.getX() * v.getX() + v.getY() * v.getY());
    }
     
    public static void restartCounter(){
         counter = 1;
    }
     
    //I know I could use toString method here to simplify the task, but I did this method first
    //so I forgot
    public static String textAddition(VectorNode v1, VectorNode v2){
         
         String text = "";
         double xAdd = v1.getX() + v2.getX();
         String addX = " " + xAdd;
         double yAdd = v1.getY() + v2.getY();
         String addY = " " + yAdd;
         
         text = "VECTOR ADDITION: " + "\n" + "\n" + v1.getName().toUpperCase() + " + " + v2.getName().toUpperCase() + "\n" + "= < x1 , y1 > + < x2 , y2 >" + "\n" + 
                 "= < x1 + x2 , y1 + y2 >" +"\n" + "= < " + v1.getX() + " + " + v2.getX() + " , " + v1.getY() + 
                 " + " + v2.getY() + " >" + "\n" + "= <" + addX + " ," + addY + " >";
         
         return text;
    }
    
    public static String textAddition(ArrayList<VectorNode> list){
        
        String intro = "VECTOR ADDITION:" + "\n" + "\n";
        String line1 = "";
        String line2 = "= ";
        String line3 = "= < ";
        String line4 = "= < ";
        String line5 = "= ";
        String text = "";
        
        line5 += add(list).getVectorFormString();
        
        for(int i = 0; i < list.size(); i++){
            if(i<list.size()-1){
                line1 += list.get(i).getName() + " + ";
                line2 += stringLine2(i+1) + " + ";
                line3 += "x" + (i+1) + " + ";
                line4 += list.get(i).getX() + " + ";
            }
            else{
                line1 += list.get(1).getName();
                line2 += stringLine2(i+1);
                line3 += "x" + (i+1) + " , ";
                line4 += list.get(i).getX() + " , ";
            }        
        }
        
        for(int i = 0; i < list.size(); i++){
            if(i<list.size()-1){
                line3 += "y" + (i+1) + " + ";
                line4 += list.get(i).getY() + " + ";
            }
            else{
                line3 += "y" + (i+1) + " >";
                line4 += list.get(i).getY() + " >";
            }        
        }
        
        text = intro + line1 + "\n" + line2 + "\n" + line3 + "\n" + line4 + "\n"+ line5; 
        
        return text;
    }
    
    //draft, not completed
    public int compareTo(VectorNode v){
        
        if(this.getNorm() < v.getNorm())
            return -1;
        else if(this.getNorm() == v.getNorm()) 
            return 0;
        else
            return 1;
        
    }
    
    public boolean equals(VectorNode v){
        
        boolean b= false;
        
        if(this.getX() == v.getX() && this.getY() == v.getY())
            b = true;
        
        return b;
    }
    
    public static boolean equals(VectorNode v1, VectorNode v2){
        
        boolean b = false;
        
        if(v1.getX() == v2.getX() && v1.getY() == v2.getY())
            b = true;
        
        return b;
    }
    
    public static VectorNode applyScale(VectorNode v, double scale){
        
        VectorNode v2 = new VectorNode( v.getX() * scale , v.getY() * scale );
        v2.setXValue(v.getX());
        v2.setYValue(v.getY());
        
        return v2;
    }
    
    public static VectorNode create(double X, double Y, double scale){
        
        VectorNode v = new VectorNode( X , Y );
        v = applyScale( v , scale );
        counter--;
        
        return v;
    }
    
    public static VectorNode create(double X, double Y){
        
        VectorNode v = new VectorNode( X , Y );
        v = applyScale( v , defaultScale );
        counter--;
        
        return v;
    }
    
    public static String stringLine2(int i){
        return "< x" + i + " , " + "y" + i + " >";
    }
    
    public String getVectorFormString(){
        return "< " + this.XValue + " , " + this.YValue + " >";
    }
    
    public String toString(){
         return this.ID + "<" + Math.round(this.XValue) + "," + Math.round(this.YValue) + ">";
    }
    
    
}
