package view.viewClasses;

import javafx.scene.shape.Rectangle;

/**
 * Created by vkukanauskas on 25/02/2016.
 */
public class MySymbol extends Rectangle {
    private int StationId;

    public MySymbol(double width, double heigth){
        this.setWidth(width);
        this.setHeight(heigth);
    }

    //region getter and setter

    public int getStationId() {
        return StationId;
    }

    public void setStationId(int stationId) {
        StationId = stationId;
    }


    //endregion getter adn setter
}
