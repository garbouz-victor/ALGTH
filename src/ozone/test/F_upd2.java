package ozone.test;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.HashSet;

public class F_upd2 {
    static int result = 0;
    static int nextInt;

    public static void main(String[] args) {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream("input.txt"), StandardCharsets.UTF_8));
             BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("output.txt"), StandardCharsets.UTF_8))) {
            setResult(br);
            bw.write(String.valueOf(result));
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }


    private static void setResult(BufferedReader br) throws IOException {
        int a;
        int number;
        int target = 0;
        boolean isFirst = true;
        HashSet<Integer> set = new HashSet<>();
        while ((a = getNextNumber(br)) != -1) {
            if (IsDigit(a)) {
                number = a - '0';
                while (IsDigit(getNextNumber(br))) {
                    number = number * 10 + (nextInt - '0');
                }
                if (isFirst) {
                    target = number;
                }
                if (target < number) {
                    continue;
                }
                if (set.contains(target - number)) {
                    result = 1;
                    break;

                }
                if (!isFirst) {
                    set.add(number);
                }
                isFirst = false;
            }
        }
    }

    private static boolean IsDigit(int c) {
        return c >= '0' && c <= '9';
    }

    private static int getNextNumber(BufferedReader br) throws IOException {
        nextInt = br.read();
        return nextInt;
    }
}
