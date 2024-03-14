package org.example.second.heap;

import lombok.Getter;

import java.util.*;

@Getter
public final class FibonacciHeap {

    private Entry min = null;
    private int size = 0;

    public Entry enqueue(Integer value) {
        Entry result = new Entry(value);

        min = mergeLists(min, result);
        ++size;

        return result;
    }

    public Entry min() {
        if (Objects.isNull(min)) {
            throw new NoSuchElementException("Heap is empty.");
        }

        return min;
    }

    public Entry dequeueMin() {
        if (Objects.isNull(min)) {
            throw new NoSuchElementException("Heap is empty.");
        }

        --size;
        Entry minElem = min;

        if (min.next == min) {
            min = null;
        } else {
            min.prev.next = min.next;
            min.next.prev = min.prev;
            min = min.next;
        }

        if (minElem.child != null) {
            Entry curr = minElem.child;

            do {
                curr = curr.next;
            } while (curr != minElem.child);
        }

        min = mergeLists(min, minElem.child);
        if (min == null) {
            return minElem;
        }

        List<Entry> treeTable = new ArrayList<>();
        List<Entry> toVisit = new ArrayList<>();

        for (Entry curr = min; toVisit.isEmpty() || toVisit.get(0) != curr; curr = curr.next){
            toVisit.add(curr);
        }

        for (Entry curr : toVisit) {
            while (true) {
                while (curr.degree >= treeTable.size()) {
                    treeTable.add(null);
                }

                if (treeTable.get(curr.degree) == null) {
                    treeTable.set(curr.degree, curr);

                    break;
                }

                Entry other = treeTable.get(curr.degree);
                treeTable.set(curr.degree, null);

                Entry min = (other.value < curr.value) ? other : curr;
                Entry max = (other.value < curr.value) ? curr  : other;

                max.next.prev = max.prev;
                max.prev.next = max.next;

                max.next = max.prev = max;
                min.child = mergeLists(min.child, max);

                ++min.degree;
                curr = min;
            }

            if (curr.value <= min.value) {
                min = curr;
            }
        }

        return minElem;
    }

    private static Entry mergeLists(Entry one, Entry two) {
        if (one == null && two == null) {
            return null;
        } else if (one != null && two == null) {
            return one;
        } else if (one == null) {
            return two;
        } else {
            Entry oneNext = one.next;
            one.next = two.next;
            one.next.prev = one;
            two.next = oneNext;
            two.next.prev = two;

            return one.value < two.value ? one : two;
        }
    }

    public void clear() {
        min = null;
        size = 0;
    }

    @Getter
    public static final class Entry {
        private int degree = 0;

        private Entry next;
        private Entry prev;

        private Entry child;

        private final Integer value;

        private Entry(Integer value) {
            next = prev = this;
            this.value = value;
        }

        public Integer getValue() {
            return value;
        }
    }

}