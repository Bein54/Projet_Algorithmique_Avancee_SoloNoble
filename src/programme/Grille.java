package programme;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class Grille {
	
	private int ligneTrou, colonneTrou, nbLignes, nbColonnes, numeroCas;
	private boolean finGrille;
	private String[][] grille;
	
	public Grille(File f) throws IOException {
		this.ligneTrou = 0;
		this.colonneTrou = 0;
		this.finGrille = false;
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
		this.numeroCas = 0;
		this.nbColonnes = this.grille[0].length;
		this.nbLignes = this.grille.length;
	}
	
	public Grille(String[][] tab) {
		this.ligneTrou = 0;
		this.colonneTrou = 0;
		this.finGrille = false;
		this.numeroCas = 0;
		this.grille = tab;
		this.nbColonnes = this.grille[0].length;
		this.nbLignes = this.grille.length;
	}
	
	public Grille() throws IOException {
		this.ligneTrou = 0;
		this.colonneTrou = 0;
		this.finGrille = false;
		this.numeroCas = 0;
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
	
	public void chercherTrou(){
		boolean trouve = false;
		
		if(colonneTrou == nbColonnes -1) {
			colonneTrou = 0;
			if (ligneTrou + 1 == nbLignes) {
				finGrille = true;
			}
			else
				ligneTrou++;
		}
		
		else
			colonneTrou++;
		
		if(!finGrille) {
			int i = ligneTrou;
			int j = colonneTrou;
			
			while (i < this.nbLignes && !trouve) {
				String[] ligne = this.grille[i];
				
				while (j < this.nbColonnes && !trouve) {
					
					if (ligne[j].equals(".")) {
						//System.out.println(j);
						trouve = true;
						this.ligneTrou = i;
						this.colonneTrou = j;
						this.numeroCas = 0;
					}
					else 
						j++;
				}
				i++;
				j = 0;
			}
			
			if (!trouve) {
				this.finGrille = true;
			}
		}
	}
	
	public boolean deplacerBille(){
		String[] ligne = this.grille[ligneTrou];
		if (colonneTrou+2 < nbColonnes) {
			if(ligne[colonneTrou+1].equals("o") && ligne[colonneTrou+2].equals("o") && (numeroCas == 0)) {
				
				grille[ligneTrou][colonneTrou] = "o";
				grille[ligneTrou][colonneTrou+1] = ".";
				grille[ligneTrou][colonneTrou+2] = ".";
				numeroCas = 1;
				return true;
			}
		}
		if((colonneTrou-2)>=0) {
			
			if(ligne[colonneTrou-1].equals("o") && ligne[colonneTrou-2].equals("o") && (numeroCas == 0 || numeroCas == 1)) {
				
				grille[ligneTrou][colonneTrou-2] = ".";
				grille[ligneTrou][colonneTrou-1] = ".";
				grille[ligneTrou][colonneTrou] = "o";
				
				numeroCas = 2;
				return true;
			}
		}
		
			if ((ligneTrou-2>= 0) && ((colonneTrou-1>=0) )) {
				String[] ligne1 = this.grille[ligneTrou-2];
				String[] ligne2 = this.grille[ligneTrou-1];
				
				if(ligne1[colonneTrou].equals("o") && ligne2[colonneTrou].equals("o") && (numeroCas != 3 && numeroCas != 4)) {
					
					grille[ligneTrou-2][colonneTrou] = ".";
					grille[ligneTrou-1][colonneTrou] = ".";
					grille[ligneTrou][colonneTrou] = "o";
					
					numeroCas = 3;
					return true;
				}
			}
			
			if ((ligneTrou + 2 < nbLignes) && ((colonneTrou - 1 >= 0) && (colonneTrou + 1 < nbColonnes))) {
					String[] ligne1 = this.grille[ligneTrou+1];
					String[] ligne2 = this.grille[ligneTrou+2];
					
					if(ligne1[colonneTrou].equals("o") && ligne2[colonneTrou].equals("o") && numeroCas != 4) {
						
						grille[ligneTrou][colonneTrou] = "o";
						grille[ligneTrou+1][colonneTrou] = ".";
						grille[ligneTrou+2][colonneTrou] = ".";
						
						numeroCas = 4;
						return true;
					}
			}
		return false;
	}

	public String[][] getGrille() {
		return this.grille;
	}
	
	public void setGrille(String[][] grille) {
		this.grille = grille;
	}
	
	public boolean getFinGrille() {
		return this.finGrille;
	}

	public void setFinGrille(boolean finGrille) {
		this.finGrille = finGrille;
	}
	
	
}
