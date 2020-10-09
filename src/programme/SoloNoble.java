package programme;

import java.awt.List;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Set;
import java.util.TreeMap;



public class SoloNoble {
	
	private Grille tablier;
	private HashMap<Integer,String[][]> solutions;
	
	public SoloNoble(File f) throws IOException {
		this.tablier = new Grille(f);
		this.solutions = new HashMap<Integer, String[][]>();
	}
	
	public SoloNoble() throws IOException {
		this.tablier = new Grille();
		this.solutions = new HashMap<Integer, String[][]>();
	}
	
	


	public boolean resoudreSoloNoble(int billes) throws IOException {
		boolean grilleValide = true;
		File test = new File("test.txt");
		if (billes == 1) {
			this.ecrireSolution(1);
		}
		
		else {
			String[][] grilleDeBase = this.tablier.getGrille();
			String[][] grilleActuelle = this.tablier.getGrille();
			boolean deplacementReussi = false;
			grilleValide = false;
			
			Grille nouvelleGrille = this.tablier;
			
			while ((!grilleValide) && !this.tablier.getFinGrille()) {
				
				this.tablier.chercherTrou();
				
				deplacementReussi = this.tablier.deplacerBille();
				System.out.println(deplacementReussi);
				grilleActuelle = this.tablier.getGrille();
				for (int i = 0; i < grilleActuelle.length; i++) {
					for (int j = 0; j < grilleActuelle[i].length; j++) {
						System.out.print(grilleActuelle[i][j]);
					}
					System.out.println();
				}

				while (deplacementReussi && !grilleValide) {
					this.tablier = new Grille(grilleActuelle);
					
					grilleValide = this.resoudreSoloNoble(billes - 1);
					System.out.println(grilleValide);
					
					if (!grilleValide) {
						
						this.tablier.setGrille(grilleActuelle);
						deplacementReussi = tablier.deplacerBille();
						System.out.println(deplacementReussi);
						grilleActuelle = this.tablier.getGrille();
					}
					this.tablier = new Grille(test);
					grilleValide = this.resoudreSoloNoble(billes -1);
				}
				
				if (!deplacementReussi && !grilleValide) {
					this.tablier.setGrille(grilleActuelle);
				}
			}
			
			if(!grilleValide) {
				this.tablier.setGrille(grilleDeBase);
			}
			
			else
				this.ecrireSolution(billes);
			
		}
		return grilleValide;
	}
	
	private void ecrireSolution(int nbBilles) {
		solutions.put(nbBilles, this.tablier.getGrille());
	}
	
	private void ecrireToutesSolutions() {
		String s = "";
		Set<Integer> cle = this.solutions.keySet();
		for (Integer elt : cle) {
			String[][] tab = solutions.get(elt);
			for (int i = 0; i < tab.length; i++) {
				for (int j = 0; j < tab[i].length; j++) {
					s+=tab[i][j];
				}
				s+="\n";
			}
			s+="\n\n";
		}
		System.out.println(s);
	}
	
	

	public HashMap<Integer, String[][]> getSolutions() {
		return solutions;
	}

	public static void main(String[] args) throws IOException {
		SoloNoble sn;
		if (args.length > 0)
			sn = new SoloNoble(new File(args[0]));
		else {
			sn = new SoloNoble();
		}
		int nbBilles = sn.getTablier().calculerNbBilles();
		sn.resoudreSoloNoble(nbBilles);
		sn.ecrireToutesSolutions();
	}

	

	public Grille getTablier() {
		return tablier;
	}
}
