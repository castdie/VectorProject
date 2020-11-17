
package view;

import model.VectorNode;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import model.Vector;



public class VectorView extends HBox{
    
    private Text titleText = new Text("VECTOR ADDITION");
    private TextField scalar = new TextField();
    private TextField xField = new TextField();
    private TextField yField = new TextField();
    private Button create = new Button("Create");
    private Button calculate = new Button("Calculate");
    private Button clear = new Button("Clear");
    private TableView tableView = new TableView();
    private Text vectors = new Text("Vectors :");
    private Text resultantVector = new Text("Resultant Vector :");
    private Text resVecXY = new Text();
    private Pane leftPane = new Pane();  
    private VBox rightVBox = new VBox(30);
    private HBox HB1 = new HBox(4);
    private HBox HB2 = new HBox(5);
    private double scale = 5;
    private TableColumn<Vector, String> IDColumn = new TableColumn<>("Vector ID"); 
    private TableColumn<Vector, Double> XColumn = new TableColumn<>("X Value"); 
    private TableColumn<Vector, Double> YColumn = new TableColumn<>("Y Value"); 
    
    
    public VectorView(){
        
        leftPane.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
        rightVBox.setAlignment(Pos.CENTER);
        titleText.setFont(Font.font("Arial Black", FontWeight.BLACK, 15));
        vectors.setFont(Font.font("Arial Black", FontWeight.BLACK, 12));
        resultantVector.setFont(Font.font("Arial Black", FontWeight.BLACK, 12));
        scalar.setPromptText("Scalar");
        xField.setPromptText("X Value");
        yField.setPromptText("Y Value");
        create.setMinSize(195, 30);
        calculate.setMinSize(195, 30);
        clear.setMinSize(250, 50);
        leftPane.setMinSize(1100, 800);
        scalar.setMaxWidth(130);
        xField.setMaxWidth(130);
        yField.setMaxWidth(130);
        HB2.setAlignment(Pos.CENTER);
        scalar.setFocusTraversable(false);
        xField.setFocusTraversable(false);
        yField.setFocusTraversable(false);
        IDColumn.setMinWidth(100);
        IDColumn.setCellValueFactory(new PropertyValueFactory<>("ID"));
        XColumn.setMinWidth(150);
        XColumn.setCellValueFactory(new PropertyValueFactory<>("XValue"));
        YColumn.setMinWidth(150);
        YColumn.setCellValueFactory(new PropertyValueFactory<>("YValue"));
        tableView.setEditable(true);
        tableView.setMaxWidth(395);
        tableView.setMaxHeight(350);
        tableView.getColumns().addAll(IDColumn, XColumn, YColumn);
        createLines(leftPane);
        HB1.getChildren().addAll(scalar, xField, yField);
        HB2.getChildren().addAll(create, calculate);
        rightVBox.getChildren().addAll(titleText, HB1, HB2, vectors, tableView, clear, resultantVector, resVecXY);
        this.getChildren().addAll(leftPane, rightVBox);
    }
    
    public void createLines(Pane pane){
        int i;
        for (i = 0; i <= 1100; i += 25){
            Line line = new Line(i, 0, i, 800);
            pane.getChildren().add(line);
        }
        
        for (i = 0; i <= 800; i += 25){
            Line line = new Line(0, i, 1100, i);
            pane.getChildren().add(line);
        }
        
        Circle circle = new Circle(550,400,6);
        
        
        Line xLine = new Line(0,400,1100,400);
        xLine.setStrokeWidth(2.5);
        
        Line x1Line = new Line(1097,400,1092,395);
        x1Line.setStrokeWidth(2.5);
        
        Line x2Line = new Line(1097,400,1092,405);
        x2Line.setStrokeWidth(2.5);
        
        
        Line yLine = new Line(550,0,550,800);
        yLine.setStrokeWidth(2.5);
        
        Line y1Line = new Line(550,3,545,8);
        y1Line.setStrokeWidth(2.5);
        
        Line y2Line = new Line(550,3,555,8);
        y2Line.setStrokeWidth(2.5);
        
        Line xScale = new Line(545,375,555,375);
        xScale.setStrokeWidth(3);
        
        Line yScale = new Line(575,395,575,405);
        yScale.setStrokeWidth(3);
        
        Text txtx = new Text(1080,415,"x");
        txtx.setFont(Font.font("Arial Black", FontWeight.BLACK, 14));
       
        Text txty = new Text(560,20,"y");
        txty.setFont(Font.font("Arial Black", FontWeight.BLACK, 14));
        
     
        Text scaleTxt = new Text(575,418,String.valueOf(scale));
        scaleTxt.setFont(Font.font("Arial Black", FontWeight.BLACK, 12));
        Text scaleTxt2 = new Text(525,370,String.valueOf(scale));
        scaleTxt2.setFont(Font.font("Arial Black", FontWeight.BLACK, 12));
        
        pane.getChildren().addAll(circle, xLine,x1Line, x2Line, yLine, y1Line, y2Line, txtx, txty, xScale, yScale, scaleTxt, scaleTxt2);
    }
    
    public void setCreateHandler(EventHandler handler){
        
        create.setOnAction(handler);
        
    }
    
    public void setCalculateHandler(EventHandler handler){
        
        calculate.setOnAction(handler);
        
    }
    
    public void setClearHandler(EventHandler handler){
        
        clear.setOnAction(handler);
        
    }

    public Text getTitleText() {
        return titleText;
    }

    public void setTitleText(Text titleText) {
        this.titleText = titleText;
    }

    public TextField getScalar() {
        return scalar;
    }

    public void setScalar(TextField scalar) {
        this.scalar = scalar;
    }

    public TextField getxField() {
        return xField;
    }

    public void setxField(TextField xField) {
        this.xField = xField;
    }

    public TextField getyField() {
        return yField;
    }

    public void setyField(TextField yField) {
        this.yField = yField;
    }

    public Button getCreate() {
        return create;
    }

    public void setCreate(Button create) {
        this.create = create;
    }

    public Button getCalculate() {
        return calculate;
    }

    public void setCalculate(Button calculate) {
        this.calculate = calculate;
    }

    public Button getClear() {
        return clear;
    }

    public void setClear(Button clear) {
        this.clear = clear;
    }

    public Text getVectors() {
        return vectors;
    }

    public void setVectors(Text vectors) {
        this.vectors = vectors;
    }

    public Text getResultantVector() {
        return resultantVector;
    }

    public void setResultantVector(Text resultantVector) {
        this.resultantVector = resultantVector;
    }

    public Text getResVecXY() {
        return resVecXY;
    }

    public void setResVecXY(Text resVecXY) {
        this.resVecXY = resVecXY;
    }

    public Pane getLeftPane() {
        return leftPane;
    }

    public void setLeftPane(Pane leftPane) {
        this.leftPane = leftPane;
    }

    public VBox getRightVBox() {
        return rightVBox;
    }

    public void setRightVBox(VBox rightVBox) {
        this.rightVBox = rightVBox;
    }

    public HBox getHB1() {
        return HB1;
    }

    public void setHB1(HBox HB1) {
        this.HB1 = HB1;
    }

    public HBox getHB2() {
        return HB2;
    }

    public void setHB2(HBox HB2) {
        this.HB2 = HB2;
    }

    public TableColumn<Vector, String> getIDColumn() {
        return IDColumn;
    }

    public void setIDColumn(TableColumn<Vector, String> IDColumn) {
        this.IDColumn = IDColumn;
    }

    public TableColumn<Vector, Double> getXColumn() {
        return XColumn;
    }

    public void setXColumn(TableColumn<Vector, Double> XColumn) {
        this.XColumn = XColumn;
    }

    public TableColumn<Vector, Double> getYColumn() {
        return YColumn;
    }

    public void setYColumn(TableColumn<Vector, Double> YColumn) {
        this.YColumn = YColumn;
    }

    public TableView getTableView() {
        return tableView;
    }

    public void setTableView(TableView tableView) {
        this.tableView = tableView;
    }

    public double getScale() {
        return scale;
    }

    public void setScale(double scale) {
        this.scale = scale;
    }
    
    
    
}
