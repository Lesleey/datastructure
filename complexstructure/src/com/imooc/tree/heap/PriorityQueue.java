package com.imooc.tree.heap;

/**
 * @author Lesleey
 * @date 2020/5/29-22:24
 * @function
 */
public class PriorityQueue<E extends Comparable<E>> {
    private MaxHeap<E> queue = new MaxHeap<>();
    public void offer(E e){
        queue.add(e);
    }
    public E pop(){
        return queue.extractMax();
    }
}
