package Greedy;

import java.util.Arrays;
import java.util.Scanner;

public class IsConnected {
    public static void main(String[] args) {
        init();
    }

    public static void init (){
        Scanner input = new Scanner (System.in);
        int v = input.nextInt();
        int e = input.nextInt();
        int [][] a = new int [v][v];
        for (int i = 0 ; i < a.length ; i++){
            for (int j = 0; j < a.length; j++) {
                if (i != j )
                    a[i][j] = 10000;
            }
        }

        System.out.println("Enter Edges Between 2 Vertices :");

        for (int i = 0; i < e; i++) {
            int v1 = input.nextInt();
            int v2 = input.nextInt();
            int weight = input.nextInt();
            a[v1 -1][v2-1] = weight;
        }

        System.out.println(" Matrix:-");
        for (int i = 0; i < a.length; i++) {
            System.out.println(Arrays.toString(a[i]));
        }



        if (isConnected(a, v)) {
            System.out.println("Connected.");
        }
        else {
            System.out.println("Not Connected.");
        }
    }

    public static boolean isConnected(int[][] graph, int v) {
        boolean[] visited = new boolean[v];
        dfs(graph, visited, 0);


        for (int i = 0; i < visited.length; i++) {
            if (!visited[i]) {
                return false;
            }
        }

        return true;
    }

    private static void dfs(int[][] graph, boolean[] visited, int node) {
        visited[node] = true;
        for (int i = 0; i < graph.length; i++) {
            if (graph[node][i] != 10000 && !visited[i]) {
                dfs(graph, visited, i);
            }
        }
    }




}
