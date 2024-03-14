package org.example.second.heap;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.*;
import java.util.stream.Collectors;
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
                Arguments.of(List.of(1, -5, 2, -4, 3, -3, 4, -2, 5, -1, 0)),
                Arguments.of(List.of(1, 3, 2, 5, 4))
        );
    }

    @Test
    public void checkHeapStructure() {
        List<Integer> values = List.of(1, 3, 2, 5, 4);
        values.forEach(heap::enqueue);

        List<FibonacciHeap.Entry> entries = new ArrayList<>();
        FibonacciHeap.Entry entry = heap.min();
        entries.add(entry);
        while (!entries.get(0).getValue().equals(entry.getNext().getValue())) {
            entry = entry.getNext();
            entries.add(entry);
        }

        Assertions.assertAll(
                () -> Assertions.assertEquals(1, entries.get(0).getValue()),
                () -> Assertions.assertEquals(4, entries.get(0).getNext().getValue()),
                () -> Assertions.assertEquals(3, entries.get(0).getPrev().getValue())
        );

        Assertions.assertAll(
                () -> Assertions.assertEquals(5, entries.get(1).getNext().getValue()),
                () -> Assertions.assertEquals(1, entries.get(1).getPrev().getValue())
        );

        Assertions.assertAll(
                () -> Assertions.assertEquals(2, entries.get(2).getNext().getValue()),
                () -> Assertions.assertEquals(4, entries.get(2).getPrev().getValue())
        );

        Assertions.assertAll(
                () -> Assertions.assertEquals(3, entries.get(3).getNext().getValue()),
                () -> Assertions.assertEquals(5, entries.get(3).getPrev().getValue())
        );

        Assertions.assertAll(
                () -> Assertions.assertEquals(1, entries.get(4).getNext().getValue()),
                () -> Assertions.assertEquals(2, entries.get(4).getPrev().getValue())
        );
    }

    @Test
    public void checkHeapStructureAfterMerge() {
        List<Integer> values = List.of(1, 3, 2, 5, 4);
        values.forEach(heap::enqueue);

        heap.dequeueMin();

        Set<FibonacciHeap.Entry> entries = new LinkedHashSet<>();
        FibonacciHeap.Entry entry = heap.min();
        traverse(entry, entries);

        List<FibonacciHeap.Entry> entriesList = entries.stream().toList();

        Assertions.assertAll(
                () -> Assertions.assertEquals(2, entriesList.get(0).getNext().getValue()),
                () -> Assertions.assertEquals(2, entriesList.get(0).getPrev().getValue()),
                () -> Assertions.assertEquals(3, entriesList.get(0).getChild().getValue())
        );

        Assertions.assertAll(
                () -> Assertions.assertEquals(4, entriesList.get(1).getNext().getValue()),
                () -> Assertions.assertEquals(4, entriesList.get(1).getPrev().getValue()),
                () -> Assertions.assertNull(entriesList.get(1).getChild())
        );

        Assertions.assertAll(
                () -> Assertions.assertEquals(3, entriesList.get(2).getNext().getValue()),
                () -> Assertions.assertEquals(3, entriesList.get(2).getPrev().getValue()),
                () -> Assertions.assertEquals(5, entriesList.get(2).getChild().getValue())
        );

        Assertions.assertAll(
                () -> Assertions.assertEquals(5, entriesList.get(3).getNext().getValue()),
                () -> Assertions.assertEquals(5, entriesList.get(3).getPrev().getValue()),
                () -> Assertions.assertNull(entriesList.get(3).getChild())
        );
    }

    private void traverse(FibonacciHeap.Entry entry, Set<FibonacciHeap.Entry> entries) {
        if (Objects.isNull(entry)) {
            return;
        }

        entries.add(entry);

        traverse(entry.getChild(), entries);

        if (entries.contains(entry.getNext())) {
            return;
        }

        traverse(entry.getNext(), entries);
    }
}
