package classes.trainLine;

import javafx.scene.paint.Color;
import view.viewClasses.StationView;

import java.util.ArrayList;

/**
 * Created by Vilkaz on 17.02.2016.
 */
public class TrainLine {
    private Color color;
    private int lineNumber;
    private ArrayList<StationView> stations = new ArrayList<StationView>();


    public TrainLine() {
    }

    public TrainLine(int lineNumber, Color color) {
        this.lineNumber = lineNumber;
        this.color = color;
    }

    public void addStationView(StationView stationView){
        this.getStations().add(stationView);
    }


    //region getter and setter


    public ArrayList<StationView> getStations() {
        return stations;
    }

    public Color getColor() {
        return color;
    }

    public TrainLine(int lineNumber){
        this.lineNumber = lineNumber;
    }

    public int getLineNumber() {
        return lineNumber;
    }

    //endregion getter and setter

}
