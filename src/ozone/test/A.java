package ozone.test;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.HashSet;
import java.util.Scanner;

public class A {

    public static void main(String... args) throws FileNotFoundException {
        Scanner scanner = new Scanner(
                new FileInputStream("input-201.txt"));
        PrintWriter writer = new PrintWriter("input-201.a.txt");
        HashSet<Long> set = new HashSet<>();
        while (scanner.hasNext()) {
            long val = scanner.nextLong();
            if (set.contains(val)) {
                set.remove(val);
            } else {
                set.add(val);
            }
        }
        writer.print(set.iterator().next());
        writer.close();
    }
}
