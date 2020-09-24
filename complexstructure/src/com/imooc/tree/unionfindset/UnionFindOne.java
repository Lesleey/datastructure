package com.imooc.tree.unionfindset;

/**
 * @author Lesleey
 * @date 2020/5/30-14:44
 * @function quick find
 */
public class UnionFindOne implements  UnionFindSet {

    private int[] data;

    //把每个数据所在集合初始化为其自身, 也就是每个数据都在不同的集合中
    public UnionFindOne(int size){
        data = new int[size];
        for (int i = 0; i < size; i++) {
            data[i] = i;
        }
    }

    /*
     *  0 1 2 3 4 5  ==> 0 1 2 3 4 5
     *  0 1 0 1 0 1      0 0 0 0 0 0
     *  通过遍历数组，将 数组中 p 的集合编号全部修改为 q 的集合编号的值 ，时间复杂度为O(n)
     * */
    @Override
    public void union(int p, int q) {
        int qListNo = find(q);
        int pListNo = find(p);
        if(pListNo == qListNo) return;
        for (int i = 0; i < data.length; i++) {
            if(qListNo == data[i]) data[i] = pListNo;
        }
    }

    // 在这种情况下，判断两个数据是否在同一个集合，只需要判断对一个的值是否相等即可，O(1)级别，所以叫 Quick Find
    @Override
    public boolean isConnected(int p, int q) {
        return find(q) == find(p);
    }
    @Override
    public int find(int q) {
        if(q < 0 || q >= data.length) throw new IllegalArgumentException("参数无效");
        return data[q];
    }

    @Override
    public int getSize() {
        return data.length;
    }
}
