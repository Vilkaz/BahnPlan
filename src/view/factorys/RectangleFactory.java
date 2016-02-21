package view.factorys;

import javafx.scene.shape.Rectangle;
import view.defaultValues.GeneralSettings;

import javafx.scene.paint.Color;


/**
 * Created by Vilkaz on 20.02.2016.
 */
public class RectangleFactory {

    double width = new GeneralSettings().getDefaultNodeWidth();
    double arcValue = 5;
    double lineWidth = 2;


    public  Rectangle getBasicRectangle(){
        Rectangle rectangle =   new Rectangle(width,width);
        rectangle.setArcHeight(arcValue);
        rectangle.setArcWidth(arcValue);
        rectangle.setFill(Color.TRANSPARENT);
        rectangle.setStroke(Color.BLACK);
        rectangle.setStrokeWidth(lineWidth);
        return rectangle;
    }
}
