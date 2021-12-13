package api.test;

import api.*;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class DirectedWeightedGraphCTest {

    @Test
    void getNode() {
        DirectedWeightedGraphAlgorithmsC n = new DirectedWeightedGraphAlgorithmsC();
        n.load("1000Nodes.json");
        NodeData node = new NodeDataC(0, "118.0,426.0,588.0");
        assertEquals(node.getLocation().y(), n.getGraph().getNode(0).getLocation().y());
        assertEquals(node.getLocation().x(), n.getGraph().getNode(0).getLocation().x());
        assertEquals(node.getLocation().z(), n.getGraph().getNode(0).getLocation().z());
        assertNotEquals(5.8, n.getGraph().getNode(375).getLocation().y());
    }

    @Test
    void getEdge() {
        DirectedWeightedGraphAlgorithmsC n = new DirectedWeightedGraphAlgorithmsC();
        n.load("10000Nodes.json");
        assertEquals(68.82141577499756, n.getGraph().getEdge(0, 200).getWeight());
        assertNotEquals(60.0, n.getGraph().getEdge(0, 200).getWeight());
    }

    @Test
    void addNode() {
        DirectedWeightedGraphAlgorithmsC n = new DirectedWeightedGraphAlgorithmsC();
        n.load("100000Nodes.json");
        n.getGraph().addNode(new NodeDataC(100000, "98.0,678.5,456.0"));
        n.save("100000Nodes.json");
        n.load("100000Nodes.json");
        assertEquals(100000, n.getGraph().getNode(n.getGraph().nodeSize()-1).getKey());
    }

    @Test
    void connect() {
        DirectedWeightedGraphAlgorithmsC n = new DirectedWeightedGraphAlgorithmsC();
        n.load("1000Nodes.json");
        n.getGraph().connect(56, 78, 13);
        n.save("1000Nodes.json");
        n.load("1000Nodes.json");
        assertEquals(13.0, n.getGraph().getEdge(56, 78).getWeight());
        n.getGraph().connect(56, 78, 7);
        n.save("1000Nodes.json");
        n.load("1000Nodes.json");
        assertEquals(7.0, n.getGraph().getEdge(56, 78).getWeight());
        n.getGraph().connect(56, 56, 76);
        n.save("1000Nodes.json");
        n.load("1000Nodes.json");
        assertNull(n.getGraph().getEdge(56, 56));
        n = new DirectedWeightedGraphAlgorithmsC();
        DirectedWeightedGraph f = new DirectedWeightedGraphC();
        f.addNode(new NodeDataC(3,"35.21310882485876,32.104636394957986,0.0"));
        f.addNode(new NodeDataC(4,"35.212111165456015,32.106235628571426,0.0"));
        f.connect(3,4,10);
        n.init(f);
    }

    @Test
    void removeNode() {
        DirectedWeightedGraphAlgorithmsC n = new DirectedWeightedGraphAlgorithmsC();
        n.load("100000Nodes.json");
        n.getGraph().removeNode(99966);
        n.save("100000Nodes.json");
        n.load("100000Nodes.json");
        assertNull(n.getGraph().getNode(99966));
    }

    @Test
    void removeEdge() {
        DirectedWeightedGraphAlgorithmsC n = new DirectedWeightedGraphAlgorithmsC();
        n.load("1000Nodes.json");
        n.getGraph().removeEdge(487,327);
        n.save("1000Nodes.json");
        n.load("1000Nodes.json");
        assertNull(n.getGraph().getEdge(487, 327));
    }

    @Test
    void nodeSize() {
        DirectedWeightedGraphAlgorithmsC n = new DirectedWeightedGraphAlgorithmsC();
        n.load("1000Nodes.json");
        assertEquals(1000, n.getGraph().nodeSize());
    }

    @Test
    void edgeSize() {
        DirectedWeightedGraphAlgorithmsC n = new DirectedWeightedGraphAlgorithmsC();
        n.load("10000Nodes.json");
        assertEquals(90000, n.getGraph().edgeSize());
    }
}