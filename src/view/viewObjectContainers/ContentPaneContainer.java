package view.viewObjectContainers;

import classes.neighbor.Neighbor;
import classes.trainLine.TrainLine;
import javafx.scene.shape.Line;
import view.viewClasses.Coordinates;
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

    private ArrayList<TrainLine> trainLines = new ArrayList<TrainLine>();

    private TrainLine actualTrainLine;

    private Coordinates coordinates;


    private Line line;

    public void addStationToLastLine(StationView stationView) {
        ArrayList<TrainLine> trainLines = this.getTrainLines();
        trainLines.get(trainLines.size() - 1).addStationView(stationView);
    }


    public ArrayList<Integer> getUsedLineNumbers() {
        ArrayList<Integer> lineNumbers = new ArrayList<>();
        for (TrainLine trainLine : this.getTrainLines()) {
            lineNumbers.add(trainLine.getLineNumber());
        }
        return lineNumbers;
    }


    public TrainLine getLineByLineNumber(int lineNumber) {
        TrainLine result = null;
        for (TrainLine trainLine : this.getTrainLines()) {
            if (trainLine.getLineNumber() == lineNumber) {
                result = trainLine;
            }
        }
        return result;
    }

    public void addStationToLine(int lineNumber) {

    }

    public void addStationView(StationView stationView) {
        this.stations.add(stationView);
    }

    public void addLine(TrainLine trainLine) {
        this.trainLines.add(trainLine);
    }


    public int getLengthFromLine() {
        double x1 = this.line.getStartX();
        double y1 = this.line.getStartY();
        double x2 = this.line.getEndX();
        double y2 = this.line.getEndY();
        double xLength = Math.abs(x1 - x2);
        double yLength = Math.abs(y1 - y2);
        double lineLength = Math.sqrt(Math.pow(xLength, 2) + Math.pow(yLength, 2));
        return (int) lineLength;
    }

    //region getter and setter


    public TrainLine getActualTrainLine() {
        return actualTrainLine;
    }

    public void setActualTrainLine(TrainLine actualTrainLine) {
        this.actualTrainLine = actualTrainLine;
    }

    public ArrayList<StationView> getStations() {
        return stations;
    }

    public ArrayList<TrainLine> getTrainLines() {
        return trainLines;
    }

    public Line getLine() {
        return line;
    }

    public void setLine(Line line) {
        this.line = line;
    }

    public Coordinates getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(Coordinates coordinates) {
        this.coordinates = coordinates;
    }

//endregion getter and setter


}
