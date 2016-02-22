package view;

import classes.line.Line;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import view.factorys.LineCreatorFactory;
import view.factorys.RectangleFactory;
import view.factorys.StationCreatorFactory;
import view.factorys.StationGenerator;
import view.viewClasses.StationView;
import view.viewObjectContainers.ContentPaneContainer;

/**
 * Created by Vilkaz on 19.02.2016.
 */
public class ViewController {

    private ContentPaneContainer contentPaneContainer = new ContentPaneContainer();

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
    public void addNewLine(){
        VBox lineCreator =  new LineCreatorFactory().getLineCreator(contentPaneContainer);
        lineCreator.setLayoutX(200);
        lineCreator.setLayoutY(50);
        lineCreator.getChildren().addAll(getLineCreatorOKBtn(lineCreator));
        contentPane.getChildren().add(lineCreator);

    }

    private void addStationViewToContentPane(MouseEvent event) {
        StationView stationView = new StationGenerator().getStationView();
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
        for (Line line : contentPaneContainer.getLines()){
           for (StationView stationView : line.getStations()){
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


    private Button getLineCreatorOKBtn(final VBox lineCreator){
        Button button = new Button("weiter");
        button.setOnMouseReleased(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                lineCreator.setVisible(false);
                setActualLine(lineCreator);
                displayMessage("Bitte mit der Maus klicken, wo die Station hinzugefügt sein soll");
                // diese StationView hat nur FArbe bisher festgelegt, alles andere ist leer.
                StationView stationView = new StationView(getLineByLineCreator(lineCreator).getColor());
                contentPaneContainer.setActualStation(stationView);
                activateStationCreator();
            }
        });
        return button;
    }

    private void setActualLine(VBox lineCreator){
        Line line = getLineByLineCreator(lineCreator);
        contentPaneContainer.setActualLine(line);
    }

    private Line getLineByLineCreator(VBox lineCreator){
        HBox lineNumberChooserBlock = (HBox) lineCreator.getChildren().get(0);
        ChoiceBox choiceBox = (ChoiceBox) lineNumberChooserBlock.getChildren().get(1);
        ColorPicker colorPicker = (ColorPicker) lineCreator.getChildren().get(1);
        Line line = new Line((int)choiceBox.getValue(),colorPicker.getValue());
        return  line;
    }


    private void activateStationCreator(){
        contentPane.setOnMouseClicked(new EventHandler<javafx.scene.input.MouseEvent>() {
            @Override
            public void handle(javafx.scene.input.MouseEvent event) {
                contentPaneContainer.getActualStation().setX(event.getSceneX());
                contentPaneContainer.getActualStation().setY(event.getSceneY());
                setStationCreatorOnMouseClick(event);
                renderAll();
            }
        });

    }

    private void setStationCreatorOnMouseClick(MouseEvent event){
        VBox stationCreator = new StationCreatorFactory().getStationCreator();
        stationCreator.getChildren().addAll(
                getStationCreatorNeighborButton(stationCreator),
                getStationCreatorEndStationButton(stationCreator)
        );
        stationCreator.setLayoutX(event.getSceneX());
        stationCreator.setLayoutY(event.getSceneY());
        contentPane.getChildren().add(stationCreator);
        //addStationViewToContentPane(event);
    }

    private Button getStationCreatorEndStationButton(final VBox stationCreator){
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

    private Button getStationCreatorNeighborButton(final VBox stationCreator){
        Button button = new Button("Nachbar hinzufügen");
        button.setOnMouseReleased(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                stationCreator.setVisible(false);
                putValuesFromStationCreatorIntoStationView(stationCreator);
                contentPaneContainer.addActualStationToActualLine();
                contentPaneContainer.addLine(contentPaneContainer.getActualLine());
                renderAll();
                setNeighborToStationView();
                //setStationOnMouseClick(stationCreator);
            }
        });
        return button;
    }

    private void setNeighborToStationView(){
        Polygon connector = new Polygon(50,50,100,100);
        Rectangle rectangle = contentPaneContainer.getActualStation().getRectangle();
        //connector.layoutXProperty().bind(rectangle.xProperty());
        //connector.layoutYProperty().bind(rectangle.yProperty());
       contentPane.getChildren().add(connector);
        System.out.println("polygon?");
    }

    private void putValuesFromStationCreatorIntoStationView(VBox stationCreator) {
        StationView stationView = contentPaneContainer.getActualStation();
        TextField nameTextField = (TextField) stationCreator.getChildren().get(0);
        String name = nameTextField.getText();
        HBox zoneHBox = (HBox) stationCreator.getChildren().get(1);
        ChoiceBox zoneChoiceBox = (ChoiceBox) zoneHBox.getChildren().get(1);
        stationView.setName(new Text(name));
        stationView.setZone((int)zoneChoiceBox.getValue());
        stationView.setRectangle(new RectangleFactory().getRectangleByColor(stationView.getColor()));
    }

    private void setStationOnMouseClick(VBox stationCreator){
        contentPane.setOnMouseClicked(new EventHandler<javafx.scene.input.MouseEvent>() {
            @Override
            public void handle(javafx.scene.input.MouseEvent event) {
                addStationViewToContentPane(event);
                renderAll();
            }
        });
    }



    //region getter and setter

    public Pane getContentPane() {
        return contentPane;
    }


    //endregion getter and setter

}
