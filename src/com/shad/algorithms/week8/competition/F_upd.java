package com.shad.algorithms.week8.competition;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.StringTokenizer;

public class F_upd {
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

    private static class Triple {
        int first, second, third;
        public Triple(int first, int second, int third) {
            this.first = first;
            this.second = second;
            this.third = third;
        }
    }

    private static class RMXQ {
        private int n;
        int[] a;
        int[] segments, p;
        int from, to;
        public RMXQ(int n){
            this.n = n;
            a = new int[n];
            segments = new int[4*n+1];
            p = new int[4*n+1];
            for (int i = 1; i <= 4*n; i++) {
                p[i] = Integer.MIN_VALUE;
            }
            this.from = 1;
        }

        public RMXQ(int from, int to) {
            this(to - from + 1);
            this.from = from;
            this.to = to;
        }
        
        public void push(int v) {
            if (p[v] == Integer.MIN_VALUE || v > 4*n) {
                return;
            }
            segments[v] = segments[v] + p[v];
            if (2*v <= 4*n) {
                if (p[v*2] == Integer.MIN_VALUE) {
                    p[v*2] = p[v];
                } else {
                    p[v*2] += p[v];
                }
            }
            if (2*v + 1 <= 4*n) {
                if (p[v*2+1] == Integer.MIN_VALUE) {
                    p[v*2+1] = p[v];
                } else {
                    p[v*2+1] += p[v];
                }
            }
            p[v] = Integer.MIN_VALUE;
        }

        public int getMax(int ql, int qr, int v, int sl, int sr) {
            push(v);
            if (ql <= sl && sr <= qr) {
                return segments[v];
            } else if (qr < sl || sr < ql) {
                return Integer.MIN_VALUE;
            }
            int m = (sl+sr)/2;
            int ans1 = getMax(ql, qr, v*2, sl, m);
            int ans2 = getMax(ql, qr, v*2+1, m+1, sr);
            return Math.max(ans1, ans2);
        }

        public int getMax(int l, int r) {
            int ql = l - from + 1;
            int qr = r - from + 1;
            return getMax(ql, qr, 1, 1, n);
        }

        public void add(int l, int r, int val) {
            int ql = l - from + 1;
            int qr = r - from + 1;
            add(ql, qr, 1, val, 1, n);
        }

        public void add(int ql, int qr, int v, int x,  int sl, int sr) {
            push(v);
            if (ql <= sl && sr <= qr) {
                p[v] = x;
                push(v);
                return;
            } else if (qr < sl || sr < ql) {
                return;
            }
            int m = (sl+sr)/2;
            add(ql, qr, v*2, x, sl, m);
            add(ql, qr, v*2+1, x, m+1, sr);
            segments[v] = Math.max(segments[v*2], segments[v*2+1]);
        }
    }

    private static class Solver {
        private static class Rectangle {
            int x1, y1, x2, y2;
            public Rectangle(int x1, int y1, int x2, int y2) {
                this.x1 = x1;
                this.y1 = y1;
                this.x2 = x2;
                this.y2 = y2;
            }
        }
        private static class Event implements Comparable<Event>{
            int code, x;
            Rectangle r;
            public Event(int code, int x, Rectangle r) {
                this.code = code;
                this.r = r;
                this.x = x;
            }

            @Override
            public int compareTo(Event other) {
                if (this.x > other.x) {
                    return 1;
                } else if (this.x == other.x) {
                    if (this.code > other.code) {
                        return -1;
                    } else if(this.code == other.code) {
                        return 0;
                    } else {
                        return 1;
                    }
                } else {
                    return -1;
                }
            }

        }

        private Event[] events;
        private Rectangle[] rectangles;
        int n;
        int y_min, y_max;
        public Solver(InputStream is) throws IOException {
            FastScanner scanner = new FastScanner(is);
            this.n = scanner.nextInt();
            y_min = Integer.MAX_VALUE;
            y_max = Integer.MIN_VALUE;
            rectangles = new Rectangle[n];
            events = new Event[2*n];
            int eventsCursor = 0;
            for (int i = 0; i < n; i++) {
                int x1 = scanner.nextInt();
                int y1 = scanner.nextInt();
                int x2 = scanner.nextInt();
                int y2 = scanner.nextInt();
                rectangles[i] = new Rectangle(x1, y1, x2, y2);
                events[eventsCursor] = new Event(1, x1, rectangles[i]);
                eventsCursor++;
                events[eventsCursor] = new Event(0, x2/*+1*/, rectangles[i]);
                eventsCursor++;
                if (y_min > Math.min(y1, y2)) {
                    y_min = Math.min(y1, y2);
                }
                if (y_max < Math.max(y1, y2)) {
                    y_max = Math.max(y1, y2);
                }
            }
        }

        public String solve() {
            Arrays.sort(events);
            RMXQ q = new RMXQ(y_min, y_max);
            int max = 1;
            int x = rectangles[0].x1, y = rectangles[0].y1;
            for (Event e : events) {
                if (e.code == 1) {
                    int yl = Math.min(e.r.y1, e.r.y2);
                    int yr = Math.max(e.r.y1, e.r.y2);
                    q.add(yl, yr, 1);
                    int current = q.getMax(y_min, y_max);
                    if (max < current) {
                        max = current;
                        x = e.x;
                    }
                } else {
                    //code 0
                    int yl = Math.min(e.r.y1, e.r.y2);
                    int yr = Math.max(e.r.y1, e.r.y2);
                    q.add(yl, yr, -1);
                }
            }
            ArrayList<Event> yEvents = new ArrayList<>();
            for (Rectangle r : rectangles) {
                if (r.x1 <= x && x <= r.x2) {
                    yEvents.add(new Event(1, r.y1, r));
                    yEvents.add(new Event(-1, r.y2+1, r));
                }
            }
            Collections.sort(yEvents);
            int yMax = 1;
            int current = 0;
            for (Event e : yEvents) {
                current += e.code;
                if (yMax < current) {
                    yMax = current;
                    y = e.x;
                }
            }
            return max + "\n" + x + " " + y;
        }
    }

    private static void randomTest() {
        int n = (int)(Math.random()*5)+4;
        int[] array = new int[n+1];
        StringBuilder b = new StringBuilder();
        b.append(n).append("\n");
        RMXQ q = new RMXQ(n);
        for (int i = 0; i < 7; i++) {
            int l = (int)(Math.random()*(n-1))+1;
            int r = (int)(Math.random()*(n-1))+1;
            if (l > r) {
                int temp = r;
                r = l;
                l = temp;
            }
            int val = (int)(Math.random()*10)-2;
            b.append(l).append(" ").append(r).append(" ").append(val).append("\n");
            q.add(l, r, val);
            for (int j = l; j <= r; j++) {
                array[j] += val;
            }
        }
        int test = q.getMax(1, n);
        int max = 0;
        for (int i = 1; i <= n; i++) {
            if (max < array[i]) {
                max = array[i];
            }
        }
        if (test != max) {
            System.out.println(b.toString().trim());
            System.out.println(Arrays.toString(array));
            throw new RuntimeException("max = " + max + " test = " + test);
        }
    }

    private static void simpleTest() {
        RMXQ q = new RMXQ(7);
        q.from = 1;
        q.add(2,4,4);
        q.add(1,3,-2);
        q.add(1,2,6);
        q.add(1,2,4);
        q.add(2,5,2);
        q.add(1,6,-1);
        q.add(1,6,5);
        if (q.getMax(1,7) != 18) {
            throw new RuntimeException("" + q.getMax(1,7));
        }
    }

    private static void simpleTest2() {
        RMXQ q = new RMXQ(4);
        q.from = 1;
        q.add(2,2,5);
        q.add(2,3,0);
        q.add(1,3,-1);
        q.add(1,2,-2);
        q.add(2,3,-1);
        q.add(1,1,4);
        q.add(1,3,-2);
        if (q.getMax(1,4) != 0) {
            throw new RuntimeException("" + q.getMax(1,4));
        }
    }

    private static void test() {
//        simpleTest();
//        simpleTest2();
//        for (int i = 1; i < 10000; i++) {
//            randomTest();
//        }

        RMXQ q = new RMXQ(0,4);
        //0 0 3 3
        q.add(0,4, 1);
        //0 0 1 1
        q.add(0, 2, 1);
        //1 0 2 1
        q.add(0, 2, 1);
        //1 1 2 2
        q.add(1, 3, 1);
        // 1 1 3 3
        q.add(1, 4, 1);
        int max = q.getMax(0,4);
        if (max != 5) {
            throw new RuntimeException("max " + max + " but should be 5!") ;
        }
        q = new RMXQ(0,4);
        //0 0 3 3
        q.add(0,2, 1);
        //0 0 1 1
        q.add(0, 4, 1);
        //1 0 2 1
        q.add(1, 4, 1);
        //1 1 2 2
        q.add(1, 3, 1);
        // 1 1 3 3
        q.add(0, 2, 1);
        max = q.getMax(0,4);
        if (max != 5) {
            throw new RuntimeException("max " + max + " but should be 5!") ;
        }
        q = new RMXQ(-10, 10);
        q.add(-7, 3+1, 1);
        q.add(-5,5+1, 1);
        q.add(-1, 3+1, 1);
        max = q.getMax(-10, 10);
        if (max != 3) {
            throw new RuntimeException("max " + max + " but should be 3!") ;
        }
        q.add(-10, -6+1, 1);
        q.add(-4, 5+1, 1);
        max = q.getMax(-10, 10);
        if (max != 4) {
            throw new RuntimeException("max " + max + " but should be 4!") ;
        }
        q.add(-8, -3+1, 1);
        max = q.getMax(-10, 10);
        if (max != 4) {
            throw new RuntimeException("max " + max + " but should be 4!") ;
        }
        q.add(-1,0+1, 1);
        max = q.getMax(-10, 10);
        if (max != 5) {
            throw new RuntimeException("max " + max + " but should be 5!") ;
        }
        q.add(-4, -3+1, 1);
        max = q.getMax(-10, 10);
        if (max != 5) {
            throw new RuntimeException("max " + max + " but should be 5!") ;
        }
        q.add(-1, 9+1, 1);
        max = q.getMax(-10, 10);
        if (max != 6) {
            throw new RuntimeException("max " + max + " but should be 6!") ;
        }
        q.add(0, 9+1, 1);
        max = q.getMax(-10, 10);
        if (max != 7) {
            throw new RuntimeException("max " + max + " but should be 7!") ;
        }
        q.add(1, 8+1, 1);
        max = q.getMax(-10, 10);
        if (max != 7) {
            throw new RuntimeException("max " + max + " but should be 7!") ;
        }
        q.add(-10, -1+1, 1);
        max = q.getMax(-10, 10);
        if (max != 7) {
            throw new RuntimeException("max " + max + " but should be 7!") ;
        }
    }

    public static void main(String... args) throws IOException {
//        test();
        Solver s = new Solver(System.in);
        System.out.println(s.solve());
    }
}
