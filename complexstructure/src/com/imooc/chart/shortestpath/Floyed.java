package com.imooc.chart.shortestpath;

import com.imooc.chart.basicshow.WeightMatrix;

/**
 * @author Lesleey
 * @date 2020/9/18-19:52
 * @function Floyed 寻找所有对的最短路径 时间复杂度 O(V * V * V)
 *  图的直径： 所有点对最短路径中的最大值
 *  这种算法可以包含负权边；检测负权环
 *  dis[i][j] 就是当前已经找到了从 i 到 j 的最短距离, 这种算法的基本更新原理 dis[a][c] = Math.min(dis[a][b], dis[b][c]);
 *  算法思路：
 *  1. 初始化一个 dis[V][V]的二维数组， 首先dis[a][a] = 0; 如果顶点 a 到顶点 b 相连， 则 dis[a][b] = ab; 其余的值为正无穷
 *
 */
public class Floyed {
    private WeightMatrix weightMatrix;
    private int[][] dis;
    private boolean hasNegativecycle;

    public Floyed(WeightMatrix weightMatrix) {
        this.weightMatrix = weightMatrix;
        int v = weightMatrix.getVertex();
        dis = new int[v][v];
        for (int i = 0; i < v; i++) {
            for (int j = 0; j < v; j++) {
                if (i == j)
                    dis[i][j] = 0;
                else if (weightMatrix.isExistEdge(i, j))
                    dis[i][j] = weightMatrix.getWeight(i, j);
                else
                    dis[i][j] = Integer.MAX_VALUE;
            }
        }

        //该层循环是 寻找寻找是否经过顶点t使得 顶点i到顶点j的最短路径变小
        for (int t = 0; t < v; t++) {
            //该层循环是遍历所有的结果从 i 到 j 的最短路径
            for (int i = 0; i < v; i++) {
                for (int j = 0; j < v; j++) {
                    if (dis[i][t] != Integer.MAX_VALUE && dis[t][j] != Integer.MAX_VALUE
                            && dis[i][t] + dis[t][j] < dis[i][j])
                        dis[i][j] = dis[i][t] + dis[t][j];
                }
            }
        }
        for (int i = 0; i < v; i++) {
            if (dis[i][i] < 0)
                hasNegativecycle = true;

        }
    }

    public int distTo(int v, int w){
        if(hasNegativecycle) throw new RuntimeException("图中存在负权边");
        weightMatrix.isValid(v);
        weightMatrix.isValid(w);
        return dis[v][w];
    }

    public void print(){
        for (int i = 0; i < dis.length; i++) {
            for (int j = 0; j < dis.length; j++) {
                System.out.print(dis[i][j] + " ");
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        WeightMatrix matrix = new WeightMatrix("complexstructure/src/com/imooc/chart/weightgraph.txt");
        Floyed bf = new Floyed(matrix);
        bf.print();
    }
}


