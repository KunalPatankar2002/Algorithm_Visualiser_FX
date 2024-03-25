package com.example;

import javafx.application.Platform;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class HeapSort {

    private final int width = 1000, height = 600;
    private final int size = 200;
    private final float barWidth = (float) width / size;
    private final List<Rectangle> bars = new ArrayList<>();
    private float[] barHeight = new float[size];
    private int current_index, swap1, swap2;
    private Color barColour = Color.ORANGE;
    @SuppressWarnings("exports")
    public HeapSort(Pane sortingPane) throws InterruptedException {

        initBars(sortingPane);
        // shuffleBars();
        // I call shuffleBars as the first line of heapSort() Ideally it would be called here tho

        Thread sorterThread = new Thread(this::heapSort);
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
            bar.setFill(barColour);
            bars.add(bar);
            sortingPane.getChildren().add(bar);
        }
        
        Platform.runLater(this::repaintBars);
        Thread.sleep(100);
    }


    private void max_heapify(float[] barHeight, int size, int i) throws InterruptedException {

        int l = 2 * i + 1;
        int r = 2 * i + 2;
        
        int largest = i;
    
        if (l < size && barHeight[l] > barHeight[i]) {
            largest = l;
        }
    
        if (r < size && barHeight[r] > barHeight[largest]) {
            largest = r;
        }
    
        if (largest != i) {
            // swap elements
            float temp = barHeight[i];
            barHeight[i] = barHeight[largest];
            barHeight[largest] = temp;
            swap1 = i;
            swap2 = largest;
            Thread.sleep(10);
            Platform.runLater(this::repaintBars);
            max_heapify(barHeight, size, largest);
        }
    }
    
    private void build_max_heap(float[] barHeight) throws InterruptedException {
        int size = barHeight.length;
    
        for (int i = size / 2 - 1; i >= 0; i--) {
            max_heapify(barHeight, size, i);
        }
    }
    
    private void heapSort(){
        try {
            shuffleBars();
            heapSort(barHeight);
        } catch (InterruptedException e) {}
    }
    private void heapSort(float[] barHeight) throws InterruptedException {
        build_max_heap(barHeight);
        for (int i = barHeight.length - 1; i >= 0; i--) {
            // swap elements
            float temp = barHeight[i];
            barHeight[i] = barHeight[0];
            barHeight[0] = temp;
            swap1 = i;
            swap2 = 0;
            Thread.sleep(10);
            Platform.runLater(this::repaintBars);
    
            max_heapify(barHeight, i, 0);
        }
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
            Color color = barColour;
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
