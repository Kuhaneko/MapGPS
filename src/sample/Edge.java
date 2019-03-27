package sample;

public class Edge {
    Node origin; //these names help if a route is unidirectional
    Node destination; //TODO junit test whether edges connect to node
    float weight;

    public Node getOrigin() {
        return origin;
    }

    public Node getDestination() {
        return destination;
    }

    public float weight() {
        return weight;
    }
}
