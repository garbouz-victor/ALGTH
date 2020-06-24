package com.shad.algorithms.week8.competition;

import java.io.*;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

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

    private static class Solver{
        int n,q;
        int[] a;
        Pair[] segments;
        private Set<Integer> join;
        private static class Pair {
            int count;
            Set<Integer> vals;
            public Pair(int count, Set<Integer> vals) {
                this.count = count;
                this.vals = vals;
            }
        }
        public Solver(InputStream is) throws IOException {
            FastScanner scanner = new FastScanner(is);
            n = scanner.nextInt();
            a = new int[n+1];
            for (int i = 1; i <= n; i++) {
                a[i] = scanner.nextInt();
            }
            segments = new Pair[4*n+1];
            build(1, 1, n);
            q = scanner.nextInt();
            PrintWriter writer = new PrintWriter(System.out);
            join = new HashSet<>();
            for (int i = 0; i < q; i++) {
                int l = scanner.nextInt();
                int r = scanner.nextInt();
                Pair answer = getVals(l, r, 1, 1, n);
                writer.println(answer.count);
                join.clear();
            }
            writer.close();
        }

        private void build(int v, int sl, int sr) {
            if (sl == sr) {
                Set<Integer> ans = new HashSet<>();
                ans.add(a[sl]);
                segments[v] = new Pair(1, ans);
                return;
            } else {
                int m = (sl+sr)/2;
                build(v*2, sl, m);
                build(v*2+1, m+1, sr);
                Set<Integer> join =  new HashSet<>();
                join.addAll(segments[v*2].vals);
                join.addAll(segments[v*2+1].vals);
                segments[v] = new Pair(join.size(), join);
            }
        }

        private Pair getVals(int ql, int qr, int v, int sl, int sr) {
            if (ql<=sl && sr<=qr) {
                return segments[v];
            } else if (qr<sl || sr<ql) {
                return null;
            } else {
                int m = (sl+sr)/2;
                Pair ans1 = getVals(ql, qr, v*2, sl, m);
                Pair ans2 = getVals(ql, qr, v*2+1, m+1, sr);
                if (ans1 != null) {
                    join.addAll(ans1.vals);
                }
                if (ans2 != null) {
                    join.addAll(ans2.vals);
                }
                return new Pair(join.size(), join);
            }
        }
    }

    public static void main(String... args) throws IOException {
        Solver s = new Solver(System.in);
    }
}
