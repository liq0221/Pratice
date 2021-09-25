package com.pinc.practice.sort;

public class MyHeap {

    private int[] heap;

    private int heapSize;

    private final int limit;

    public MyHeap(int size) {
        this.heap = new int[size];
        this.limit = size;
        this.heapSize = 0;
    }

    public void push(int value) {
        if (heapSize == limit) {
           throw new RuntimeException("堆已满...");
        }
        heap[heapSize] = value;
        heapInsert(heap, heapSize++);
    }

    // 返回数组中的最大数
    public int pop() {
        if (heapSize == 0) {
            throw new RuntimeException("堆已空...");
        }
        int res = heap[0];
        swap(heap, 0, --heapSize);
        heapify(heap, 0, heapSize);
        return res;
    }

    public void heapSort(int[] arr) {

        // 调整为大根堆
        for (int i = arr.length - 1; i >= 0; i--) {
            heapify(arr, i, arr.length);
        }
        int heapSize = arr.length;
        // 开始排序
        swap(arr, 0, --heapSize);
        while (heapSize > 0) {
            heapify(arr, 0, heapSize);
            swap(arr, 0, --heapSize);
        }
    }

    // 插入一个数，保证还是大根堆
    private void heapInsert(int[] arr, int i) {
        while (arr[i] > arr[(i - 1) / 2]) {
            swap(arr, i, (i - 1) / 2);
            i = (i - 1) / 2;
        }
    }

    // 调整为大根堆
    private void heapify(int[] heap, int i, int heapSize) {
        int left = i * 2 + 1;
        while (left < heapSize) {
            int largest = left + 1 < heapSize && heap[left + 1] > heap[left] ? left + 1 : left;
            largest = heap[largest] > heap[i] ? largest : i;
            if (largest == i) {
                break;
            }
            swap(heap, i, largest);
            i = largest;
            left = i * 2 + 1;
        }
    }


    private void swap(int[] _arr, int i, int j) {
        int temp = _arr[i];
        _arr[i] = _arr[j];
        _arr[j] = temp;
    }
}
