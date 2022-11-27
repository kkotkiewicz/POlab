package agh.ics.oop.gui;

import agh.ics.oop.IMapElement;
import javafx.geometry.Pos;
import javafx.scene.image.ImageView;
import javafx.scene.image.Image;
import javafx.scene.layout.VBox;
import javafx.scene.control.Label;

import java.awt.*;

public class GuiElementBox {
    GuiElementBox(IMapElement element) {
        this.element = element;
        image = new Image(this.element.getImageResource());
        imageView = new ImageView();
        vBox = new VBox(2);
        label = new Label(element.getLocation().toString());
        setProperties();
    }
    private IMapElement element;
    private Image image;
    private ImageView imageView;
    private VBox vBox;
    private Label label;

    private void setProperties() {
        imageView.setImage(image);
        imageView.setFitHeight(20);
        imageView.setFitWidth(20);
        vBox.getChildren().add(imageView);
        vBox.getChildren().add(label);
        vBox.setAlignment(Pos.CENTER);
    }

    public VBox getVBox() {
        return vBox;
    }
}
