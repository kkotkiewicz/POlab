package agh.ics.oop;
import agh.ics.oop.gui.App;
import javafx.application.Application;

import static agh.ics.oop.Direction.*;


public class World {
    public static void main(String[] args) {
        Application.launch(App.class, args);
    }
    public static void run(Direction[] directions) {
        for (Direction element : directions) {
            switch (element) {
                case FORWARD:
                    System.out.println("Zwierzak idzie do przodu");
                    break;
                case RIGHT:
                    System.out.println("Zwierzak skręca w prawo");
                    break;
                case LEFT:
                    System.out.println("Zwierzak skręca w lewo");
                    break;
                case BACKWARD:
                    System.out.println("Zwierzak idzie do tyłu");
                    break;

            }
        }
    }
    public static Direction[] convertToEnum(String[] string) {
        Direction[] directions = new Direction[string.length];
        int i = 0;
        for (String element : string) {
            switch (element) {
                case "f":
                    directions[i] = Direction.FORWARD;
                    break;
                case "r":
                    directions[i] = Direction.RIGHT;
                    break;
                case "l":
                    directions[i] = Direction.LEFT;
                    break;
                case "b":
                    directions[i] = Direction.BACKWARD;
                    break;
                default:
                    directions[i] = Direction.NONE;
                    break;

            }
            i+=1;

        }
        return directions;
    }
}
