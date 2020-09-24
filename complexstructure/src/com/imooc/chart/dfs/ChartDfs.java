package com.imooc.chart.dfs;

import com.imooc.chart.basicshow.GraphList;
import com.imooc.chart.basicshow.Matrix;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * @author Lesleey
 * @date 2020/8/25-18:33
 * @function 树的深度优先遍历： 先序遍历、后序遍历（和树类似）
 */
public class ChartDfs {
    private static List<Integer> postOrder = new ArrayList<>();
    private static List<Integer> preOrder = new ArrayList<>();
    public static void main(String[] args) {
        //以下三种遍历方式只能遍历只有一个连通分量的图，所以严格的来说应该对所有的顶点都进行 dfs
        dfsMatrix(new Matrix("complexstructure/src/com/imooc/chart/graph.txt"));
    }

    public static void dfs(Matrix matrix){
        boolean[] isvisited = new boolean[matrix.getVertex()];
        for (int i = 0; i < isvisited.length; i++) {
            if(isvisited[i]) continue;
            dfsRecursionMatrix(matrix, i, isvisited);
        }
    }
//-------------------------------------递归-------------------------------------------------------
    /**
     * @param root 当前正在遍历的顶点
     * @param matrix 图
     * @param isVisited  记录当前顶点是否被访问过
     * */
    public static void dfsRecursionMatrix(Matrix matrix, int root,  boolean[] isVisited){
        if(isVisited[root]) return;
        preOrder.add(root);
        //System.out.println(root);
        isVisited[root] = true;
        for (Integer son : matrix.connectVertex(root)) {
            if(isVisited[son]) continue;
            dfsRecursionMatrix(matrix, son, isVisited);
        }
        System.out.println(root); //后序遍历
        postOrder.add(root);
    }

    public static void dfsRecursionGraphList(GraphList graphList, int root, boolean[] isVisited){
        if(isVisited[root]) return;
        System.out.println(root);
        isVisited[root] = true;
        for (Integer son : graphList.connectVertex(root)) {
            if(isVisited[son]) continue;
            dfsRecursionGraphList(graphList, son, isVisited);
        }
    }

    //-------------------------------------非递归-------------------------------------------------------
    public static void  dfsMatrix(Matrix matrix){
        int vertex = matrix.getVertex();
        boolean[] isVisited = new boolean[vertex];
        LinkedList<Integer> stack = new LinkedList<>();
        int root = 0;
        stack.push(root);
        while(! stack.isEmpty()){
            Integer head  = stack.pollFirst();
            List<Integer> conns = new ArrayList<>();
            if(! isVisited[head]) {
                System.out.println(head);
                conns = matrix.connectVertex(head);
            }
            isVisited[head] = true;
            for (int i = conns.size() - 1; i >= 0 ; i --) {
                 if(isVisited[conns.get(i)]) continue;
                 stack.push(conns.get(i));
            }
        }
    }

    public static List<Integer> postOrder(){
        return postOrder;
    }

    public static  List<Integer> preOrder(){
        List<Integer> res = new ArrayList<>(preOrder);
        preOrder.clear();
        return res;
    }

}
