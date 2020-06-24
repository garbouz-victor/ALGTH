package com.shad.algorithms.contest.exam1;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class F {

    public static void main(String... args) {
        Scanner scanner = new Scanner(System.in);
        String line = scanner.nextLine();
        Pattern pattern = Pattern.compile("(\\w+,\\s*\\w+)(\\s+or)(\\s+\\w+)");
        Matcher matcher = pattern.matcher(line);
        while (matcher.find()) {
            String first = matcher.group(1);
            String second = matcher.group(2);
            String third = matcher.group(3);
            line = line.replaceFirst(first+second+third, first + "," + second + third);
        }
        pattern = Pattern.compile("(\\w+,\\s*\\w+)(\\s+and)(\\s+\\w+)");
        matcher = pattern.matcher(line);
        while (matcher.find()) {
            String first = matcher.group(1);
            String second = matcher.group(2);
            String third = matcher.group(3);
            line = line.replaceFirst(first+second+third, first + "," + second + third);
        }
        pattern = Pattern.compile("(\\w+,\\s*\\w+)(\\s+nor)(\\s+\\w+)");
        matcher = pattern.matcher(line);
        while (matcher.find()) {
            String first = matcher.group(1);
            String second = matcher.group(2);
            String third = matcher.group(3);
            line = line.replaceFirst(first+second+third, first + "," + second + third);
        }
        System.out.println(line);
    }
}
