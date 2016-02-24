package view.defaultValues;

/**
 * Created by Vilkaz on 20.02.2016.
 */
public class GeneralSettings {
    // stationView
    private int defaultStationSymbolWidth = 40;
    private int minZone = 1;
    private int maxZone = 2;

    //tramlines
    private int minLineNr = 1;
    private int maxLineNr = 20;




    //region getter and setter


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
