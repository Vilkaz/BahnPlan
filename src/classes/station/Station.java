package classes.station;

import classes.neighbor.Neighbor;

import java.util.ArrayList;

/**
 * Created by Vilkaz on 17.02.2016.
 */
public class Station {
    private int id;
    private int zone;
    private String name;
    private boolean endStation;
    private ArrayList<Integer> lines;
    private ArrayList<Neighbor> neighbors;
    private int x;
    private int y;
    private int distance_to_route_startpoint;
    private Station vorgänger;


    /**
     *
     * Constructor for calculating classes.route and price
     *
      * @param id
     * @param zone
     * @param endStation
     * @param neighbors
     */
    public Station(int id, int zone, boolean endStation, ArrayList<Neighbor> neighbors) {
        this.id = id;
        this.zone = zone;
        this.endStation = endStation;
        this.neighbors = neighbors;
        this.distance_to_route_startpoint = 0;
        this.vorgänger = null;
    }


    /**
     * Constructor for visualisation
     *
     * @param id
     * @param zone
     * @param name
     * @param endstation
     * @param lines
     * @param neighbors
     * @param x
     * @param y
     */
    public Station(int id, int zone, String name, boolean endstation, ArrayList<Integer> lines, ArrayList<Neighbor> neighbors, int x, int y) {
        this.id = id;
        this.zone = zone;
        this.name = name;
        this.endStation = endstation;
        this.lines = lines;
        this.neighbors = neighbors;
        this.x = x;
        this.y = y;
    }

    public boolean hasNeighborWithId(Station potentialNeighbour) {
        return this.neighbors.contains(potentialNeighbour);
    }





    //region getter and setter

    public int getId() {
        return id;
    }

    public int getZone() {
        return zone;
    }

    public String getName() {
        return name;
    }

    public boolean isEndStation() {
        return endStation;
    }

    public ArrayList<Integer> getLines() {
        return lines;
    }

    public ArrayList<Neighbor> getNeighbors() {
        return neighbors;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getDistance_to_route_startpoint(){
        return this.distance_to_route_startpoint;
    }

    public void setDistance_to_route_startpoint(int d){
        this.distance_to_route_startpoint = d;
    }

    public Station getVorgänger(){
        return this.vorgänger;
    }

    public void setVorgänger(Station s){
        this.vorgänger = s;
    }
    //endregion getter and setter

}
