package view.factorys;

import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.*;
import view.viewClasses.StationView;

/**
 * Created by Vilkaz on 20.02.2016.
 */
public class StationGenerator {

    public StationView getStationView(){
        Text text = new Text("test Station");
        Rectangle rectangle = new RectangleFactory().getBasicRectangle();
        StationView stationView = new StationView(text, rectangle);
        return  stationView;
    }
}
