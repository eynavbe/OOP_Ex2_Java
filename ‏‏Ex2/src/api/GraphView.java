package api;

import java.awt.*;
import java.awt.event.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.Line2D;
import java.util.*;
import java.util.List;
import javax.swing.*;

//Displays the graph via GUI
public class GraphView extends JFrame implements ActionListener{
    private final int screenHeight = 650;
    private final int screenWidth = 650;
    private Graphics graph;
    DirectedWeightedGraph directedWeightedGraph;
    private String menuI = "";
    private JPanel panelTsp,panelShortest,panelRemoveNode,panelAddNode,panelConnect;
    private JFrame frameTsp,frameRemoveNode;
    private List<NodeData> nodeDataList;
    private List<JRadioButton> radioButtonNode;
    private List<JCheckBox> checkBoxesTsp;
    private String[] pointsTsp;
    private JButton newCTsp,newCRemove;
    private int src = 0, dest1 = 0;
    private JComboBox cBoxShortest1;
    private JComboBox cBoxShortest2;
//    private int x = 0;
    private JTextField tf1,tf2,tf3,tfConnect;
    private NodeData nodeData;
    GraphView(DirectedWeightedGraph directedWeightedGraph) {
        if (directedWeightedGraph == null){
            return;
        }
        this.directedWeightedGraph = directedWeightedGraph;
        initGUI();
    }
    /**
     * remove All Content Pane */
    private void removeAllPane(){
        this.getContentPane().removeAll();
    }
    private void addNode(){
        this.getContentPane().removeAll();
        panelAddNode = new JPanel();
        JLabel label1 = new JLabel("Enter node:");
         tf1 = new JTextField(4);
        JLabel label2 = new JLabel("Enter latitude:");
        tf2 = new JTextField(9);
        JLabel label3 = new JLabel("Enter longitude:");
        tf3 = new JTextField(9);
        label1.setForeground(Color.blue);
        label2.setForeground(Color.blue);
        label3.setForeground(Color.blue);
        JButton send = new JButton("Send");
        panelAddNode.add(label1);
        panelAddNode.add(tf1);
        panelAddNode.add(label2);
        panelAddNode.add(tf2);
        panelAddNode.add(label3);
        panelAddNode.add(tf3);
        panelAddNode.add(send);
        send.addActionListener(this);
        this.getContentPane().add(BorderLayout.SOUTH,panelAddNode);
        panelAddNode.setVisible(false);
    }
    private void removeNode(){
        this.getContentPane().removeAll();
        frameRemoveNode = new JFrame();
        frameRemoveNode.setSize(screenWidth,screenHeight);
        frameRemoveNode.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        panelRemoveNode = new JPanel();
        JLabel l =new JLabel("Choose point to remove: ");
        Iterator<NodeData> nodeDataIterator = this.directedWeightedGraph.nodeIter();
        radioButtonNode = new ArrayList<>();
        ButtonGroup group = new ButtonGroup();
        group.add(new JRadioButton(String.valueOf(1)));
        panelRemoveNode.add(l);
        int a = 0;
        while (nodeDataIterator.hasNext()){
            int w = nodeDataIterator.next().getKey();
            pointsTsp[a] = (String.valueOf(w));
            a++;
            JRadioButton r = new JRadioButton(String.valueOf(w));
            group.add(r);
            radioButtonNode.add(r);
        }
        radioButtonNode.get(0).setSelected(true);
        JButton b2Node=new JButton("select");
        b2Node.addActionListener(this);
        for (JRadioButton jRadioButton : radioButtonNode) {
            panelRemoveNode.add(jRadioButton);
        }
        panelRemoveNode.add(b2Node);
        frameRemoveNode.getContentPane().add(BorderLayout.CENTER,panelRemoveNode);
        panelRemoveNode.setVisible(false);
        frameRemoveNode.setVisible(false);
        newCRemove = new JButton("new choice");
        newCRemove.addActionListener(this);
        this.getContentPane().add(BorderLayout.SOUTH,newCRemove);
        newCRemove.setVisible(false);
    }

    private void connect(){
        this.getContentPane().removeAll();
        panelConnect = new JPanel();
        Iterator<NodeData> nodeDataIterator = this.directedWeightedGraph.nodeIter();
        String[] pointsTsp = new String[this.directedWeightedGraph.nodeSize()];
        int a = 0;
        while (nodeDataIterator.hasNext()){
            int w = nodeDataIterator.next().getKey();
            pointsTsp[a] = (String.valueOf(w));
            a++;
        }
        cBoxShortest1 = new JComboBox(pointsTsp);
        cBoxShortest2 = new JComboBox(pointsTsp);
        JLabel l2shortest = new JLabel("select src: ");
        JLabel l1shortest = new JLabel("select dest: ");
        l2shortest.setForeground(Color.blue);
        l1shortest.setForeground(Color.blue);
        JButton b2shortest=new JButton("sel");
        JLabel l1weight = new JLabel("weight: ");
        l1weight.setForeground(Color.blue);
        b2shortest.addActionListener(this);
        panelConnect.add(l2shortest);
        panelConnect.add(cBoxShortest1);
        panelConnect.add(l1shortest);
        panelConnect.add(cBoxShortest2);
        panelConnect.add(l1weight);
        tfConnect = new JTextField(5);
        panelConnect.add(tfConnect);
        panelConnect.add(b2shortest);
        this.getContentPane().add(BorderLayout.SOUTH,panelConnect);
        panelConnect.setVisible(false);
        panelShortest.setVisible(false);
        frameTsp.setVisible(false);
        panelTsp.setVisible(false);
        newCTsp.setVisible(false);
    }


    /**
     * Displays the shortestPathDist+shortestPath panel*/
    private void shortestPathDistS(){
        this.getContentPane().removeAll();
        panelShortest = new JPanel();
        Iterator<NodeData> nodeDataIterator = this.directedWeightedGraph.nodeIter();
        String[] pointsTsp = new String[this.directedWeightedGraph.nodeSize()];
        int a = 0;
        while (nodeDataIterator.hasNext()){
           int w = nodeDataIterator.next().getKey();
            pointsTsp[a] = (String.valueOf(w));
            a++;
        }
        cBoxShortest1 = new JComboBox(pointsTsp);
        cBoxShortest2 = new JComboBox(pointsTsp);
        JLabel l2shortest = new JLabel("select src: ");
        JLabel l1shortest = new JLabel("select dest: ");
        l2shortest.setForeground(Color.blue);
        l1shortest.setForeground(Color.blue);
        JButton b2shortest=new JButton("sel");
        b2shortest.addActionListener(this);
        panelShortest.add(l2shortest);
        panelShortest.add(cBoxShortest1);
        panelShortest.add(l1shortest);
        panelShortest.add(cBoxShortest2);
        panelShortest.add(b2shortest);
        this.getContentPane().add(BorderLayout.SOUTH,panelShortest);
        panelShortest.setVisible(false);
        frameTsp.setVisible(false);
        panelTsp.setVisible(false);
        newCTsp.setVisible(false);
    }
    private void removeNode(int key){
        this.directedWeightedGraph.removeNode(key);
        repaint();
    }
    private void removeEdge(int src,int dest){
        this.directedWeightedGraph.removeEdge(src,dest);
        repaint();
    }
    /**
     * Displays the tsp panel*/
    private void CheckBoxTsp(){
        this.getContentPane().removeAll();
        panelTsp = new JPanel();
        frameTsp = new JFrame();
        frameTsp.setSize(screenWidth,screenHeight);
        frameTsp.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JLabel l =new JLabel("Choose pointsTsp: ");
        Iterator<NodeData> nodeDataIterator = this.directedWeightedGraph.nodeIter();
        pointsTsp = new String[this.directedWeightedGraph.nodeSize()];
        checkBoxesTsp = new ArrayList<>();
        int a = 0;
        while (nodeDataIterator.hasNext()){
            int w = nodeDataIterator.next().getKey();
            pointsTsp[a] = (String.valueOf(w));
            a++;
            checkBoxesTsp.add(new JCheckBox(String.valueOf(w)));
        }
        JButton b = new JButton("Choose");
        b.addActionListener(this);
        panelTsp.add(l);
        for (JCheckBox c: checkBoxesTsp){
            panelTsp.add(c);
        }
        panelTsp.add(b);
        panelTsp.setPreferredSize(new Dimension(screenWidth, 100));
        frameTsp.getContentPane().add(BorderLayout.CENTER,panelTsp);
        panelTsp.setVisible(false);
        frameTsp.setVisible(false);
        newCTsp = new JButton("new choice");
        newCTsp.addActionListener(this);
        this.getContentPane().add(BorderLayout.SOUTH,newCTsp);
        newCTsp.setVisible(false);
    }

    /**
     * Initializes the screen*/
    private void initGUI() {
        this.setSize(screenWidth,screenHeight);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle("Graph View");
        MenuBar menuBar = new MenuBar();
        Menu menu = new Menu("DirectedWeightedGraphAlgorithms");
        Menu menu2 = new Menu("DirectedWeightedGraph");
        menuBar.add(menu);
        menuBar.add(menu2);
        this.setMenuBar(menuBar);
        MenuItem item1 = new MenuItem("getGraph");
        item1.addActionListener(this);
        MenuItem item2 = new MenuItem("isConnected");
        item2.addActionListener(this);
        MenuItem item3 = new MenuItem("shortestPathDist+shortestPath");
        item3.addActionListener(this);
        MenuItem item4 = new MenuItem("center");
        item4.addActionListener(this);
        MenuItem item5 = new MenuItem("tsp");
        item5.addActionListener(this);
        MenuItem item6 = new MenuItem("removeNode");
        item6.addActionListener(this);
        MenuItem item7 = new MenuItem("removeEdge");
        item7.addActionListener(this);
        MenuItem item8 = new MenuItem("getNode");
        item8.addActionListener(this);
        MenuItem item9 = new MenuItem("getEdge");
        item9.addActionListener(this);
        MenuItem item10 = new MenuItem("addNode");
        item10.addActionListener(this);
        MenuItem item11 = new MenuItem("connect");
        item11.addActionListener(this);
        MenuItem item12 = new MenuItem("nodeSize");
        item12.addActionListener(this);
        MenuItem item13 = new MenuItem("edgeSize");
        item13.addActionListener(this);
        menu.add(item1);
        menu.add(item2);
        menu.add(item3);
        menu.add(item4);
        menu.add(item5);
        menu2.add(item6);
        menu2.add(item7);
        menu2.add(item8);
        menu2.add(item9);
        menu2.add(item10);
        menu2.add(item11);
        menu2.add(item12);
        menu2.add(item13);

        CheckBoxTsp();
        shortestPathDistS();
        addNode();
        connect();
    }

    /**
     * Draws the arrowhead to the line */
    private void drawArrow(Graphics2D g2d, Line2D.Double line) {
        AffineTransform at = new AffineTransform();
        Polygon polygon = new Polygon();
        polygon.addPoint(0, 5);
        polygon.addPoint(-5, -5);
        polygon.addPoint(5, -5);
        at.setToIdentity();
        double a = Math.atan2(line.y1 - line.y2, line.x1 - line.x2);
        at.translate(line.x1, line.y1);
        at.rotate(a - Math.PI / 2d);
        Graphics2D g = (Graphics2D) g2d.create();
        g.setTransform(at);
        g.fill(polygon);
        g.dispose();
    }

    /**
     * Draws the graph*/
    private void getGraphPaint() {
        final int start = 70;
        final int graphDimensions = 500;
        final int radius = 5;
        Iterator<NodeData> iteratorNode = directedWeightedGraph.nodeIter();
        double min_lat = Integer.MAX_VALUE;
        double min_lon = Integer.MAX_VALUE;
        double max_lat = Integer.MIN_VALUE;
        double max_lon = Integer.MIN_VALUE;
        NodeData nodeDataC;
        while (iteratorNode.hasNext()) {
            nodeDataC = iteratorNode.next();
            min_lat = Math.min(min_lat, nodeDataC.getLocation().x());
            min_lon = Math.min(min_lon, nodeDataC.getLocation().y());
            max_lat = Math.max(max_lat, nodeDataC.getLocation().x());
            max_lon = Math.max(max_lon, nodeDataC.getLocation().y());
        }
        double latExtent = max_lat - min_lat;
        double lonExtent = max_lon - min_lon;
        iteratorNode = directedWeightedGraph.nodeIter();
        while (iteratorNode.hasNext()){
            nodeDataC = iteratorNode.next();
            graph.setColor(new Color(128,0,0));
            int ly = start + (int)(graphDimensions - ( (graphDimensions * (nodeDataC.getLocation().x() - min_lat)) / latExtent));
            int lx = start + (int)((graphDimensions * (nodeDataC.getLocation().y() - min_lon)) / lonExtent);
            graph.fillOval(lx - radius, ly - radius, 2 * radius, 2 * radius);
            graph.drawString((String.valueOf(nodeDataC.getKey())), lx - radius, ly - radius);
            Iterator<EdgeData> edgeIter = directedWeightedGraph.edgeIter(nodeDataC.getKey());
            if (edgeIter == null){
                if (menuI.equals("getNode")) {
                    graph.setColor(Color.RED);
                    int ly3 = start + (int) (graphDimensions -((graphDimensions * (nodeData.getLocation().x() - min_lat)) / latExtent));
                    int lx3 = start + (int) (((graphDimensions * (nodeData.getLocation().y() - min_lon)) / lonExtent));
                    graph.fillOval(lx3 - radius, ly3 - radius, 3 * radius, 3 * radius);
                    graph.setColor(Color.BLACK);
                    panelShortest.setVisible(false);
                    newCRemove.setVisible(true);
                }
                if (menuI.equals("removeNode")){
                    panelShortest.setVisible(false);
                    newCRemove.setVisible(true);
                }
                if (menuI.equals("connect")){
                    connect();
                    panelConnect.setVisible(true);
                }
                if (menuI.equals("addNode")){
                    addNode();
                    panelAddNode.setVisible(true);
                }
            }
            while (edgeIter != null && edgeIter.hasNext()) {
                EdgeData edgeData = edgeIter.next();
                graph.setColor(new Color(0,128,128));
                NodeData dest = directedWeightedGraph.getNode(edgeData.getDest());
                int lyDest =  start + (int)((graphDimensions - ((graphDimensions * (dest.getLocation().x() - min_lat)) / latExtent)));
                int lxDest = start + (int) (((graphDimensions * (dest.getLocation().y() - min_lon)) / lonExtent));
                int lxNew2 = (int) (0.9 * (lxDest - lx));
                int lxNew2Start = (int) (0.1 * (lxDest - lx));
                int lyNew2 = (int) (0.9 * (lyDest - ly));
                int lyNew2Start = (int) (0.1 * (lyDest - ly));
                Line2D.Double line = new Line2D.Double(lx + lxNew2, ly + lyNew2, lx + lxNew2Start, ly + lyNew2Start);
                graph.drawLine(lx + lxNew2,  ly + lyNew2,  lx + lxNew2Start,  ly + lyNew2Start);
                drawArrow((Graphics2D) graph, line);
                graph.setColor(Color.BLACK);
                if (menuI != null){
                    if (menuI.equals("getGraph")){
                        double dist = nodeDataC.getLocation().distance(dest.getLocation());
                        graph.drawString(String.format("%.2f km", dist), ((lx + (int) (0.5 * (lxDest - lx)))), ((ly) + (int) (0.5 * (lyDest - ly))));
                        removeAllPane();
                    }
                    if (menuI.equals("shortestPathDist+shortestPath")){
                        double dist = this.directedWeightedGraph.getEdge( nodeDataC.getKey(),dest.getKey()).getWeight();
                        graph.drawString(String.format("%.2f", dist), ((lx + (int) (0.7 * (lxDest - lx)))), ((ly) + (int) (0.7 * (lyDest - ly))));
//                        List<NodeData> nodeDataList = new DirectedWeightedGraphAlgorithmsC(this.directedWeightedGraph).shortestPath(src,dest1);
                        if (nodeDataList != null && nodeDataList.size() > 0) {
                            for (int j = 0; j < nodeDataList.size() - 1; j++) {
                                graph.setColor(new Color(0, 255, 0));
                                int ly3 = start + (int) (graphDimensions - ((graphDimensions * (nodeDataList.get(j).getLocation().x() - min_lat)) / latExtent));
                                int lx3 = start + (int) (((graphDimensions * (nodeDataList.get(j).getLocation().y() - min_lon)) / lonExtent));
                                int lyDest1 = start + (int) (graphDimensions - ((graphDimensions * (nodeDataList.get(j + 1).getLocation().x() - min_lat)) / latExtent));
                                int lxDest1 = start + (int) ((graphDimensions * (nodeDataList.get(j + 1).getLocation().y() - min_lon) / lonExtent));
                                int lxNew3 = (int) (0.9 * (lxDest1 - lx3));
                                int lyNew3 = (int) (0.9 * (lyDest1 - ly3));
                                Line2D.Double line1 = new Line2D.Double(lx3 + lxNew3, ly3 + lyNew3, lx3, ly3);
                                drawArrow((Graphics2D) graph, line1);
                                graph.drawLine(lx3, ly3, (lx3 + lxNew3), (ly3 + lyNew3));
                                graph.setColor(Color.RED);
                                graph.fillOval(lx3 - radius, ly3 - radius, (int) (2.5 * radius), (int) (2.5 * radius));
                            }
                            int ly3 = start + (int) (graphDimensions - ((graphDimensions * (nodeDataList.get(nodeDataList.size() - 1).getLocation().x() - min_lat)) / latExtent));
                            int lx3 = start + (int) (((graphDimensions * (nodeDataList.get(nodeDataList.size() - 1).getLocation().y() - min_lon)) / lonExtent));
                            graph.setColor(Color.RED);
                            graph.fillOval((lx3 - radius), (ly3 - radius), (int) (2.5 * radius), (int) (2.5 * radius));
                        }
                        shortestPathDistS();
                        panelShortest.setVisible(true);
                    }
                    if (menuI.equals("isConnected")){
                        removeAllPane();
                    }
                    if (menuI.equals("center")) {
                        panelShortest.setVisible(false);
//                        NodeData center = new DirectedWeightedGraphAlgorithmsC(this.directedWeightedGraph).center();
                        removeAllPane();
                        if (nodeData != null) {
                            graph.setColor(Color.RED);
                            int ly3 = start + (int) (graphDimensions - ((graphDimensions * (nodeData.getLocation().x() - min_lat)) / latExtent));
                            int lx3 = start + (int) (((graphDimensions * (nodeData.getLocation().y() - min_lon)) / lonExtent));
                            graph.fillOval(lx3 - radius, ly3 - radius, 3 * radius, 3 * radius);
                            graph.setColor(Color.BLACK);
                            double dist = this.directedWeightedGraph.getEdge(nodeDataC.getKey(), dest.getKey()).getWeight();
                            graph.drawString(String.format("%.2f", dist), ((lx + (int) (0.7 * (lxDest - lx)))), ((ly) + (int) (0.7 * (lyDest - ly))));
                        }
                    }
                    if (menuI.equals("getEdge")) {
                        graph.setColor(Color.RED);
                        int ly3 = start + (int) (graphDimensions -((graphDimensions * (this.directedWeightedGraph.getNode(src).getLocation().x() - min_lat)) / latExtent));
                        int lx3 = start + (int) (((graphDimensions * (this.directedWeightedGraph.getNode(src).getLocation().y() - min_lon)) / lonExtent));
                        graph.fillOval(lx3 - radius, ly3 - radius, 3 * radius, 3 * radius);
                        int ly32 = start + (int) (graphDimensions -((graphDimensions * (this.directedWeightedGraph.getNode(dest1).getLocation().x() - min_lat)) / latExtent));
                        int lx32 = start + (int) (((graphDimensions * (this.directedWeightedGraph.getNode(dest1).getLocation().y() - min_lon)) / lonExtent));
                        graph.fillOval(lx32 - radius, ly32 - radius, 3 * radius, 3 * radius);
                        graph.setColor(new Color(0, 255, 0));
                        int ly31 = start + (int) (graphDimensions - ((graphDimensions * (this.directedWeightedGraph.getNode(src).getLocation().x() - min_lat)) / latExtent));
                        int lx31 = start + (int) (((graphDimensions * (this.directedWeightedGraph.getNode(src).getLocation().y() - min_lon)) / lonExtent));
                        int lyDest1 = start + (int) ((graphDimensions - ((graphDimensions * (this.directedWeightedGraph.getNode(dest1).getLocation().x() - min_lat)) / latExtent)));
                        int lxDest1 = start + (int) (((graphDimensions * (this.directedWeightedGraph.getNode(dest1).getLocation().y() - min_lon)) / lonExtent));
                        int lxNew3 = (int) (0.9 * (lxDest1 - lx31));
                        int lyNew3 = (int) (0.9 * (lyDest1 - ly31));
                        Line2D.Double line1 = new Line2D.Double(lx3 + lxNew3, ly3 + lyNew3, lx3, ly3);
                        drawArrow((Graphics2D) graph, line1);
                        graph.drawLine(lx3, ly3, (lx3 + lxNew3), (ly3 + lyNew3));
                        graph.setColor(Color.BLACK);
                        double dist = nodeDataC.getLocation().distance(dest.getLocation());
                        graph.drawString(String.format("%.2f km", dist), ((lx + (int) (0.5 * (lxDest - lx)))), ((ly) + (int) (0.5 * (lyDest - ly))));
                        shortestPathDistS();
                        panelShortest.setVisible(true);
                    }
                    if (menuI.equals("getNode") && nodeData!=null) {
//                        NodeData center = this.directedWeightedGraph.getNode(x);
                        graph.setColor(Color.RED);
                        int ly3 = start + (int) (graphDimensions -((graphDimensions * (nodeData.getLocation().x() - min_lat)) / latExtent));
                        int lx3 = start + (int) (((graphDimensions * (nodeData.getLocation().y() - min_lon)) / lonExtent));
                        graph.fillOval(lx3 - radius, ly3 - radius, 3 * radius, 3 * radius);
                        graph.setColor(Color.BLACK);
                        double dist = nodeDataC.getLocation().distance(dest.getLocation());
                        graph.drawString(String.format("%.2f km", dist), ((lx + (int) (0.5 * (lxDest - lx)))), ((ly) + (int) (0.5 * (lyDest - ly))));
                        panelShortest.setVisible(false);
                        newCRemove.setVisible(true);
                    }
                if (menuI.equals("tsp")){
                    double dist = this.directedWeightedGraph.getEdge( nodeDataC.getKey(),dest.getKey()).getWeight();
                    graph.drawString(String.format("%.2f", dist), ((lx + (int) (0.7 * (lxDest - lx)))), ((ly) + (int) (0.7 * (lyDest - ly))));
                    if (nodeDataList != null && nodeDataList.size() > 0) {
                        for (int k = 0; k < nodeDataList.size() - 1; k++) {
                            graph.setColor(new Color(0, 255, 0));
                            int ly3 = start + (int) (graphDimensions - ((graphDimensions * (nodeDataList.get(k).getLocation().x() - min_lat)) / latExtent));
                            int lx3 = start + (int) (((graphDimensions * (nodeDataList.get(k).getLocation().y() - min_lon)) / lonExtent));
                            int lyDest1 = start + (int) ((graphDimensions - ((graphDimensions * (nodeDataList.get(k + 1).getLocation().x() - min_lat)) / latExtent)));
                            int lxDest1 = start + (int) (((graphDimensions * (nodeDataList.get(k + 1).getLocation().y() - min_lon)) / lonExtent));
                            int lxNew3 = (int) (0.9 * (lxDest1 - lx3));
                            int lyNew3 = (int) (0.9 * (lyDest1 - ly3));
                            Line2D.Double line1 = new Line2D.Double(lx3 + lxNew3, ly3 + lyNew3, lx3, ly3);
                            drawArrow((Graphics2D) graph, line1);
                            graph.drawLine(lx3, ly3, (lx3 + lxNew3), (ly3 + lyNew3));
                            graph.setColor(Color.RED);
                            graph.fillOval((lx3 - radius), (ly3 - radius), (int) (2.5 * radius), (int) (2.5 * radius));
                        }
                        int ly3 = start + (int) (graphDimensions - (graphDimensions * (nodeDataList.get(nodeDataList.size() - 1).getLocation().x() - min_lat) / latExtent));
                        int lx3 = start + (int) ((graphDimensions * (nodeDataList.get(nodeDataList.size() - 1).getLocation().y() - min_lon) / lonExtent));
                        graph.setColor(Color.RED);
                        graph.fillOval((lx3 - radius), (ly3 - radius), (int) (2.5 * radius), (int) (2.5 * radius));
                    }
                    panelShortest.setVisible(false);
                    newCTsp.setVisible(true);
                }
                if (menuI.equals("removeNode")){
                    graph.setColor(Color.BLACK);
                    double dist = nodeDataC.getLocation().distance(dest.getLocation());
                    graph.drawString(String.format("%.2f km", dist), ((lx + (int) (0.5 * (lxDest - lx)))), ((ly) + (int) (0.5 * (lyDest - ly))));
                    panelShortest.setVisible(false);
                    newCRemove.setVisible(true);
                }
                if (menuI.equals("removeEdge")){
                    graph.setColor(Color.BLACK);
                    double dist = nodeDataC.getLocation().distance(dest.getLocation());
                    graph.drawString(String.format("%.2f km", dist), ((lx + (int) (0.5 * (lxDest - lx)))), ((ly) + (int) (0.5 * (lyDest - ly))));
                    shortestPathDistS();
                    panelShortest.setVisible(true);
                }
                if (menuI.equals("connect")){
                    graph.setColor(Color.BLACK);
                    double dist = this.directedWeightedGraph.getEdge( nodeDataC.getKey(),dest.getKey()).getWeight();
                    graph.drawString(String.format("%.2f", dist), ((lx + (int) (0.7 * (lxDest - lx)))), ((ly) + (int) (0.7 * (lyDest - ly))));
                    connect();
                    panelConnect.setVisible(true);
                }
                if (menuI.equals("addNode")){
                    double dist = nodeDataC.getLocation().distance(dest.getLocation());
                    graph.drawString(String.format("%.2f km", dist), ((lx + (int) (0.5 * (lxDest - lx)))), ((ly) + (int) (0.5 * (lyDest - ly))));
                    addNode();
                    panelAddNode.setVisible(true);
                }
                }else {
                    removeAllPane();
                    double dist = nodeDataC.getLocation().distance(dest.getLocation());
                    graph.drawString(String.format("%.2f km", dist), ((lx + (int) (0.5 * (lxDest - lx)))), ((ly) + (int) (0.5 * (lyDest - ly))));
                }
            }
        }
    }

    @Override
    public void paintComponents(Graphics g) {
        this.graph = g;
        getGraphPaint();
    }

    public void paint(Graphics g) {
        Image image = createImage(screenWidth,screenHeight );
        graph = image.getGraphics();
        paintComponents(graph);
        g.drawImage(image, 0, 0, this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String str = e.getActionCommand();
        if (str.equals("getGraph")) {
            menuI = "getGraph";
            repaint();
            removeAllPane();
        }
        if (str.equals("isConnected")) {
            menuI = "isConnected";
            repaint();
            removeAllPane();
            String msg;
            boolean isConnected1 = new DirectedWeightedGraphAlgorithmsC(this.directedWeightedGraph).isConnected();
            if (isConnected1){
                msg = "The graph is Connected";
            }else {
                msg = "The graph is not Connected";
            }
            JOptionPane.showMessageDialog(this,msg);
        }
        if (str.equals("center")) {
            menuI = "center";
            nodeData = new DirectedWeightedGraphAlgorithmsC(this.directedWeightedGraph).center();
            if (nodeData == null) {
                String msg;
                msg = "The graph is not connected, therefore it has no center point";
                JOptionPane.showMessageDialog(this,msg);
            } else{
                repaint();
                removeAllPane();
            }
        }
        if (str.equals("shortestPathDist+shortestPath")) {
            menuI = "shortestPathDist+shortestPath";
            repaint();
            shortestPathDistS();
            panelShortest.setVisible(true);
        }
        if (menuI.equals("shortestPathDist+shortestPath")) {
            if (str.equals("sel")) {
                StringBuilder msg = new StringBuilder();
                nodeDataList = new DirectedWeightedGraphAlgorithmsC(this.directedWeightedGraph).shortestPath(Integer.parseInt(String.valueOf(cBoxShortest1.getSelectedItem())), Integer.parseInt(String.valueOf(cBoxShortest2.getSelectedItem())));
                double sum = new DirectedWeightedGraphAlgorithmsC(this.directedWeightedGraph).shortestPathDist(Integer.parseInt(String.valueOf(cBoxShortest1.getSelectedItem())),Integer.parseInt(String.valueOf(cBoxShortest2.getSelectedItem())));
                if (nodeDataList == null || nodeDataList.size() == 0){
                    msg.append(String.format("There is no path between vertex %d and vertex %d \n",Integer.parseInt(String.valueOf(cBoxShortest1.getSelectedItem())),Integer.parseInt(String.valueOf(cBoxShortest2.getSelectedItem()))));
                    JOptionPane.showMessageDialog(this, msg);
                }else {
                    for (int i = 0; i < nodeDataList.size() - 1; i++) {
                        int r1 = nodeDataList.get(i).getKey();
                        int r2 = nodeDataList.get(i + 1).getKey();
                        double w = this.directedWeightedGraph.getEdge(r1, r2).getWeight();
                        msg.append(String.format("%d -> %d : weight = %f \n", r1, r2, w));
                    }
                    msg.append("-----------------\n");
                    JOptionPane.showMessageDialog(this, msg + "Total Weight: " + sum);
                }
                panelShortest.setVisible(true);
                repaint();
            }
        }
        if (str.equals("removeNode")){
            menuI = "removeNode";
            repaint();
            removeNode();
            frameRemoveNode.setVisible(true);
            panelRemoveNode.setVisible(true);
        }

        if (menuI.equals("removeNode")){
            if (str.equals("select")) {
                int x = 0;
                for (JRadioButton jRadioButton : radioButtonNode) {
                    if (jRadioButton.isSelected()) {
                        x = Integer.parseInt(jRadioButton.getText());
                    }
                }
                frameRemoveNode.dispose();
                frameRemoveNode.setVisible(false);
                panelRemoveNode.setVisible(false);
                panelRemoveNode.setVisible(false);
                removeNode(x);
                repaint();
                removeNode();
            }
            if (str.equals("new choice")) {
                panelRemoveNode.setVisible(true);
                frameRemoveNode.setVisible(true);
            }
        }
        if (str.equals("addNode")){
            menuI = "addNode";
            repaint();
            addNode();
            panelAddNode.setVisible(true);
        }
        if (menuI.equals("addNode")){
            if (str.equals("Send")){
                if (isNumeric(tf1.getText()) && isNumericDo(tf2.getText()) && isNumericDo(tf3.getText())){
                    String pos = ""+tf2.getText()+","+tf3.getText()+",0.0";
                    this.directedWeightedGraph.addNode(new NodeDataC(Integer.parseInt(tf1.getText()),pos));
                    repaint();
                    addNode();
                    panelAddNode.setVisible(true);
                }else {
                    StringBuilder msg = new StringBuilder();
                    msg.append("You did not enter the correct parameters");
                    JOptionPane.showMessageDialog(this, msg);
                }
            }
        }
        if (str.equals("connect")){
            menuI = "connect";
            repaint();
            connect();
            panelConnect.setVisible(true);
        }
        if (menuI.equals("connect")){
            if (str.equals("sel")) {
                if (isNumericDo(tfConnect.getText())){
//                    src = Integer.parseInt(String.valueOf(cBoxShortest1.getSelectedItem())) ;
//                    dest1 = Integer.parseInt(String.valueOf(cBoxShortest2.getSelectedItem()));
                    this.directedWeightedGraph.connect(Integer.parseInt(String.valueOf(cBoxShortest1.getSelectedItem())),Integer.parseInt(String.valueOf(cBoxShortest2.getSelectedItem())),Double.parseDouble(tfConnect.getText()));
//                    src = 0; dest1 = 0;
                    panelConnect.setVisible(true);
                    repaint();
                }else {
                    StringBuilder msg = new StringBuilder();
                    msg.append("You did not enter the correct parameters");
                    JOptionPane.showMessageDialog(this, msg);
                }
            }
        }
        if (str.equals("getEdge")){
            menuI = "getEdge";
            repaint();
            shortestPathDistS();
            panelShortest.setVisible(true);
        }
        if (menuI.equals("getEdge")) {
            if (str.equals("sel")) {
                StringBuilder msg = new StringBuilder();
                src = Integer.parseInt(String.valueOf(cBoxShortest1.getSelectedItem())) ;
                dest1 = Integer.parseInt(String.valueOf(cBoxShortest2.getSelectedItem()));
                if (this.directedWeightedGraph.getEdge(src,dest1) == null){
                    msg.append(String.format("There is no path between vertex %d and vertex %d \n",src,dest1));
                    JOptionPane.showMessageDialog(this, msg);
                    src = 0; dest1 = 0;
                }
                panelShortest.setVisible(true);
                repaint();
            }
        }
        if (str.equals("removeEdge")){
            menuI = "removeEdge";
            repaint();
            shortestPathDistS();
            panelShortest.setVisible(true);
        }
        if (menuI.equals("removeEdge")) {
            if (str.equals("sel")) {
                StringBuilder msg = new StringBuilder();
                src = Integer.parseInt(String.valueOf(cBoxShortest1.getSelectedItem())) ;
                dest1 = Integer.parseInt(String.valueOf(cBoxShortest2.getSelectedItem()));
                if (this.directedWeightedGraph.getEdge(src,dest1) == null){
                    msg.append(String.format("There is no path between vertex %d and vertex %d \n",src,dest1));
                    JOptionPane.showMessageDialog(this, msg);
                }else {
                    removeEdge(src,dest1);
                }
                src = 0; dest1 = 0;
                panelShortest.setVisible(true);
                repaint();
            }
        }
        if (str.equals("getNode")){
            menuI = "getNode";
            repaint();
            removeNode();
            frameRemoveNode.setVisible(true);
            panelRemoveNode.setVisible(true);
        }
        if (menuI.equals("getNode")){
            if (str.equals("select")) {
                int x = Integer.parseInt(radioButtonNode.get(0).getText());
                for (JRadioButton jRadioButton : radioButtonNode) {
                    if (jRadioButton.isSelected()) {
                        x = Integer.parseInt(jRadioButton.getText());
                    }
                }
                nodeData = this.directedWeightedGraph.getNode(x);
                frameRemoveNode.dispose();
                frameRemoveNode.setVisible(false);
                panelRemoveNode.setVisible(false);
                panelRemoveNode.setVisible(false);
                repaint();
                removeNode();
            }
            if (str.equals("new choice")) {
                panelRemoveNode.setVisible(true);
                frameRemoveNode.setVisible(true);
            }
        }
        if (str.equals("nodeSize")){
            StringBuilder msg = new StringBuilder();
            msg.append(String.format("node Size: %d \n",this.directedWeightedGraph.nodeSize()));
            JOptionPane.showMessageDialog(this, msg);
        }
        if (str.equals("edgeSize")){
            StringBuilder msg = new StringBuilder();
            msg.append(String.format("edge Size: %d \n",this.directedWeightedGraph.edgeSize()));
            JOptionPane.showMessageDialog(this, msg);
        }
        if (str.equals("tsp")) {
            menuI = "tsp";
            repaint();
            CheckBoxTsp();
            frameTsp.setVisible(true);
            panelTsp.setVisible(true);
        }
        if (menuI.equals("tsp")) {
            if (str.equals("Choose")) {
                List<NodeData>  nodeDataList1 = new ArrayList<>();
                for (int j = 0; j < checkBoxesTsp.size(); j++) {
                    if (checkBoxesTsp.get(j).isSelected()) {
                        NodeData u = this.directedWeightedGraph.getNode(Integer.parseInt(pointsTsp[j]));
                        nodeDataList1.add(u);
                    }
                }
                nodeDataList = new DirectedWeightedGraphAlgorithmsC(this.directedWeightedGraph).tsp(nodeDataList1);
                frameTsp.dispose();
                frameTsp.setVisible(false);
                panelTsp.setVisible(false);
                panelShortest.setVisible(false);
                if (nodeDataList == null || nodeDataList.size() == 0) {
                    StringBuilder msg = new StringBuilder();
                    msg.append("There is no path between the vertices you marked \n");
                    JOptionPane.showMessageDialog(this, msg);
                }
                repaint();
                CheckBoxTsp();
            }
            if (str.equals("new choice")) {
                panelTsp.setVisible(true);
                frameTsp.setVisible(true);
                panelShortest.setVisible(false);
            }
        }
    }
    private boolean isNumeric(String str) {
        try {
            Integer.parseInt(str);
            return true;
        } catch(NumberFormatException e){
            return false;
        }
    }
    private boolean isNumericDo(String str) {
        try {
            Double.parseDouble(str);
            return true;
        } catch(NumberFormatException e){
            return false;
        }
    }
}
