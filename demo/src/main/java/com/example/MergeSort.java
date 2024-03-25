package com.example;

import javafx.application.Platform;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MergeSort {

    private final int width = 1000, height = 600;
    private final int size = 200;
    private final float barWidth = (float) width / size;
    private final List<Rectangle> bars = new ArrayList<>();
    private float[] barHeight = new float[size];
    private int current_index, swap1, swap2;

    @SuppressWarnings("exports")
    public MergeSort(Pane sortingPane) throws InterruptedException {

        initBars(sortingPane);
        // shuffleBars();
        // I call shuffleBars as the first line of MergeSort() Ideally it would be called here tho

        Thread sorterThread = new Thread(this::mergeSort);
        sorterThread.setDaemon(true);
        sorterThread.start();
    }

    private void initBars(Pane sortingPane) throws InterruptedException {
        float interval = (float) height / size;
        for (int i = 0; i < size; i++) {
            barHeight[i] = i * interval;
        }
        for (int i = 0; i < size; i++) {
            Rectangle bar = new Rectangle(i * barWidth, height - barHeight[i], barWidth, barHeight[i]);
            bar.setFill(Color.BLUE);
            bars.add(bar);
            sortingPane.getChildren().add(bar);
        }
        
        Platform.runLater(this::repaintBars);
        Thread.sleep(100);
    }


    private void merge(int left, int mid, int right) throws InterruptedException {
        int leftSize = mid - left + 1;
        int rightSize = right - mid;

        // Create temporary arrays
        float[] leftArray = new float[leftSize];
        float[] rightArray = new float[rightSize];

        // Copy data to temporary arrays
        for (int i = 0; i < leftSize; ++i)
            leftArray[i] = barHeight[left + i];
        for (int j = 0; j < rightSize; ++j)
            rightArray[j] = barHeight[mid + 1 + j];

        // Merge the temporary arrays back into barHeight
        int i = 0, j = 0, k = left;
        while (i < leftSize && j < rightSize) {
            if (leftArray[i] <= rightArray[j])
                barHeight[k++] = leftArray[i++];
            else
                barHeight[k++] = rightArray[j++];
            current_index = k;
            Thread.sleep(10);
            Platform.runLater(this::repaintBars);
        }

        // Copy remaining elements of leftArray
        while (i < leftSize) {
            barHeight[k++] = leftArray[i++];
            current_index = k;
            Thread.sleep(10);
            Platform.runLater(this::repaintBars);
        }

        // Copy remaining elements of rightArray
        while (j < rightSize) {
            barHeight[k++] = rightArray[j++];
            current_index = k;
            Thread.sleep(10);
            Platform.runLater(this::repaintBars);
        }

    }
    private void mergeSort() {
        try {
            shuffleBars();
            mergeSort(0, size -1);
        } catch (InterruptedException ignored) {}
    }
    private void mergeSort(int left, int right) throws InterruptedException {
        if (left < right) {
            // Find the middle point
            int mid = (left + right) / 2;

            // Sort first and second halves
            mergeSort(left, mid);
            mergeSort(mid + 1, right);

            // Merge the sorted halves
            merge(left, mid, right);
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
