package com.shad.algorithms.exam2;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Scanner;

public class F {

    private static class Solver {
        Scanner scanner;
        LinkedList<String> stack;
        String line;
        public Solver(InputStream is) {
            scanner = new Scanner(is);
            stack = new LinkedList<>();
            line = scanner.nextLine();
        }

        public long solve() {
            int cursor = 0;
            long answer = 0;
            while (cursor < line.length()) {
                char ch = line.charAt(cursor);
                ArrayList<Character> digits = new ArrayList<>();
                if (Character.isDigit(ch)) {
                    while (Character.isDigit(ch) && cursor < line.length()) {
                        digits.add(ch);
                        cursor++;
                        ch = line.charAt(cursor);
                    }
                    if (cursor < line.length()) {
                        char nextChar = line.charAt(cursor);
                        if ('[' == nextChar) {
                            String digit = "";
                            for(Character d : digits) {
                                digit += d;
                            }
                            if (!stack.isEmpty()) {
                                String valInStack = stack.pop();
                                if (!valInStack.equals("0")) {
                                    stack.push(valInStack);
                                }
                            }
                            stack.push(String.valueOf(digit)+"m");
                            stack.push(String.valueOf(0));
                        } else if (']' == nextChar) {
                            int val = Integer.valueOf(stack.pop());
                            val += digits.size();
                            String op = stack.pop();
                            if (op.contains("m")) {
                                int mult = Integer.valueOf(op.replaceFirst("m",""));
                                stack.push(String.valueOf(val*mult));
                            }
                        } else {
                            if (stack.isEmpty()) {
                                stack.push(String.valueOf(digits.size()));
                            } else {
                                int val = Integer.valueOf(stack.pop());
                                stack.push(String.valueOf(val+digits.size()));
                                continue;
                            }
                        }
                    } else {
                        break;
                    }
                } else if(']' == ch) {
                    int val1I = Integer.valueOf(stack.pop());
                    String val2S = stack.pop();
                    if (val2S.contains("m")) {
                        int val2I = Integer.valueOf(val2S.replaceFirst("m", ""));
                        stack.push(String.valueOf(val1I * val2I));
                    } else {
                        int val2I = Integer.valueOf(val2S);
                        stack.push(String.valueOf(val1I + val2I));
                    }
                    digits = new ArrayList<>();
                    while(true) {
                        String str = stack.pop();
                    }
                }  else {
                    if (stack.isEmpty()) {
                        stack.push(String.valueOf(1));
                    } else {
                        int val = Integer.valueOf(stack.pop());
                        stack.push(String.valueOf(val+1));
                    }
                }
                cursor++;
            }
            while (!stack.isEmpty()) {
                String val1 = stack.pop();
                if (val1.contains("m")) {
                    throw new IllegalArgumentException();
                }
                if (stack.isEmpty()) {
                    answer += Integer.valueOf(val1);
                    break;
                } else {
                    String val2 = stack.pop();
                    if (val2.contains("m")) {
                        int val1I = Integer.valueOf(val1);
                        int val2I = Integer.valueOf(val2.replaceFirst("m", ""));
                        stack.push(String.valueOf(val1I * val2I));
                    } else {
                        int val1I = Integer.valueOf(val1);
                        int val2I = Integer.valueOf(val2);
                        stack.push(String.valueOf(val1I + val2I));
                    }
                }
            }
            return answer;
        }
    }

    public static void main(String... args) {
        Solver s = new Solver(System.in);
        System.out.println(s.solve());
    }
}
