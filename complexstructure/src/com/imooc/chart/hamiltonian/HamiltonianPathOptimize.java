package com.imooc.chart.hamiltonian;

import com.imooc.chart.basicshow.Matrix;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Lesleey
 * @date 2020/9/10-18:13
 * @function 哈密尔顿路优化，状态压缩（减少空间复杂度）， 记忆化搜索（将搜索结果存储起来）
 */
public class HamiltonianPathOptimize {
    private static int head = 0;
    private static Map<String, Integer> maps= new HashMap<>();

    /**
     * @param root 当前正在遍历的顶点
     * @param matrix 图
     * @param state  记录当前顶点是否被访问过 ,使用二进制树，例如 0001 表示有四个顶点，其中顶点 0  被访问过， 其他三个没有被访问过
     * */
    public static int dfsRecursionMatrix(Matrix matrix, int root, int state){
        int res = 0;
        state += (1 << root);
        if(state + 1 == Math.pow(2, matrix.getVertex())){
            return 1;
        }
        if(maps.containsKey(root + ":" + state))
            return maps.get(root + ":" + state);
        for (Integer son : matrix.connectVertex(root)) {
            if(((state >> son) & 1) == 1) continue;
            res += dfsRecursionMatrix(matrix, son, state);
        }
        state -= (1 << root);
        maps.put(root + ":" + state, res);
        return res;
    }

    public static void main(String[] args) {
        Matrix matrix = new Matrix("complexstructure/src/com/imooc/chart/graph.txt");
        System.out.println(dfsRecursionMatrix(matrix, 0, 0));
    }
}
