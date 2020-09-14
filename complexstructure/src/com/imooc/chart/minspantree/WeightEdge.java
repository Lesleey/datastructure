package com.imooc.chart.minspantree;

/**
 * @author Lesleey
 * @date 2020/9/13-16:00
 * @function
 */
public class WeightEdge implements Comparable<WeightEdge> {
    int v;
    int w;
    int weight;

    public WeightEdge(int v, int w, int weight){
         this.v = v;
         this.w = w;
         this.weight = weight;
    }

    @Override
    public String toString() {
        return "WeightEdge{" +
                "v=" + v +
                ", w=" + w +
                ", weight=" + weight +
                '}';
    }

    @Override
    public int compareTo(WeightEdge o) {

        return weight - o.weight;
    }
}
