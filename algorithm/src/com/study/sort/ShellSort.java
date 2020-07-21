package com.study.sort;

/**
 * @author Lesleey
 * @date 2020/6/3-9:01
 * @function 改进的插入排序
 * 使之对由索引间距相等的元素构成的子数组排序，建议初始索引间距为 n/2, 以后每趟都将这个值减半，直到为一.
 *  时间复杂度（平均） 时间复杂度（最坏） 时间复杂度（最好） 空间复杂度 稳定性 复杂性
 *       O(nlogn)	    O(n^2)	        O(n^1.3)	           O(1) 不稳定	较复杂
 */
public class ShellSort {
    public void sort(int[] arr, int start, int end){
        int interval = (end - start) / 2 + start;
        //当间距为 1 时结束
        while(interval >= 1){
            //进行插入排序
            for (int i = interval; i <= end; i++) {
                int curInsertVal = arr[i];
                //上一个数据的下标
                int j = i - interval;
                while(j >= start && arr[j] > curInsertVal){
                    arr[j + interval] = arr[j];
                    j -= interval;
                }

                arr[j + interval]= curInsertVal;
            }

            interval /= 2;
        }
    }
    public static void main(String[] args) {
        int[] arr={0,4,5,5,8,1,5,0};
        new ShellSort().sort(arr, 0, arr.length - 1);
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i]+" ");
        }
    }
}
