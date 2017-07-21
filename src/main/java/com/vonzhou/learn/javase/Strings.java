package com.vonzhou.learn.javase;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * @version 2017/7/13.
 */
public class Strings {
    public static void main(String[] args) {
        System.out.println(String.format("%s\n%s", "line 1", "line2"));
        System.out.println(String.format("%s%n%s", "line 1", "line2"));

        Set<String> target = new HashSet<>();
        target.add("aa");
        Set<String> cond = new HashSet<>(Arrays.asList(""));
        System.out.println(target.containsAll(cond));
    }
}
