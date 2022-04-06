import javax.swing.*; //fenêtre
import java.awt.*; //graphics
import java.awt.event.*; //événements
import java.io.*; //image
import javax.imageio.*; //ImageIO
import java.awt.image.*; //BufferedImage

/**
 * \class RequinAnimation : fenêtre d'animation du contact requin/poisson
 */
public class RequinAnimation extends JFrame implements ActionListener{
    
    //<!fenêtre principale de jeu
    private FenetreJeu fenetre;
    
    //<!image du fond de la fenêtre
    private BufferedImage fond;
    
    //<!image de l'explosion
    private BufferedImage Explosion;
    
    //<!abscisse du poisson
    private int x =0;
    
    //<!timer pour les mouvements, indicateur de début et fin de niveau
    private Timer time;
    
    //<!variable permettant de compter le temps qui passe
	private double temps;

    /**
   * \fn MeteoriteAnimation(FenetreJeu f) : constructeur MeteoriteAnimation
   * 
   * @param FenetreJeu f : objet désignant la fenêtre principale de jeu
   */ 
    public RequinAnimation(FenetreJeu f) throws IOException{
		
		//initialisation de la fenêtre
		
        fenetre=f;
        this.setTitle("Choc Requin");
		this.setSize(1111,833);
		this.setLocation(606,152);
		this.setResizable(false);
		this.setAlwaysOnTop(true);
        
        //initialisation du timer
        
        time = new Timer(1,this);
        time.start();
        
        //initialisation des composants
        
        Explosion = ImageIO.read(new File("images/explosion.gif"));
        fond = ImageIO.read(new File("images/REQUIN2RUE.png"));
        this.setVisible(true);
    }
    
    /**
   * \fn void actionPerformed(ActionEvent e) : méthode permettant de gérer la partie graphique de compter le temps grâce au timer, de modifier l'abscisse du rover et gérant le son
   * 
   * @param ActionEvent e : événement associé
   */
    public void actionPerformed (ActionEvent e){
		if(isShowing()){ //si la fenêtre est affichée
			x=x+3;
			if(x==699){ //l'animation est terminée
				time.stop();
				this.setVisible(false);
				try{
					new FenetreChangementNiveau(fenetre.numNiveau,fenetre);
				}catch(IOException Exception){
				}
			}
		}else{
			time.stop();
			this.setVisible(false);
			try{
				new FenetreChangementNiveau(fenetre.numNiveau,fenetre);
			}catch(IOException Exception){
			}
		}
    }
    
    /**
   * \fn void paint(Graphics g) : méthode gérant l'affichage graphique de la fenêtre
   * 
   * @param Graphics g : objet graphique d'affichage
   */ 
    public void paint(Graphics g){
        g.drawImage(fond,0,0,1111,833,null);
        
        if(x<=400){ //dessin du poisson
        g.drawImage(fenetre.Nemo.image,x,350,200,200,null);
        repaint();
        }
        
        if(x>400){ //dessin de l'explosion quand le robot touche la météorite
            g.drawImage(Explosion,0,0,1111,833,null);
        }
    }
   
    
}

