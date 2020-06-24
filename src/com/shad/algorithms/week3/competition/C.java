package com.shad.algorithms.week3.competition;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Collections;
import java.util.StringTokenizer;

public class C {
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

    static class Team implements Comparable<Team>{
        int i;
        int solved;
        int time;

        public Team(int i, int solved, int time) {
            this.i = i;
            this.solved = solved;
            this.time = time;
        }

        @Override
        public int compareTo(Team other) {
            if (this.solved > other.solved) {
                return 1;
            } else if (this.solved == other.solved) {
                if (this.time < other.time) {
                    return 1;
                } else if (this.time == other.time) {
                    if (this.i < other.i) {
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
    }

    public static void main(String... args) throws IOException {
        FastScanner scanner = new FastScanner(System.in);
        int n = scanner.nextInt();
        Team[] teams = new Team[n];
        for (int i = 0; i < n; i++) {
            teams[i] = new Team(i+1, scanner.nextInt(), scanner.nextInt());
        }
        Arrays.sort(teams, Collections.reverseOrder());
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < n; i++) {
            builder.append(teams[i].i).append(" ");
        }
        builder.deleteCharAt(builder.length() - 1);
        System.out.println(builder.toString());
    }
}
