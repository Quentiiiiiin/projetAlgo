import java.io.*; //image
import java.awt.image.*; //BufferedImage

/**
 * \class Poisson : classe créant un poisson
 */
public class Poisson{
	
	//<!image du poisson
	BufferedImage image;
	
	//<!dimensions du poisson
	int hauteur, largeur;
	
	//<!point (x,y) désignant l'origine du Poisson
	APoint origine;
	
	//<!texte lié au Poisson
	String description;
	
	/**
   * \fn Poisson(BufferedImage i, String s) : constructeur Poisson
   * 
   * @param BufferedImage i : image du Poisson
   * @param String s : texte de description du Poisson
   */
	public Poisson(BufferedImage i, String s) throws IOException{
		description =s;
		image = i;
		hauteur = 100;
		largeur = 100;
		origine = new APoint(0,700);
	}
	
	/**
   * \fn void ChangePosition(int courantX, int courantY, int vitesseI, int angleI, double t, double viscosite) : méthode gérant l'actualisation de la position du poisson pour le déplacement de celui-ci
   * 
   * @param int courantX : courantX du niveau en cours
   * @param int courantY : courantY du niveau en cours
   * @param int vitesseI : vitesse initiale du Poisson
   * @param int angleI : angle initial du Poisson
   * @param double t : temps depuis le départ du Poisson
   * @param double viscosite : viscosité du milieu en cours
   */ 
	public void ChangePosition(int courantX, int courantY, int vitesseI, int angleI, double t, double viscosite){//méthode effectuant le changement de la position du poisson en fonction du temps et des paramètres du niveau en cours
				
        origine.x = 0*viscosite*courantX*Math.pow(t,2) + vitesseI * Math.cos (Math.toRadians(angleI))*t + 0;
        origine.y = viscosite*courantY*Math.pow(t,2) - vitesseI * Math.sin (Math.toRadians(angleI))*t + 700;
        
	}	
}
