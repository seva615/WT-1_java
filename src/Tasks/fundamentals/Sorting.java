package Tasks.fundamentals;

import java.util.Arrays;
import java.util.Objects;
import java.util.Scanner;
import java.util.concurrent.CancellationException;

public class Sorting {
    public static void sort(double[] array, DoubleComparator comparator) {
        Objects.requireNonNull(array);
        sort(array, 0, array.length, comparator);
    }

    public static void sort(double[] array, int from, int to, DoubleComparator comparator) {
        Objects.requireNonNull(array);
        Objects.checkFromToIndex(from, to, array.length);
        Objects.requireNonNull(comparator);

        int i = from + 1;
        int j = i + 1;
        while (i < to) {
            if (i <= 0 || comparator.compare(array[i - 1], array[i]) <= 0) {
                i = j;
                j++;
            } else {
                double tmp = array[i];
                array[i] = array[i - 1];
                array[i - 1] = tmp;
                i--;
            }
        }
    }

    public static void main(String[] args) {
        try {
            Scanner scanner = new Scanner(System.in);
            double[] array = Input.readArray(scanner);
            System.out.println("Your array:");
            System.out.println(Arrays.toString(array));

            sort(array, DoubleComparator.NORMAL);

            System.out.println("Sorted array:");
            System.out.println(Arrays.toString(array));
        } catch (CancellationException ignored) {
        }
    }
}
