package com.example.tradereportingengine.service.specifications;

public class AnagramChecker {
    /**
     * Overall time complexity: O(n)
     * Overall space complexity: O(1)
     */
    public boolean areAnagrams(String str1, String str2) {


        // If the lengths of the strings are not equal, they cannot be anagrams
        if (str1.length() != str2.length()) {
            return false;
        }

        // Convert both strings to lowercase
        str1 = str1.toLowerCase();
        str2 = str2.toLowerCase();

        // Create a frequency array for characters
        int[] charCount = new int[26]; // Assuming the input strings contain only lowercase letters

        // Count the frequency of each character in str1 and decrement for str2
        for (int i = 0; i < str1.length(); i++) {
            if (str1.charAt(i) >= 'a' && str1.charAt(i) <= 'z') {
                charCount[str1.charAt(i) - 'a']++;
            }
            if (str2.charAt(i) >= 'a' && str2.charAt(i) <= 'z') {
                charCount[str2.charAt(i) - 'a']--;
            }
        }

        // Check if all counts are zero
        for (int count : charCount) {
            if (count != 0) {
                return false;
            }
        }

        return true;
    }
}