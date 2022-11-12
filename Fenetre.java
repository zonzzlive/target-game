import java.awt.*;

public class Fenetre extends Frame{
    
    public Fenetre (int width, int height){

        setTitle("Jeu de la cible " + height + "x" + width);                     //nom de ma fenêtre
        setSize(width, height);                                                  //taille de ma fenêtre
        setVisible(true);                                                     //la fenêtre est visible
        setBackground(Color.BLUE);                                               //backgroung color = blue

        Cible cible = new Cible(width, height, 10);                             //créer un objet cible
        new Thread(cible).start();
        add(cible);
        addMouseListener(new EcouteSouris(cible));
    }
}
