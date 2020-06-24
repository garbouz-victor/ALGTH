package ozone.test;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.HashSet;
import java.util.Scanner;

public class F_upd {
    public static void main(String... args) throws FileNotFoundException {
        Scanner scanner = new Scanner(new FileInputStream("input.txt"));
        PrintWriter writer = new PrintWriter(new FileOutputStream("output.txt"));
        int target = scanner.nextInt();
        HashSet<Integer> set = new HashSet<>();
        while (scanner.hasNext()) {
            int val = scanner.nextInt();
            if (val > target) {
                continue;
            } else {
                int diff = target - val;
                if (set.contains(diff)) {
                    writer.print(1);
                    writer.close();
                    return;
                } else {
                    set.add(val);
                }
            }
        }
        writer.print(0);
        writer.close();
    }
}
