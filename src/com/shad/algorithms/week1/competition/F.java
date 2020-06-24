package com.shad.algorithms.week1.competition;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class F {
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
        HairDresser dresser = new HairDresser();
        int currentClientHours;
        int currentClientMinutes;
        int currentClientRating;
        Time exitTime;
        for (int i = 0; i < n; i++) {
            currentClientHours = scanner.nextInt();
            currentClientMinutes = scanner.nextInt();
            currentClientRating = scanner.nextInt();
            exitTime = dresser.process(currentClientHours, currentClientMinutes, currentClientRating);
            System.out.println(exitTime.hours + " " + exitTime.minutes);
        }
    }

    static class Time {

        int hours;
        int minutes;

        public Time(int hours, int minutes) {
            this.hours = hours;
            this.minutes = minutes;
        }

        public void updateMinutes(int minutes) {
            if (this.minutes + minutes >= 60) {
                this.hours++;
            }
            this.minutes = (this.minutes + minutes) % 60;
        }

        public Time addMinutes(int minutes) {
            Time newTime = new Time(this.hours, this.minutes);
            newTime.updateMinutes(minutes);
            return newTime;
        }

        int compareOf(Time t) {
            if (this.hours > t.hours) {
                return 1;
            } else if (this.hours == t.hours && this.minutes > t.minutes) {
                return 1;
            } else if (this.hours == t.hours && this.minutes == t.minutes) {
                return 0;
            } else {
                return -1;
            }
        }

        int delta(Time t) {
            return (this.hours - t.hours)* 60 + (this.minutes - t.minutes);
        }
    }

    static class HairDresser {
        LinkedList<Time> clients = new LinkedList<>();
        Time timer;
        int workingSlot = 20;
        public Time process(int hours, int minutes, int rating) {
            Time startTime = new Time(hours, minutes);
            while (!clients.isEmpty() && startTime.delta(clients.getFirst()) >= 0) {
                clients.poll();
            }
            if (clients.isEmpty()) {
                Time endTime = startTime;
                endTime.updateMinutes(workingSlot);
                clients.add(endTime);
                return endTime;
            } else {
                if (clients.size() > rating) {
                    return startTime;
                } else {
                    Time lastClientTime = clients.getLast();
                    clients.add(lastClientTime.addMinutes(workingSlot));
                    return clients.getLast();
                }
            }
        }
    }
}
