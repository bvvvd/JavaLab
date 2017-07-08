package com.epam.java.lab.tdd;

import java.util.Iterator;
import java.util.List;
import java.util.Objects;

public class Range {

    private long lowerBound;
    private long upperBound;

    public Range(long lowerBound, long upperBound) {
        validateArguments(lowerBound, upperBound);
        
        this.lowerBound = lowerBound;
        this.upperBound = upperBound;
    }

    private void validateArguments(long lowerBound, long upperBound) {
        if (lowerBound > upperBound) {
            throw new IllegalArgumentException();
        }
    }

    boolean isBefore(Range otherRange) {
        Objects.requireNonNull(otherRange);

        return upperBound < otherRange.lowerBound;
    }

    boolean isAfter(Range otherRange) {
        return false;
    }

    boolean isConcurrent(Range otherRange) {
        return false;
    }

    long getLowerBound() {
        return 0;
    }

    long getUpperBound() {
        return 0;
    }

    boolean contains(long value) {
        return false;
    }

    List<Long> asList() {
        return null;
    }

    Iterator<Long> asIterator() {
        return null;
    }
}
