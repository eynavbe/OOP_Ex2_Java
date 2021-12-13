package api.test;

import api.DirectedWeightedGraph;
import api.DirectedWeightedGraphAlgorithmsC;
import api.DirectedWeightedGraphC;
import api.NodeDataC;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

public class blockToV {

    public static void main(String[] args){
        DirectedWeightedGraphAlgorithmsC graph = new DirectedWeightedGraphAlgorithmsC();
        graph.load("G1.json");
        creatGrephInit();
        creatGreph1000000();
        creatGreph100000();
        creatGreph10000();
        creatGreph1000();
        creatGreph10();
    }

    public static void creatGreph10() {
        DirectedWeightedGraphAlgorithmsC n = new DirectedWeightedGraphAlgorithmsC();
        //Creates a json file with 100000 vertices and 20 edges on average per vertex
        Random rand = new Random();

        int id = 0;
        double random_VPosx;
        double random_VPosy;
        double random_VPosz = 0.0;
        String pos = "";
        JSONObject vertexD = new JSONObject();
        JSONArray VList = new JSONArray();
        //creats vertex - nodes in json
        for (int i = 0; i < 10; i++) {
            random_VPosx = rand.nextDouble()*10 + 11;
            random_VPosy = rand.nextDouble()*4 + 7;
            pos += random_VPosx + "," + random_VPosy + "," + random_VPosz;
            vertexD.put("pos", pos);
            vertexD.put("id", id);
            VList.add(vertexD);
            pos = "";
            id++;
            vertexD = new JSONObject( );
        }

        int random_v1;
        int random_v2;
        double random_weight;
        JSONObject EdgesD = new JSONObject( );
        JSONArray EList = new JSONArray( );
        //creats edges in json
        for (int j = 0; j < (10*2); j++) {
            random_v1 = rand.nextInt(10);
            random_v2 = rand.nextInt(10);
            random_weight = rand.nextDouble()*5 + 0.000001;
            EdgesD.put("src", random_v1);
            EdgesD.put("w", random_weight);
            EdgesD.put("dest", random_v2);
            EList.add(EdgesD);
            EdgesD = new JSONObject();
        }

        JSONObject obj = new JSONObject( );
        obj.put("Edges", EList);
        obj.put("Nodes", VList);
        //Write JSON file
        try (FileWriter file1 = new FileWriter("data/10Nodes.json")) {
            //We can write any JSONArray or JSONObject instance to the file
            file1.write(obj.toJSONString());
            //file1.flush();
            file1.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    public static void creatGreph1000() {
        DirectedWeightedGraphAlgorithmsC n = new DirectedWeightedGraphAlgorithmsC();
        //Creates a json file with 100000 vertices and 20 edges on average per vertex
        Random rand = new Random();

        int id = 0;
        double random_VPosx;
        double random_VPosy;
        double random_VPosz = 0.0;
        String pos = "";
        JSONObject vertexD = new JSONObject();
        JSONArray VList = new JSONArray();
        //creats vertex - nodes in json
    }
    public static void creatGreph10000() {
        DirectedWeightedGraphAlgorithmsC n = new DirectedWeightedGraphAlgorithmsC();
        //Creates a json file with 100000 vertices and 20 edges on average per vertex
        Random rand = new Random();
        int id = 0;
        double random_VPosx;
        double random_VPosy;
        double random_VPosz = 0.0;
        String pos = "";
        JSONObject vertexD = new JSONObject();
        JSONArray VList = new JSONArray();
        //creats vertex - nodes in json
        //Creates a json file with 1000000 vertices and 20 edges on average per vertex
        for (int i = 0; i < 10000; i++) {
            random_VPosx = rand.nextDouble()*20 + 23;
            random_VPosy = rand.nextDouble()*74 + 56;
            random_VPosz = rand.nextDouble();
            pos += random_VPosx + "," + random_VPosy + "," + random_VPosz;
            vertexD.put("pos", pos);
            vertexD.put("id", id);
            VList.add(vertexD);
            pos = "";
            id++;
            vertexD = new JSONObject();
        }

        int random_v1;
        int random_v2;
        double random_weight;
        JSONObject EdgesD = new JSONObject( );
        JSONArray EList = new JSONArray( );
        //creats edges in json
        for (int j = 0; j < (10000*20); j++) {
            random_v1 = rand.nextInt(10000);
            random_v2 = rand.nextInt(10000);
            random_weight = rand.nextDouble()*20 + 0.000001;
            EdgesD.put("src", random_v1);
            EdgesD.put("w", random_weight);
            EdgesD.put("dest", random_v2);
            EList.add(EdgesD);
            EdgesD = new JSONObject();
        }

        JSONObject obj = new JSONObject( );
        obj.put("Edges", EList);
        obj.put("Nodes", VList);
        //Write JSON file
        try (FileWriter file = new FileWriter("data/10000Nodes.json")) {
            //We can write any JSONArray or JSONObject instance to the file
            file.write(obj.toJSONString());
            file.flush();
            file.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void creatGreph100000() {
        DirectedWeightedGraphAlgorithmsC n = new DirectedWeightedGraphAlgorithmsC();
        //Creates a json file with 100000 vertices and 20 edges on average per vertex
        Random rand = new Random();

        int id = 0;
        double random_VPosx;
        double random_VPosy;
        double random_VPosz = 0.0;
        String pos = "";
        JSONObject vertexD = new JSONObject();
        JSONArray VList = new JSONArray();
        //creats vertex - nodes in json
        for (int i = 0; i < 100000; i++) {
            random_VPosx = rand.nextDouble()*10 + 25;
            random_VPosy = rand.nextDouble()*10 + 25;
            pos += random_VPosx + "," + random_VPosy + "," + random_VPosz;
            vertexD.put("pos", pos);
            vertexD.put("id", id);
            VList.add(vertexD);
            pos = "";
            id++;
            vertexD = new JSONObject();
        }
        int random_v1;
        int random_v2;
        double random_weight;
        JSONObject EdgesD = new JSONObject( );
        JSONArray EList = new JSONArray( );
        //creats edges in json
        for (int j = 0; j < (100000*20); j++) {
            random_v1 = rand.nextInt(100000);
            random_v2 = rand.nextInt(100000);
            random_weight = rand.nextDouble()*20 + 0.000001;
            EdgesD.put("src", random_v1);
            EdgesD.put("w", random_weight);
            EdgesD.put("dest", random_v2);
            EList.add(EdgesD);
            EdgesD = new JSONObject();
        }

        JSONObject obj = new JSONObject( );
        obj.put("Edges", EList);
        obj.put("Nodes", VList);
        //Write JSON file
        try (
                FileWriter file = new FileWriter("data/100000Nodes.json")) {
            //We can write any JSONArray or JSONObject instance to the file
            file.write(obj.toJSONString());
            //file.flush();
            file.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    public static void creatGreph1000000() {
        DirectedWeightedGraphAlgorithmsC n = new DirectedWeightedGraphAlgorithmsC();
        //Creates a json file with 100000 vertices and 20 edges on average per vertex
        Random rand = new Random();

        int id = 0;
        double random_VPosx;
        double random_VPosy;
        double random_VPosz = 0.0;
        String pos = "";
        JSONObject vertexD = new JSONObject();
        JSONArray VList = new JSONArray();
        //creats vertex - nodes in json
        //Creates a json file with 1000000 vertices and 20 edges on average per vertex
        for (int i = 0; i < 1000000; i++) {
            random_VPosx = rand.nextDouble()*5 + 23;
            random_VPosy = rand.nextDouble()*3 + 3;
            random_VPosz = rand.nextDouble();
            pos += random_VPosx + "," + random_VPosy + "," + random_VPosz;
            vertexD.put("pos", pos);
            vertexD.put("id", id);
            VList.add(vertexD);
            pos = "";
            id++;
            vertexD = new JSONObject();
        }

        int random_v1;
        int random_v2;
        double random_weight;
        JSONObject EdgesD = new JSONObject( );
        JSONArray EList = new JSONArray( );
        //creats edges in json
        for (int j = 0; j < (1000000*2); j++) {
            random_v1 = rand.nextInt(1000000);
            random_v2 = rand.nextInt(1000000);
            random_weight = rand.nextDouble()*50 + 0.000001;
            EdgesD.put("src", random_v1);
            EdgesD.put("w", random_weight);
            EdgesD.put("dest", random_v2);
            EList.add(EdgesD);
            EdgesD = new JSONObject();
        }

        JSONObject obj = new JSONObject( );
        obj = new JSONObject();
        obj.put("Edges", EList);
        obj.put("Nodes", VList);
        //Write JSON file
        try (FileWriter file = new FileWriter("data/1000000Nodes.json")) {
            //We can write any JSONArray or JSONObject instance to the file
            file.write(obj.toJSONString());
            file.flush();
            file.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void creatGrephInit() {
        DirectedWeightedGraph n1 = new DirectedWeightedGraphC();
        n1.addNode(new NodeDataC(0, "2931.0,616.0,3406.0"));
        n1.addNode(new NodeDataC(1, "9487.0,3674.0,8887.0"));
        n1.addNode(new NodeDataC(2, "2398.0,7062.0,227.0"));
        n1.addNode(new NodeDataC(3, "2196.0,5812.0,9211.0"));
        n1.connect(0, 1, 68.82141577499756);
        n1.connect(1, 2, 845.0989408234293);
        n1.connect(2, 3, 991.7446030685612);
        n1.connect(2, 1, 3.5);
        n1.connect(3, 0, 4);
        DirectedWeightedGraphAlgorithmsC graph = new DirectedWeightedGraphAlgorithmsC();
        graph.init(n1);

        graph.load("G1.json");
    }
}

