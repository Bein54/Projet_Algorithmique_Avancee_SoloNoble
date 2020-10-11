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
		if (billes == 1) {
			this.ecrireSolution(1);
		}
		
		else {
			System.out.println("Etape n°" + billes);
			String[][] grilleActuelle = this.tablier.getGrille();
			
			Grille tablierActuel = this.tablier;
			
			boolean deplacementReussi = false;
			grilleValide = false;
			
			System.out.println("FinGrilleTrou :"+this.tablier.getFinGrille());
			this.tablier.chercherTrou();
			
			while ((!grilleValide) && !this.tablier.getFinGrille()) {
				
				deplacementReussi = this.tablier.deplacerBille();

				while (deplacementReussi && !grilleValide) {
					
					tablierActuel = this.tablier;
					
					grilleActuelle = this.tablier.getGrille();
					
					this.tablier = new Grille(grilleActuelle);
					
					grilleValide = this.resoudreSoloNoble(billes - 1);
					
					System.out.println("Fin de la grille : " + this.tablier.getFinGrille());
					
					System.out.println(grilleValide);
					
					if (!grilleValide) {
						
						if (this.tablier.getFinGrille()) {
							this.tablier.setFinGrille(false);
						}
						this.tablier = tablierActuel;
						
						deplacementReussi = this.tablier.deplacerBille();
						
					}
				}
				
				if (!grilleValide) {
					this.tablier.chercherTrou();
				}
			}
			
			if(grilleValide) {
				this.tablier = tablierActuel;
				this.ecrireSolution(billes);
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
