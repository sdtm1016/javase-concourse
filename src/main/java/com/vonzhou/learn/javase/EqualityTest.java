package com.vonzhou.learn.javase;

/**
 * @version 2017/7/11.
 */
public class EqualityTest {
    public static void main(String[] args) {
        Byte b1 = new Byte("1");
        Byte b2 = new Byte("1");

        System.out.println(b1 == b2); // false
    }
}
