package programme;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Set;
import java.util.TreeMap;



public class SoloNoble {
	
	private Grille tablier;
	private HashMap<Integer,String[][]> solutions;
	private int nombreDeplacements, nombreAppelsResoudreSoloNoble;
	
	public SoloNoble(String nomFichier) throws IOException {
		this.tablier = new Grille(nomFichier);
		this.solutions = new HashMap<Integer, String[][]>();
		this.nombreDeplacements = 0;
		this.nombreAppelsResoudreSoloNoble = 0;
		
		String s = "Bienvenue !\n";
		s += "Cet algorithme va tenter de r�soudre le Solo Noble suivant :\n\n";
		
		for (int i = 0; i < tablier.getGrille().length; i++) {
			for (int j = 0; j < tablier.getGrille()[0].length; j++) {
				s += tablier.getGrille()[i][j];
			}
			s += "\n";
		}
		
		System.out.println(s);
	}
	
	public SoloNoble() throws IOException{
		
		
		this.tablier = new Grille();
		this.solutions = new HashMap<Integer, String[][]>();
		this.nombreDeplacements = 0;
		this.nombreAppelsResoudreSoloNoble = 0;
		
		String s = "Bienvenue !\n";
		s += "Cet algorithme va tenter de r�soudre le Solo Noble suivant :\n\n";
		
		for (int i = 0; i < tablier.getGrille().length; i++) {
			for (int j = 0; j < tablier.getGrille()[0].length; j++) {
				s += tablier.getGrille()[i][j];
			}
			s += "\n";
		}
		
		System.out.println(s);
	}

	public boolean resoudreSoloNoble(int billes) throws IOException {
		boolean grilleValide = false;
		nombreAppelsResoudreSoloNoble++;
		
		String[][] grilleTempo = new String[this.tablier.getNbLignes()][this.tablier.getNbColonnes()];
		for (int i = 0; i < this.tablier.getGrille().length; i++) {
			for (int j = 0; j < this.tablier.getGrille()[0].length; j++) {
				grilleTempo[i][j] = this.tablier.getGrille()[i][j];
			}
		}
		
		solutions.put(billes, grilleTempo);
		
		if (billes == 1) {
			grilleValide = true;
			ecrireToutesSolutions();
		}
		
		else {
			int i = 0;
			int j = 0;
			grilleValide = false;
			
			while (!grilleValide && i < tablier.getNbLignes()) {
				
				j = 0;
				while (!grilleValide && j < tablier.getNbColonnes()) {
					
					if (tablier.getGrille()[i][j].equals(".")) {
						
						
						if(tablier.deplacementValide("droite", i,j)) {
							
							tablier.deplacerBille("droite", i,j);
							nombreDeplacements++;
							
							grilleValide = resoudreSoloNoble(billes - 1);
							
							if (!grilleValide) {
								
								tablier.retourArriere("droite", i, j);
								nombreDeplacements++;
							}
						}
						
						if(!grilleValide && tablier.deplacementValide("bas", i,j)) {
							
							tablier.deplacerBille("bas", i,j);
							nombreDeplacements++;
							
							grilleValide = resoudreSoloNoble(billes - 1);
							
							if (!grilleValide) {
								
								tablier.retourArriere("bas", i, j);
								nombreDeplacements++;
							}
						}
						
						if(!grilleValide && tablier.deplacementValide("haut", i,j)) {
							
							tablier.deplacerBille("haut", i,j);
							nombreDeplacements++;
							
							grilleValide = resoudreSoloNoble(billes - 1);
							
							if (!grilleValide) {
								
								tablier.retourArriere("haut", i, j);
								nombreDeplacements++;
							}
						}
						
						if(!grilleValide && tablier.deplacementValide("gauche", i,j)) {
							
							tablier.deplacerBille("gauche", i,j);
							nombreDeplacements++;
							
							grilleValide = resoudreSoloNoble(billes - 1);
							
							if (!grilleValide) {
								
								tablier.retourArriere("gauche", i, j);
								nombreDeplacements++;
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
	
	public void ecrireToutesSolutions() {
		for(int i = solutions.size(); i > 0; i--) {
			String [][] elt = solutions.get(i);
			
			System.out.println();
			System.out.println("Etape n�" + (solutions.size() + 1 - i) + " :\n");
			
			for (int j = 0; j < elt.length; j++) {
				
				for (int k = 0; k < elt[0].length; k++) {
					
					System.out.print(elt[j][k]);
				}
				System.out.println();
			}
			System.out.println();
			System.out.println("-------");
		}
		
		System.out.println("Nombre de d�placements : " + nombreDeplacements);
		System.out.println("Nombre d'appels de la m�thode resoudreSoloNoble() : " + nombreAppelsResoudreSoloNoble);
	}

	public static void main(String[] args) throws IOException {
		SoloNoble sn;
		if (args.length > 0)
			sn = new SoloNoble(args[0]);
		
		else 
			sn = new SoloNoble();
		
		double debut = System.currentTimeMillis();
		boolean resolution = sn.resoudreSoloNoble(sn.getTablier().calculerNombreBilles());
		double fin = System.currentTimeMillis();
		if(!resolution)
			System.out.println("Grille impossible !");
		
		
		System.out.println("Temps de recherche : " + ((fin - debut) / 1000) + "s");
	}

	

	public Grille getTablier() {
		return tablier;
	}
}
