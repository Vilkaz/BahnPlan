package view;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import view.factorys.StationGenerator;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 * Created by Vilkaz on 19.02.2016.
 */
public class ViewController {

    @FXML
    private RadioButton adminBtn, clientBtn;

    @FXML
    private TextField infoTF;

    @FXML
    private Pane contentPane;

    @FXML
    public void startMouseListener(){
        contentPaneMouseClicked();
    }

    @FXML
    public void contentPaneMouseClicked(){
        contentPane.setOnMouseClicked(new EventHandler<javafx.scene.input.MouseEvent>() {
            @Override
            public void handle(javafx.scene.input.MouseEvent event) {
               displayMessage("du bist Admin hast maus geklickt bei x = " + event.getX()+ " und y = " + event.getY());
               Pane pane = StationGenerator.getStationSet();
               pane.setLayoutX(event.getX());
               pane.setLayoutY(event.getY());
               contentPane.getChildren().add(pane);
            }
        });

    }


    @FXML public void startClientMouseListener(){
        contentPane.setOnMouseClicked(new EventHandler<javafx.scene.input.MouseEvent>() {
            @Override
            public void handle(javafx.scene.input.MouseEvent event) {
                displayMessage("du bist Kunde und  hast maus geklickt bei x = " + event.getX()+ " und y = " + event.getY());
            }
        });
    }

    private void displayMessage(String message){
        infoTF.setText(message);
    }

}
