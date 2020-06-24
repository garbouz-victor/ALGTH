package com.shad.algorithms.week1.competition;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class Forth {
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
//        FastScanner scanner = new FastScanner(System.in);
//        int n = scanner.nextInt();
        int n = 100000;
        int[] firstCards = new int[n];
        int[] secondCards = new int[n];
        int firstFront = 0, secondFront = 0;
        int firstTail = n/2 - 1, secondTail = n/2 - 1;
        for (int i = 0; i <= firstTail; i++) {
            firstCards[i] = i + 1;
        }
//        for (int i = 0; i <= firstTail; i++) {
//            firstCards[i] = scanner.nextInt();
//        }
         for (int i = 0; i <= secondTail; i++) {
            secondCards[i] = (firstCards[i] + i) % n;
        }
//        for (int i = 0; i <= secondTail; i++) {
//            secondCards[i] = scanner.nextInt();
//        }
        int steps = 0;
        int maxSteps = 200000;
//        boolean traceEnabled = true;
//        while (steps < maxSteps) {
////            if (traceEnabled) {
////                System.out.println(steps);
////                trace(firstCards, secondCards, firstFront, secondFront, firstTail, secondTail, n);
////                scanner.nextChar();
////            }
//            if (firstFront == firstTail + 1) {
//                System.out.println("second " + steps);
//                return;
//            } else if (secondFront == secondTail + 1) {
//                System.out.println("first " + steps);
//                return;
//            }
//            if (firstCards[firstFront % n] > secondCards[secondFront % n] && secondCards[secondFront % n] != 0) {
//                //first win this round
//                firstTail = moveTail(firstTail, n);
//                firstCards[firstTail % n] = firstCards[firstFront % n];
//                firstTail = moveTail(firstTail, n);
//                firstCards[firstTail % n] = secondCards[secondFront % n];
//                firstFront = moveFront(firstFront, n);
//                secondFront = moveFront(secondFront, n);
//            } else if(firstCards[firstFront % n] == 0) {
//                //first win this round
//                firstTail = moveTail(firstTail, n);
//                firstCards[firstTail % n] = firstCards[firstFront % n];
//                firstTail = moveTail(firstTail, n);
//                firstCards[firstTail % n] = secondCards[secondFront % n];
//                firstFront = moveFront(firstFront, n);
//                secondFront = moveFront(secondFront, n);
//            }  else {
//                //second win this round
//                secondTail = moveTail(secondTail, n);
//                secondCards[secondTail % n] = firstCards[firstFront % n];
//                secondTail = moveTail(secondTail, n);
//                secondCards[secondTail % n] = secondCards[secondFront % n];
//                firstFront = moveFront(firstFront, n);
//                secondFront = moveFront(secondFront, n);
//            }
//            steps++;
//        }
//        System.out.println("draw");
        Drunker play = new Drunker(n, firstCards, secondCards, maxSteps);
        System.out.println(play.play());
    }

    public static int moveTail(int tail, int n) {
//        if (tail == n - 1) {
//            tail = 0;
//        } else {
//            tail = tail + 1;
//        }
        return tail + 1;
    }

    public static void trace(int[] firstCards, int[] secondCards, int firstFront, int secondFront,
                             int firstTail, int secondTail, int n) {
        printArray(firstCards, firstFront, firstTail, n);
        printArray(secondCards, secondFront, secondTail, n);
        System.out.println("");
    }

    public static void printArray(int[] array, int front, int tail, int n) {
        while(front <= tail) {
            System.out.print(array[front % n]);
            front = moveFront(front, n);
        }
//        System.out.print(array[tail % n]);
        System.out.println();
    }

    public static int moveFront(int front, int n) {
        return moveTail(front, n);
    }
}

class Drunker {

    private LinkedList<Integer> firstCards;
    private LinkedList<Integer> secondCards;
    int steps;
    boolean traceEnabled = true;

    public Drunker(int n, int[] firstCards, int[] secondCards, int steps) {
        this.firstCards = new LinkedList<>();
        this.secondCards = new LinkedList<>();
        for (int i = 0; i < n/2; i++) {
            this.firstCards.add(firstCards[i]);
            this.secondCards.add(secondCards[i]);
        }
        this.steps = steps;
    }

    public String play() throws IOException {
        int stepsCount = 0;
        int currentFirst;
        int currentSecond;
        Forth.FastScanner scanner = new Forth.FastScanner(System.in);
        while (!firstCards.isEmpty()
                && !secondCards.isEmpty()
                && stepsCount < steps) {
//            if (traceEnabled) {
//                System.out.println(firstCards);
//                System.out.println(secondCards);
//                scanner.nextChar();
//            }
            currentFirst = firstCards.pop();
            currentSecond = secondCards.pop();
            if (currentFirst == 0) {
                firstCards.add(currentFirst);
                firstCards.add(currentSecond);
            } else if (currentSecond == 0) {
                secondCards.add(currentFirst);
                secondCards.add(currentSecond);
            } else if (currentFirst > currentSecond) {
                firstCards.add(currentFirst);
                firstCards.add(currentSecond);
            } else {
                secondCards.add(currentFirst);
                secondCards.add(currentSecond);
            }
            stepsCount++;
        }
        if (firstCards.isEmpty()) {
            return "second " + stepsCount;
        } else if (secondCards.isEmpty()) {
            return "first " + stepsCount;
        } else {
            return "draw";
        }
    }
}
