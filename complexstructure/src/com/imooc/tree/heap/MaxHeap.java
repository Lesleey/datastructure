package com.imooc.tree.heap;

/**
 * @author Lesleey
 * @date 2020/5/29-20:19
 * @function
 */
public class MaxHeap<E extends Comparable<E>> {
    //假设不会越界
    private  E[] arr = (E[]) new Comparable[10];

    int size;

    //返回堆中元素个数
    public int getSize(){
        return size;
    }

    // 返回索引 index 对应的父节点的索引
    public int getParentIndex(int index){
        if(index <= 0) throw new IllegalArgumentException("参数无效!");
        return (index - 1) /2;
    }
    //返回左子节点对应的索引
    public int getLeftChild(int index){
        return index * 2 + 1;
    }
    //返回右子节点对应的索引
    public int getRightChild(int index){
        return index * 2 + 2;
    }

    // 堆的 replace 操作
    public E replace(E e){
        if(size == 0) return null;
        E ret = arr[0];
        arr[0] = e;
        siftDown(0);
        return ret;
    }
    //取堆顶元素
    public E extractMax(){
        if(size == 0) return null;
        E ret = arr[0];
        swap(-- size, 0);
        siftDown(0);
        return ret;
    }
    //堆的下沉操作 sift down
    public void siftDown(int index){
        while(getLeftChild(index) < size){
            int swapIndex = getLeftChild(index);
            if(swapIndex + 1 < size && arr[swapIndex + 1].compareTo(arr[swapIndex]) > 0) swapIndex ++;
            if(arr[index].compareTo(arr[swapIndex]) > 0) return;
            swap(index, swapIndex);
            index = swapIndex;
        }

    }
    public void add(E e){
        if(size == arr.length) throw new ArrayStoreException("堆已满");
        arr[size ++] = e;
        siftUp(size - 1);
    }
    // 进行堆的上浮操作 sift Up
    public void siftUp(int index){
        //如果父节点小于子节点的值，则进行交换
        while(index > 0 && arr[getParentIndex(index)].compareTo(arr[index]) < 0){
            swap(index, getParentIndex(index));
            index = getParentIndex(index);
        }
    }
    //交换n, m 位置的值
    public void swap(int n, int m){
        E tmp = arr[n];
        arr[n] = arr[m];
        arr[m] = tmp;
    }
    //堆化
    public void heapfiy(E[] arr){
        this.arr = arr;
        size = arr.length;
        for (int i = getParentIndex(arr.length - 1); i >= 0 ; i--) {
            siftDown(i);
        }
    }
}
