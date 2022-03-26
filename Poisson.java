import java.io.*; //image
import java.awt.image.*; //BufferedImage

/**
 * \class Poisson : classe créant un rover
 */
public class Poisson{
	
	//<!image du rover
	BufferedImage image;
	
	//<!dimensions du rover
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
		origine = new APoint(0,833-100);
	}
	
	/**
   * \fn void ChangePosition(int vent, int vitesseI, int angleI, double t, double g) : méthode gérant l'actualisation de la position du poisson pour le déplacement de celui-ci
   * 
   * @param int courant : courant du niveau en cours
   * @param int vitesseI : vitesse initiale du Poisson
   * @param int angleI : angle initial du Poisson
   * @param double t : temps depuis le départ du Poisson
   * @param double viscosite : viscosité du milieu en cours
   */ 
	public void ChangePosition(int courant, int vitesseI, int angleI, double t, double viscosite){//méthode effectuant le changement de la position du rover en fonction du temps et des paramètres du niveau en cours
				
		origine.x = 0.25*vent*Math.pow(t,2)+vitesseI*Math.cos(Math.toRadians(angleI))*t;
		origine.y = g*Math.pow(t,2)-vitesseI*Math.sin(Math.toRadians(angleI))*t+833-hauteur;
	}	
}
