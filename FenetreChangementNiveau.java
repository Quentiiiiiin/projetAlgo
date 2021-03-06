import javax.swing.*; //fenêtre
import java.awt.*; //couleur
import java.awt.event.*; //événements
import java.io.*; //image

/**
 * \class FenetreChangementNiveau : fenêtre gérant l'introduction d'un niveau par une animation
 */
public class FenetreChangementNiveau extends JFrame implements ActionListener{  //implements action listener implements ActionListener
		
	//<!fenêtre principale de jeu
	private FenetreJeu fenetre;
		
	//<!abscisse de la zone de texte
	private int x;
		
	//<!numéro du niveau en cours
	private int niveau;
		
	//<!timer permettant de relancer le niveau correctement 
	private Timer mt;
		
	
	/**
   * \fn FenetreChangementNiveau(int i, FenetreJeu f) : constructeur FenetreChangementNiveau
   * 
   * @param int i : numéro du niveau en cours
   * @param FenetreJeu f : objet désignant la fenêtre principale de jeu
   */
    public FenetreChangementNiveau (int i, FenetreJeu f) throws IOException{
		fenetre=f;
		
		//initialisation de la fenêtre de défaite
		
        this.setTitle("R\u00e9sultat");
		this.setSize(1000,500);
		this.setLocation(500,275);
		this.setResizable(false);
		this.setAlwaysOnTop(true);
		
		//initialisation + placement des éléments de la fenêtre de défaite
		niveau =f.numNiveau;
        mt = new Timer(1,this);
        
        x = 325;
        
		//ajout des éléments + affichage de la fenêtre de défaite
		this.setVisible(true);
		
		mt.start();
		
    }
    
    /**
   * \fn void paint(Graphics g) : méthode gérant l'affichage graphique de la fenêtre
   * 
   * @param Graphics g : objet graphique d'affichage
   */ 
    public void paint(Graphics g){
		g.setColor(new Color(255,0,0));
		g.fillRect(0,0,getWidth(),getHeight());
		g.setColor(Color.WHITE);
		g.setFont(new Font("Curlz MT",Font.BOLD,120));
		g.drawString("Niveau "+niveau,x,300);
	}

    
    /**
   * \fn void actionPerformed(ActionEvent e) : méthode permettant de gérer la partie graphique de compter le temps grâce au timer, de modifier l'abscisse du texte en conséquence afin de le faire bouger
   * 
   * @param ActionEvent e : événement associé
   */ 
    public void actionPerformed(ActionEvent e){
		if(isShowing()){
			if(x<=1000){ // Une condition à tout prix vérifiée
				fenetre.enJeu = false;
				fenetre.AffJeu.repaint();
			}
		}
	}
}
