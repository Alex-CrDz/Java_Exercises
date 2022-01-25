package Exercises.Topic_0;

import java.util.InputMismatchException;
import java.util.Scanner;

import static java.lang.Math.*;

/**
 * Exercise taked from https://www.w3resource.com/java-exercises/basic/index.php
 * Exercise #35
 */

public class PoligonArea {

    /**
     *
     * @param numSides is the integer number of sides of the polygon
     * @param sideLen is the integer value of length of each side of the polygon
     * @return the area value for the polygon taken from input
     */
    public static double getPoligonArea(int numSides, int sideLen) {
        return (numSides * pow(sideLen, 2)) / (4 * tan(PI / numSides));
    }

    public static void main(String[] args) {
        Scanner read = new Scanner(System.in);
        int numSides;
        int sideLen;
        while (true) {      //  persistent loop that keeps asking for input when wrong input is taken
            System.out.print("Input the number of sides on the polygon (>0): ");
            try {
                numSides = read.nextInt();
            } catch (InputMismatchException e) {
                read.nextLine();    //  flush input buffer from scanner
                continue;
            }
            if (numSides > 0) {
                break;
            }
        }
        while (true) {      //  persistent loop that keeps asking for input when wrong input is taken
            System.out.print("Input the length of one of the sides (>0): ");
            try {
                sideLen = read.nextInt();
            } catch (InputMismatchException e) {
                read.nextLine();    //  flush input buffer from scanner
                continue;
            }
            if (sideLen > 0) {
                break;
            }
        }
        System.out.println("The area is: " + getPoligonArea(numSides, sideLen));
    }
}
