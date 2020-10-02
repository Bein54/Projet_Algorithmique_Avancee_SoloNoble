package programme;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

public class Grille {
	
	private File grille;
	private int ligneTrou, colonneTrou, nbLignes, nbColonnes, numeroCas;
	private boolean finFichier;
	private File nouveauFichier;
	
	public Grille(File f) throws IOException {
		this.ligneTrou = 0;
		this.colonneTrou = 0;
		this.finFichier = false;
		this.grille = f;
		this.numeroCas = 0;
		this.nouveauFichier = grille;
		this.calculerTaille();
	}
	
	public Grille() throws IOException {
		this.ligneTrou = 0;
		this.colonneTrou = 0;
		this.finFichier = false;
		this.numeroCas = 0;
		File f = new File("tablier_par_defaut.txt");
		PrintWriter writer = new PrintWriter(f, "UTF-8");
		writer.println("  ooo  ");
		writer.println("  ooo  ");
		writer.println("ooooooo");
		writer.println("ooo.ooo");
		writer.println("ooooooo");
		writer.println("  ooo  ");
		writer.println("  ooo  ");
		writer.close();
		this.grille = f;
		this.calculerTaille();
		this.nouveauFichier = grille;
	}
	
	public void calculerTaille() throws IOException {
		BufferedReader br = new BufferedReader(new FileReader(this.grille));
		int colonnes = br.readLine().length();
		this.nbColonnes = colonnes;
		int lignes = 1;
		while (br.readLine() != null)
			lignes++;
		br.close();
		this.nbLignes = lignes;
	}
	
	public String trouverLigne(int ligne) throws IOException {
		BufferedReader br = new BufferedReader(new FileReader(this.grille));
		int i = 0;
		String res = br.readLine();
		while (i != ligne) {
			i++;
			res = br.readLine();
		}
		br.close();
		return res;
	}
	
	public void chercherTrou() throws IOException {
		boolean trouve = false;
		int i = 0;
		while (i < this.nbLignes && !trouve) {
			String ligne = this.trouverLigne(i);
			int j = 0;
			while (j < this.nbColonnes && !trouve) {
				if (ligne.charAt(j) == ('.')) {
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
			this.finFichier = true;
		}
	}
	
	public File deplacerBille() throws IOException {
		String ligne = this.trouverLigne(ligneTrou);
		String s = "";
		boolean deplacementReussi = false;
		File f = this.grille;
		if(ligne.charAt(colonneTrou+1)=='o' && ligne.charAt(nbColonnes+2)=='o' && numeroCas == 0) {
			s = ligne.substring(0, colonneTrou-1);
			s+="o";
			s+=".";
			s+=".";
			s+=ligne.substring(colonneTrou+3);
			
			String[] tab = {s};
			this.ecrireNouveauFichier(tab, ligneTrou);
			numeroCas = 1;
			deplacementReussi = true;
		}
		
		else if(ligne.charAt(colonneTrou-1)=='o' && ligne.charAt(nbColonnes-2)=='o' && (numeroCas == 0 || numeroCas == 1)) {
			s = ligne.substring(0, colonneTrou-3);
			s+=".";
			s+=".";
			s+="o";
			s+=ligne.substring(colonneTrou+1);
			
			String[] tab = {s};
			this.ecrireNouveauFichier(tab, ligneTrou);
			numeroCas = 2;
			deplacementReussi = true;
		}
		else {
			String ligne1 = this.trouverLigne(ligneTrou-2);
			String ligne2 = this.trouverLigne(ligneTrou-1);
			String s1 = "";
			String s2 = "";
			if(ligne1.charAt(colonneTrou)=='o' && ligne2.charAt(colonneTrou)=='o' && (numeroCas != 3 && numeroCas != 4)) {
				s1 = ligne1.substring(0, colonneTrou-1);
				s1+=".";
				s1+=ligne1.substring(colonneTrou+1);
				
				s2 = ligne2.substring(0, colonneTrou-1);
				s2+=".";
				s2+=ligne2.substring(colonneTrou+1);
				
				s = ligne.substring(0, colonneTrou-1);
				s+="o";
				s+=ligne.substring(colonneTrou+1);
				
				String[] tab = {s1,s2,s};
				this.ecrireNouveauFichier(tab, ligneTrou -2);
				numeroCas = 3;
				deplacementReussi = true;
			}
			else {
				ligne1 = this.trouverLigne(ligneTrou+1);
				ligne2 = this.trouverLigne(ligneTrou+2);
				
				if(ligne1.charAt(colonneTrou)=='o' && ligne2.charAt(colonneTrou)=='o' && numeroCas != 4) {
					s = ligne.substring(0, colonneTrou-1);
					s+="o";
					s+=ligne.substring(colonneTrou+1);
					
					s1 = ligne1.substring(0, colonneTrou-1);
					s1+=".";
					s1+=ligne1.substring(colonneTrou+1);
					
					s2 = ligne2.substring(0, colonneTrou-1);
					s2+=".";
					s2+=ligne2.substring(colonneTrou+1);
					
					String[] tab = {s,s1,s2};
					this.ecrireNouveauFichier(tab, ligneTrou);
					numeroCas = 4;
					deplacementReussi = true;
				}
			}
		}
		if(deplacementReussi) {
			
		}
		
		return nouveauFichier;
	}
	
	public File getGrille() {
		return this.grille;
	}
	
	public void ecrireNouveauFichier(String[] tab, int indiceDebut) throws IOException {
		int i = 0;
		File f = new File("nouveau_fichier");
		PrintWriter writer = new PrintWriter(f, "UTF-8");
		BufferedReader br = new BufferedReader(new FileReader(this.grille));
		String ligne = br.readLine();
		while (i != indiceDebut) {
			writer.println(ligne);
			ligne = br.readLine();
			i++;
		}
		
		int posArret = i;
		for (int ind = 0; i< tab.length; ind++) {
			writer.println(tab[ind]);
		}
		
		while(i != posArret+tab.length) {
			ligne = br.readLine();
			i++;
		}
		
		while(ligne != null) {
			writer.println(ligne);
			ligne = br.readLine();
		}
		
		br.close();
		writer.close();
		this.nouveauFichier = f;
	}
	
	public void ecrireSolution() throws IOException {
		Grille g = new Grille();
		BufferedReader br = new BufferedReader(new FileReader(g.getGrille()));
		String ligne;
		while ((ligne = br.readLine()) != null){
			// Afficher le contenu du fichier ligne par ligne
			System.out.println (ligne);
		}
		br.close();
	}

	public int getNbLignes() {
		return this.nbLignes;
	}

	public int getNbColonnes() {
		return this.nbColonnes;
	}
	
	public boolean getFinFichier() {
		return this.finFichier;
	}
	
	public int getNumeroCas() {
		return this.numeroCas;
	}
}
