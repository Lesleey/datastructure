package com.imooc.tree.unionfindset;

/**
 * @author Lesleey
 * @date 2020/5/30-16:02
 * @function 基于 rank 的优化
 */
public class UnionFindFive implements UnionFindSet{
    private int[] data;
    private int[] height; //height[i] 表示以 i节点 为根的树的高度

    //把每个数据所在集合初始化为其自身, 也就是每个数据都在不同的集合中
    public UnionFindFive(int size){
        data = new int[size];
        height = new int[size];
        for (int i = 0; i < size; i++) {
            data[i] = i;
            height[i] = 1;
        }
    }

    // 将 p 的根节点 指向 q 的根节点
    @Override
    public void union(int p, int q) {
        // 分别获得q, p 的根节点的索引
        int qRootIndex = find(q);
        int pRootIndex = find(p);
        if(qRootIndex == pRootIndex) return;

        //如果q 高， 让 p 指向 q
        if(height[qRootIndex] > height[pRootIndex]) {
            data[pRootIndex] = qRootIndex;
         //如果 p 高，让 q 指向 p
        }else if(height[qRootIndex] < height[pRootIndex]){
            data[qRootIndex] = pRootIndex;
        }else{
            data[qRootIndex] = pRootIndex;
            height[pRootIndex] ++;
        }
    }

    // 如果 p, q的根节点相同，表示处于同一个集合中
    @Override
    public boolean isConnected(int p, int q) {
        return find(q) == find(p);
    }

    // 寻找 q 对应的根节点 对应的位置
    @Override
    public int find(int q) {
        while(data[q] != q) {
            data[q] = data[data[q]];
            q = data[q];
        }
        /*
         让所有节点都直接指向根节点，通过递归实现
        if(data[q] != q){
            data[q] = find(data[q]);
        }
        */

        return q;
    }

    @Override
    public int getSize() {
        return data.length;
    }
}
