package com.shad.algorithms.week2.competition;

import java.util.Scanner;
import java.util.TreeSet;

public class N {

    public static void main(String... args) {
        Scanner scanner = new Scanner(System.in);;
        TreeSet<Integer> set = new TreeSet<>();
        StringBuilder builder = new StringBuilder();
        while (scanner.hasNextLine()) {
            String str = scanner.nextLine();
            String[] tokens = str.split(" ");
            String command = tokens[0];
            String value = tokens[1];
            if ("insert".equals(command)) {
                set.add(Integer.valueOf(value));
            } else if ("delete".equals(command)) {
                set.remove(Integer.valueOf(value));
            } else if ("exists".equals(command)) {
                builder.append(set.contains(Integer.valueOf(value))).append("\n");
            } else if ("next".equals(command)) {
                Integer element = set.higher(Integer.valueOf(value));
                if (element == null) {
                    builder.append("none").append("\n");
                } else {
                    builder.append(element).append("\n");
                }
            } else if ("prev".equals(command)) {
                Integer element = set.lower(Integer.valueOf(value));
                if (element == null) {
                    builder.append("none").append("\n");
                } else {
                    builder.append(element).append("\n");
                }
            }
        }
        System.out.println(builder.toString());
    }
}
