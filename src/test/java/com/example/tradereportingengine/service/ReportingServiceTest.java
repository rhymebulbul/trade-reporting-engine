package com.example.tradereportingengine.service;

import com.example.tradereportingengine.service.specifications.AnagramChecker;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ReportingServiceTest {

    private final AnagramChecker anagramChecker = new AnagramChecker();

    @Test
    void testAreAnagrams_True() {
        assertTrue(anagramChecker.areAnagrams("listen", "silent"));
        assertTrue(anagramChecker.areAnagrams("triangle", "integral"));
        assertTrue(anagramChecker.areAnagrams("evil", "vile"));
        assertTrue(anagramChecker.areAnagrams("anagram", "nagaram"));
    }

    @Test
    void testAreAnagrams_False() {
        assertFalse(anagramChecker.areAnagrams("hello", "world"));
        assertFalse(anagramChecker.areAnagrams("java", "spring"));
    }

    @Test
    void testAreAnagrams_DifferentLengths() {
        assertFalse(anagramChecker.areAnagrams("abc", "abcd"));
        assertFalse(anagramChecker.areAnagrams("test", "testing"));
    }

    @Test
    void testAreAnagrams_EmptyStrings() {
        assertTrue(anagramChecker.areAnagrams("", ""));
    }

    @Test
    void testAreAnagrams_CaseInsensitive() {
        assertTrue(anagramChecker.areAnagrams("Listen", "Silent"));
        assertTrue(anagramChecker.areAnagrams("Triangle", "Integral"));
    }
}