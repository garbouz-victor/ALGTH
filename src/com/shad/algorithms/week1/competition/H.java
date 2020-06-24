package com.shad.algorithms.week1.competition;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class H {
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
        int[] ids = new int[100001];
        int[] removedCounter = new int[100001];
        int operationType;
        int id;
        int removedElements = 0;
        for (int i = 1; i <= operationCount; i++) {
            operationType = scanner.nextInt();
            if (operationType == 1) {
                id = scanner.nextInt();
                ids[id] = queue.size();
                removedCounter[id] = removedElements;
                queue.add(id);
            } else if (operationType == 2) {
                queue.poll();
                removedElements++;
            } else if (operationType == 3) {
                queue.removeLast();
            } else if (operationType == 4) {
                id = scanner.nextInt();
                builder.append(ids[id] + removedCounter[id] - removedElements).append("\n");
            } else {
                builder.append(queue.getFirst()).append("\n");
            }
        }
        System.out.println(builder.toString());
    }
}
