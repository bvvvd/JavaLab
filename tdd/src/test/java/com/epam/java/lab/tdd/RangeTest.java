package com.epam.java.lab.tdd;

import static org.hamcrest.core.Is.is;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

class RangeTest {

    private Range range;

    @BeforeEach
    void before() {
        range = new Range(-5,5);
    }

    @Test
    @DisplayName("constructor will throw IllegalArgumentException in case of trying to create Range object with upperBound less than lowerBound")
    void testThatWeCanNotCreateInconsistentRange() {
        assertThrows(IllegalArgumentException.class, () -> new Range(5, -5));
    }

    @Test
    @DisplayName("isBefore will throw NPE when the argument is null")
    void testThatIsBeforeFailsOnNullArgument() {
        assertThrows(NullPointerException.class, () -> range.isBefore(null));
    }

    @Test
    @DisplayName("isBefore will return true when the argument Range is after")
    void testThatIsBeforeReturnsTrueOnAfterRange() {
        Range afterRange = new Range(10,15);
        assertThat(range.isBefore(afterRange), is(true));
    }

    @Test
    @DisplayName("isBefore will return false when the argument Range is before")
    void testThatIsBeforeReturnsFalseOnBeforeArgument() {
        Range beforeRange = new Range(-10, -9);
        assertThat(range.isBefore(beforeRange), is(false));
    }

    @Test
    @DisplayName("isBefore will return false when Ranges have some intersections")
    void testThatIsBeforeReturnsFalseOnIntersectedArgument() {
        Range intersectRange = new Range(-10, 0);
        assertThat(range.isBefore(intersectRange), is(false));
    }
}