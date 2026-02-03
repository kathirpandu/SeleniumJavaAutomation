package com.kathir.programs;

import java.util.HashMap;
import java.util.Map;

public class InputPrograms
{
    public static void findDuplicates(String str) {
        Map<Character, Integer> map = new HashMap<>();

        for (char ch : str.toCharArray()) {
            if (ch != ' ') {  // ignore spaces if needed
                map.put(ch, map.getOrDefault(ch, 0) + 1);
            }
        }

        System.out.println("Duplicate characters:");
        for (Map.Entry<Character, Integer> entry : map.entrySet()) {
            if (entry.getValue() > 1) {
                System.out.println(entry.getKey() + " -> " + entry.getValue());
            }
        }
    }
}
