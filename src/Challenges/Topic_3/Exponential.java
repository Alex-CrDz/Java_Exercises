package Challenges.Topic_3;

import java.text.DecimalFormat;
import java.util.Scanner;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.stream.IntStream;

import static java.lang.Math.pow;

public class Exponential {

    public static Function<Integer, Double> factorialFn = (number) -> {
        return (double) IntStream.rangeClosed(1, number).reduce(1, (int x, int y) -> x * y);
    };

    public static BiFunction<Double, Integer, Double> n_term = (valueX, term) -> {
        double exp = pow(valueX, term);
        double factorial = factorialFn.apply(term);
        double result = exp / factorial;
        return result;
    };

    public static Function<Double, Double> exponentialFn = valueX -> {
        DecimalFormat df = new DecimalFormat("0.0000");
        int terms = 9;
        double summary = 0;
        for (int i = 1; i <= terms; i++) {
            summary += n_term.apply(valueX, i);
        }
        return Double.valueOf(df.format(1.0 + summary));
    };

    public static void main(String[] args) {
        DecimalFormat df = new DecimalFormat("0.0000");
        Scanner read = new Scanner(System.in);
        int numCases;
        double numCase;
        numCases = read.nextInt();
        for (int i = 0; i < numCases; i++) {
            numCase = read.nextDouble();
            System.out.println(exponentialFn.apply(numCase));
        }
    }
}
