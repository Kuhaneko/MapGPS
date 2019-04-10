package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.ChoiceBox;
import javafx.scene.image.Image;
import javafx.stage.FileChooser;

import java.io.File;
import java.util.LinkedList;


public class Controller {

    @FXML
    private ChoiceBox<Node> fromChoice, toChoice;
    @FXML
    private Canvas canvas;

    private Graphics gfx;

    private File imageFile;
    private Graph graph;
    private Image image;




    @FXML
    private void loadImage() {
        graph = new Graph();

        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Choose an image...");
        imageFile = fileChooser.showOpenDialog(canvas.getScene().getWindow());

        if (imageFile != null) {
            image = new Image(imageFile.toURI().toString());

            //hardcode nodes for now i guess


            Node townA = new Node("Town A", 94, 55);
            Node townB = new Node("Town B", 356, 47);
            Node townC = new Node("Town C", 237, 118);
            Node townD = new Node("Town D", 70, 183);
            Node townE = new Node("Town E", 404, 228);
            Node townF = new Node("Town F", 199, 286);
            Node townG = new Node("Town G", 396, 367);
            Node townH = new Node("Town H", 127, 417);

            graph.addNode(townA, townB, townC, townD, townE, townF, townG);
            graph.addNode(townH);

            graph.addEdge(new Edge(townA, townB, 5));
            graph.addEdge(new Edge(townB, townC, 3));
            graph.addEdge(new Edge(townA, townC, 7));
            graph.addEdge(new Edge(townD, townC, 4));
            graph.addEdge(new Edge(townC, townE, 2));
            graph.addEdge(new Edge(townC, townF, 5));
            graph.addEdge(new Edge(townD, townF, 4));
            graph.addEdge(new Edge(townE, townF, 9));
            graph.addEdge(new Edge(townF, townH, 6));
            graph.addEdge(new Edge(townF, townG, 7));
            graph.addEdge(new Edge(townG, townH, 14));


            for (Node n : graph.getNodes()) {
                fromChoice.getItems().add(n);
                toChoice.getItems().add(n);
            }

            //////////////////////////////////////// GFX
            gfx = new Graphics(canvas, image, graph);

//            gfx.drawMap(graph.getNodes(), graph.getEdges());

//
//            SnapshotParameters params = new SnapshotParameters();
//            params.setFill(Color.TRANSPARENT);
//            routedImage = canvas.snapshot(params, null);
        }
    }

    @FXML
    private void calculatePath() {
        if(fromChoice.getValue() != null && toChoice.getValue() != null) {
//            gfx.restoreImage();
            LinkedList<Edge> path = graph.dijkstra(fromChoice.getValue(), toChoice.getValue());

            gfx.drawPath(path);
        }
    }

    @FXML
    private void test(ActionEvent actionEvent) {
        gfx.restoreImage();
    }
}
