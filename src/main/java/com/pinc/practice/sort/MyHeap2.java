package com.pinc.practice.sort;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

public class MyHeap2<T> {

    private ArrayList<T> heap;

    private Map<T, Integer> indexMap;

    private int heapSize;

    private Comparator<? super T> com;

    public MyHeap2(Comparator<T> com) {
        this.heap = new ArrayList<>();
        this.indexMap = new HashMap<>();
        this.heapSize = 0;
        this.com = com;
    }

    public void push(T value) {
        heap.add(value);
        indexMap.put(value, heapSize);
        heapInsert(heap, heapSize++);
    }

    public T pop() {
        T res = heap.get(0);
        int end = heapSize - 1;
        swap(0, end);
        heap.remove(end);
        indexMap.remove(res);
        heapify(heap, 0, --heapSize);
        return res;
    }

    private void heapify(ArrayList<T> heap, int index, int heapSize) {
        int left = index * 2 + 1;
        while (left < heapSize) {
            int largest = left + 1 < heapSize && com.compare(heap.get(left), heap.get(left + 1)) > 0 ? left : left + 1;
            largest = com.compare(heap.get(index), heap.get(largest)) > 0 ? index : largest;
            if (largest == index) {
                break;
            }
            swap(index, largest);
            index = largest;
            left = index * 2 + 1;
        }
    }

    public void resign(T value) {
        Integer index = indexMap.get(value);
        heapInsert(heap, index);
        heapify(heap, index, heapSize);
    }

    private void heapInsert(ArrayList<T> heap, int i) {
        while (com.compare(heap.get(i), heap.get(((i - 1) / 2))) > 0) {
            swap(i, (i - 1) / 2);
            i = (i - 1) / 2;
        }
    }

    private void swap(int i, int j) {
        T o1 = heap.get(i);
        T o2 = heap.get(j);
        heap.add(j, o1);
        heap.add(i, o2);
        indexMap.put(o1, j);
        indexMap.put(o2, i);
    }


}
