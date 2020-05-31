package com.imooc.array;

import java.util.Arrays;

/**
 * @author Lesleey
 * @date 2020/5/28-20:16
 * @function
 */
public class Array <T> {
    private T[] data;
    private int size;
    //初始化数组
    public Array(int capacity){
        this.data = (T[])new Object[capacity];
    }
    public Array(){
        this(3);
    }

    // 获取数组的容量
    public int getCapacity(){
        return data.length;
    }

    // 获取数组中的元素个数
    public int getSize(){
        return size;
    }

    //移除指定索引的元素
    public T remove(int index){
        if(index < 0 || index >= size) throw new IllegalArgumentException("无效的 index");
        T ret = data[index];
        size --;
        for (int i = index; i < size  ; i++) {
            data[i] = data[i+1];
        }
        return ret;
    }
    // 从数组中删除第一个元素, 返回删除的元素
    public T removeFirst(){
        return remove(0);
    }

    // 从数组中删除最后一个元素, 返回删除的元素
    public T removeLast(){
        return remove(size - 1);
    }
    //向指定索引添加元素
    public void add(int index, T t){
        if(index < 0 || index > size) throw new IllegalArgumentException("无效的 index");
        if(size == data.length) resize(data.length * 2);
        System.arraycopy(data, index, data, index + 1, size - index);
        data[index] = t;
        size ++;
    }

    // 向所有元素后添加一个新元素
    public void addLast(T e){
        add(size, e);
    }

    // 在所有元素前添加一个新元素
    public void addFirst(T e){
        add(0, e);
    }


    // 获取index索引位置的元素
    public T get(int index){
        if(index < 0 || index >= size)
            throw new IllegalArgumentException("Get failed. Index is illegal.");
        return data[index];
    }

    // 修改index索引位置的元素为e
    public void set(int index, T e){
        if(index < 0 || index >= size) throw new IllegalArgumentException("无效的 index");
        data[index] = e;
    }

    //数组扩容 | 缩容，新容量为 capacity
    private void resize(int capacity){
        data = Arrays.copyOf(data, capacity);
    }

    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        for (int i = 0; i < size; i++) {
            sb.append(data[i] + " ");
        }
        sb.append("], data.length = " + data.length);
        return sb.toString();
    }
}

