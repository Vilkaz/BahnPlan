package view.factorys;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import view.ViewController;
import view.viewObjectContainers.ContentPaneContainer;

/**
 * Created by Vilkaz on 20.02.2016.
 */
public class LineCreatorFactory {

    public VBox getLineCreator(ContentPaneContainer contentPaneContainer){
        ColorPicker color = new ColorPicker(Color.BLACK);
        ChoiceBox choiceBox = new LineNrChoiceBoxFactory().getChoiceBox(contentPaneContainer);
        Text lineNumberPickerDescription = new Text("Liniennummer");
        HBox lineNumberPicker = new HBox(lineNumberPickerDescription, choiceBox);
        VBox lineCreator = new VBox(lineNumberPicker, color);
        lineCreator.setPadding(new Insets(10,10,10,10));
        lineCreator.getStyleClass().add("line-creator");
        return  lineCreator;
    }

}
