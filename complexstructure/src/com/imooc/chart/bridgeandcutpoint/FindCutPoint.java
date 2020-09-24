package com.imooc.chart.bridgeandcutpoint;

import com.imooc.chart.basicshow.Matrix;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.IntStream;

/**
 * @author Lesleey
 * @date 2020/9/5-13:24
 * @function 寻找无向图中的割点 dfs
 *  如果删除一个顶点，导致图的连通分量发生变化，则该点就为割点
 *  1. 如果为根节点，则判断遍历树中该根节点的孩子数是量，如果大于 1 则表示该根节点 割点
 *  2. 如果不为根节点，则如果存在 x 节点的子节点 y，如果 low[y] >= ord[x]则表示 x是割点
 */
public class FindCutPoint {
    //节点访问顺序 pred[i] = k，表示节点 i 是被 k 个访问的节点
    private static int[] pred;

    //节点能到达的最小（也就是最先访问）的 ord 值， low[i] = k 表示节点 i 最小可以到达的节点 k
    private static int[] low;
    private static int order = 0;
    private static Set<Integer> res = new HashSet<>();
    private static int parent = 0;
    private static int child = 0;
    public static void findBridge(Matrix matrix){
        int vertex = matrix.getVertex();
        pred = new int[vertex];
        low = new int[vertex];
        IntStream.range(0, vertex).forEach((k)-> pred[k] = -1);
        for (int i = 0; i < vertex; i++) {
            if(pred[i] == -1){
                order = 0;
                parent = 0;
                child = 0;
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
                if(root != parent && low[son] >= low[root])
                    res.add(root);
                //如果为根节点
                child ++;
                if(root == parent && child > 1) {
                    res.add(root);
                }
            }
            //如果不是当前节点的父节点
            else if(pred[son] + 1 != pred[root]){
                low[root] = Math.min(low[root], low[son]);
            }
        }
    }
}
