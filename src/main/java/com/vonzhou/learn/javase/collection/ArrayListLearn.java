package com.vonzhou.learn.javase.collection;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by vonzhou on 2017/6/11.
 */
public class ArrayListLearn {
    public static void main(String[] args) {
        test1();
    }

    /**
     * Exception in thread "main" java.lang.ClassCastException: [Ljava.lang.Object; cannot be cast to [Ljava.lang.String;
     */
    public static void test2() {
        List<String> list = new ArrayList<>();
        list.add("dog");

        Object[] oa = list.toArray();//其实此时数组类型已为 [Ljava.lang.Object;
        System.out.println(((String) oa[0]).length());
        String[] sa = (String[]) oa; // 所以此时会抛出 ClassCastException
    }

    public static void test1() {
        List<String> list = Arrays.asList("hello");

        System.out.println(list.toArray().getClass());

        Object[] oa = list.toArray();
        String[] sa = (String[]) oa;// ok
    }


}
