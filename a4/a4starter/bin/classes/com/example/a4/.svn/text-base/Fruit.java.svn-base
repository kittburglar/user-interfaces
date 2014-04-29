/**
 * CS349 Winter 2014
 * Assignment 4 Demo Code
 * Jeff Avery
 */
package com.example.a4;
import android.graphics.*;
import android.util.Log;

/**
 * Class that represents a Fruit. Can be split into two separate fruits.
 */
public class Fruit {
    private Path path = new Path();
    private Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
    private Matrix transform = new Matrix();
    private Region region = new Region();
    private boolean falling = false;
    private boolean missed = false;
    private boolean slashed = false;
    public int slashtype = 0;
    public int spawntype = 0;
    public int timeOnScreen = 0;
    public int fruitType = 0;
    public boolean startFruit = false;
    public int fruitNumber= 0;
    
    /**
     * A fruit is represented as Path, typically populated 
     * by a series of points 
     */
    Fruit(float[] points) {
        init();
        this.path.reset();
        this.path.moveTo(points[0], points[1]);
        for (int i = 2; i < points.length; i += 2) {
            this.path.lineTo(points[i], points[i + 1]);
        }
        this.path.moveTo(points[0], points[1]);
        
    }

    Fruit(Region region) {
        init();
        Region region2 = new Region();
        this.path = region.getBoundaryPath();
        region2.setPath(path,new Region(new Rect(0,0,1000,1000)));
        this.region = region2;
    }

    Fruit(Path path) {
        init();
        Region region2 = new Region();
        this.path = path;
        region2.setPath(path,new Region(new Rect(0,0,1000,1000)));
        this.region = region2;
    }

    private void init() {
        this.paint.setColor(Color.RED);
        this.paint.setStrokeWidth(5);
        
    }
    public Region getRegion() { return region; }
    /**
     * The color used to paint the interior of the Fruit.
     */
    public void setFalling(boolean f){
        falling = f;
    }

    public boolean getFalling(){
        return falling;
    }

    public void setMissed(boolean m){
        missed = m;
    }

    public int getFruitNum(){
        return fruitNumber;
    }

    public void setFruitNum(int m){
        fruitNumber = m;
    }
    
    public boolean getMissed(){
        return missed;
    }

    public void setSlashed(boolean m){
        slashed = m;
    }

    public boolean getSlashed(){
        return slashed;
    }
    public int getFillColor() { return paint.getColor(); }
    public void setFillColor(int color) { paint.setColor(color); }

    /**
     * The width of the outline stroke used when painting.
     */
    public double getOutlineWidth() { return paint.getStrokeWidth(); }
    public void setOutlineWidth(float newWidth) { paint.setStrokeWidth(newWidth); }

    /**
     * Concatenates transforms to the Fruit's affine transform
     */
    public void rotate(float theta) { transform.postRotate(theta); }
    public void scale(float x, float y) { transform.postScale(x, y); }
    public void translate(float tx, float ty) { transform.postTranslate(tx, ty); }

    /**
     * Returns the Fruit's affine transform that is used when painting
     */
    public Matrix getTransform() { return transform; }

    /**
     * The path used to describe the fruit shape.
     */
    public Path getTransformedPath() {
        Path originalPath = new Path(path);
        Path transformedPath = new Path();
        originalPath.transform(transform, transformedPath);
        return transformedPath;
    }

    /**
     * Paints the Fruit to the screen using its current affine
     * transform and paint settings (fill, outline)
     */
    public void draw(Canvas canvas) {
        // TODO BEGIN CS349
        // tell the shape to draw itself using the matrix and paint parameters
        // TODO END CS349
    	paint.setColor(getFillColor());
    	canvas.drawPath(getTransformedPath(), paint);
    	
    }

    /**
     * Tests whether the line represented by the two points intersects
     * this Fruit.
     */
    public boolean intersects(PointF p1, PointF p2) {
        // TODO BEGIN CS349
        // calculate angle between points
        // rotate and flatten points passed in 
        // rotate path and create region for comparison
        // TODO END CS349
    	Path line = new Path();
    	line.reset();
    	line.moveTo(p1.x, p1.y);
    	line.lineTo(p2.x, p2.y);
    	line.lineTo(p2.x + (float) .1,  p2.y+ (float) .1);
    	line.lineTo(p1.x, p1.y+ (float) .1);
    	line.lineTo(p1.x, p1.y);
    	
    	Region region1 = new Region();
    	boolean valid1 = region1.setPath(line, new Region(new Rect(0,0,1000,1000)));
    
    	Region region2 = new Region();
    	boolean valid2 = region2.setPath(getTransformedPath(), new Region(new Rect(0,0,1000,1000)));
    	/*
    	if (valid1){
    		Log.d("intersect","valid1");
    	}
    	if (valid2){
    		Log.d("intersect","valid2");
    	}
    	if (region2.op(region1,Region.Op.INTERSECT)){
    		Log.d("intersect","real intersection1");
    	}
    	else{
    		Log.d("intersect","no real intersection");
    	}
    	*/
    	if(!contains(p1) && !contains(p2) && region1.op(region2,Region.Op.INTERSECT)){
    		//Log.d("hey", "intersect success" + p1.x + "," + p1.y);
    		return true;
    	}
    	else{
    		//Log.d("intersect", "intersect failed" + p1.x + "," + p1.y);
    		return false;
    	}
    	
    	//contains(p1);
    	//return false;
    }

    /**
     * Returns whether the given point is within the Fruit's shape.
     */
    public boolean contains(PointF p1) {
        Region region = new Region();
        boolean valid = region.setPath(getTransformedPath(), new Region(new Rect(0,0,1000,1000)));
        //Log.d("contains",""+valid+ " " +  region.contains((int) p1.x, (int) p1.y));
        return valid && region.contains((int) p1.x, (int) p1.y);
    }

    /**
     * This method assumes that the line represented by the two points
     * intersects the fruit. If not, unpredictable results will occur.
     * Returns two new Fruits, split by the line represented by the
     * two points given.
     */
    public Fruit[] split(PointF p1, PointF p2) {
    	Path topPath = null;
    	Path bottomPath = null;
    	// TODO BEGIN CS349
        // calculate angle between points
        // rotate and flatten points passed in
        // rotate region
        // define region masks and use to split region into top and bottom
        // TODO END CS349
    	
    	//Slice + to the roof
    	Path line = new Path();
    	if((p1.x <= p2.x) && (p1.y >= p2.y)){
	    	line.moveTo(p2.x, p2.y);
	    	line.lineTo(480, 0);
	    	line.lineTo(480, 600);
	    	line.lineTo(0, 600);
	    	line.lineTo(p1.x, p1.y);
    	}
    	else if((p1.x > p2.x) && (p1.y < p2.y)){
    		line.moveTo(p1.x, p1.y);
	    	line.lineTo(480, 0);
	    	line.lineTo(480, 600);
	    	line.lineTo(0, 600);
	    	line.lineTo(p2.x, p2.y);
    	}
    	else if((p1.x < p2.x) && (p1.y < p2.y)){
    		line.moveTo(p1.x, p1.y);
    		line.lineTo(0, 0);
    		line.lineTo(480, 0);
    		line.lineTo(480, 600);
    		line.lineTo(p2.x, p2.y);
    	}
    	else if((p1.x > p2.x)&&(p1.y > p2.y)){
    		line.moveTo(p2.x, p2.y);
    		line.lineTo(0, 0);
    		line.lineTo(480, 0);
    		line.lineTo(480, 600);
    		line.lineTo(p1.x, p1.y);
    		
    	}
    	//FruitShape
    	Region region2 = new Region();
    	boolean valid2 = region2.setPath(getTransformedPath(), new Region(new Rect(0,0,1000,1000)));
    	//Top of Fruit (Region)
    	Region topRegion = new Region();
    	boolean validtopRegion = topRegion.setPath(line, new Region(new Rect(0,0,1000,1000)));
    	//Intersect this region with fruit
    	topRegion.op(topRegion, region2, Region.Op.INTERSECT);
    	topPath = topRegion.getBoundaryPath();
    	if (validtopRegion){
    		Log.d("intersect","topRegion is not noll");
    	}
    	
    	
    	//Slice + to the roof
    	Path line2 = new Path();
    	if((p1.x <= p2.x) && (p1.y >= p2.y)){
	    	line2.moveTo(p1.x, p1.y);
	    	line2.lineTo(0, 600);
	    	line2.lineTo(0, 0);
	    	line2.lineTo(480, 0);
	    	line2.lineTo(p2.x, p2.y);
    	}
    	else if((p1.x > p2.x) && (p1.y < p2.y)){
    		line2.moveTo(p1.x, p1.y);
	    	line2.lineTo(480, 0);
	    	line2.lineTo(0, 0);
	    	line2.lineTo(0, 600);
	    	line2.lineTo(p2.x, p2.y);
    	}
    	else if((p1.x < p2.x) && (p1.y < p2.y)){
    		line2.moveTo(p1.x, p1.y);
    		line2.lineTo(0, 0);
    		line2.lineTo(0, 600);
    		line2.lineTo(480, 600);
    		line2.lineTo(p2.x, p2.y);
    	}
    	else if((p1.x > p2.x)&&(p1.y > p2.y)){
    		line2.moveTo(p2.x, p2.y);
    		line2.lineTo(0, 0);
    		line2.lineTo(0, 600);
    		line2.lineTo(480, 600);
    		line2.lineTo(p1.x, p1.y);
    	}
    	//FruitShape
    	//Region region2 = new Region();
    	//boolean valid2 = region2.setPath(getTransformedPath(), new Region(new Rect(0,0,1000,1000)));
    	//Top of Fruit (Region)
    	Region bottomRegion = new Region();
    	boolean validbottomRegion = bottomRegion.setPath(line2, new Region(new Rect(0,0,1000,1000)));
    	//Intersect this region with fruit
    	bottomRegion.op(bottomRegion, region2, Region.Op.INTERSECT);
    	bottomPath = bottomRegion.getBoundaryPath();
    	if (validbottomRegion){
    		Log.d("intersect","bottomRegion is not null");
    	}
    	//bottomPath = topPath;
    	
        if (topPath != null && bottomPath != null) {
        	Fruit Fruit1 = new Fruit(topPath);
            Fruit1.setFalling(getFalling());
            Fruit1.setSlashed(true);
            Fruit1.setFillColor(getFillColor());
            Fruit1.setOutlineWidth((float) getOutlineWidth());
            //Fruit1.setOutlineColor(getOutlineColor());
            //Fruit1.slashtype =  1 + (int)(Math.random() * ((2 - 1) + 1));
            Fruit1.slashtype = 1;

            //Fruit 2
            Fruit Fruit2 = new Fruit(bottomPath);
            Fruit2.setFalling(getFalling());
            Fruit2.setSlashed(true);
            
            Fruit2.setFillColor(getFillColor());
            Fruit2.setOutlineWidth((float) getOutlineWidth());
            //Fruit2.setOutlineColor(getOutlineColor());
            Fruit2.slashtype = 2;	
        	
        	float[] Splatter = {0,10,5,10,5,5,10,5,10,0,   20,0,20,5,25,5,25,10,30,10,      30,20,25,20,25,25,20,25,20,30      ,10,30,10,25,5,25,5,20,0,20};
        	Fruit Splatter1 = new Fruit(Splatter);
        	Region region = new Region();
        	Splatter1.scale((float) 1.5, (float)1.5);
	        region.setPath(Fruit1.getTransformedPath(),new Region(new Rect(0,0,1000,1000)));
            Splatter1.translate(region.getBounds().left, region.getBounds().top);
            if(fruitNumber == 0){
        	Splatter1.setFillColor(Color.rgb(249, 222, 129));
            }
            else if(fruitNumber == 1){
            	Splatter1.setFillColor(Color.rgb(234, 4, 43));
            }
            else if(fruitNumber == 2){
            	Splatter1.setFillColor(Color.rgb(232, 234, 13));
            }
            else if(fruitNumber == 3){
            	Splatter1.setFillColor(Color.rgb(222, 213, 87));
            }
            else if(fruitNumber == 4){
            	Splatter1.setFillColor(Color.rgb(232, 124, 108));
            }
        	Splatter1.translate(0 + (int)(Math.random() * ((50 - 0) + 1)),0 + (int)(Math.random() * ((50 - 0) + 1)));
        	Splatter1.fruitType = 2;
        	
        	Fruit Splatter2 = new Fruit(Splatter);
        	Splatter2.translate(region.getBounds().left, region.getBounds().top);
        	if(fruitNumber == 0){
            	Splatter2.setFillColor(Color.rgb(249, 222, 129));
                }
                else if(fruitNumber == 1){
                	Splatter2.setFillColor(Color.rgb(234, 4, 43));
                }
                else if(fruitNumber == 2){
                	Splatter2.setFillColor(Color.rgb(232, 234, 13));
                }
                else if(fruitNumber == 3){
                	Splatter2.setFillColor(Color.rgb(222, 213, 87));
                }
                else if(fruitNumber == 4){
                	Splatter2.setFillColor(Color.rgb(232, 124, 108));
                }
        	Splatter2.translate(0 + (int)(Math.random() * ((80 - 45) + 1)),0 + (int)(Math.random() * ((80 - 45) + 1)));
        	Splatter2.fruitType = 2;
        	
        	
        	Fruit Splatter4 = new Fruit(Splatter);
        	Splatter4.scale((float) .5,(float) .5);
        	Splatter4.translate(region.getBounds().left, region.getBounds().top);
        	if(fruitNumber == 0){
            	Splatter4.setFillColor(Color.rgb(249, 222, 129));
                }
                else if(fruitNumber == 1){
                	Splatter4.setFillColor(Color.rgb(234, 4, 43));
                }
                else if(fruitNumber == 2){
                	Splatter4.setFillColor(Color.rgb(232, 234, 13));
                }
                else if(fruitNumber == 3){
                	Splatter4.setFillColor(Color.rgb(222, 213, 87));
                }
                else if(fruitNumber == 4){
                	Splatter4.setFillColor(Color.rgb(232, 124, 108));
                }
        	Splatter4.translate(0 + (int)(Math.random() * ((120 - 75) + 1)),0 + (int)(Math.random() * ((120 - 75) + 1)));
        	Splatter4.fruitType = 2;
        	
        	
        	return new Fruit[] { Fruit1, Fruit2, Splatter1, Splatter2, Splatter4};
        }
        return new Fruit[0];
    }
}
