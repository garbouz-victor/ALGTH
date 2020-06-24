package com.shad.algorithms.week2.competition;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Objects;
import java.util.StringTokenizer;
import java.util.TreeSet;

public class B {
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

    static class MyValue implements Comparable<MyValue>
    {
        int val;

        public MyValue(int val) {
            this.val = val;
        }

        @Override
        public int compareTo(MyValue other) {
            if (val > other.val) {
                return 1;
            } else if (val <= other.val) {
                return -1;
            } else {
                return 0;
            }
        }

        public int getVal() {
            return val;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            MyValue myValue = (MyValue) o;
            return val == myValue.val;
        }

        @Override
        public int hashCode() {
            return Objects.hash(val);
        }
    }

    static class BProcessor {

        String answer;

        public BProcessor(InputStream is) throws IOException {
            FastScanner scanner = new FastScanner(is);
            TreeSet<MyValue> arraySet = new TreeSet<>();
            int n = scanner.nextInt();
            for (int i = 0; i < n; i++) {
                arraySet.add(new MyValue(scanner.nextInt()));
            }
            int k = scanner.nextInt();
            MyValue l,r;
            StringBuilder builder = new StringBuilder();
            for (int i = 0; i < k; i++) {
                l = new MyValue(scanner.nextInt());
                r = new MyValue(scanner.nextInt() + 1);
                builder.append(arraySet.subSet(l,r).size()).append(" ");
            }
            builder.deleteCharAt(builder.length() - 1);
            this.answer = builder.toString();
        }

        public String getAnswer() {
            return this.answer;
        }
    }

    public static void main(String... args) throws IOException {
        FastScanner scanner = new FastScanner(System.in);
        TreeSet<MyValue> arraySet = new TreeSet<>();
        int n = scanner.nextInt();
        for (int i = 0; i < n; i++) {
            arraySet.add(new MyValue(scanner.nextInt()));
        }
        int k = scanner.nextInt();
        MyValue l,r;
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < k; i++) {
            l = new MyValue(scanner.nextInt());
            r = new MyValue(scanner.nextInt() + 1);
            builder.append(arraySet.subSet(l,r).size()).append(" ");
        }
        builder.deleteCharAt(builder.length() - 1);
        System.out.println(builder.toString());
    }
}
