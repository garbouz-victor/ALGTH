package com.shad.algorithms.week3.competition;

import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.TreeSet;

public class H {

    public static void main(String... args) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader("trains.in"));
        int k = Integer.parseInt(reader.readLine().trim());
        TreeSet<Integer> free = new TreeSet<>();
        for (int i = 0; i < k; i++) {
            free.add(i+1);
        }
        HashMap<Integer, Integer> busy = new HashMap<>();
        StringBuilder builder = new StringBuilder();
        String line = reader.readLine();
        FileOutputStream output = new FileOutputStream("trains.out");
        while (line != null) {
            String[] operations = line.split(" ");
            if (operations[0].equals("+")) {
                Integer val = Integer.parseInt(operations[1]);
                Integer end = free.pollFirst();
                if (end == null) {
                    String result = 0 + " " + val;
                    output.write(result.getBytes());
                    return;
                } else {
                    busy.put(val, end);
                    builder.append(val).append(" ").append(end).append("\n");
                }
            } else {
                Integer val = Integer.parseInt(operations[1]);
                Integer end = busy.get(val);
                free.add(end);
                busy.remove(val);
            }
            line = reader.readLine();
        }
        reader.close();
        if (builder.length() > 1) {
            builder.deleteCharAt(builder.length() - 1);
        }

        output.write(builder.toString().getBytes());
        output.close();
    }
}
