package Tasks.fundamentals;

import java.util.Objects;
import java.util.Scanner;
import java.util.concurrent.CancellationException;
import java.util.function.DoubleUnaryOperator;

public class RangeFunctionCaller {
    public static void callFunctionOnRange(DoubleUnaryOperator function,
                                           double a, double b, double h,
                                           DoubleBiConsumer callback) {
        Objects.requireNonNull(function);
        Objects.requireNonNull(callback);

        Numbers.requireNonNaN(a);
        Numbers.requireNonNaN(b);
        Numbers.requireNonNaN(h);

        Numbers.requireMinMax(a, b);

        Numbers.requireNonNegative(h);

        while (a < b) {
            callback.accept(a, function.applyAsDouble(a));
            a += h;
        }
    }

    public static void callFunctionOnRange(double a, double b, double h) {
        callFunctionOnRange(Math::tan, a, b, h, (x, y) -> {
            System.out.printf("x = %.5f, F(x) = %.5f \n", x, y);
        });
    }

    public static void main(String[] args) {
        try {
            String cancel = "cancel";
            Scanner scanner = new Scanner(System.in);

            double a = Input.readDouble(scanner, "Enter a:", cancel);
            double b = Input.readDouble(scanner, "Enter b:", cancel);
            double h = Input.readDouble(scanner, "Enter h:", cancel);

            callFunctionOnRange(a, b, h);
        } catch (IllegalArgumentException e) {
            System.err.println(e.getMessage() == null ? "Error" : e.getMessage());
        } catch (CancellationException ignored) {
        }
    }
}
