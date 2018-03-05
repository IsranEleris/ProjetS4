package jeuDeLaVieTest;

public class Test {
	static ListeChainee plateau = new ListeChainee();

	public static int Cellule(int x, int y){    //renvoi l'état d'une cellule sous forme d'entier, 1 si elle est vivante, 0 si elle est morte
		if(plateau.contains(new Cellule(x,y))){
			return 1;
		}
		return 0;
	}
	public static int Voisins(int x, int y){    //Méthode calculant le nombre de vosins vivant d'une cellule morte ou vivante et retourne ce nombre.
		int c=0;
		c+=Cellule(x-1,y-1);
		c+=Cellule(x,y-1);
		c+=Cellule(x-1,y);
		c+=Cellule(x+1,y+1);
		c+=Cellule(x,y+1);
		c+=Cellule(x+1,y);
		c+=Cellule(x+1,y-1);
		c+=Cellule(x-1,y+1);
	    return c;
	}


	public static ListeChainee Voisins(int x, int y){    // Stocke les cellules mortes dans une liste chainee
	    ListeChainee voisins = new ListeChainee();
	    if(!(plateau.contains(x-1,y))) voisins.put(new Cellule(x-1,y));
        if(!(plateau.contains(x+1,y))) voisins.put(new Cellule(x+1,y));
        if(!(plateau.contains(x,y-1))) voisins.put(new Cellule(x,y-1));
        if(!(plateau.contains(x,y+1))) voisins.put(new Cellule(x,y+1));
        if(!(plateau.contains(x-1,y-1))) voisins.put(new Cellule(x-1,y-1));
        if(!(plateau.contains(x-1,y+1))) voisins.put(new Cellule(x-1,y+1));
        if(!(plateau.contains(x+1,y-1))) voisins.put(new Cellule(x+1,y-1));
        if(!(plateau.contains(x+1,y-1))) voisins.put(new Cellule(x+1,y-1));
        return voisins;
    }



	public static void plateauPlusUn() {  //calcule l'étape n+1 du jeu de la vie
		ListeChainee plateauSuiv;
		plateauSuiv = plateau.clone();
		ListeChainee NouvelleCellules = new ListeChainee();    //On stocke toutes les nouvelles cellules mortes à vivantes pour ne pas les "ajouter deux fois".
		Maillon i = plateau.getTete();
		while (i.getSuivant != null) {
			int x = i.getElement().getLigne();
			int y = i.getElement().getColonne();
			int voisins = Voisins(x, y);
			if (!(voisins == 3 || voisins == 2)) {
				plateauSuiv.remove(new Cellule(x, y));
			}
			ListeChainee tmp = Voisins(x, y);
			Maillon j = tmp.getTete();
			while (j.suivant != null) {
				int x1 = j.getElement().getLigne();
				int y1 = j.getElement().getColonne();
				if (Voisins(x1, y1) == 3 && !(NouvelleCellules.contains(new Maillon(new Cellule(x1,y1)),null))){
					plateauSuiv.put(x1, y1);
					NouvelleCellules.insert((new Maillon(new Cellule(x1, x2)), null), NouvelleCellules);
				}
			}
		}
		plateau = plateauSuiv;
	}


	public static void main(String[] args) {
		int c = 20;
		while(c<20) {
			plateauPlusUn();
			plateau.toString();
			c++;
		}
	}

}

