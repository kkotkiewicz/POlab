package agh.ics.oop;
import static agh.ics.oop.Direction.*;


public class World {
    public static void main(String[] args) {

        System.out.println("Start");

        run(convertToEnum(args));
        System.out.println("Stop");
        Vector2d position1 = new Vector2d(1,2);
        System.out.println(position1);
        Vector2d position2 = new Vector2d(-2,1);
        System.out.println(position2);
        System.out.println(position1.add(position2));
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
