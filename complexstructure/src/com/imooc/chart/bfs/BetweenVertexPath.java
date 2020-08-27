package com.imooc.chart.bfs;

import com.imooc.chart.basicshow.Matrix;

import java.util.LinkedList;

/**
 * @author Lesleey
 * @date 2020/8/26-19:58
 * @function  判断两个节点之间是否存在路径
 */
public class BetweenVertexPath {

    public static void bfsMatrix(Matrix matrix, int start, int end){
        int vertex = matrix.getVertex();
        int[] isVisited = new int[vertex];
        for (int i = 0; i < isVisited.length; i++) {
            isVisited[i] = -1;
        }
        LinkedList<Integer> stack = new LinkedList<>();
        stack.push(start);
        isVisited[start] = start;
        while(! stack.isEmpty()){
            Integer head  = stack.pollFirst();
            for (Integer son: matrix.connectVertex(head)) {
                if(isVisited[son] != -1) continue;
                isVisited[son] = head;
                if(son == end) break;
                stack.add(son);
            }
        }

        if(isVisited[end] == -1 ) return;
        while(isVisited[end] != end ){
            System.out.println(end);
            end = isVisited[end];
        }
    }

    public static void main(String[] args) {
        bfsMatrix(new Matrix("complexstructure/src/com/imooc/chart/graph.txt"),0, 6);

    }
}
