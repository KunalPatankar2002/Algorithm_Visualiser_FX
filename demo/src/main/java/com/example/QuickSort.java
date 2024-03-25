package com.example;

import javafx.application.Platform;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class QuickSort {

    private final int width = 1000, height = 600;
    private final int size = 200;
    private final float barWidth = (float) width / size;
    private final List<Rectangle> bars = new ArrayList<>();
    private float[] barHeight = new float[size];
    private int current_index, swap1, swap2;

    @SuppressWarnings("exports")
    public QuickSort(Pane sortingPane) throws InterruptedException {

        initBars(sortingPane);
        // shuffleBars();
        // I call shuffleBars as the first line of quickSort() Ideally it would be called here tho

        Thread sorterThread = new Thread(this::quickSort);
        sorterThread.setDaemon(true);
        sorterThread.start();
    }

    private void initBars(Pane sortingPane) throws InterruptedException {
        float interval = (float) height / size;
        for (int i = 0; i < size; i++) {
            barHeight[i] = (i+1) * interval;
        }
        for (int i = 0; i < size; i++) {
            Rectangle bar = new Rectangle(i * barWidth, height - barHeight[i], barWidth, barHeight[i]);
            bar.setFill(Color.GREEN);
            bars.add(bar);
            sortingPane.getChildren().add(bar);
        }
        
        Platform.runLater(this::repaintBars);
        Thread.sleep(100);
    }


    private void quickSort() {
        try {
            shuffleBars();
            quickSortHelper(0, size - 1);
        } catch (InterruptedException ignored) {
        }
    }

    private void quickSortHelper(int low, int high) throws InterruptedException {
        if (low < high) {
            int partitionIndex = partition(low, high);
            quickSortHelper(low, partitionIndex - 1);
            quickSortHelper(partitionIndex + 1, high);
        }
    }

    private int partition(int low, int high) throws InterruptedException {
        float pivot = barHeight[high];
        current_index = high;
        int i = (low - 1);

        for (int j = low; j <= high - 1; j++) {
            if (barHeight[j] < pivot) {
                i++;
                float temp = barHeight[i];
                barHeight[i] = barHeight[j];
                barHeight[j] = temp;
            }
            swap1 = i;
            swap2 = j;
            Thread.sleep(1);
            Platform.runLater(this::repaintBars);
        }
        float temp = barHeight[i + 1];
        barHeight[i + 1] = barHeight[high];
        barHeight[high] = temp;
        swap1 = i + 1;
        swap2 = high;
        Thread.sleep(1);
        Platform.runLater(this::repaintBars);
        return (i + 1);
    }

    private void shuffleBars() throws InterruptedException {
        
        for (int i = size - 1; i >= 1; i--) {
            int rand = new Random().nextInt(i + 1);
            float temp = barHeight[i];
            barHeight[i] = barHeight[rand];
            barHeight[rand] = temp;
            Thread.sleep(10);
            Platform.runLater(this::repaintBars);
    
        }
    }

    private void repaintBars() {
        for (int i = 0; i < size; i++) {
            Rectangle bar = bars.get(i);
            Color color = Color.GREEN;
            if (i == current_index)
                color = Color.WHITE;
            else if (i == swap1)
                color = Color.GRAY;
            else if (i == swap2)
                color = Color.LIGHTGRAY;
            bar.setHeight(barHeight[i]);
            bar.setY(height - barHeight[i]);
            bar.setFill(color);
        }
    }
}
