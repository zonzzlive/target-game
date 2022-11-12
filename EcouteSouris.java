import java.awt.event.*;

public class EcouteSouris implements MouseListener {

    Cible cible;

    public EcouteSouris(Cible cible){
        this.cible = cible;
    }

    public void mouseClicked(MouseEvent e) {
        cible.computeScore(e.getX(), e.getY());
    }
    public void mouseEntered(MouseEvent e) {
        //System.out.println("[souris] enter " + pos(e));
    }
    public void mouseExited(MouseEvent e) {
        //System.out.println("[souris] exit " + pos(e));
    }
    public void mousePressed(MouseEvent e) {
        //System.out.println("[souris] pressed " + pos(e));
    }
    public void mouseReleased(MouseEvent e) {
        //System.out.println("[souris] released " + pos(e));
    }

    public String pos(MouseEvent e){
        return ("(" + e.getX() + "," + e.getY() + ")");
    }

}
