package com.shad.algorithms.week8.competition;

import java.io.*;
import java.util.StringTokenizer;

public class E {
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
        int n,m;
        int[] a, segments;
        public Solver(InputStream is) throws IOException {
            FastScanner scanner = new FastScanner(is);
            n = scanner.nextInt();
            a = new int[n+1];
            segments = new int[4*n+1];
            for (int i = 1; i <= n; i++) {
                a[i] = scanner.nextInt();
            }
            build(1, 1, n);
            PrintWriter writer = new PrintWriter(System.out);
            m = scanner.nextInt();
            for (int i = 0; i < m; i++) {
                int code = scanner.nextInt();
                int l = scanner.nextInt();
                int r = scanner.nextInt();
                if (code == 0) {
                    setPosition(l, r, 1, 1, n);
                } else {
                    writer.println(getSum(l, r, 1, 1, n));
                }
            }
            writer.flush();
            writer.close();
//            while (true) {
//                int l = scanner.nextInt();
//                int r = scanner.nextInt();
//                writer.write(getSum(l, r, 1, 1, n)+"\n");
//                writer.flush();
//            }
        }

        private void build(int v, int sl, int sr) {
            if (sl == sr) {
                if (isEven(sl)) {
                    segments[v] = -a[sl];
                } else {
                    segments[v] = a[sl];
                }
                return;
            }
            int m = (sl+sr)/2;
            build(v*2, sl, m);
            build(v*2+1, m+1, sr);
            segments[v] = segments[v*2] + segments[v*2+1];
        }

        private int getSum(int ql, int qr, int v, int sl, int sr) {
            if (ql <= sl && sr <= qr) {
                if (isEven(ql)) {
                    return -segments[v];
                } else {
                    return segments[v];
                }
            } else if (sr < ql || qr < sl) {
                return Integer.MAX_VALUE;
            } else {
                int m = (sl+sr)/2;
                int ans1 = getSum(ql, qr, v*2, sl, m);
                int ans2 = getSum(ql, qr, v*2+1, m+1, sr);
                if (ans1 == Integer.MAX_VALUE) {
                    return ans2;
                }
                if (ans2 == Integer.MAX_VALUE) {
                    return ans1;
                }
                return ans1+ans2;
            }
        }

        private void setPosition(int i, int x, int v, int sl, int sr) {
            if (sl == sr) {
                if (isEven(i)) {
                    segments[v] = -x;
                } else {
                    segments[v] = x;
                }
                return;
            }
            int m = (sl+sr)/2;
            if (i <= m) {
                setPosition(i, x, v*2, sl, m);
            } else {
                setPosition(i, x, v*2+1, m+1, sr);
            }
            segments[v] = segments[v*2] + segments[v*2+1];
        }

        private boolean isEven(int val) {
            return val%2 == 0;
        }
    }

    public static void main(String... args) throws IOException {
        Solver s = new Solver(System.in);
    }
}
