package Tasks.fundamentals;

import java.util.Arrays;
import java.util.Objects;
import java.util.Scanner;

public class Prime {
    private static final int[] EMPTY_ARRAY = {};

    public static int[] primeNumberIndexes(int[] numbers) {
        Objects.requireNonNull(numbers);

        final int len = numbers.length;
        if (len == 0)
            return EMPTY_ARRAY;

        int size = 0;
        int[] indexes = new int[len];

        for (int i = 0; i < len; i++) {
            if (isPrime(numbers[i])) {
                indexes[size++] = i;
            }
        }
        if (size == 0)
            return EMPTY_ARRAY;
        if (size == len)
            return indexes;
        return Arrays.copyOf(indexes, size);
    }

    public static boolean isPrime(long number) {
        if (number < 2)
            return false;
        if (number == 2)
            return true;
        if ((number & 1) == 0)
            return false;
        for (long i = 3; i * i <= number; i += 2)
            if (number % i == 0)
                return false;
        return true;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int[] array = Arrays.stream(Input.readArray(scanner)).
                mapToInt(value -> (int) value).toArray();

        System.out.println("Your array:");
        System.out.println(Arrays.toString(array));

        int[] primeNumberIndexes = primeNumberIndexes(array);

        if (primeNumberIndexes.length == 0) {
            System.out.println("Prime number not found");
        } else {
            System.out.println("Prime number numbers:");
            int[] primeNumberNumbers = Arrays.stream(primeNumberIndexes).map(i -> i + 1).toArray();
            System.out.println(Arrays.toString(primeNumberNumbers));
        }
    }
}