package com.hllog.kruskal;

import java.util.Arrays;

/**
 * @author hllog
 * @create 2022-08-20 0:35
 */
public class KruskalAlgorithm {
    private int edgeNumber;
    private char[] vertex;
    private int[][] matrix;
    private static final int INF = Integer.MAX_VALUE;

    public static void main(String[] args) {
        char[] vertex = {'A', 'B', 'C', 'D', 'E', 'F', 'G'};
        int[][] matrix = {
                {0, 12, INF, INF, INF, 16, 14},
                {12, 0, 10, INF, INF, 7, INF},
                {INF, 10, 0, 3, 5, 6, INF},
                {INF, INF, 3, 0, 4, INF, INF},
                {INF, INF, 5, 4, 0, 2, 8},
                {16, 7, 6, INF, 2, 0, 9},
                {14, INF, INF, INF, 8, 9, 0}
        };

        KruskalAlgorithm kruskalAlgorithm = new KruskalAlgorithm(vertex, matrix);
        kruskalAlgorithm.print();

        EdgeData[] edges = kruskalAlgorithm.getEdges();
        System.out.println(Arrays.toString(edges));
        kruskalAlgorithm.sortEdges(edges);
        System.out.println(Arrays.toString(edges));

        kruskalAlgorithm.kruskal();
    }

    public KruskalAlgorithm(char[] vertex, int[][] matrix) {
        int vLen = vertex.length;
        this.vertex = new char[vLen];
        for (int i = 0; i < vertex.length; i++) {
            this.vertex[i] = vertex[i];
        }
        this.matrix = new int[vLen][vLen];
        for (int i = 0; i < vLen; i++) {
            for (int j = 0; j < vLen; j++) {
                this.matrix[i][j] = matrix[i][j];
            }
        }
        for (int i = 0; i < vLen; i++) {
            for (int j = i + 1; j < vLen; j++) {
                if (this.matrix[i][j] != INF) {
                    edgeNumber++;
                }
            }
        }
    }

    public void print() {
        System.out.println("邻接矩阵");
        for (int i = 0; i < vertex.length; i++) {
            for (int j = 0; j < vertex.length; j++) {
                System.out.printf("%12d\t", matrix[i][j]);
            }
            System.out.println();
        }
    }

    private void sortEdges(EdgeData[] edges) {
        for (int i = 0; i < edges.length - 1; i++) {
            for (int j = 0; j < edges.length - 1; j++) {
                if (edges[j].weight > edges[j + 1].weight) {
                    EdgeData temp = edges[j];
                    edges[j] = edges[j + 1];
                    edges[j + 1] = temp;
                }
            }
        }
    }

    private int getPosition(char ch) {
        for (int i = 0; i < vertex.length; i++) {
            if (vertex[i] == ch) {
                return i;
            }
        }
        return -1;
    }

    private EdgeData[] getEdges() {
        int index = 0;
        EdgeData[] edges = new EdgeData[edgeNumber];
        for (int i = 0; i < vertex.length; i++) {
            for (int j = i + 1; j < vertex.length; j++) {
                if (matrix[i][j] != INF) {
                    edges[index++] = new EdgeData(vertex[i], vertex[j], matrix[i][j]);
                }
            }
        }
        return edges;
    }

    /**
     * 获取下标为i的顶点的终点
     *
     * @param ends 各个顶点对应的终点是哪个
     * @param i    顶点对应的下标
     * @return 下标为i的顶点的终点
     */
    private int getEnd(int[] ends, int i) {
        while (ends[i] != 0) {
            i = ends[i];
        }
        return i;
    }

    public void kruskal() {
        int index = 0;
        int[] ends = new int[edgeNumber];
        EdgeData[] rets = new EdgeData[edgeNumber];
        EdgeData[] edges = getEdges();
        System.out.println("图的边集合=" + Arrays.toString(edges) + "共" + edges.length);
        sortEdges(edges);
        for (int i = 0; i < edgeNumber; i++) {
            int p1 = getPosition(edges[i].start);
            int p2 = getPosition(edges[i].end);
            int m = getEnd(ends, p1);
            int n = getEnd(ends, p2);
            if (m != n) {
                ends[m] = n;
                rets[index++] = edges[i];
            }
        }
        System.out.println("最小生成树" + Arrays.toString(rets));
    }
}

class EdgeData {
    char start;
    char end;
    int weight;

    public EdgeData(char start, char end, int weight) {
        this.start = start;
        this.end = end;
        this.weight = weight;
    }

    @Override
    public String toString() {
        return "EdgeData{" +
                "start=" + start +
                ", end=" + end +
                ", weight=" + weight +
                '}';
    }
}