package com.hllog.dynamic;

/**
 * @author hllog
 * @create 2022-08-19 21:32
 */
public class KnapsackProblem {
    public static void main(String[] args) {
        int[] weights = {1, 4, 3};
        int[] values = {1500, 3000, 2000};
        int capacity = 4;
        int n = values.length;

        int[][] commodity = new int[n + 1][capacity + 1];

        // dp[i][j]表示前i个物品装在容量为j的背包中的最大价值
        int[][] dp = new int[n + 1][capacity + 1];
        for (int i = 1; i < dp.length; i++) {
            for (int j = 1; j < dp[0].length; j++) {
                if (weights[i - 1] > j) {
                    dp[i][j] = dp[i - 1][j];
                } else {
                    if (dp[i - 1][j] < values[i - 1] + dp[i - 1][j - weights[i - 1]]) {
                        dp[i][j] = values[i - 1] + dp[i - 1][j - weights[i - 1]];
                        commodity[i][j] = 1;
                    } else {
                        dp[i][j] = dp[i - 1][j];
                    }
                }
            }
        }

        int i = commodity.length - 1;
        int j = commodity[0].length - 1;
        while (i > 0 && j > 0) {
            if (commodity[i][j] == 1) {
                System.out.printf("第%d 个商品放入到背包\n", i);
                j -= weights[i - 1];
            }
            i--;
        }
        for (int[] item : dp) {
            for (int t : item) {
                System.out.print(t + " ");
            }
            System.out.println();
        }
    }
}
