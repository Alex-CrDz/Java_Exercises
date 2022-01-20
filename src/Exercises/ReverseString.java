package Exercises;

import java.util.Scanner;

/**
 * Exercise taked from https://www.w3resource.com/java-exercises/basic/index.php
 * Exercise #37
 */

public class ReverseString {

    /**
     *
     * @param input is the string to be reversed
     * @return a new string object with the reversed input string
     */
    public static String reverseString(String input) {
        String reversed = "";
        for (int i = input.length() - 1; i >= 0; i--) {
            reversed += input.charAt(i);
        }
        return reversed;
    }

    public static void main(String[] args) {
        Scanner read = new Scanner(System.in);
        String input;
        System.out.print("Input a string: ");
        input = read.nextLine();
        System.out.print("Reverse string: " + reverseString(input));
    }
}
