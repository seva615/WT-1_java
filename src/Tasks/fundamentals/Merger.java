package Tasks.fundamentals;

import java.util.Arrays;
import java.util.Objects;
import java.util.Scanner;

public class Merger {
    public static double[] merge(double[] a, int from1, int to1,
                                 double[] b, int from2, int to2,
                                 DoubleComparator comparator) {
        Objects.requireNonNull(a);
        Objects.checkFromToIndex(from1, to1, a.length);

        Objects.requireNonNull(b);
        Objects.checkFromToIndex(from2, to2, b.length);

        int len = (to1 - from1) + (to2 - from2);

        if (len == 0)
            return new double[0];

        int resultSize = to1 - from1;
        double[] array = new double[len];
        System.arraycopy(a, from1, array, 0, resultSize);

        for (int i = from2; i < to2; i++) {
            double element = b[i];

            int destPos = Numbers.binarySearch(element, array, 0, resultSize, comparator);
            if (destPos < 0)
                destPos = (-destPos) - 1;

            System.arraycopy(array, destPos, array, destPos + 1, resultSize - destPos);
            array[destPos] = element;
            resultSize++;
        }
        return array;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter sorted array");
        double[] a = Input.readArray(scanner);

        System.out.println("Enter sorted array");
        double[] b = Input.readArray(scanner);

        System.out.println("First array");
        System.out.println(Arrays.toString(a));

        System.out.println("Second array");
        System.out.println(Arrays.toString(b));

        double[] merge = merge(
                a, 0, a.length,
                b, 0, b.length,
                DoubleComparator.NORMAL);

        System.out.println("Merged array");
        System.out.println(Arrays.toString(merge));
    }
}
