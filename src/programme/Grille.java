package programme;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

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
		this.nouvelleGrille = grille;
		this.nbColonnes = this.grille[0].length;
		this.nbLignes = this.grille.length;
	}
	
	public Grille(String[][] tab) {
		this.ligneTrou = 0;
		this.colonneTrou = 0;
		this.finGrille = false;
		this.numeroCas = 0;
		this.grille = tab;
		System.out.println("taille de grille" + this.grille[0].length);
		this.nbColonnes = this.grille[0].length;
		this.nbLignes = this.grille.length;
		this.nouvelleGrille = grille;
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
		this.nbColonnes = dimensions[1];
		this.nbLignes = dimensions[0];
		this.nouvelleGrille = grille;
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
			//System.out.println(j);
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
			System.out.println("ligne :" + i);
			for (int j = 0; j < nbColonnes; j++) {
				System.out.println("colonne :" + j);
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
						System.out.println(j);
						trouve = true;
						this.ligneTrou = i;
						this.colonneTrou = j;
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
		
		if(ligneTrou == 4 && colonneTrou == 6) {
			System.out.println("YESSSSSSSSSSSSSSSSSSSSSSSSSSSS");
			System.out.println(numeroCas);
		}
		
	}
	
	public boolean deplacerBille(){
		String[] ligne = this.grille[ligneTrou];
		System.out.println("colonneTrou :"+colonneTrou);
		String[] tab = new String[nbColonnes];
		if (colonneTrou+2 < nbColonnes) {
			System.out.println("MouvementDroite");
			if(ligne[colonneTrou+1].equals("o") && ligne[colonneTrou+2].equals("o") && (numeroCas == 0)) {
				System.out.println("YAAAAAAAAAAA");
				/*for (int i = 0; i <= colonneTrou-1; i++) {
					tab[i] = ligne[i];
				}
				tab[colonneTrou] = "o";
				tab[colonneTrou + 1] = ".";
				tab[colonneTrou + 2] = ".";
				for (int i = colonneTrou + 3; i < tab.length; i++) {
					tab[i] = ligne[i];
				}
				String[][] t = {tab};
				this.ecrireNouvelleGrille(t, ligneTrou);*/
				
				grille[ligneTrou][colonneTrou] = "o";
				grille[ligneTrou][colonneTrou+1] = ".";
				grille[ligneTrou][colonneTrou+2] = ".";
				numeroCas = 1;
				return true;
			}
		}
		if((colonneTrou-2)>=0) {
			
			if(ligne[colonneTrou-1].equals("o") && ligne[colonneTrou-2].equals("o") && (numeroCas == 0 || numeroCas == 1)) {
				System.out.println("MouvementGauche");
				/*for (int i = 0; i <= colonneTrou-3; i++) {
					tab[i] = ligne[i];
				}
				tab[colonneTrou - 2] = ".";
				tab[colonneTrou - 1] = ".";
				tab[colonneTrou] = "o";
				for (int i = colonneTrou + 1; i < tab.length; i++) {
					tab[i] = ligne[i];
				}
				String[][] t = {tab};
				this.ecrireNouvelleGrille(t, ligneTrou);*/
				
				grille[ligneTrou][colonneTrou-2] = ".";
				grille[ligneTrou][colonneTrou-1] = ".";
				grille[ligneTrou][colonneTrou] = "o";
				numeroCas = 2;
				return true;
			}
		}
		
			String[] tab1 = new String[nbColonnes];
			String[] tab2 = new String[nbColonnes];
			if ((ligneTrou-2>= 0) && ((colonneTrou-1>=0) )) {
				String[] ligne1 = this.grille[ligneTrou-2];
				String[] ligne2 = this.grille[ligneTrou-1];
				System.out.println("3OUIIIIIIIIIIIIII");
				if(ligne1[colonneTrou].equals("o") && ligne2[colonneTrou].equals("o") && (numeroCas != 3 && numeroCas != 4)) {
					System.out.println("MouvementHaut");
					/*for (int i = 0; i <= colonneTrou-1; i++) {
						tab1[i] = ligne1[i];
					}
					tab1[colonneTrou] = ".";
					for (int i = colonneTrou + 1; i < tab.length; i++) {
						tab1[i] = ligne1[i];
					}
					
					for (int i = 0; i <= colonneTrou-1; i++) {
						tab2[i] = ligne2[i];
					}
					tab2[colonneTrou] = ".";
					for (int i = colonneTrou + 1; i < tab.length; i++) {
						tab2[i] = ligne2[i];
					}
					
					for (int i = 0; i <= colonneTrou-1; i++) {
						tab[i] = ligne[i];
					}
					tab[colonneTrou] = "o";
					for (int i = colonneTrou + 1; i < tab.length; i++) {
						tab[i] = ligne[i];
					}
					String[][] t = {tab1, tab2, tab};
					this.ecrireNouvelleGrille(t, ligneTrou -2);*/
					
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
						System.out.println("MouvementBas");
						/*for (int i = 0; i <= colonneTrou-1; i++) {
							tab[i] = ligne[i];
						}
						tab[colonneTrou] = "o";
						for (int i = colonneTrou + 1; i < tab.length; i++) {
							tab[i] = ligne[i];
						}
						
						for (int i = 0; i <= colonneTrou-1; i++) {
							tab1[i] = ligne1[i];
						}
						tab1[colonneTrou] = ".";
						for (int i = colonneTrou + 1; i < tab.length; i++) {
							tab1[i] = ligne1[i];
						}
						
						for (int i = 0; i <= colonneTrou-1; i++) {
							tab2[i] = ligne2[i];
						}
						tab2[colonneTrou] = ".";
						for (int i = colonneTrou + 1; i < tab.length; i++) {
							tab2[i] = ligne2[i];
						}
						
						String[][] t = {tab, tab1, tab2};
						this.ecrireNouvelleGrille(t, ligneTrou);*/
						
						grille[ligneTrou][colonneTrou] = "o";
						grille[ligneTrou+1][colonneTrou] = ".";
						grille[ligneTrou+2][colonneTrou] = ".";
						numeroCas = 4;
						return true;
					}
			}
		return false;
	}
	
	public String[][] getNouvelleGrille() {
		return nouvelleGrille;
	}

	public String[][] getGrille() {
		return this.grille;
	}
	
	public void setGrille(String[][] grille) {
		this.grille = grille;
	}

	public void ecrireNouvelleGrille(String[][] tab, int indiceDebut){
		
		for (int i = 0; i < indiceDebut; i++) {
			this.nouvelleGrille[i] = this.grille[i];
		}
		
		int taille = tab.length;
		
		for (int i = indiceDebut; i < indiceDebut + taille; i++) {
			this.nouvelleGrille[i] = tab[i-indiceDebut];
		}
		
		for (int i = indiceDebut + taille; i < this.grille.length; i++) {
			this.nouvelleGrille[i] = this.grille[i];
		}
		
		this.grille = nouvelleGrille;
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
