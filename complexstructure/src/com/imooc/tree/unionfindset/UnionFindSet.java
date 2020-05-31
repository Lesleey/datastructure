package com.imooc.tree.unionfindset;

/**
 * @author Lesleey
 * @date 2020/5/30-13:24
 * @function
 */
public interface UnionFindSet{
    //将 p 位置对应数据的所在集合和 q 位置对应的数据的所在集合合并起来
    public void union(int p, int q);

    // p 位置的数据和 q 位置的数据是否属于同一个集合
    public boolean isConnected(int p, int q);

    // 数据 q 对应的集合编号
    public int find(int q);

    int getSize();
}
