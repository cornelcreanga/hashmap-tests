package com.cornel.hashtest.trove;

import gnu.trove.list.array.TLongArrayList;
import org.github.jamm.MemoryMeter;

import java.util.ArrayList;
import java.util.List;

/**
 * suggested args: -Xms3000m -Xmx3000m -javaagent:<your location></>/.m2/repository/com/github/stephenc/jamm/0.2.5/jamm-0.2.5.jar
 */
public class TestLongList {

    public static final int SET_SIZE = 1_000_000;

    public static final long[] dataset = buildData();

    public static long[] buildData() {
        long[] result = new long[SET_SIZE];
        for (int i = 0; i < SET_SIZE; i++)
            result[i] = (long) (Math.random() * Integer.MAX_VALUE);
        return result;
    }

    public void buildTroveMap() {
        long t1 = System.currentTimeMillis();
        TLongArrayList list = new TLongArrayList(SET_SIZE);
        for (int i = SET_SIZE; i-- > 0; )
            list.add(dataset[i]);
        long t2 = System.currentTimeMillis();
        System.out.println("Building Trove list:" + (t2 - t1) + " ms.");
        MemoryMeter meter = new MemoryMeter();
        System.out.println("trove:" + meter.measureDeep(list) / (1024 * 1024) + " Mbytes");
    }

    public void buildJdkMap() {
        long t1 = System.currentTimeMillis();
        List list = new ArrayList<Long>(SET_SIZE);
        for (int i = SET_SIZE; i-- > 0; )
            list.add(dataset[i]);
        long t2 = System.currentTimeMillis();
        System.out.println("Building JDK list:" + (t2 - t1) + " ms.");
        MemoryMeter meter = new MemoryMeter();
        System.out.println("trove:" + meter.measureDeep(list) / (1024 * 1024) + " Mbytes");
    }

    public static void main(String[] args) throws Exception {
        TestLongList map = new TestLongList();
        map.buildJdkMap();
        System.gc();
        map.buildTroveMap();

    }

}
