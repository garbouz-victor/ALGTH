package com.shad.algorithms.week1.competition;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class First_2 {
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
        int stackSize;
        if (operationValue > 100) {
            stackSize = operationValue/4;
        } else {
            stackSize = 25;
        }
        int[] stack = new int[stackSize];
        int arraySize = stackSize;
        int[] deletedValuesArray = new int[arraySize];
        for (int i = 1; i <= operationValue; i++) {
            operationType = scanner.nextInt();
            if (operationType == 1) {
                cursorInStack += 1;
                val = scanner.nextInt();
                if (cursorInStack >= stackSize) {
                    int[] newStack = new int[stackSize * 2];
                    System.arraycopy(stack, 0, newStack, 0, stackSize);
                    stack = newStack;
                    stackSize *= 2;
                }
                stack[cursorInStack] = val;
            } else if (operationType == 2) {
                cursorInArray += 1;
                if (cursorInArray >= arraySize) {
                    int[] newArray = new int[arraySize * 2];
                    System.arraycopy(deletedValuesArray, 0, newArray, 0, arraySize);
                    deletedValuesArray = newArray;
                    arraySize *= 2;
                }
                deletedValuesArray[cursorInArray] = stack[cursorInStack];
                cursorInStack -= 1;
            }
        }
        for (int i = 0; i <= cursorInArray; i++) {
            System.out.println(deletedValuesArray[i]);
        }
    }
}
