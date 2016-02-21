package view.factorys;

import classes.line.Line;
import javafx.scene.Node;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import view.viewClasses.StationView;


/**
 * Created by Vilkaz on 21.02.2016.
 */
public class StationViewFactory {

    public StationView getStationView(VBox stationCreator, MouseEvent event, Line line){
        TextField nameTextField = (TextField) stationCreator.getChildren().get(0);
        String name = nameTextField.getText();
        HBox zoneHBox = (HBox) stationCreator.getChildren().get(1);
        ChoiceBox zoneChoiceBox = (ChoiceBox) zoneHBox.getChildren().get(1);
        StationView stationView = new StationView(
                new Text(name),
                event.getSceneX(),
                event.getSceneY(),
                (int) zoneChoiceBox.getValue(),
                false,
                new RectangleFactory().getRectangleByColor(line.getColor()));
        return stationView;
    }
}
