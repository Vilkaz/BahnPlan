package view.factorys;

import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import view.defaultValues.GeneralSettings;

import javafx.scene.paint.Color;
import view.viewClasses.MyRectangle;
import view.viewClasses.StationView;


/**
 * Created by Vilkaz on 20.02.2016.
 */
public class StationSymbolFactory {

    private double width = new GeneralSettings().getDefaultStationSymbolWidth();
    private double arcValue = 5;
    private double lineWidth = 2;
    private int stationID;

    public MyRectangle getSymbolByStationView(StationView stationView) {
        MyRectangle symbol = getBasicSymbol();
        symbol.setStationId(stationView.getId());
        symbol.setStroke(stationView.getColor());
        symbol.setFill(stationView.getColor());
        return symbol;
    }

    public MyRectangle getBasicSymbol() {
        final MyRectangle symbol = new MyRectangle(width, width);
        symbol.setArcHeight(arcValue);
        symbol.setArcWidth(arcValue);
        symbol.setStroke(Color.BLACK);
        symbol.setStrokeWidth(lineWidth);
        symbol.setOnMouseDragged(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                symbol.setX(event.getX());
                symbol.setY(event.getY());
            }
        });
        return symbol;
    }
}
