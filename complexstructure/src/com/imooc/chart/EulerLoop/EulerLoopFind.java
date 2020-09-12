package com.imooc.chart.EulerLoop;

import com.imooc.chart.basicshow.Matrix;

import java.util.*;

/**
 * @author Lesleey
 * @date 2020/9/11-19:17
 * @function 寻找欧拉回路
 */
public class EulerLoopFind {
    public static void main(String[] args) {
        Matrix matrix = new Matrix("complexstructure/src/com/imooc/chart/graph.txt");
        findLoopFind(matrix, 0, new HashSet<>(), new ArrayList<>());
        for (List<Integer> path : paths) {
            path.forEach(System.out::print);
            System.out.println();
        }
        findLoopFindHierholzer(matrix, 0);
    }
    private static int head = 0;
    private static List<List<Integer>> paths = new ArrayList<>();

    /**
     * 1. 暴力解法（回溯法，不建议）
     * */
    public static void findLoopFind(Matrix matrix, int root, Set<String> isVisited, List<Integer> tmppath){
        if(root == head && isVisited.size() == matrix.getEdge()){
            paths.add(new ArrayList<>(tmppath));
            return;
        }
        for (int son: matrix.connectVertex(root)) {
            String key = root < son ? root + ":" + son: son + ":" + root;
            if(! isVisited.add(key)) continue;
            tmppath.add(root);
            findLoopFind(matrix, son, isVisited, tmppath);
            tmppath.remove(tmppath.size() - 1);
            isVisited.remove(key);
        }
    }

    /**
     * Fleury 算法：每次遍历一个边，就将该边从图中暂时删去，然后寻找相邻不为桥的边进行dfs, 时间复杂度为O（(v +e)^2）
     * */

    /**
     * Hierholzer 算法： 寻找环，然后与环相邻的环...时间复杂度为O（v）
     * */
    public static void findLoopFindHierholzer(Matrix matrix, int root){
        //1. 首先判断是否是连通的，然后判断所有的顶点的度是否为偶数， 省略

        List<Integer> res = new ArrayList<>();
        LinkedList<Integer> stack = new LinkedList<>();
        stack.push(root);
        int curV = root;
        while(! stack.isEmpty()){
            if(matrix.degreeVertex(curV) > 0){
                Integer next = matrix.connectVertex(curV).iterator().next();
                matrix.removeEdge(curV, next);
                curV = next;
                stack.push(curV);
            }else{
                curV = stack.poll();
                res.add(curV);
            }
        }
        res.forEach(System.out::print);
    }
}
