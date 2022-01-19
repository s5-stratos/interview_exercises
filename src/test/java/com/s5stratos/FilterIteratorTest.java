package com.s5stratos;

import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.function.Predicate;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Unit test for simple App.
 */
public class FilterIteratorTest 
{

    private static final Predicate<String> predicate = (String s) -> !s.isBlank();

    /**
     * Rigorous Test :-)
     */
    @Test
    public void filterOfEmptyIsEmpty()
    {
        Iterator<String> underlying = Collections.<String>emptyList().iterator();
        FilterIterator<String> filtered = new FilterIterator<>(predicate, underlying);
        assertExhausted(filtered);
    }

    @Test
    public void filterWorksAtAll() {
        Iterator<String> underlying = Arrays.asList(
            "a",
            "",
            "b",
            "  ",
            "c",
            ""
        ).iterator();
        FilterIterator<String> filtered = new FilterIterator<>(predicate, underlying);
        assertNext(filtered, "a");
        assertNext(filtered, "b");
        assertNext(filtered, "c");
        assertExhausted(filtered);
    }

    @Test
    public void filterCanSkipMultiples() {
        Iterator<String> underlying = Arrays.asList(
            "a",
            "",
            "",
            "",
            "b"
        ).iterator();
        FilterIterator<String> filtered = new FilterIterator<>(predicate, underlying);
        assertNext(filtered, "a");
        assertNext(filtered, "b");
        assertExhausted(filtered);
    }

    @Test
    public void filterCanSkipAtStart() {
        Iterator<String> underlying = Arrays.asList(
            "",
            "",
            "a",
            "b" 
        ).iterator();

        FilterIterator<String> filtered = new FilterIterator<>(predicate, underlying);
        assertNext(filtered, "a");
        assertNext(filtered, "b");
        assertExhausted(filtered);
    }

    @Test
    public void filterCanSkipAtEnd() {
        Iterator<String> underlying = Arrays.asList(
            "a",
            "b",
            "",
            ""
        ).iterator();

        FilterIterator<String> filtered = new FilterIterator<>(predicate, underlying);
        assertNext(filtered, "a");
        assertNext(filtered, "b");
        assertExhausted(filtered);
    }

    @Test
    public void filterCanSkipRandomInternal() {
        Iterator<String> underlying = Arrays.asList(
            "",
            "a",
            "",
            "",
            "b",
            "",
            "c",
            "",
            "",
            "",
            "d",
            ""
        ).iterator();
        FilterIterator<String> filtered = new FilterIterator<>(predicate, underlying);
        assertNext(filtered, "a");
        assertNext(filtered, "b");
        assertNext(filtered, "c");
        assertNext(filtered, "d");
        assertExhausted(filtered);
    }

    public static <T> void assertExhausted(Iterator<T> filtered) {
        assertFalse(filtered.hasNext());
        try {
            filtered.next();
            throw new RuntimeException("should have thrown");
        } catch (NoSuchElementException e) {

        }
    }

    public static <T> void assertNext(Iterator<T> filtered, T next) {
        assertTrue(filtered.hasNext());
        assertEquals(next, filtered.next());
    }
}
