package view.viewClasses;

import javafx.scene.Node;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

/**
 * Created by Vilkaz on 20.02.2016.
 */
public class StationView {
    private Text name;
    double x;
    double y;
    int zone;
    Color color;
    boolean endstation;
    private Rectangle rectangle;


    public StationView(Color color) {
        this.color = color;
    }

    public StationView(Text name, double x, double y, int zone, boolean endstation, Rectangle rectangle) {
        this.name = name;
        this.x = x;
        this.y = y;
        this.zone = zone;
        this.endstation = endstation;
        this.rectangle = rectangle;
    }

    public StationView(Text name, Rectangle rectangle) {
        this.name = name;
        this.rectangle = rectangle;
    }

    //region getter and setter


    public Color getColor() {
        return color;
    }

    public void setName(Text name) {
        this.name = name;
    }

    public void setRectangle(Rectangle rectangle) {
        this.rectangle = rectangle;
    }

    public void setX(double x) {
        this.x = x;
    }

    public void setY(double y) {
        this.y = y;
    }

    public void setZone(int zone) {
        this.zone = zone;
    }

    public void setEndstation(boolean endstation) {
        this.endstation = endstation;
    }

    public Text getName() {
        return name;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public int getZone() {
        return zone;
    }

    public boolean isEndstation() {
        return endstation;
    }

    public Rectangle getRectangle() {
        return rectangle;
    }


    //endregion getter and setter
}
