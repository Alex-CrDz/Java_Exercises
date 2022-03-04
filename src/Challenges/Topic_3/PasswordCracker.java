package Challenges.Topic_3;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.function.BiFunction;

public class PasswordCracker {

    public static BiFunction<String, List<String>, String> passChecker = (loginAttempt, passwordList) -> {
        String password = "";
        for (int i = 1; i <= loginAttempt.length(); i++) {
            String fragment = loginAttempt.substring(0, i);
            if (passwordList.contains(fragment)) {
                loginAttempt = loginAttempt.substring(i);
                password += fragment + " ";
                i = 0;
            }
        }
        if (loginAttempt.isEmpty()) {
            password = password.trim();
            return password;
        }
        return "WRONG PASSWORD";
    };

    public static void main(String[] args) {
        Scanner read = new Scanner(System.in);
        int numCases;
        numCases = read.nextInt();
        for (int i = 0; i < numCases; i++) {
            int numUsers;
            numUsers = read.nextInt();
            List<String> passwordList = new ArrayList<String>();
            String loginAttempt;
            for (int j = 0; j < numUsers; j++) {
                passwordList.add(read.next());
            }
            loginAttempt = read.next();
            System.out.println(passChecker.apply(loginAttempt, passwordList));
        }
    }
}
