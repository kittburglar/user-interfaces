/**
 * CS349 Winter 2014
 * Assignment 3 Demo Code
 * Jeff Avery & Michael Terry
 */
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/*
 * View of the main play area.
 * Displays pieces of fruit, and allows players to slice them.
 */
public class View extends JPanel implements ModelListener {
    private Model model;
    private final MouseDrag drag;

    private Timer t;
    private int timeElapsed = 0;
    private JLabel gameOver;

    // Constructor
    View (Model m) {
        model = m;
        model.addObserver(this);
        int fps = 1;
        ImageIcon image = new ImageIcon("start.png");
        gameOver = new JLabel(image);
        //this.add(gameOver);
        this.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.HORIZONTAL;
        c.ipady = 40;      //make this component tall
        c.weightx = 0.0;
        c.gridwidth = 3;
        c.gridx = 0;
        c.gridy = 1;

        //gameOver.setText("GAMEOVER!");
        gameOver.setVisible(false);
        this.add(gameOver);
        setBackground(Color.GRAY);
        //setOpaque(false);
        // add a couple of fruit instances for test purposes
        // in a real game, you want to spawn fruit in random locations from the bottom of the screen
        // we use ellipse2D for simple shapes, you might consider something more complex

        ActionListener rePainter = new ActionListener(){
            //int timeElapsed = 0;
            int[] x = {0,5,5,10,10,   40,40,45,45,50,      50,45,45,40,40      ,10,10,5,5,0 };
            int[] y = {10,10,5,5,0,    0,5,5,10,10,        40,40,45,45,50      ,50,45,45,40,40 };
            int[] xApple = {0,5,5,10,10,   35,35,40,40,45,      45,40,40,35,35      ,10,10,5,5,0 };
            int[] yApple = {10,10,5,5,0,    0,5,5,10,10,        35,35,40,40,45      ,45,40,40,35,35 };
            int[] xWatermelon = {0,5,5,10,10,   60,60,65,65,70,      70,65,65,60,60      ,10,10,5,5,0 };
            int[] yWatermelon = {10,10,5,5,0,    0,5,5,10,10,        50,50,55,55,60      ,60,55,55,50,50 };
            int[] xPineapple = {0,5,5,10,10,   30,30,35,35,40,      40,35,35,30,30      ,10,10,5,5,0 };
            int[] yPineapple = {10,10,5,5,0,    0,5,5,10,10,        50,50,55,55,60      ,60,55,55,50,50 };
            int[] xPeach = {0,5,5,10,10,   30,30,35,35,40,      40,35,35,30,30      ,10,10,5,5,0 };
            int[] yPeach = {10,10,5,5,0,    0,5,5,10,10,        30,30,35,35,40      ,40,35,35,30,30 };
            //final Fruit f = new Fruit(new Area(new Ellipse2D.Double(0, 0, 50, 50)));
            Shape apple = new Ellipse2D.Double(0, 0, 50, 50);
            public void actionPerformed(ActionEvent e){
                //timeElapsed++;
                //int[] x = {0,50,50,0};
                //int[] y = {0,0,50,50};
                if(model.running == false){
                    //System.out.println("BOOM");
                    gameOver.setVisible(true);
                }
                final Fruit f;
                int fruitNumber = 0 + (int)(Math.random() * ((4 - 0) + 1));
                if(fruitNumber == 0){
                    f = new Fruit(new Area(new Polygon(xApple,yApple,xApple.length)));
                    f.setFillColor(Color.RED);
                    f.setOutlineColor(Color.RED.darker());
                    f.setOutlineWidth(2);
                    f.spawntype = 1 + (int)(Math.random() * ((2 - 1) + 1));
                    if(f.spawntype == 1)
                        f.translate(0 + (int)(Math.random() * ((100 - 0) + 1)), 445);
                    else
                        f.translate(300 + (int)(Math.random() * ((400 - 300) + 1)),445);
                    //f.timeCreated = timeElapsed;
                    model.add(f);
                }

                else if(fruitNumber == 1){
                    Fruit f2 = new Fruit(new Area(new Polygon(x,y,x.length)));
                    f2.setFillColor(Color.ORANGE);
                    f2.setOutlineColor(Color.ORANGE.darker());
                    f2.setOutlineWidth(2);
                    f2.spawntype = 1 + (int)(Math.random() * ((2 - 1) + 1));
                    if(f2.spawntype == 1)
                        f2.translate(0 + (int)(Math.random() * ((100 - 0) + 1)), 445);
                    else
                        f2.translate(300 + (int)(Math.random() * ((400 - 300) + 1)),445);
                    //f.timeCreated = timeElapsed;
                    model.add(f2);
                }
                else if(fruitNumber == 2){
                    Fruit f3 = new Fruit(new Area(new Polygon(xWatermelon,yWatermelon,xWatermelon.length)));
                    f3.setFillColor(Color.GREEN);
                    f3.setOutlineColor(Color.GREEN.darker());
                    f3.setOutlineWidth(2);
                    f3.spawntype = 1 + (int)(Math.random() * ((2 - 1) + 1));
                    if(f3.spawntype == 1)
                        f3.translate(0 + (int)(Math.random() * ((100 - 0) + 1)), 445);
                    else
                        f3.translate(300 + (int)(Math.random() * ((400 - 300) + 1)),445);
                    //f.timeCreated = timeElapsed;
                    model.add(f3);
                }
                else if(fruitNumber == 3){
                    Fruit f4 = new Fruit(new Area(new Polygon(xPineapple,yPineapple,xPineapple.length)));
                    f4.setFillColor(Color.YELLOW);
                    f4.setOutlineColor(Color.YELLOW.darker());
                    f4.setOutlineWidth(2);
                    f4.spawntype = 1 + (int)(Math.random() * ((2 - 1) + 1));
                    if(f4.spawntype == 1)
                        f4.translate(0 + (int)(Math.random() * ((100 - 0) + 1)), 445);
                    else
                        f4.translate(300 + (int)(Math.random() * ((400 - 300) + 1)),445);
                    //f.timeCreated = timeElapsed;
                    model.add(f4);
                }
                else if(fruitNumber == 4){
                    Fruit f5 = new Fruit(new Area(new Polygon(xPeach,yPeach,xPeach.length)));
                    f5.setFillColor(Color.PINK);
                    f5.setOutlineColor(Color.PINK.darker());
                    f5.setOutlineWidth(2);
                    f5.spawntype = 1 + (int)(Math.random() * ((2 - 1) + 1));
                    if(f5.spawntype == 1)
                        f5.translate(0 + (int)(Math.random() * ((150 - 0) + 1)), 445);
                    else
                        f5.translate(250 + (int)(Math.random() * ((400 - 250) + 1)),445);
                    //f.timeCreated = timeElapsed;
                    model.add(f5);
                }
                /*
                else if(f == 9000){
                    f = new Fruit(new Area(new Polygon(x,y,x.length)));
                    f.setFillColor(Color.RED);
                    f.setOutlineColor(Color.RED.darker());
                    f.setOutlineWidth(2);
                    f.translate(200, 200);
                    model.add(f);
                }
                */
            }
        };
        t = new Timer(1000, rePainter);
        t.start();

        /*
        Fruit f2 = new Fruit(new Area(new Rectangle2D.Double(0, 0, 100, 100)));
        f2.translate(200, 100);
        //f2.rotate(30);
        model.add(f2);


        Fruit f3 = new Fruit(new Area(new Rectangle2D.Double(0, 0, 100, 100)));
        //f3.translate(0,0);

        f3.translate(200, 200);
        model.add(f3);

        ActionListener rePainter = new ActionListener(){
            public void actionPerformed(ActionEvent e){
                for (Fruit s : model.getShapes()) {
                    //translate(getTransform().getTranslateX()-1,getTransform().getTranslateY());
                    //g2.draw(fruitShape);
                    model.move(s);
                }
            }
        };

        Timer t = f.getTimer();
        t = new Timer(1000/fps, rePainter);
        t.start();
        */
        // drag represents the last drag performed, which we will need to calculate the angle of the slice
        drag = new MouseDrag();
        // add mouse listener
        addMouseListener(mouseListener);

    }

    // Update fired from model
    @Override
    public void update() {
        this.repaint();
    }

    // Panel size
    @Override
    public Dimension getPreferredSize() {
        return new Dimension(500,400);
    }

    // Paint this panel
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;

        // draw all pieces of fruit
        // note that fruit is responsible for figuring out where and how to draw itself
        for (Fruit s : model.getShapes()) {
            s.draw(g2);
            //System.out.println("s.draw(g2)");
        }
    }

    // Mouse handler
    // This does most of the work: capturing mouse movement, and determining if we intersect a shape
    // Fruit is responsible for determining if it's been sliced and drawing itself, but we still
    // need to figure out what fruit we've intersected.
    private MouseAdapter mouseListener = new MouseAdapter() {
        @Override
        public void mousePressed(MouseEvent e) {
            super.mousePressed(e);
            drag.start(e.getPoint());
        }

        @Override
        public void mouseReleased(MouseEvent e) {

            super.mouseReleased(e);
            drag.stop(e.getPoint());

            // you could do something like this to draw a line for testing
            // not a perfect implementation, but works for 99% of the angles drawn

            int[] x = { (int) drag.getStart().getX(), (int) drag.getEnd().getX(), (int) drag.getEnd().getX(), (int) drag.getStart().getX()};
            int[] y = { (int) drag.getStart().getY()-1, (int) drag.getEnd().getY()-1, (int) drag.getEnd().getY()+1, (int) drag.getStart().getY()+1};

            Fruit slash = new Fruit(new Area(new Polygon(x, y, x.length)));
            slash.fruitType = 1;
            //model.add(slash);

            // find intersected shapes
            int offset = 0; // Used to offset new fruits
            //System.out.println("hello");
            for (Fruit s : model.getShapes()) {
                if (s.intersects(drag.getStart(), drag.getEnd()) && s.fruitType == 0) {
                    gameOver.setVisible(false);
                    if(s.startFruit){
                        //System.out.println("End Fruit!!");

                        model.getShapesReal().clear();
                        model.setLivesLeft(5);
                        timeElapsed = 0;
                        model.setCount(0);
                        gameOver.setVisible(false);
                        model.running = true;
                        //gameOver.setText("gameOver");

                    }
                    int[] x2 = { (int) drag.getStart().getX() + (int) drag.getEnd().getX()/2, (int) drag.getEnd().getX(), (int) drag.getEnd().getX(), (int) drag.getStart().getX()+(int) drag.getEnd().getX()/2};
                    int[] y2 = { (int) drag.getStart().getY() +(int) drag.getEnd().getY()/2-1, (int) drag.getEnd().getY()-1, (int) drag.getEnd().getY()+1, (int) drag.getStart().getY()+(int) drag.getEnd().getY()/2+1};

                    Fruit splatter = new Fruit(new Area(new Polygon(x2, y2, x2.length)));
                    splatter.setOutlineColor(s.getFillColor());
                    splatter.fruitType = 2;
                    //model.add(splatter);
                    //s.setFillColor(Color.RED);
                    //update();
                    //REMOVE THIS FRUIT (s)
                    model.getShapesReal().remove(s);
                    if(!s.getSlashed() && !s.startFruit){
                        model.incCount();
                    }
                    //System.out.println("we sliced a fruit!");
                    try {
                        Fruit[] newFruits = s.split(drag.getStart(), drag.getEnd());

                        // add offset so we can see them split - this is used for demo purposes only!
                        // you should change so that new pieces appear close to the same position as the original piece
                        for (Fruit f : newFruits) {
                            //f.translate(offset, offset);
                            //offset += 20;
                            f.translate(s.getTransform().getTranslateX(),s.getTransform().getTranslateY());
                            model.add(f);


                        }
                    } catch (Exception ex) {
                        System.err.println("Caught error: " + ex.getMessage());
                    }
                } else {
                    //System.out.println("we missed a fruit!");
                    //s.setFillColor(Color.BLUE);
                }
            }
        }
    };

    /*
     * Track starting and ending positions for the drag operation
     * Needed to calculate angle of the slice
     */
    private class MouseDrag {
        private Point2D start;
        private Point2D end;

        MouseDrag() { }

        protected void start(Point2D start) { this.start = start; }
        protected void stop(Point2D end) { this.end = end; }

        protected Point2D getStart() { return start; }
        protected Point2D getEnd() { return end; }

    }
}
