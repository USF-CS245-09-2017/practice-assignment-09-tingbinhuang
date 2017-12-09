/*

Practice Assignment 9, CS 245, Section I 
Author: Tingbin Huang 

*/

public class GraphAdjMatrix implements Graph {

	int vertexNum;
	int[][] list;

	public GraphAdjMatrix(int vertices) {
		list = new int[vertices][vertices];
		this.vertexNum = vertices;
	}

	public int getEdge(int v1, int v2) {
		return list[v1][v2];
	}

	public void addEdge(int v1, int v2, int w) {
		list[v2][v1] = w;
		list[v1][v2] = w;
	}

	public int spanning(int[][] graph) {
		int parent[] = new int[vertexNum];

		int key[] = new int[vertexNum];

		Boolean mstSet[] = new Boolean[vertexNum];

		for (int i = 0; i < vertexNum; i++) {
			key[i] = Integer.MAX_VALUE;
			mstSet[i] = false;
		}

		key[0] = 0;
		parent[0] = -1;

		for (int count = 0; count < vertexNum - 1; count++) {
			int u = minimun(key, mstSet);
			mstSet[u] = true;
			for (int v = 0; v < vertexNum; v++) {
				if (graph[u][v] != 0 && mstSet[v] == false && graph[u][v] < key[v]) {
					parent[v] = u;
					key[v] = graph[u][v];
				}
			}
		}
		int weightNum = 0;

		for (int i = 1; i < vertexNum; i++)
			weightNum += graph[i][parent[i]];

		return weightNum;
	}

	int minimun(int key[], Boolean mstSet[]) {
		int min = Integer.MAX_VALUE, min_index = -1;

		for (int v = 0; v < vertexNum; v++)
			if (mstSet[v] == false && key[v] < min) {
				min = key[v];
				min_index = v;
			}

		return min_index;
	}
	
	public int createSpanningTree() {
		return spanning(list);
	}
}
