import javax.swing.*; //fenêtre
import java.awt.*; //partie graphique
import java.awt.event.*; //événement
import java.io.*; //image
import javax.imageio.*; //ImageIO
import java.awt.image.*; 

/**
 * \class FenetreWin : fenêtre s'affichant en cas de victoire
 */
public class FenetreWin extends JFrame implements ActionListener{
	
	//<!fenêtre principale de jeu
    FenetreJeu fenetre;
    
    //<!image de l'eau propre
    BufferedImage eauPropre;
    
    //<!abscisse du poisson
    int x =0;
    
    //<!ordonnée du poisson
    int y=0;
    
    //<!timer pour les mouvements
    Timer time;
    
    //<!variable permettant de compter le temps qui passe
	double temps;

    
    /**
   * \fn FenetreWin(FenetreJeu f) : constructeur FenetreWin
   * 
   * @param FenetreJeu f : objet désignant la fenêtre principale de jeu
   */ 
    public FenetreWin (FenetreJeu f) throws IOException{
		
		//initialisation de la fenêtre
		
		fenetre=f;
        this.setTitle("Sortie de l'eau polluée");   //("Att\u00e9rissage sur Mars");
		this.setSize(500,500);
		this.setLocation(650,245);
		this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		//lancement du timer
		        
        time = new Timer(10,this);
        time.start();
    
        eauPropre = ImageIO.read(new File("IMAGE EAU PROPRE "));
        
        this.setVisible(true);  
    }
    
    /**
   * \fn void actionPerformed(ActionEvent e) : méthode permettant d'actualiser l'ordonnée et l'abscisse du poisson dans le temps
   * 
   * @param ActionEvent e : événement associé
   */
    public void actionPerformed (ActionEvent e){
        //descente vers le coin bas-droite de la fenêtre du poisson
        x=x+1;
        y=y+1;
        repaint();
        if(y==300){ //on s'arrête à un certain point
			time.stop();
			new FenetreFinale(fenetre); //création de la fenêtre finale
            this.setVisible(false);
        }
    }
    
    /**
   * \fn void paint(Graphics g) : méthode gérant l'affichage graphique de la fenêtre
   * 
   * @param Graphics g : objet graphique d'affichage
   */ 
    public void paint(Graphics g){
        g.drawImage(eauPropre,0,0,500,500,null);
        g.drawImage(fenetre.Nemo.image,x,y,100,100,null);
    }
   
    
}
