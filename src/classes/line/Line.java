package classes.line;

import javafx.scene.paint.Color;

import java.util.ArrayList;

/**
 * Created by Vilkaz on 17.02.2016.
 */
public class Line {
    private Color color;
    private int lineNumber;


    public Line() {
    }

    public Line(int lineNumber, Color color) {
        this.lineNumber = lineNumber;
        this.color = color;
    }


    //region getter and setter

    public Line(int lineNumber){
        this.lineNumber = lineNumber;
    }

    public int getLineNumber() {
        return lineNumber;
    }

    //endregion getter and setter

}
