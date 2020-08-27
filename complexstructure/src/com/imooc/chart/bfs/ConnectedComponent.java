package com.imooc.chart.bfs;

import com.imooc.chart.basicshow.Matrix;

import java.util.LinkedList;

/**
 * @author Lesleey
 * @date 2020/8/26-19:58
 * @function 连通分量个数，以及每个连通分量中的元素
 */
public class ConnectedComponent {

    public static void bfsMatrix(Matrix matrix){
        int vertex = matrix.getVertex();
        int[] isVisited = new int[vertex];
        int ccid = 1;
        for (int i = 0; i < isVisited.length; i++) {
            if(isVisited[i] != 0) continue;
            LinkedList<Integer> stack = new LinkedList<>();
            int root = i;
            stack.push(root);
            isVisited[root] = ccid;
            while(! stack.isEmpty()){
                Integer head  = stack.pollFirst();
                for (Integer son: matrix.connectVertex(head)) {
                    if(0 == isVisited[son] ){
                        stack.addLast(son);
                        isVisited[son] = ccid;
                    }
                }
            }
            ccid ++;
        }
        System.out.println();
    }

    public static void main(String[] args) {
        bfsMatrix(new Matrix("complexstructure/src/com/imooc/chart/graph.txt"));

    }
}
