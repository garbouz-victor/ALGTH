package com.shad.algorithms.test2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

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

    static class Figure {
        int startX, startY, endX, endY;

        public Figure(int startX, int startY, int endX, int endY) {
            this.startX = startX;
            this.startY = startY;
            this.endX = endX;
            this.endY = endY;
        }
    }

    static class Pair<K extends Comparable<K>,V extends Comparable<V>> implements Comparable<Pair<K,V>>{
        K first;
        V second;

        public Pair(K first, V second) {
            this.first = first;
            this.second = second;
        }

        @Override
        public int compareTo(Pair<K,V> other) {
            if (this.first.compareTo(other.first) == 1) {
                return 1;
            } else if (this.first.compareTo(other.first) == 0) {
                return other.second.compareTo(other.second);
            } else {
                return -1;
            }
        }
    }

    static class Resolver {
        List<Figure> figures;
        int W, H, a, b, n;
        int maxA = 0, resultStartX, resultStartY;

        public Resolver(InputStream is) throws IOException {
            FastScanner scanner = new FastScanner(is);
            this.W = scanner.nextInt();
            this.H = scanner.nextInt();
            this.a = scanner.nextInt();
            this.b = scanner.nextInt();
            this.n = scanner.nextInt();
            figures = new ArrayList<>();
            for (int i = 0; i < n; i++) {
                figures.add(new Figure(scanner.nextInt(), scanner.nextInt(),
                        scanner.nextInt(), scanner.nextInt()));
            }
            //borders
            figures.add(new Figure(-1, -1, 0, H+1));
            figures.add(new Figure(-1, -1, W+1, 0));
            figures.add(new Figure(W, -1, W+1, H+1));
            figures.add(new Figure(-1, H, W+1, H+1));
        }

        public String getAnswer() {
            for (int i = 0; i < figures.size(); i++) {
                for (int j = i+1; j < figures.size(); j++) {
                    findPlace(i, j);
                }
            }
            return resultStartX + " " + resultStartY + " " + (resultStartX+maxA) + " " + (resultStartY+b);
        }

        private void findPlace(int figure1, int figure2) {
            Figure f1 = figures.get(figure1);
            Figure f2 = figures.get(figure2);
            int bottom = Math.min(f1.endY, f2.endY);
            int ceil = Math.max(f1.startY, f2.startY);
            if (ceil - bottom < b) {
                return;
            }
            List<Pair<Integer, Integer>> projections = new ArrayList<>();
            for (int i = 0; i < figures.size(); i++) {
                if (i != figure1 && i != figure2) {
                    //checking
                    Figure other = figures.get(i);
                    if (other.endY <= bottom || other.startY >= ceil) {
                        continue;
                    } else {
                        projections.add(new Pair<>(other.startX, 1));
                        projections.add(new Pair<>(other.endX, 0));
                    }
                }
            }
            Collections.sort(projections);
            int current = 0;
            for (int i = 0, last = -1; i < projections.size(); i++) {
                if (projections.get(i).second == 1) {
                    if (current == 0 && maxA < projections.get(i).first - last) {
                        maxA = projections.get(i).first - last;
                        resultStartX = last;
                        resultStartY = bottom;
                    }
                    current++;
                } else {
                    current--;
                }
                last = projections.get(i).first;
            }
        }
    }

    public static void main(String... args) throws IOException {
        Resolver res = new Resolver(System.in);
        System.out.println(res.getAnswer());
    }
}
