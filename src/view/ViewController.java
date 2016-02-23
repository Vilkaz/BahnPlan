package view;

import classes.neighbor.Neighbor;
import classes.trainLine.TrainLine;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import view.factorys.*;
import view.viewClasses.Coordinates;
import view.viewClasses.StationView;
import view.viewObjectContainers.ContentPaneContainer;

/**
 * Created by Vilkaz on 19.02.2016.
 */
public class ViewController {

    private ContentPaneContainer contentPaneContainer = new ContentPaneContainer();

    private Line line;

    @FXML
    private RadioButton adminBtn, clientBtn;

    @FXML
    private TextField infoTF;

    @FXML
    private Pane contentPane;


    @FXML
    private Button addNewLineBtn;

    @FXML
    public void startMouseListener() {
        startAdminMouseListener();
    }


    @FXML
    public void startAdminMouseListener() {
        enableAddNewLineBtn();
    }

    private void enableAddNewLineBtn() {
        addNewLineBtn.setVisible(true);
    }

    private void disableAddNewLineBtn() {
        addNewLineBtn.setVisible(false);
    }

    @FXML
    public void addNewLine() {
        VBox lineCreator = new LineCreatorFactory().getLineCreator(contentPaneContainer);
        lineCreator.setLayoutX(200);
        lineCreator.setLayoutY(50);
        lineCreator.getChildren().addAll(getLineCreatorOKBtn(lineCreator));
        contentPane.getChildren().add(lineCreator);

    }

    private void addStationViewToContentPane(MouseEvent event) {
        StationView stationView = new StationViewFactory().getStationView();
        stationView.setX(event.getX());
        stationView.setY(event.getY());
        contentPaneContainer.addStationView(stationView);
    }


    @FXML
    public void startClientMouseListener() {
        disableAddNewLineBtn();
        contentPane.setOnMouseClicked(new EventHandler<javafx.scene.input.MouseEvent>() {
            @Override
            public void handle(javafx.scene.input.MouseEvent event) {
                displayMessage("du bist Kunde und  hast maus geklickt bei x = " + event.getX() + " und y = " + event.getY());
            }
        });
    }

    private void displayMessage(String message) {
        infoTF.setText(message);
    }


    public void renderAll() {
        renderAllLines();
    }

    private void renderAllLines() {
        for (TrainLine trainLine : contentPaneContainer.getTrainLines()) {
            for (StationView stationView : trainLine.getStations()) {
                renderStationView(stationView);
            }
        }
    }

    private void renderAllStations() {
        for (StationView station : contentPaneContainer.getStations()) {
            renderStationView(station);
        }
    }

    private void renderStationView(StationView stationView) {
        Pane stationPane = new Pane(stationView.getName(), stationView.getRectangle());
        stationPane.setLayoutX(stationView.getX());
        stationPane.setLayoutY(stationView.getY());
        contentPane.getChildren().add(stationPane);
    }

    @FXML
    public void getContentPaneContextMenu(MouseEvent event) {
        MenuItem menuItem1 = new MenuItem("Neue Linie hier anfangen");
        ContextMenu contextMenu = new ContextMenu(menuItem1);
        contextMenu.autoHideProperty().setValue(true);
        contextMenu.show(contentPane, event.getScreenX(), event.getScreenY());
        displayMessage("Context Menu here !");
    }


    /**
     * LineCreator gets here an button, which then activates the Station constructor,
     * and saves the currend line in the container, and makes the line creator dissapear.
     *
     * Also the StationID will be generated here. in all next Stept, the Station ID
     * will be simply incremented.
     *
     * @param lineCreator
     * @return
     */
    private Button getLineCreatorOKBtn(final VBox lineCreator) {
        Button button = new Button("weiter");
        button.setOnMouseReleased(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                contentPane.getChildren().remove(lineCreator);
                setActualLine(lineCreator);
                displayMessage("Bitte mit der Maus klicken, wo die Station hinzugefügt sein soll");
                // diese StationView hat nur FArbe bisher festgelegt, alles andere ist leer.
                StationView stationView = new StationView(contentPaneContainer.getActualTrainLine());
                contentPaneContainer.setActualStation(stationView);
                activateStationCreator();
            }
        });
        return button;
    }


    /**
     * the Line that is actualy in editing, will be saved underextr avariable,
     * to get an easy access to it
     *
     * @param lineCreator
     */
    private void setActualLine(VBox lineCreator) {
        TrainLine trainLine = getLineByLineCreator(lineCreator);
        contentPaneContainer.addLine(trainLine);
        contentPaneContainer.setActualTrainLine(trainLine);
    }

    private TrainLine getLineByLineCreator(VBox lineCreator) {
        HBox lineNumberChooserBlock = (HBox) lineCreator.getChildren().get(0);
        ChoiceBox choiceBox = (ChoiceBox) lineNumberChooserBlock.getChildren().get(1);
        ColorPicker colorPicker = (ColorPicker) lineCreator.getChildren().get(1);
        TrainLine trainLine = new TrainLine((int) choiceBox.getValue(), colorPicker.getValue());
        return trainLine;
    }


    /**
     * The StationCreator window will popped on mouseclick,
     * also the click coordinates will be added to actualStation variable
     * in the container
     */
    private void activateStationCreator() {
        contentPane.setOnMouseClicked(new EventHandler<javafx.scene.input.MouseEvent>() {
            @Override
            public void handle(javafx.scene.input.MouseEvent event) {
                contentPaneContainer.getActualStation().setX(event.getX());
                contentPaneContainer.getActualStation().setY(event.getY());
                setStationCreatorOnMouseClick(event);
            }
        });

    }

    /**
     * StationCreator will be produces here and added to the contentPane
     * the Buttons will be added from separatly functions,
     *
     * @param event
     */
    private void setStationCreatorOnMouseClick(MouseEvent event) {
        VBox stationCreator = new StationCreatorFactory().getStationCreator();
        stationCreator.getChildren().addAll(
                getStationCreatorNeighborButton(stationCreator),
                getStationCreatorEndStationButton(stationCreator)
        );
        stationCreator.setLayoutX(event.getSceneX());
        stationCreator.setLayoutY(event.getSceneY());
        contentPane.getChildren().add(stationCreator);
    }

    private Button getStationCreatorEndStationButton(final VBox stationCreator) {
        Button button = new Button("Linien Ende");
        button.setOnMouseReleased(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                stationCreator.setVisible(false);
                displayMessage("LINIE ZU ENDE GEBAUT ! ");
            }
        });
        return button;
    }

    /**
     * IF we clicked on that Button,
     * are putting values in the "actual" variables
     *
     * @param stationCreator
     * @return
     */
    private Button getStationCreatorNeighborButton(final VBox stationCreator) {
        Button button = new Button("Nachbar hinzufügen");
        button.setOnMouseReleased(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                deleteElementFromContentPane(stationCreator);
                putValuesFromStationCreatorIntoStationView(stationCreator);
                event.consume();
                prepareForNeighborStation();
            }
        });
        return button;
    }


    /**
     * the "actual" variables, are put in the PaneContainer
     */
    private void prepareForNeighborStation() {
        renderAll();
        setNeighborToStationView();
    }

    private void addActualStationToActualLine() {
        contentPaneContainer.addActualStationToActualLine();
    }

    private void deleteElementFromContentPane(Node node) {
        contentPane.getChildren().remove(node);
    }

    private void setNeighborToStationView() {
        final Line line = new Line();
        contentPaneContainer.setLine(line);
        contentPane.getChildren().add(line);
        Coordinates startingCoordinates = contentPaneContainer.getActualStation().getRectangleMidleCoordinates();
        line.setStartX(startingCoordinates.getX());
        line.setStartY(startingCoordinates.getY());
        line.setStrokeWidth(10);
        line.setStroke(contentPaneContainer.getActualTrainLine().getColor());
        final Rectangle test = contentPaneContainer.getActualStation().getRectangle();
        contentPane.setOnMouseMoved(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                line.setEndX(event.getX());
                line.setEndY(event.getY());
            }
        });

        contentPane.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                disableContaintPaneMouseMovedEvent();
                cloneActualStationViewToPreviouse();
                setEventCoordinatesToActualStationView(event);
                addCloneOfActualStationViewToActualLine();
                setStationCreatorOnMouseClick(event);
            }
        });
    }

    private void addCloneOfActualStationViewToActualLine(){
        StationView stationView = new StationView(contentPaneContainer.getActualStation());
        contentPaneContainer.addActualStationToActualLine();
    }

    private void disableContaintPaneMouseMovedEvent() {
        contentPane.setOnMouseMoved(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                event.consume();
            }
            });
    }

    private void setEventCoordinatesToActualStationView(MouseEvent event) {
        this.contentPaneContainer.getActualStation().setX(event.getX());
        this.contentPaneContainer.getActualStation().setY(event.getY());
    }

    private void cloneActualStationViewToPreviouse() {
        this.contentPaneContainer.cloneActualStationViewToPreviouse();
        setFirstNeighborForActualStation();
    }



    private void    setFirstNeighborForActualStation(){
        contentPaneContainer.getActualStation().setFirstNeighbor(
                contentPaneContainer.getFirstNeighborForActualStation()
        );
    }



    private void putValuesFromStationCreatorIntoStationView(VBox stationCreator) {
        StationView stationView = contentPaneContainer.getActualStation();
        TextField nameTextField = (TextField) stationCreator.getChildren().get(0);
        String name = nameTextField.getText();
        HBox zoneHBox = (HBox) stationCreator.getChildren().get(1);
        ChoiceBox zoneChoiceBox = (ChoiceBox) zoneHBox.getChildren().get(1);
        stationView.setName(new Text(name));
        stationView.setZone((int) zoneChoiceBox.getValue());
        stationView.setRectangle(new RectangleFactory().getRectangleByColor(stationView.getColor()));
    }

    private void setStationOnMouseClick(VBox stationCreator) {
        contentPane.setOnMouseClicked(new EventHandler<javafx.scene.input.MouseEvent>() {
            @Override
            public void handle(javafx.scene.input.MouseEvent event) {
                addStationViewToContentPane(event);
                renderAll();
            }
        });
    }


    //region getter and setter


    public Line getLine() {
        return line;
    }

    public void setLine(Line line) {
        this.line = line;
    }

    public Pane getContentPane() {
        return contentPane;
    }


    //endregion getter and setter

}
