package com.shad.algorithms.week4.competitions;

import java.util.*;

public class F {
    private static HashMap<Integer, HashSet<Integer>> moves = new HashMap<>();
    static {
        init();
    }

    public static void main(String... args) {
//        Scanner scanner = new Scanner(System.in);
//        int n = scanner.nextInt();
//        System.out.println(getAnswerUpd(n));

//        for (int i = 1; i < 18; i++) {
//            if (getAnswer(i) != getAnswerUpd(i)) {
//                System.out.println("Error");
//                return;
//            }
//        }
        test();
//        System.out.println(getAnswerUpd(n));
    }

    private static long getAnswer(int n) {
        LinkedList<Integer> currentPositions = new LinkedList<>();
        currentPositions.add(1);
        currentPositions.add(2);
        currentPositions.add(3);
        currentPositions.add(4);
        currentPositions.add(5);
        currentPositions.add(6);
        currentPositions.add(7);
        currentPositions.add(9);
        if (n == 1) {
            return 8;
        }
        long count = 0;
        for (int i = 1; i < n; i++) {
            LinkedList<Integer> newPositions = new LinkedList<>();
            for (Integer position : currentPositions) {
                newPositions.addAll(moves.get(position));
            }
            currentPositions.clear();
            if (i == n-1) {
                count = newPositions.size();
            }
            newPositions.forEach(e -> currentPositions.add(e));
        }
        return count;
    }

    private static long getAnswerUpd(int n) {
        HashMap<Integer, Long> currentPositions = new HashMap<>();
        currentPositions.put(1, 1l);
        currentPositions.put(2, 1l);
        currentPositions.put(3, 1l);
        currentPositions.put(4, 1l);
        currentPositions.put(5, 1l);
        currentPositions.put(6, 1l);
        currentPositions.put(7, 1l);
        currentPositions.put(9, 1l);
        if (n == 1) {
            return 8;
        }
        long count = 0;
        for (int i = 1; i < n; i++) {
            HashMap<Integer, Long> newPositions = new HashMap<>();
            for (Integer position : currentPositions.keySet()) {
                for(Integer next : moves.get(position)) {
                    if(newPositions.containsKey(next)) {
                        long currentCount = newPositions.get(next);
                        currentCount += currentPositions.get(position);
                        currentCount %= 1000000000l;
                        newPositions.put(next, currentCount);
                    } else {
                        newPositions.put(next, currentPositions.get(position));
                    }
                }
            }
            currentPositions = newPositions;
        }
        for (Integer position : currentPositions.keySet()) {
            count += currentPositions.get(position);
        }
        return count%1000000000l;
    }

    private static long getAnswerTest(int n) {
        long number = (long) Math.pow(10, n) - 1;
        long count = 0;
        while (number > (long) Math.pow(10, n-1) - 1) {
            if (checkNumber(number, n)) {
                count++;
            }
            number--;
        }
        return count;
    }

    private static boolean checkNumber(long number, int n) {
        long mod = (long)Math.pow(10, n-1);
        int[] numbers = new int[n];
        for (int i = 1; i <= n; i++) {
            int num = (int)(number/mod);
            numbers[i-1] = num;
            number -= num*mod;
            mod /= 10;
        }
        if (numbers[0] == 0 || numbers[0] == 8) {
            return false;
        }
        HashSet<Integer> nextPositions = moves.get(numbers[0]);
        for (int i = 1; i < n; i++) {
            if(!nextPositions.contains(numbers[i])) {
                return false;
            } else {
                nextPositions = moves.get(numbers[i]);
            }
        }
        return true;
    }

    private static void test() {
        boolean flag = checkNumber(34, 2);
        if (!flag) {
            System.out.println("Error!");
        }
        for (int i = 1; i < 10; i++) {
            System.out.println(i);
            if (getAnswerTest(i) != getAnswerUpd(i)) {
                System.out.println("Error" + i);
                return;
            }
        }
    }

    private static void init() {
        //1
        HashSet<Integer> s1 = new HashSet<>();
        s1.add(8);
        s1.add(6);
        moves.put(1, s1);
        //2
        HashSet<Integer> s2 = new HashSet<>();
        s2.add(7);
        s2.add(9);
        moves.put(2, s2);
        //3
        HashSet<Integer> s3 = new HashSet<>();
        s3.add(4);
        s3.add(8);
        moves.put(3, s3);
        //4
        HashSet<Integer> s4 = new HashSet<>();
        s4.add(3);
        s4.add(9);
        s4.add(0);
        moves.put(4, s4);
        //5
        HashSet<Integer> s5 = new HashSet<>();
        moves.put(5, s5);
        //6
        HashSet<Integer> s6 = new HashSet<>();
        s6.add(7);
        s6.add(0);
        s6.add(1);
        moves.put(6, s6);
        //7
        HashSet<Integer> s7 = new HashSet<>();
        s6.add(2);
        s6.add(6);
        moves.put(7, s7);
        //8
        HashSet<Integer> s8 = new HashSet<>();
        s8.add(1);
        s8.add(3);
        moves.put(8, s8);
        //9
        HashSet<Integer> s9 = new HashSet<>();
        s9.add(2);
        s9.add(4);
        moves.put(9, s9);
        //0
        HashSet<Integer> s0 = new HashSet<>();
        s0.add(4);
        s0.add(6);
        moves.put(0, s0);
    }
}
