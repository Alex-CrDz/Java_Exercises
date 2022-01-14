package Exercises;

import java.util.Scanner;

public class ReverseString {
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
