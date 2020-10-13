package programme;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Grille {
	
	private int nbLignes, nbColonnes;
	private String[][] grille;
	
	public Grille(String nomFichier) throws IOException {
		BufferedReader br = new BufferedReader(new FileReader(new File(nomFichier)));
		String ligne;
		List<List<String>> l1 = new ArrayList<List<String>>();
		
		while ((ligne = br.readLine()) != null){
			List<String> l2 = new ArrayList<String>();
			for (int j = 0; j < ligne.length(); j++) {
				l2.add(Character.toString(ligne.charAt(j)));
			}
			l1.add(l2);
		}
		br.close();
		
		this.grille = new String[l1.size()][l1.get(0).size()];
		
		for (int i = 0; i < l1.size(); i++) {
            for (int j = 0; j < l1.get(0).size(); j++) {
                grille[i][j] = l1.get(i).get(j);
            }
        }
		
		this.nbColonnes = this.grille[0].length;
		this.nbLignes = this.grille.length;
	}
	
	public Grille() throws IOException {
		
		grille = new String[7][7];
		
		for (int i = 0; i < 2; i++) {
			grille[i][0] = " ";
			grille[i][1] = " ";
			for (int j = 2; j < 5; j++) {
				grille[i][j] = "o";
			}
			grille[i][5] = " ";
			grille[i][6] = " ";
		}
		
		for (int i = 2; i < 5; i++) {
			for (int j = 0; j < grille[0].length; j++) {
				grille[i][j] = "o";
			}
		}
		
		grille[3][3] = ".";
		
		for (int i = 5; i < grille.length; i++) {
			grille[i][0] = " ";
			grille[i][1] = " ";
			for (int j = 2; j < 5; j++) {
				grille[i][j] = "o";
			}
			grille[i][5] = " ";
			grille[i][6] = " ";
		}
		
		this.nbColonnes = this.grille[0].length;
		this.nbLignes = this.grille.length;
	}
	
	public int calculerNombreBilles() {
		int somme = 0;
		for (int i = 0; i < nbLignes; i++) {
			for (int j = 0; j < nbColonnes; j++) {
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
			if(grille[i-1][j].equals("o") && grille[i-2][j].equals("o")) {
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
		switch(deplacement) {
		case "gauche" :
			grille[i][j] = "o";
			grille[i][j+1] = ".";
			grille[i][j+2] = ".";
			break;
		case "droite" :
			grille[i][j-2] = ".";
			grille[i][j-1] = ".";
			grille[i][j] = "o";
			break;
		case "haut" :
			grille[i][j] = "o";
			grille[i+1][j] = ".";
			grille[i+2][j] = ".";
			break;
		case "bas" :
			grille[i-2][j] = ".";
			grille[i-1][j] = ".";
			grille[i][j] = "o";
			break;
		}
	}
	public void retourArriere(String deplacement, int i, int j) {
		switch(deplacement) {
		case "gauche" :
			grille[i][j] = ".";
			grille[i][j+1] = "o";
			grille[i][j+2] = "o";
			break;
		case "droite" :
			grille[i][j-2] = "o";
			grille[i][j-1] = "o";
			grille[i][j] = ".";
			break;
		case "bas" :
			grille[i-2][j] = "o";
			grille[i-1][j] = "o";
			grille[i][j] = ".";
			break;
		case "haut" :
			grille[i][j] = ".";
			grille[i+1][j] = "o";
			grille[i+2][j] = "o";
			break;
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
}
