package com.shad.algorithms.week8.competition;

import java.io.*;
import java.util.Scanner;
import java.util.StringTokenizer;

public class A {
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
    private static class RSQ {
        int[] a; long[] segments;
        int n;
        public RSQ(InputStream is) throws IOException {
            Scanner scanner = new Scanner(is);
            PrintWriter writer = new PrintWriter(System.out);
            n = scanner.nextInt();
            a = new int[n+1];
            segments = new long[4*n+1];
            for (int i = 1; i <= n; i++) {
                a[i] = scanner.nextInt();
            }
            build(1, 1, n);
            scanner.nextLine();
            while (scanner.hasNextLine()) {
                String[] commands = scanner.nextLine().split(" ");
                int val1 = Integer.valueOf(commands[1]);
                int val2 = Integer.valueOf(commands[2]);
                if ("sum".equals(commands[0])) {
                    writer.println(getSum(val1, val2, 1, 1, n));
                    writer.flush();
                } else {
                    setPos(val1, val2, 1,1, n);
                }
            }
            writer.flush();
            writer.close();
        }

        public long getSum(int ql, int qr, int v, int sl, int sr) {
            if (ql <= sl && sr <= qr) {
                return segments[v];
            } else if (qr < sl || sr < ql) {
                return Long.MAX_VALUE;
            }  else if (sl == sr) {
               return segments[v];
            } else {
                int m = (sl + sr)/2;
                long ans1 = getSum(ql, qr, v*2, sl, m);
                long ans2 = getSum(ql, qr, v*2+1, m+1, sr);
                if (ans1 == Long.MAX_VALUE) {
                    return ans2;
                }
                if (ans2 == Long.MAX_VALUE) {
                    return ans1;
                }
                return ans1 + ans2;
            }
        }

        public void setPos(int i, int x, int v, int sl, int sr) {
            if (sl == sr) {
                segments[v] = x;
                return;
            }
            int m = (sl + sr)/2;
            if (i <= m) {
                setPos(i, x, v*2, sl, m);
            } else {
                setPos(i, x, v*2+1, m+1, sr);
            }
            segments[v] = segments[v*2]+segments[v*2+1];
        }

        private void build(int v, int sl, int sr) {
            if (sl == sr) {
                segments[v] = a[sl];
                return;
            }
            int m = (sl + sr) / 2;
            build(v*2, sl, m);
            build(v*2 + 1, m+1, sr);
            segments[v] = segments[v*2] + segments[v*2+1];
        }
    }

    public static void main(String... args) throws IOException {
        RSQ s = new RSQ(System.in);
    }
}
