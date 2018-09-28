
import javax.swing.JPanel;
import javax.swing.JFrame;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Dimension;

public class Bouncing extends JPanel{
	public static Sphere[] sphereArr = new Sphere[10];
	
    public static final int WIDTH = 1024;
    public static final int HEIGHT = 768;
    public static final int FPS = 60;
    public static final int RADIUS = 50;
    
//    double positionX;
//    double positionY;

    //Note: The following are not used yet, you should use them in writing your code.
//    double velocityX;
//    double velocityY;

    double accelerationX;
    double accelerationY;
   
    class Runner implements Runnable{
    	
        public Runner()
        {
        	WIDTH = 450;
            //Feel free to change these default values
//            positionX = 275;
//            positionY = HEIGHT - 275;
//            velocityX = 100;
//            velocityY = -100;
              accelerationY = 98;
            
            //your code here for adding the second sphere
            Bouncing.sphereArr[0] = new Sphere(275, HEIGHT - 275, 100, -100);
            Bouncing.sphereArr[1] = new Sphere(300, HEIGHT - 400, 200, -200);
            Bouncing.sphereArr[2] = new Sphere(475, HEIGHT - 575, 400, -300);
            Bouncing.sphereArr[3] = new Sphere(100, HEIGHT - 275, 100, -100);
            Bouncing.sphereArr[4] = new Sphere(370, HEIGHT - 400, 200, -200);
            Bouncing.sphereArr[5] = new Sphere(700, HEIGHT - 575, 400, -300);
            Bouncing.sphereArr[6] = new Sphere(802, HEIGHT - 275, 100, -100);
            Bouncing.sphereArr[7] = new Sphere(650, HEIGHT - 400, 200, -200);
            Bouncing.sphereArr[8] = new Sphere(235, HEIGHT - 575, 400, -300);
            Bouncing.sphereArr[9] = new Sphere(914, HEIGHT - 575, 400, -300);
        }
        public void run()
        {
            while(true){
            	for(int sphereOrder = 0; sphereOrder < sphereArr.length; sphereOrder++) 
            	{
            		//your code here            	
                    //Implement Movement  here
                    
                    //(Use velocityX and velocityY rather than fixed constants)
                	sphereArr[sphereOrder].positionX += sphereArr[sphereOrder].velocityX / (double)FPS; //delete this line
                	sphereArr[sphereOrder].positionY += sphereArr[sphereOrder].velocityY / (double)FPS; //delete this
                    
                    //Implement bouncing here
                    	//the conditions are slightly changed so that the ball does not hide from the screen
                	if(sphereArr[sphereOrder].positionY >= HEIGHT - RADIUS*2.5 || sphereArr[sphereOrder].positionY <= 0) {
                		sphereArr[sphereOrder].velocityY *= -1;
                	}
                	if(sphereArr[sphereOrder].positionX <= 0 || sphereArr[sphereOrder].positionX >= WIDTH - RADIUS) {
                		sphereArr[sphereOrder].velocityX *= -1;
                	}
                    //Implement gravity here (Bonus)
                	sphereArr[sphereOrder].velocityY = sphereArr[sphereOrder].velocityY + (accelerationY*(1.0 / FPS)); // V(final) = V0 + at
            	}
                
                //don't mess too much with the rest of this method
                repaint();
                try{
                    Thread.sleep(1000/FPS);
                }
                catch(InterruptedException e){}
            }
        }    
    }
    
    public Bouncing(){
        this.setPreferredSize(new Dimension(WIDTH, HEIGHT));

        Thread mainThread = new Thread(new Runner());
        mainThread.start();
    }
    public static void main(String[] args){
        JFrame frame = new JFrame("Physics!!!");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Bouncing world = new Bouncing();
        frame.setContentPane(world);
        frame.pack();
        frame.setVisible(true);
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);            

        g.setColor(Color.BLACK);
        g.fillRect(0, 0, WIDTH, HEIGHT);

        //The cannon you see is actually *not* a photograph of a real cannon.
        //It's drawn by the following. 
        g.setColor(Color.ORANGE);
        int xpts[] = {75, 275, 275, 350, 325, 150};
        int ypts[] = {HEIGHT-50, HEIGHT-250, HEIGHT-275, HEIGHT- 175, HEIGHT-175, HEIGHT-25};
        g.fillPolygon(xpts, ypts, 6);
                
        g.setColor(Color.BLUE);
        g.fillOval(150, HEIGHT-200, 200, 200);

        //this is where the sphere is drawn. As a bonus make it draw something else
        // (e.g., your object from the previous lab).
        
        //your code here for drawing the other spheres
        for(int sphereOrder = 0; sphereOrder < sphereArr.length; sphereOrder++) {
        	g.setColor(Color.WHITE);
            g.drawOval((int)sphereArr[sphereOrder].positionX, (int) sphereArr[sphereOrder].positionY,  RADIUS,  RADIUS);
        }
    }    
}
class Sphere 
{
	public double positionX;
	public double positionY;
	public double velocityX;
	public double velocityY;
	public Sphere(double _posX, double _posY, double _velX, double _velY) 
	{
		this.positionX = _posX;
		this.positionY = _posY;
		this.velocityX = _velX;
		this.velocityX = _velY;
	}
}
