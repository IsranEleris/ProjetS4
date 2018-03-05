package projets4;

public class Test {
	static ListeChainee plateau = new ListeChainee();

	public static int Cellule(int x, int y){    //renvoi l'état d'une cellule sous forme d'entier, 1 si elle est vivante, 0 si elle est morte
		if(plateau.contains(new Cellule(x,y))){
			return 1;
		}
		return 0;
	}
	public static boolean CelluleVivante(int x, int y){    //Méthode calculant l'état d'une cellule vivante à l'état n pour l'état n+1, renvoi vrai si elle reste en vie et faux si elle va mourir
		int c=0;
		c+=Cellule(x-1,y-1);
		c+=Cellule(x,y-1);
		c+=Cellule(x-1,y);
		c+=Cellule(x+1,y+1);
		c+=Cellule(x,y+1);
		c+=Cellule(x+1,y);
		c+=Cellule(x+1,y-1);
		c+=Cellule(x-1,y+1);
		if((c==2 || c==3)){
			return true;
		}else
			return false;
	}


	public static boolean CelluleMorte(int x, int y){  //Pour les cellules mortes comprises dans xMax/yMin et xMin/yMax on vérifie le nombre de voisins vivants, si il est égal à 3 la cellule né au temps n+1 sinon elle reste morte
		int c = 0;
		c+=Cellule(x-1,y-1);
		c+=Cellule(x+1,y+1);
		c+=Cellule(x+1,y-1);
		c+=Cellule(x-1,y+1);
		c+=Cellule(x,y+1);
		c+=Cellule(x+1,y);
		c+=Cellule(x-1,y);
		c+=Cellule(x,y-1);
		if(c==3){
			return true;
		}else
			return false;
	}

	public static void plateauPlusUn(){  //calcule l'étape n+1 du jeu de la vie
		ListeChainee plateauSuiv;
		plateauSuiv = plateau.clone();
		int xMin = plateau.getxMin();
		int xMax = plateau.getyMax();
		int yMin = plateau.getyMin();
		int yMax = plateau.getyMax();
		for(int y = yMax; y<=yMin; y--){ //Parcours de haut en bas pour la dimension  verticale
			for (int x = xMin; x<= xMax; x++){   // Parcours de gauche à droite pour la dimension horizontale
				if(Cellule(y,x)==1){     // Cas d'une cellule déjà vivante au temps n
					if(!CelluleVivante(y,x))    // Si la cellule a 2 ou 3 voisins au temps n elle reste en vie et on ne touche à rien, sinon on la supprime au temps n+1
						plateauSuiv.remove(new Cellule(y,x));
				}else{    //Cas d'une cellule non vivante au temps n
					if(CelluleMorte(y,x))   // Si la cellule comprends exactement 3 voisins au temps n, elle naît au temps n+1
						plateauSuiv.add(new Cellule(y,x));
				}
			}
		}
		plateau = plateauSuiv;
	}


	public static void main(String[] args) {

		while(true){
			plateauPlusUn();
			plateau.toString();
		}

	}

}

