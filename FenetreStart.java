import javax.swing.*; //fenêtre
import java.awt.*; //couleur
import java.awt.event.*; //événements
import java.io.*; //images

/**
 * \class FenetreStart : fenêtre d'introduction expliquant les règles, le contexte et le but du jeu
 */

public class FenetreStart extends JFrame implements ActionListener{
	
	//<!bouton start
	private JButton start1;
	
	//<!bouton permettant de couper le son
	private JButton Son;
	
	
	
	/**
   * \fn FenetreStart() : constructeur FenetreStart
   */ 
	public FenetreStart(){
		
		//initialisation de la fenêtre
		
		this.setTitle("Poisson: le jeu");
		this.setSize(1200,1000);
		this.setLocation(400,20);
		this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		//création des éléments de la page d'accueil
		
		
		start1= new JButton("START");
		start1.setBounds(450,800,300,100);
		start1.setBackground(new Color(249,200,0));
		start1.setFont(new Font("Agency FB",Font.BOLD,50));
		start1.setForeground(new Color(52,62,162));
		start1.addActionListener(this);
		
		JLabel ombre = new JLabel();
		ombre.setLayout(null);
		ombre.setSize(1200,200);
		ombre.setLocation(106,0);
		ombre.setText("Poisson");
		ombre.setFont(new Font("Agency FB",Font.BOLD,180));
		ombre.setForeground(Color.black);
		ombre.setBackground(new Color(200,62,162));
		
		JLabel txt = new JLabel();
		txt.setLayout(null);
		txt.setSize(1200,200);
		txt.setLocation(100,0);
		txt.setText("Poisson");
		txt.setFont(new Font("Agency FB",Font.BOLD,180));
		txt.setForeground(new Color(249,200,93));
		txt.setBackground(new Color(52,62,162));
		
		String T = readFile("texte/debut.txt");
        JTextArea Texte = new JTextArea(T);
        Texte.setBackground(new Color(52,0,0));
        Texte.setForeground(Color.white);
        Texte.setBounds(50,200,1200,600);
        Texte.setFont(new Font("Agency FB",Font.BOLD,25));
        Texte.setEditable(false);
             
        
        JPanel panneauGlobal = new JPanel();
		panneauGlobal.setLayout(null);
		panneauGlobal.setBounds(0,0,1000,1000);
		panneauGlobal.setBackground(new Color(52,62,50));
		panneauGlobal.add(start1);
		panneauGlobal.add(txt);
		panneauGlobal.add(Texte);
		panneauGlobal.add(ombre);
		
		
		this.add(panneauGlobal);
		this.setVisible(true);
		
	}
	
	/**
   * \fn void actionPerformed(ActionEvent e) : méthode permettant de lancer le jeu si
   * l'utilisateur appuie sur start ou de couper le son si il appuie sur le bouton correspondant
   * 
   * @param ActionEvent e : événement associé
   */ 
	 public void actionPerformed (ActionEvent e){
		 
		 if(e.getSource()==start1){ //lancement du jeu
			 
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
		
