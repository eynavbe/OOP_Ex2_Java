# OOP_Ex2_Java
The algorithm gets vertices and edges and matches them to each other, checking paths, binding components.
The algorithm, in other words, produces a graph and executes all sorts of operations on it.
##Main departments and interfaces:
- **DirectedWeightedGraph**: This interface represents a Directional Weighted Graph [Helped algorithms](http://math.oxford.emory.edu/site/cs171/directedAndEdgeWeightedGraphs/) , The interface has a road-system or communication network in mind – and support a large number of nodes (over 100,000). The graph representation is based on:
HashMap<Integer,HashMap<Integer,EdgeData>> : When the Integer of the external HashMap represents the src of the Edge and the Integer of the internal HashMap represents the dest of the EdgeData specified in the internal HashMap
 HashMap<Integer,NodeData>: All vertices in the graph where the Integer represents the key and the NodeData represents the vertex itself.
ArrayList<EdgeData> : List of all EdgeData in the graph.
- **DirectedWeightedGraphC**:  Implementing an Interface DirectedWeightedGraph
- **DirectedWeightedGraphAlgorithms**: This interface represents a Directed (positive) Weighted Graph Theory Algorithms including:
   - clone() - copy
   - init(graph) - Initializing graph
   - isConnected() - strongly (all ordered pais connected)
   - double shortestPathDist(int src, int dest)  - The shortest path from the src vertex to the dst vertex
   - List<NodeData> shortestPath(int src, int dest) - Returns a list of the shortest path between 2 vertices
   - NodeData center() - finds the NodeData which minimizes the max distance to all the other nodes. [Helped algorithms](https://en.wikipedia.org/wiki/Graph_center)  
   - List<NodeData> tsp(List<NodeData> cities) - computes a list of consecutive nodes which go over all the nodes in cities. [Helped algorithms](https://en.wikipedia.org/wiki/Travelling_salesman_problem)
   - save(file) - save JSON file
   - load(file) - load JSON file
- **DirectedWeightedGraphAlgorithmsC**: Implementing an Interface DirectedWeightedGraphAlgorithms
- **EdgeData**: This interface represents the set of operations applicable on a directional edge(src,dest) in a (directional) weighted graph .
- **EdgeDataC**:  Implementing an Interface EdgeData
- **GeoLocation**: This interface represents a geo location <x,y,z>, (aka Point3D data).
- **GeoLocationC**:  Implementing an Interface GeoLocation
- **NodeData**: This interface represents the set of operations applicable on a node (vertex) in a (directional) weighted graph.
- **NodeDataC**:  Implementing an Interface NodeData
- **GraphView**: Creates a drawing of a graph (with directional edges, vertices - with their id, weight of each edge, and a menu to the function we performed in the graph).
- **forSortestPath**: Assistance class for ShortestPath function.
- **blockToV**: produces graphs and loads them into a json file.
 - **DirectedWeightedGraphAlgorithmsCTest**: test class DirectedWeightedGraphAlgorithmsC
 - **DirectedWeightedGraphCTest**: test class DirectedWeightedGraphC
  
 ## How the main functions in the algorithm works:
- **isConected**: Checks if the graph is linked. We used the ["BFS" algorithm](https://en.wikipedia.org/wiki/Breadth-first_search). 
The function activates a "BFS" for each vertex in the graph. The function " isConected " returns a “false” as soon as the “BFS” return “false” to one of the vertices (i.e if there is a white vertex after its activation), if the BPS returned only true - it returns true.
- **shortestPath**: Goes through all the pathes that are from the source to the destination. And returns the path with the lowest weight.
- **tsp**: The function checks which route is the shortest (with the least weight) according to the given list.
The function works by checking the shortest route in each of the order options of the list and updating the variable of the minimum route.
- **Center**: Each of the vertices is sent to shortestPath.
There is a variable that maintains the maximum distance from one vertex to another. Whenever a smaller maximum distance is found with a different vertex we will replace, and set it in the variable.
The vertex that is in the variable after we have checked the maximum distance from all the vertices is the center point and this is the vertex that we return.

## How to download, run and use the graphical interface:
Activating the init function in the DirectedWeightedGraphAlgorithms interface that receives the DirectedWeightedGraph parameter activates the graphical interface.
The graphical GUI interface is used in a way that obtains a DirectedWeightedGraph parameter that contains the information needed to create the graph.
The screen initializes by displaying the Directed Weighted Graph parameter in the graph by vertices and edge.
There are 2 types of menus on the screen that contain the options to display the results of the algorithms that appear in the DirectedWeightedGraphAlgorithms interface and the DirectedWeightedGraph interface.
Each click on one of the options in the menu if the function should have parameters will open to the user an option of selecting the required parameters and then displaying the results obtained from the functions on the graph in bold or by way of message.
### menu DirectedWeightedGraphAlgorithms:
- **getGraph**: displays the graph
- **isConnected**: displays whether the graph is connected or not.
- **shortestPathDist+shortestPath**: At the bottom of the screen there is a selection of "src" and "dest" from the vertices of the graph clicking on the "sel" button will display a message representing the distance between the 2 selected points clicking on "ok" will display the track on the graph by marking the track in green (including arrows Relevant) and mark in red the enlarged points where the route passes if there is no route between the points a message will appear.
- **center**: Mark the enlarged point in red, mark the midpoint in the graph. If there is no such point (the graph is not linked), a message will be displayed. 
- **tsp**: The user has to select vertices from the list of existing vertices for the graph and clicking on "Choose" will mark in the graph the shortest route that passes through all the points selected by marking the track in green (including Relevant arrows) and mark in red the enlarged points where the route passe. If no such route is found, a message will appear. Clicking on "new choice" that appears at the bottom of the page will allow you to re-select points.
  
### menu DirectedWeightedGraph:
- **removeNode**: The user has to select one vertice from the list of existing vertices for the graph and clicking on "select" will delete the point and edge attached to it from the graph.
- **removeEdge**: At the bottom of the screen there is a selection of "src" and "dest" from the vertices of the graph clicking on the "sel" button will delete the page if such a page exists if no message is displayed.
- **getNode**: The user has to select one vertice from the list of existing vertices for the graph and clicking on "select" will Mark the enlarged point in red, mark the midpoint in the graph. Clicking on "new choice" that appears at the bottom of the page will allow you to re-select point.
- **getEdge**: At the bottom of the screen there is a selection of "src" and "dest" from the vertices of the graph clicking on the "sel" button will marking the edge in green (including Relevant arrows) and mark in red the enlarged points that connect the edge. If no such edge exists, a message will appear.
- **addNode**: at the bottom of the screen, a text box will appear for the user to type id "node" and "latitude" and "longitude". Clicking "Send" will add this point to the screen if the text entered by the user is correct in case no message appears.
- **connect**: at the bottom of the screen there is a selection of "src" and "dest" from the vertices of the graph and a text box for the user to type " weight" of the edge. clicking on the "sel" button will connect these points (with an arrow of the side direction) with the weight typed. If the weight is incorrect, a message will appear.   
- **nodeSize**: a message of node size will appear.
- **edgeSize**: a message of edge size will appear.  
  
### How to run the jar:
1. go to [link](https://github.com/eynavbe/OOP_Ex2_Java/tree/abd973aa25159a68891191177e8d63d7ccfc5080/%E2%80%8F%E2%80%8FEx2/out/artifacts/Ex2_jar)
2. In the folder where the jar is, open a new folder called "data" and put the json files you want to run inside.
3. Open cmd to the path of the folder where the jar is located.
3. Register "java -jar Ex2.jar NAME_JSON"
Example NAME_JSON: G1.json
  
## Import libraries:
Json-simple.1.1.1.jar
  
## Tests:
1. Finding the shortest trajectory on an unlinked graph - check an existing trajectory and a non-existent trajectory.
2. The shortest trajectory between 2 vertices.
3. Check "isConnect" on a linked and unlinked graph.
4. Finding a center in a linked and unlinked graph on graphs of different sizes.
5. Checking a trajectory between a vertex and itself.
6. load on a non-existent file.
7. A test in which the "shortestPath" will return the shortest weight and not the smallest edges (for example in case the trajectory between vertices is longer in terms of edges but shorter in terms of weight).
8. tsp with a list of 2 vertices and with a list of more than 2, and check that the shortest route is obtained.

### Analysis of the performance of the algorithms on graphs of 1000, 10000, 100000, 1000000 (With an average of 20 ribs - inbound + outbound - total 20):
#### G1 graph:
- **Load:** load successfully (245 ms)
- **isConnect:** Returns “true” as required (267 ms)
- **shortestPathDist:** Returns the shortest.
- **shortestPath:** Returns the shortest path.
- **Center:** return 8 (253 ms).
- **Tsp:** find the minimum path if exist (226 ms).

#### G2 graph:
- **Load:**  load successfully (236 ms)
- **isConnect:** Returns “true” as required (253 ms)
- **shortestPathDist:** Returns the shortest.
- **shortestPath:** Returns the shortest path.
- **Center:** return 0 (314 ms).
- **Tsp:** find the minimum path if exist.(245 ms).
  
#### G3 graph:
- **Load:** load successfully (250 ms).
- **isConnect:** Returns “true” as required (256 ms)
- **shortestPathDist:** Returns the shortest.
- **shortestPath:** Returns the shortest path.
- **Center:** return 40 (4 sec 10 ms).
- **Tsp:** find the minimum path if exist (269 ms).
  
#### 10 nodes graph – not connected graph:
- **Load:** load successfully (238 ms)
- **isConnect:** Returns “false” as required (264 ms)
- **shortestPathDist:** Returns the shortest path if there is, if there is no path, returns -1 as required.
- **shortestPath:** Returns the shortest path if there is, if there is no path, returns null as required.
- **Center:** return null because the graph is no connected (238 ms).
- **Tsp:** find the minimum path if exist (250 ms).
  
#### 1,000 nodes graph:
- **Load:** load successfully (324 ms).
- **isConnect:** Returns “true” as required (1 sec 49 ms).
- **shortestPathDist**: Returns the shortest path successfully (500 ms).
- **shortestPath:** Returns the shortest path if there is. (500 ms)
- **Tsp:** find the minimum path if exist (700 ms).

#### 10,000 nodes graph:
- **Load:** load successfully (655 ms).
- **isConnect:** Returns “true” as required (54 sec)
- **shortestPathDist:** Returns the shortest path if there is. (657 ms)
- **shortestPath:** Returns the shortest path if there is (657 ms).
- **Tsp:** find the minimum path if exist.

#### 100,000 nodes graph:
- **Load:** load successfully (7 sec 272 ms).
- **shortestPathDist:** Returns the shortest path if there is (27 sec).
- **shortestPath:** Returns the shortest path if there is (27 sec).
- **Tsp:** find the minimum path if exist.

#### 1,000,000 nodes graph:
- **Load:** can’t load the file because it is to big.
 
 
### UML:
![src](https://user-images.githubusercontent.com/93534494/145817214-bd64fc1c-6c9b-4bc5-ae66-03c883215690.png) 
