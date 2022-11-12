import java.awt.*;

public class Fenetre extends Frame{
    
    private int height;
    private int width;

    public Fenetre (int width, int height){
        this.height = height;
        this.width = width;

        setTitle("Jeu de la cible " + height + "x" + width);                     //nom de ma fenêtre
        setSize(width, height);                                                  //taille de ma fenêtre
        setVisible(true);                                                     //la fenêtre est visible
        setBackground(Color.BLUE);                                               //backgroung color = blue

        Cible cible = new Cible(width, height, 10);
        new Thread(cible).start();
        add(cible);
        addMouseListener(new EcouteSouris(cible));
    }
}
