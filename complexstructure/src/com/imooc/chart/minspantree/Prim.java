package com.imooc.chart.minspantree;

import com.imooc.chart.basicshow.WeightMatrix;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

/**
 * @author Lesleey
 * @date 2020/9/13-16:51
 * @function 基于切分定理
 *   算法思想：操作切分，从 n: V-n结束，n从1开始。将选中的所有顶点构成一个切分，然后剩余部分作为另一个切分。根据切分定理选择最短的边。直到没有切分。
 *
 */
public class Prim {
    private WeightMatrix weightMatrix;
    private List<WeightEdge> minspantree;

    /**
     *  时间复杂度：O(VE)
     * */
    public Prim(WeightMatrix weightMatrix){
        this.weightMatrix = weightMatrix;
        minspantree = new ArrayList<>();
        boolean[] isVisited = new boolean[weightMatrix.getVertex()];
        isVisited[0] = true;
        for (int i = 0; i < weightMatrix.getVertex(); i++) {
            WeightEdge curEdge = null;
            for (int j = 0; j < weightMatrix.getVertex(); j++) {
                if (isVisited[j]) {
                    for (int son : weightMatrix.connectVertex(j)) {
                        if (isVisited[son]) continue;
                        int tmpWeight = weightMatrix.getWeight(j, son);
                        curEdge = curEdge != null && curEdge.weight < tmpWeight ? curEdge : new WeightEdge(j, son, tmpWeight);
                    }
                }
            }
            if (curEdge != null) {
                minspantree.add(curEdge);
                isVisited[curEdge.v] = isVisited[curEdge.w] = true;
            }
        }
    }
    /**
     *  O(ElogE)
     * */
    public Prim(WeightMatrix weightMatrix, PriorityQueue<WeightEdge> priorityQueue){
        this.weightMatrix = weightMatrix;
        minspantree = new ArrayList<>();
        boolean[] isVisited = new boolean[weightMatrix.getVertex()];
        isVisited[0] = true;
        for (int son: weightMatrix.connectVertex(0)) {
            priorityQueue.add(new WeightEdge(0, son, weightMatrix.getWeight(0, son)));
        }
        while( ! priorityQueue.isEmpty()){
            WeightEdge min = priorityQueue.poll();
            if(isVisited[min.v] == isVisited[min.w]) continue;
            minspantree.add(min);
            int root = isVisited[min.v] ? min.w: min.v;
            isVisited[min.v] = isVisited[min.w] = true;
            for (int son: weightMatrix.connectVertex(root)) {
                if(isVisited[son]) continue;
                priorityQueue.add(new WeightEdge(root, son, weightMatrix.getWeight(root, son)));
            }

        }

    }
    public List<WeightEdge> result(){
        return minspantree;
    }
}
