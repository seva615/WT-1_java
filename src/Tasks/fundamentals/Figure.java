package Tasks.fundamentals;

import java.util.Scanner;
import java.util.concurrent.CancellationException;

public class Figure {
    public static boolean contains(double yMin, double yMax,
                                   double x1, double x2,
                                   double x, double y) {
        return contains(yMin, 0, yMax, -x1, x1, -x2, x2, x, y);
    }

    public static boolean contains(double yMin, double yDelimiter, double yMax,
                                   double xMin1, double xMax1,
                                   double xMin2, double xMax2,
                                   double x, double y) {
        Numbers.requireNonNaN(yMin);
        Numbers.requireNonNaN(yDelimiter);
        Numbers.requireNonNaN(yMax);

        Numbers.requireNonNaN(xMin1);
        Numbers.requireNonNaN(xMax1);
        Numbers.requireNonNaN(xMin2);
        Numbers.requireNonNaN(xMax2);

        Numbers.requireNonNaN(x);
        Numbers.requireNonNaN(y);

        Numbers.requireMinMax(yMin, yDelimiter);
        Numbers.requireMinMax(yDelimiter, yMax);

        Numbers.requireMinMax(xMin1, xMax1);
        Numbers.requireMinMax(xMin2, xMax2);

        if (y > yDelimiter) {
            return (y <= yMax) && contains(xMin1, xMax1, x);
        } else {
            return (y >= yMin) && contains(xMin2, xMax2, x);
        }
    }

    public static boolean contains(double min, double max, double x) {
        return x >= min && x <= max;
    }

    public static void main(String[] args) {
        final String cancel = "cancel";
        String help =
                        "                     |y\n" +
                        "                     |\n" +
                        "                     | yMax\n" +
                        "               ______|______\n" +
                        "               |     |     | x1\n" +
                        "               |     |     |\n" +
                        "               |     |     |              x\n" +
                        "_______________|_____|_____|_________________\n" +
                        "          |          |  x, y    |\n" +
                        "          |          |   .      | x2\n" +
                        "          |__________|__________|\n" +
                        "                     |  yMin\n" +
                        "                     |\n" +
                        "                     |";

        System.out.println(help);
        Scanner scanner = new Scanner(System.in);
        try {
            double yMin = Input.readDouble(scanner, "Enter yMin: ", cancel);
            double yMax = Input.readDouble(scanner, "Enter yMax: ", cancel);

            double x1 = Input.readDouble(scanner, "Enter x1: ", cancel);
            double x2 = Input.readDouble(scanner, "Enter x2: ", cancel);

            while (true) {
                System.out.println("Do you want to check the point? [Yes=yes,No=other]");
                String token = scanner.nextLine();
                if (!"yes".equals(token))
                    break;
                double x = Input.readDouble(scanner, "Enter x: ", cancel);
                double y = Input.readDouble(scanner, "Enter y: ", cancel);

                boolean contains = contains(yMin, yMax, x1, x2, x, y);
                System.out.println("Contains? " + contains);
            }
        } catch (IllegalArgumentException e) {
            System.err.println("Figure creation error");
        } catch (CancellationException ignored) {
        }
    }
}
