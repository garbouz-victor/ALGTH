package com.shad.algorithms.week2.competition;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.StringTokenizer;
import java.util.TreeSet;

public class G_upd {
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
                    if (this.i > other.i) {
                        return 1;
                    } else {
                        return -1;
                    }
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
        TreeSet<Jewel> jewels = new TreeSet<>();
        for (int i = 1; i <= n; i++) {
            jewels.add(new Jewel(i, scanner.nextInt(), scanner.nextInt()));
        }
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < k; i++) {
            Jewel j = jewels.pollLast();
            builder.append(j.i).append("\n");
        }
        System.out.println(builder.toString());
    }
}
