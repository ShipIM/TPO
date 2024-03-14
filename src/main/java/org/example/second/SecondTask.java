package org.example.second;

import org.example.second.heap.FibonacciHeap;

public class SecondTask {

    public static void main(String[] args) {
        FibonacciHeap heap = new FibonacciHeap();

        heap.enqueue(-5);
        heap.enqueue(-4);
        heap.enqueue(-3);
        heap.enqueue(-2);
        heap.enqueue(-1);
        heap.enqueue(0);
        heap.enqueue(1);
        heap.enqueue(2);
        heap.enqueue(3);
        heap.enqueue(4);
        heap.enqueue(5);

        System.out.println(heap.dequeueMin().getValue());
    }

}
