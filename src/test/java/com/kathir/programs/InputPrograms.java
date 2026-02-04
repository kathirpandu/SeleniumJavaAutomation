package com.kathir.programs;

import io.cucumber.java.sl.In;

import java.util.HashMap;
import java.util.Map;

public class InputPrograms
{
    public static void findDuplicates(String str) {
       Map<Character, Integer> map = new HashMap<>();
       for(char ch:str.toCharArray())
           if (ch != ' ') map.put(ch, map.getOrDefault(ch, 0) + 1);
        System.out.println("Duplicate Character:");
       for(Map.Entry<Character, Integer> entry: map.entrySet())
           if (entry.getValue() > 1) System.out.println(entry.getKey() + "-->" + entry.getValue());
    }

    public static void reverseString(String input) {
        char[] chars = input.toCharArray();
        int left =0; int right =input.length()-1;
        while(left<right){
           char temp =chars[left];
            chars[left]=chars[right];
            chars[right]= temp;
            right--;
            left++;
        }
        System.out.println("Reverse Word:");
        System.out.println(chars);
    }

// Usage


}
