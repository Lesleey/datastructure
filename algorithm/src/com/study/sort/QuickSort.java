package com.study.sort;

/**
 * @author Lesleey
 * @date 2020/6/2-17:15
 * @function  通过一趟排序将要排序的数据分割成独立的两部分，其中一部分的所有数据都比另外一部分的所有数据都要小，
 * 然后再按此方法对这两部分数据分别进行快速排序，整个排序过程可以递归进行，以此达到整个数据变成有序序列。
 *  1. 首先选择将一个分界值（一般是数组中的第一个元素），将数组分为左右两个部分。
 *  2. 将大于分界值的集中到数组右侧，小于分界值的集中到数组的左侧。
 *  3. 左侧和右侧的数据可以独立排序，递归进行 1 步骤
 *  时间复杂度（平均） 时间复杂度（最坏） 时间复杂度（最好） 空间复杂度 稳定性 复杂性
 *    O(nlogn)          o(n^2)          O(nlogn)        O(nlogn)  不稳定 较复杂
 */
public class QuickSort {
    public void quickSort(int[] arr, int start, int end){
        if(start < end){
            int dvi = devision(arr, start, end);
            quickSort(arr, start, dvi);
            quickSort(arr, dvi + 1, end);
        }
    }

    /**
     * @return 基准元素的索引， 也就是分界值的索引
     *  找出基准元素，进行分割
     * */
    public int devision(int[] arr, int start, int end){
        int base = arr[start];
        while(start < end){
            while(start < end && arr[end] >= base){
                end --;
            }
            arr[start] = arr[end];
            while(start < end && arr[start] <= base){
                start ++;
            }
            arr[end] = arr[start];
        }

        arr[end] = base;
        return start;
    }

    public static void main(String[] args) {
        int[] array= {5,5,3,4,6,1,78,5,6,12,10};
        new QuickSort().quickSort(array, 0, array.length-1);
        for (int i : array) {
            System.out.print(i+" ");
        }
    }
}
