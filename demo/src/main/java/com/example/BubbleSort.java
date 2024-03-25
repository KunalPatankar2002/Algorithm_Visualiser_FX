package com.example;

import javafx.application.Platform;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class BubbleSort {

    private final int width = 1000, height = 600;
    private final int size = 200;
    private final float barWidth = (float) width / size;
    private final List<Rectangle> bars = new ArrayList<>();
    private float[] barHeight = new float[size];
    private int current_index, swap1, swap2;

    @SuppressWarnings("exports")
    public BubbleSort(Pane sortingPane) throws InterruptedException {

        initBars(sortingPane);
        // shuffleBars();
        // I call shuffleBars as the first line of bubbleSort() Ideally it would be
        // called here tho

        Thread sorterThread = new Thread(this::bubbleSort);
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
            bar.setFill(Color.MAGENTA);
            bars.add(bar);
            sortingPane.getChildren().add(bar);
        }

        Platform.runLater(this::repaintBars);
        Thread.sleep(100);
    }

    private void bubbleSort() {
        try {
            shuffleBars();
            float temp = 0;
            for (int i = 0; i < size; i++) {
                for (int j = 1; j < (size - i); j++) {
                    if (barHeight[j - 1] > barHeight[j]) {
                        // swap elements
                        temp = barHeight[j - 1];
                        barHeight[j - 1] = barHeight[j];
                        barHeight[j] = temp;
                    }
                    swap1 = j-1;
                    swap2 = j;
                    Thread.sleep(10);
                    Platform.runLater(this::repaintBars);
                }
            }
        } catch (InterruptedException ignored) {
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
