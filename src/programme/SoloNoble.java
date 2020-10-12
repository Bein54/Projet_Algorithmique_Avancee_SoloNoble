package programme;

import java.io.File;
import java.io.IOException;
import java.util.Set;
import java.util.TreeMap;



public class SoloNoble {
	
	private Grille tablier;
	private TreeMap<Integer,String[][]> solutions;
	
	public SoloNoble(File f) throws IOException {
		this.tablier = new Grille(f);
		this.solutions = new TreeMap<Integer, String[][]>();
	}
	
	public SoloNoble() throws IOException {
		this.tablier = new Grille();
		this.solutions = new TreeMap<Integer, String[][]>();
	}
	
	


	public boolean resoudreSoloNoble(int billes) throws IOException {
		boolean grilleValide = true;
		/*if (billes == 2) {
			System.out.println("YESSSSSSSS");
			String[][] grilleTempo = new String[this.tablier.grille.length][this.tablier.grille[0].length];
			for (int i = 0; i < this.tablier.grille.length; i++) {
				for (int j = 0; j < this.tablier.grille[0].length; j++) {
					System.out.print(this.tablier.grille[i][j]);
				}
				System.out.println();
			}
			
		}*/
		if (billes == 1) {
			//C'est un merdier
			System.out.println("YESSSSSSSS");
			String[][] grilleTempo = new String[this.tablier.nbLignes][this.tablier.nbColonnes];
			for (int i = 0; i < this.tablier.grille.length; i++) {
				for (int j = 0; j < this.tablier.grille[0].length; j++) {
					grilleTempo[i][j] = this.tablier.grille[i][j];
				}
			}
			solutions.put(billes, grilleTempo);
			for(int i = solutions.size(); i > 0; i--) {
				String [][] elt = solutions.get(i);
				for (int j = 0; j < elt.length; j++) {
					for (int k = 0; k < elt[0].length; k++) {
						System.out.print(elt[i][j]);
					}
					System.out.println();
				}
				System.out.println("--------------------------");
			}
			//ecrireToutesSolutions();
		}
		
		else {
			int i = 0;
			int j = 0;
			boolean deplacementValide = false;
			grilleValide = false;
			
			String[][] grilleTempo = new String[this.tablier.grille.length][this.tablier.grille[0].length];
			for (int a = 0; a < this.tablier.grille.length; a++) {
				for (int b = 0; b < this.tablier.grille[0].length; b++) {
					grilleTempo[a][b] = this.tablier.grille[a][b];
				}
			}
			
			solutions.put(billes, grilleTempo);
			
			while (!grilleValide && i < this.tablier.getNbLignes()) {
				
				j = 0;
				while (!grilleValide && j < this.tablier.getNbColonnes()) {
					
					if (this.tablier.grille[i][j].equals(".")) {
						//System.out.println(billes);
						
						if(!grilleValide && (this.tablier.deplacementValide("gauche", i,j))) {
							this.tablier.deplacerBille("gauche", i,j);
							grilleValide = this.resoudreSoloNoble(billes - 1);
							//System.out.println(grilleValide);
							if (!grilleValide) {

								this.tablier.retourArriere("gauche", i, j);
							}
						}
						
						if(!grilleValide && (this.tablier.deplacementValide("droite", i,j))) {
							this.tablier.deplacerBille("droite", i,j);
							grilleValide = this.resoudreSoloNoble(billes - 1);
							if (!grilleValide) {
								this.tablier.retourArriere("droite", i, j);
							}
						}
						
						if(!grilleValide && (this.tablier.deplacementValide("bas", i,j))) {
							this.tablier.deplacerBille("bas", i,j);
							grilleValide = this.resoudreSoloNoble(billes - 1);
							if (!grilleValide) {
								this.tablier.retourArriere("bas", i, j);
							}
						}
						
						if(!grilleValide && (this.tablier.deplacementValide("haut", i,j))) {
							this.tablier.deplacerBille("haut", i,j);
							grilleValide = this.resoudreSoloNoble(billes - 1);
							if (!grilleValide) {
								this.tablier.retourArriere("haut", i, j);
							}
						}
						
			
					}
					j++;
				}
				i++;
			}
			
		}
		return grilleValide;
	}
	
	
	public void ecrireSolution(int nbBilles) {
		solutions.put(nbBilles, this.tablier.getGrille());
	}
	
	public void ecrireToutesSolutions() {
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
	
	

	public TreeMap<Integer, String[][]> getSolutions() {
		return solutions;
	}

	public static void main(String[] args) throws IOException {
		SoloNoble sn;
		if (args.length > 0)
			sn = new SoloNoble(new File("src/grilles/tablier2.txt"));
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
