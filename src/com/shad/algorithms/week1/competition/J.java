package com.shad.algorithms.week1.competition;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;

public class J {
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
        FastScanner scanner = new FastScanner(System.in);
        int operationCount = scanner.nextInt();
        LinkedList<Integer> queue = new LinkedList<>();
        StringBuilder builder = new StringBuilder();
        char operationType;
        String line;
        for (int i = 1; i <= operationCount; i++) {
            line = scanner.nextLine();
            operationType = line.charAt(0);
            if (operationType == '+') {
                queue.add(Integer.valueOf(line.substring(2)));
            } else if (operationType == '*') {
                queue.add((int)Math.ceil(queue.size()/2.0), Integer.valueOf(line.substring(2)));
            } else if (operationType == '-') {
                builder.append(queue.poll()).append("\n");
            }
        }
        System.out.println(builder.toString());
    }

    public List<Integer> execute(InputStream input) throws IOException {
        FastScanner scanner = new FastScanner(input);
        int operationCount = scanner.nextInt();
        LinkedList<Integer> queue = new LinkedList<>();
//        StringBuilder builder = new StringBuilder();
        List<Integer> returnArray = new ArrayList<>();
        char operationType;
        String line;
        for (int i = 1; i <= operationCount; i++) {
            line = scanner.nextLine();
            operationType = line.charAt(0);
            if (operationType == '+') {
                queue.add(Integer.valueOf(line.substring(2)));
            } else if (operationType == '*') {
                queue.add((int)Math.ceil(queue.size()/2.0), Integer.valueOf(line.substring(2)));
            } else if (operationType == '-') {
//                builder.append(queue.poll()).append("\n");
                returnArray.add(queue.poll());
            }
        }
//        return builder.toString();
        return returnArray;
    }
}
