package com.kgwb.fxgui;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.control.ProgressBar;
import javafx.util.Duration;

import java.util.concurrent.atomic.AtomicInteger;

public class ParallelProgressBar extends ProgressBar {
    private AtomicInteger myDoneCount = new AtomicInteger();
    private int myTotalCount;
    private Timeline myWatcher = new Timeline(new KeyFrame(Duration.millis(10), e -> update()));

    private void update() {
        setProgress(1.0 * myDoneCount.get() / myTotalCount);
        if (myDoneCount.get() >= myTotalCount) {
            myWatcher.stop();
            myTotalCount = 0;
        }
    }

    public boolean isRunning() {
        return myTotalCount > 0;
    }

    public void start(int totalCount) {
        myDoneCount.set(0);
        myTotalCount = totalCount;
        setProgress(0.0);
        myWatcher.setCycleCount(Timeline.INDEFINITE);
        myWatcher.play();
    }

    public void add(int n) {
        myDoneCount.addAndGet(n);
    }
}