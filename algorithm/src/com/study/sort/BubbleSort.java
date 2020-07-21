package com.study.sort;

/**
 * @author Lesleey
 * @date 2020/6/2-17:05
 * @function 冒泡排序： 每次遍历都找出当前的最大值并将其放到数组的"尾部"
 *  对于长度为n 的数组，第一趟找出最大的值，将其放到索引为n-1的位置上，第二趟找出第二大的值放到n-2的位置上，依次类推
 *   将大值 "浮动" 到数组尾部
 *   时间复杂度（平均） 时间复杂度（最坏） 时间复杂度（最好） 空间复杂度 稳定性 复杂性
 *      O(n^2)           O(n^2)               O(n)          O(1)    稳定  简单
 */
public class BubbleSort {
    public void sort(int[] arr, int start, int end){
        for (int i = start; i < end; i++) {
            for (int j = start; j < end - i; j++) {
                if(arr[j] > arr[j + 1]){
                    int tmp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = tmp;
                }
            }
        }
    }

    public void sortRecursion(int[] arr, int start, int end){
        if(start == end) return;

        for (int i = start; i < end; i++) {
            if(arr[i] > arr[i + 1]){
                int tmp = arr[i];
                arr[i] = arr[i + 1];
                arr[i + 1] = tmp;
            }
        }
        sortRecursion(arr, start, end - 1);
    }

    public static void main(String[] args) {
        int[] arr = {1,5,2,3,4,6,12,10};
        new BubbleSort().sortRecursion(arr, 0, arr.length - 1);
        for (int i = 0; i < arr.length; i++) {
            System.out.println(arr[i]);
        }
    }
}
