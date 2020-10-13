package programme;

import java.io.IOException;
import java.util.HashMap;

public class TestSoloNoble {
	private TestGrille tablier;
	private HashMap<Integer,String[][]> solutions;
	private int nombreDeplacements, nombreAppelsResoudreSoloNoble;
	
	public TestSoloNoble(String nomFichier) throws IOException {
		this.tablier = new TestGrille(nomFichier);
		this.solutions = new HashMap<Integer, String[][]>();
		this.nombreDeplacements = 0;
		this.nombreAppelsResoudreSoloNoble = 0;
		
		String s = "Bienvenue !\n";
		s += "Cet algorithme va tenter de résoudre le Solo Noble suivant :\n\n";
		
		for (int i = 0; i < tablier.getGrille().length; i++) {
			for (int j = 0; j < tablier.getGrille()[0].length; j++) {
				s += tablier.getGrille()[i][j];
			}
			s += "\n";
		}
		
		System.out.println(s);
	}
	
	public TestSoloNoble() throws IOException{
		
		
		this.tablier = new TestGrille();
		this.solutions = new HashMap<Integer, String[][]>();
		this.nombreDeplacements = 0;
		this.nombreAppelsResoudreSoloNoble = 0;
		
		String s = "Bienvenue !\n";
		s += "Cet algorithme va tenter de résoudre le Solo Noble suivant :\n\n";
		
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
						tablier.setDeplacement("debut");
						tablier.deplacerBille(i, j);
						nombreDeplacements++;
						
						while(!grilleValide && !this.tablier.getDeplacement().equals("fin")) {
							
							grilleValide = resoudreSoloNoble(billes - 1);
							System.out.println(grilleValide);
							if(!grilleValide) {
								tablier.retourArriere(i, j);
								nombreDeplacements++;
								
								tablier.deplacerBille(i, j);
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
			System.out.println("Etape n°" + (solutions.size() + 1 - i) + " :\n");
			
			for (int j = 0; j < elt.length; j++) {
				
				for (int k = 0; k < elt[0].length; k++) {
					
					System.out.print(elt[j][k]);
				}
				System.out.println();
			}
			System.out.println();
			System.out.println("-------");
		}
		
		System.out.println("Nombre de déplacements : " + nombreDeplacements);
		System.out.println("Nombre d'appels de la méthode resoudreSoloNoble() : " + nombreAppelsResoudreSoloNoble);
	}

	public static void main(String[] args) throws IOException {
		TestSoloNoble sn;
		if (args.length > 0)
			sn = new TestSoloNoble(args[0]);
		
		else 
			sn = new TestSoloNoble();
		
		double debut = System.currentTimeMillis();
		boolean resolution = sn.resoudreSoloNoble(sn.getTablier().calculerNombreBilles());
		double fin = System.currentTimeMillis();
		if(!resolution)
			System.out.println("Grille impossible !");
		
		
		System.out.println("Temps de recherche : " + ((fin - debut) / 1000) + "s");
	}

	

	public TestGrille getTablier() {
		return tablier;
	}
}
