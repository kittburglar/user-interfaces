/**
 * CS349 Winter 2014
 * Assignment 3 Demo Code
 * Jeff Avery & Michael Terry
 */
import javax.swing.*;
import java.awt.*;
import java.awt.geom.*;
import java.lang.Math.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


/**
 * Class that represents a Fruit. Can be split into two separate fruits.
 */
public class Fruit implements FruitInterface {
    private Area            fruitShape   = null;
    private Color           fillColor    = Color.BLUE;
    private Color           outlineColor = Color.BLACK;
    private AffineTransform transform    = new AffineTransform();
    private double          outlineWidth = 5;
    private Timer t;
    private int fps = 40;
    private boolean falling = false;
    private boolean missed = false;
    private boolean slashed = false;
    public int slashtype = 0;
    public int spawntype = 0;
    public int timeOnScreen = 0;
    public int fruitType = 0;
    public boolean startFruit = false;
    /**
     * A fruit is represented using any arbitrary geometric shape.
     */
    Fruit (Area fruitShape) {
        this.fruitShape = (Area)fruitShape.clone();
    }

    /**
     * The color used to paint the interior of the Fruit.
     */
    public Color getFillColor() {
        return fillColor;
    }
    /**
     * The color used to paint the interior of the Fruit.
     */
    public void setFillColor(Color color) {
        fillColor = color;
    }
    /**
     * The color used to paint the outline of the Fruit.
     */
    public Color getOutlineColor() {
        return outlineColor;
    }
    /**
     * The color used to paint the outline of the Fruit.
     */
    public void setOutlineColor(Color color) {
        outlineColor = color;
    }
    
    /**
     * Gets the width of the outline stroke used when painting.
     */
    public double getOutlineWidth() {
        return outlineWidth;
    }

    /**
     * Sets the width of the outline stroke used when painting.
     */
    public void setOutlineWidth(double newWidth) {
        outlineWidth = newWidth;
    }

    /**
     * Concatenates a rotation transform to the Fruit's affine transform
     */
    public void rotate(double theta) {
        transform.rotate(theta);
    }

    /**
     * Concatenates a scale transform to the Fruit's affine transform
     */
    public void scale(double x, double y) {
        transform.scale(x, y);
    }

    /**
     * Concatenates a translation transform to the Fruit's affine transform
     */
    public void translate(double tx, double ty) {
        transform.translate(tx, ty);
    }

    /**
     * Returns the Fruit's affine transform that is used when painting
     */
    public AffineTransform getTransform() {
        return (AffineTransform)transform.clone();
    }

    /**
     * Creates a transformed version of the fruit. Used for painting
     * and intersection testing.
     */
    public Area getTransformedShape() {
        return fruitShape.createTransformedArea(transform);
    }

    public void setFalling(boolean f){
        falling = f;
    }

    public boolean getFalling(){
        return falling;
    }

    public void setMissed(boolean m){
        missed = m;
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
    /**
     * Paints the Fruit to the screen using its current affine
     * transform and paint settings (fill, outline)
     */
    public void draw(Graphics2D g2) {
        // TODO BEGIN CS349
        //g2.translate(getTransform().getTranslateX(), getTransform().getTranslateY());
        //g2.scale(getTransform().getScaleX(),getTransform().getScaleY());
        //g2.rotate(getTransform().get);
        AffineTransform originalTransform = getTransform();
        g2.setTransform(getTransform());
        g2.setPaint(getFillColor());
        g2.fill(fruitShape);
        g2.setPaint(getOutlineColor());
        float f = (float) getOutlineWidth();
        g2.setStroke(new BasicStroke(f));
        g2.draw(fruitShape);
        g2.setTransform(originalTransform);
        /*
        ActionListener rePainter = new ActionListener(){
            public void actionPerformed(ActionEvent e){
                //translate(getTransform().getTranslateX()-1,getTransform().getTranslateY());
                //g2.draw(fruitShape);
            }
        };
        t = new Timer(1000/fps, rePainter);
        t.start();
*/
        // TODO END CS349
    }

    public Timer getTimer(){
        return t;
    }
    /**
     * Tests whether the line represented by the two points intersects
     * this Fruit.
     */
    public boolean intersects(Point2D p1, Point2D p2) {
        // TODO BEGIN CS349
        Line2D Shape1 = new Line2D.Double(p1,p2);
        if(!contains(p1) && !contains(p2) && Shape1.intersects(getTransformedShape().getBounds2D()))
        return true;
        else
        return false;
        // TODO END CS349
        //return false;
    }

    /**
     * Returns whether the given point is within the Fruit's shape.
     */
    public boolean contains(Point2D p1) {
        return this.getTransformedShape().contains(p1);
    }

    /**
     * This method assumes that the line represented by the two points
     * intersects the fruit. If not, unpredictable results will occur.
     * Returns two new Fruits, split by the line represented by the
     * two points given.
     */
    public Fruit[] split(Point2D p1, Point2D p2) throws NoninvertibleTransformException {

        Area topArea = null;
        Area bottomArea = null;

        // TODO BEGIN CS349
        Double x = getTransformedShape().getBounds().getX();
        Double y = getTransformedShape().getBounds().getY();
        Double deltaY = p2.getY() - p1.getY();
        Double deltaX = p2.getX() - p1.getX();
        Double angle = Math.atan(deltaY / deltaX) * 180 / Math.PI;
        //System.out.println(angle);
        transform.setToIdentity();
       //fruitShape.transform(AffineTransform.getTranslateInstance(-p1.getY(), -p1.getX()));
        //transform.translate(-p1.getX(),-p1.getY());
        if(angle < 0){
            if(angle > -15){
                transform.translate(0,fruitShape.getBounds().getHeight()/2);
            }

            else if(angle < -75){
                transform.translate(0,-fruitShape.getBounds().getHeight()/2);
            }

            transform.rotate(Math.toRadians(-angle));
            transform.translate(0, -fruitShape.getBounds().getHeight());

            Area bottomRect = new Area(new Rectangle(0,-450,450,450));
            Area topRect = new Area(new Rectangle(0,0,450,450));
            bottomRect.intersect(getTransformedShape());
            topRect.intersect(getTransformedShape());

            transform.translate(0, fruitShape.getBounds().getHeight());
            transform.rotate(Math.toRadians(angle));
            transform.setToIdentity();
            transform.translate(x, y+fruitShape.getBounds().getHeight());
            
            /*
            transform.translate(0, getTransformedShape().getBounds().getHeight());
            transform.translate(x-getTransformedShape().getBounds().getWidth()/2, y+getTransformedShape().getBounds().getHeight()/2);
            */
            bottomArea = bottomRect;
            bottomArea.transform(AffineTransform.getRotateInstance(Math.toRadians(angle)));
            topArea = topRect;
            topArea.transform(AffineTransform.getRotateInstance(Math.toRadians(angle)));
            //bottomArea.transform(AffineTransform.getTranslateInstance(-getTransformedShape().getBounds().getWidth(),-getTransformedShape().getBounds().));
        }
        else{
            if(angle < 15){
                transform.translate(0,-fruitShape.getBounds().getHeight()/2);
            }

            else if (angle >75){
                transform.translate(0,fruitShape.getBounds().getHeight()/2);
            }

        //fruitShape.transform(AffineTransform.getRotateInstance(-angle));
        transform.rotate(Math.toRadians(-angle));
        //transform.translate(-p1.getX(), -p1.getY());
        Area bottomRect = new Area(new Rectangle(0,0,450,450));
        Area topRect = new Area(new Rectangle(0,-450,450,450));
        bottomRect.intersect(getTransformedShape());
        topRect.intersect(getTransformedShape());
            /*
        if(((p1.getX() < p2.getX()) && (p1.getY() > p2.getY())) || ((p1.getX() > p2.getX()) && (p1.getY() < p2.getY()))){
            transform.scale(-1,1);
            transform.translate(getTransformedShape().getBounds().getWidth(),0)  
        */
        //    fruitShape.transform(AffineTransform.getRotateInstance(angle))
        transform.rotate(Math.toRadians(angle));
        transform.translate(x, y);
        //    fruitShape.transform(AffineTransform.getTranslateInstance(-p1.getY(),-p1.getX()));
        bottomArea = bottomRect;
        bottomArea.transform(AffineTransform.getRotateInstance(Math.toRadians(angle)));
        topArea = topRect;
        topArea.transform(AffineTransform.getRotateInstance(Math.toRadians(angle)));
        }
        // TODO END CS349
        if (topArea != null && bottomArea != null){
            //Fruit 1
            Fruit Fruit1 = new Fruit(topArea);
            Fruit1.setFalling(getFalling());
            Fruit1.setSlashed(true);
            Fruit1.setFillColor(getFillColor());
            Fruit1.setOutlineWidth(getOutlineWidth());
            Fruit1.setOutlineColor(getOutlineColor());
            //Fruit1.slashtype =  1 + (int)(Math.random() * ((2 - 1) + 1));
            Fruit1.slashtype = 1;

            //Fruit 2
            Fruit Fruit2 = new Fruit(bottomArea);
            Fruit2.setFalling(getFalling());
            Fruit2.setSlashed(true);
            Fruit2.setFillColor(getFillColor());
            Fruit2.setOutlineWidth(getOutlineWidth());
            Fruit2.setOutlineColor(getOutlineColor());
            Fruit2.slashtype = 2;

            int[] xSplatter = {0,5,5,10,10,   20,20,25,25,30,      30,25,25,20,20      ,10,10,5,5,0 };
            int[] ySplatter = {10,10,5,5,0,    0,5,5,10,10,        20,20,25,25,30      ,30,25,25,20,20 };
            Fruit Splatter1 = new Fruit(new Area(new Polygon(xSplatter,ySplatter,xSplatter.length)));
            Splatter1.setFillColor(getFillColor());
            Splatter1.setOutlineWidth(getOutlineWidth());
            Splatter1.setOutlineColor(getOutlineColor());
            Splatter1.translate(0 + (int)(Math.random() * ((50 - 0) + 1)),0 + (int)(Math.random() * ((50 - 0) + 1)));
            if(angle<0)
            Splatter1.translate(0,-50);
            Splatter1.fruitType = 2;

            int[] xSplatter2 = {10,15,15,20,20,   20,20,25,25,30,      30,25,25,20,20      ,20,20,15,15,10 };
            int[] ySplatter2 = {20,20,15,15,10,    10,15,15,20,20,        20,20,25,25,30      ,30,25,25,20,20 };
            Fruit Splatter2 = new Fruit(new Area(new Polygon(xSplatter2,ySplatter2,xSplatter2.length)));
            Splatter2.setFillColor(getFillColor());
            Splatter2.setOutlineWidth(getOutlineWidth());
            Splatter2.setOutlineColor(getOutlineColor());
            Splatter2.translate(0 + (int)(Math.random() * ((60 - 0) + 1)),0 + (int)(Math.random() * ((60 - 0) + 1)));
            if(angle<0)
                Splatter2.translate(0,-50);
            Splatter2.fruitType = 2;

            int[] xSplatter3 = {10,15,15,20,20,   20,20,25,25,30,      30,25,25,20,20      ,20,20,15,15,10 };
            int[] ySplatter3 = {20,20,15,15,10,    10,15,15,20,20,        20,20,25,25,30      ,30,25,25,20,20 };
            Fruit Splatter3 = new Fruit(new Area(new Polygon(xSplatter3,ySplatter3,xSplatter3.length)));
            Splatter3.setFillColor(getFillColor());
            Splatter3.setOutlineWidth(getOutlineWidth());
            Splatter3.setOutlineColor(getOutlineColor());
            Splatter3.translate(0 + (int)(Math.random() * ((60 - 0) + 1)),0 + (int)(Math.random() * ((60 - 0) + 1)));
            Splatter3.fruitType = 2;
            if(angle<0)
                Splatter3.translate(0,-50);
            Splatter3.setSlashed(true);

            Fruit Splatter4 = new Fruit(new Area(new Rectangle(0,0,5,5)));
            Splatter4.setFillColor(getFillColor());
            Splatter4.setOutlineWidth(getOutlineWidth());
            Splatter4.setOutlineColor(getOutlineColor());
            Splatter4.translate(0 + (int)(Math.random() * ((80 - 0) + 1)),0 + (int)(Math.random() * ((80 - 0) + 1)));
            Splatter4.fruitType = 2;
            Splatter4.setSlashed(true);
            if(angle<0)
                Splatter4.translate(0,-50);

            Fruit Splatter5 = new Fruit(new Area(new Rectangle(0,0,5,5)));
            Splatter5.setFillColor(getFillColor());
            Splatter5.setOutlineWidth(getOutlineWidth());
            Splatter5.setOutlineColor(getOutlineColor());
            Splatter5.translate(0 + (int)(Math.random() * ((80 - 0) + 1)),0 + (int)(Math.random() * ((80 - 0) + 1)));
            Splatter5.fruitType = 2;
            Splatter5.setSlashed(true);
            if(angle<0)
                Splatter5.translate(0,-50);

            Fruit Splatter6 = new Fruit(new Area(new Rectangle(0,0,5,5)));
            Splatter6.setFillColor(getFillColor());
            Splatter6.setOutlineWidth(getOutlineWidth());
            Splatter6.setOutlineColor(getOutlineColor());
            Splatter6.translate(0 + (int)(Math.random() * ((80 - 0) + 1)),0 + (int)(Math.random() * ((80 - 0) + 1)));
            Splatter6.fruitType = 2;
            Splatter6.setSlashed(true);
            if(angle<0)
                Splatter6.translate(0,-50);

            /*
            if(Fruit1.slashtype == 1){
                Fruit2.slashtype = 2;
            }
            else{
                Fruit2.slashtype =  1;
            }
            */
            Fruit[] myFruitArray = new Fruit[] { Fruit1, Fruit2, Splatter1,Splatter2,Splatter3,Splatter4,Splatter5,Splatter6 };


            return myFruitArray;
        }
        return new Fruit[0];
     }
}
