package agh.ics.oop.gui;

import agh.ics.oop.*;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.HPos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.control.Label;

import java.util.Arrays;
import java.util.List;
import java.util.Vector;
import java.util.concurrent.TimeUnit;


public class App extends Application implements ISimulationObserver {
    public GridPane gridPane = new GridPane();
    public Scene scene;

    private AbstractWorldMap map;
    private Thread thread;
    private ThreadedSimulationEngine engine;
    @Override
    public void init() throws Exception {
        super.init();

        Parameters parameters = getParameters();
        List<String> elements = parameters.getRaw();
        String[] args = new String[elements.size()];
        for (int i = 0; i < elements.size(); i++) {
            args[i] = elements.get(i);
        }
        try {
//            Direction[] directions = new OptionsParser().parse(args);
            //        IWorldMap map = new RectangularMap(10, 5);
            this.map = new GrassField(10);

            Vector2d[] positions = {new Vector2d(2,2), new Vector2d(3,4)};
            engine = new ThreadedSimulationEngine(map, positions);
            System.out.println(engine);

            engine.addObserver(this);


        }
        catch (IllegalArgumentException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setOnCloseRequest(e -> {
            Platform.exit();
            System.exit(0);
        });

        gridPane.setHgap(0);
        gridPane.setVgap(0);
        VBox vBox = new VBox();
        TextField textField = new TextField();
        Button button = new Button("Start");
        button.setOnAction(event -> {
            String[] strMoves = textField.getCharacters().toString().split(" ");

            Direction[] moves = new OptionsParser().parse(strMoves);
            System.out.println(engine);
            engine.setDirections(moves);
            this.thread = new Thread(this.engine);
            this.thread.start();
        });

        vBox.getChildren().addAll(textField, button);

        createGrid();

        vBox.getChildren().add(gridPane);
        scene = new Scene(vBox);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void createGrid() {
        int height = 40;
        int width = 40;
        Label firstLabel = new Label("x/y");

        gridPane.getColumnConstraints().add(new ColumnConstraints(width));

        GridPane.setHalignment(firstLabel, HPos.CENTER);

        gridPane.getRowConstraints().add(new RowConstraints(height));

        gridPane.add(firstLabel, 0,0,1,1);


        Vector2d lowerLeft = map.getLowerLeft();
        Vector2d upperRight = map.getUpperRight();
        System.out.println(lowerLeft.toString());
        System.out.println(upperRight.toString());

        for (int i = lowerLeft.x; i <= upperRight.x; i++) {
            Label label = new Label("" + i);
            gridPane.add(label, 1 + i - lowerLeft.x, 0, 1, 1);
            gridPane.getColumnConstraints().add(new ColumnConstraints(width));
            GridPane.setHalignment(label, HPos.CENTER);

        }

        for (int i = upperRight.y; i >= lowerLeft.y; i--) {
            Label label = new Label("" + i);
            gridPane.add(label, 0, 1 + upperRight.y - i, 1 , 1);
            gridPane.getRowConstraints().add(new RowConstraints(height));
            GridPane.setHalignment(label, HPos.CENTER);
        }

        for (int i = lowerLeft.x; i <= upperRight.x; i++) {
            for (int j = upperRight.y; j>= lowerLeft.y; j--) {
                if (!map.isOccupied(new Vector2d(i,j))) {
                    continue;
                }
                GuiElementBox guiElementBox = new GuiElementBox(map.objectAt(new Vector2d(i,j)));
                VBox vBox = guiElementBox.getVBox();
                gridPane.add(vBox, 1 + i - lowerLeft.x, 1 + upperRight.y - j,1,1);
                GridPane.setHalignment(vBox, HPos.CENTER);
            }
        }
        gridPane.setGridLinesVisible(true);
    }

    public void refresh() {
        Platform.runLater(() -> {
            gridPane.getChildren().clear();
            gridPane.getColumnConstraints().clear();
            gridPane.getRowConstraints().clear();
            gridPane.setGridLinesVisible(false);
            createGrid();
        });
    }



}
