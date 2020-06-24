package com.shad.algorithms.week1.competition;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.StringTokenizer;

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
        FastScanner scanner = new FastScanner(System.in);
        int n = scanner.nextInt();
        int maxOperationSteps = 200000;
        LinkedList<Integer> firstCards = new LinkedList<>();
        LinkedList<Integer> secondCards = new LinkedList<>();
        for (int i = 0; i < n/2; i++) {
            firstCards.add(scanner.nextInt());
        }
        for (int i = 0; i < n/2; i++) {
            secondCards.add(scanner.nextInt());
        }
        int firstCard;
        int secondCard;
        int i;
        for (i = 0; i < maxOperationSteps; i++) {
            if (firstCards.isEmpty()) {
                System.out.println("second " + i);
                return;
            } else if (secondCards.isEmpty()) {
                System.out.println("first " + i);
                return;
            } else {
                firstCard = firstCards.poll();
                secondCard = secondCards.poll();
//                System.out.println(String.format("first card = %s second card %s", firstCard, secondCard));
//                System.out.println(firstCards);
//                System.out.println(secondCards);
                if (firstCard == 0 && secondCard == n-1) {
                    //first win this round
                    firstCards.add(firstCard);
                    firstCards.add(secondCard);
                } else if (secondCard == 0 && firstCard == n-1) {
                    //second win this round
                    secondCards.add(firstCard);
                    secondCards.add(secondCard);
                } else if (firstCard > secondCard) {
                    //first win this round
                    firstCards.add(firstCard);
                    firstCards.add(secondCard);
                } else {
                    //second win this round
                    secondCards.add(firstCard);
                    secondCards.add(secondCard);
                }
            }
        }
        if (firstCards.isEmpty()) {
            System.out.println("second " + i);
            return;
        } else if (secondCards.isEmpty()) {
            System.out.println("first " + i);
            return;
        }
        System.out.println("draw");
    }
}
