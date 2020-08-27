package com.imooc.chart.bfs;

import com.imooc.chart.basicshow.Matrix;

import java.util.LinkedList;

/**
 * @author Lesleey
 * @date 2020/8/26-20:18
 * @function  无权无向图单源路径（bfs使用该方法获取的路径也是最短路径，原因类似树）
 */
public class SingleSourcePath {
    /**
     * @param matrix 图
     * @param root 确定的顶点
     * @param target 寻找的顶点
     * */
    public static void getSingleSourcePath(Matrix matrix, int root, int target){

        int vertex = matrix.getVertex();

        int[] isVisited = new int[vertex];
        for (int i = 0; i < isVisited.length; i++) {
            isVisited[i] = -1;
        }
        LinkedList<Integer> stack = new LinkedList<>();
        stack.push(root);
        isVisited[root] = root;
        while(! stack.isEmpty()){
            Integer head  = stack.pollFirst();
            for (Integer son: matrix.connectVertex(head)) {
                if(isVisited[son] == -1){
                    isVisited[son] = head;
                    stack.add(son);
                }
                if(son == target) break;
            }
        }
        if(isVisited[target] == -1 ) return;
        while(isVisited[target] != target ){
            System.out.println(target);
            target = isVisited[target];
        }

    }

    public static void main(String[] args) {
        getSingleSourcePath(new Matrix("complexstructure/src/com/imooc/chart/graph.txt"), 0, 6);

    }

}
