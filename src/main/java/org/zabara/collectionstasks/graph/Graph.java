package org.zabara.collectionstasks.graph;

import java.util.List;

/**
 * T - тип вершин.
 * M - тип меры, между вершинами.
 * <p/>
 * Created by Yaroslav_Zabara on 2/11/14.
 */
public interface Graph<T, M extends Number> {

	//вершины
	List<T> getVertices();

	//пути от вершины
	List<M> getVertexEdges(T vertice);

	//пути от вершины
	List<M> getVertexEdges(int verticeIndex);

	//матрица всех путей
	List<List<M>> getEdges();

	boolean isOriented();

	void setOrient(boolean orient);

	void addVertex(T value);

	void addEdge(T from, T to, M cost);

    public List<M> getMinEdges(T from);

}
