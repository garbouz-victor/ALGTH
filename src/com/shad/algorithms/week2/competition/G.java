package com.shad.algorithms.week2.competition;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.*;

public class G {
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

    static class Jewel implements Comparable<Jewel>{
        int i,v,w;
        int count;
        HashSet<Integer> same;

        public Jewel(int i, int v, int w) {
            this.i = i;
            this.v = v;
            this.w = w;
            this.count = 1;
        }


        @Override
        public int compareTo(Jewel other) {
            if (this.v - this.w > other.v - other.w) {
                return 1;
            } else if (this.v - this.w == other.v - other.w) {
                if (this.v > other.v) {
                    return 1;
                } else if (this.v == other.v) {
                    return 0;
                } else {
                    return -1;
                }
            } else {
                return -1;
            }
        }

        public void addOneMore(Jewel other) {
            if (this.same == null) {
                this.same = new HashSet<>();
            }
            same.add(other.i);
            this.count++;
        }

        public int removeOne() {
            int iForRemove = this.same.iterator().next();
            this.same.remove(iForRemove);
            this.count--;
            return iForRemove;
        }
    }

    public static void main(String... args) throws IOException {
        FastScanner scanner = new FastScanner(System.in);
        int n = scanner.nextInt();
        int k = scanner.nextInt();
        HashMap<Integer, TreeMap<Integer, Jewel>> jewels = new HashMap<>();
        for (int i = 1; i <= n; i++) {
            Jewel j = new Jewel(i, scanner.nextInt(), scanner.nextInt());
            if (jewels.containsKey(j.v)) {
                TreeMap<Integer, Jewel> map = jewels.get(j.v);
                if (map.containsKey(j.w)) {
                    Jewel sameJ = map.get(j.w);
                    sameJ.addOneMore(j);
                } else {
                    map.put(j.w, j);
                }
            } else {
                TreeMap<Integer, Jewel> map = new TreeMap<>();
                map.put(j.w, j);
                jewels.put(j.v, map);
            }
        }
        StringBuilder builder = new StringBuilder();
        double max, currentVal;
//        LinkedList<Jewel> savedValues = new LinkedList<>();
        int savedV = 0, savedW = 0;
        for (int i = 0; i < k; i++) {
            Jewel favorite = null;
            max = Integer.MIN_VALUE;
            for (Integer valueKey : jewels.keySet()) {
                TreeMap<Integer, Jewel> map = jewels.get(valueKey);
                if (map.isEmpty()) {
                    continue;
                }
                Jewel test = map.get(map.firstKey());
                currentVal = calcValue(savedV, savedW, test);
                if (max < currentVal) {
                    max = currentVal;
                    favorite = test;
                }
            }
            if (favorite.count > 1) {
                builder.append(favorite.removeOne()).append("\n");
            } else {
                builder.append(favorite.i).append("\n");
                jewels.get(favorite.v).remove(favorite.w);
            }
            savedV += favorite.v;
            savedW += favorite.w;
        }
        System.out.println(builder.toString());
    }

    public static double calcValue(int savedV, int savedW, Jewel test) {
        return (double)(savedV + test.v)/(savedW + test.w);
    }
}
