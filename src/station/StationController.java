package station;

import neighbor.Neighbor;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by Vilkaz on 19.02.2016.
 */
public class StationController {

    /** hier sind mockup stations, hab versucht
     * die linie 1-2-3 von Rinnes tabelle umzuwandeln, mit unterschiedlichen längen,
     * die IDs sind wie folgt gebaut = [LinienNummer][zweistellige Stations nummer(01 02 03 usw]
     * Somit ergeben sich IDs wie 101, 201 usw. Sinn = sonst wäre (linie 1, station 11) gleich wie (Linie 11 station 1)
     * um jetzt sowas zu erzeugen, muss eine linie mehr als 100 Stationen haben (Linie1, Station 101) wäre gleich (Linie 11, Station 01)
     * Diese Sicherheit reicht für unser Feldversuch dicke aus.
     */
    public static ArrayList<Station> getMockupStations(){
        ArrayList<Station> stations = new ArrayList<Station>();

        /**
         * as we work with integer ans id, we can't make it "000"
         */
        stations.add(new Station(0,1,false, new ArrayList<Neighbor>(Arrays.asList(
                new Neighbor(101, 1),
                new Neighbor(201, 3),
                new Neighbor(301, 2)
        ))));

        stations.add(new Station(101, 2, false,
                new ArrayList<Neighbor>(Arrays.asList(
                        new Neighbor(1, 0),
                        new Neighbor(2, 102)
                ))));

        stations.add(new Station(102, 2, false,
                new ArrayList<Neighbor>(Arrays.asList(
                        new Neighbor(2, 101),
                        new Neighbor(1,103)))));

        stations.add(new Station(103, 1, false,
                new ArrayList<Neighbor>(Arrays.asList(
                        new Neighbor(1, 102),
                        new Neighbor(3,104),
                        new Neighbor(4, 203)))));

        stations.add(new Station(104, 2, false,
                new ArrayList<Neighbor>(Arrays.asList(
                        new Neighbor(3, 103),
                        new Neighbor(1,105)))));

        stations.add(new Station(104, 2, false,
                new ArrayList<Neighbor>(Arrays.asList(
                        new Neighbor(1, 104),
                        new Neighbor(1,106)))));

        stations.add(new Station(104, 2, true,
                new ArrayList<Neighbor>(Arrays.asList(
                        new Neighbor(1, 105)))));



        stations.add(new Station(201, 1, false,
                new ArrayList<Neighbor>(Arrays.asList(
                        new Neighbor(3, 0),
                        new Neighbor(2, 202)
                ))));

        stations.add(new Station(202, 1, false,
                new ArrayList<Neighbor>(Arrays.asList(
                        new Neighbor(2, 201),
                        new Neighbor(1, 203)
                ))));

        stations.add(new Station(203, 1, false,
                new ArrayList<Neighbor>(Arrays.asList(
                        new Neighbor(1, 202),
                        new Neighbor(2, 204),
                        new Neighbor(4, 103),
                        new Neighbor(5, 303)
                ))));


        stations.add(new Station(204, 2, false,
                new ArrayList<Neighbor>(Arrays.asList(
                        new Neighbor(2, 203),
                        new Neighbor(1, 205)
                ))));

        stations.add(new Station(205, 2, false,
                new ArrayList<Neighbor>(Arrays.asList(
                        new Neighbor(1, 204),
                        new Neighbor(1, 206)
                ))));

        stations.add(new Station(206, 2, true,
                new ArrayList<Neighbor>(Arrays.asList(
                        new Neighbor(1, 205)))));

        stations.add(new Station(301, 1, false,
                new ArrayList<Neighbor>(Arrays.asList(
                        new Neighbor(2, 0),
                        new Neighbor(2, 302)
                ))));

        stations.add(new Station(302, 1, false,
                new ArrayList<Neighbor>(Arrays.asList(
                        new Neighbor(2, 301),
                        new Neighbor(2, 303)
                ))));

        stations.add(new Station(303, 1, false,
                new ArrayList<Neighbor>(Arrays.asList(
                        new Neighbor(2, 302),
                        new Neighbor(1, 304),
                        new Neighbor(5, 203)
                ))));

        stations.add(new Station(304, 2, false,
                new ArrayList<Neighbor>(Arrays.asList(
                        new Neighbor(1, 303),
                        new Neighbor(4, 305)
                ))));

        stations.add(new Station(305, 2, false,
                new ArrayList<Neighbor>(Arrays.asList(
                        new Neighbor(4, 304),
                        new Neighbor(1, 306)
                ))));

        stations.add(new Station(306, 2, true,
                new ArrayList<Neighbor>(Arrays.asList(
                        new Neighbor(1, 305)
                ))));
        return stations;
    }

}
