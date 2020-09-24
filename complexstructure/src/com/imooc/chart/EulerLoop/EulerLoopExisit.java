package com.imooc.chart.EulerLoop;

import com.imooc.chart.basicshow.Matrix;

/**
 * @author Lesleey
 * @date 2020/9/11-18:19
 * @function 欧拉回路： 从某个点出发，沿着边行走，经过每个边恰好一次， 然后回到原点（注意： 是每个边恰好一次）。
 *  性质： 对于每个点，进一次，出一次要耗费两条边， 要想每个边的都走一次，回到原点，每个点都必须有进有出。
 *    每个点的相连边数（即每个点的度）必须是偶数。
 *    对于无向连通图，充分必要条件： 每个点的度是偶数 《==》 图存在欧拉回路
 *        对于度为偶数的点肯定存在一个环， 欧拉回路实际上就是多个相连的环
 *    对于有向连通图, 充分必要条件： 每个点的入度等于出度 《==》 图中存在欧拉回路
 *
 *    欧拉路径：
 *      对于无向连通图的充分必要条件： 除了两个点的度数为奇数， 其余每个点的度数为偶数
 *      对于有向连通图的充分必要条件： 除了两个点，其余点的入度等于出度， 这两个点，一个入度比出度大一，一个入度比出度小一
 */
public class EulerLoopExisit {

    public static boolean hasEulerLoop(Matrix matrix){
        int vertex = matrix.getVertex();
        boolean[] isVisited = new boolean[vertex];
/*        int res = 0;
        for (int i = 0; i < vertex; i++) {
            if(! isVisited[i]){
                dfsRecursionMatrix(matrix, i, isVisited);
                res ++;
            }
        }*/
        for (int i = 0; i < matrix.getVertex(); i++) {
            if(!matrix.isDirection() && matrix.degreeVertex(i) % 2 != 0) return false;
            if(matrix.isDirection() && matrix.outDegree(i) != matrix.inDegree(i) ) return false;
        }
        return true;
      /* return res <= 1;*/
    }

    /**
     * @param root 当前正在遍历的顶点
     * @param matrix 图
     * @param isVisited  记录当前顶点是否被访问过
     * */
    public static void dfsRecursionMatrix(Matrix matrix, int root,  boolean[] isVisited){
        if(isVisited[root]) return;
        System.out.println(root);
        isVisited[root] = true;
        for (Integer son : matrix.connectVertex(root)) {
            if(isVisited[son]) continue;
            dfsRecursionMatrix(matrix, son, isVisited);
        }
    }
}
