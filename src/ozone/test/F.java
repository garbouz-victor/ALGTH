package ozone.test;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class F {
    private static List<Integer> array;
    public static void main(String... args) throws FileNotFoundException {
        Scanner scanner = new Scanner(new FileInputStream("input.txt"));
        PrintWriter writer = new PrintWriter(new FileOutputStream("output.txt"));
        int target = scanner.nextInt();
        array = new ArrayList<>();
        array.add(-1);
        while (scanner.hasNext()) {
            int val = scanner.nextInt();
            if (val > target) {
                continue;
            }
            array.add(val);
        }
        if (array.size() >= 3) {
            Collections.sort(array);
            int rightCursor = array.size() - 1;
            while (rightCursor > 0 && array.get(rightCursor) > target) {
                rightCursor--;
            }
            int leftCursor = 1;
            int currentResult;
            while (leftCursor < rightCursor) {
                currentResult = array.get(leftCursor) + array.get(rightCursor);
                if (target < currentResult) {
                    rightCursor--;
                } else if (target > currentResult) {
                    leftCursor++;
                } else {
                    writer.print(1);
                    writer.close();
                    return;
                }
            }
        }
        writer.print(0);
        writer.close();
    }

    private static int find(int sl, int sr, int x) {
        if (sl == sr) {
            if (array.get(sl) != x) {
                return -1;
            }
        } else if (sl < sr) {
            int m = (sl+sr)/2;
            if (array.get(m) > x) {
                return find(sl, m, x);
            } else if (array.get(m) == x){
                return m;
            } else {
                return find(m+1, sr, x);
            }
        }
        return -1;
    }
}
