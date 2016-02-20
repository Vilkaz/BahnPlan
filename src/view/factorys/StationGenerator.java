package view.factorys;

import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;

/**
 * Created by Vilkaz on 20.02.2016.
 */
public class StationGenerator {

    public static Pane getStationSet(){
        TextField text = new TextField("test Station");
        Pane pane = new Pane(text);
        return  pane;
    }
}
