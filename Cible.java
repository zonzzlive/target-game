import java.awt.*;
import java.util.Random;

import java.awt.Graphics;

public class Cible extends Component implements Runnable{
    
    int r;                                                                                                           //rayon de la cible
    int maxX;                                                                                                        //dimension x de la fenêtre
    int maxY;                                                                                                        //dimension y de la fenêtre
    
    int x;                                                                                                           //coordonnées x de la cible
    int y;                                                                                                           //coordonnées y de la cible

    public Cible(int maxX, int maxY, int r){

        this.maxX = maxX;
        this.maxY = maxY;
        this.r = r;

        setVisible(true);                                                                                         //déclare la cible comme étant visible
    }
    
    @Override
    public void paint(Graphics gc)                                                                                  //fonction en charge de "dessiner" la cible
    {
        int i = 0;

        x = randomNumber(maxX, r*10);                                                                               //génére une coordonnées x aléatoire
        y = randomNumber(maxY, r*10);                                                                               //génére une coordonnées y aléatoire

        while(i<10){
            Graphics2D g2 = (Graphics2D) gc;
            int rTemp = r * (10 - i);                                                                               //diminue au fur et à mesure le rayon des cercles

            if(i < 2){                                                                                              //vérifie le positionnement du cercle et lui assigne la bonne couleur
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

            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);                 //permet d'avoir un rond en activant l'anti antialiasing
            g2.fillOval(x - rTemp, y - rTemp, rTemp*2, rTemp*2);                                                    //créer le rond coloré selon la couleur obtenu dans les if ci-dessus
            g2.setColor(Color.black);                                                                               //déclare la couleur du prochain rond à noir 
            g2.drawOval(x - rTemp, y - rTemp, rTemp*2, rTemp*2);                                                    //créer un rond qui n'a que des bordures
            i++;

        }
    }

    public void computeScore(int xClick, int yClick){                                                               //vérifie si le click est dans la cible et si il l'est cherche le nombre de points gagnés
        int xDist = ((xClick - 8) - x)*((xClick - 8) - x);                                                          //calcule la distance, sur l'axe x, du clic par rapport au centre, "-8" est nécesaire à cause d'un décalage sur la fenêtre
        int yDist = ((yClick - 31) - y)*((yClick - 31) - y);                                                        //calcule la distance, sur l'axe y, du clic par rapport au centre, "-31" est nécessaire à cause d'un décalage sur la fenêtre

        double distClick = xDist + yDist;
        double point = distClick / (r*r);

        int pointFinal = Math.min(Math.max((int)(10 - Math.sqrt(point)) + 1, 0), 10);                         //ramène les points entre 0 et 10, on ne peut dépasser 10, 11 accessible si on clic au centre exact de la cible, 
                                                                                                                   //on ne peut aller en dessous de 0, si on clic en dehors de la cible nous sommes en négatif

        if(pointFinal > 0){
            System.out.println("Vous avez touche la cible, vous avez gagne " + pointFinal + " points");
        }


    }

    public int randomNumber(int upperbound, int maxRayon){                                                      //génére un nombre aléatoire selon le upperbound et le maxRayon envoyé
        Random rand = new Random();
        int posMax = (int)(upperbound - (2.5*maxRayon));                                                        //le 2.5*maxRayon est obligatoire pour éviter une sortie de la fenêtre à cause d'un décalage dans le coin haut gauche ou nous ne sommes pas à x=0, y=0 mais à x=8, y=31
        return rand.nextInt(posMax) + maxRayon;                                                        
    }

    @Override
    public void run(){
        int i = 1500;
        while(true){
            try {
                Thread.sleep(i);                                                                                //met en pause le thread² pour i millième de secondes
                repaint();
            } catch(InterruptedException e) {
                System.out.println("got interrupted!");
            }
            if(i>500){                                                                                          //bloque la vitesse à 500 millièmes de secondes pour éviter que la cible n'apparaisse et ne disparaisse pas trop rapidement
                i -= 20;
            }
        }
    }

}
