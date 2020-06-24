package com.shad.algorithms.week1.competition;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Third {
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
        int stackSize = operationCount;
//        if (operationCount > 100) {
//            stackSize = operationCount/4;
//        } else {
//            stackSize = 25;
//        }
        int stackWithMinSize = stackSize * 2;
        int[] stackWithMin = new int[stackWithMinSize];
        int stackPosition = -1;
        int arraySize = stackSize;
//        int[] arrayWithMin = new int[arraySize];
//        int positionInArray = -1;
        int currentMin = 2_147_483_647;
        int operationType;
        int val;
        StringBuilder builder = new StringBuilder();
        for (int i = 1; i <= operationCount; i++) {
            operationType = scanner.nextInt();
            if (operationType == 1) {
                val = scanner.nextInt();
//                if (stackPosition >= stackWithMinSize - 1) {
//                    int[] newStack = new int[stackWithMinSize * 2];
//                    System.arraycopy(stackWithMin, 0, newStack, 0, stackWithMinSize);
//                    stackWithMin = newStack;
//                    stackWithMinSize *= 2;
//                    stackSize *= 2;
//                }
                if (val < currentMin) {
                    currentMin = val;
                }
                stackPosition += 1;
                stackWithMin[stackPosition] = val;
                stackPosition += 1;
                stackWithMin[stackPosition] = currentMin;
            } else if (operationType == 2) {
                stackPosition -= 2;
                if (stackPosition >= 0) {
                    currentMin = stackWithMin[stackPosition];
                } else {
                    currentMin = 2_147_483_647;
                }
            } else if (operationType == 3) {
//                if (positionInArray >= arraySize - 1) {
//                    int[] newArray = new int[arraySize * 2];
//                    System.arraycopy(arrayWithMin, 0, newArray, 0, arraySize);
//                    arrayWithMin = newArray;
//                    arraySize *= 2;
//                }
//                positionInArray += 1;
//                arrayWithMin[positionInArray] = currentMin;
                builder.append(currentMin).append("\n");
            }
        }
        System.out.println(builder.toString());
    }
}
