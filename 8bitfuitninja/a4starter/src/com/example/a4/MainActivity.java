/**
 * CS349 Winter 2014
 * Assignment 4 Demo Code
 * Jeff Avery
 */
package com.example.a4;

import java.util.Timer;
import java.util.TimerTask;
import android.os.Handler;
import android.app.Activity;
import android.graphics.Point;
import android.os.Bundle;
import android.util.Log;
import android.view.Display;
import android.view.ViewGroup;


import com.example.a4complete.R;

public class MainActivity extends Activity {
    private Model model;
    private MainView mainView;
    private TitleView titleView;
    public static Point displaySize;
    Handler timerHandler = new Handler();
    Runnable timerRunnable = new Runnable() {
         @Override
         synchronized public void run() {
              model.setC();
              model.notifyObservers();
             
              
              timerHandler.removeCallbacks(timerRunnable);
              timerHandler.postDelayed(timerRunnable, 20);
         }
    };

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.getWindow().setTitle("CS349 A4 Demo");

        // save display size
        Display display = getWindowManager().getDefaultDisplay();
        displaySize = new Point();
        display.getSize(displaySize);

        // initialize model
        model = new Model();

        // set view
        setContentView(R.layout.main);
        //titleView.setBackgroundColor(getResources().getColor(android.R.color.darker_gray));
    }

    class RemindTask extends TimerTask {
      	 public void run() {
      		 Log.d("RemindTask","Test");
      		 model.setC();
      		 model.notifyObservers();
      	 }
       }
    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);

        // create the views and add them to the main activity
        titleView = new TitleView(this.getApplicationContext(), model);
        ViewGroup v1 = (ViewGroup) findViewById(R.id.main_1);
        v1.addView(titleView);

        mainView = new MainView(this.getApplicationContext(), model);
        ViewGroup v2 = (ViewGroup) findViewById(R.id.main_2);
        v2.addView(mainView);

        // notify all views
        model.initObservers();
        timerHandler.removeCallbacks(timerRunnable);
        timerHandler.postDelayed(timerRunnable, 0);
    }
}
