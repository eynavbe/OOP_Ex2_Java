package api;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import java.io.*;
import java.util.*;
import java.util.List;

public class DirectedWeightedGraphAlgorithmsC implements DirectedWeightedGraphAlgorithms{
    final int WHITE = 0;
    final int GRAY = 1;
    final int BLACK = 2;
    private double min = Double.MAX_VALUE;
    private DirectedWeightedGraph directedWeightedGraph;
    private List<Integer> pathMin;
    public DirectedWeightedGraphAlgorithmsC(){}

    public DirectedWeightedGraphAlgorithmsC( DirectedWeightedGraph directedWeightedGraph){
        this.directedWeightedGraph = directedWeightedGraph;
    }

    @Override
    public void init(DirectedWeightedGraph g) {
        GraphView graphView = new GraphView(g);
        graphView.setVisible(true);
    }

    @Override
    public DirectedWeightedGraph getGraph() {
        return this.directedWeightedGraph;
    }

    @Override
    public DirectedWeightedGraph copy() {
        DirectedWeightedGraphC directedWeightedGraphC = (DirectedWeightedGraphC) this.directedWeightedGraph;
        return new DirectedWeightedGraphC(directedWeightedGraphC.getEdgeGraphs(),directedWeightedGraphC.getNodeList(), directedWeightedGraphC.getEdgeList());
    }

    @Override
    public boolean isConnected() {
        Iterator<NodeData> iterator = this.directedWeightedGraph.nodeIter();
        while (iterator != null && iterator.hasNext()){
            NodeData s = iterator.next();
            if (!BFS(s)){
                return false;
            }
        }
        return true;
    }
    
    private boolean BFS(NodeData s){
        boolean connect = true;
        s.setTag(GRAY);
        LinkedList<NodeData> queue = new LinkedList<>();
        queue.add(s);
        while (queue.size() != 0){
            NodeData u = queue.poll();
            Iterator<EdgeData> adj  = this.directedWeightedGraph.edgeIter(u.getKey());
            while (adj != null && adj.hasNext()){
                NodeData v = this.directedWeightedGraph.getNode(adj.next().getDest());
                if (v.getTag() == WHITE){
                    v.setTag(GRAY);
                    queue.add(v);
                }
            }
            u.setTag(BLACK);
        }
        Iterator<NodeData> iterator = this.directedWeightedGraph.nodeIter();
        while (iterator.hasNext()){
            NodeData n = iterator.next();
            if (n.getTag() == WHITE){
                connect = false;
            }else {
                n.setTag(WHITE);
            }
        }
        return connect;
    }

    @Override
    public double shortestPathDist(int src, int dest) {
        if(src == dest){
            return -1;
        }
        List<ForShortestPath> d = findShortestPaths(src,dest);
        if (d.size() > 0){
            return d.get(d.size()-1).getSumWeight();
        }else {
            return -1;
        }
    }

    @Override
    public List<NodeData> shortestPath(int src, int dest) {
        if (src == dest){
            return null;
        }
        List<NodeData> list = new ArrayList<>();
        List<ForShortestPath> d = findShortestPaths(src,dest);
        for (ForShortestPath j: d){
            NodeData nodeData = this.directedWeightedGraph.getNode(j.getValue());
            if (nodeData != null) {
                list.add(nodeData);
            }
        }
        if (list.size() == 0){
            return null;
        }
        return list;
    }

    @Override
    public NodeData center() {
        double max ;
        NodeData nodeDataChoose = null;
        double minMax =Integer.MAX_VALUE;
        Iterator<NodeData> dataIterator = this.directedWeightedGraph.nodeIter();
        while (dataIterator.hasNext()){
            NodeData nodeData = dataIterator.next();
            Iterator<NodeData> dataIterator2 = this.directedWeightedGraph.nodeIter();
            max = 0;
            while (dataIterator2.hasNext()){
                NodeData nodeData2 = dataIterator2.next();
                if (nodeData.getKey()!=nodeData2.getKey()){
                    double maxNew = shortestPathDist(nodeData.getKey(),nodeData2.getKey());
                    if (maxNew == -1){
                        return null;
                    }
                    if (max < maxNew){
                        max = maxNew;
                    }
                }
            }
            if (minMax > max && max != 0){
                minMax = max;
                nodeDataChoose = nodeData;
            }
        }
        return nodeDataChoose;
    }
    /**
     * Checks if the value is in the list */
    private boolean valueInTheList(int n, List<ForShortestPath> path)
    {
        for (ForShortestPath forShortestPath : path) {
            if (forShortestPath.getValue() == n) {
                return false;
            }
        }
        return true;
    }

    /**Checks all tracks, as long as the weight is less than the saved weight,
     *  the test continues whether this track will be the minimum track.
     * The function returns a list of the shortest paths **/
    private List<ForShortestPath> findShortestPaths(int src, int dst){
        HashMap<Integer, HashMap<Integer, EdgeData>> g = ((DirectedWeightedGraphC)this.directedWeightedGraph).getEdgeGraphs();
        Queue<List<ForShortestPath> > queue = new LinkedList<>();
        List<ForShortestPath> path = new ArrayList<>();
        path.add(new ForShortestPath(src,0));
        double wMin = Double.MAX_VALUE;
        List<ForShortestPath> shortest =new ArrayList<>();
        queue.offer(path);
        while (!queue.isEmpty())
        {
            path = queue.poll();
            int last = path.get(path.size() - 1).getValue();
            if (last == dst)
            {
                if (path.get(path.size()-1).getSumWeight() < wMin){
                    shortest = new ArrayList<>();
                    for (ForShortestPath forShortestPath : path) {
                        shortest.add(new ForShortestPath(forShortestPath.getValue(), forShortestPath.getSumWeight()));
                    }
                    wMin = path.get(path.size()-1).getSumWeight();
                }
            }
            HashMap<Integer, EdgeData> f = g.get(last);
            if (f == null){
                break;
            }
            List<Integer> lastNode = new ArrayList<>(f.keySet());
            for (Integer integer : lastNode) {
                if (path.get(path.size() - 1).getSumWeight() > wMin) {
                    break;
                }
                List<ForShortestPath> forShortestPathList = new ArrayList<>(path);
                if (valueInTheList(integer, path)) {
                    double weight = this.directedWeightedGraph.getEdge(forShortestPathList.get(forShortestPathList.size() - 1).getValue(), integer).getWeight();
                    forShortestPathList.add(new ForShortestPath(integer, forShortestPathList.get(forShortestPathList.size() - 1).getSumWeight() + weight));
                    queue.offer(forShortestPathList);
                }
            }
        }
        return shortest;
    }
    
    @Override
    public List<NodeData> tsp(List<NodeData> cities) {
        List<NodeData> listMin = new ArrayList<>();
        pathMin = new ArrayList<>();
        min = Double.MAX_VALUE;
        if (cities != null){
            //sends to the function that checks after placing the first value
            for (int i = 0; i < cities.size(); i++)
            {
                List<ForShortestPath> path = new ArrayList<>();
                ForShortestPath ForShortestPath = new ForShortestPath(cities.get(i).getKey(),0);
                path.add(ForShortestPath);
                boolean[] visited = new boolean[cities.size()];
                visited[i] = true;
                tspShortestRouteI(cities, visited, path);
            }
            if (pathMin != null){
                for (int i = 0; i < pathMin.size()-1; i++) {
                    List<NodeData> short1 = shortestPath(pathMin.get(i),pathMin.get(i+1));
                    listMin.addAll(short1);
                }
            }
        }
        pathMin = null;
        if (listMin.size() == 0){
            return null;
        }
        return listMin;
    }

    /**
    *     The function checks which route is the shortest (with the least weight).
    *     According to the list given when the first value of the route is given.
    *     save the route minimal.
    **/
    private void tspShortestRouteI(List<NodeData> cities, boolean[] visited, List<ForShortestPath> path)
    {
        double sum = 0;
        if (path.size() == cities.size()) {
            for (ForShortestPath forShortestPath : path) {
                sum += forShortestPath.getSumWeight();
            }
            if (sum < min) {
                min = sum;
                pathMin = new ArrayList<>();
                for (ForShortestPath h: path){
                    pathMin.add(h.getValue());
                }
            }
            return;
        }
        for (int i = 0; i < cities.size(); i++) {
            int w = cities.get(i).getKey();
            if (!visited[i]) {
                visited[i] = true;
                List<ForShortestPath> k = findShortestPaths(path.get(path.size()-1).getValue(),w);
                if (k.size() != 0){
                    ForShortestPath ForShortestPath = new ForShortestPath(w,k.get(k.size()-1).getSumWeight());
                    path.add(ForShortestPath);
                    tspShortestRouteI(cities, visited, path);
                    visited[i] = false;
                    path.remove(path.size() - 1);
                }

            }
        }
    }

    @Override
    public boolean save(String file) {
        DirectedWeightedGraph directedWeightedGraph = this.directedWeightedGraph;
        JSONObject obj = new JSONObject();
        JSONArray listNodes = new JSONArray();
        Iterator<NodeData> iteratorNode = directedWeightedGraph.nodeIter();
        while (iteratorNode.hasNext()){
            NodeDataC nodeDataC = (NodeDataC) iteratorNode.next();
            JSONObject objNode = new JSONObject();
            objNode.put("pos", nodeDataC.getPos());
            objNode.put("id", nodeDataC.getKey());
            listNodes.add(objNode);
        }
        obj.put("Nodes", listNodes);
        JSONArray listEdges = new JSONArray();
        Iterator<EdgeData> iteratorEdges = directedWeightedGraph.edgeIter();
        while (iteratorEdges.hasNext()){
            EdgeData edgeData =  iteratorEdges.next();
            JSONObject objNode = new JSONObject();
            objNode.put("src", edgeData.getSrc());
            objNode.put("w", edgeData.getWeight());
            objNode.put("dest", edgeData.getDest());
            listEdges.add(objNode);
        }
        obj.put("Edges", listEdges);
        try (FileWriter fileSave = new FileWriter("data/"+file)) {
            fileSave.write(obj.toJSONString());
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public boolean load(String file) {
        String jsonString = "data/"+file;
        DirectedWeightedGraphC directedWeightedGraphC = new DirectedWeightedGraphC();
        try (Reader reader = new FileReader(jsonString)){
            JSONParser parser = new JSONParser();
            JSONObject jsonObject =  (JSONObject) parser.parse(reader);
            JSONArray nodes = (JSONArray) jsonObject.get("Nodes");
            for (Object node : nodes) {
                JSONObject nodesObject = (JSONObject) node;
                String src = (String) nodesObject.get("pos");
                long id = (Long) nodesObject.get("id");
                directedWeightedGraphC.addNode(new NodeDataC((int) id, src));
            }
            JSONArray edges =(JSONArray)  jsonObject.get("Edges");
            for (Object edge : edges) {
                JSONObject edgesObject = (JSONObject) edge;
                long src = (Long) edgesObject.get("src");
                Double w = (Double) edgesObject.get("w");
                long dest = (Long) edgesObject.get("dest");
                directedWeightedGraphC.connect((int) src, (int) dest, w);
            }
            this.directedWeightedGraph = directedWeightedGraphC;
            try {
                init(this.directedWeightedGraph);
                return true;
            }catch (Exception e){
                return false;
            }
        } catch (ParseException | IOException e) {
            return false;
        }

    }
}

