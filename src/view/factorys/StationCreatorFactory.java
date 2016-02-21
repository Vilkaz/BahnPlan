package view.factorys;

import javafx.geometry.Insets;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import view.defaultValues.GeneralSettings;

/**
 * Created by Vilkaz on 21.02.2016.
 */
public class StationCreatorFactory {

    int minZone = new GeneralSettings().getMinZone();
    int maxZone = new GeneralSettings().getMaxZone();

    public VBox getStationCreator(){
        TextField name = new TextField();
        name.setPromptText("Stationname");
        Text zoneDescription = new Text("Zone");
        ChoiceBox zoneChoser = getZoneChoser();
        HBox zoneChoseHBox = new HBox(zoneDescription, zoneChoser);
        VBox stationCreator = new VBox(name, zoneChoseHBox);
        stationCreator.paddingProperty().setValue(new Insets(10,10,10,10));
        stationCreator.getStyleClass().add("station-creator");
        return stationCreator;
    }

    private ChoiceBox getZoneChoser(){
        ChoiceBox zoneChoser = new ChoiceBox();
        for (int i = minZone; i<=maxZone; i++){
            zoneChoser.getItems().add(i);
        }
        zoneChoser.setValue(zoneChoser.getItems().get(0));
        return zoneChoser;
    }
}
