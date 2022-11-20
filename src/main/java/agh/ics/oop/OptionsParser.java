package agh.ics.oop;

public class OptionsParser {
    public Direction[] parse(String[] options) {
        int i = 0;
        for (String element: options) {
            switch (element) {
                case "f", "forward", "b", "backward", "r", "right", "l", "left":
                    i+=1;
                    break;
            }
        }
        Direction[] directions = new Direction[i];
        i = 0;
        for (String element: options) {

            switch (element) {
                case "f", "forward":
                    directions[i] = Direction.FORWARD;
                    break;
                case "b", "backward":
                    directions[i] = Direction.BACKWARD;
                    break;
                case "l", "left":
                    directions[i] = Direction.LEFT;
                    break;
                case "r", "right":
                    directions[i] = Direction.RIGHT;
                    break;
                default:
                    i -= 1;
                    throw new IllegalArgumentException(element + " is not legal move specification");
            }
            i+=1;
        }
        return directions;
    }
}
