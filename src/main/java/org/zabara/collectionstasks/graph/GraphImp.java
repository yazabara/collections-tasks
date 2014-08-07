package org.zabara.collectionstasks.graph;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Yaroslav_Zabara on 2/11/14.
 */
public class GraphImp<T> implements Graph<T, java.lang.Integer> {

    List<List<Integer>> edges = new ArrayList<List<Integer>>();
    List<T> vertices = new ArrayList<T>();
    boolean oriented = false;

    @Override
    public List<T> getVertices() {
        return vertices;
    }

    @Override
    public void addVertex(T value) {
        vertices.add(value);
        makeNewEdge();
    }

    @Override
    public List<Integer> getVertexEdges(T vertex) {
        return edges.get(vertices.indexOf(vertex));
    }

    @Override
    public List<Integer> getVertexEdges(int vertexIndex) {
        if (vertexIndex < 0 || vertexIndex >= vertices.size()) {
            return null;
        }
        return edges.get(vertexIndex);
    }

    @Override
    /**
     * Алгорити Дейкстры
     */
    public List<Integer> getMinEdges(T from) {

        List<Boolean> completeWay = new ArrayList<Boolean>();
        List<List<T>> minWays = new ArrayList<List<T>>();
        List<Integer> minWidth = new ArrayList<Integer>();

        int fromIndex = vertices.indexOf(from);
        if (fromIndex < 0) {
            return null;
        }

        //initializing alg
        for (int i = 0; i < vertices.size(); i++) {
            completeWay.add(false);
            minWidth.add(-1);
            minWays.add(new ArrayList<T>());
        }
        minWidth.set(fromIndex, 0);
        List<T> currWay = new ArrayList<T>();
        currWay.add(vertices.get(fromIndex));
        minWays.set(fromIndex, currWay);

        //пока есть доступные пути
        int curIndex = getNextMinWidthIndex(minWidth, completeWay);
        while (curIndex != -1) {
            for (int i = 0; i < completeWay.size(); i++) {
                //если еще не брали
                if (completeWay.get(i) == false) {
                    //если есть путь
                    if (edges.get(curIndex).get(i) != null) {
                        //если до этого не было пути или путь меньше чем путь до текущего + до предыщушей точки
                        if (minWidth.get(i) > (edges.get(curIndex).get(i) + minWidth.get(curIndex))
                                || minWidth.get(i) == -1) {
                            //задали новую минимальную длину
                            minWidth.set(i, edges.get(curIndex).get(i) + minWidth.get(curIndex));
                            //берем все точки "до" и добавляем к текущему
                            minWays.set(i, new ArrayList<T>(minWays.get(curIndex)));
                            minWays.get(i).add(vertices.get(i));
                        }
                    }
                }
            }
            completeWay.set(curIndex, true);
            curIndex = getNextMinWidthIndex(minWidth, completeWay);
        }
        printMinWidthResults(minWays, minWidth, fromIndex);
        return minWidth;
    }

    private int getNextMinWidthIndex(List<Integer> minWidth, List<Boolean> completeWay) {
        int index = -1;
        for (int i = 0; i < completeWay.size(); i++) {
            //если доступна и уже не прошли
            if (completeWay.get(i) == false && minWidth.get(i) != -1) {
                //если новая точка или путь меньше, чем прошлые
                if (index > minWidth.get(i) || index == -1) {
                    index = i;
                }
            }
        }
        return index;
    }

    private void printMinWidthResults(List<List<T>> minWays, List<Integer> minWidth, Integer fromIndex) {
        for (int i = 0; i < vertices.size(); i++) {
            if (minWidth.get(i) != -1) {
                System.out.println("Min way from " + vertices.get(fromIndex) + " to " + vertices.get(i) + " is " + minWidth.get(i));
                System.out.print("[");
                for (int j = 0; j < minWays.get(i).size(); j++) {
                    System.out.print(minWays.get(i).get(j));
                    if (j == minWays.get(i).size()-1) {
                        System.out.print("]");
                    } else  {
                        System.out.print(" -> ");
                    }

                }
                System.out.println();
            }
        }
    }


    @Override
    public void addEdge(T from, T to, java.lang.Integer cost) {
        int fromIndex = vertices.indexOf(from);
        int toIndex = vertices.indexOf(to);
        if (fromIndex < 0 || toIndex < 0 || fromIndex == toIndex) {
            return;
        }
        edges.get(fromIndex).set(toIndex, cost);
    }

    @Override
    public List<List<Integer>> getEdges() {
        return edges;
    }

    @Override
    public boolean isOriented() {
        return oriented;
    }

    @Override
    public void setOrient(boolean orient) {
        if (!oriented && orient) {
            makeOrientedGraph();
        }
        oriented = orient;
    }

    private void makeOrientedGraph() {
        //дублируем веса
        for (int i = 0; i < edges.size(); i++) {
            for (int j = 0; j < edges.get(i).size(); j++) {
                if (edges.get(j).get(i) != null) {
                    edges.get(i).set(j, edges.get(j).get(i));
                }
            }
        }
    }

    private void makeNewEdge() {
        edges.add(new ArrayList<Integer>(edges.size()));
        for (int i = 0; i < edges.size(); i++) {
            edges.get(i).add(null);//обнуляем пути и доавляем ячейку
            if (i != edges.size() - 1) {
                edges.get(edges.size() - 1).add(null);//добавляем пустые пусти к новой вершине
            }
        }
    }

    private String makeStringResult(List<List<Integer>> curEdges) {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < curEdges.size(); i++) {
            for (int j = 0; j < curEdges.get(i).size(); j++) {
                if (curEdges.get(i).get(j) != null) {
                    stringBuilder.append("[From " + vertices.get(i) + " to " + vertices.get(j) + " cost = " + curEdges.get(i).get(j) + "]  \n");
                }
            }
        }
        return stringBuilder.toString();
    }

    @Override
    public String toString() {
        return makeStringResult(edges);
    }
}
