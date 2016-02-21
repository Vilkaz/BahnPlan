package view.viewObjectContainers;

import classes.line.Line;
import view.viewClasses.StationView;

import java.util.ArrayList;

/**
 * Created by Vilkaz on 20.02.2016.
 */


/**
 * All views will be aded here (Stations, and Sonnections)
 * This Class will store them in static variables, so that they are always acceptable.
 */
public class ContentPaneContainer {

    private ArrayList<StationView> stations = new ArrayList<StationView>();

    private ArrayList<Line> lines = new ArrayList<Line>();

    private StationView actualStation;


    private Line actualLine;

    public void addStationToLastLine(StationView stationView){
        ArrayList<Line> lines = this.getLines();
        lines.get(lines.size()-1).addStationView(stationView);
    }

    public void addActualStationToActualLine(){
        this.actualLine.addStationView(this.getActualStation());
    }

    public ArrayList<Integer> getUsedLineNumbers() {
        ArrayList<Integer> lineNumbers = new ArrayList<>();
        for (Line line : this.getLines()) {
            lineNumbers.add(line.getLineNumber());
        }
        return lineNumbers;
    }


    public Line getLineByLineNumber(int lineNumber) {
        Line result = null;
        for (Line line : this.getLines()) {
            if (line.getLineNumber() == lineNumber) {
                result = line;
            }
        }
        return result;
    }

    public void addStationToLine(int lineNumber) {

    }

    public void addStationView(StationView stationView) {
        this.stations.add(stationView);
    }

    public void addLine(Line line) {
        this.lines.add(line);
    }

    //region getter and setter


    public Line getActualLine() {
        return actualLine;
    }

    public void setActualLine(Line actualLine) {
        this.actualLine = actualLine;
    }

    public StationView getActualStation() {
        return actualStation;
    }

    public void setActualStation(StationView actualStation) {
        this.actualStation = actualStation;
    }

    public ArrayList<StationView> getStations() {
        return stations;
    }

    public ArrayList<Line> getLines() {
        return lines;
    }

    //endregion getter and setter


}
