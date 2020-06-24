package com.shad.algorithms.week3.competition;

import java.io.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.StringTokenizer;
import java.util.TreeSet;

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

    static class Heap<T extends Comparable<T>> {

        protected ArrayList<T> heap;
        int size;

        public Heap() {
            heap = new ArrayList<>();
            heap.add(0, null);
            size = 0;
        }

        private void siftUp(int position) {
            if (size < 1) {
                throw new IllegalArgumentException();
            }
            int parentPosition = (int)Math.floor(position/2.0);
            while (parentPosition >= 1
                    && heap.get(parentPosition).compareTo(heap.get(position)) == -1) {
                swap(parentPosition, position);
                position = parentPosition;
                parentPosition = (int)Math.floor(position/2.0);
            }
        }

        private void siftDown(int position) {
            int leftChildPosition = position * 2;
            int rightChildPosition = position * 2 + 1;
            while ((leftChildPosition <= size && heap.get(position).compareTo(heap.get(leftChildPosition)) == -1)
                    || (rightChildPosition <= size && heap.get(position).compareTo(heap.get(rightChildPosition)) == -1)) {
                if (leftChildPosition <= size && rightChildPosition <= size) {
                    T left = heap.get(leftChildPosition);
                    T right = heap.get(rightChildPosition);
                    T current = heap.get(position);
                    if (left.compareTo(right) == 1) {
                        swap(position, leftChildPosition);
                        position = leftChildPosition;
                    } else {
                        swap(position, rightChildPosition);
                        position = rightChildPosition;
                    }
                } else if (rightChildPosition > size) {
                    swap(position, leftChildPosition);
                    position = leftChildPosition;
                } else {
                    swap(position, rightChildPosition);
                    position = rightChildPosition;
                }
                leftChildPosition = position * 2;
                rightChildPosition = position * 2 + 1;
            }
        }

        public T getMax() {
            return heap.get(1);
        }

        public T pollMax() {
            if (size < 1) {
                throw new IllegalArgumentException();
            } else if (size == 1) {
                T min = heap.get(1);
                heap.remove(size);
                size--;
                return min;
            } else {
                T min = heap.get(1);
                swap(1, size);
                heap.remove(size);
                size--;
                siftDown(1);
                return min;
            }
        }

        public void insert(T element) {
            size++;
            heap.add(element);
            siftUp(size);
        }

        public void swap(int l, int r) {
            if (l > size || r > size) {
                throw new IllegalArgumentException();
            }
            if (l < 1 || r < 1) {
                throw new IllegalArgumentException();
            }
            T temp = heap.get(l);
            heap.set(l, heap.get(r));
            heap.set(r, temp);
        }
    }

    public static void testHeap() throws IOException {
//        simpleTest();
//        oneMoreTest();
        generalTest();
    }

    public static void simpleTest() {
        Heap<Integer> heap = new Heap<Integer>();
        heap.insert(1);
        heap.insert(2);
        heap.insert(3);
        if (heap.pollMax() != 3) {
            System.out.println("Error!");
        }
        if (heap.pollMax() != 2) {
            System.out.println("Error!");
        }
        if (heap.pollMax() != 1) {
            System.out.println("Error!");
        }

        heap.insert(5);
        if (heap.pollMax() != 5) {
            System.out.println("Error!");
        }

        heap.insert(3);
        if (heap.getMax() != 3) {
            System.out.println("Error!");
        }

        heap.insert(5);
        if (heap.getMax() != 5) {
            System.out.println("Error!");
        }

        heap.insert(3);
        if (heap.getMax() != 5) {
            System.out.println("Error!");
        }

        if (heap.pollMax() != 5) {
            System.out.println("Error!");
        }
        if (heap.pollMax() != 3) {
            System.out.println("Error!");
        }
        if (heap.pollMax() != 3) {
            System.out.println("Error!");
        }

        heap.insert(1);
        heap.insert(2);
        heap.insert(3);
        heap.insert(4);
        heap.insert(5);
        heap.insert(6);
        if (heap.pollMax() != 6) {
            System.out.println("Error!");
        }
        heap.insert(7);
        if (heap.pollMax() != 7) {
            System.out.println("Error!");
        }
        heap.insert(5);
        if (heap.pollMax() != 5) {
            System.out.println("Error!");
        }
        if (heap.pollMax() != 5) {
            System.out.println("Error!");
        }
        if (heap.pollMax() != 4) {
            System.out.println("Error!");
        }
        if (heap.pollMax() != 3) {
            System.out.println("Error!");
        }
        if (heap.pollMax() != 2) {
            System.out.println("Error!");
        }
        if (heap.pollMax() != 1) {
            System.out.println("Error!");
        }
        heap.insert(1);
        heap.insert(1);
        heap.insert(1);
        heap.insert(1);
        heap.insert(3);
        heap.insert(3);
        heap.insert(3);
        heap.insert(3);

        if (heap.pollMax() != 3) {
            System.out.println("Error!");
        }
        if (heap.pollMax() != 3) {
            System.out.println("Error!");
        }
        if (heap.pollMax() != 3) {
            System.out.println("Error!");
        }
        if (heap.pollMax() != 3) {
            System.out.println("Error!");
        }
        if (heap.pollMax() != 1) {
            System.out.println("Error!");
        }
        if (heap.pollMax() != 1) {
            System.out.println("Error!");
        }
        if (heap.pollMax() != 1) {
            System.out.println("Error!");
        }
        if (heap.pollMax() != 1) {
            System.out.println("Error!");
        }
    }

    public static void oneMoreTest() {
        Heap<Integer> heap = new Heap<>();
        heap.insert(1);
        heap.insert(3);
        heap.insert(2);
        if (heap.pollMax() != 3) {
            System.out.println("Error!");
        }
        if (heap.pollMax() != 2) {
            System.out.println("Error!");
        }
        if (heap.pollMax() != 1) {
            System.out.println("Error!");
        }
        heap.insert(2);
        heap.insert(3);
        heap.insert(1);
        if (heap.pollMax() != 3) {
            System.out.println("Error!");
        }
        if (heap.pollMax() != 2) {
            System.out.println("Error!");
        }
        if (heap.pollMax() != 1) {
            System.out.println("Error!");
        }
    }

    private static void generalTest() throws IOException {
        for (int j = 0; j < 100000; j++) {
            String test = getTestString(100);
            FastScanner scanner = new FastScanner(new ByteArrayInputStream(test.getBytes()));
            int n = scanner.nextInt();
            Heap<Integer> heap = new Heap<>();
            TreeSet<Integer> tree = new TreeSet<>();
            for (int i = 0; i < n; i++) {
                int operation = scanner.nextInt();
                if (operation == 0) {
                    int val = scanner.nextInt();
                    heap.insert(val);
                    tree.add(val);
                } else {
                    if (tree.pollLast() != heap.pollMax()) {
                        System.out.println("Error!");
                        System.out.println(test);
                        return;
                    }
                }
            }
        }

    }

    public static String getTestString(int maxInt) {
        int n = 1 + (int) (Math.random() * ((maxInt - 1) + 1));
        int size = 0;
        StringBuilder builder = new StringBuilder();
        builder.append(n).append("\n");
        TreeSet<Integer> set = new TreeSet<>();
        for (int operation = 0; operation < n; operation++) {
            int type = 1 + (int) (Math.random() * ((3 - 1) + 1));
            if (type % 3 == 0 && size > 0) {
                builder.append("1").append("\n");
                set.pollLast();
                size--;
            } else {
                int val = 1 + (int) (Math.random() * ((100 - 1) + 1));
                while (set.contains(val)) {
                    val = 1 + (int) (Math.random() * ((100 - 1) + 1));
                }
                set.add(val);
                builder.append("0").append(" ").append(val).append("\n");
                size++;
            }
        }

        builder.deleteCharAt(builder.length() - 1);
        return builder.toString();
    }

    public static void main(String... args) throws IOException {
//        testHeap();
        FastScanner scanner = new FastScanner(System.in);
        int n = scanner.nextInt();
        Heap<Integer> heap = new Heap<>();
//        TreeSet<Integer> tree = new TreeSet<>();
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < n; i++) {
            int operation = scanner.nextInt();
            if (operation == 0) {
                int val = scanner.nextInt();
                heap.insert(val);
//                tree.add(val);
            } else {
//                int val1 = tree.pollLast();
                int val2 = heap.pollMax();
//                if (val1 != val2) {
//                    System.out.println(val1 + " != " + val2);
//                }
                builder.append(val2).append("\n");
            }
        }
        if (builder.length() > 1) {
            builder.deleteCharAt(builder.length() - 1);
        }
        System.out.println(builder.toString());
    }
}
