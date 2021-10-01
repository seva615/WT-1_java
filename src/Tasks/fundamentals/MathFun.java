package Tasks.fundamentals;

import java.util.Scanner;
import java.util.concurrent.CancellationException;

public class MathFun {
    public static double calc(double x, double y) {
        Numbers.requireNonNaN(x);
        Numbers.requireNonNaN(y);

        double sin = Math.sin(x + y);
        double a = 1 + sin * sin;

        double div = 1 + (x * x) * (y * y);
        double toAbs = x - (2 * x) / div;
        double b = 2 + Math.abs(toAbs);

        return (a / b) + x;
    }

    public static void main(String[] args) {
        try {
            Scanner scanner = new Scanner(System.in);
            String cancel = "cancel";
            double x = Input.readDouble(scanner, "Enter x", cancel);
            double y = Input.readDouble(scanner, "Enter y", cancel);
            System.out.println("F(x, y)=" + calc(x, y));
        } catch (CancellationException ignored) {
        }
    }
}
