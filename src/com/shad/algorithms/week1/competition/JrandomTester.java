package com.shad.algorithms.week1.competition;

import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class JrandomTester {

    int n;
    LinkedList<Integer> ids = new LinkedList<>();

    public JrandomTester(int n) {
        this.n = n;
        for (int i = 1; i <= n; i++) {
            ids.add(i);
        }
    }

    public String generateEvents() {
        StringBuilder builder = new StringBuilder();
        builder.append(n).append("\n");
        String event;
        int dequeueNum = 0;
        for (int i = 0; i < n; i++) {
            event = generateEvent();
            while (event.equals("-") && dequeueNum == 0) { event = generateEvent();}
            if (event.equals("-")) {
                dequeueNum--;
            } else {
                dequeueNum++;
            }
            builder.append(event).append("\n");
        }
        return builder.toString();
    }

    public String generateEvent() {
        int operationType = (int) (Math.random() * 3);
        if (operationType == 0) {
            return "+ " + ids.poll();
        } else if (operationType == 1) {
            return "* " + ids.poll();
        } else {
            return "-";
        }
    }

    public static void main(String... args) throws IOException {
//        System.out.println(new JrandomTester(10).generateEvents());
        J forTestJ = new J();
        J_upd forTestJ_upd = new J_upd();
        for (int i = 0; i <= 1000; i++) {
            String input = new JrandomTester(1000).generateEvents();
            List<Integer> str1 = forTestJ.execute(new ByteArrayInputStream(input.getBytes()));
            List<Integer> str2 = null;
            try {
                str2 = forTestJ_upd.execute(new ByteArrayInputStream(input.getBytes()));
            } catch (Exception ex) {
                System.out.println("Error with");
                System.out.println(input);
                throw new RuntimeException(ex);
            }

            if (!str1.equals(str2)) {
                System.out.println(input);
                System.out.println("output 1:");
                System.out.println(str1);
                System.out.println("output 2:");
                System.out.println(str2);
            }
        }
    }
}
