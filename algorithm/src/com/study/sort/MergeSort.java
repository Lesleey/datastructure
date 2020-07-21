package com.study.sort;

/**
 * @author Lesleey
 * @date 2020/6/2-21:11
 * @function 归并排序：将原数组分为两半，对这两半分别排序，将排序后的这两半归并为第二个临时数组，然后在将这个临时数组复制回原数组
 * 时间复杂度（平均） 时间复杂度（最坏） 时间复杂度（最好） 空间复杂度 稳定性 复杂性
 *      O(nlogn)        O(nlogn)        O(nlogn)        o(n)       稳定 较复杂
 */
public class MergeSort {
    public void mergeSort(int[] arr, int start, int end){
        if(start < end){
            int mid = start + (end - start)/2;
            mergeSort(arr, start, mid);
            mergeSort(arr, mid + 1, end);
            //合并数组的排序结构
            mergeRes(arr, start, mid, end);
        }
    }

    private void mergeRes(int[] arr, int start, int mid, int end) {
        int[] tmparr = new int[end - start + 1];
        int newIndex = 0;
        int leftstart = start, leftend = mid;
        int rightstart = mid + 1, rightend = end;
        while(leftstart <= leftend && rightstart <= rightend){
            if(arr[leftstart] < arr[rightstart]){
                tmparr[newIndex ++] = arr[leftstart ++];
            }else{
                tmparr[newIndex ++] = arr[rightstart ++];
            }
        }

        while(leftstart <= leftend) tmparr[newIndex ++] = arr[leftstart ++];

        while(rightstart <= rightend) tmparr[newIndex ++] = arr[rightstart ++];

        //将排好序的临时数组复制到原数组中
        System.arraycopy(tmparr, 0, arr, start, tmparr.length);
    }

    public static void main(String[] args) {
        int[] arr={5,1,2,0,0,6,0,7,5,0};
        new MergeSort().mergeSort(arr,0,arr.length-1);
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i]+" ");
        }
    }
}
