package main;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import neighbor.Neighbor;
import station.Station;
import station.StationController;

import java.util.ArrayList;
import java.util.Arrays;

public class Main extends Application {

    //@todo getRoute(Start, End, Stationen);

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("view.fxml"));
        primaryStage.setTitle("Bahn Plan");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }

    public static void main(String[] args) {


        /**
         * Abbildungen von LLinien 1-2-3 mit der verbindung über station 0 und die "3er"
         * hoffentlich sind alle Entfernungen auch beidseitig richtig,
         * sollte dir dabei fehler rauskommen, würde ich gucken ob ich
         * entfernung von 101 zu 102    die gleiche ist wie von 102 zu 101
         * kannst debuger vor dem launch anschmeissen, und dir die variable "stations" im debuger genau anschauen,
         */
    ArrayList<Station> stations = StationController.getMockupStations();

    launch(args);

    }






}
