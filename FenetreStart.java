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
		start1.setBounds(450,880,300,60);
		start1.setBackground(new Color(206,206,206));
		start1.setFont(new Font("Juice ITC",Font.BOLD,50));
		start1.setForeground(new Color(255,128,0));  
		start1.addActionListener(this);
		
		JLabel ombre = new JLabel();
		ombre.setLayout(null);
		ombre.setSize(1200,150);
		ombre.setLocation(246,0);
		ombre.setText("Nemo : Le Jeu");
		ombre.setFont(new Font("Juice ITC",Font.BOLD,140));
		ombre.setForeground(Color.black);
		ombre.setBackground(new Color(0,0,255));  
		
		JLabel txt = new JLabel();
		txt.setLayout(null);
		txt.setSize(1200,150);
		txt.setLocation(240,0);
		txt.setText("Nemo : Le Jeu");
		txt.setFont(new Font("Juice ITC",Font.BOLD,140));
		txt.setForeground(Color.white);  
		txt.setBackground(new Color(0,0,0)); 
		
		String T = readFile("texte/debut.txt");
        JTextArea Texte = new JTextArea(T);
        Texte.setBackground(new Color(255,128,0)); 
        Texte.setForeground(new Color(0,0,0));
        Texte.setBounds(30,190,1200,700);
        Texte.setFont(new Font("Juice ITC",Font.BOLD,23));
        Texte.setEditable(false);
             
        
        JPanel panneauGlobal = new JPanel();
		panneauGlobal.setLayout(null);
		panneauGlobal.setBounds(0,0,1000,1000);
		panneauGlobal.setBackground(new Color(255,128,0)); 
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
			try{
			   setVisible(false); //fermeture de la fenêtre start
			   new FenetreChoix(); //création de la fenêtre choix
		   }catch(IOException exception){
		   }
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
		
