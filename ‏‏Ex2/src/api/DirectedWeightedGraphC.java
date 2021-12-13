package api;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

public class DirectedWeightedGraphC implements DirectedWeightedGraph{
    private HashMap<Integer,HashMap<Integer,EdgeData>> edgeGraphs = new HashMap<>();
    private static int mc = 0;
    private HashMap<Integer,NodeData> nodeList = new HashMap<>();
    private ArrayList<EdgeData> edgeList = new ArrayList<>();
    DirectedWeightedGraphC(HashMap<Integer,HashMap<Integer,EdgeData>> edgeGraphs, HashMap<Integer,NodeData> nodeList,ArrayList<EdgeData> edgeList){
        this.edgeGraphs = edgeGraphs;
        this.edgeList = edgeList;
        this.nodeList = nodeList;
    }
    public DirectedWeightedGraphC(){

    }

    public ArrayList<EdgeData> getEdgeList() {
        return edgeList;
    }

    public HashMap<Integer, HashMap<Integer, EdgeData>> getEdgeGraphs() {
        return edgeGraphs;
    }

    public HashMap<Integer, NodeData> getNodeList() {
        return nodeList;
    }

    @Override
    public NodeData getNode(int key) {
        if (nodeList == null){
            return null;
        }
        return nodeList.get(key);
    }

    @Override
    public EdgeData getEdge(int src, int dest) {
        if (edgeGraphs == null){
            return null;
        }
        if (edgeGraphs.get(src) == null){
            return null;
        }
        return edgeGraphs.get(src).get(dest);
    }

    @Override
    public void addNode(NodeData n) {
        nodeList.put(n.getKey(), n);
        edgeGraphs.put(n.getKey(),null);
        mc++;
    }

    @Override
    public void connect(int src, int dest, double w) {
        EdgeDataC edgeData = new EdgeDataC(src, dest, w);
        if (src == dest){
            return;
        }
        if (nodeList.get(dest) != null && nodeList.get(src) != null) {
            if (edgeGraphs.get(src) != null) {
                edgeGraphs.get(src).put(dest, edgeData);
            } else {
                HashMap<Integer, EdgeData> k = new HashMap<>();
                k.put(dest, edgeData);
                edgeGraphs.put(src, k);
            }
            edgeList.add(edgeData);
            mc++;
        }
    }

    @Override
    public Iterator<NodeData> nodeIter() throws RuntimeException {
        if (nodeList == null){
            return null;
        }
        return nodeList.values().iterator();
    }

    @Override
    public Iterator<EdgeData> edgeIter() throws RuntimeException{
        if (edgeList == null){
            return null;
        }
        return edgeList.iterator();
    }

    @Override
    public Iterator<EdgeData> edgeIter(int node_id) throws RuntimeException{
        if (edgeGraphs == null){
            return null;
        }
        if (edgeGraphs.get(node_id) == null){
            return null;
        }
        return edgeGraphs.get(node_id).values().iterator();
    }

    @Override
    public NodeData removeNode(int key) {
        if (nodeList == null){
            return null;
        }

        if (edgeGraphs == null){
            return null;
        }
        Iterator<NodeData> iterator = nodeIter();
        while (iterator.hasNext()){
            Integer dest1 = iterator.next().getKey();
            removeEdge(key,dest1);
            removeEdge(dest1,key);
        }
        NodeData nodeData = nodeList.get(key);
        if (nodeData != null){
            nodeList.remove(key);
            if (edgeGraphs != null){
                edgeGraphs.remove(key);
            }
            mc++;
        }
        return nodeData;
    }

    @Override
    public EdgeData removeEdge(int src, int dest) {
        if (edgeGraphs == null){
            return null;
        }
        if (edgeGraphs.get(src) == null){
            return null;
        }
        EdgeData edgeData = edgeGraphs.get(src).get(dest);
        edgeGraphs.get(src).remove(dest);
        edgeList.remove(edgeData);
        mc++;
        return edgeData;
    }

    @Override
    public int nodeSize() {
        return nodeList.size();
    }

    @Override
    public int edgeSize() {
        return edgeList.size();
    }

    @Override
    public int getMC() {
        return mc;
    }

}
