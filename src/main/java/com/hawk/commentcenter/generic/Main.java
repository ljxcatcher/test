package com.hawk.commentcenter.generic;

import java.util.HashMap;
import java.util.Map;

/**
 * @author junxiong.lang
 * @date 2016/12/6 18:59
 */
public class Main {
    public static void main(String[] args) {
        testPrintMap();
    }

    private static void testPrintMap() {
        Map<String, Object> map = new HashMap<>();
        map.put("ljx", "good");
        map.put("das", "well");

        printMap(map);
    }

    private static void printMap(Map<?, ?> map) {
        for (Object o : map.keySet()) {
            System.out.println(o + "=" + map.get(o));
        }
    }
}
