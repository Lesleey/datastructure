package com.imooc.tree.unionfindset;

/**
 * @author Lesleey
 * @date 2020/5/30-15:17
 * @function Quick Union
 */
public class UnionFindThree implements UnionFindSet {

    private int[] data;
    private int[] sz; //sz[i] 表示以 i节点 为根的树所包含的元素个数

    //把每个数据所在集合初始化为其自身, 也就是每个数据都在不同的集合中
    public UnionFindThree(int size){
        data = new int[size];
        sz = new int[size];
        for (int i = 0; i < size; i++) {
            data[i] = i;
            sz[i] = 1;
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
        if(sz[qRootIndex] >= sz[pRootIndex]) {
            data[pRootIndex] = qRootIndex;
            sz[qRootIndex] += sz[pRootIndex];
        }else{
            data[pRootIndex] = pRootIndex;
            sz[pRootIndex] += sz[qRootIndex];
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
        while(data[q] != q) q = data[q];
        return q;
    }

    @Override
    public int getSize() {
        return data.length;
    }
}
