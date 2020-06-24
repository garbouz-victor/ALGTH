package com.shad.algorithms.other;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Scanner;

public class PasswordChecker {
    public static void main(String... args) {
        Scanner scanner = new Scanner(System.in);
        String password = scanner.nextLine();
        if (password.length() < 7) {
            System.out.println("Weak");
            return;
        } else {
            HashSet<Character> digits = new HashSet<>();
            Character[] specialsArray = {'!','@','#','$','%','&','*'};
            HashSet<Character> specials = new HashSet<>(Arrays.asList(specialsArray));
            int specialCounter = 0;
            for (int i = 0; i < password.length(); i++) {
                if (Character.isDigit(password.charAt(i))) {
                    digits.add(password.charAt(i));
                } else if (specials.contains(password.charAt(i))) {
                    specials.remove(password.charAt(i));
                    specialCounter++;
                }
                if (specialCounter >= 2 && digits.size() >=2) {
                    System.out.println("Strong");
                    return;
                }
            }
            System.out.println("Weak");
        }
    }
}
