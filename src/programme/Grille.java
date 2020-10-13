package programme;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class Grille {
	
	public int nbLignes, nbColonnes;
	public String[][] grille;
	
	public Grille(File f) throws IOException {
		int [] dimensions = calculerTailleGrille(f);
		this.grille = new String[dimensions[0]][dimensions[1]];
		BufferedReader br = new BufferedReader(new FileReader(f));
		String ligne;
		int i = 0;
		while ((ligne = br.readLine()) != null){
			for (int j = 0; j < ligne.length(); j++) {
				grille[i][j] = Character.toString(ligne.charAt(j));
			}
			i++;
		}
		br.close();
		this.nbColonnes = this.grille[0].length;
		this.nbLignes = this.grille.length;
	}
	
	public Grille(String[][] tab) {
		this.grille = tab;
		this.nbColonnes = this.grille[0].length;
		this.nbLignes = this.grille.length;
	}
	
	public Grille() throws IOException {
		int [] dimensions = calculerTailleGrille(null);
		this.grille = new String[dimensions[0]][dimensions[1]];
		BufferedReader br = new BufferedReader(new FileReader("src/grilles/tablier1.txt"));
		String ligne;
		int i = 0;
		while ((ligne = br.readLine()) != null){
			for (int j = 0; j < ligne.length(); j++) {
				grille[i][j] = Character.toString(ligne.charAt(j));
			}
			i++;
		}
		br.close();
		this.nbColonnes = this.grille[0].length;
		this.nbLignes = this.grille.length;
	}
	
	public int[] calculerTailleGrille(File f) throws IOException {
		int[] tab = new int[2];
		if (f == null) {
			BufferedReader br = new BufferedReader(new FileReader("src/grilles/tablier1.txt"));
			String ligne;
			int i = 0;
			int j = 0;
			while ((ligne = br.readLine()) != null){
				if (i == 0)
					j = ligne.length();
				i++;
			}
			br.close();
			
			tab[0] = i;
			tab[1] = j;
		}
		
		else {
			BufferedReader br = new BufferedReader(new FileReader(f));
			String ligne;
			int i = 0;
			int j = 0;
			while ((ligne = br.readLine()) != null){
				if (i == 0)
					j = ligne.length();
				i++;
			}
			br.close();
			
			tab[0] = i;
			tab[1] = j;
		}
		
		
		return tab;
	}
	
	public int calculerNbBilles(){
		int somme = 0;
		for (int i = 0; i < nbLignes; i++) {
			//System.out.println("ligne :" + i);
			for (int j = 0; j < nbColonnes; j++) {
				//System.out.println("colonne :" + j);
				if(grille[i][j].equals("o"))
					somme++;
			}
		}
		return somme;
	}
	
	public boolean deplacementValide(String deplacement, int i, int j) {
		if (deplacement.equals("gauche") && (j+2) < nbColonnes) {
			if(grille[i][j+1].equals("o") && grille[i][j+2].equals("o")) {
				return true;
			}
		}
		
		if(deplacement.equals("droite") && (j-2)>=0) {
			if(grille[i][j-1].equals("o") && grille[i][j-2].equals("o")) {
				return true;
			}
		}
		
		if (deplacement.equals("bas") && (i-2)>= 0) {
			if(grille[i-2][j].equals("o") && grille[i-1][j].equals("o")) {
				return true;
			}
		}
		
		if (deplacement.equals("haut") && (i + 2) < nbLignes) {
			if(grille[i+1][j].equals("o") && grille[i+2][j].equals("o")) {
				return true;
			}
		}
		return false;

	}
	
	public void deplacerBille(String deplacement, int i, int j){
		if (deplacement.equals("gauche")) {
				
			grille[i][j] = "o";
			grille[i][j+1] = ".";
			grille[i][j+2] = ".";
			
		}
		if(deplacement.equals("droite")) {
			grille[i][j-2] = ".";
			grille[i][j-1] = ".";
			grille[i][j] = "o";
		}
		
		if (deplacement.equals("bas")) {
			grille[i-2][j] = ".";
			grille[i-1][j] = ".";
			grille[i][j] = "o";
		}
			
		if (deplacement.equals("haut")) {
			grille[i][j] = "o";
			grille[i+1][j] = ".";
			grille[i+2][j] = ".";
		}
	}
	public void retourArriere(String deplacement, int i, int j) {
		if (deplacement.equals("gauche")) {
				
			grille[i][j] = ".";
			grille[i][j+1] = "o";
			grille[i][j+2] = "o";
		}
		if(deplacement.equals("droite") ){
			
			grille[i][j-2] = "o";
			grille[i][j-1] = "o";
			grille[i][j] = ".";
		}
		
		if (deplacement.equals("bas")) {
			grille[i-2][j] = "o";
			grille[i-1][j] = "o";
			grille[i][j] = ".";
		}
			
		if (deplacement.equals("haut")) {
			grille[i][j] = ".";
			grille[i+1][j] = "o";
			grille[i+2][j] = "o";
		}
	}

	public String[][] getGrille() {
		return this.grille;
	}
	
	public void setGrille(String[][] grille) {
		this.grille = grille;
	}

	public int getNbLignes() {
		return nbLignes;
	}

	public int getNbColonnes() {
		return nbColonnes;
	}

	public String getCase(int i, int j) {
		return grille[i][j];
	}
	
	
	
}
