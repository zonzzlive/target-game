public class Jeu {

    public static void main (String args[]){

        if(args.length != 2){                                                                                   //vérifie qu'il y ai bien 2 paramètres saisis
            throw new IllegalArgumentException("Il faut deux parametres");                                    //arrêtes le programme et affiche le message d'erreur
        }
        else {
            for(int i = 0; i<2; i++){                                                                           //vérifie que les paramètres sont bien des int 
                try{                                                                                            
                    Integer.parseInt(args[i]);                                                                  //essaye de traduire args[i] en int
                } catch(NumberFormatException ex) {                                                             //si args[i] ne peut être traduit en int
                    throw new IllegalArgumentException("Les parametres doivent etre des entiers");           //arrêtes le programme et affiche le message d'erreur
                }
            }
        }

        new Fenetre(Integer.parseInt(args[0]), Integer.parseInt(args[1]));                      //création de notre fenêtre

    }

}
