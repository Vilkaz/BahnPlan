package main;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import neighbor.Neighbor;
import route.Route;
import station.Station;
import station.StationController;

import java.util.ArrayList;
import java.util.Arrays;

public class Main extends Application {

    //@todo getRoute(Start, End, Stationen);
    private static ArrayList<Station> getRoute(Station start, Station end, ArrayList<Station> V){

        ArrayList<Station> Route = new ArrayList<Station>();

        ArrayList<Integer> distance = new ArrayList<Integer>();
        ArrayList<Station> vorgänger = new ArrayList<Station>();

        // gehe alle Knoten durch, setze die Distanz zu allen Knoten/Haltestellen die nicht
        // der Startpunkt sind auf unendlich und die Distanz zum Startpunkt 0
        for(int i = 0; i < V.size(); i++){

            if(V.get(i).getId() == start.getId()){
                distance.set(i, 0);
            }
            else{
                distance.set(i,Integer.MAX_VALUE);
            }

            // Kein Knoten hat einen Vorgänger
            vorgänger.set(i,null);
        }

        // Für jeden Knoten
        for(int j = 0; j < V.size()-1; j++){
            // gehe alle Kanten durch
            for(int u = 0; u < V.size(); u++){
                for(int v = 0; v < V.get(u).getNeighbors().size(); v++){
                    // Wenn die Entfernung zu einem Knoten kleiner ist als die bisher bekannte Entfernung
                    if(distance.get(u) + V.get(u).getNeighbors().get(v).getDistance() < distance.get(v)){
                        // Aktualisiere die Entfernung
                        distance.set(v, distance.get(u) + V.get(u).getNeighbors().get(v).getDistance());
                        // und setze den neuen Vorgänger, die vorhergehende Station
                        vorgänger.set(v, V.get(u));
                    }
                }
            }
        }

        //normalerweise Erfolg nun die Überprüfung ob es negative zyklen gibt. Diese habe ich erstmal weggelassen,
        // weil wir logischerweise keine negativen Kantengewichte haben können.
        // Dies sollte jedoch noch bei der Eingabe abgefangen werden


        return Route;
    }

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

    Station startstation = stations.get(1);
    Station endstation = stations.get(10);
    int kosten = 2;

    // kosten sollten zur bestimmung der Route nicht notwendig sein. ggf anpassen
    Route net = new Route(stations, kosten);

    ArrayList<Station> route = getRoute(startstation, endstation, stations);

    launch(args);

    }






}
