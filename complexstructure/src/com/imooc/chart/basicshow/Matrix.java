package com.imooc.chart.basicshow;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

/**
 * @author Lesleey
 * @date 2020/5/31-17:13
 * @function 使用邻接矩阵表示(有)无向无权图, 存储图时空间太过浪费
 * 空间复杂度 O(v^2 )
 * 时间复杂度 建图 O（E）, 查看是否相邻 O（1） 求一个点的相邻节点 O（v）
 */
public class Matrix implements Cloneable{
    //顶点数量
    private int vertex;
    //边的数量
    private int edge;
    //表示图的连接矩阵
    private int[][] matrix;
    //表示是否是有向图
    private boolean isDirectin;

    private int[] indegrees ;
    private int[] outdegrees;
    public Matrix(String fileName, boolean isDirectin){
        this.isDirectin = isDirectin;
        File file = new File(fileName);
        try (Scanner scanner = new Scanner(file);){
            this.vertex = scanner.nextInt();
            this.edge = scanner.nextInt();
            indegrees = new int[vertex];
            outdegrees = new int[vertex];

            if(vertex <0 || edge < 0) throw  new IllegalArgumentException("参数异常！！");
            matrix = new int[vertex][vertex];

            for (int i = 0; i < edge; i++) {
                int a = scanner.nextInt();
                int b = scanner.nextInt();
                isValid(a);
                isValid(b);
                if(a == b) throw new IllegalArgumentException("不支持自环边");
                if(matrix[a][b] != 0) throw new IllegalArgumentException("不支持平行边");
                matrix[a][b] = 1;
                if(! isDirectin){
                    matrix[b][a] = 1;
                }else{
                    indegrees[b] ++;
                    outdegrees[a] ++;
                }
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public Matrix(String fileName){
        this(fileName, false);
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
        if(isDirectin) throw new RuntimeException("无效的方法");
        isValid(v);
        return connectVertex(v).size();
    }
    //入度
    public int inDegree(int v){
        if(!isDirectin) throw new RuntimeException("无效的方法");
        isValid(v);
        return indegrees[v];
    }

    //出度
    public int outDegree(int v){
        isValid(v);
        return outdegrees[v];
    }
    public boolean isDirection(){
        return isDirectin;
    }
    public boolean isExistEdge(int v, int w){
        isValid(v);
        isValid(w);
        return matrix[v][w] != 0;
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
         if(! this.isDirectin)
                matrix[w][v] = 0;
         else{
             outdegrees[v] --;
             indegrees[w] --;
         }
    }
    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(String.format("顶点个数%d, 边的个数%d, directed = %b\n", vertex, edge, isDirectin));
        for (int i = 0; i < vertex; i++) {
            for (int j = 0; j < vertex; j++) {
                stringBuilder.append(matrix[i][j] + " ");
            }
            stringBuilder.append('\n');
        }
        return stringBuilder.toString();
    }

    public int[] inDegrees(){
        return Arrays.copyOf(indegrees, indegrees.length);
    }
    public int[] outDegrees(){
        return Arrays.copyOf(outdegrees, outdegrees.length);
    }

    public Matrix reverseMatrix()  {
        Matrix matrix = null;
        try {
            matrix = (Matrix) clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        int[][] matrixarr = new int[matrix.getVertex()][matrix.getVertex()];
        matrix.indegrees = new int[matrix.getVertex()];
        matrix.outdegrees = new int[matrix.getVertex()];
        for (int i = 0; i < matrixarr.length; i++) {
            for (int j = 0; j < matrixarr[0].length; j++) {
                 matrixarr[i][j] = this.matrix[j][i];
                 matrix.outdegrees[i] ++;
                 matrix.indegrees[j] ++;
            }
        }
        matrix.matrix = matrixarr;


        return matrix;
    }
    public static void main(String[] args) {
        Matrix matrix = new Matrix("complexstructure/src/com/imooc/chart/graph.txt", true);
        System.out.println(matrix);
    }
}
