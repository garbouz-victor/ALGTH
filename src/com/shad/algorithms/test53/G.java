package com.shad.algorithms.test53;

public class G {
    public static void main(String... args) {
        for (int n = 1; n < 10; n++) {
            double M = 0.0;
            int all = 0;
            for (int i = 0; i <= n; i++) {
                for (int j = i; j <= n; j++) {
//                    if (i != j) {
//                    System.out.println(i + " " + j);
                    all++;
//                    }
                }
            }
            for (int i = 0; i <= n; i++) {
                for (int j = i; j <= n; j++) {
//                    if (i != j) {
                    M += Math.abs(j-i)*1.0/all;
//                    }
                }
            }
            if (M*3==8) {
                System.out.println("n = " + n);
                return;
            }
        }
    }
}
