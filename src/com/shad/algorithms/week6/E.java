package com.shad.algorithms.week6;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Scanner;

public class E {
    private static class Node {
        int val;
        Node previous;
        public Node(int val, Node previous) {
            this.val = val;
            this.previous = previous;
        }
        public Node(int val) {
            this(val, null);
        }
    }
    public static void main(String... args) {
        Scanner scanner = new Scanner(System.in);
        int start = scanner.nextInt();
        int end = scanner.nextInt();
        Node answer = null;
        ArrayList<Node> front = new ArrayList<>();
        HashSet<Integer> marked = new HashSet<>();
        ArrayList<Node> previousFront;
        front.add(new Node(start));
        boolean isFound = false;
        while (!isFound) {
            previousFront = front;
            front = new ArrayList<>();
            for (Node n : previousFront) {
                int val1 = rotateLeft(n.val);
                if (!marked.contains(val1)) {
                    marked.add(val1);
                    if (val1 == end) {
                        answer = new Node(val1, n);
                        isFound = true;
                        break;
                    } else {
                        front.add(new Node(val1, n));
                    }
                }
                int val2 = rotateRight(n.val);
                if (!marked.contains(val2)) {
                    marked.add(val2);
                    if (val2 == end) {
                        answer = new Node(val2, n);
                        isFound = true;
                        break;
                    } else {
                        front.add(new Node(val2, n));
                    }
                }
                if (canAddFirst(n.val)) {
                    int val3 = addFirst(n.val);
                    if (!marked.contains(val3)) {
                        marked.add(val3);
                        if (val3 == end) {
                            answer = new Node(val3, n);
                            isFound = true;
                            break;
                        } else {
                            front.add(new Node(val3, n));
                        }
                    }
                }
                if (canDeleteLast(n.val)) {
                    int val4 = deleteLast(n.val);
                    if (!marked.contains(val4)) {
                        marked.add(val4);
                        if (val4 == end) {
                            answer = new Node(val4, n);
                            isFound = true;
                            break;
                        } else {
                            front.add(new Node(val4, n));
                        }
                    }
                }
            }
        }
        StringBuilder builder = new StringBuilder();
        ArrayList<Integer> answerArray = new ArrayList<>();
        while (answer != null) {
            answerArray.add(answer.val);
            answer = answer.previous;
        }
        for (int i = answerArray.size()-1; i >= 0; i--) {
            builder.append(answerArray.get(i)).append("\n");
        }
        if (builder.length() > 0) {
            builder.deleteCharAt(builder.length() - 1);
        }
        System.out.println(builder.toString());
    }

    private static int rotateRight(int val) {
        String str = String.valueOf(val);
        return Integer.valueOf(String.valueOf(new char[]{str.charAt(3), str.charAt(0), str.charAt(1), str.charAt(2)}));
    }

    private static int rotateLeft(int val) {
        String str = String.valueOf(val);
        return Integer.valueOf(String.valueOf(new char[]{str.charAt(1), str.charAt(2), str.charAt(3), str.charAt(0)}));
    }

    private static boolean canAddFirst(int val) {
        return String.valueOf(val).charAt(0) != '9';
    }

    private static int addFirst(int val) {
        return val + 1000;
    }

    private static boolean canDeleteLast(int val) {
        return val%10 != 1;
    }

    private static int deleteLast(int val) {
        return val-1;
    }
}
