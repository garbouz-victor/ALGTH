package com.shad.algorithms.week4.competitions;

import java.util.HashMap;
import java.util.Scanner;

public class E {

    private static HashMap<Integer, Answer> answers = new HashMap<>();

    public static void main(String... args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        answers.put(1, new Answer(0, "1"));
        for (int i = 1; i <= n; i++) {
            if (answers.containsKey(i)) {
                Answer ans = answers.get(i);
                if (i+1 <= n) {
                    updateAnswer(new Answer(ans.val + 1, ans.answer + " " + (i+1)), i+1);
                }
                if (i*2 <= n) {
                    updateAnswer(new Answer(ans.val + 1, ans.answer + " " + (i*2)), i*2);
                }
                if (i*3 <= n) {
                    updateAnswer(new Answer(ans.val + 1, ans.answer + " " + (i*3)), i*3);
                }
            } else {
               throw new RuntimeException("something goes wrong!");
            }
            answers.remove(i-1);
        }
        System.out.println(answers.get(n));
    }

    private static void updateAnswer(Answer update, int position) {
        if (!answers.containsKey(position)) {
            answers.put(position, update);
        } else {
            Answer current = answers.get(position);
            if (current.val > update.val) {
                answers.put(position, update);
            }
        }
    }

    private static class Answer {
        int val;
        String answer;

        public Answer(int val, String answer) {
            this.val = val;
            this.answer = answer;
        }

        @Override
        public String toString() {
            return val + "\n" + answer;
        }
    }

    private static Answer getAnswer(int val) {
        if (val == 1) {
            return new Answer(0, "1");
        }
        if (answers.containsKey(val)) {
            return answers.get(val);
        } else {
            Answer val1 = getAnswer(val-1);
            val1.val = val1.val + 1;
            val1.answer = val1.answer + " " + val;
            Answer val2 = new Answer(Integer.MAX_VALUE, "");
            if (val%2 == 0) {
                val2 = getAnswer(val/2);
                val2.val = val2.val + 1;
                val2.answer = val2.answer + " " + val;
            }
            Answer val3 = new Answer(Integer.MAX_VALUE, "");
            if (val%3 == 0) {
                val3 = getAnswer(val/3);
                val3.val = val3.val + 1;
                val3.answer = val3.answer + " " + val;
            }
            Answer ans = null;
            if (val1.val < val2.val) {
                if (val1.val < val3.val) {
                    ans = val1;
                } else {
                    ans = val3;
                }
            } else if (val2.val < val3.val) {
                ans = val2;
            } else {
                ans = val3;
            }
            answers.put(val, ans);
            return ans;
        }
    }
}
