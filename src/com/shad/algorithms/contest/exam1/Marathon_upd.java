package com.shad.algorithms.contest.exam1;

import java.io.*;
import java.text.DecimalFormat;
import java.util.*;

public class Marathon_upd {
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

        private static class Pair implements Comparable<Solver.Pair>{
            int id, distance;

            public Pair(int id, int distance) {
                this.id = id;
                this.distance = distance;
            }

            @Override
            public int compareTo(Solver.Pair other) {
                if (this.distance > other.distance) {
                    return 1;
                } else if (this.distance == other.distance) {
                    if (this.id > other.id) {
                        return 1;
                    } else if (this.id == other.id) {
                        return 0;
                    } else {
                        return -1;
                    }
                } else {
                    return -1;
                }
            }
        }
        HashMap<Integer, Pair> runners;
        //distance+runners
        TreeSet<Pair> distances;
        public Solver(InputStream is) throws IOException {
            FastScanner scanner = new FastScanner(is);
            PrintWriter writer = new PrintWriter(System.out);
            int Q = scanner.nextInt();
            runners = new HashMap<>();
            distances = new TreeSet<>();
            DecimalFormat df = new DecimalFormat("#.######");
            //df.setRoundingMode(RoundingMode.CEILING);
            for (int i = 0; i < Q; i++) {
                String[] command = scanner.nextLine().split(" ");
                if ("RUN".equals(command[0])) {
                    Integer id = Integer.valueOf(command[1]);
                    Integer newDistance = Integer.valueOf(command[2]);
                    Pair newDistPair = new Pair(id, newDistance);
                    if (runners.containsKey(id)) {
                        Pair currentDistPair = runners.get(id);
                        distances.remove(currentDistPair);
                        distances.add(newDistPair);
                        runners.put(id, newDistPair);
                    } else {
                        runners.put(id, newDistPair);
                        distances.add(newDistPair);
                    }
                } else {
                    //CHEER
                    Integer id = Integer.valueOf(command[1]);
                    Pair currentDistancePair = runners.get(id);
                    if (currentDistancePair == null) {
                        writer.println(0);
                    } else if (runners.size() == 1) {
                        writer.println(1);
                    } else {
                        SortedSet<Pair> lowerKeys = distances.headSet(new Pair(Integer.MAX_VALUE, currentDistancePair.distance-1));
                        double lowerCount = lowerKeys.size();
                        int runnersCount = runners.size()-1;
                        double portion = lowerCount/runnersCount;
                        writer.println(portion);
                    }
                }
            }
            writer.close();
        }
    }

    public static void main(String... args) throws IOException {
        Solver s = new Solver(System.in);
    }
}
