package com.hllog.kmp;

/**
 * @author hllog
 * @create 2022-08-19 21:53
 */
public class ViolenceMatch {
    public static void main(String[] args) {
        String str1 = "abcabcabcabcd";
        String str2 = "abcd";
        int index = violenceMatch(str1, str2);
        System.out.println(index);
    }

    public static int violenceMatch(String str1, String str2) {
        char[] s1 = str1.toCharArray();
        char[] s2 = str2.toCharArray();
        int len1 = s1.length;
        int len2 = s2.length;

        int i = 0;
        int j = 0;
        while (i < len1 && j < len2) {
            if (s1[i] == s2[j]) {
                i++;
                j++;
            } else {
                i = i - (j - 1);
                j = 0;
            }
        }
        if (j == len2) {
            return i - j;
        } else {
            return -1;
        }
    }
}
