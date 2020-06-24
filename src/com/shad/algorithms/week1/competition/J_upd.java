package com.shad.algorithms.week1.competition;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class J_upd {

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

    static class Node {
        Node next;
        int id;
        Node previous;

        public Node(Node next, Node previous, int id) {
            this.next = next;
            this.id = id;
        }
    }

    static class Queue {
        Node first;
        Node last;
        Node mid;
        long size = 0;
        boolean sizeEven = true;

        public void enqueue(int id) {
            Node newNode = new Node(null, null, id);
            if (first == null) {
                this.first = newNode;
                this.last = first;
                this.mid = first;
            } else {
                this.last.next = newNode;
                newNode.previous = this.last;
                this.last = newNode;
                if (sizeEven) {
                    this.mid = this.mid.next;
                }
            }
            size++;
            sizeEven ^= true;
        }

//        public int dequeue() {
//            Node returnNode = this.first;
//            if (this.mid == this.first) {
//                this.mid = this.first.next;
//                this.mid.previous = null;
//            }
//            this.first = this.first.next;
//            if (!sizeEven) {
//                this.mid = this.mid.previous;
//            } else {
//                this.mid = this.mid.next;
//            }
//            size--;
//            sizeEven ^= true;
//            return returnNode.id;
//        }

        public int dequeue() {
            Node returnNode = this.first;
            this.first = this.first.next;
            if (size == 1) {
                updateTheMiddle(null);
            } else if (sizeEven) {
                updateTheMiddle(this.mid.next);
            }
            size--;
            sizeEven ^= true;
            return returnNode.id;
        }

        public void updateTheMiddle(Node node) {
            this.mid = node;
        }

        public void setInTheMiddle(int id) {
            Node newNode = new Node(null, null, id);
            if (first == null) {
                this.first = newNode;
                this.last = first;
                this.mid = first;
            } else if(size == 1) {
                enqueue(id);
                return;
            }  else {
                newNode.next = this.mid.next;
                newNode.previous = this.mid;
                this.mid.next = newNode;
                if (sizeEven) {
                    this.mid = newNode;
                }
            }
            size++;
            sizeEven ^= true;
        }

        @Override
        public String toString() {
            Node currentNode = first;
            if (first == null) {
                return "[]";
            }
            StringBuilder builder = new StringBuilder();
            builder.append("[");
            do {
                builder.append(currentNode.id).append(" ");
                currentNode = currentNode.next;
            } while(currentNode != null);
            builder.append("]");
            return builder.toString();
        }
    }

    public static void main(String... args) throws IOException {
//        testQueue();
        FastScanner scanner = new FastScanner(System.in);
        int operationCount = scanner.nextInt();
        Queue queue = new Queue();
        StringBuilder builder = new StringBuilder();
        char operationType;
        String line;
        for (int i = 1; i <= operationCount; i++) {
            line = scanner.nextLine();
            operationType = line.charAt(0);
            if (operationType == '+') {
                queue.enqueue(Integer.valueOf(line.substring(2)));
            } else if (operationType == '*') {
                queue.setInTheMiddle(Integer.valueOf(line.substring(2)));
            } else if (operationType == '-') {
                builder.append(queue.dequeue()).append("\n");
            }
        }
        System.out.println(builder.toString());
    }

    public List<Integer> execute(InputStream input) throws IOException {
        FastScanner scanner = new FastScanner(input);
        int operationCount = scanner.nextInt();
        Queue queue = new Queue();
//        StringBuilder builder = new StringBuilder();
        List<Integer> returnArray = new ArrayList<>();
        char operationType;
        String line;
        for (int i = 1; i <= operationCount; i++) {
            line = scanner.nextLine();
            operationType = line.charAt(0);
            if (operationType == '+') {
                queue.enqueue(Integer.valueOf(line.substring(2)));
            } else if (operationType == '*') {
                queue.setInTheMiddle(Integer.valueOf(line.substring(2)));
            } else if (operationType == '-') {
//                builder.append(queue.dequeue()).append("\n");
                returnArray.add(queue.dequeue());
            }
        }
//        return builder.toString();
        return returnArray;
    }

    static void testQueue(){
        simpleQueueTest();
        setInTheMiddleTest();
        mixedTest();
    }
    static void simpleQueueTest() {
        Queue queue = new Queue();
        queue.enqueue(1);
        queue.enqueue(2);
        queue.enqueue(3);
        queue.enqueue(4);
        queue.enqueue(5);
        int val = queue.dequeue();
        if (val != 1) {
            throw new RuntimeException("first tests error");
        }
        val = queue.dequeue();
        if (val != 2) {
            throw new RuntimeException("first tests error");
        }
        val = queue.dequeue();
        if (val != 3) {
            throw new RuntimeException("first tests error");
        }
        val = queue.dequeue();
        if (val != 4) {
            throw new RuntimeException("first tests error");
        }
        val = queue.dequeue();
        if (val != 5) {
            throw new RuntimeException("first tests error");
        }
    }

    public static void setInTheMiddleTest() {
        Queue queue = new Queue();
        queue.enqueue(1);
        System.out.println(queue);
        queue.setInTheMiddle(5);
        System.out.println(queue);
        queue.setInTheMiddle(2);
        System.out.println(queue);
        queue.setInTheMiddle(4);
        System.out.println(queue);
        queue.setInTheMiddle(3);
        System.out.println(queue);
        int val = queue.dequeue();
        if (val != 1) {
            throw new RuntimeException("setInTheMiddleTest tests error");
        }
        val = queue.dequeue();
        if (val != 2) {
            throw new RuntimeException("setInTheMiddleTest tests error");
        }
        val = queue.dequeue();
        if (val != 3) {
            throw new RuntimeException("setInTheMiddleTest tests error");
        }
        val = queue.dequeue();
        if (val != 4) {
            throw new RuntimeException("setInTheMiddleTest tests error");
        }
        val = queue.dequeue();
        if (val != 5) {
            throw new RuntimeException("setInTheMiddleTest tests error");
        }
    }

    public static void mixedTest() {
        Queue queue = new Queue();
        queue.setInTheMiddle(1);
        System.out.println(queue);
        queue.setInTheMiddle(2);
        System.out.println(queue);
        int val = queue.dequeue();
        System.out.println(queue);
        if (val != 1) {
            throw new RuntimeException("mixedTest test error");
        }
        queue.setInTheMiddle(3);
        System.out.println(queue);
        queue.enqueue(4);
        System.out.println(queue);
        queue.enqueue(5);
        System.out.println(queue);
        val = queue.dequeue();
        System.out.println(queue);
        if (val != 2) {
            throw new RuntimeException("mixedTest test error");
        }
        val = queue.dequeue();
        System.out.println(queue);
        if (val != 3) {
            throw new RuntimeException("mixedTest test error");
        }
        queue.enqueue(6);
        System.out.println(queue);
        queue.setInTheMiddle(7);
        System.out.println(queue);
        val = queue.dequeue();
        System.out.println(queue);
        if (val != 4) {
            throw new RuntimeException("mixedTest test error");
        }
        val = queue.dequeue();
        System.out.println(queue);
        if (val != 5) {
            throw new RuntimeException("mixedTest test error");
        }
        val = queue.dequeue();
        System.out.println(queue);
        if (val != 7) {
            throw new RuntimeException("mixedTest test error");
        }
        val = queue.dequeue();
        System.out.println(queue);
        if (val != 6) {
            throw new RuntimeException("mixedTest test error");
        }
    }
}
