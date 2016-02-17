package station;

import tramline.Tramline;

import java.util.ArrayList;

/**
 * Created by Vilkaz on 17.02.2016.
 */
public class Station {
    private int id;
    private ArrayList<Station> neighbors;
    private ArrayList<Tramline> belongsToLines;

    public Station(int id,
                   ArrayList<Station> neighborIds,
                   ArrayList<Tramline> tramlines) {
        this.id = id;
        this.neighbors = neighborIds;
        this.belongsToLines = tramlines;
    }


    public boolean hasNeighborWithId(Integer potentialNeighbourId) {
        return this.neighbors.contains(potentialNeighbourId);
    }

    //region getter and setter
    public int getId() {
        return id;
    }

    public ArrayList<Station> getNeighbors() {
        return neighbors;
    }

    public ArrayList<Tramline> getBelongsToLines() {
        return belongsToLines;
    }

    //endregion getter and setter

}
