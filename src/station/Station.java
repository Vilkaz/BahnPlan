package station;

import tramline.Tramline;

import java.util.ArrayList;

/**
 * Created by Vilkaz on 17.02.2016.
 */
public class Station {
    private int id;
    private int zone;
    private String name;
    private boolean endstation;
    private ArrayList<Integer> lines;
    private ArrayList<Integer> neighbors;

    public Station(int id,
                   ArrayList<Integer> neighborIds,
                   ArrayList<Integer> tramlines) {
        this.id = id;
        this.neighbors = neighborIds;
        this.lines = tramlines;
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

    public boolean isEndstation() {
        return endstation;
    }

    public ArrayList<Integer> getLines() {
        return lines;
    }

    public ArrayList<Integer> getNeighbors() {
        return neighbors;
    }


    //endregion getter and setter

}
