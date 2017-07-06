package fr.enseirb.battleship;

public class Axe {
	int taille;
	String titre;
	
		
		// Constructeur 
		Axe() {
			this.taille = 0;
		}
		
		Axe(int taille) {
			this.taille = taille;
		}
		
		// Put the title
		Axe(String titre, int taille) {
			this.titre = titre;
			this.taille = taille;
		}
		
		// Get the taille
		int getTaille() {
			return this.taille;
		}
	
		// set the taille of grid
		void setTaille(int taille) {
			this.taille = taille;
		}
		
	    public String svg() {
	        return String.format("<line x1='20' y1='80' x2='10' y2='1200' />\n");
	    }


		
		String description() {
			return "Axe taille : " + this.taille + ", " + description();
		}

}
