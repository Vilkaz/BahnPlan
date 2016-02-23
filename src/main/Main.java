package main;

import classes.route.Route;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import classes.station.Station;
import classes.station.StationController;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class Main extends Application {

    private static ArrayList<Station> getRoute(Station start, Station end, ArrayList<Station> V){

        ArrayList<Station> Route = new ArrayList<Station>();

        // gehe alle Knoten durch, setze die Distanz zu allen Knoten/Haltestellen die nicht
        // der Startpunkt sind auf "unendlich" und die Distanz zum Startpunkt 0
        for(int i = 0; i < V.size(); i++){

            if(V.get(i).getId() == start.getId()){
                V.get(i).setDistance_to_route_startpoint(0);
            }
            else{
                // Interger.Max_Value darf ich hier nicht nehmen weil später eine distanz dazuaddiert wird.
                V.get(i).setDistance_to_route_startpoint(1000000);
            }

            // Keine Station hat einen Vorgänger
            V.get(i).setVorgänger(null);
        }

        // Für jeden Knoten
        for(int j = 0; j < V.size()-1; j++)

            // gehe alle Kanten durch
            for (int u = 0; u < V.size(); u++) {

                Station NodeU = V.get(u);

                for (int v = 0; v < NodeU.getNeighbors().size(); v++) {

                    // Id des Nachbarn holen
                    int id = NodeU.getNeighbors().get(v).getId();
                    Station NodeV = null;

                    // Benachbarte Station holen
                    for(int k = 0; k < V.size(); k++){
                       if(V.get(k).getId() == id){
                           NodeV = V.get(k);
                       }
                    }

                    // Prüfe ob die Distanz zu dem Nachbar V kürzer ist als die bisher bekannte Distanz.
                    if (NodeU.getDistance_to_route_startpoint() + NodeU.getNeighbors().get(v).getDistance() < NodeV.getDistance_to_route_startpoint()) {

                        // Aktualisiere die Entfernung
                        NodeV.setDistance_to_route_startpoint(NodeU.getDistance_to_route_startpoint() + NodeU.getNeighbors().get(v).getDistance());

                        // und setze den neuen Vorgänger, die vorhergehende Station
                        NodeV.setVorgänger(NodeU);
                    }
                }
            }

        // Normalerweise Erfolgt im Bellman-Ford Alg. nun die Überprüfung ob es negative Zyklen/Kreise gibt.
        // Diese habe ich erstmal weggelassen,
        // weil wir logischerweise keine negativen Kantengewichte haben können.

        // Anlegen der Route, fange am Ende an und gehe zum jeweiligen Vorgänger bis die Startstation erreicht ist.
        Station Node = end;

        while(Node.getId() != start.getId()){

            // Füge Vorgänger am Anfang der Arraylist an
            Route.add(0,Node);

            Node = Node.getVorgänger();

        }
        // Füge die Startstation hinzu.
        Route.add(0, start);

        return Route;
    }

    private static int calculate_price(ArrayList<Station> route){

        int price = 2;

        if(route.size() == 2){
            price = 1;
        }
        else{
            int zone = route.get(0).getZone();

            for(int i = 0; i<route.size(); i++){

                if(zone != route.get(i).getZone()){

                    price += 1;
                    zone = route.get(i).getZone();
                }

                if(route.get(i).isEndStation()){

                    price += 1;
                }
            }
        }


        return price;
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("../view/view.fxml"));
        primaryStage.setTitle("Bahn Plan");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }

    public static void main(String[] args) {


        /**
         * Abbildungen von LLinien 1-2-3 mit der verbindung über classes.station 0 und die "3er"
         * hoffentlich sind alle Entfernungen auch beidseitig richtig,
         * sollte dir dabei fehler rauskommen, würde ich gucken ob ich
         * entfernung von 101 zu 102    die gleiche ist wie von 102 zu 101
         * kannst debuger vor dem launch anschmeissen, und dir die variable "stations" im debuger genau anschauen,
         */
        ArrayList<Station> stations = StationController.getMockupStations();

        Station startstation = stations.get(1);
        Station endstation = stations.get(10);

        // Route bestimmen mit Hilfe des Bellman-Ford-Algorithmus
        ArrayList<Station> route = getRoute(startstation, endstation, stations);

        // Berechnen der Kosten
        int price = calculate_price(route);

        launch(args);

    }






}
