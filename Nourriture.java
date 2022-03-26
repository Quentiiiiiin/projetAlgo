import java.io.*; //image
import javax.imageio.*; //BufferedImage

/**
 * \class Nourriture : classe fille d'ElementMarin gérant les carburants
 */
public class Nourriture extends ElementMarin{
	
	
	//<!niveau du carburant déterminant le poucentage de carburant ajouté à son contact
	private int bonus;

	public Nourriture(int b, FenetreJeu f) throws IOException{
		
		super(f);
		bonus = b;
		
		//initialisation de l'image en fonction du bonus du carburant
		
		if(bonus==1){
			image = ImageIO.read(new File("images/Nourriture1.png"));
		}else if(bonus==2){
			image = ImageIO.read(new File("images/Nourriture2.png"));
		}
		if(bonus==3){
			image = ImageIO.read(new File("images/Nourriture3.png"));
		}
			
	}
	
	/**
   * \fn void action() : méthode ajoutant du carburant en fonction de la variable bonus et lançant la lecture du son du carburant
   */ 
	public void action() {
		f.AffJeu.affiche = false;
		if(bonus==1){
			f.pNourriture=f.pNourriture+15.0;
		}else if(bonus==2){
			f.pNourriture=f.pNourriture+25.0;
		}else if(bonus==3){
			f.pNourriture=f.pNourriture+35.0;
		}
		if(f.pNourriture>100){
			f.pNourriture=100;
		}
	}
}
