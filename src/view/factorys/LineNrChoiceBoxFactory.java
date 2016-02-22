package view.factorys;

import javafx.scene.control.ChoiceBox;
import view.defaultValues.GeneralSettings;
import view.viewObjectContainers.ContentPaneContainer;

import java.util.ArrayList;

/**
 * Created by Vilkaz on 21.02.2016.
 */
public class LineNrChoiceBoxFactory {

    private int maxLineNr = new GeneralSettings().getMaxLineNr();
    private int minLineNr = new GeneralSettings().getMinLineNr();

    public ChoiceBox getChoiceBox(ContentPaneContainer contentPaneContainer){
        ArrayList posibleLineNumbers = getPosibleLineNumbers(contentPaneContainer);
        ChoiceBox choiceBox = new ChoiceBox();
        choiceBox.getItems().addAll(posibleLineNumbers);
        choiceBox.setValue(choiceBox.getItems().get(0));
        return choiceBox;
    }

    private ArrayList<Integer> getPosibleLineNumbers(ContentPaneContainer contentPaneContainer){
        ArrayList<Integer> usedLineNumbers = contentPaneContainer.getUsedLineNumbers();
        ArrayList<Integer> lineNumberList = new ArrayList<Integer>();
        for (int i = this.minLineNr; i<=this.maxLineNr;i++){
            if (!usedLineNumbers.contains(i)){
                lineNumberList.add(i);
            }
        }
        return lineNumberList;
    }


}
