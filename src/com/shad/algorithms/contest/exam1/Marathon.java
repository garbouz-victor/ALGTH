package com.shad.algorithms.contest.exam1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.*;

public class Marathon {
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

        private static class Pair implements Comparable<Pair>{
            int id, distance;

            public Pair(int id, int distance) {
                this.id = id;
                this.distance = distance;
            }

            @Override
            public int compareTo(Pair other) {
                if (this.distance > other.distance) {
                    return 1;
                } else if (this.distance == other.distance) {
                    if (this.id > other.id) {
                        return 1;
                    } else {
                        return -1;
                    }
                } else {
                    return -1;
                }
            }
        }
        HashMap<Integer, Integer> runners;
        //distance+runners
        TreeMap<Integer, Set<Integer>> distances;
        public Solver(InputStream is) throws IOException {
            FastScanner scanner = new FastScanner(is);
            StringBuilder builder = new StringBuilder();
            int Q = scanner.nextInt();
            runners = new HashMap<>();
            distances = new TreeMap<>();
            DecimalFormat df = new DecimalFormat("#.######");
            //df.setRoundingMode(RoundingMode.CEILING);
            for (int i = 0; i < Q; i++) {
                String[] command = scanner.nextLine().split(" ");
                if ("RUN".equals(command[0])) {
                    Integer id = Integer.valueOf(command[1]);
                    Integer newDistance = Integer.valueOf(command[2]);
                    if (runners.containsKey(id)) {
                        Integer currentDist = runners.get(id);
                        distances.get(currentDist).remove(id);
                        if (distances.get(currentDist).isEmpty()) {
                            distances.remove(currentDist);
                        }
                        if (!distances.containsKey(newDistance)) {
                            distances.put(newDistance, new TreeSet<>());
                        }
                        distances.get(newDistance).add(id);
                        runners.put(id, newDistance);
                    } else {
                        runners.put(id, newDistance);
                        if (!distances.containsKey(newDistance)) {
                            distances.put(newDistance, new TreeSet<>());
                        }
                        distances.get(newDistance).add(id);
                    }
                } else {
                    //CHEER
                    Integer id = Integer.valueOf(command[1]);
                    Integer currentDistance = runners.get(id);
                    if (currentDistance == null) {
                        builder.append(0).append("\n");
                    } else if (runners.size() == 1) {
                        builder.append(1).append("\n");
                    } else {
                        Integer lowerKey = distances.lowerKey(currentDistance);
                        double lowerCount = 0.0;
                        while (lowerKey != null) {
                            lowerCount += distances.get(lowerKey).size();
                            lowerKey = distances.lowerKey(lowerKey);
                        }
                        int runnersCount = runners.size()-1;
                        double portion = lowerCount/runnersCount;
                        builder.append(df.format(portion)).append("\n");
                    }
                }
            }
            if (builder.length() > 0) {
                builder.deleteCharAt(builder.length()-1);
            }
            System.out.println(builder.toString());
        }
    }

    public static void main(String... args) throws IOException {
        Solver s = new Solver(System.in);
    }
}
