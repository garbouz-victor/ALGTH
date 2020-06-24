package com.shad.algorithms.week8.competition;

import java.io.*;
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

    private static class Crypto {
        private static class Matrx2x2 {
            int a,b,c,d;
            public Matrx2x2(int a, int b, int c, int d) {
                this.a = a;
                this.b = b;
                this.c = c;
                this.d = d;
            }
            public static Matrx2x2 multiply(Matrx2x2 A, Matrx2x2 B, int charR) {
                return new Matrx2x2(
                        ((A.a * B.a) % charR + (A.b * B.c) % charR) % charR,
                        ((A.a * B.b) % charR + (A.b * B.d) % charR) % charR,
                        ((A.c * B.a) % charR + (A.d * B.c) % charR) % charR,
                        ((A.c * B.b) % charR + (A.d * B.d) % charR) % charR);
            }

            @Override
            public String toString() {
                return a + " " + b + "\n" + c + " " + d + "\n";
            }
        }
        int r,n,m;
        Matrx2x2[] A;
        Matrx2x2[] segments;
        public Crypto(InputStream is, OutputStream out) throws IOException {
            FastScanner scanner = new FastScanner(is);
            PrintWriter writer = new PrintWriter(out);
            r = scanner.nextInt();
            n = scanner.nextInt();
            m = scanner.nextInt();
            A = new Matrx2x2[n+1];
            segments = new Matrx2x2[4*n+1];
            for (int i = 1; i <= n; i++) {
                A[i] = new Matrx2x2(scanner.nextInt(), scanner.nextInt(), scanner.nextInt(), scanner.nextInt());
                scanner.nextLine();
            }
            build(1, 1, n);
            for (int i = 0; i < m; i++) {
                int ql = scanner.nextInt();
                int qr = scanner.nextInt();
                writer.print(get(ql, qr, 1, 1, n));
                writer.println();
            }
            writer.flush();
            writer.close();
        }

        private void build(int v, int sl, int sr) {
            if (sl == sr) {
                segments[v] = A[sl];
                return;
            }
            int m = (sl + sr)/2;
            build(v*2, sl, m);
            build(v*2+1, m+1, sr);
            segments[v] = Matrx2x2.multiply(segments[v*2], segments[v*2+1], r);
        }

        private Matrx2x2 get(int ql, int qr, int v, int sl, int sr) {
            if (ql <= sl && sr <= qr) {
                return segments[v];
            }
            if (sr < ql || qr < sl) {
                return null;
            }
            int m = (sl + sr)/2;
            Matrx2x2 ans1 = get(ql, qr, v*2, sl, m);
            Matrx2x2 ans2 = get(ql, qr, v*2+1, m+1, sr);
            if (ans1 == null) {
                return ans2;
            }
            if (ans2 == null) {
                return ans1;
            }
            return Matrx2x2.multiply(ans1, ans2, r);
        }
    }
    public static void main(String... args) throws IOException {
        Crypto c = new Crypto(
                new FileInputStream(new File("crypto.in")),
                new FileOutputStream(new File("crypto.out")));
    }
}
