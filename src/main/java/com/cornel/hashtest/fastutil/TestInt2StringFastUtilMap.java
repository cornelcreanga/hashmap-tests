package com.cornel.hashtest.fastutil;

import it.unimi.dsi.fastutil.ints.Int2ObjectOpenHashMap;
import org.github.jamm.MemoryMeter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * suggested args: -Xms3000m -Xmx3000m -javaagent:<your location></>/.m2/repository/com/github/stephenc/jamm/0.2.5/jamm-0.2.5.jar
 */
public class TestInt2StringFastUtilMap {


    public static final int SET_SIZE = 1_000_000;

    public static final List<String> dataset = buildData();

    public static List<String> buildData() {
        List<String> result = new ArrayList<String>(SET_SIZE);

        for (int i = 0; i < SET_SIZE; i++) {
            int len = 10 + (int) (Math.random() * 15);
            StringBuilder sb = new StringBuilder(len);
            for (int j = 0; j < len; j++) {
                sb.append((char) (30 + Math.random() * 65));
            }
            result.add(sb.toString());
        }
        return result;
    }

    public void buildFastUtilMap() {
        long t1 = System.currentTimeMillis();
        Int2ObjectOpenHashMap map = new Int2ObjectOpenHashMap(SET_SIZE, 0.5f);
        for (int i = SET_SIZE; i-- > 0; )
            map.put(i, dataset.get(i));
        long t2 = System.currentTimeMillis();
        System.out.println("Build FastUtil map:" + (t2 - t1) + " ms.");
        MemoryMeter meter = new MemoryMeter();
        System.out.println("FastUtil:" + meter.measureDeep(map) / (1024 * 1024) + " Mbytes");

        t1 = System.currentTimeMillis();
        for (int i = SET_SIZE; i-- > 0; )
            map.get(i);

        t2 = System.currentTimeMillis();
        System.out.println("get FastUtil:" + (t2 - t1));


    }

    public void buildJdkMap() {
        long t1 = System.currentTimeMillis();
        Map<Integer, String> map = new HashMap<Integer, String>(SET_SIZE, 0.5f);
        for (int i = SET_SIZE; i-- > 0; )
            map.put(i, dataset.get(i));
        long t2 = System.currentTimeMillis();
        System.out.println("Build JDK map:" + (t2 - t1) + " ms.");
        MemoryMeter meter = new MemoryMeter();
        System.out.println("jdk:" + meter.measureDeep(map) / (1024 * 1024) + " Mbytes");

        t1 = System.currentTimeMillis();
        for (int i = SET_SIZE; i-- > 0; )
            map.get(i);

        t2 = System.currentTimeMillis();
        System.out.println("get jdk:" + (t2 - t1));

    }

    public static void main(String[] args) throws Exception {
        TestInt2StringFastUtilMap map = new TestInt2StringFastUtilMap();
        map.buildJdkMap();
        System.gc();
        map.buildFastUtilMap();

    }
}
