package Exercises;

import java.util.Scanner;

public class SumConcatedNumbers {

    public static String concatNumber(int inputNum) {
        String num = String.valueOf(inputNum);
        return num + " + " + num + num + " + " + num + num + num;
    }

    public static int sumNumber(int inputNum) {
        String num = String.valueOf(inputNum);
        return inputNum + Integer.parseInt(num + num) + Integer.parseInt(num + num + num);
    }

    public static void main(String[] args) {
        Scanner read = new Scanner(System.in);
        int inputNum;
        System.out.print("Input number: ");
        inputNum = read.nextInt();
        System.out.println(concatNumber(inputNum) + " = " + sumNumber(inputNum));
    }
}