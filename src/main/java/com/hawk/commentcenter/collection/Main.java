package com.hawk.commentcenter.collection;

import java.util.*;

/**
 * 测试hashset、hashmap、hashtable存储元素特性
 *
 * @author junxiong.lang
 * @date 2016/11/29 15:11
 */
public class Main {
    private static Set<String> set = new HashSet<>();
    private static Map<String, Object> map = new HashMap<>();
    private static Map<String, Object> table = new Hashtable<>();

    public static void main(String[] args) {
        testSet();
        testMap();
        testTable();
    }

    private static void testSet() {
        String val1 = new String("ljx");
        String val2 = new String("ljx");

        set.add(val1);
        set.add(val2);
        set.add(null);
        set.add(null);

        System.out.println(set);
    }

    private static void testMap() {
        String key1 = new String("ljx");
        String key2 = new String("ljx");
        Integer value1 = 110;
        Integer value2 = 111;

        System.out.println(map.put(key1, value1));
        System.out.println(map.put(key2, value2));      // the key compares by 'reference' or 'value'.
        System.out.println(map.put(null, null));

        System.out.println(map);
    }

    private static void testTable() {
        String key1 = new String("ljx");
        String key2 = new String("ljx");
        Integer value1 = 110;
        Integer value2 = 111;

        System.out.println(table.put(key1, value1));
        System.out.println(table.put(key2, value2));
//        System.out.println(table.put(null, null));      // runtime exception. hashtable can not store an entry like <null,null>
//        System.out.println(table.put(null, 2));         // runtime exception cause the key is null
//        System.out.println(table.put("2", null));       // runtime exception cause the value is null

        System.out.println(table);
    }

}
