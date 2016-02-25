package view.defaultValues;

/**
 * Created by Vilkaz on 20.02.2016.
 */
public class GeneralSettings {
    // stationView
    //symbol
    private int defaultStationSymbolWidth = 40;
    private int increaseSizeOnMouseOver = 20;

    //connector
    private int connectorWidth=10;

    private int minZone = 1;
    private int maxZone = 2;

    //tramlines
    private int minLineNr = 1;
    private int maxLineNr = 20;




    //region getter and setter


    public int getConnectorWidth() {
        return connectorWidth;
    }

    public int getIncreaseSizeOnMouseOver() {
        return increaseSizeOnMouseOver;
    }

    public int getMinZone() {
        return minZone;
    }

    public int getMaxZone() {
        return maxZone;
    }

    public int getMinLineNr() {
        return minLineNr;
    }

    public int getDefaultStationSymbolWidth() {
        return defaultStationSymbolWidth;
    }

    public int getMaxLineNr() {
        return maxLineNr;
    }

    //endregion getter and setter
}
