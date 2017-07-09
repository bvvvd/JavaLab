package com.epam.java.lab.tdd;

import static org.hamcrest.core.Is.is;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.LongStream;
import java.util.stream.Stream;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsEqual.equalTo;
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
    @DisplayName("isConcurrent will return false when the argument Range is before")
    void testThatIsConcurrentReturnsFalseOnBeforeArgument() {
        Range beforeRange = new Range(-20, -10);
        assertThat(range.isConcurrent(beforeRange), is(false));
    }


    @Test
    @DisplayName("isConcurrent will return false when the argument Range is after")
    void testThatIsConcurrentReturnsFalseOnAfterArgument() {
        Range afterRange = new Range(10, 20);
        assertThat(range.isConcurrent(afterRange), is(false));
    }

    @Test
    @DisplayName("contains will return true if range contains argument")
    void testThatContainsReturnsTrueWhenArgumentBelongsToRange() {
        long belongingArgument = 0;
        assertThat(range.contains(belongingArgument), is(true));
    }

    @Test
    @DisplayName("contains will return false if argument is more that upperBound")
    void testThatContainsReturnsFalseOnNotGreaterArgument() {
        long greaterArgument = 10;
        assertThat(range.contains(greaterArgument), is(false));
    }

    @Test
    @DisplayName("contains will return false if argument is less than lowerBound")
    void testThatContainsReturnsFalseOnLesserArgument() {
        long lesserArgument = -10;
        assertThat(range.contains(lesserArgument), is(false));
    }

    @Test
    @DisplayName("contains will return true if argument is equal to lowerBound")
    void testThatContainsReturnsTrueOnLowerBoundArgument() {
        long lowerBound = range.getLowerBound();
        assertThat(range.contains(lowerBound), is(true));
    }

    @Test
    @DisplayName("contains will return true if argument is equal to upperBound")
    void testThatContainsReturnsTrueOnUpperBoundArgument() {
        long upperBound = range.getUpperBound();
        assertThat(range.contains(upperBound), is(true));
    }

    @Test
    @DisplayName("asList will return list of all longs belonging to range, including the bounds")
    void testThatAsListReturnsListOfAllLongsContainingInRange() {
        List<Long> expected = new ArrayList<>();
        LongStream.rangeClosed(-5, 5).forEach(expected::add);

        List<Long> actual = range.asList();

        assertThat(expected, equalTo(actual));
    }

    @Test
    @DisplayName("asList will return one-element List is lowerBound == upperBound")
    void testThatAsListReturnsOneElementListWhenRangeContainsOneValue() {
        List<Long> expected = new ArrayList<>();
        expected.add(0L);

        Range actualRange = new Range(0, 0);

        List<Long> actual = actualRange.asList();
        assertThat(expected, equalTo(actual));
    }
}