package programme;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
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
			String[][] grilleDeBase = this.tablier.getGrille();
			String[][] grilleActuelle = this.tablier.getGrille();
			
			Grille tablierDeBase = this.tablier;
			Grille tablierActuel = this.tablier;
			
			boolean deplacementReussi = false;
			grilleValide = false;
			
			this.tablier.chercherTrou();
			Grille tablierTemporaire = this.tablier;
			
			while ((!grilleValide) && !this.tablier.getFinGrille()) {
				
				deplacementReussi = this.tablier.deplacerBille();
				
				//System.out.println(deplacementReussi);
				
				grilleActuelle = this.tablier.getGrille();
				
				/*for (int i = 0; i < grilleActuelle.length; i++) {
					for (int j = 0; j < grilleActuelle[i].length; j++) {
						System.out.print(grilleActuelle[i][j]);
					}
					System.out.println();
				}*/
				
				

				while (deplacementReussi && !grilleValide) {
					
					tablierActuel = this.tablier;
					
					this.tablier = new Grille(grilleActuelle);
					
					grilleValide = this.resoudreSoloNoble(billes - 1);
					
					System.out.println("Fin de la grille : " + this.tablier.getFinGrille());
					
					//System.out.println(grilleValide);
					
					if (!grilleValide) {
						this.tablier = tablierActuel;
						deplacementReussi = this.tablier.deplacerBille();
						//System.out.println("TAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA");
						
						grilleActuelle = this.tablier.getGrille();
						
					}
				}
				
				if (!grilleValide) {
					this.tablier = tablierTemporaire;
					this.tablier.chercherTrou();
					tablierTemporaire = this.tablier;
				}
			}
			System.out.println(grilleValide);
			System.out.println(this.tablier.getFinGrille());
			
			if(grilleValide)
				this.ecrireSolution(billes);
			else
				this.tablier = tablierDeBase;
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
