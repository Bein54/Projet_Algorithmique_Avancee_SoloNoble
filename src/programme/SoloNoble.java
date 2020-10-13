package programme;

import java.io.File;
import java.io.IOException;
import java.util.Set;
import java.util.TreeMap;



public class SoloNoble {
	
	private Grille tablier;
	private TreeMap<Integer,String[][]> solutions;
	private int nombreDeplacements, nombreAppelsResoudreSoloNoble;
	
	public SoloNoble(File f) throws IOException {
		this.tablier = new Grille(f);
		this.solutions = new TreeMap<Integer, String[][]>();
		this.nombreDeplacements = 0;
		this.nombreAppelsResoudreSoloNoble = 0;
	}
	
	public SoloNoble() throws IOException {
		this.tablier = new Grille();
		this.solutions = new TreeMap<Integer, String[][]>();
		this.nombreDeplacements = 0;
		this.nombreAppelsResoudreSoloNoble = 0;
	}
	
	


	public boolean resoudreSoloNoble(int billes) throws IOException {
		boolean grilleValide = false;
		if (billes == 1) {
			//C'est un merdier
			grilleValide = true;
			System.out.println("YESSSSSSSS");
			String[][] grilleTempo = new String[this.tablier.nbLignes][this.tablier.nbColonnes];
			for (int i = 0; i < this.tablier.grille.length; i++) {
				for (int j = 0; j < this.tablier.grille[0].length; j++) {
					grilleTempo[i][j] = this.tablier.grille[i][j];
				}
			}
			solutions.put(billes, grilleTempo);
			for(int i = solutions.size(); i > 0; i--) {
				System.out.println();
				String [][] elt = solutions.get(i);
				for (int j = 0; j < elt.length; j++) {
					for (int k = 0; k < elt[0].length; k++) {
						System.out.print(elt[j][k]);
					}
					System.out.println();
				}
				System.out.println("--------------------------");
			}
			System.out.println("Nombre de déplacements : " + nombreDeplacements);
			System.out.println("Nombre d'appels de la méthode resoudreSoloNoble() : " + nombreAppelsResoudreSoloNoble);
			//ecrireToutesSolutions();
		}
		
		else {
			int i = 0;
			int j = 0;
			grilleValide = false;
			
			String[][] grilleTempo = new String[this.tablier.grille.length][this.tablier.grille[0].length];
			for (int a = 0; a < this.tablier.grille.length; a++) {
				for (int b = 0; b < this.tablier.grille[0].length; b++) {
					grilleTempo[a][b] = this.tablier.grille[a][b];
				}
			}
			/*if (billes == 15)
			for (int k = 0; k < grilleTempo.length; k++) {
				for (int k2 = 0; k2 < grilleTempo[0].length; k2++) {
					System.out.print(grilleTempo[k][k2]);
				}
				System.out.println();
			}*/
			
			
			
			while (!grilleValide && i < this.tablier.getNbLignes()) {
				
				j = 0;
				while (!grilleValide && j < this.tablier.getNbColonnes()) {
					
					if (this.tablier.getCase(i,j).equals(".")) {
						
						
						if(!grilleValide && (this.tablier.deplacementValide("haut", i,j))) {
							this.tablier.deplacerBille("haut", i,j);
							nombreDeplacements++;
							solutions.put(billes, grilleTempo);
							grilleValide = this.resoudreSoloNoble(billes - 1);
							nombreAppelsResoudreSoloNoble++;
							//System.out.println(grilleValide);
							if (!grilleValide) {
								this.tablier.retourArriere("haut", i, j);
								nombreDeplacements++;
							}
						}
						
						if(!grilleValide && (this.tablier.deplacementValide("gauche", i,j))) {
							this.tablier.deplacerBille("gauche", i,j);
							nombreDeplacements++;
							solutions.put(billes, grilleTempo);
							grilleValide = this.resoudreSoloNoble(billes - 1);
							nombreAppelsResoudreSoloNoble++;
							if (!grilleValide) {
								this.tablier.retourArriere("gauche", i, j);
								nombreDeplacements++;
							}
						}
						
						if(!grilleValide && (this.tablier.deplacementValide("droite", i,j))) {
							this.tablier.deplacerBille("droite", i,j);
							nombreDeplacements++;
							solutions.put(billes, grilleTempo);
							grilleValide = this.resoudreSoloNoble(billes - 1);
							nombreAppelsResoudreSoloNoble++;
							if (!grilleValide) {
								this.tablier.retourArriere("droite", i, j);
								nombreDeplacements++;
							}
						}
						
						if(!grilleValide && (this.tablier.deplacementValide("bas", i,j))) {
							this.tablier.deplacerBille("bas", i,j);
							nombreDeplacements++;
							solutions.put(billes, grilleTempo);
							grilleValide = this.resoudreSoloNoble(billes - 1);
							nombreAppelsResoudreSoloNoble++;
							if (!grilleValide) {
								this.tablier.retourArriere("bas", i, j);
								nombreDeplacements++;
							}
						}
						//System.out.println(billes);
			
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
		double timeStart = System.currentTimeMillis();
		sn.resoudreSoloNoble(nbBilles);
		double timeEnd = System.currentTimeMillis();
		System.out.println("Temps d'exécution : " +((timeEnd-timeStart)/1000)+" secondes");
	}

	

	public Grille getTablier() {
		return tablier;
	}
}
