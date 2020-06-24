package com.shad.algorithms.week3.competition;

import java.io.*;
import java.util.ArrayList;
import java.util.StringTokenizer;
import java.util.TreeSet;

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

        public T pollMin() {
            T min = heap.get(size);
            int position = size - 1;
            int minPosition = size;
            while(position > Math.floor(size/2.0)) {
                if (min.compareTo(heap.get(position)) == 1) {
                    min = heap.get(position);
                    minPosition = position;
                }
                position--;
            }
            if (minPosition != size) {
                swap(minPosition, size);
                siftUp(minPosition);
            }
            heap.remove(size);
            size--;
            return min;
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

    public static void test() throws IOException {
        for (int maxN = 10; maxN < 50; maxN++) {
            for (int j = 0; j < 1000; j++) {
                String test = getTestString(maxN);
                FastScanner scanner = new FastScanner(new ByteArrayInputStream(test.getBytes()));
                int n = scanner.nextInt();
                Heap<Integer> heap = new Heap<>();
                TreeSet<Integer> set = new TreeSet<>();
                StringBuilder builder = new StringBuilder();
                for (int i = 0; i < n; i++) {
                    String operation = scanner.nextLine();
                    if (operation.contains("Insert")) {
                        Integer val = Integer.valueOf(operation.substring(7, operation.length()-1));
                        heap.insert(val);
                        set.add(val);
                    } else if (operation.equals("GetMin")) {
                        if (!heap.pollMin().equals(set.pollFirst())) {
                            System.out.println("Error!");
                            System.out.println(test);
                            return;
                        }
                    } else if (operation.equals("GetMax")) {
                        if (!heap.pollMax().equals(set.pollLast())) {
                            System.out.println("Error!");
                            System.out.println(test);
                            return;
                        }
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
                type = 1 + (int) (Math.random() * ((2 - 1) + 1));
                if (type % 2 == 0) {
                    builder.append("GetMax").append("\n");
                    set.pollLast();
                } else {
                    builder.append("GetMin").append("\n");
                    set.pollFirst();
                }

                size--;
            } else {
                int val = 1 + (int) (Math.random() * ((100 - 1) + 1));
                while (set.contains(val)) {
                    val = 1 + (int) (Math.random() * ((100 - 1) + 1));
                }
                set.add(val);
                builder.append("Insert(").append(val).append(")").append("\n");
                size++;
            }
        }

        builder.deleteCharAt(builder.length() - 1);
        return builder.toString();
    }

    public static void main(String... args) throws IOException {
//        test();
        FastScanner scanner = new FastScanner(System.in);
        int n = scanner.nextInt();
        Heap<Integer> heap = new Heap<>();
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < n; i++) {
            String operation = scanner.nextLine();
            if (operation.contains("Insert")) {
                heap.insert(Integer.valueOf(operation.substring(7, operation.length()-1)));
            } else if (operation.equals("GetMin")) {
                builder.append(heap.pollMin()).append("\n");
            } else if (operation.equals("GetMax")) {
                builder.append(heap.pollMax()).append("\n");
            }
        }
        if (builder.length() > 1) {
            builder.deleteCharAt(builder.length() - 1);
        }
        System.out.println(builder.toString());
    }
}
