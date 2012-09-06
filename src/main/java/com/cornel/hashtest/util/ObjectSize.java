package com.cornel.hashtest.util;


import org.github.jamm.MemoryMeter;

import java.util.ArrayList;
import java.util.HashMap;

public class ObjectSize {

    public static void main(String[] args) {
        MemoryMeter meter = new MemoryMeter();

        System.out.println("Object:" + meter.measureDeep(new Object()));
        System.out.println("Integer:" + meter.measureDeep(new Integer(234321)));
        System.out.println("Long:" + meter.measureDeep(new Long(54353l)));
        System.out.println("Double:" + meter.measureDeep(new Double(54353l)));
        System.out.println("ArrayList:" + meter.measureDeep(new ArrayList()));
        System.out.println("HashMap:" + meter.measureDeep(new HashMap()));
        System.out.println("16 elements byte array:" + meter.measureDeep(new byte[10]));
        System.out.println("16 elements int array:" + meter.measureDeep(new int[10]));
        System.out.println("16 elements Object array:" + meter.measureDeep(new Object[10]));
        System.out.println("4 char string:" + meter.measureDeep("tiny"));
        System.out.println("20 char string:" + meter.measureDeep("20 I'm a small string"));
        System.out.println("100 char string:" + meter.measureDeep("100 I'm a longer string la la la la la la la la la la la la la la la la la la la la la la la la la ."));


    }
}
