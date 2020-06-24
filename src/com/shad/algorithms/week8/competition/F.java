package com.shad.algorithms.week8.competition;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.TreeMap;
import java.util.TreeSet;

public class F {
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
    private static class Solver {
        private static class Rectangle {
            int xl, yl, xr, yr;
            public Rectangle(int xl, int yl, int xr, int yr) {
                this.xl = xl;
                this.yl = yl;
                this.xr = xr;
                this.yr = yr;
            }
        }
        private static class Event implements Comparable<Event>{
            int code;
            Rectangle rectangle;
            public Event(int code, Rectangle rectangle) {
                this.code = code;
                this.rectangle = rectangle;
            }

            @Override
            public int compareTo(Event other) {
                if (this.code > other.code) {
                    return -1;
                } else {
                    return 1;
                }
            }
        }
        int n;
        Rectangle[] rectangles;
        public Solver(InputStream is) throws IOException {
            FastScanner scanner = new FastScanner(is);
            n = scanner.nextInt();
            rectangles = new Rectangle[n];
            for (int i = 0; i < n; i++) {
                rectangles[i] = new Rectangle(
                        scanner.nextInt(),
                        scanner.nextInt(),
                        scanner.nextInt(),
                        scanner.nextInt());
            }
        }

        public String solve() {
            TreeMap<Integer, TreeSet<Event>> xEvents = new TreeMap<>();
            for (Rectangle r : rectangles) {
                if (!xEvents.containsKey(r.xl)) {
                    xEvents.put(r.xl, new TreeSet<>());
                }
                xEvents.get(r.xl).add(new Event(1, r));
                if (!xEvents.containsKey(r.xr)) {
                    xEvents.put(r.xr, new TreeSet<>());
                }
                xEvents.get(r.xr).add(new Event(0, r));
            }
            TreeMap<Integer, Integer> front = new TreeMap<>();
            int max = 1;
            int xMax = rectangles[0].xl,yMax = rectangles[0].yl;
            while (!xEvents.isEmpty()) {
                Map.Entry<Integer, TreeSet<Event>> next = xEvents.pollFirstEntry();
                for (Event event: next.getValue()) {
                    if (event.code == 1) {
                        Rectangle r = event.rectangle;
                        if (front.containsKey(r.yl)) {
                            int currentValue = front.get(r.yl);
                            front.put(r.yl, currentValue+1);
                        } else {
                            front.put(r.yl, 1);
                        }
                        if (front.containsKey(r.yr+1)) {
                            int currentValue = front.get(r.yr+1);
                            front.put(r.yr+1, currentValue-1);
                        } else {
                            front.put(r.yr+1, -1);
                        }
                        int counter = 0;
                        for (Integer y : front.keySet()) {
                            counter += front.get(y);
                            if (max < counter) {
                                max = counter;
                                xMax = next.getKey();
                                yMax = y;
                            }
                        }
                    } else {
                        Rectangle r = event.rectangle;
                        if (front.containsKey(r.yl)) {
                            int currentValue = front.get(r.yl);
                            if (currentValue == 1) {
                                front.remove(r.yl);
                            } else {
                                front.put(r.yl, currentValue-1);
                            }
                        }
                        if (front.containsKey(r.yr+1)) {
                            int currentValue = front.get(r.yr+1);
                            if (currentValue == -1) {
                                front.remove(r.yr+1);
                            } else {
                                front.put(r.yr+1, currentValue+1);
                            }
                        }
                    }
                }
            }
            return max + "\n" + xMax + " " + yMax;
        }
    }

    public static void main(String... args) throws IOException {
        Solver s = new Solver(System.in);
        System.out.println(s.solve());
    }
}
