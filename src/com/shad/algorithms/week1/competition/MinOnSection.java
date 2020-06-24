package com.shad.algorithms.week1.competition;

import java.util.ArrayList;
import java.util.LinkedList;

public class MinOnSection {
    public static void main(String... args) {

    }

    class Pair {
        int position;
        int value;

        public Pair(int position, int value) {
            this.position = position;
            this.value = value;
        }
    }

    int[] array;
    LinkedList<Pair> deq;
    int n;
    int k;
    int currentR;
    int currentL;

    public MinOnSection(int[] a, int n, int k) {
        deq = new LinkedList<>();
        this.array = a;
        this.n = n;
        this.k = k;

    }

    void moveWindow() {
        if (!deq.isEmpty()) {

        }
    }

//    int minOnSection(int l, int r) {
//
//    }
}
