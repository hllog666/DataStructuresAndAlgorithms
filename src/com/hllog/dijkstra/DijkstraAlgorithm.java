package com.hllog.dijkstra;

import java.util.Arrays;

/**
 * @author hllog
 * @create 2022-08-20 10:55
 */
public class DijkstraAlgorithm {
    public static void main(String[] args) {
        char[] vertex = {'A', 'B', 'C', 'D', 'E', 'F', 'G'};
        int[][] matrix = new int[vertex.length][vertex.length];
        final int N = 65535;
        matrix[0] = new int[]{N, 5, 7, N, N, N, 2};
        matrix[1] = new int[]{5, N, N, 9, N, N, 3};
        matrix[2] = new int[]{7, N, N, N, 8, N, N};
        matrix[3] = new int[]{N, 9, N, N, N, 4, N};
        matrix[4] = new int[]{N, N, 8, N, N, 5, 4};
        matrix[5] = new int[]{N, N, N, 4, 5, N, 6};
        matrix[6] = new int[]{2, 3, N, N, 4, 6, N};
        Graph graph = new Graph(vertex, matrix);
        graph.showGraph();
        graph.dijkstra(2);
        graph.showDijkstra();
    }
}

class Graph {
    private char[] vertex;
    private int[][] matrix;
    private VisitedVertex visitedVertex;

    public Graph(char[] vertex, int[][] matrix) {
        this.vertex = vertex;
        this.matrix = matrix;
    }

    public void showGraph() {
        for (int[] link : matrix) {
            System.out.println(Arrays.toString(link));
        }
    }

    public void dijkstra(int index) {
        visitedVertex = new VisitedVertex(vertex.length, index);
        update(index);
        for (int i = 0; i < vertex.length; i++) {
            index = visitedVertex.updateArr();
            update(index);
        }
    }

    /**
     * 更新index下标到周围顶点的距离
     * 更新周围顶点的前驱节点
     *
     * @param index
     */
    public void update(int index) {
        int len = 0;
        for (int i = 0; i < matrix[index].length; i++) {
            len = visitedVertex.getDist(index) + matrix[index][i];
            if (!visitedVertex.isVisited(i) && len < visitedVertex.getDist(i)) {
                visitedVertex.updatePre(i, index);
                visitedVertex.updateDist(i, len);
            }
        }
    }

    public void showDijkstra() {
        visitedVertex.show();
    }
}

class VisitedVertex {
    /**
     * 记录各个顶点是否访问过 1 表示访问过,0 未访问,会动态更新
     */
    public int[] alreadyArr;
    /**
     * 每个下标对应的值为前一个顶点下标, 会动态更新
     */
    public int[] preVisited;
    /**
     * 记录出发顶点到其他所有顶点的距离
     */
    public int[] dist;

    public VisitedVertex(int length, int index) {
        this.alreadyArr = new int[length];
        this.preVisited = new int[length];
        this.dist = new int[length];
        Arrays.fill(dist, 65535);
        this.alreadyArr[index] = 1;
        this.dist[index] = 0;
    }

    /**
     * @param index 顶点
     * @return 如果访问过，就返回 true, 否则访问 false
     */
    public boolean isVisited(int index) {
        return alreadyArr[index] == 1;
    }

    /**
     * 更新出发顶点到index的距离
     *
     * @param index index顶点
     * @param len   出发顶点到index的距离
     */
    public void updateDist(int index, int len) {
        dist[index] = len;
    }

    /**
     * 更新pre的前驱节点为index
     *
     * @param pre   pre节点
     * @param index index节点
     */
    public void updatePre(int pre, int index) {
        preVisited[pre] = index;
    }

    /**
     * 返回出发顶点到index的距离
     *
     * @param index index顶点
     * @return 出发顶点到index的距离
     */
    public int getDist(int index) {
        return dist[index];
    }

    /**
     * 继续选择并返回新的访问顶点
     *
     * @return 新的访问顶点
     */
    public int updateArr() {
        int min = 65535, index = 0;
        for (int i = 0; i < alreadyArr.length; i++) {
            if (alreadyArr[i] == 0 && dist[i] < min) {
                min = dist[i];
                index = i;
            }
        }
        alreadyArr[index] = 1;
        return index;
    }

    public void show() {
        System.out.println("==========================");
        for (int i : alreadyArr) {
            System.out.print(i + " ");
        }
        System.out.println();
        for (int i : preVisited) {
            System.out.print(i + " ");
        }
        System.out.println();
        for (int i : dist) {
            System.out.print(i + " ");
        }
        System.out.println();
        char[] vertex = {'A', 'B', 'C', 'D', 'E', 'F', 'G'};
        int count = 0;
        for (int i : dist) {
            if (i != 65535) {
                System.out.print(vertex[count] + "(" + i + ") ");
            } else {
                System.out.println("N ");
            }
            count++;
        }
        System.out.println();
    }
}