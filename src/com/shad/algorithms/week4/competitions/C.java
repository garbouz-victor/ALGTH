package com.shad.algorithms.week4.competitions;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
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

    public static void main(String... args) throws IOException {
        FastScanner scanner = new FastScanner(System.in);
        int n = scanner.nextInt();
        int m = scanner.nextInt();
        int[][] table = new int[n][];
        int[][] penalties = new int[n][];
        for (int i = 0; i < n; i++) {
            table[i] = new int[m];
            penalties[i] = new int[m];
            for (int j = 0; j < m; j++) {
                table[i][j] = scanner.nextInt();
                penalties[i][j] = 300;
            }
        }
//        updatePenalties(table, penalties, n, m, 0, 0);
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (i == 0 && j == 0) {
                    penalties[i][j] = table[i][j];
                } else if (i == 0) {
                    penalties[i][j] = penalties[i][j-1] + table[i][j];
                } else if (j == 0) {
                    penalties[i][j] = penalties[i-1][j] + table[i][j];
                } else {
                    penalties[i][j] = Math.min(penalties[i][j-1], penalties[i-1][j]) + table[i][j];
                }
            }
        }
        System.out.println(penalties[n-1][m-1]);
    }

    private static void updatePenalties(int[][] table, int[][] penalties, int n, int m, int i, int j) {
        if (i == n || j == m) {
            return;
        }
        if (i == 0 && j == 0) {
            penalties[i][j] = table[i][j];
            updatePenalties(table, penalties, n, m, i+1, j);
            updatePenalties(table, penalties, n, m, i, j+1);
        } else {
            int penalty = table[i][j];
            if (j == 0) {
                int result = penalties[i-1][j] + penalty;
                if (penalties[i][j] > result) {
                    penalties[i][j] = result;
                }
                updatePenalties(table, penalties, n, m, i+1, j);
                updatePenalties(table, penalties, n, m, i, j+1);
            } else if (j == m-1 && i != 0) {
                int result = Math.min(penalties[i-1][j], penalties[i][j-1]) + penalty;
                if (penalties[i][j] > result) {
                    penalties[i][j] = result;
                }
                updatePenalties(table, penalties, n, m, i+1, j);
            } else if (j == m-1 && i == 0) {
                int result = penalties[i][j-1] + penalty;
                if (penalties[i][j] > result) {
                    penalties[i][j] = result;
                }
                updatePenalties(table, penalties, n, m, i+1, j);
            } else if(i == n-1 && j != 0) {
                int result = Math.min(penalties[i-1][j], penalties[i][j-1]) + penalty;
                if (penalties[i][j] > result) {
                    penalties[i][j] = result;
                }
                updatePenalties(table, penalties, n, m, i, j+1);
            } else if(i == n-1 && j == 0) {
                int result = penalties[i-1][j] + penalty;
                if (penalties[i][j] > result) {
                    penalties[i][j] = result;
                }
                updatePenalties(table, penalties, n, m, i, j+1);
            } else if(i == 0) {
                int result = penalties[i][j-1] + penalty;
                if (penalties[i][j] > result) {
                    penalties[i][j] = result;
                }
                updatePenalties(table, penalties, n, m, i+1, j);
                updatePenalties(table, penalties, n, m, i, j+1);
            } else {
                int result = Math.min(penalties[i-1][j], penalties[i][j-1]) + penalty;
                if (penalties[i][j] > result) {
                    penalties[i][j] = result;
                }
                updatePenalties(table, penalties, n, m, i+1, j);
                updatePenalties(table, penalties, n, m, i, j+1);
            }
        }
    }
}
