package com.shad.algorithms.week3.competition;

import java.io.*;
import java.util.HashMap;
import java.util.StringTokenizer;
import java.util.TreeMap;

public class D {
    static class FastScanner
    {
        BufferedReader br;
        StringTokenizer stok;

        FastScanner (InputStream is)
        {
            br = new BufferedReader(new InputStreamReader(is));
        }

        String nextToken() throws IOException {
            while (stok == null || !stok.hasMoreTokens()) {
                String s = br.readLine();
                if (s == null) {
                    return null;
                }
                stok = new StringTokenizer(s);
            }
            return stok.nextToken();
        }

        int nextInt() throws IOException {
            return Integer.parseInt(nextToken());
        }

        long nextLong() throws IOException {
            return Long.parseLong(nextToken());
        }

        double nextDouble() throws IOException {
            return Double.parseDouble(nextToken());
        }

        char nextChar() throws IOException {
            return (char) (br.read());
        }

        String nextLine() throws IOException {
            return br.readLine();
        }
    }

    public static void main(String... args) throws IOException {
//        test();
        System.out.println(Dtest.getAnswer(System.in));
    }

    public static void test() throws IOException {
        for (int i = 0; i < 1000; i++) {
            String testStr = getTestString(5, 5);
            String answer1 = DRightAnswer.getAnswer(new ByteArrayInputStream(testStr.getBytes()));
            String answer2 = Dtest.getAnswer(new ByteArrayInputStream(testStr.getBytes()));
            String[] f = answer1.split(" ");
            int[] fArray = new int[f.length];
            for (int j = 0; j < f.length; j++) {
                fArray[j] = Integer.valueOf(f[j]);
            }
            String[] s = answer2.split(" ");
            int[] sArray = new int[s.length];
            for (int j = 0; j < s.length; j++) {
                sArray[j] = Integer.valueOf(s[j]);
            }
            if (sArray.length != fArray.length) {
                System.out.println("Error!");
                System.out.println("test string");
                System.out.println(testStr);
                return;
            }
            for (int j = 0; j < fArray.length; j++) {
                if (fArray[j] != sArray[j] || fArray[j] < 0) {
                    System.out.println("Error!");
                    System.out.println("test string");
                    System.out.println(testStr);
                    System.out.println("answer = " + answer2);
                    return;
                }
            }
        }
    }

    static class DRightAnswer {
        public static String getAnswer(InputStream is) throws IOException {
            FastScanner scanner = new FastScanner(is);
            int n = scanner.nextInt();
            int m = scanner.nextInt();
            TreeMap<Integer, Integer> segments = new TreeMap<>();
            for (int i = 0; i < n; i++) {
                int start = scanner.nextInt();
                int end = scanner.nextInt();
                if (start > end) {
                    int temp = start;
                    start = end;
                    end = temp;
                }
                if (segments.containsKey(start)) {
                    int count = segments.get(start);
                    segments.put(start, count++);
                } else {
                    segments.put(start, 1);
                }
                if (segments.containsKey(end + 1)) {
                    int count = segments.get(end + 1);
                    count--;
                    segments.put(end + 1, count);
                } else {
                    segments.put(end+1, -1);
                }
            }
            TreeMap<Integer, Integer> lineWithSegments = new TreeMap<>();
            int value = 0;
            for (Integer dot : segments.keySet()) {
                value += segments.get(dot);
                lineWithSegments.put(dot, value);
            }
            StringBuilder builder = new StringBuilder();
            for (int i = 0; i < m; i++) {
                int dot = scanner.nextInt();
                if (lineWithSegments.containsKey(dot)) {
                    builder.append(lineWithSegments.get(dot)).append(" ");
                } else {
                    Integer lower = lineWithSegments.lowerKey(dot);
                    Integer higher = lineWithSegments.higherKey(dot);
                    if (lower == null) {
                        builder.append(0).append(" ");
                    } else if (higher == null) {
                        builder.append(0).append(" ");
                    } else {
                        builder.append(lineWithSegments.get(lower)).append(" ");
                    }
                }
            }
            builder.deleteCharAt(builder.length() - 1);
            return builder.toString();
        }
    }

    static class Dtest {
        public static String getAnswer(InputStream is) throws IOException {
            FastScanner scanner = new FastScanner(is);
            int n = scanner.nextInt();
            int m = scanner.nextInt();
            TreeMap<Integer, Integer> segments = new TreeMap<>();
            for (int i = 0; i < n; i++) {
                int start = scanner.nextInt();
                int end = scanner.nextInt();
                if (start > end) {
                    int temp = start;
                    start = end;
                    end = temp;
                }
                if (segments.containsKey(start)) {
                    int count = segments.get(start);
                    count++;
                    segments.put(start, count);
                } else {
                    segments.put(start, 1);
                }
                if (segments.containsKey(end + 1)) {
                    int count = segments.get(end + 1);
                    count--;
                    segments.put(end + 1, count);
                } else {
                    segments.put(end + 1, -1);
                }
            }
            TreeMap<Integer, Integer> lineWithSegments = new TreeMap<>();
            int value = 0;
            for (Integer dot : segments.keySet()) {
                value += segments.get(dot);
                lineWithSegments.put(dot, value);
            }
            StringBuilder builder = new StringBuilder();
            for (int i = 0; i < m; i++) {
                int dot = scanner.nextInt();
                if (lineWithSegments.containsKey(dot)) {
                    builder.append(lineWithSegments.get(dot)).append(" ");
                } else {
                    Integer lower = lineWithSegments.lowerKey(dot);
                    Integer higher = lineWithSegments.higherKey(dot);
                    if (lower == null) {
                        builder.append(0).append(" ");
                    } else if (higher == null) {
                        builder.append(0).append(" ");
                    } else {
                        builder.append(lineWithSegments.get(lower)).append(" ");
                    }
                }
            }
            builder.deleteCharAt(builder.length() - 1);
            return builder.toString();
        }
    }

    public static String getTestString(int maxN, int minM) {
        int n = 2 + (int)(Math.random() * ((maxN - 2) + 1));
        int m = minM + (int)(Math.random() * ((maxN - minM) + 1));
        StringBuilder builder = new StringBuilder();
        builder.append(n).append(" ").append(m).append("\n");
        for (int i = 0; i < n; i++) {
            int start = -10 + (int)(Math.random() * ((20 - 1) + 1));
            int end = -10 + (int)(Math.random() * ((20 - 1) + 1));
            builder.append(start).append(" ").append(end).append("\n");
        }
        for (int i = 0; i < m; i++) {
            int val = -20 + (int)(Math.random() * ((30 - 1) + 1));
            builder.append(val).append(" ");
        }
        builder.deleteCharAt(builder.length() - 1);
        return builder.toString();
    }
}
