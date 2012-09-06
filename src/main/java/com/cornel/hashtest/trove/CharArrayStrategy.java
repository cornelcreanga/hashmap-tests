package com.cornel.hashtest.trove;

import gnu.trove.strategy.HashingStrategy;

public class CharArrayStrategy implements HashingStrategy {
    public int computeHashCode(Object o) {
        char[] c = (char[]) o;
        // use the shift-add-xor class of string hashing functions
        // cf. Ramakrishna and Zobel, "Performance in Practice
        // of String Hashing Functions"
        int h = 31; // seed chosen at random
        for (int i = 0; i < c.length; i++) { // could skip invariants
            h = h ^ ((h << 5) + (h >> 2) + c[i]); // L=5, R=2 works well for ASCII input
        }
        return h;
    }

    public boolean equals(Object o1, Object o2) {
        char[] c1 = (char[]) o1;
        char[] c2 = (char[]) o2;
        if (c1.length != c2.length) { // could drop this check for fixed-length keys
            return false;
        }
        for (int i = 0, len = c1.length; i < len; i++) { // could skip invariants
            if (c1[i] != c2[i]) {
                return false;
            }
        }
        return true;
    }
}
