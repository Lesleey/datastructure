package com.study.sort;

import java.util.Arrays;
import java.util.LinkedList;

/**
 * @author Lesleey
 * @date 2020/6/2-21:43
 * @function 基数排序：适用于有明确范围的数组，创建一个临时数组，待排序的数组可以散在这个数组上
 *  1.找出数组中最大值,并获得它的位数
 *  2. 首先按照个位数排序，将其分配到 0 -9 的桶中。（此时值为 0 -9的元素就会变得有序）
 *  3. 然后按照十位数排序，再次将其分配到 0 - 9 的桶中。（此时10 - 99 的元素就会有序）
 *  4. 依次直到最大值的最高位（其余值如果没有对应的位补零）
 * 时间复杂度（平均） 时间复杂度（最坏） 时间复杂度（最好） 空间复杂度 稳定性 复杂性
 *      O(d(n+r))       O(d(n+r))       O(d(n+r))       O((n+r))   稳定 较复杂
 */
public class RadixSort {
    public void radixSort(int[] arr, int start, int end){
        int[] tmparr = Arrays.copyOf(arr, arr.length);
        int max = Integer.MIN_VALUE;
        for (int i = start; i <= end; i++) {
            if(arr[i] > max) max = arr[i];
        }
        int position = (max + "").length();
        //初始化 10 个桶
        LinkedList<Integer>[] buckets = new LinkedList[10];
        for (int i = 0; i < buckets.length; i++) {
            buckets[i] = new LinkedList();
        }
        // 遍历所有的位数
        for (int i = 0; i < position; i++) {
            //将数组中的元素放到合适的桶
            for (int j = start; j <= end; j++) {
                int curPosVal = (int) (arr[j] / Math.pow(10, i)) % 10;
                buckets[curPosVal].addLast(j);
            }
            int index = 0;
            //更新数组序列
            for (int j = 0; j < buckets.length; j++) {
                Integer tmp ;
                while((tmp = buckets[j].pollFirst()) != null){
                    arr[index ++] = tmparr[tmp];
                }
            }
            tmparr = Arrays.copyOf(arr, arr.length);
        }
    }

    public static void main(String[] args) {
        int[] arr={5,1,2,0,0,6,0,7,5,0};
        new RadixSort().radixSort(arr,0,arr.length-1);
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i]+" ");
        }
    }

}
