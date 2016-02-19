package route;

import station.Station;

import java.util.ArrayList;

/**
 *
 *
 *
 * Created by Vilkaz on 19.02.2016.
 */


public class Route {
    private ArrayList<Station> stations;
    private double price;

    public Route(ArrayList<Station> stations, double price) {
        this.stations = stations;
        this.price = price;
    }

    //region getter and setter

    public ArrayList<Station> getStations() {
        return stations;
    }

    public double getPrice() {
        return price;
    }


    //endregion getter and setter
}
