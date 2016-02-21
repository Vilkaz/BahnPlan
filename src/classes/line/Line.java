package classes.line;

import javafx.scene.paint.Color;
import view.viewClasses.StationView;

import java.util.ArrayList;

/**
 * Created by Vilkaz on 17.02.2016.
 */
public class Line {
    private Color color;
    private int lineNumber;
    private ArrayList<StationView> stations = new ArrayList<StationView>();


    public Line() {
    }

    public Line(int lineNumber, Color color) {
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

    public Line(int lineNumber){
        this.lineNumber = lineNumber;
    }

    public int getLineNumber() {
        return lineNumber;
    }

    //endregion getter and setter

}
