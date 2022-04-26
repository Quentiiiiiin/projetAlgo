import javax.swing.*; //fenêtre
import java.awt.*; //couleur
import java.awt.event.*; //événements
import java.io.*; 
import javax.imageio.*; 

/**
 * \class FenetreChoix : fenêtre permettant de choisir le poisson avec lequel l'utilisateur veut jouer  
 */ 

public class FenetreChoix extends JFrame implements ActionListener{ //fenêtre du choix du poisson
    
    //<!Poisson Florent
    private Poisson Florent;
    
    //<!Poisson Louis
    private Poisson Louis;
    
    //<!Poisson Quentin
    private Poisson Quentin;
    
    //<!Poisson Tequel
    private Poisson Tequel; 
    
    //<!Poisson Dory : le premier pour tester ??
    private Poisson Dory;
    
    //<!bouton de choix sur lequel l'utilisateur appuie pour commencer le jeu  
    private JButton Choisir;
    
    //<!bouton sélection du poisson florent
    private JButton Floflo;
    
    //<!bouton sélection du poisson louis
    private JButton Loulou;
    
    //<!bouton sélection du poisson quentin
    private JButton Qq;
    
    //<!bouton sélection du poisson tequel
    private JButton Teqteq;
    
    //<!bouton sélection du poisson Dory
    private JButton Dodo;
    
    //<!zone de fond bleu à droite de la fenêtre
    private JPanel Fond2;
    
    //<!zone de texte où l'on va afficher la description de chaque poisson
    private JTextArea Texte;
    
    //<!poisson choisi
    private Poisson poissonChoisi;
    
    /**
   * \fn FenetreChoix() : constructeur FenetreChoix
   */ 
    public FenetreChoix () throws IOException{
		
        //initialisation de la fenêtre
        
        this.setTitle("Choix de ton poisson");
		this.setSize(1000,1000);
		this.setLocation(400,20);
		this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		//initialisation des composants
        
        JPanel Fond = new JPanel();
        Fond.setLayout(null);
        Fond.setBounds(0,0,1000,1000);
        Fond.setBackground(Color.white);
        this.add(Fond);        
        
        JLabel Titre = new JLabel ("SELECTION DU POISSON EN DANGER:");
        Titre.setLayout(null);
        Titre.setBounds(10,0,400,100);
        Titre.setFont(new Font("Agency FB",Font.BOLD,25));
        Titre.setForeground(new Color(0,0,255));
        Fond.add(Titre);
        
        JLabel NomJeu = new JLabel ("NEMO");
        NomJeu.setLayout(null);
        NomJeu.setBounds(730,10,270,100);
        NomJeu.setFont(new Font("Agency FB",Font.BOLD,45));
        NomJeu.setForeground(new Color(206,206,206));
        Fond.add(NomJeu);
       
        JLabel A = new JLabel(new ImageIcon("images/Nemo.jpg"));
        A.setBounds(100,90,200,200);
        Fond.add(A);
        
        JLabel B = new JLabel(new ImageIcon("images/Crab.jpg"));
        B.setBounds(400,90,200,200);
        Fond.add(B);
        
        JLabel C = new JLabel(new ImageIcon("images/Deb.jpg"));
        C.setBounds(100,390,200,200);
        Fond.add(C);
        
        JLabel D = new JLabel(new ImageIcon("images/Rai.jpg"));
        D.setBounds(400,390,200,200);
        Fond.add(D);
        
        JLabel E = new JLabel(new ImageIcon("images/Dory.jpg"));
        E.setBounds(250,690,200,200);
        Fond.add(E);
        
        Floflo = new JButton("Nemo");
		Floflo.setLayout(null);
		Floflo.setBounds(100,305,200,50);
		Floflo.setBackground(new Color(219,190,242));
        Floflo.addActionListener(this); 
        Floflo.setFont(new Font("Agency FB",Font.BOLD,25));
        Fond.add(Floflo);
        
        Loulou = new JButton("Jacques");
		Loulou.setLayout(null);
		Loulou.setBounds(400,305,200,50);
		Loulou.setBackground(new Color(242,190,214));
        Loulou.addActionListener(this);
        Loulou.setFont(new Font("Agency FB",Font.BOLD,25)); 
        Fond.add(Loulou);
        
        Qq= new JButton("Monsieur Rai");
		Qq.setLayout(null);
		Qq.setBounds(400,605,200,50);
		Qq.setBackground(new Color(251,236,225));
		Qq.setFont(new Font("Agency FB",Font.BOLD,25));
        Qq.addActionListener(this); 
        Fond.add(Qq);
        
        Teqteq = new JButton("Deb");
		Teqteq.setLayout(null);
		Teqteq.setBounds(100,605,200,50);
		Teqteq.setBackground(new Color(180,207,250));
        Teqteq.addActionListener(this);
        Teqteq.setFont(new Font("Agency FB",Font.BOLD,25)); 
        Fond.add(Teqteq);
        
        Dodo = new JButton("Dory");
		Dodo.setLayout(null);
		Dodo.setBounds(250,905,200,50);
	    Dodo.setBackground(new Color(247,198,197));
        Dodo.addActionListener(this); 
        Dodo.setFont(new Font("Agency FB",Font.BOLD,25));
        Fond.add(Dodo);
        
        Choisir = new JButton("CHOISIR");
		Choisir.setLayout(null);
		Choisir.setBounds(725,800,250,100);
		Choisir.setBackground(new Color(206,206,206));
		Choisir.setForeground(new Color(0,0,255));
        Choisir.addActionListener(this); 
        Choisir.setFont(new Font("Agency FB",Font.BOLD,35));
        Fond.add(Choisir);
        
        Texte = new JTextArea();
        Texte.setEditable(false);
        Texte.setLayout(null);
        Texte.setBackground(new Color(0,0,255));
        Texte.setForeground(new Color(206,206,206));
        Texte.setFont(new Font("Agency FB",Font.BOLD,25));
        Texte.setBounds(725,300,300,400);
        Fond.add(Texte);
        
        Fond2 = new JPanel();
        Fond2.setLayout(null);
        Fond2.setBounds(700,0,300,1000);
        Fond2.setBackground(new Color(0,0,255));
        Fond.add(Fond2);

        
		//initialisation des poissons
        
        Florent = new Poisson(ImageIO.read(new File("images/nemo.png")),"texte/Nemo.txt");
        Louis = new Poisson(ImageIO.read(new File("images/crab.png")),"texte/Jacques.txt");
        Quentin = new Poisson(ImageIO.read(new File("images/rai.png")),"texte/MonsieurRai.txt");
        Tequel = new Poisson(ImageIO.read(new File("images/deb.png")),"texte/Deb.txt");
        Dory = new Poisson(ImageIO.read(new File("images/dory.png")),"texte/Dory.txt");
              
        this.setVisible(true);
    
    }

    /**
   * \fn void actionPerformed(ActionEvent e) : méthode permettant de lancer le jeu si
   * l'utilisateur appuie sur le bouton de choix 
   * 
   * @param ActionEvent e : événement associé
   */ 
    public void actionPerformed (ActionEvent e){ 
		

	//le poisson sélectionné est enregistré

        if (e.getSource() == Floflo){
            Texte.repaint();
            Texte.setText(readFile(Florent.description));
            poissonChoisi=Florent;
            
        }
        if (e.getSource() == Loulou){
            Texte.repaint();
            Texte.setText(readFile(Louis.description));
            poissonChoisi=Louis;
        }
        if (e.getSource() == Qq){
            Texte.repaint();
            Texte.setText(readFile(Quentin.description));
            poissonChoisi=Quentin;
        }
        if (e.getSource() == Teqteq){
            Texte.repaint();
            Texte.setText(readFile(Tequel.description));
            poissonChoisi=Tequel;
        }
        if (e.getSource() == Dodo){
            Texte.repaint();
            Texte.setText(readFile(Dory.description));
            poissonChoisi=Dory;
        }
        
	//appui sur le bouton choisir
        
        if(e.getSource()==Choisir){
            if(poissonChoisi!=null){ //si l'utilisateur a choisi un poisson
				try{
					new FenetreJeu(poissonChoisi); //création de la fenêtre principale de jeu
					setVisible(false);
				}catch(IOException exception){
				}
			}else{ //si l'utilisateur n'a choisi aucun poisson
				JOptionPane.showMessageDialog(this,"Veuillez s\u00e9lectionner un poisson !"); //message d'erreur
			}
        }
        }
        /**
   * \fn static String readFile(String chemin) : méthode permettant de convertir des fichiers texte en String en conservant la présentation du texte
   * 
//    * @param String chemin : localisation du fichier à convertir
//    */ 
	 public static String readFile(String chemin) {
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
 }




