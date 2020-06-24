package com.shad.algorithms.contest.exam1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class First {
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
        Pattern pattern = Pattern.compile("(\\w+,\\s*\\w+)(\\sor)(\\s\\w+)");
        Matcher matcher = pattern.matcher(line);
        while (matcher.find()) {
            String first = matcher.group(1);
            String second = matcher.group(2);
            String third = matcher.group(3);
            line = line.replaceFirst(first+second+third, first + "," + second + third);
        }
        pattern = Pattern.compile("(\\w+,\\s*\\w+)(\\sand)(\\s\\w+)");
        matcher = pattern.matcher(line);
        while (matcher.find()) {
            String first = matcher.group(1);
            String second = matcher.group(2);
            String third = matcher.group(3);
            line = line.replaceFirst(first+second+third, first + "," + second + third);
        }
        pattern = Pattern.compile("(\\w+,\\s*\\w+)(\\snor)(\\s\\w+)");
        matcher = pattern.matcher(line);
        while (matcher.find()) {
            String first = matcher.group(1);
            String second = matcher.group(2);
            String third = matcher.group(3);
            line = line.replaceFirst(first+second+third, first + "," + second + third);
        }
        System.out.println(line);
    }
}
