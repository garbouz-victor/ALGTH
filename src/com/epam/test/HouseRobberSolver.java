package com.epam.test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class HouseRobberSolver {

    private static class Solver {
        int[] values;
        int[] df;
        int max, current, previous;
        public Solver(int[] values) {
            this.values = values;
            this.df = new int[this.values.length];
        }

        public int getAnswer() {
            max = 0;
            current = 0;
            previous = 0;
            for (int i = 0; i < values.length; i++) {
                if (i == 0) {
                    df[i] = values[i];
                } else if (i == 1) {
                    df[i] = Math.max(values[i], df[i-1]);
                } else {
                    df[i] = Math.max(
                            Math.max(values[i] + getDfI(i-2), getDfI(i-3) + values[i-1]),
                            getDfI(i-2));
                }
                if (max < df[i]) {
                    max = df[i];
                }
            }
            return max;
        }

        private int getDfI(int i) {
            if (i < 0 || i >= df.length) {
                return 0;
            } else {
                return df[i];
            }
        }
    }

    private static void test() {
        int[] testArray = new int[]{1, 4, 2, 5};
        Solver s = new Solver(testArray);
        int answer = s.getAnswer();
        if (answer != 9) {
            throw new RuntimeException(String.format("Answer %s is error answer!", answer));
        }
        testArray = new int[]{6, 9, 0, 2, 5};
        s = new Solver(testArray);
        answer = s.getAnswer();
        if (answer != 14) {
            throw new RuntimeException(String.format("Answer %s is error answer!", answer));
        }
    }

    private static void largeTest() {
        int size = 10;
        int[] testArray = new int[size];
        for (int i = 0; i < size; i++) {
            testArray[i] = ThreadLocalRandom.current().nextInt(0, 100);
        }
        int testAnswer = 0;
        int answer = 0;
        int n = testArray.length;
        int N = (int) Math.pow(2d, Double.valueOf(n));
        List<Integer> numsAnswer = null;
        for (int i = 1; i < N; i++) {
            String code = Integer.toBinaryString(N | i).substring(1);
            List<Integer> nums = new ArrayList<>();
            for (int j = 0; j < n; j++) {
                if (code.charAt(j) == '1') {
                    nums.add(j);
                }
            }
            //test nums
            boolean flag = false;
            for (int j = 1; j < nums.size(); j++) {
                if (nums.get(j) - nums.get(j-1) == 1) {
                    flag = true;
                    break;
                }
            }
            if (flag) {
                continue;
            }
            int tempAnswer = 0;
            for (Integer j : nums) {
                tempAnswer += testArray[j];
            }
            if (answer < tempAnswer) {
                answer = tempAnswer;
                numsAnswer = nums;
            }
        }
        Solver s = new Solver(testArray);
        testAnswer = s.getAnswer();
        if (answer != testAnswer) {
            throw new RuntimeException(String.format(
                    "Error for %s, answer = %s, testAnswer = %s, nums = %s",
                    Arrays.toString(testArray), answer, testAnswer, Arrays.toString(numsAnswer.toArray())));
        }
    }

    public static void main(String... args) {
        test();
        for (int i = 0; i < 100; i++) {
            largeTest();
        }
    }
}
