import javax.swing.*; //fenêtre
import java.awt.*; //couleur
import java.awt.event.*; //événements
import java.io.*; //image

/**
 * \class FenetreLose : fenêtre s'affichant en cas de game over
 */
public class FenetreLose extends JFrame implements ActionListener{ 
	
	//<!bouton restart
	private JButton Restart;
	
	//<!fenêtre principale de jeu
	private FenetreJeu f;
	
	/**
   * \fn FenetreLose(FenetreJeu f) : constructeur FenetreLose
   * 
   * @param FenetreJeu f : objet désignant la fenêtre principale de jeu
   */ 
    public FenetreLose (FenetreJeu f) throws IOException{
		
		//création de la fenêtre de défaite
		
        this.setTitle("R\u00e9sultat");  //("Résultat");
		this.setSize(600,400);
		this.setLocation(450,400);
		this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		this.f = f;
		
		//initialisation + placement des éléments de la fenêtre de défaite
		
        
        JPanel Fond = new JPanel();
        Fond.setLayout(null);
        Fond.setBounds(0,0,600,400);
        
        JLabel Phrase = new JLabel();
		Phrase.setLayout(null);
		Phrase.setSize(400,50);
		Phrase.setLocation(150,20);
		Phrase.setFont(new Font("Arial",Font.BOLD,40));
		Phrase.setForeground(Color.white);
		
        JLabel Gif = new JLabel();
        Gif.setSize(250,150);
        Gif.setLocation(175,100);
        
        Restart = new JButton("Restart");
        Restart.setBounds(250,300,100,50);
        Restart.setBackground(Color.white);
        
        //ajout d'un écouteur sur le bouton restart pour l'IHM
        
        Restart.addActionListener(this);
			
		//ajout des éléments + affichage de la fenêtre de défaite
		
		Phrase.setText("T'as tué Nemo super sympa de ta part");
		Fond.setBackground(new Color(236,134,131));
		Gif.setIcon(new ImageIcon("GIF POISSON QUI REMONTE A LA SURFACE"));  //image
		Fond.add(Phrase);
		Fond.add(Restart);
		Fond.add(Gif);
		this.add(Fond);
		this.setVisible(true);
    }
    
    /**
   * \fn void actionPerformed(ActionEvent e) : méthode permettant de recommencer le jeu si l'utilisateur appuie sur le bouton restart
   * 
   * @param ActionEvent e : événement associé
   */
    public void actionPerformed(ActionEvent e){
		//la fenêtre de défaite se ferme, le jeu redémarre
		f.restart();
		this.setVisible(false);
	}
    
}
