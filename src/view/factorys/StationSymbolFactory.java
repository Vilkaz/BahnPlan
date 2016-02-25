package view.factorys;

import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Rectangle;
import view.defaultValues.GeneralSettings;

import javafx.scene.paint.Color;
import view.viewClasses.StationView;


/**
 * Created by Vilkaz on 20.02.2016.
 */
public class StationSymbolFactory {

    private double width = new GeneralSettings().getDefaultStationSymbolWidth();
    private double arcValue = 5;
    private double lineWidth = 2;
    private int stationID;

    public Rectangle getRectangleByStationView(StationView stationView) {
        Rectangle rectangle = getBasicRectangle();
        rectangle.setId(stationView.getId());
        rectangle.setStroke(stationView.getColor());
        rectangle.setFill(stationView.getColor());
        return rectangle;
    }

    public Rectangle getBasicRectangle() {
        final Rectangle rectangle = new Rectangle(width, width);
        rectangle.setArcHeight(arcValue);
        rectangle.setArcWidth(arcValue);
        rectangle.setStroke(Color.BLACK);
        rectangle.setStrokeWidth(lineWidth);
        rectangle.setOnMouseDragged(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                rectangle.setX(event.getX());
                rectangle.setY(event.getY());
            }
        });
        return rectangle;
    }
}
