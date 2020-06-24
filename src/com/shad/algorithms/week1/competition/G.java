package com.shad.algorithms.week1.competition;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.LinkedList;
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

    public static void main(String... args) throws IOException {
        FastScanner scanner = new FastScanner(System.in);
        String line = scanner.nextLine();
        LinkedList<Integer> stack = new LinkedList<>();
        String[] tokens = line.split(" ");
        int temp;
        for (String token : tokens) {
             if (token.equals("*")) {
                temp = stack.pop();
                stack.push(stack.pop() * temp);
            } else if (token.equals("+")) {
                temp = stack.pop();
                stack.push(stack.pop() + temp);
            } else if (token.equals("-")) {
                temp = stack.pop();
                stack.push(stack.pop() - temp);
            } else {
                stack.push(Integer.parseInt(token));
            }
        }
        System.out.println(stack.pop());
    }
}
