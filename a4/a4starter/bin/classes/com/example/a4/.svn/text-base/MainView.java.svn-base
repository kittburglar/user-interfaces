/**
 * CS349 Winter 2014
 * Assignment 4 Demo Code
 * Jeff Avery & Michael Terry
 */
package com.example.a4;

import android.content.Context;
import android.graphics.*;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import java.util.Iterator;
import java.util.Observable;
import java.util.Observer;

import com.example.a4complete.R;

/*
 * View of the main game area.
 * Displays pieces of fruit, and allows players to slice them.
 */
public class MainView extends View implements Observer {
    private final Model model;
    private final MouseDrag drag = new MouseDrag();
    private int count = 0;
    private int spawnrate = 30;
    // Constructor
    MainView(Context context, Model m) {
        super(context);

        // register this view with the model
        model = m;
        model.addObserver(this);
        //setBackgroundColor(Color.GRAY);
        // TODO BEGIN CS349
        // test fruit, take this out before handing in!
        
/*
        Fruit f2 = new Fruit(new float[] {0, 20, 20, 0, 40, 0, 60, 20, 60, 40, 40, 60, 20, 60, 0, 40});
        f2.translate(200, 550);
        model.add(f2);
        */
        // TODO END CS349

        // add controller
        // capture touch movement, and determine if we intersect a shape
        setOnTouchListener(new OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        // Log.d(getResources().getString(R.string.app_name), "Touch down");
                        drag.start(event.getX(), event.getY());
                        break;

                    case MotionEvent.ACTION_UP:
                        // Log.d(getResources().getString(R.string.app_name), "Touch release");
                        drag.stop(event.getX(), event.getY());

                        // find intersected shapes
                        Iterator<Fruit> i = model.getShapes().iterator();
                        while(i.hasNext()) {
                            Fruit s = i.next();
                            if (s.intersects(drag.getStart(), drag.getEnd()) && s.fruitType == 0) {
                            	
                            	if(s.startFruit){
                                    //System.out.println("End Fruit!!");

                                    model.getShapesReal().clear();
                                    model.setLivesLeft(5);
                                    //timeElapsed = 0;
                                    model.setCount(0);
                                    //gameOver.setVisible(false);
                                    model.running = true;
                                    //gameOver.setText("gameOver");

                                }
                            	//s.setFillColor(Color.RED);
                                try {
                                    Fruit[] newFruits = s.split(drag.getStart(), drag.getEnd());
                                    model.getShapesReal().remove(s);
                                    if(!s.getSlashed() && !s.startFruit){
                                        model.incCount();
                                    }
                                    // TODO BEGIN CS349
                                    // you may want to place the fruit more carefully than this
                                    
                                    // TODO END CS349
                                    
                                    model.add(newFruits[0]);
                                    model.add(newFruits[1]);
                                                                        
                                    model.add(newFruits[2]);
                                    model.add(newFruits[3]);
                                    model.add(newFruits[4]);
                                    // TODO BEGIN CS349
                                    // delete original fruit from model
                                    // TODO END CS349

                                } catch (Exception ex) {
                                    Log.e("fruit_ninja", "Error: " + ex.getMessage());
                                }
                            } else {
                                //s.setFillColor(Color.BLUE);
                            }
                            invalidate();
                        }
                        break;
                }
                return true;
            }
        });
    }

    // inner class to track mouse drag
    // a better solution *might* be to dynamically track touch movement
    // in the controller above
    class MouseDrag {
        private float startx, starty;
        private float endx, endy;

        protected PointF getStart() { return new PointF(startx, starty); }
        protected PointF getEnd() { return new PointF(endx, endy); }

        protected void start(float x, float y) {
            this.startx = x;
            this.starty = y;
        }

        protected void stop(float x, float y) {
            this.endx = x;
            this.endy = y;
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        // draw background
        setBackgroundColor(Color.GRAY);

        // draw all pieces of fruit
        for (Fruit s : model.getShapes()) {
            s.draw(canvas);
        }
    
    }

    @Override
    public void update(Observable observable, Object data) {
    	count++;
    	
    	if((count % spawnrate) == 0 && model.running){
    		spawnrate = 50 + (int)(Math.random() * ((60 - 50) + 1));
    		//Fruit f = new Fruit(new float[] {0, 20, 20, 0, 40, 0, 60, 20, 60, 40, 40, 60, 20, 60, 0, 40});
    		float[] Apple = {0,10,5,10,5,5,10,5,10,0,   35,0,35,5,40,5,40,10,45,10,      45,35,40,35,40,40,35,40,35,45      ,10,45,10,40,5,40,5,35,0,35};
    		float[] Watermelon = {0,10,5,10,5,5,10,5,10,0,   60,0,60,5,65,5,65,10,70,10,      70,50,65,50,65,55,60,55,60,60,      10,60,10,55,5,55,5,50,0,50 };
    		float[] Banana = {0,10,5,10,5,5,10,5,10,0,   30,0,30,5,35,5,35,10,40,10,      40,50,35,50,35,55,30,55,30,60      ,10,10,10,10,5,5,5,5,0,0 };
    		float[] Pineapple = {0,10,5,10,5,5,10,5,10,0,   30,0,30,5,35,5,35,10,40,10,      40,50,35,50,35,55,30,55,30,60      ,10,60,10,55,5,55,5,50,0,50 };
    		float[] Peach = {0,10,5,10,5,5,10,5,10,0,   30,0,30,5,35,5,35,10,40,10,      40,30,35,30,35,35,30,35,30,40      ,10,40,10,35,5,35,5,30,0,30 };
    		int fruitNumber = 0 + (int)(Math.random() * ((4 - 0) + 1));
    		/*
    		if(!model.running){
                //System.out.println("End Fruit!!");

                model.getShapesReal().clear();
                model.setLivesLeft(5);
                timeElapsed = 0;
                model.setCount(0);
                gameOver.setVisible(false);
                model.running = true;
                //gameOver.setText("gameOver");

            }*/
    		if(model.getLivesLeft() <= 0){
                //t.stop();
                //timeElap = 0;
    			Log.d("MainView","0 lives");
                model.getShapesReal().clear();
                float[] endPath = {0,10,5,10,5,5,10,5,10,0,   35,0,35,5,40,5,40,10,45,10,      45,35,40,35,40,40,35,40,35,45      ,10,45,10,40,5,40,5,35,0,35};
                Fruit endFruit = new Fruit(endPath);
                endFruit.setFillColor(Color.BLACK);
                //endFruit.setOutlineColor(Color.GRAY);
                endFruit.startFruit = true;
                endFruit.translate(200,100);
                model.add(endFruit);
                model.running = false;
            }
    		if(fruitNumber == 0){
    			Fruit f = new Fruit(Apple);
    			f.setFruitNum(fruitNumber);
    			f.setFillColor(Color.rgb(193, 31, 24));
    			f.scale((float)1.5,(float)1.5);
        		f.spawntype = 1 + (int)(Math.random() * ((2 - 1) + 1));
                if(f.spawntype == 1)
                    f.translate(0 + (int)(Math.random() * ((200 - 0) + 1)), 700);
                else
                    f.translate(200 + (int)(Math.random() * ((400 - 200) + 1)),700);
                if(model.running)
                model.add(f);	
    		}
    		else if(fruitNumber == 1){
    			Fruit f2 = new Fruit(Watermelon);
    			f2.setFruitNum(fruitNumber);
    			f2.scale((float)1.5,(float)1.5);
    			f2.setFillColor(Color.rgb(77, 158, 54));
        		f2.spawntype = 1 + (int)(Math.random() * ((2 - 1) + 1));
                if(f2.spawntype == 1)
                    f2.translate(0 + (int)(Math.random() * ((200 - 0) + 1)), 700);
                else
                    f2.translate(200 + (int)(Math.random() * ((400 - 200) + 1)),700);
                if(model.running)
                model.add(f2);	
    		}
    		else if(fruitNumber == 2){
    			Fruit f2 = new Fruit(Banana);
    			f2.setFruitNum(fruitNumber);
    			f2.scale((float)1.5,(float)1.5);
    			f2.setFillColor(Color.rgb(232, 234, 13));
        		f2.spawntype = 1 + (int)(Math.random() * ((2 - 1) + 1));
                if(f2.spawntype == 1)
                    f2.translate(0 + (int)(Math.random() * ((200 - 0) + 1)), 700);
                else
                    f2.translate(200 + (int)(Math.random() * ((400 - 200) + 1)),700);
                if(model.running)
                model.add(f2);	
    		}
    		else if(fruitNumber == 3){
    			Fruit f3 = new Fruit(Pineapple);
    			f3.setFruitNum(fruitNumber);
    			f3.scale((float)1.5,(float)1.5);
    			f3.setFillColor(Color.rgb(222, 213, 87));
        		f3.spawntype = 1 + (int)(Math.random() * ((2 - 1) + 1));
                if(f3.spawntype == 1)
                    f3.translate(0 + (int)(Math.random() * ((200 - 0) + 1)), 700);
                else
                    f3.translate(200 + (int)(Math.random() * ((400 - 200) + 1)), 700);
                if(model.running)
                model.add(f3);	
    		}
    		if(fruitNumber == 4){
    			Fruit f4 = new Fruit(Peach);
    			f4.setFruitNum(fruitNumber);
    			f4.setFillColor(Color.rgb(247, 155, 133));
    			f4.scale((float)1.5,(float)1.5);
        		f4.spawntype = 1 + (int)(Math.random() * ((2 - 1) + 1));
                if(f4.spawntype == 1)
                    f4.translate(0 + (int)(Math.random() * ((200 - 0) + 1)), 700);
                else
                    f4.translate(200 + (int)(Math.random() * ((400 - 200) + 1)), 700);
                if(model.running)
                model.add(f4);	
    		}
    		
    	}
    	//Log.d("MainView","Timer!");
    	Iterator<Fruit> i = model.getShapes().iterator();
    	
        while(i.hasNext()) {
        	Fruit s = i.next();
        	
        	s.timeOnScreen++;
        	//if(s.fruitType == 0)
        	model.move(s);
        	Region region = new Region();
	        region.setPath(s.getTransformedPath(),new Region(new Rect(0,0,1000,1000)));
        	
	        
            if(s.getMissed()){
                if(!s.getSlashed()){
                	Log.d("MainView",model.getLivesLeft() + "We missed");
                model.intMissCount();
                model.decLivesLeft();
                //model.getShapesReal().remove(s);
                }
                model.getShapesReal().remove(s);
            }
        }
        
        invalidate();
    }
}
