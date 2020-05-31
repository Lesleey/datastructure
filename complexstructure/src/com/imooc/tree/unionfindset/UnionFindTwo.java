package com.imooc.tree.unionfindset;

/**
 * @author Lesleey
 * @date 2020/5/30-15:17
 * @function Quick Union
 */
public class UnionFindTwo implements UnionFindSet {

    private int[] data;

    //把每个数据所在集合初始化为其自身, 也就是每个数据都在不同的集合中
    public UnionFindTwo(int size){
        data = new int[size];
        for (int i = 0; i < size; i++) {
            data[i] = i;
        }
    }

    // 将 p 的根节点 指向 q 的根节点
    @Override
    public void union(int p, int q) {
        int qRootIndex = find(q);
        int pRootIndex = find(p);
        if(qRootIndex == pRootIndex) return;
        data[qRootIndex] = pRootIndex;
    }

    // 如果 p, q的根节点相同，表示处于同一个集合中
    @Override
    public boolean isConnected(int p, int q) {
        return find(q) == find(p);
    }

    // 寻找 q 对应的根节点 对应的位置
    @Override
    public int find(int q) {
        while(data[q] != q) q = data[q];
        return q;
    }

    @Override
    public int getSize() {
        return data.length;
    }
}
