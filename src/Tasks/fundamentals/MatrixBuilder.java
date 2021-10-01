package Tasks.fundamentals;

import java.util.Arrays;
import java.util.Objects;
import java.util.Scanner;
import java.util.concurrent.CancellationException;

public class MatrixBuilder {
    public static double[][] buildMatrix(double[] src) {
        Objects.requireNonNull(src);
        return buildMatrix(src, 0, src.length);
    }

    public static double[][] buildMatrix(double[] src, int from, int to) {
        Objects.requireNonNull(src);
        Objects.checkFromToIndex(from, to, src.length);

        int len = to - from;

        if (len == 0)
            return new double[0][];

        double[][] matrix = new double[len][len];
        for (int y = 0; y < len; y++) {
            for (int x = 0; x < len; x++) {
                matrix[y][x] = src[((y + x) % len) + from];
            }
        }
        return matrix;
    }

    public static void main(String[] args) {
        try {
            Scanner scanner = new Scanner(System.in);
            double[] array = Input.readArray(scanner);
            System.out.println("Your array:");
            System.out.println(Arrays.toString(array));

            double[][] matrix = buildMatrix(array);
            System.out.println("Matrix:");
            for (double[] row : matrix)
                System.out.println(Arrays.toString(row));
        } catch (CancellationException ignored) {
        }
    }
}
