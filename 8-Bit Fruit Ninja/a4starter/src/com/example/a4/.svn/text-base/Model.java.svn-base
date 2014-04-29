/**
 * CS349 Winter 2014
 * Assignment 4 Demo Code
 * Jeff Avery & Michael Terry
 */
package com.example.a4;

import android.util.Log;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.Region;
import android.os.Handler;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;
import java.util.Timer;
import java.util.TimerTask;


/*
 * Class the contains a list of fruit to display.
 * Follows MVC pattern, with methods to add observers,
 * and notify them when the fruit list changes.
 */
public class Model extends Observable {
    // List of fruit that we want to display
    private ArrayList<Fruit> shapes = new ArrayList<Fruit>();
    private int count;
    private int missCount;
    private int livesLeft = 5;
    public boolean running = true;
    //private Path path = new Path();
    // Constructor
    Model() {
        shapes.clear();
        
        /*
        final Handler timerHandler = new Handler();
        final Runnable timerRunnable = new Runnable(){
        	@Override
        	synchronized public void run(){
        		setChanged();
        		notifyObservers();
        	Log.d("Timer Inturrupt","sup");
        	timerHandler.removeCallbacks(this);
        	timerHandler.postDelayed(this,20);
        
        	}
        };
        */
        
        //new Timer().scheduleAtFixedRate(new RemindTask(), 0, 1000);
    };
/*
    class RemindTask extends TimerTask {
   	 public void run() {
   		 Log.d("RemindTask","Test");
   		 setChanged();
   		 notifyObservers();
   	 }
    }
  */
    
    // Model methods
    // You may need to add more methods here, depending on required functionality.
    // For instance, this sample makes to effort to discard fruit from the list.
    public void setC(){
    	setChanged();
    }
    public void add(Fruit s) {
        shapes.add(s);
        setChanged();
        notifyObservers();
    }
    
    public void incCount(){
        count++;
    }

    public int getCount(){
        return count;
    }

    public void setCount(int newCount){
          count = newCount;
    }

    public int getMissCount(){
        return missCount;
    }

    public void intMissCount(){
        missCount++;
    }

    public int getLivesLeft(){
        return livesLeft;
    }

    public void decLivesLeft(){
    	if(getLivesLeft() > 0){
        livesLeft--;
    	}
    }

    public void setLivesLeft(int lives){
        livesLeft = lives;
    }
    public void move(Fruit s){
    	/*
    	    if(s.getFalling() && s.getTransformedShape().getBounds().getY() == 445){
    	        s.setMissed(true);
    	        s.setFalling(false);
    	    }
    	      */
    	    /*
    	    else if(s.getFalling()){
    	          s.translate(0, 5);
    	    }
    	    */

    	        if(-17 + s.timeOnScreen/4 == 0){
    	        	Log.d("move","reached top of arch!");
    	            s.setFalling(true);
    	        }
    	        
    	        Region region = new Region();
    	        region.setPath(s.getTransformedPath(),new Region(new Rect(0,0,1000,1000)));
    	        //this.region = region;
    	        //if((s.getFalling() && s.getTransformedShape().getBounds().getY() >= 445) ){
    	        if((s.getFalling() && region.getBounds().centerY() >= 700) ){
    	        	Log.d("move","hit bottom!");
    	        	s.setMissed(true);
    	        }
    	        if(s.fruitType == 2){
            		s.scale((float) 1-s.timeOnScreen/20, (float)1-s.timeOnScreen/20);
            		notifyObservers();
            		if(region.getBounds().height()==0)
            			getShapesReal().remove(s);
                }
    	        else{
    	            if(s.getSlashed()){
    	                if(s.slashtype == 1){
    	                    s.translate(5, s.timeOnScreen/4);
    	                notifyObservers();
    	                }
    	                else if(s.slashtype == 2){
    	                    s.translate(-5, s.timeOnScreen/4);
    	                notifyObservers();
    	                }
    	                //s.rotate(0 + (int)(Math.random() * ((360 - 0) + 1)));
    	            }
    	            else{
    	            	if(s.startFruit){
    	            		
    	            	}
    	            	else if(s.spawntype == 1){
    	                    s.translate((float) 1, (float) -17 + s.timeOnScreen/4);
    	            	notifyObservers();
    	            	}
    	                else{
    	                    s.translate((float)-1,(float) -17 + s.timeOnScreen/4);
    	                    notifyObservers();
    	                }
    	            }

    	        }
    	    //yInit + v*t - 4.9t^2

    	    notifyObservers();
    	  }
    
    public void remove(Fruit s) {
        shapes.remove(s);
    }

    public ArrayList<Fruit> getShapes() {
        return (ArrayList<Fruit>) shapes.clone();
    }

    public ArrayList<Fruit> getShapesReal() {
        return (ArrayList<Fruit>) shapes;
    }
    
    // MVC methods
    // Basic MVC methods to bind view and model together.
    public void addObserver(Observer observer) {
        super.addObserver(observer);
    }

    // a helper to make it easier to initialize all observers
    public void initObservers() {
        setChanged();
        notifyObservers();
    }

    @Override
    public synchronized void deleteObserver(Observer observer) {
        super.deleteObserver(observer);
        setChanged();
        notifyObservers();
    }

    @Override
    public synchronized void deleteObservers() {
        super.deleteObservers();
        setChanged();
        notifyObservers();
    }
}
