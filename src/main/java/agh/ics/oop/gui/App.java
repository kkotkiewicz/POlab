package agh.ics.oop.gui;

import agh.ics.oop.*;
import javafx.application.Application;
import javafx.geometry.HPos;
import javafx.scene.Scene;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.stage.Stage;
import javafx.scene.control.Label;

import java.util.Arrays;
import java.util.List;
import java.util.Vector;
import java.util.concurrent.TimeUnit;


public class App extends Application {
    public GridPane gridPane = new GridPane();
    public Scene scene = new Scene(gridPane, 600, 600);

    private AbstractWorldMap map;
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
            Direction[] directions = new OptionsParser().parse(args);
            //        IWorldMap map = new RectangularMap(10, 5);
            this.map = new GrassField(10);

            Vector2d[] positions = {new Vector2d(2,2), new Vector2d(3,4)};
            IEngine engine = new SimulationEngine(directions, map, positions);
            engine.run();

            System.out.println(this.map);
        }
        catch (IllegalArgumentException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void start(Stage primaryStage) throws Exception {

        int height = 25;
        int width = 25;
        Label firstLabel = new Label("x/y");
        gridPane.getColumnConstraints().add(new ColumnConstraints(width));
        GridPane.setHalignment(firstLabel, HPos.CENTER);
        gridPane.getRowConstraints().add(new RowConstraints(height));
        gridPane.add(firstLabel, 0,0,1,1);
        gridPane.setGridLinesVisible(true);
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
                Label label = new Label(map.objectAt(new Vector2d(i,j)).toString());
                gridPane.add(label, 1 + i - lowerLeft.x, 1 + upperRight.y - j,1,1);
                GridPane.setHalignment(label, HPos.CENTER);
            }
        }


        primaryStage.setScene(scene);
        primaryStage.show();
    }

}
