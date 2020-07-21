package com.study.sort;

/**
 * @author Lesleey
 * @date 2020/6/2-17:16
 * @function 选择排序
 *  选择数组中最小的值与索引为0的位置进行交换，选择第二小的与索引1的位置交换，依次类推
 *   时间复杂度（平均） 时间复杂度（最坏） 时间复杂度（最好） 空间复杂度 稳定性 复杂性
 *      O(n^2)          O(n^2)              O(n^2)          O(1) 不稳定 简单
 */
public class SelectSort {

    public void sort(int[] arr, int start, int end){
        for (int i = start; i < end; i++) {
            for (int j = i + 1; j <= end ; j++) {
                if(arr[i] > arr[j]){
                    int tmp = arr[j];
                    arr[j] = arr[i];
                    arr[i] = tmp;
                }
            }
        }
    }

    public void sortRecursion(int[] arr, int start, int end){
        if(start == end) return;
        for (int i = start + 1; i <= end ; i++) {
            if(arr[i] < arr[start]){
                int tmp = arr[start];
                arr[start] = arr[i];
                arr[i] = tmp;
            }
        }
        sortRecursion(arr, start + 1, end);
    }

    public static void main(String[] args) {
        int[] arr={5,2,3,8,0,1,6,7,0};
        new SelectSort().sort(arr,0,arr.length - 1);
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i]+" ");
        }
    }
}
