package com.shad.algorithms.test40;

public class G {
    public static void main(String... args) {
        for (int n = 1; n < 7; n++) {
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
            if (M*3==5) {
                System.out.println("n = " + n);
            }
        }
    }
}
