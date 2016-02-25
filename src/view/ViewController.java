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
import javafx.scene.text.Text;
import view.defaultValues.GeneralSettings;
import view.factorys.*;
import view.viewClasses.*;
import view.viewObjectContainers.ContentPaneContainer;

import java.util.ArrayList;

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
        lineCreator.getChildren().add(getLineCreatorOKBtn(lineCreator));
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
        for (StationView stationView : getAllStations()) {
            renderStationView(stationView);
        }
    }

    private void renderAllLines() {
        getAllStations();
    }

    private ArrayList<StationView> getAllStations() {
        ArrayList<StationView> stations = new ArrayList<>();
        for (TrainLine trainLine : contentPaneContainer.getTrainLines()) {
            for (StationView stationView : trainLine.getStations()) {
                stations.add(stationView);
            }
        }
        return stations;
    }


    private void renderAllStations() {
        for (StationView station : contentPaneContainer.getStations()) {
            renderStationView(station);
        }
    }

    private void renderStationView(StationView stationView) {
        Pane stationPane = new Pane(stationView.getName(), stationView.getSymbol());
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
     * <p/>
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
                //addStationViewToTramLines(event);
                activateStationCreator();
            }
        });
        return button;
    }

    private void addStationViewToTramLines(MouseEvent event) {
        StationView stationView = new StationView(contentPaneContainer.getActualTrainLine(), event);
        contentPaneContainer.getTrainLines().get(0).addStationView(stationView);
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
                saveCoordinates(event.getX(), event.getY());
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
                getAddExistingStationViewAsNeighborBtn(stationCreator),
                getStationCreatorEndStationButton(stationCreator)
        );
        stationCreator.setLayoutX(100);
        stationCreator.setLayoutY(50);
        contentPane.getChildren().add(stationCreator);
    }


    private Button getAddExistingStationViewAsNeighborBtn(final VBox stationCreator) {
        Button button = new Button("Add vorhandene Stattion");
        contentPane.setOnMouseMoved(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                makeLineFollowMouse(event, line);
            }
        });

        button.setOnMouseReleased(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                stationCreator.setVisible(false);
                displayMessage("CLICK AUF STATION SYMBOL");
                for (final MyRectangle symbol : getAllMySymbols()) {

                    symbol.setOnMouseEntered(new EventHandler<MouseEvent>() {
                        @Override
                        public void handle(MouseEvent event) {
                            showMoustOverMyRectangleEffect(symbol);
                        }
                    });
                    symbol.setOnMouseExited(new EventHandler<MouseEvent>() {
                        @Override
                        public void handle(MouseEvent event) {
                            revertMoustOverStationSymbolEffect(symbol);
                        }
                    });

                    symbol.setOnMouseReleased(new EventHandler<MouseEvent>() {
                        @Override
                        public void handle(MouseEvent event) {
                            StationView stationView = getActualStationView();
                            putValuesFromStationCreatorIntoStationView(stationCreator, stationView);
                            addStationViewToLastTrainLine(stationView);

                            StationView prevStation = getPreviousStationView(stationView);
                            setStationViewAsNeighborForLastStation(prevStation);

                            StationView clickedStationView = getStationViewById(symbol.getStationId());
                            setStationViewAsNeighborForLastStation(clickedStationView);
                            event.consume();
                            renderAll();
                        }
                    });

                }
            }

        });
        return button;
    }


    private StationView getPreviousStationView(StationView stationView){
        StationView previousStationView = new StationView();
        return getStationViewById(stationView.getId()-1);
    }

    private void setStationViewAsNeighborForLastStation(StationView stationView) {
        StationView lastStationView = getLastTrainLine().getLastStationView();
        int distance = getDistanceBetweenTwoStationViews(stationView, lastStationView);
        Neighbor neighborForLastStationView = new Neighbor(
                distance,
                stationView.getId()
        );

        Neighbor neighborForClickedStation = new Neighbor(
                distance,
                lastStationView.getId()
        );
        stationView.addNeighbor(neighborForClickedStation);
        lastStationView.addNeighbor(neighborForLastStationView);
    }


    private int getDistanceBetweenTwoStationViews(StationView stationView1, StationView stationView2) {
        double x1 = stationView1.getSymbol().getX();
        double y1 = stationView1.getSymbol().getY();
        double x2 = stationView2.getSymbol().getX();
        double y2 = stationView2.getSymbol().getY();
        Coordinates c1 = new Coordinates(x1, y1);
        Coordinates c2 = new Coordinates(x2, y2);
        int distance = contentPaneContainer.getDistanceBetweenTwoCoordinates(c1, c2);
        return distance;
    }


    public StationView getStationViewById(int id) {
        StationView result = new StationView();

        for (StationView stationView : getAllStations()) {
            if (stationView.getId() == id) {
                result = stationView;
                break;
            }
        }

        return result;
    }


    private void revertMoustOverStationSymbolEffect(MyRectangle symbol) {
        int factor = getMyRectangleIncreaseFactor();
        symbol.setWidth(new GeneralSettings().getDefaultStationSymbolWidth());
        symbol.setHeight(new GeneralSettings().getDefaultStationSymbolWidth());
        symbol.setX(symbol.getX() + factor / 2);
        symbol.setY(symbol.getY() + factor / 2);
    }

    private void showMoustOverMyRectangleEffect(MyRectangle symbol) {
        int factor = getMyRectangleIncreaseFactor();
        symbol.setWidth(symbol.getWidth() + factor);
        symbol.setHeight(symbol.getHeight() + factor);
        symbol.setX(symbol.getX() - factor / 2);
        symbol.setY(symbol.getY() - factor / 2);
    }


    private int getMyRectangleIncreaseFactor() {
        return new GeneralSettings().getIncreaseSizeOnMouseOver();
    }


    private Button getStationCreatorEndStationButton(final VBox stationCreator) {
        Button button = new Button("Linien Ende");
        button.setOnMouseReleased(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                stationCreator.setVisible(false);
                StationView stationView = getActualStationView();
                putValuesFromStationCreatorIntoStationView(stationCreator, stationView);
                setNeighborsForStationView(stationView);
                addStationViewToLastTrainLine(stationView);
                renderAll();
                displayMessage("LINIE ZU ENDE GEBAUT ! ");
            }
        });
        return button;
    }


    /**
     * ab dem zweitem Eintrag wird für die vorher eingetragene Station
     * view, die jetzige Station als nachbar eingetragen.
     * Und die vorherige Station wird als nachbar für die Jetzige eingetragen.
     *
     * @param stationView
     */
    private void setNeighborsForStationView(StationView stationView) {
        if (getLastTrainLine().getStations().size() > 0) {
            addActualStationToLastAdded(stationView);
            addIdFromLastAddedStationAsNeighbor(stationView);
        }
    }


    private void addIdFromLastAddedStationAsNeighbor(StationView stationView) {
        stationView.addNeighbor(new Neighbor(
                contentPaneContainer.getLengthFromLine(),
                getLastTrainLine().getLastStationView().getId()
        ));
    }

    private void addActualStationToLastAdded(StationView stationView) {
        getLastTrainLine().getLastStationView().addNeighbor(
                new Neighbor(contentPaneContainer.getLengthFromLine(),
                        stationView.getId()
                )
        );
    }


    private TrainLine getLastTrainLine() {
        ArrayList<TrainLine> trainLines = contentPaneContainer.getTrainLines();
        return trainLines.get(trainLines.size() - 1);
    }


    /**
     * IF we clicked on that Button,
     * we are putting values in the "actual" variables
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
                StationView stationView = getActualStationView();
                putValuesFromStationCreatorIntoStationView(stationCreator, stationView);
                setNeighborsForStationView(stationView);
                event.consume();
                renderAll();
                drawNeighborConnector(stationView);
            }
        });
        return button;
    }

    private StationView getActualStationView() {
        StationView stationView = new StationView(
                contentPaneContainer.getCoordinates(),
                contentPaneContainer.getActualTrainLine()
        );
        return stationView;
    }


    private void deleteElementFromContentPane(Node node) {
        contentPane.getChildren().remove(node);
    }

    private void drawNeighborConnector(final StationView stationView) {
        final Line line = drawLine(stationView);
        contentPane.setOnMouseMoved(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                makeLineFollowMouse(event, line);
            }
        });

        onClickCreateNewStationCreator(stationView);

    }

    private void makeLineFollowMouse(MouseEvent event, Line line) {
        line.setEndX(event.getX());
        line.setEndY(event.getY());
        contentPaneContainer.getCoordinates().setX(event.getX());
        contentPaneContainer.getCoordinates().setY(event.getY());
    }

    private Line drawLine(StationView stationView) {
        final Line line = new Line();
        contentPaneContainer.setLine(line);
        contentPane.getChildren().add(line);
        Coordinates startingCoordinates = stationView.getSymbolMidleCoordinates();
        line.setStartX(startingCoordinates.getX());
        line.setStartY(startingCoordinates.getY());
        line.setStrokeWidth(8);
        line.setStroke(contentPaneContainer.getActualTrainLine().getColor());
        return line;
    }


    private void onClickCreateNewStationCreator(final StationView stationView) {
        contentPane.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                addStationViewToLastTrainLine(stationView);
                disableContentPaneClickEvent();
                addCreatedStationToTramLine(event);
            }
        });
    }

    private ArrayList<MyRectangle> getAllMySymbols() {
        ArrayList<MyRectangle> symbols = new ArrayList<>();
        for (StationView stationView : getAllStations()) {
            symbols.add(stationView.getSymbol());
        }
        return symbols;
    }

    private void addStationViewToLastTrainLine(StationView stationView) {
        getLastTrainLine().addStationView(stationView);
    }


    private void disableContentPaneClickEvent() {
        contentPane.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
            }
        });

        contentPane.setOnMouseMoved(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
            }
        });
    }

    private void addCreatedStationToTramLine(MouseEvent event) {
        saveCoordinates(event.getX(), event.getY());
        //addCloneOfActualStationViewToActualLine();
        setStationCreatorOnMouseClick(event);
    }


    private void disableContaintPaneMouseMovedEvent() {
        contentPane.setOnMouseMoved(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                event.consume();
            }
        });
    }


    private void putValuesFromStationCreatorIntoStationView(VBox stationCreator, StationView stationView) {
        TextField nameTextField = (TextField) stationCreator.getChildren().get(0);
        String name = nameTextField.getText();
        HBox zoneHBox = (HBox) stationCreator.getChildren().get(1);
        ChoiceBox zoneChoiceBox = (ChoiceBox) zoneHBox.getChildren().get(1);
        stationView.setName(new Text(name));
        stationView.setZone((int) zoneChoiceBox.getValue());
        MyRectangle rect = new StationSymbolFactory().getSymbolByStationView(stationView);
        stationView.setSymbol(rect);
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

    private void saveCoordinates(double x, double y) {
        this.contentPaneContainer.setCoordinates(new Coordinates(x, y));
    }

    private double getX() {
        return this.contentPaneContainer.getCoordinates().getX();
    }

    private double getY() {
        return this.contentPaneContainer.getCoordinates().getY();
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
