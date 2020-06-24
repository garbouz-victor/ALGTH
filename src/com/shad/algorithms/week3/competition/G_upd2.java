package com.shad.algorithms.week3.competition;

import java.io.*;
import java.util.ArrayList;
import java.util.StringTokenizer;
import java.util.TreeSet;

public class G_upd2 {
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

    static class MinMaxHeap<T extends Comparable<T>> {

        protected ArrayList<T> heap;
        protected int size;
        int rootPosition = 1;

        public MinMaxHeap() {
            heap = new ArrayList<>();
            heap.add(0, null);
            size = 0;
        }

        protected void pushDown(int position) {
            if (minLevel(position)) {
                pushDownMin(position);
            } else {
                pushDownMax(position);
            }
        }

        protected void pushDownMax(int position) {
            if (hasChildren(position)) {
                int m = getPositionOfTheLargestChildAndGrandChild(position);
                if (m == -1) {
                    return;
                }
                if (isGrandChild(position, m)) {
                    if (heap.get(m).compareTo(heap.get(position)) == 1) {
                        swap(m, position);
                        if (heap.get(m).compareTo(heap.get(getParent(m))) == -1) {
                            swap(m, getParent(m));
                        }
                        pushDownMax(m);
                    }
                } else if (heap.get(m).compareTo(heap.get(position)) == 1) {
                    swap(m, position);
                }
            }
        }

        protected int getPositionOfTheLargestChildAndGrandChild(int position) {
            int fsmGrandChild = getPositionOfTheLargestChild(position*2);
            int ssmGrandChild = getPositionOfTheLargestChild(position*2 + 1);
            if (ssmGrandChild != -1) {
                if (heap.get(fsmGrandChild).compareTo(heap.get(ssmGrandChild)) == 1) {
                    if (heap.get(fsmGrandChild).compareTo(heap.get(getPositionOfTheLargestChild(position))) == 1) {
                        return fsmGrandChild;
                    } else {
                        return getPositionOfTheLargestChild(position);
                    }
                } else {
                    if (heap.get(ssmGrandChild).compareTo(heap.get(getPositionOfTheLargestChild(position))) == 1) {
                        return ssmGrandChild;
                    } else {
                        return getPositionOfTheLargestChild(position);
                    }
                }
            } else if (fsmGrandChild != -1) {
                if (heap.get(fsmGrandChild).compareTo(heap.get(getPositionOfTheLargestChild(position))) == 1) {
                    return fsmGrandChild;
                } else {
                    return getPositionOfTheLargestChild(position);
                }
            } else {
                return getPositionOfTheLargestChild(position);
            }
        }

        protected int getPositionOfTheLargestChild(int position) {
            if (position*2 > size) {
                return -1;
            } else {
                int fch = position*2;
                int sch = position*2 + 1;
                if (sch <= size) {
                    if (heap.get(fch).compareTo(heap.get(sch)) == 1) {
                        return fch;
                    } else {
                        return sch;
                    }
                } else {
                    return fch;
                }
            }
        }

        protected void pushDownMin(int position) {
            if (hasChildren(position)) {
                //index of smallest child or grandchild of position
                int m = getPositionOfSmallestChildAndGrandChild(position);
                if (m == -1) {
                    return;
                }
                if (isGrandChild(position, m)) {
                    if (heap.get(m).compareTo(heap.get(position)) == -1) {
                        swap(m, position);
                        if (heap.get(m).compareTo(heap.get(getParent(m))) == 1) {
                            swap(m, getParent(m));
                        }
                        pushDownMin(m);
                    }
                } else if (heap.get(m).compareTo(heap.get(position)) == -1) {
                    swap(m, position);
                }
            }
        }

        protected int getPositionOfSmallestChild(int position) {
            if (position*2 > size) {
                return -1;
            } else {
                int fch = position*2;
                int sch = position*2 + 1;
                if (sch <= size) {
                    if (heap.get(fch).compareTo(heap.get(sch)) == -1) {
                        return fch;
                    } else {
                        return sch;
                    }
                } else {
                    return fch;
                }
            }
        }

        protected int getPositionOfSmallestChildAndGrandChild(int position) {
            int fsmGrandChild = getPositionOfSmallestChild(position*2);
            int ssmGrandChild = getPositionOfSmallestChild(position*2 + 1);
            if (ssmGrandChild != -1) {
                if (heap.get(fsmGrandChild).compareTo(heap.get(ssmGrandChild)) == -1) {
                    if (heap.get(fsmGrandChild).compareTo(heap.get(getPositionOfSmallestChild(position))) == -1) {
                        return fsmGrandChild;
                    } else {
                        return getPositionOfSmallestChild(position);
                    }
                } else {
                    if (heap.get(ssmGrandChild).compareTo(heap.get(getPositionOfSmallestChild(position))) == -1) {
                        return ssmGrandChild;
                    } else {
                        return getPositionOfSmallestChild(position);
                    }
                }
            } else if (fsmGrandChild != -1) {
                if (heap.get(fsmGrandChild).compareTo(heap.get(getPositionOfSmallestChild(position))) == -1) {
                    return fsmGrandChild;
                } else {
                    return getPositionOfSmallestChild(position);
                }
            } else {
                return getPositionOfSmallestChild(position);
            }
        }

        protected boolean isGrandChild(int position, int grandChildPosition) {
            if (grandChildPosition < 4) {
                return false;
            } else if (Math.floor(Math.floor(grandChildPosition/2.0)/2.0) == position) {
                return true;
            } else {
                return false;
            }
        }

        protected boolean hasChildren(int position) {
            return size >= position*2;
        }

        public void pushUp(int position) {
            if (position != rootPosition) {
                if (minLevel(position)) {
                    if (heap.get(position).compareTo(heap.get(getParent(position))) == 1) {
                        swap(position, getParent(position));
                        pushUpMax(getParent(position));
                    } else {
                        pushUpMin(position);
                    }
                } else{
                    if (heap.get(position).compareTo(heap.get(getParent(position))) == -1) {
                        swap(position, getParent(position));
                        pushUpMin(getParent(position));
                    } else {
                        pushUpMax(position);
                    }
                }
            }
        }

        protected void pushUpMin(int position) {
            if (hasGrandParent(position) && heap.get(position).compareTo(heap.get(getGrandParent(position))) == -1) {
                swap(position, getGrandParent(position));
                pushUpMin(getGrandParent(position));
            }
        }

        protected void pushUpMax(int position) {
            if (hasGrandParent(position) && heap.get(position).compareTo(heap.get(getGrandParent(position))) == 1) {
                swap(position, getGrandParent(position));
                pushUpMax(getGrandParent(position));
            }
        }

        protected int getParent(int position) {
            return (int)Math.floor(position/2.0);
        }

        protected int getGrandParent(int position) {
            return getParent(getParent(position));
        }

        protected boolean hasGrandParent(int position) {
            if (position >= 4) {
                return true;
            } else {
                return false;
            }
        }

        protected void swap(int l, int r) {
            T temp = heap.get(l);
            heap.set(l, heap.get(r));
            heap.set(r, temp);
        }

        protected boolean minLevel(int position) {
            int level = (int)(Math.log(position)/Math.log(2));
            return level % 2 == 0;
        }

        public void insert(T val) {
            heap.add(val);
            size++;
            pushUp(size);
        }

        public T getMin() {
            T min = heap.get(rootPosition);
            if (size != rootPosition) {
                swap(rootPosition,size);
                heap.remove(size);
                size--;
                pushDown(rootPosition);
            } else {
                heap.remove(size);
                size--;
            }
            return min;
        }

        public T getMax() {
            int maxPosition = getPositionOfTheLargestChild(rootPosition);
            if (maxPosition == -1) {
                T max = heap.get(rootPosition);
                heap.remove(size);
                size--;
                return max;
            } else {
                T max = heap.get(maxPosition);
                swap(maxPosition, size);
                heap.remove(size);
                size--;
                pushDown(maxPosition);
                return max;
            }
        }
    }

    public static void test() throws IOException {
        for (int maxN = 10; maxN < 50; maxN++) {
            for (int j = 0; j < 1000; j++) {
                String test = getTestString(maxN);
                FastScanner scanner = new FastScanner(new ByteArrayInputStream(test.getBytes()));
                int n = scanner.nextInt();
                MinMaxHeap<Integer> heap = new MinMaxHeap<>();
                TreeSet<Integer> set = new TreeSet<>();
                StringBuilder builder = new StringBuilder();
                for (int i = 0; i < n; i++) {
                    String operation = scanner.nextLine();
                    if (operation.contains("Insert")) {
                        Integer val = Integer.valueOf(operation.substring(7, operation.length()-1));
                        heap.insert(val);
                        set.add(val);
                    } else if (operation.equals("GetMin")) {
                        if (!heap.getMin().equals(set.pollFirst())) {
                            System.out.println("Error!");
                            System.out.println(test);
                            return;
                        }
                    } else if (operation.equals("GetMax")) {
                        try {
                            if (!heap.getMax().equals(set.pollLast())) {
                                System.out.println("Error!");
                                System.out.println(test);
                                return;
                            }
                        } catch (Exception ex) {
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
        StringBuilder builder = new StringBuilder();
        MinMaxHeap<Integer> minMax = new MinMaxHeap<>();
        for (int i = 0; i < n; i++) {
            String operation = scanner.nextLine();
            if (operation.contains("Insert")) {
                minMax.insert(Integer.valueOf(operation.substring(7, operation.length()-1)));
            } else if (operation.equals("GetMin")) {
                builder.append(minMax.getMin()).append("\n");
            } else if (operation.equals("GetMax")) {
                builder.append(minMax.getMax()).append("\n");
            }
        }
        if (builder.length() > 1) {
            builder.deleteCharAt(builder.length() - 1);
        }
        System.out.println(builder.toString());
    }
}
