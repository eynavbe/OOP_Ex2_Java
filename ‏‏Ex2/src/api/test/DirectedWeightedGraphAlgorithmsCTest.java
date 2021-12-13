package api.test;

import api.*;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

class DirectedWeightedGraphAlgorithmsCTest {

    @Test
    void init() {
        DirectedWeightedGraphAlgorithmsC n = new DirectedWeightedGraphAlgorithmsC();
        DirectedWeightedGraph f = new DirectedWeightedGraphC();
        f.addNode(new NodeDataC(3,"35.67,32.66,0.0"));
        f.addNode(new NodeDataC(4,"35.87,32.66,0.0"));
        f.connect(3,4,10);
        n.init(f);
    }

    @Test
    void getGraph() {
        DirectedWeightedGraphAlgorithmsC n = new DirectedWeightedGraphAlgorithmsC();
        n.load("10Nodes.json");
        assertEquals(n.getGraph().getClass().getName(), "api.DirectedWeightedGraphC");
    }

    @Test
    void copy() {
    }

    @Test
    void isConnected() {
        DirectedWeightedGraphAlgorithmsC n = new DirectedWeightedGraphAlgorithmsC();
        n.load("G1.json");
        assertTrue(n.isConnected());
        n.load("G2.json");
        assertTrue(n.isConnected());
        n.load("G3.json");
        assertTrue(n.isConnected());
        n.load("10Nodes.json");
        assertFalse(n.isConnected());
        n.load("1000Nodes.json");
        assertTrue(n.isConnected());
        n.load("10000Nodes.json");
        assertTrue(n.isConnected());
    }

    @Test
    void shortestPathDist() {
        DirectedWeightedGraphAlgorithmsC n = new DirectedWeightedGraphAlgorithmsC();
        n.load("G3.json");
        assertEquals((int)n.shortestPathDist(2, 4), 2);
        n.load("10Nodes.json");
        n.getGraph().connect(9, 2, 6.0);
        n.save("10Nodes.json");
        n.load("10Nodes.json");
        assertEquals((int)n.shortestPathDist(9, 2), 4);
        assertEquals((int)n.shortestPathDist(0, 2), 2);
        assertEquals((int)n.shortestPathDist(4, 4), -1);
        assertEquals(n.shortestPathDist(5, 1), -1);
        assertEquals(n.shortestPathDist(5, 5), -1);
    }

    @Test
    void shortestPath() {
        DirectedWeightedGraphAlgorithmsC n = new DirectedWeightedGraphAlgorithmsC();
        n.load("10Nodes.json");
        List<NodeData> list = new ArrayList<>();
        list.add(n.getGraph().getNode(5));
        list.add(n.getGraph().getNode(8));
        list.add(n.getGraph().getNode(3));
        list.add(n.getGraph().getNode(9));
        List<NodeData> list1 = n.shortestPath(5, 9);
        for (int i = 0; i < (list1.size()) && (i< list.size()); i++) {
            assertEquals(list.get(i), list1.get(i));
        }
        System.out.println(n.shortestPath(0, 0));
        assertNull(n.shortestPath(0, 0));
        assertNull(n.shortestPath(9, 1));

        n.load("1000Nodes.json");
        List<NodeData> list1000 = new ArrayList<>();
        list1000.add(n.getGraph().getNode(498));
        list1000.add(n.getGraph().getNode(784));
        List<NodeData> list2 = n.shortestPath(498, 784);
        for (int i = 0; i < (list2.size()) && (i< list1000.size()); i++) {
            assertEquals(list1000.get(i), list2.get(i));
        }
        list1000 = new ArrayList<>();
        list1000.add(n.getGraph().getNode(0));
        list1000.add(n.getGraph().getNode(769));
        list1000.add(n.getGraph().getNode(631));
        list1000.add(n.getGraph().getNode(195));
        list1000.add(n.getGraph().getNode(765));
        list1000.add(n.getGraph().getNode(661));
        list1000.add(n.getGraph().getNode(999));
        list2 = n.shortestPath(0, 999);
        for (int i = 0; i < (list2.size()) && (i< list1000.size()); i++) {
            assertEquals(list1000.get(i), list2.get(i));
        }
        assertNull(n.shortestPath(0, 0));

        n.load("10000Nodes.json");
        list1000 = new ArrayList<>();
        list1000.add(n.getGraph().getNode(9693));
        list1000.add(n.getGraph().getNode(6605));
        list2 = n.shortestPath(9693, 6605);
        for (int i = 0; i < (list2.size()) && (i< list1000.size()); i++) {
            assertEquals(list1000.get(i), list2.get(i));
        }
        n.load("100000Nodes.json");
        list1000 = new ArrayList<>();
        list1000.add(n.getGraph().getNode(68266));
        list1000.add(n.getGraph().getNode(4756));
        list1000.add(n.getGraph().getNode(55716));
        list1000.add(n.getGraph().getNode(99025));
        list1000.add(n.getGraph().getNode(78067));
        list1000.add(n.getGraph().getNode(8100));
        list1000.add(n.getGraph().getNode(1912));
        list2 = n.shortestPath(68266, 1912);
        for (int i = 0; i < (list2.size()) && (i< list1000.size()); i++) {
            assertEquals(list1000.get(i), list2.get(i));
        }
    }

    @Test
    void center() {
        DirectedWeightedGraphAlgorithmsC n = new DirectedWeightedGraphAlgorithmsC();
        n.load("10Nodes.json");
        assertNull(n.center());
        n.load("G1.json");
        assertEquals(n.center().getKey(), 8);
        n.load("G2.json");
        assertEquals(n.center().getKey(), 0);
        n.load("G3.json");
        assertEquals(n.center().getKey(), 40);
    }

    @Test
    void tsp() {
        DirectedWeightedGraphAlgorithmsC n = new DirectedWeightedGraphAlgorithmsC();
        n.load("10Nodes.json");
        List<NodeData> list = new ArrayList<>();
        list.add(n.getGraph().getNode(5));
        list.add(n.getGraph().getNode(9));
        List<NodeData> list1 = n.tsp(list);
        List<NodeData> newListTsp = n.shortestPath(5,9);
        for (int i = 0; i < (list1.size()) && (i< newListTsp.size()); i++) {
            assertEquals(newListTsp.get(i).getKey(), list1.get(i).getKey());
        }
        n.load("G2.json");
        List<NodeData> listTry2 = new ArrayList<>();
        listTry2.add(n.getGraph().getNode(9));
        listTry2.add(n.getGraph().getNode(5));
        listTry2.add(n.getGraph().getNode(2));
        List<NodeData> list2 = n.tsp(listTry2);
        List<NodeData> listTry1 = n.shortestPath(5,2);
        List<NodeData> listTry3 = n.shortestPath(2,9);
        List<NodeData> list3 = new ArrayList<>();
        list3.addAll(listTry1);
        list3.addAll(listTry3);
        for (int i = 0; i < (list3.size()) && (i< list2.size()); i++) {
            assertEquals(list3.get(i).getKey(), list2.get(i).getKey());
        }

        n.load("G3.json");
        listTry2 = new ArrayList<>();
        listTry2.add(n.getGraph().getNode(9));
        listTry2.add(n.getGraph().getNode(5));
        listTry2.add(n.getGraph().getNode(2));
        list2 = n.tsp(listTry2);
        listTry1 = n.shortestPath(9,2);
        listTry3 = n.shortestPath(2,5);
        list3 = new ArrayList<>();
        list3.addAll(listTry1);
        list3.addAll(listTry3);
        for (int i = 0; i < (list3.size()) && (i< list2.size()); i++) {
            assertEquals(list3.get(i).getKey(), list2.get(i).getKey());
        }

        n.load("1000Nodes.json");
        listTry2 = new ArrayList<>();
        listTry2.add(n.getGraph().getNode(906));
        listTry2.add(n.getGraph().getNode(545));
        listTry2.add(n.getGraph().getNode(232));
        list2 = n.tsp(listTry2);
        listTry1 = n.shortestPath(545,232);
        listTry3 = n.shortestPath(232,906);
        list3 = new ArrayList<>();
        list3.addAll(listTry1);
        list3.addAll(listTry3);
        for (int i = 0; i < (list3.size()) && (i< list2.size()); i++) {
            assertEquals(list3.get(i).getKey(), list2.get(i).getKey());
        }
    }

    @Test
    void save() {
//        //use save with existed file in other test
    }

    @Test
    void load() {
        DirectedWeightedGraphAlgorithmsC n = new DirectedWeightedGraphAlgorithmsC();
        assertFalse(n.load("notExistFile.json"));
        //use load with existed file in other test
    }

}