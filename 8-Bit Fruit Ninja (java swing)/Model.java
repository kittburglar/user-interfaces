/**
 * CS349 Winter 2014
 * Assignment 3 Demo Code
 * Jeff Avery & Michael Terry
 */
import java.util.ArrayList;
import java.util.Vector;

/*
 * Class the contains a list of fruit to display.
 * Follows MVC pattern, with methods to add observers,
 * and notify them when the fruit list changes.
 */
public class Model {
  // Observer list
  private Vector<ModelListener> views = new Vector();
  //private Vector<ModelListener> titleview = new Vector();
  // Fruit that we want to display
  private ArrayList<Fruit> shapes = new ArrayList();
  private int count;
  private int missCount;
  private int livesLeft = 5;
  public boolean running = true;
  // Constructor
  Model() {
    shapes.clear();

  }

  // MVC methods
  // These likely don't need to change, they're just an implementation of the
  // basic MVC methods to bind view and model together.
  public void addObserver(ModelListener view) {
    views.add(view);
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
      livesLeft--;
  }

  public void setLivesLeft(int lives){
      livesLeft = lives;
  }

    public void notifyObservers() {
    for (ModelListener v : views) {
      v.update();
    }
  }

  // Model methods
  // You may need to add more methods here, depending on required functionality.
  // For instance, this sample makes to effort to discard fruit from the list.
  public void add(Fruit s) {
    shapes.add(s);
    notifyObservers();
  }
  public void move(Fruit s, int timeElap){
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

        if(-21 + s.timeOnScreen/2 == 0){
            s.setFalling(true);
        }
        if((s.getFalling() && s.getTransformedShape().getBounds().getY() >= 445) ){
            s.setMissed(true);
        }

        else{
            if(s.getSlashed()){
                if(s.slashtype == 1)
                    s.translate(5, s.timeOnScreen/2);
                else if(s.slashtype == 2)
                    s.translate(-5, s.timeOnScreen/2);
                //s.rotate(0 + (int)(Math.random() * ((360 - 0) + 1)));
            }
            else{
                if(s.spawntype == 1)
                    s.translate(1,-11.5 + s.timeOnScreen/6);

                else
                    s.translate(-1,-11.5 + s.timeOnScreen/6);
            }

        }
    //yInit + v*t - 4.9t^2

    notifyObservers();
  }

  public ArrayList<Fruit> getShapesReal() {
      return (ArrayList<Fruit>)shapes;
  }

  public ArrayList<Fruit> getShapes() {
      return (ArrayList<Fruit>)shapes.clone();
  }


}
