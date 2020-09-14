package com.imooc.chart.minspantree;

import com.imooc.chart.basicshow.WeightMatrix;

import java.util.PriorityQueue;


/**
 * @author Lesleey
 * @date 2020/9/13-15:01
 * @function 寻找无向带权图的最小生成树
 *   应用： 布线设计、网络设计、电路设计、城市道路设计
 *     将图中的顶点分为两个部分，每个部分叫做一个切分；如果一个边的两个端点，属于切分不同的两边，这个边称为横切边。
 *    切分定理：横切边中的最短的边，属于最小生成树。
 */
public class MinimumSpanningTree {


    public static void main(String[] args) {
        WeightMatrix matrix = new WeightMatrix("complexstructure/src/com/imooc/chart/weightgraph.txt");
        //Kruskal kruskal = new Kruskal(matrix);
        Prim prim = new Prim(matrix, new PriorityQueue<>());
        System.out.println(prim.result());
    }
}
