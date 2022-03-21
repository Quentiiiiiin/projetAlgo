import javax.swing.*; //fenêtre
import java.awt.*; //couleur
import java.awt.event.*; //événement
import java.io.*; //image

/**
 * \class FenetreFinale : dernière fenêtre du jeu
 */
public class FenetreFinale extends JFrame implements MouseListener, ActionListener{
	
	//<!fenêtre principale de jeu
	//private FenetreJeu fenetre;
	
	//<!zone où seront affichées les infos sur les poissons
	private JLabel Info;
	
	//<!bouton restart
	private JButton Restart;
	
	/**
   * \fn FenetreFinale(FenetreJeu f) : constructeur FenetreFinale
   * 
   * @param FenetreJeu f : objet désignant la fenêtre principale de jeu
   */
    public FenetreFinale (/*FenetreJeu f*/) {
				
		//initialisation de la fenêtre
		
		//fenetre=f;
        this.setTitle("Att\u00e9rissage sur Mars");
		this.setSize(1000,1000);
		this.setLocation(400,20);
		this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        //initialisation des composants
        
        JPanel Fond = new JPanel();
        Fond.setLayout(null);
        Fond.setBounds(0,0,1000,1000);
        Fond.setBackground(new Color(52,62,162)); //changer couleur 
        
        JLabel Gif = new JLabel();
        Gif.setBounds(0,0,1000,1000);
        Gif.setBackground(new Color(52,62,162)); //changer couleur 
        Fond.add(Gif);
        
        JLabel I1 = new JLabel(new ImageIcon("images/photo_mars.png"));
        I1.setBounds(50,50,600,500);
        Gif.add(I1);
        
        JLabel I2 = new JLabel(new ImageIcon("images/mars.gif"));
        I2.setBounds(580,50,500,500);
        Gif.add(I2);
        
        Info = new JLabel("+ D'INFOS");
        Info.setBounds(700,520,150,50);
        Info.setBackground(new Color(52,62,162)); //changer couleur 
        Info.setForeground(new Color(249,200,93)); //changer couleur 
        Info.setFont(new Font("Agency FB",Font.BOLD,35));
        Info.addMouseListener(this);
        Gif.add(Info);
        
        Restart = new JButton("Restart");
        Restart.setBounds(730,30,200,75);
        Restart.setFont(new Font("Agency FB",Font.BOLD,35));
        Restart.setForeground(new Color(52,62,162)); //changer couleur 
        Restart.setBackground(new Color(249,200,93)); //changer couleur 
        Gif.add(Restart);
        
        JPanel cadreJaune = new JPanel();
        cadreJaune.setLayout(null);
        cadreJaune.setBounds(40,40,620,520);
        cadreJaune.setBackground(new Color(249,200,93)); //changer couleur 
        Gif.add(cadreJaune);
        
        String T = readFile("description.txt");
        JTextArea Texte = new JTextArea(T);
        Texte.setBackground(new Color(249,200,93)); //changer couleur 
        Texte.setForeground(new Color(52,62,162)); //changer couleur 
        Texte.setBounds(45,610,900,330);
        Texte.setFont(new Font("Agency FB",Font.BOLD,30));
        Texte.setEditable(false);
        Gif.add(Texte);
        
        
        JPanel fondTexte2 = new JPanel();
        fondTexte2.setLayout(null);
        fondTexte2.setBounds(45,610,900,330);
        fondTexte2.setBackground(new Color(249,200,93)); //changer couleur 
        Gif.add(fondTexte2);
        
        
        JPanel fondTexte = new JPanel();
        fondTexte.setLayout(null);
        fondTexte.setBounds(35,600,920,350);
        fondTexte.setBackground(new Color(249,200,93)); //changer couleur 
        Gif.add(fondTexte);
        
        
        this.add(Fond);
        
        this.setVisible(true);      
        Restart.addActionListener(this);
        
    }
    
    /**
   * \fn void actionPerformed(ActionEvent e) : méthode permettant de gérer l'arrêt/la lecture du son et le recommencement du jeu en fonction de l'appui de l'utilisateur sur les boutons
   * 
   * @param ActionEvent e : événement associé
   */ 
    public void actionPerformed(ActionEvent e){
		if(e.getSource()==Restart){
			//fenetre.restart();
			//restart.lecture();
			setVisible(false);
        }
    }
    
	/**
   * \fn void mouseEntered(MouseEvent e) : méthode permettant d'afficher la fenêtre d'infos supplémentaires si l'utilisateur passe sa souris sur le bouton Info
   * 
   * @param MouseEvent e : événement associé
   */
	public void mouseEntered(MouseEvent e){
		if(e.getSource() == Info){
			//new FenetreInfo(); //création de la fenêtre info
		}
	}
	
	/**
   * \fn static String readFile(String chemin) : méthode permettant de convertir des fichiers texte en String en conservant la présentation du texte
   * 
   * @param String chemin : localisation du fichier à convertir
   */ 
    public static String readFile(String chemin){
        try{
            InputStream flux= new FileInputStream(chemin);
            InputStreamReader lecture= new InputStreamReader(flux,"UTF-8");
            try (BufferedReader buff = new BufferedReader(lecture)) {
                String ligne = "";
                String contenu = "";
                while ((ligne = buff.readLine()) != null){
                    contenu += ligne + "\n";
                }
                return contenu;
            } catch (Exception e) {
                System.out.println(e.toString());
            }
       } catch (IOException e){
            System.out.println(e.toString());
       }
       return null;
    } 
    
    /**
   * autres méthodes devant être implémentées avec le MouseListener
   * 
   * @param MouseEvent e : événement associé
   */
	public void mouseClicked(MouseEvent e) {}
	public void mousePressed(MouseEvent e) {}
	public void mouseReleased(MouseEvent e) {}
	public void mouseExited(MouseEvent e) {}
    
    
}
