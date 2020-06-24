package com.shad.algorithms.week1.competition;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class First {
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
        int operationValue = scanner.nextInt();
        int operationType;
        int cursorInStack = -1;
        int cursorInArray = -1;
        int val;
        int[] stack = new int[operationValue];
        int[] deletedValuesArray = new int[operationValue/2 + 1];
        for (int i = 1; i <= operationValue; i++) {
            operationType = scanner.nextInt();
            if (operationType == 1) {
                cursorInStack += 1;
                val = scanner.nextInt();
                stack[cursorInStack] = val;
            } else if (operationType == 2) {
                cursorInArray += 1;
                deletedValuesArray[cursorInArray] = stack[cursorInStack];
                cursorInStack -= 1;
            }
        }
        for (int i = 0; i <= cursorInArray; i++) {
            System.out.println(deletedValuesArray[i]);
        }
    }
}
