package com.shad.algorithms.test53;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.*;

public class L {
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

    private static class Pair<K extends Comparable<K>, V extends Comparable<V>> implements Comparable<Pair<K,V>>{

        K first; V second;

        public Pair(K first, V second) {
            this.first = first;
            this.second = second;
        }

        @Override
        public int compareTo(Pair<K, V> other) {
            if (this.first.compareTo(other.first) == 1) {
                return 1;
            } else if (this.first.compareTo(other.first) == 0) {
                return this.second.compareTo(other.second);
            } else {
                return -1;
            }
        }
    }

    private static class Figure {
        int startX, startY, endX, endY;

        public Figure(int startX, int startY, int endX, int endY) {
            this.startX = startX;
            this.startY = startY;
            this.endX = endX;
            this.endY = endY;
        }

        public boolean isInside(int left, int right) {
            if (this.endX <= left) {
                return false;
            } else if (this.startX >= right) {
                return false;
            } else {
                return true;
            }
        }
    }

    private static void check2Figures(int i, int j) {
        if (distanceBetween(figures[i], figures[j]) < a) {
            return;
        } else {
            //we need to check figures inside sector
            int left = getLeftSide(figures[i], figures[j]);
            int right = getRightSide(figures[i], figures[j]);
            TreeMap<Integer, Integer> figureInside = new TreeMap<>();
            for (int k = 0; k < figures.length; k++) {
                if (k == i || k == j) {
                    continue;
                }
                if (figures[k].isInside(left, right)) {
                    if (figureInside.containsKey(figures[k].startY)) {
                        int figureCount = figureInside.get(figures[k].startY);
                        figureCount++;
                        figureInside.put(figures[k].startY, figureCount);
                    } else {
                        figureInside.put(figures[k].startY, 1);
                    }
                    if (figureInside.containsKey(figures[k].endY)) {
                        int figureCount = figureInside.get(figures[k].endY);
                        figureCount--;
                        figureInside.put(figures[k].endY, figureCount);
                    } else {
                        figureInside.put(figures[k].endY, -1);
                    }
                }
            }
            int length = 0;
            int previousEnd = 0;
            int current = 0;
            int previous = 0;
            for (Integer y : figureInside.keySet()) {
                previous = current;
                current += figureInside.get(y);
                if (current != 0 && previous == 0) {
                    length = y - previousEnd;
                    if (maxB < length) {
                        maxB = length;
                        answerStartX = left;
                        answerEndX = right;
                        answerStartY = previousEnd;
                        answerEndY = answerStartY + maxB;
                    }
                } else if (current == 0) {
                    previousEnd = y;
                }
            }
        }
    }

    private static int distanceBetween(Figure first, Figure second) {
        int leftSide = getLeftSide(first, second);
        int rightSide = getRightSide(first, second);
        return rightSide - leftSide;
    }

    private static int getLeftSide(Figure first, Figure second) {
        return Math.min(first.endX, second.endX);
    }

    private static int getRightSide(Figure first, Figure second) {
        return Math.max(first.startX, second.startX);
    }

    private static int W,H,a,b,n;

    private static int maxB = 0, answerStartX, answerStartY, answerEndX, answerEndY;

    private static Figure[] figures;

    public static void main(String... args) throws IOException {
        FastScanner scanner = new FastScanner(System.in);
        W = scanner.nextInt();
        H = scanner.nextInt();
        a = scanner.nextInt();
        b = scanner.nextInt();
        n = scanner.nextInt();
        figures = new Figure[n+4];
        for (int i = 0; i < n; i++) {
            figures[i] = new Figure(scanner.nextInt(), scanner.nextInt(),
                    scanner.nextInt(), scanner.nextInt());
        }
        //bottom
        figures[n] = new Figure(-1, -1, W+1, 0);
        //left
        figures[n+1] = new Figure(-1, -1, 0, H+1);
        //right
        figures[n+2] = new Figure(W, -1, W+1, H+1);
        //up
        figures[n+3] = new Figure(-1, H, W+1, H+1);

        for (int i = 0; i < figures.length; i++) {
            for (int j = 0; j < figures.length; j++) {
                if (i != j) {
                    check2Figures(i, j);
                }
            }
        }

        System.out.println(answerStartX + " " + answerStartY + " " + answerEndX + " " + answerEndY);
    }
}
