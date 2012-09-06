package com.cornel.hashtest.trove;

import gnu.trove.map.hash.TIntIntHashMap;
import org.github.jamm.MemoryMeter;

import java.util.HashMap;
import java.util.Map;

/**
 * suggested args: -Xms3000m -Xmx3000m -javaagent:<your location></>/.m2/repository/com/github/stephenc/jamm/0.2.5/jamm-0.2.5.jar
 */
public class TestIntegersMap {

    public static final int SET_SIZE = 1_000_000;

    public static final int[] dataset = buildData();

    public static int[] buildData() {
        int[] result = new int[SET_SIZE];
        for (int i = 0; i < SET_SIZE; i++)
            result[i] = (int) (Math.random() * Integer.MAX_VALUE);
        return result;
    }

    public void buildTroveMap() {
        long t1 = System.currentTimeMillis();
        TIntIntHashMap map = new TIntIntHashMap(SET_SIZE, 0.9f);
        for (int i = SET_SIZE; i-- > 0; )
            map.put(i, dataset[i]);
        long t2 = System.currentTimeMillis();
        System.out.println("Build Trove map:" + (t2 - t1) + " ms.");
        MemoryMeter meter = new MemoryMeter();
        System.out.println("trove:" + meter.measureDeep(map) / (1024 * 1024) + " Mbytes");

        t1 = System.currentTimeMillis();
        for (int i = SET_SIZE; i-- > 0; )
            map.get(dataset[i]);

        t2 = System.currentTimeMillis();
        System.out.println("get trove:" + (t2 - t1));


    }

    public void buildJdkMap() {
        long t1 = System.currentTimeMillis();
        Map<Integer, Integer> map = new HashMap<Integer, Integer>(SET_SIZE, 0.9f);
        for (int i = SET_SIZE; i-- > 0; )
            map.put(i, dataset[i]);
        long t2 = System.currentTimeMillis();
        System.out.println("Build JDK map:" + (t2 - t1) + " ms.");
        MemoryMeter meter = new MemoryMeter();
        System.out.println("jdk:" + meter.measureDeep(map) / (1024 * 1024) + " Mbytes");

        t1 = System.currentTimeMillis();
        for (int i = SET_SIZE; i-- > 0; )
            map.get(dataset[i]);

        t2 = System.currentTimeMillis();
        System.out.println("get jdk:" + (t2 - t1));

    }


    public static void main(String[] args) throws Exception {
        TestIntegersMap map = new TestIntegersMap();
        map.buildJdkMap();
        System.gc();
        map.buildTroveMap();

    }
}
