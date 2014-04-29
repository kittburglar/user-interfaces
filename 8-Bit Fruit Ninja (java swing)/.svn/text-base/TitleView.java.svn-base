/**
 * CS349 Winter 2014
 * Assignment 3 Demo Code
 * Jeff Avery & Michael Terry
 */
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Area;
import java.awt.geom.Ellipse2D;

/*
 * View to display the Title, and Score
 * Score currently just increments every time we get an update
 * from the model (i.e. a new fruit is added).
 */
public class TitleView extends JPanel implements ModelListener {
  private Model model;
  private JLabel title, score, time, lives;
  private int count = 0;
  //Added
  private Timer t;
  private int fps = 40;
  private int timeElap = 0;
  private JLabel gameOver;
  //private int livesLeft = 5;
  // Constructor requires model reference
  TitleView (final Model model) {
    // register with model so that we get updates
    this.model = model;
    this.model.addObserver(this);
    //lol
    /*
    Fruit backgroundHead = new Fruit(new Area(new Ellipse2D.Double(100, 50, 250, 300)));
    backgroundHead.setFillColor(Color.pink);
    backgroundHead.fruitType = 2;
    model.add(backgroundHead);
    */
    // draw something
    //setBorder(BorderFactory.createLineBorder(Color.black));

    setBackground(Color.GRAY);
    //setOpaque(false);
    // You may want a better name for this game!

    title = new JLabel("8-Bit Fruit Ninja ");
    title.setFont(new Font("Times New Roman", Font.PLAIN, 20));
    score = new JLabel();
    score.setFont(new Font("Times New Roman", Font.PLAIN, 20));
    time = new JLabel();
    time.setFont(new Font("Times New Roman", Font.PLAIN, 20));
    lives = new JLabel();
    lives.setFont(new Font("Times New Roman", Font.PLAIN, 20));
    gameOver = new JLabel();
    // use border layout so that we can position labels on the left and right
    this.setLayout(new GridBagLayout());
    GridBagConstraints c = new GridBagConstraints();
    c.fill = GridBagConstraints.HORIZONTAL;
    c.weightx = 0.5;
    c.gridx = 0;
    c.gridy = 0;
    this.add(title, c);
    c.fill = GridBagConstraints.HORIZONTAL;
    c.weightx = 0.5;
    c.gridx = 0;
    c.gridy = 1;
    this.add(score, c);
      c.fill = GridBagConstraints.HORIZONTAL;
      c.weightx = 0.5;
      c.gridx = 2;
      c.gridy = 0;
      this.add(lives, c);
      c.fill = GridBagConstraints.HORIZONTAL;
      c.weightx = 0.5;
      c.gridx = 2;
      c.gridy = 1;
      this.add(time, c);
    //this.setLayout(new GridBagLayout());



      //this.add(gameOver, c);
      //gameOver.setText("GAME OVER");
      ActionListener rePainter = new ActionListener(){
          public void actionPerformed(ActionEvent e){

              if(model.getLivesLeft() == 0){
                //t.stop();
                timeElap = 0;
                model.getShapesReal().clear();
                Fruit endFruit = new Fruit(new Area(new Rectangle(95, 190, 300, 20)));
                endFruit.setFillColor(Color.GRAY);
                endFruit.setOutlineColor(Color.GRAY);
                endFruit.startFruit = true;
                model.add(endFruit);
                model.running = false;
              }



              //System.out.println("Hello");
              time.setText("Time: " + timeElap/3600 + ":" + timeElap/60 % 60 +"  ");
              timeElap++;
              for (Fruit s : model.getShapes()) {
                  //translate(getTransform().getTranslateX()-1,getTransform().getTranslateY());
                  //g2.draw(fruitShape);
                  s.timeOnScreen++;
                  if(s.fruitType == 0)
                  model.move(s,timeElap);
                  /*
                  if(s.getSlashed()){
                      System.out.println("fruit pieces hit the ground! we have misscount:" + model.getMissCount());
                      model.getShapesReal().remove(s);
                  }
                  */
                  if(s.fruitType == 2 && s.timeOnScreen == 200){
                      model.getShapesReal().remove(s);
                  }
                  if(s.getMissed()){
                      if(!s.getSlashed()){
                      model.intMissCount();
                      model.decLivesLeft();
                      //model.getShapesReal().remove(s);
                      }
                      model.getShapesReal().remove(s);
                  }

              }

          }
      };
      t = new Timer(1000/60, rePainter);
      t.start();
  }

  // Panel size
  @Override
  public Dimension getPreferredSize() {
    return new Dimension(500,40);
  }

  // Update from model
  // This is ONLY really useful for testing that the view notifications work
  // You likely want something more meaningful here.
  @Override
  public void update() {
    //count++;
    //paint(getGraphics());
    this.repaint();
  }

  // Paint method
  @Override
  public void paintComponent(Graphics g) {
    super.paintComponent(g);
    score.setText("Count: " + model.getCount() + "  ");
    lives.setText("Lives: " + model.getLivesLeft() + " ");
  }

  /*
  public GameTime(){
      super();
      ActionListener rePainter = new ActionListener() {
          public void actionPerformed(ActionEvent actionEvent) {
              System.out.println("Hello");
          }
      };
      t = new Timer(1000/fps, rePainter);
      t.start();
  }
  */
}
