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

public class SoloNoble {
	
	private Grille tablier;
	private boolean finJeu;
	
	public SoloNoble(File f) throws IOException {
		this.tablier = new Grille(f);
		this.finJeu = false;
	}
	
	public SoloNoble() throws IOException {
		this.tablier = new Grille();
		this.finJeu = false;
	}
	
	
	public boolean resoudreSoloNoble(int billes) throws IOException {
		boolean resultat = true;
		File test = new File("test.txt");
		if (billes == 1) {
			this.finJeu = true;
			this.tablier.ecrireSolution();
		}
		else {
			String[][] grilleActuelle = this.tablier.getGrille();
			boolean valide = false;
			while ((!valide) && !this.tablier.getFinGrille()) {
				this.tablier.chercherTrou();
				
				if (!this.tablier.getFinGrille()) {
					while (tablier.getNumeroCas() != 4) {
						String[][] nouvelleGrille = this.tablier.deplacerBille();
					}
					this.tablier = new Grille(test);
					valide = this.resoudreSoloNoble(billes -1);
				}
				
				else {
					resultat = false;
				}
			}
			
		}
		return resultat;
	}


	public static void main(String[] args) throws IOException {
		SoloNoble sn;
		if (args[0] != null)
			sn = new SoloNoble(new File(args[0]));
		else {
			sn = new SoloNoble();
		}
		int nbBilles = sn.getTablier().calculerNbBilles();
		sn.resoudreSoloNoble(nbBilles);
	}

	public Grille getTablier() {
		return tablier;
	}

	public boolean isFinJeu() {
		return finJeu;
	}
}
