package view.defaultValues;

/**
 * Created by Vilkaz on 20.02.2016.
 */
public class GeneralSettings {
    private double defaultNodeWidth = 20;

    private int minLineNr = 1;
    private int maxLineNr = 20;
    private int minZone = 1;
    private int maxZone = 2;

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

    public double getDefaultNodeWidth() {
        return defaultNodeWidth;
    }

    public int getMaxLineNr() {
        return maxLineNr;
    }

    //endregion getter and setter
}
