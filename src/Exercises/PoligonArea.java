package Exercises;

import java.util.InputMismatchException;
import java.util.Scanner;

import static java.lang.Math.*;

public class PoligonArea {
    public static double getPoligonArea(int numSides, int sideLen) {
        return (numSides * pow(sideLen, 2)) / (4 * tan(PI / numSides));
    }

    public static void main(String[] args) {
        Scanner read = new Scanner(System.in);
        int numSides;
        int sideLen;
        while (true) {
            System.out.print("Input the number of sides on the polygon (>0): ");
            try {
                numSides = read.nextInt();
            } catch (InputMismatchException e) {
                read.nextLine();
                continue;
            }
            if (numSides > 0) {
                break;
            }
        }
        while (true) {
            System.out.print("Input the length of one of the sides (>0): ");
            try {
                sideLen = read.nextInt();
            } catch (InputMismatchException e) {
                read.nextLine();
                continue;
            }
            if (sideLen > 0) {
                break;
            }
        }
        System.out.println("The area is: " + getPoligonArea(numSides, sideLen));
    }
}
