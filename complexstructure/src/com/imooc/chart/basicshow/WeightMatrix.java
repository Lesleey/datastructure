package com.imooc.chart.basicshow;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * @author Lesleey
 * @date 2020/5/31-17:13
 * @function 使用邻接矩阵表示无向无权图, 存储图时空间太过浪费
 * 空间复杂度 O(v^2 )
 * 时间复杂度 建图 O（E）, 查看是否相邻 O（1） 求一个点的相邻节点 O（v）
 */
public class WeightMatrix {
    //顶点数量
    private int vertex;
    //边的数量
    private int edge;
    //表示图的连接矩阵
    private int[][] matrix;

    public WeightMatrix(String fileName){
        File file = new File(fileName);
        try (Scanner scanner = new Scanner(file);){
            this.vertex = scanner.nextInt();
            this.edge = scanner.nextInt();

            if(vertex <0 || edge < 0) throw  new IllegalArgumentException("参数异常！！");
            matrix = new int[vertex][vertex];

            for (int i = 0; i < edge; i++) {
                int a = scanner.nextInt();
                int b = scanner.nextInt();
                isValid(a);
                isValid(b);
                int weight = scanner.nextInt();
                if(a == b) throw new IllegalArgumentException("不支持自环边");
                if(matrix[a][b] != 0) throw new IllegalArgumentException("不支持平行边");
                matrix[a][b] = matrix[b][a] = weight;
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    //寻找与 v相连的顶点
    public List<Integer> connectVertex(int v){
        isValid(v);
        List<Integer> res = new ArrayList<>();
        for (int i = 0; i < this.vertex; i++) {
            if(matrix[v][i] != 0) res.add(i);
        }
        return res;
    }
    //获取该顶点的度
    public int degreeVertex(int v){
        return connectVertex(v).size();
    }
    public boolean isExistEdge(int v, int w){
        isValid(v);
        isValid(w);
        return matrix[v][w] != 0;
    }

    public int getWeight(int v, int w){
        isValid(v);
        isValid(w);
        return matrix[v][w];
    }
    public int getVertex() {
        return vertex;
    }

    public int getEdge() {
        return edge;
    }

    public void isValid(int v){
        if(v < 0 || v >= vertex) throw new IllegalArgumentException("顶点参数不合法");
    }

    public void removeEdge(int v, int w ){
         isValid(v);
         isValid(w);
         matrix[v][w] = 0;
         matrix[w][v] = 0;
    }
    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(String.format("顶点个数%d, 边的个数%d,\n", vertex, edge));
        for (int i = 0; i < vertex; i++) {
            for (int j = 0; j < vertex; j++) {
                stringBuilder.append(matrix[i][j] + " ");
            }
            stringBuilder.append('\n');
        }
        return stringBuilder.toString();
    }

    public static void main(String[] args) {
        WeightMatrix matrix = new WeightMatrix("complexstructure/src/com/imooc/chart/graph.txt");
        System.out.println(matrix);
    }
}
