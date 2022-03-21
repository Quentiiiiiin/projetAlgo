import javax.swing.*; //fenêtre
import java.awt.*; //couleur
import java.awt.event.*; //événements
import java.io.*; //images

/**
 * \class FenetreStart : fenêtre d'introduction expliquant les règles, le contexte et le but du jeu
 */

public class FenetreStart extends JFrame implements ActionListener{
	
	//bouton start
	private JButton start1;
	
	
    //FenetreStart() : constructeur FenetreStart

	public FenetreStart(){
		
		//initialisation de la fenêtre
		
		this.setTitle("Nemo: le jeu");
		this.setSize(1200,1000);
		this.setLocation(400,20);
		this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		//création des éléments de la page d'accueil
		
		start1= new JButton("START");
		start1.setBounds(450,800,300,100);
		start1.setBackground(new Color(206,206,206));
		start1.setFont(new Font("Agency FB",Font.BOLD,50));
		start1.setForeground(new Color(0,0,255));  
		start1.addActionListener(this);
		
		JLabel ombre = new JLabel();
		ombre.setLayout(null);
		ombre.setSize(1200,200);
		ombre.setLocation(106,0);
		ombre.setText("Le Jeu Nemo");
		ombre.setFont(new Font("Agency FB",Font.BOLD,180));
		ombre.setForeground(Color.black);
		ombre.setBackground(new Color(0,0,255));  
		
		JLabel txt = new JLabel();
		txt.setLayout(null);
		txt.setSize(1200,200);
		txt.setLocation(100,0);
		txt.setText("Le Jeu Nemo");
		txt.setFont(new Font("Agency FB",Font.BOLD,180));
		txt.setForeground(new Color(0,0,255));  
		txt.setBackground(new Color(0,0,0)); 
		
		String T = readFile("debut.txt");
        JTextArea Texte = new JTextArea(T);
        Texte.setBackground(new Color(0,127,255)); 
        Texte.setForeground(new Color(206,206,206));
        Texte.setBounds(50,200,1200,600);
        Texte.setFont(new Font("Agency FB",Font.BOLD,25));
        Texte.setEditable(false);
             
        
        JPanel panneauGlobal = new JPanel();
		panneauGlobal.setLayout(null);
		panneauGlobal.setBounds(0,0,1000,1000);
		panneauGlobal.setBackground(new Color(0,127,255)); 
		panneauGlobal.add(start1);
		panneauGlobal.add(txt);
		panneauGlobal.add(Texte);
		panneauGlobal.add(ombre);
		
		
		this.add(panneauGlobal);
		this.setVisible(true);
		
	}
	
	/**
   * \fn void actionPerformed(ActionEvent e) : méthode permettant de lancer le jeu si
   * l'utilisateur appuie sur start
   * 
   * @param ActionEvent e : événement associé
   */ 
	 public void actionPerformed (ActionEvent e){
		 
		if(e.getSource()==start1){ //lancement du jeu
			
			   setVisible(false); //fermeture de la fenêtre start
			   //new FenetreChoix(audioOn); //création de la fenêtre choix
	   }
	 }
	 
	/**
   * \fn static String readFile(String chemin) : méthode permettant de convertir des fichiers texte en String en conservant la présentation du texte
   * 
   * @param String chemin : localisation du fichier à convertir
   */ 
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
		
