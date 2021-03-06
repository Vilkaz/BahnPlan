package view.viewClasses;

import classes.neighbor.Neighbor;
import classes.trainLine.TrainLine;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import view.defaultValues.GeneralSettings;
import view.factorys.StationSymbolFactory;

import java.util.ArrayList;

/**
 * Created by Vilkaz on 20.02.2016.
 */



public class StationView {
    private int id;
    private Text name;
    double x;
    double y;
    int zone;
    Color color;
    boolean endstation;
    private MyRectangle symbol;
    private ArrayList<Neighbor> neighbors = new ArrayList<>();
    private ArrayList<TrainLine> trainLines = new ArrayList<>();


    /**
     * this constructor will be called when the initial StationView from
     * LineContsructor data will be initiated.
     * @param trainLine
     */
    public StationView(TrainLine trainLine, MouseEvent event) {
        this.color = trainLine.getColor();
        this.id = trainLine.getLineNumber()*100;
        this.symbol = new StationSymbolFactory().getSymbolByStationView(this);
        int constante = new GeneralSettings().getDefaultStationSymbolWidth();
        symbol.setX(event.getSceneX()-constante/2);
        symbol.setY(event.getSceneY()-constante);
        this.name = new Text("");
        addTrainLine(trainLine);
    }

    public StationView() {
    }




    public void addTrainLine(TrainLine trainLine){
        this.getTrainLines().add(trainLine);
    }

    public StationView(StationView oldStationView){
        this.id = oldStationView.getId();
        this.name = oldStationView.getName();
        this.x = oldStationView.getX();
        this.y = oldStationView.getY();
        this.zone = oldStationView.getZone();
        this.color = oldStationView.getColor();
        this.neighbors = oldStationView.getNeighbors();
        this.symbol = oldStationView.getSymbol();
    }

    public void addNeighbor(Neighbor neighbor){
        this.getNeighbors().add((neighbor));
    }

    public StationView(Coordinates coordinates,TrainLine trainLine) {
        this.id = getStationViewIDByTrainLine(trainLine);
        this.x = coordinates.getX();
        this.y = coordinates.getY();
        this.color = trainLine.getColor();
    }

    private int getStationViewIDByTrainLine(TrainLine trainLine){
        if (trainLine.getStations().size()==0){
            return  trainLine.getLineNumber()*100;
        } else {
            ArrayList<StationView> stations = trainLine.getStations();
            int size = stations.size();
            return stations.get(size-1).getId()+1;
        }
    }

    public StationView(Color color) {
        this.color = color;
    }

    public StationView(Text name, double x, double y, int zone, boolean endstation, MyRectangle symbol) {
        this.name = name;
        this.x = x;
        this.y = y;
        this.zone = zone;
        this.endstation = endstation;
        this.symbol = symbol;
    }

    public int getLastNeighborRange(){
        return this.getNeighbors().get(getNeighbors().size()-1).getDistance();
    }

    public StationView(Text name, MyRectangle symbol) {
        this.name = name;
        this.symbol = symbol;
    }

    public Coordinates getSymbolMidleCoordinates() {
        double x = this.getX()+new GeneralSettings().getDefaultStationSymbolWidth()/2;
        double y = this.getY()+new GeneralSettings().getDefaultStationSymbolWidth()/2;
        return new Coordinates(x, y);
    }

    public void setFirstNeighbor(Neighbor neighbor){
        this.getNeighbors().clear();
        this.getNeighbors().add(neighbor);
    }


    public void getJson(){

    }

    //region getter and setter


    public void setNeighbors(ArrayList<Neighbor> neighbors) {
        this.neighbors = neighbors;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public ArrayList<TrainLine> getTrainLines() {
        return trainLines;
    }

    public void setTrainLines(ArrayList<TrainLine> trainLines) {
        this.trainLines = trainLines;
    }

    public ArrayList<Neighbor> getNeighbors() {
        return neighbors;
    }

    public Color getColor() {
        return color;
    }

    public void setName(Text name) {
        this.name = name;
    }

    public void setSymbol(MyRectangle symbol) {
        this.symbol = symbol;
    }

    public void setX(double x) {
        this.x = x;
    }

    public void setY(double y) {
        this.y = y;
    }

    public void setZone(int zone) {
        this.zone = zone;
    }

    public void setEndstation(boolean endstation) {
        this.endstation = endstation;
    }

    public Text getName() {
        return name;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public int getZone() {
        return zone;
    }

    public boolean isEndstation() {
        return endstation;
    }

    public MyRectangle getSymbol() {
        return symbol;
    }



    //endregion getter and setter
}
