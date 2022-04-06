import java.io.*; //image
import javax.imageio.*; //BufferedImage

/**
 * \class Meteorites : classe fille d'ElementMarin gérant les requins
 */
public class Requin extends ElementMarin{
	

	
	/**
   * \fn Requin(FenetreJeu f) : constructeur Requin
   * 
   * @param FenetreJeu f : objet désignant la fenêtre principale de jeu
   */
	public Requin(FenetreJeu f) throws IOException{ 
		
		super(f);
		image = ImageIO.read(new File("images/Requin.png"));
	}
	
	/**
   * \fn void action() : méthode gérant le contact entre le requin et nemo
   */
	public void action(){
		f.toucheRequin = true;  //le booléen gérant le contact requin dans la fenêtre jeu devient true
		
		if(f.pNourriture>15.0){
			try{
				new RequinAnimation(f); //lancement de l'animation météorite
			}catch(IOException Exception){
			}
		}
	}
}
