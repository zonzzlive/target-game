import java.awt.*;
import java.util.Random;

import java.awt.Graphics;

public class Cible extends Component implements Runnable{
    
    int r;
    int maxX;
    int maxY;
    
    int x;
    int y;

    public Cible(int maxX, int maxY, int r){

        this.maxX = maxX;
        this.maxY = maxY;
        this.r = r;

        setVisible(true);
    }

    public void init(){

    }
    
    @Override
    public void paint(Graphics gc)
    {
        int i = 0;

        x = randomNumber(maxX, r*10);
        y = randomNumber(maxY, r*10);
        while(i<10){
            Graphics2D g2 = (Graphics2D) gc;
            int rTemp = r * (10 - i);

            if(i < 2){
                g2.setColor(Color.white);
            } else if (i < 4) {
                g2.setColor(Color.black);
            } else if (i < 6){
                g2.setColor(Color.blue);
            } else if (i < 8){
                g2.setColor(Color.red);
            } else if (i < 10){
                g2.setColor(Color.yellow);
            }

            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);                     //permet d'avoir un rond en activant l'anti antialiasing
            g2.fillOval(x - rTemp, y - rTemp, rTemp*2, rTemp*2);
            g2.setColor(Color.black);
            g2.drawOval(x - rTemp, y - rTemp, rTemp*2, rTemp*2);
            i++;

        }
    }

    public void computeScore(int xClick, int yClick){
        int xDist = ((xClick - 8) - x)*((xClick - 8) - x);
        int yDist = ((yClick - 31) - y)*((yClick - 31) - y);

        double distClick = xDist + yDist;
        double point = distClick / (r*r);

        int pointFinal = Math.min(Math.max((int)(10 - Math.sqrt(point)) + 1, 0), 10);

        if(pointFinal > 0){
            System.out.println("Vous avez touche la cible, vous avez gagne " + pointFinal + " points");
        }


    }

    public int randomNumber(int upperbound, int maxRayon){                                                      //génére un nombre aléatoire selon le upperbound envoyé
        Random rand = new Random();
        int posMax = (int)(upperbound - (2.5*maxRayon));
        return rand.nextInt(posMax) + maxRayon;      
    }

    @Override
    public void run(){
        int i = 1500;
        while(true){
            try {
                Thread.sleep(i);                                                                         //met en pause le thread pour 1.5 secondes
                repaint();
            } catch(InterruptedException e) {
                System.out.println("got interrupted!");
            }
            if(i>500){
                i -= 20;
            }
        }
    }

}
