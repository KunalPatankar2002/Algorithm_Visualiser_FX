package com.example;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class App extends Application {

    private static final int WIDTH = 1000;
    private static final int HEIGHT = WIDTH * 9 / 16 + 46;

    private Button sortButton, mergeButton, bubbleButton, heapButton;
    private BorderPane root;
    private Pane sortingPane;

    @Override
    public void start(@SuppressWarnings("exports") Stage primaryStage) {
        root = new BorderPane();
        root.setPadding(new Insets(10));
        root.setStyle("-fx-background-color: black;");

        sortingPane = new Pane();
        sortingPane.setPrefSize(WIDTH, HEIGHT);

        sortButton = new Button("Quick Sort");
        sortButton.setOnAction(e -> {
            sortingPane.getChildren().clear();
            try {
                new QuickSort(sortingPane);
            } catch (InterruptedException ignored) {
            }
        });

        mergeButton = new Button("Merge Sort");
        mergeButton.setOnAction(e -> {
            sortingPane.getChildren().clear();
            try {
                new QuickSort(sortingPane);
            } catch (InterruptedException ignored) {
            }
        });

        bubbleButton = new Button("Bubble Sort");
        bubbleButton.setOnAction(e -> {
            sortingPane.getChildren().clear();
            try {
                new QuickSort(sortingPane);
            } catch (InterruptedException ignored) {
            }
        });

        heapButton = new Button("Heap Sort");
        heapButton.setOnAction(e -> {
            sortingPane.getChildren().clear();
            try {
                new QuickSort(sortingPane);
            } catch (InterruptedException ignored) {
            }
        });

        HBox buttonBox = new HBox(10);
        buttonBox.setPadding(new Insets(10));
        buttonBox.getChildren().addAll(sortButton, bubbleButton, mergeButton, heapButton);
        root.setTop(buttonBox);

        root.setCenter(sortingPane);

        Scene scene = new Scene(root, WIDTH, HEIGHT, Color.BLACK);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Sorting Visualiser");
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
