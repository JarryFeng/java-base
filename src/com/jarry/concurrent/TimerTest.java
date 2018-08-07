package com.jarry.concurrent;

import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by jarry on 2018/7/26.
 */
public class TimerTest {
    private static int x = 0;
    public static void main(String[] args) {

        class MyTask extends TimerTask {

            @Override
            public void run() {
                x++;
                System.out.println("booming" + x);
                System.out.println(new Date().getSeconds());

                new Timer().schedule(new MyTask(),2000* (1+ x%2));
            }
        }

        new Timer().schedule(new MyTask(), 2000);
    }
}
