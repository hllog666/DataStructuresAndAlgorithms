package com.hllog.prim;

import java.util.Arrays;

/**
 * @author hllog
 * @create 2022-08-19 23:47
 */
public class PrimAlgorithm {
    public static void main(String[] args) {
        char[] data = new char[]{'A', 'B', 'C', 'D', 'E', 'F', 'G'};
        int vertexCount = data.length;
        int[][] weights = {
                {10000, 5, 7, 10000, 10000, 10000, 2},
                {5, 10000, 10000, 9, 10000, 10000, 3},
                {7, 10000, 10000, 10000, 8, 10000, 10000},
                {10000, 9, 10000, 10000, 10000, 4, 10000},
                {10000, 10000, 8, 10000, 10000, 5, 4},
                {10000, 10000, 10000, 4, 5, 10000, 6},
                {2, 3, 10000, 10000, 4, 6, 10000}
        };
        MyGraph graph = new MyGraph(vertexCount);
        MinTree minTree = new MinTree();
        minTree.createGraph(graph, vertexCount, data, weights);
        minTree.showGraph(graph);
        minTree.prim(graph, 0);
    }
}

class MinTree {
    /**
     * @param graph       图对象
     * @param vertexCount 图的顶点个数
     * @param data        各个顶点的值
     * @param weights     邻接矩阵
     */
    public void createGraph(MyGraph graph, int vertexCount, char[] data, int[][] weights) {
        int i, j;
        for (i = 0; i < vertexCount; i++) {
            graph.data[i] = data[i];
            for (j = 0; j < vertexCount; j++) {
                graph.weights[i][j] = weights[i][j];
            }
        }
    }

    public void showGraph(MyGraph graph) {
        for (int[] weight : graph.weights) {
            System.out.println(Arrays.toString(weight));
        }
    }

    /**
     * @param graph 图
     * @param v     表示从图的第几个节点开始生成
     */
    public void prim(MyGraph graph, int v) {
        int[] visited = new int[graph.vertexCount];
        visited[v] = 1;
        // 记录两个顶点的下标
        int h1 = -1;
        int h2 = -1;
        int minWeight = 10000;
        for (int k = 0; k < graph.vertexCount; k++) {
            for (int i = 0; i < graph.vertexCount; i++) {
                for (int j = 0; j < graph.vertexCount; j++) {
                    if (visited[i] == 1 && visited[j] == 0 & graph.weights[i][j] < minWeight) {
                        minWeight = graph.weights[i][j];
                        h1 = i;
                        h2 = j;
                    }
                }
            }
            System.out.println("边<" + graph.data[h1] + "," + graph.data[h2] + "> 权值：" + minWeight);
            visited[h2] = 1;
            minWeight = 10000;
        }
    }
}

class MyGraph {
    int vertexCount;
    char[] data;
    int[][] weights;

    public MyGraph(int vertexCount) {
        this.vertexCount = vertexCount;
        data = new char[vertexCount];
        weights = new int[vertexCount][vertexCount];
    }
}