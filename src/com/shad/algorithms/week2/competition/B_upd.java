package com.shad.algorithms.week2.competition;

import java.io.*;
import java.util.Arrays;
import java.util.Random;
import java.util.StringTokenizer;

public class B_upd {
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
        int[] array = new int[n];
        for (int i = 0; i < n; i++) {
            array[i] = scanner.nextInt();
        }
        int k = scanner.nextInt();
        StringBuilder builder = new StringBuilder();
        //shuffling
        Random rand = new Random();
        for (int i = 0; i < array.length; i++) {
            int randomIndexToSwap = rand.nextInt(array.length);
            int temp = array[randomIndexToSwap];
            array[randomIndexToSwap] = array[i];
            array[i] = temp;
        }
        Arrays.sort(array);
        int l,r;
        int leftCursor, rightCursor;
        for (int i = 0; i < k; i++) {
            l = scanner.nextInt();
            r = scanner.nextInt();
            leftCursor = getLeftPosition(array, l);
            if (leftCursor == -1) {
                builder.append(0).append(" ");
                continue;
            }
            rightCursor = getRightPosition(array, r);
            if (rightCursor >= leftCursor) {
                builder.append(rightCursor - leftCursor + 1).append(" ");
            } else {
                builder.append(0).append(" ");
            }
        }
        builder.deleteCharAt(builder.length() - 1);
        System.out.println(builder.toString());

//        test();
//        System.out.println(getTestString(5,4, 0, 100));

//        for (int j = 0; j < 1000000; j++) {
//            int n = 100;
//            for (int k = 5; k < 10 ; k++) {
//                String test = getTestString(n, k, -100, 100);
//                B.BProcessor processor = new B.BProcessor(new ByteArrayInputStream(test.getBytes()));
//                B_UpdProcessor processorTest = new B_UpdProcessor(new ByteArrayInputStream(test.getBytes()));
//                String answer = processor.getAnswer();
//                String testAnswer = processorTest.getAnswer();
//                String[] answerArray = answer.split(" ");
//                String[] testAnswerArray = testAnswer.split(" ");
//                for (int i = 0; i < k ; i++) {
//                    if (Integer.valueOf(answerArray[i]) != Integer.valueOf(testAnswerArray[i])) {
//                        System.out.println("test " + test);
//                        System.out.println("answer " + answer);
//                        System.out.println("testAnswer " + testAnswer);
//                        return;
//                    }
//                }
//            }
//        }
    }

    static class B_UpdProcessor {
        String answer;

        public B_UpdProcessor(InputStream is) throws IOException {
            FastScanner scanner = new FastScanner(is);
            int n = scanner.nextInt();
            int[] array = new int[n];
            for (int i = 0; i < n; i++) {
                array[i] = scanner.nextInt();
            }
            int k = scanner.nextInt();
            StringBuilder builder = new StringBuilder();
            //shuffling
            Random rand = new Random();
            for (int i = 0; i < array.length; i++) {
                int randomIndexToSwap = rand.nextInt(array.length);
                int temp = array[randomIndexToSwap];
                array[randomIndexToSwap] = array[i];
                array[i] = temp;
            }
            Arrays.sort(array);
            int l, r;
            int leftCursor, rightCursor;
            for (int i = 0; i < k; i++) {
                l = scanner.nextInt();
                r = scanner.nextInt();
                leftCursor = getLeftPosition(array, l);
                if (leftCursor == -1) {
                    builder.append(0).append(" ");
                    continue;
                }
                rightCursor = getRightPosition(array, r);
                if (rightCursor >= leftCursor) {
                    builder.append(rightCursor - leftCursor + 1).append(" ");
                } else {
                    builder.append(0).append(" ");
                }
            }
            builder.deleteCharAt(builder.length() - 1);
            this.answer = builder.toString();
        }

        public String getAnswer() {
            return this.answer;
        }
    }

    public static int getLeftPosition(int[] array, int val) {
        if (array.length == 0) {
            return -1;
        }
        if (val <= array[0]) {
            return 0;
        } else {
            int right = array.length - 1;
            int left = 0;
            int mid = (right - left) / 2;
            while(right >= left) {
                if (array[mid] >= val) {
                    if (mid == 0) {
                        break;
                    } else {
                        if (array[mid - 1] >= val) {
                            right = mid;
                            mid = left + (right - left) / 2;
                        } else {
                            break;
                        }
                    }
                } else {
                    left = mid + 1;
                    if (left == right) {
                        mid = right;
                    } else if(left > right) {
                        mid = -1;
                        break;
                    }  else {
                        mid = left + (right - left) / 2;
                    }
                }
            }
            return mid;
        }
    }

    public static int getRightPosition(int[] array, int val) {
        if (array.length == 0) {
            return -1;
        } else if(array[array.length - 1] <= val) {
            return array.length - 1;
        } else {
            int left = 0;
            int right = array.length - 1;
            int mid = (right - left) / 2;
            while (right >= left) {
                if (array[mid] <= val) {
                    if (mid == array.length) {
                        break;
                    } else if (array[mid+1] <= val) {
                        left = mid + 1;
                        mid = left + (right - left)/2;
                    } else {
                        break;
                    }
                } else {
                    right = mid - 1;
                    if (left == right) {
                        mid = right;
                    } else if(left > right) {
                        mid = -1;
                        break;
                    }  else {
                        mid = left + (right - left) / 2;
                    }
                }
            }
            return mid;
        }
    }

    public static void test() {
        testLeft();
        testRight();
    }

    public static void testRight() {
        int result = getRightPosition(new int[]{1, 1}, 2);
        if (result != 1) {
            System.out.println("Error!");
        }
        result = getRightPosition(new int[]{1, 1}, 1);
        if (result != 1) {
            System.out.println("Error!");
        }
        result = getRightPosition(new int[]{1, 1, 2}, 2);
        if (result != 2) {
            System.out.println("Error!");
        }
        result = getRightPosition(new int[]{2, 2, 2}, 2);
        if (result != 2) {
            System.out.println("Error!");
        }
        result = getRightPosition(new int[]{0, 1, 2}, 1);
        if (result != 1) {
            System.out.println("Error!");
        }
        result = getRightPosition(new int[]{0}, 1);
        if (result != 0) {
            System.out.println("Error!");
        }
        result = getRightPosition(new int[]{}, 1);
        if (result != -1) {
            System.out.println("Error!");
        }
        result = getRightPosition(new int[]{1, 1, 1, 2, 2, 2, 2, 3, 3, 3}, 3);
        if (result != 9) {
            System.out.println("Error!");
        }
        result = getRightPosition(new int[]{1, 1, 1, 2, 2, 2, 2, 3, 3, 3}, 2);
        if (result != 6) {
            System.out.println("Error!");
        }
        result = getRightPosition(new int[]{1, 1, 1, 1, 2, 2, 2, 2, 3, 3, 3}, 2);
        if (result != 7) {
            System.out.println("Error!");
        }
        result = getRightPosition(new int[]{1, 1, 1, 1, 1, 2, 2, 2, 2, 3, 3, 3}, 2);
        if (result != 8) {
            System.out.println("Error!");
        }

        result = getRightPosition(new int[]{1, 1, 1, 1, 1, 2, 2, 2, 2, 3, 3, 3}, 3);
        if (result != 11) {
            System.out.println("Error!");
        }

        result = getRightPosition(new int[]{1, 1, 1}, -1);
        if (result != -1) {
            System.out.println("Error!");
        }
        result = getRightPosition(new int[]{-1, 1, 2}, 2);
        if (result != 2) {
            System.out.println("Error!");
        }
        result = getRightPosition(new int[]{-6, -5, -2, 0, 2, 3, 7, 12, 29, 53}, 4);
        if (result != 5) {
            System.out.println("Error!");
        }
        result = getRightPosition(new int[]{-6, -5, -2, 0, 2, 3, 7, 12, 29, 53}, 7);
        if (result != 6) {
            System.out.println("Error!");
        }
    }

    public static void testLeft() {
        int result = getLeftPosition(new int[]{1, 1}, 2);
        if (result != -1) {
            System.out.println("Error!");
        }
        result = getLeftPosition(new int[]{1, 1, 1}, 2);
        if (result != -1) {
            System.out.println("Error!");
        }
        result = getLeftPosition(new int[]{1, 1, 2}, 2);
        if (result != 2) {
            System.out.println("Error!");
        }
        result = getLeftPosition(new int[]{2, 2, 2}, 2);
        if (result != 0) {
            System.out.println("Error!");
        }
        result = getLeftPosition(new int[]{0, 1, 2}, 1);
        if (result != 1) {
            System.out.println("Error!");
        }
        result = getLeftPosition(new int[]{0}, 1);
        if (result != -1) {
            System.out.println("Error!");
        }
        result = getLeftPosition(new int[]{}, 1);
        if (result != -1) {
            System.out.println("Error!");
        }
        result = getLeftPosition(new int[]{1, 1, 1, 2, 2, 2, 2, 3, 3, 3}, 1);
        if (result != 0) {
            System.out.println("Error!");
        }
        result = getLeftPosition(new int[]{1, 1, 1, 2, 2, 2, 2, 3, 3, 3}, 2);
        if (result != 3) {
            System.out.println("Error!");
        }
        result = getLeftPosition(new int[]{1, 1, 1, 1, 2, 2, 2, 2, 3, 3, 3}, 2);
        if (result != 4) {
            System.out.println("Error!");
        }
        result = getLeftPosition(new int[]{1, 1, 1, 1, 1, 2, 2, 2, 2, 3, 3, 3}, 2);
        if (result != 5) {
            System.out.println("Error!");
        }

        result = getLeftPosition(new int[]{1, 1, 1, 1, 1, 2, 2, 2, 2, 3, 3, 3}, 3);
        if (result != 9) {
            System.out.println("Error!");
        }

        result = getLeftPosition(new int[]{1, 1, 1}, -1);
        if (result != 0) {
            System.out.println("Error!");
        }
        result = getLeftPosition(new int[]{-1, 1, 2}, 2);
        if (result != 2) {
            System.out.println("Error!");
        }
        result = getLeftPosition(new int[]{-6, -5, -2, 0, 2, 3, 7, 12, 29, 53}, 4);
        if (result != 6) {
            System.out.println("Error!");
        }
    }

    public static String getTestString(int n, int k, int min, int max) {
        StringBuilder builder = new StringBuilder();
        builder.append(n).append("\n");
        for (int i = 0; i < n; i++) {
            builder.append(min + (int)(Math.random() * ((max - min) + 1))).append(" ");
        }
        builder.deleteCharAt(builder.length() - 1);
        builder.append("\n");
        builder.append(k).append("\n");
        for (int i = 0; i < k; i++) {
            int first = 0 + (int)(Math.random() * ((n-1 - 0) + 1));
            int second = 0 + (int)(Math.random() * ((n-1 - 0) + 1));
            builder.append(Math.min(first,second)).append(" ").append(Math.max(first,second)).append("\n");
        }
        return builder.toString();
    }
}
