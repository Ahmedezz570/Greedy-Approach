package Greedy;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Test {
    static class Edge {
        int vertex1, vertex2, weight;

        Edge(int vertex1, int vertex2, int weight) {
            this.vertex1 = vertex1;
            this.vertex2 = vertex2;
            this.weight = weight;
        }

        @Override
        public String toString() {
            return "(" + vertex1 + ", " + vertex2 + ", " + weight + ")";
        }
    }

    public static int[][] init() {
        Scanner input = new Scanner(System.in);

        System.out.print("Enter number of vertices: ");
        int n = input.nextInt();

        int[][] W = new int[n][n];

        System.out.println("Enter the adjacency matrix (use a large number for infinity):");
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                W[i][j] = input.nextInt();
            }
        }

        return W;
    }

    public static List<Edge> prim(int n, int[][] W) {
        int[] nearest = new int[n];
        int[] distance = new int[n];
        List<Edge> F = new ArrayList<>();

        for (int i = 1; i < n; i++) {
            nearest[i] = 0;
            distance[i] = W[0][i];
        }

        for (int k = 1; k < n; k++) {
            int min = Integer.MAX_VALUE;
            int vnear = -1;

            for (int i = 1; i < n; i++) {
                if (distance[i] >= 0 && distance[i] < min) {
                    min = distance[i];
                    vnear = i;
                }
            }

            if (vnear == -1)
                break;

            F.add(new Edge(nearest[vnear] + 1, vnear + 1, W[vnear][nearest[vnear]]));

            distance[vnear] = -1;

            for (int i = 1; i < n; i++) {
                if (W[i][vnear] < distance[i]) {
                    distance[i] = W[i][vnear];
                    nearest[i] = vnear;
                }
            }
        }

        return F;
    }

    public static void main(String[] args) {
        int[][] W = init(); // استدعاء طريقة الإدخال
        int n = W.length; // عدد الرؤوس هو طول المصفوفة

        List<Edge> result = prim(n, W);

        System.out.println("Minimum spanning tree:");
        for (Edge edge : result) {
            System.out.println(edge);
        }
    }
}
