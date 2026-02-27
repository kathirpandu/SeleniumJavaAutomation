package com.kathir.programs;

import io.cucumber.java.be.I;
import io.cucumber.java.sl.In;

import java.util.*;

public class InputPrograms {

    //###############################################################

    public static void findDuplicates(String str) {
        Map<Character, Integer> map = new HashMap<>();
        for (char ch : str.toCharArray())
            if (ch != ' ') map.put(ch, map.getOrDefault(ch, 0) + 1);
        System.out.println("Duplicate Character:");
        for (Map.Entry<Character, Integer> entry : map.entrySet())
            if (entry.getValue() > 1) System.out.println(entry.getKey() + "-->" + entry.getValue());
    }

    public static void reverseString(String input) {
        char[] chars = input.toCharArray();
        int left = 0;
        int right = input.length() - 1;
        while (left < right) {
            char temp = chars[left];
            chars[left] = chars[right];
            chars[right] = temp;
            right--;
            left++;
        }
        System.out.println("Reverse Word:");
        System.out.println(chars);
    }

    public static void isPalindrome(String s) {
        s = s.replaceAll("\\s+", "").toLowerCase();

        int i = 0, j = s.length() - 1;
        boolean palindrome = true;

        while (i < j) {
            if (s.charAt(i) != s.charAt(j)) {
                palindrome = false;
                break;
            }
            i++;
            j--;
        }

        if (palindrome)
            System.out.println(s + " is palindrome");
        else
            System.out.println(s + " is not palindrome");

    }

    public static void fizzBuzz(int n) {
        for (int i = 1; i <= n; i++) {
            if (i % 3 == 0 && i % 5 == 0)
                System.out.println("FizzBuzz");
            else if (i % 3 == 0)
                System.out.println("Fizz");
            else if (i % 5 == 0)
                System.out.println("Buzz");
            else
                System.out.println(i);
        }
    }

    public static void findLargest(int[] arr) {
        int max = Integer.MIN_VALUE, second = Integer.MIN_VALUE;
        for (int num : arr) {
            if (num > max) {
                second = max;
                max = num;
            } else if (num > second && num != max) {
                second = num;
            }
        }
        System.out.println("Max: " + max + ", Second Max: " + second);
    }

    public static void findLeast(int[] arr) {
        int min = Integer.MAX_VALUE, second = Integer.MAX_VALUE;
        for (int num : arr)
            if (num < min) {
                second = min;
                min = num;
            } else if (num < second && num != min) {
                second = num;

            }
        System.out.println("min: " + min + ", Second min: " + second);
    }

    public static void reverseWords(String s) {
        String[] words = s.trim().split("\\s+");
        StringBuilder sb = new StringBuilder();
        for (int i = words.length - 1; i >= 0; i--) {
            sb.append(words[i]).append(" ");
        }
        System.out.println("Reverse word is " + sb.toString().trim());

    }

    public static void fibonacci(int n) {
        int a = 0, b = 1;
        System.out.print(a + " " + b);
        for (int i = 2; i < n; i++) {
            int c = a + b;
            System.out.print(" " + c);
            a = b;
            b = c;
        }
    }

    public static void removeDuplicates(int[] arr) {
        System.out.println(Arrays.stream(arr).distinct().toArray());
    }


    public static void StringPermutation(String str) {

        Set<String> result = new HashSet<>();

        permute(str.toCharArray(), 0, result);

        for (String s : result) {
            System.out.println(s);
        }

        System.out.println("Total unique permutations: " + result.size());
    }

    static void permute(char[] arr, int index, Set<String> result) {

        if (index == arr.length) {
            result.add(new String(arr));
            return;
        }

        for (int i = index; i < arr.length; i++) {
            swap(arr, index, i);
            permute(arr, index + 1, result);
            swap(arr, index, i); // backtrack
        }
    }

    static void swap(char[] arr, int i, int j) {
        char temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    public static void isPrime(int n) {

        if (n <= 1) {
            System.out.println(n + " is not a prime number");
            return;
        }

        boolean isPrime = true;

        for (int i = 2; i <= Math.sqrt(n); i++) {
            if (n % i == 0) {
                isPrime = false;
                break;
            }
        }

        if (isPrime) {
            System.out.println(n + " is a prime number");
        } else {
            System.out.println(n + " is not a prime number");
        }
    }

    public static int sumDigits(int n) {
        int sum = 0;
        while (n > 0) {
            sum += n % 10;
            n /= 10;
        }
        return sum;
    }

    public static boolean isAnagram(String s1, String s2) {
        char[] a = s1.toCharArray();
        char[] b = s2.toCharArray();
        if (a.length != b.length) return false;
        Arrays.sort(a);
        Arrays.sort(b);
        return Arrays.equals(a, b);
    }


    public static int missingNumber(int[] arr, int n) {
        int total = n*(n+1)/2;
        int sum = Arrays.stream(arr).sum();
        return total - sum;
    }

//############################################################################

    public static int[] rotateLeft(int[] arr, int k) {
        k = k % arr.length;
        int[] result = new int[arr.length];
        System.arraycopy(arr, k, result, 0, arr.length - k);
        System.arraycopy(arr, 0, result, arr.length - k, k);
        return result;
    }

    public static void letterDigit(){
    String input = "!4@T#6s%1e*9t";

    String letters = input.replaceAll("[^A-Za-z]", "");
    String digits = input.replaceAll("[^0-9]", "");

    char[] letterArr = letters.toCharArray();
    char[] digitArr = digits.toCharArray();

        Arrays.sort(letterArr);
        Arrays.sort(digitArr);

    StringBuilder sb = new StringBuilder();

        for (int i = 0; i < letterArr.length; i++) {
        sb.append(Character.toUpperCase(letterArr[i]))
                .append(digitArr[i])
                .append(" ");
    }

        System.out.println(sb.toString().trim());
}


    public static void checkPermutation(int[] arr) {

        int sum = 0;

        // Step 1: Calculate total sum
        for (int num : arr) {
            sum += num;
        }

        System.out.println("Total Sum = " + sum);

        boolean found = false;

        // Step 2: Permutation traversal
        for (int i = 0; i < arr.length; i++) {

            for (int j = 0; j < arr.length; j++) {

                if (i == j) {
                    continue; // Skip same index only
                }

                int product = arr[i] * arr[j];

                System.out.println(i + " * " + j + " → "
                        + arr[i] + " * " + arr[j] + " = " + product);

                if (product == sum) {
                    System.out.println("Match Found at index "
                            + i + " and " + j);
                    found = true;
                }
            }
        }

        if (!found) {
            System.out.println("No matching permutation pair found.");
        }
    }


    public static void checkandSort(int[] arr){

        boolean isSorted = true;
        System.out.println("Before Sorting: " + Arrays.toString(arr));

        for(int i=0; i< arr.length-1; i++){
            if(arr[i]>arr[i+1]){
                isSorted= false;
                break;
            }
        }

       if (!isSorted) {
           Arrays.sort(arr);
           System.out.println("After Sorting: " + Arrays.toString(arr));


           for (int i = 0; i < arr.length - 1; i++) {
               if (arr[i] > arr[i + 1]) {
                   isSorted = false;

               }else {
                   isSorted = true;
               }
           }
       }
       if(isSorted){
           System.out.println("Sorted Array is validated: " + Arrays.toString(arr));
       }

    }


    public static void removeDuplicatesStepByStep(int[] arr) {

        List<Integer> list = new ArrayList<>();
        for (int num : arr) {
            list.add(num);
        }

        while (true) {

            Set<Integer> seen = new HashSet<>();
            boolean duplicateFound = false;

            for (int i = 0; i < list.size(); i++) {

                if (!seen.add(list.get(i))) {
                    System.out.println("Duplicate Found: " + list.get(i));
                    list.remove(i);
                    System.out.println("Array After Removal: " + list);
                    duplicateFound = true;
                    break;
                }
            }

            if (!duplicateFound) {
                break;
            }
        }
    }

    public static void findVowels(String input){

        Map<Character, Integer> map = new HashMap<>();
        for (char ch:input.toCharArray()){
            if (ch!=' '){
                map.put(ch, map.getOrDefault(ch,0)+1);
            }
        }
        System.out.println("Vowels Characters and count :");

       for(Map.Entry<Character, Integer> entry : map.entrySet()){
           if("aeiou".contains(entry.getKey().toString())){
               System.out.println("Vowel is :"+ entry.getKey()+"-->"+entry.getValue());
           }
       }

    }

    public static Character firstNonRepeatedCharm(String str) {
        if (str == null || str.isEmpty()) {
            return null;
        }

        Map<Character, Integer> map = new LinkedHashMap<>();

        // Count frequency
        for (char ch : str.toCharArray()) {
            map.put(ch, map.getOrDefault(ch, 0) + 1);
        }

        // Find first non-repeated
        for (Map.Entry<Character, Integer> entry : map.entrySet()) {
            if (entry.getValue() == 1) {
                return entry.getKey();
            }
        }

        return null; // if no unique character found
    }

    public static void findLongestWord(String sentence) {

        if (sentence == null || sentence.trim().isEmpty()) {
           System.out.println("String is empty");
        }else {

            String[] words = sentence.split("\\s+");
            String longestWord = "";

            for (String word : words) {
                if (word.length() > longestWord.length()) {
                    longestWord = word;
                }
            }
            System.out.println("longest word is :"+ longestWord);
        }
    }

    public static Character firstNonRepeatedChar(String str) {
        for (int i = 0; i < str.length(); i++) {
            char ch = str.charAt(i);
            if (str.indexOf(ch) == str.lastIndexOf(ch)) {
                return ch;
            }
        }
        return null;
    }
}



