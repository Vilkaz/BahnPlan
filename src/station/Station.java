package station;

import tramline.Tramline;

import java.util.ArrayList;

/**
 * Created by Vilkaz on 17.02.2016.
 */
public class Station {
    private int id;
    private ArrayList<Station> neighbors;
    private ArrayList<Tramline> tramlines;

    public Station(int id,
                   ArrayList<Station> neighborIds,
                   ArrayList<Tramline> tramlines) {
        this.id = id;
        this.neighbors = neighborIds;
        this.tramlines = tramlines;
    }

     public boolean hasNeighborWithId(Station potentialNeighbour) {
        return this.neighbors.contains(potentialNeighbour);
    }

    //region getter and setter
    public int getId() {
        return id;
    }

    public ArrayList<Station> getNeighbors() {
        return neighbors;
    }

    public ArrayList<Tramline> getTramlines() {
        return tramlines;
    }

    //endregion getter and setter

}
