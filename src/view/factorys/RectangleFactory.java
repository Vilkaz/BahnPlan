package view.factorys;

import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Rectangle;
import view.defaultValues.GeneralSettings;

import javafx.scene.paint.Color;


/**
 * Created by Vilkaz on 20.02.2016.
 */
public class RectangleFactory {

   private double width = new GeneralSettings().getDefaultStationSymbolWidth();
   private double arcValue = 5;
   private double lineWidth = 2;

    public Rectangle getRectangleByColor(Color color){
        Rectangle rectangle = getBasicRectangle();
        rectangle.setStroke(color);
        rectangle.setFill(color);
        return rectangle;
    }

    public  Rectangle getBasicRectangle(){
        final Rectangle rectangle =   new Rectangle(width,width);
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
