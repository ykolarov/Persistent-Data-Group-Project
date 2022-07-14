package com.sparta.persistentData.Model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MultiThread {

    private Thread thread_1;
    private Thread thread_2;
    private Thread thread_3;
    private Thread thread_4;

    public void MultiThreading() {
        this.loadThreads();
        this.startThreads();
    }

    private void loadThreads() {
        thread_1 = new Thread("t1");
        thread_2 = new Thread("t2");
        thread_3 = new Thread("t3");
        thread_4 = new Thread("t4");
    }

    private void startThreads() {
        this.thread_1.start();
        this.thread_2.start();
        this.thread_3.start();
        this.thread_4.start();
    }

    private void closeThreads() {
        this.thread_1.stop();
        this.thread_2.stop();
        this.thread_3.stop();
        this.thread_4.stop();
    }

    /**
     *  MAKE METHOD TO PUT IN DATABASE
     */
}
