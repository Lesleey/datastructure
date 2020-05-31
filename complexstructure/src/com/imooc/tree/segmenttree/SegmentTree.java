package com.imooc.tree.segmenttree;

import sun.reflect.generics.tree.Tree;

/**
 * @author Lesleey
 * @date 2020/5/30-8:57
 * @function 线段树：区间操作
 */
public class SegmentTree<E> {
    private E[] data;
    private E[] tree;
    private Merge<E> merge;
    public SegmentTree(E[] data, Merge<E> merge){
        if(data == null) throw new IllegalArgumentException("??");
        tree = (E[]) new Object[data.length * 4];
        this.data = data;
        this.merge = merge;
        buildTree(0, 0, data.length - 1);
    }

    /**
     * @param treeindex 线段树节点对应的 tree 数组索引
     * @param left 当前节点表示的区间的左端点（也就是data数组的索引区间）
     * @param right 当前节点表示的区间的右端点
     * */
    private void buildTree(int treeindex, int left, int right){
         // 当区间范围为 1 时停止划分
         if(left == right){
             tree[treeindex] = data[left];
             return;
         }
         int midle = left + ( right - left )/2;
         int leftTreeIndex = getLeftChild(treeindex);
         int rightTreeIndex = getRightChild(treeindex);
         // 分裂区间，构建左线段树
         buildTree(leftTreeIndex, left, midle);
         // 分裂区间， 构建右线段树
         buildTree(rightTreeIndex, midle + 1, right);

         // 将线段树左右端点的结构合并
         tree[treeindex] = merge.merge(tree[leftTreeIndex], tree[rightTreeIndex]);
    }
    //查询 data数组中索引 left, right之间的统计信息
    public E query(int left, int right){
        if(left < 0  || left > right  || right >= data.length) throw new IllegalArgumentException("?");
        return query(0, 0, data.length- 1, left, right);
    }

    /**
     * @param treeIndex 线段树的节点表示的索引
     * @param tl 该节点所表示区间的左端点
     * @param tr 该节点所表示区间的右端点
     * @param ql 要查询的区间的左端点
     * @param qr 要查询的区间的右端点
     * */
    private E query(int treeIndex, int tl,int tr, int ql, int qr){
        if(tl == ql && tr == qr) return tree[treeIndex];

        int midle = tl + ( tr - tl )/ 2;
        // 查找的范围在左子区间内，则去左子区间查找
        if(qr <= midle) return query(getLeftChild(treeIndex), tl, midle, ql, qr);
        // 相反，去右子节点寻找
        if(ql > midle) return query(getRightChild(treeIndex), midle + 1, tr, ql, qr);

        //说明查找的范围在左右区间，则将去两个子区间查找，然后将结果合并
        return merge.merge(query(getLeftChild(treeIndex), tl, midle, ql, midle),
                query(getRightChild(treeIndex), midle + 1, tr, midle + 1, qr));
    }

    // 更新数组中index的值为 e;
    public void set(int index, E e){
        if(index < 0 || index >= data.length) throw new IllegalArgumentException("?");
        data[index] = e;
        set(0, 0, data.length - 1, e, index);
    }

    /**
     * @param treeIndex 表示线段树节点的索引( tree 数组中节点对应的位置 )
     * @param tl 该节点所表示区间的左端点（data数组的范围）
     * @param tr 该节点所表示区间的右端点
     * @param e 要更新的新值
     * @param index 更新的值对应的索引
     * */
    private void set(int treeIndex, int tl, int tr, E e, int index){
        // 如果找到了对应的叶子节点，更新线段树的中节点的值
        if(tl == tr) {
            tree[treeIndex] = e;
            return;
        }
        int midle = tl + (tr - tl)/2;
        // 递归的寻找  index 对应的区间
        if( tl <= midle){
            set(getLeftChild(treeIndex), tl, midle, e, index);
        }else{
            set(getRightChild(treeIndex), midle + 1, tr, e, index);
        }
        // 子节点完成更新之后，重新计算该节点的值
        tree[treeIndex] = merge.merge(tree[getLeftChild(treeIndex)], tree[getRightChild(treeIndex)]);
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

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < tree.length; i++) {
            stringBuilder.append(tree[i] + " ");
        }
        return stringBuilder.toString();
    }
}
