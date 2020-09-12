package com.imooc.chart.bridgeandcutpoint;

import com.imooc.chart.basicshow.Matrix;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

/**
 * @author Lesleey
 * @date 2020/9/4-19:39
 * @function 寻找无向图中的桥（dfs）
 *  判断顶点x, y之间的边是否为桥，看通过y 是否存在另一条路回到 x或者x之前的顶点
 *  ，如果可以表示该边不是桥。
 */
public class FindBridge {
    //节点访问顺序 pred[i] = k，表示节点 i 是被 k 个访问的节点
    private static int[] pred;

    //节点能到达的最小（也就是最先访问）的 ord 值， low[i] = k 表示节点 i 最小可以到达的节点 k
    private static int[] low;
    private static int order = 0;
    private static List<int[]> res = new ArrayList<>();
    public static void findBridge(Matrix matrix){
        int vertex = matrix.getVertex();
        pred = new int[vertex];
        low = new int[vertex];
        IntStream.range(0, vertex).forEach((k)-> pred[k] = -1);
        for (int i = 0; i < vertex; i++) {
            if(pred[i] == -1){
                order = 0;
                dfsFindBridge(i, matrix);
            }
        }
    }

    private static void dfsFindBridge(int root, Matrix matrix) {
        pred[root] = order ++;
        low[root] = root;
        for (int son: matrix.connectVertex(root)) {
            //如果没有被访问过，则进行访问
            if(pred[son] == -1){
                dfsFindBridge(son, matrix);
                low[root] = Math.min(low[root], low[son]);
                if(low[son] > pred[root]) res.add(new int[]{root, son});
                //如果不是当前节点的父节点
            }
            else if(pred[son] + 1 != pred[root]){
                low[root] = Math.min(low[root], low[son]);
            }
        }
    }
}
