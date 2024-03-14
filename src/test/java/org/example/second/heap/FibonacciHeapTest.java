package org.example.second.heap;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class FibonacciHeapTest {

    private final FibonacciHeap heap = new FibonacciHeap();

    @AfterEach
    private void clearHeap() {
        heap.clear();
    }

    @Test
    public void initializeEmptyHeap() {
        Assertions.assertAll(
                () -> Assertions.assertNull(heap.getMin()),
                () -> Assertions.assertEquals(0, heap.getSize())
        );
    }

    @Test
    public void minOrDequeueOnEmptyHeapCausesException() {
        Assertions.assertAll(
                () -> Assertions.assertThrows(NoSuchElementException.class, heap::min),
                () -> Assertions.assertThrows(NoSuchElementException.class, heap::dequeueMin)
        );
    }

    @ParameterizedTest
    @MethodSource("generateIntegerList")
    public void enqueueEntriesCorrectSize(List<Integer> values) {
        values.forEach((value) -> Assertions.assertEquals(value, heap.enqueue(value).getValue()));

        Assertions.assertEquals(values.size(), heap.getSize());
    }

    @Test
    public void clearHeapSetsMinAndSizeToDefault() {
        heap.enqueue(1);

        heap.clear();

        Assertions.assertAll(
                () -> Assertions.assertNull(heap.getMin()),
                () -> Assertions.assertEquals(0, heap.getSize())
        );
    }


    @ParameterizedTest
    @MethodSource("generateIntegerList")
    public void minReturnsMin(List<Integer> values) {
        values.forEach(heap::enqueue);

        Assertions.assertEquals(values.stream().min(Integer::compareTo)
                .orElseThrow(() -> new NoSuchElementException("Something went wrong")), heap.min().getValue());
    }

    @ParameterizedTest
    @MethodSource("generateIntegerList")
    public void dequeueMinReturnsMinAndDecreasesSize(List<Integer> values) {
        values.forEach(heap::enqueue);

        Assertions.assertAll(
                () -> Assertions.assertEquals(values.stream().min(Integer::compareTo)
                        .orElseThrow(() -> new NoSuchElementException("Something went wrong")),
                        heap.dequeueMin().getValue()),
                () -> Assertions.assertEquals(values.size() - 1, heap.getSize())
        );
    }

    @ParameterizedTest
    @MethodSource("generateIntegerList")
    public void chainDequeueReturnsMin(List<Integer> values) {
        ArrayList<Integer> mutable = new ArrayList<>(values);
        values.forEach(heap::enqueue);

        while (mutable.size() != 0) {
            Integer min = mutable.stream().min(Integer::compareTo)
                    .orElseThrow(() -> new NoSuchElementException("Something went wrong"));

            Assertions.assertEquals(min, heap.dequeueMin().getValue());

            IntStream.range(0, mutable.size()).filter(i -> mutable.get(i).equals(min))
                    .findFirst().ifPresent(mutable::remove);
        }
    }

    private static Stream<Arguments> generateIntegerList() {
        return Stream.of(
                Arguments.of(List.of(-5, -4, -3, -2, -1, 0, 1, 2, 3, 4, 5)),
                Arguments.of(List.of(-2, -1, 0, 1, 2)),
                Arguments.of(List.of(-1, 0, 1)),
                Arguments.of(List.of(0)),
                Arguments.of(List.of(-5, 1, 2, 3, 4, 5)),
                Arguments.of(List.of(-5, -5, -5, -5, -5)),
                Arguments.of(List.of(1, 1, 1, 1, 1)),
                Arguments.of(List.of(1, -5, 2, -4, 3, -3, 4, -2, 5, -1, 0))
        );
    }

}
