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
        range = new Range(-5, 5);
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
        Range afterRange = new Range(10, 15);
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

    @Test
    @DisplayName("isAfter will throw NPE when argument is null")
    void testThatIsAfterFailsOnNullArgument() {
        assertThrows(NullPointerException.class, () -> range.isAfter(null));
    }

    @Test
    @DisplayName("isAfter will return true when the argument Range is before")
    void testThatIsAfterReturnsTrueOnBeforeRange() {
        Range beforeRange = new Range(-10, -8);
        assertThat(range.isAfter(beforeRange), is(true));
    }

    @Test
    @DisplayName("isAfter will return false when the argument Range is after")
    void testThatIsAfterReturnsFalseOnAfterArgument() {
        Range afterRange = new Range(6, 7);
        assertThat(range.isAfter(afterRange), is(false));
    }

    @Test
    @DisplayName("isAfter will return false when Ranges have some intersections")
    void testThatIsAfterReturnsFalseOnIntersectedArgument() {
        Range intersectRange = new Range(-10, 0);
        assertThat(range.isAfter(intersectRange), is(false));
    }

    @Test
    @DisplayName("isConcurrent will throw NPE when argument is null")
    void testThatIsConcurrentFailsOnNullArgument() {
        assertThrows(NullPointerException.class, () -> range.isConcurrent(null));
    }

    @Test
    @DisplayName("isConcurrent will return true when Ranges have some intersections")
    void testThatIsConcurrentReturnsTrueOnIntersectedArgument() {
        Range concurrentRange = new Range(0, 2);
        assertThat(range.isConcurrent(concurrentRange), is(true));
    }

    @Test
    @DisplayName("isConcurent will return false when the argument Range is before")
    void testThatIsConcurrentReturnsFalseOnBeforeArgument() {
        Range beforeRange = new Range(-20,-10);
        assertThat(range.isConcurrent(beforeRange), is(false));
    }


    @Test
    @DisplayName("isConcurent will return false when the argument Range is after")
    void testThatIsConcurrentReturnsFalseOnAfterArgument() {
        Range afterRange = new Range(10,20);
        assertThat(range.isConcurrent(afterRange), is(false));
    }

}