package com.s5stratos;

import java.util.Iterator;
import java.util.function.Predicate;

/**
 * Hello world!
 *
 */
public class FilterIterator<T> implements Iterator<T>
{
    public FilterIterator(Predicate<T> predicate, Iterator<T> underlying) 
    {
        
    }

    @Override
    public boolean hasNext() {
        return false;
    }

    @Override
    public T next() {
        return null;
    }
}
