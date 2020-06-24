package com.shad.algorithms.test72;

public class C {

    public static double test = -0.41614684;

    public static void main(String... args) {
        double l = 0.0;
        double r = Integer.MAX_VALUE;
        double mid = r;
        for (int i = 0; i < 500; i++) {
            mid = l + (r-l)/2.0;
            if (check(mid)) {
                l = mid;
            } else {
                r = mid;
            }
        }
        System.out.println(r);
//        double x = getMin(0.499, 0.00001);
//        System.out.println("x=" + x + " " + function(x));
    }

    public static boolean check(double lambda) {
        double min = getMin(lambda, 0.00000001);
//        System.out.println(function(min));
        if (min!=min) {
            return false;
        }
        if (Math.abs(test - function(min)) > 0.00000001) {
            return false;
        } else {
            return true;
        }
    }

    public static double getMin(double lambda, double e) {
        double x = 2.0;
        double previousX;
        for (int i = 0; i < 1000000; i++) {
            previousX = x;
            x = x - lambda * grad(x);
            if (Math.abs(x - previousX) > e && Math.abs(grad(x)) > e) {
                continue;
            } else {
                break;
            }
        }
        return x;
    }

    //4
    public static double function(double x) {
        return Math.pow(x,4)/4.0 + Math.cos(2.0);
    }

    public static double grad(double x) {
        return Math.pow(x,3);
    }
}

