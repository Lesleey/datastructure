package com.imooc.chart.shortestpath;

import com.imooc.chart.basicshow.WeightMatrix;

import java.util.Arrays;

/**
 * @author Lesleey
 * @date 2020/9/18-18:45
 * @function Bellman-Ford算法（有向图算法）
 *    松弛操作： div[v] 是从起始点s 到 当前顶点v ，走过的边数不超过 K 的最短距离
 *       if(dis[a] + ab < dis[b])  dis[b] = dis[a] + ab
 *    算法思想： 就是对所有的边进行 V - 1 次 松弛操作
 *    1. 初始化 dis[start] = 0, 其余为正无穷
 *    2. 对所有的边进行一次松弛操作，则求出了到所有点，经过的边数最多为1 的最短路径
 *    3. 对所有的边进行第二次松弛操作，则求出了到所有点，经过的边数最多为2 的最短路径
 *    4.  重复 V - 1 (图的顶点数)次。
 *
 *    缺陷： 当存在负权环时，没有最短路径（对于无向图，有负权边等同于有负权环）
 *    5.  在最后进行一次松弛操作，如果dis数组中的元素发生了改变，则表示存在负权环，此时表示无解
 *    优化：SPFA
 */
public class BellmanFord {
    private WeightMatrix weightMatrix;
    private int[] dis;
    private int start;
    private boolean hasNegativeCycle;
    private int[] pre;

    //时间复杂度 O（V * V * E）
    public BellmanFord(WeightMatrix weightMatrix, int start){
        weightMatrix.isValid(start);
        this.start = start;
        this.weightMatrix = weightMatrix;
        dis = new int[weightMatrix.getVertex()];
        this.pre = new int[weightMatrix.getVertex()];
        Arrays.fill(dis, Integer.MAX_VALUE);
        dis[start] = 0;
        for (int pass = 1; pass < weightMatrix.getVertex() ; pass++) {
            for (int i = 0; i < weightMatrix.getVertex(); i++) {
                for (int son: weightMatrix.connectVertex(i)) {
                    if(dis[i] != Integer.MAX_VALUE && dis[i] + weightMatrix.getWeight(i, son) < dis[son]) {
                        dis[son] = dis[i] + weightMatrix.getWeight(i, son);
                        pre[son] = pre[i];
                    }
                }
            }
        }
        for (int i = 0; i < weightMatrix.getVertex(); i++) {
            for (int son: weightMatrix.connectVertex(i)) {
                if(dis[i] != Integer.MAX_VALUE && dis[i] + weightMatrix.getWeight(i, son) < dis[son])
                    hasNegativeCycle = true;
                return;
            }
        }
    }

    public boolean isHasNegativeCycle(){
        return hasNegativeCycle;
    }

    public int distTo(int v){
        if(hasNegativeCycle) throw new RuntimeException("图中存在负权环");
        weightMatrix.isValid(v);
        return dis[v];
    }

    public static void main(String[] args) {
        WeightMatrix matrix = new WeightMatrix("complexstructure/src/com/imooc/chart/weightgraph.txt");
        BellmanFord bf = new BellmanFord(matrix, 0);
        for (int i = 0; i < matrix.getVertex(); i++) {
            System.out.println(bf.distTo(i));
        }
    }

}
