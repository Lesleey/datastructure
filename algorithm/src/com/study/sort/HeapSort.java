package com.study.sort;

/**
 * @author Lesleey
 * @date 2020/6/2-20:18
 * @function 堆排序: 类似与优先队列，选择最大元素与数组的最后进行交换，然后进行堆的下沉使用堆的两个基本操作堆化和下沉(https://www.jianshu.com/p/1215ac557516)
 *
 * 时间复杂度（平均） 时间复杂度（最坏） 时间复杂度（最好） 空间复杂度 稳定性 复杂性
 *  O(nlogn)            O(nlogn)        O(nlogn)        O(1)      不稳定 较复杂
 */
public class HeapSort {
    public void heapify(int[] arr, int start, int end){
        for (int i = end/2; i >= start ; i--) {
            siftDown(arr, i, end);
        }
    }

    public void siftDown(int[] arr, int start, int end){
        int index = start;
        int leftindex = 2 * index + 1;
        while(leftindex <= end){
            if(leftindex + 1 <= end && arr[leftindex + 1] > arr[leftindex]) leftindex ++;
            if(arr[index] >= arr[leftindex]) return;

            if(arr[index] < arr[leftindex]){
                int tmp = arr[index];
                arr[index] = arr[leftindex];
                arr[leftindex] = tmp;
            }
            index = leftindex;
            leftindex = 2 * index + 1;
        }
    }

    public void sort(int[] arr, int start, int end){
        heapify(arr, start, end);
        for (int i = end; i > start; i--) {
            int tmp = arr[i];
            arr[i] = arr[start];
            arr[start] = tmp;
            siftDown(arr, start, i - 1);
        }
    }
    public static void main(String[] args) {
        int[] arr={0,4,1, 10, 3, 5,8};
        new HeapSort().sort(arr, 0, arr.length - 1);
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i]+" ");
        }
    }
}
