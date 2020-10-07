package programme;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

public class Grille {
	
	//private File grille;
	private int ligneTrou, colonneTrou, nbLignes, nbColonnes, numeroCas;
	private boolean finGrille;
	private String[][] nouvelleGrille;
	private String[][] grille;
	
	public Grille(File f) throws IOException {
		this.ligneTrou = 0;
		this.colonneTrou = 0;
		this.finGrille = false;
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
		this.numeroCas = 0;
		this.nouvelleGrille = grille;
		this.nbColonnes = this.grille[0].length;
		this.nbLignes = this.grille.length;
	}
	
	public Grille() throws IOException {
		this.ligneTrou = 0;
		this.colonneTrou = 0;
		this.finGrille = false;
		this.numeroCas = 0;
		BufferedReader br = new BufferedReader(new FileReader("grilles/tablier1.txt"));
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
		this.nouvelleGrille = grille;
	}
	
	public int calculerNbBilles(){
		int somme = 0;
		for (int i = 0; i < grille.length; i++) {
			for (int j = 0; j < grille[i].length; j++) {
				if(grille[i][j].equals("o"))
					somme++;
			}
		}
		return somme;
	}
	
	public void chercherTrou() throws IOException {
		boolean trouve = false;
		int i = 0;
		while (i < this.nbLignes && !trouve) {
			String[] ligne = this.grille[i];
			int j = 0;
			while (j < this.nbColonnes && !trouve) {
				if (ligne[j].equals(".")) {
					trouve = true;
					this.ligneTrou = i;
					this.colonneTrou = j;
				}
				else 
					j++;
			}
			if (!trouve)
				i++;
		}
		
		if (!trouve) {
			this.finGrille = true;
		}
	}
	
	public String[][] deplacerBille() throws IOException {
		String[] ligne = this.grille[ligneTrou];
		String[] tab = new String[nbColonnes];
		boolean deplacementReussi = false;
		if(ligne[colonneTrou+1].equals("o") && ligne[nbColonnes+2].equals("o") && numeroCas == 0) {
			for (int i = 0; i <= colonneTrou-1; i++) {
				tab[i] = ligne[i];
			}
			tab[colonneTrou] = "o";
			tab[colonneTrou + 1] = ".";
			tab[colonneTrou + 2] = ".";
			for (int i = colonneTrou + 3; i < tab.length; i++) {
				tab[i] = ligne[i];
			}
			String[][] t = {tab};
			this.ecrireNouvelleGrille(t, ligneTrou);
			numeroCas = 1;
			deplacementReussi = true;
		}
		
		else if(ligne[colonneTrou-1].equals("o") && ligne[nbColonnes-2].equals("o") && (numeroCas == 0 || numeroCas == 1)) {
			for (int i = 0; i <= colonneTrou-3; i++) {
				tab[i] = ligne[i];
			}
			tab[colonneTrou - 2] = ".";
			tab[colonneTrou - 1] = ".";
			tab[colonneTrou] = "o";
			for (int i = colonneTrou + 1; i < tab.length; i++) {
				tab[i] = ligne[i];
			}
			String[][] t = {tab};
			this.ecrireNouvelleGrille(t, ligneTrou);
			numeroCas = 2;
			deplacementReussi = true;
		}
		else {
			String[] ligne1 = this.grille[ligneTrou-2];
			String[] ligne2 = this.grille[ligneTrou-1];
			String[] tab1 = new String[nbColonnes];
			String[] tab2 = new String[nbColonnes];
			if(ligne1[colonneTrou].equals("o") && ligne2[colonneTrou].equals("o") && (numeroCas != 3 && numeroCas != 4)) {
				
				for (int i = 0; i <= colonneTrou-1; i++) {
					tab1[i] = ligne[i];
				}
				tab1[colonneTrou] = ".";
				for (int i = colonneTrou + 1; i < tab.length; i++) {
					tab1[i] = ligne[i];
				}
				
				for (int i = 0; i <= colonneTrou-1; i++) {
					tab2[i] = ligne[i];
				}
				tab2[colonneTrou] = ".";
				for (int i = colonneTrou + 1; i < tab.length; i++) {
					tab2[i] = ligne[i];
				}
				
				for (int i = 0; i <= colonneTrou-1; i++) {
					tab[i] = ligne[i];
				}
				tab[colonneTrou] = "o";
				for (int i = colonneTrou + 1; i < tab.length; i++) {
					tab[i] = ligne[i];
				}
				String[][] t = {tab1, tab2, tab};
				this.ecrireNouvelleGrille(t, ligneTrou -2);
				numeroCas = 3;
				deplacementReussi = true;
			}
			else {
				ligne1 = this.grille[ligneTrou+1];
				ligne2 = this.grille[ligneTrou+2];
				
				if(ligne1[colonneTrou].equals("o") && ligne2[colonneTrou].equals("o") && numeroCas != 4) {
					for (int i = 0; i <= colonneTrou-1; i++) {
						tab[i] = ligne[i];
					}
					tab[colonneTrou] = "o";
					for (int i = colonneTrou + 1; i < tab.length; i++) {
						tab[i] = ligne[i];
					}
					
					for (int i = 0; i <= colonneTrou-1; i++) {
						tab1[i] = ligne[i];
					}
					tab1[colonneTrou] = ".";
					for (int i = colonneTrou + 1; i < tab.length; i++) {
						tab1[i] = ligne[i];
					}
					
					for (int i = 0; i <= colonneTrou-1; i++) {
						tab2[i] = ligne[i];
					}
					tab2[colonneTrou] = ".";
					for (int i = colonneTrou + 1; i < tab.length; i++) {
						tab2[i] = ligne[i];
					}
					
					String[][] t = {tab, tab1, tab2};
					this.ecrireNouvelleGrille(t, ligneTrou);
					numeroCas = 4;
					deplacementReussi = true;
				}
			}
		}
		if(deplacementReussi) {
			
		}
		
		return this.nouvelleGrille;
	}
	
	public String[][] getGrille() {
		return this.grille;
	}
	
	public void ecrireNouvelleGrille(String[][] tab, int indiceDebut) throws IOException {
		for (int i = 0; i < indiceDebut; i++) {
			this.nouvelleGrille[i] = this.grille[i];
		}
		int taille = tab.length;
		for (int i = indiceDebut; i < taille; i++) {
			this.nouvelleGrille[i] = tab[i-indiceDebut];
		}
		for (int i = taille; i < this.grille.length; i++) {
			this.nouvelleGrille[i] = this.grille[i];
		}
	}
	
	public void ecrireSolution() throws IOException {
		/*Grille g = new Grille();
		BufferedReader br = new BufferedReader(new FileReader(g.getGrille()));
		String ligne;
		while ((ligne = br.readLine()) != null){
			// Afficher le contenu du fichier ligne par ligne
			System.out.println (ligne);
		}
		br.close();*/
	}

	public int getNbLignes() {
		return this.nbLignes;
	}

	public int getNbColonnes() {
		return this.nbColonnes;
	}
	
	public boolean getFinGrille() {
		return this.finGrille;
	}
	
	public int getNumeroCas() {
		return this.numeroCas;
	}
}
