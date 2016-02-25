package view.factorys;

import classes.trainLine.TrainLine;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import view.viewClasses.MyRectangle;
import view.viewClasses.StationView;


/**
 * Created by Vilkaz on 21.02.2016.
 */
public class StationViewFactory {

    public StationView getStationView(VBox stationCreator, MouseEvent event, TrainLine trainLine){
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
                new StationSymbolFactory().getBasicSymbol());
        stationView.setSymbol(new StationSymbolFactory().getSymbolByStationView(stationView));
        return stationView;
    }

    public StationView getStationView(){
        Text text = new Text("test Station");
        MyRectangle symbol = new StationSymbolFactory().getBasicSymbol();
        StationView stationView = new StationView(text, symbol);
        return  stationView;
    }
}
