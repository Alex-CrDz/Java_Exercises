package Exercises.Topic_0;

import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * Exercise taked from https://www.w3resource.com/java-exercises/basic/index.php
 * Exercise #44
 */

public class SumConcatedNumbers {

    /**
     * @param inputNum is the integer value to be concatenated in the manner
     *                 num + num num + num num num
     *                 for example: 5 + 55 + 555
     * @return the string of concated number from input
     */
    public static String concatNumber(int inputNum) {
        String num = String.valueOf(inputNum);
        return num + " + " + num + num + " + " + num + num + num;
    }

    /**
     * @param inputNum is the integer value to be added in the manner described in concatNumber method
     * @return the total sum of the expresion concated by concatNumber Method
     */
    public static int sumNumber(int inputNum) {
        String num = String.valueOf(inputNum);
        return inputNum + Integer.parseInt(num + num) + Integer.parseInt(num + num + num);
    }

    public static void main(String[] args) {
        Scanner read = new Scanner(System.in);
        int inputNum;
        while (true) {      //  persistent loop that keeps asking for input when wrong input is taken
            System.out.print("Input number: ");
            try {
                inputNum = read.nextInt();
            } catch (InputMismatchException e) {
                read.nextLine();    //  flush input buffer from scanner
                continue;
            }
            if (inputNum > 0)
                break;
        }
        System.out.println(concatNumber(inputNum) + " = " + sumNumber(inputNum));
    }
}