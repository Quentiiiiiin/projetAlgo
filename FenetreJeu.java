import javax.swing.*; //fenêtre
import java.awt.*; //couleur
import java.awt.event.*; //événements ActionListener
import javax.swing.event.*; //événements ChangeListener
import java.io.*; //images

/**
 * \class FenetreJeu : fenêtre principale interactive de jeu  
 */ 
public class FenetreJeu extends JFrame implements ActionListener, ChangeListener{
	
	//<!barre de jauge du nourriture
	private JProgressBar currentNourriture;
	 
	//<!bouton start
	private JButton Start; 
	
	//<!bouton retstart
	private JButton Restart;
	
	//<!zone d'affichage du titre du jeu
	private JLabel Titre;
	
	//<!Poisson qui est l'objet dirigé par l'utilisateur
	Poisson Nemo;
	
	//<!objet responsable de la partie graphique du jeu
	AffichageJeu AffJeu; 
	
	//<!zone de couleur or autour de la partie graphique du jeu
	private JPanel Contour;
	
	//<!plateforme d'arrivée
	private JPanel Plateforme;
	
	//<!curseur de défilement permettant de régler la vitesse initiale du poisson
	private JSlider Vitesse;
	
	//<!curseur de défilement permettant de régler l'angle initial du poisson
	private JSlider Angle;
	
	//<!zone d'affichage de la vitesse initiale du poisson
	private JLabel VitesseI;
	
	//<!zone d'affichage de l'angle initial du poisson
	private JLabel AngleI;
	
	//<!zone d'affichage du courant présent sur le niveau
	private JLabel CourantX;

	//<!zone d'affichage du courant présent sur le niveau
	private JLabel CourantY;

	
	//<!zone d'affichage de la gravité présente sur le niveau
    private JLabel Viscosite;
    
    //<!zone d'affichage pourcentage nourriture restant
	private JLabel Nourri;
	 
	//<!bouton permettant d'afficher les règles du jeu, disponibles durant toute la partie
	private JButton Aide; 

	//<!vitesse initiale du poisson
	int vI;
	
	//<!angle initial du poisson
	int aI;
	
	//<!courant en X présent sur le niveau en cours
	private int cY;

	//<!courant en Y présent sur le niveau en cours
	private int cX;
	
	//<!pourcentage de nourriture restant au poisson
	double pNourriture=100.0;
	
	//<!gravité présente sur le niveau en cours
	double viscosite;
	
	//<!numéro du niveau en cours
	int numNiveau;
	
	//<!niveau aléatoire auquel envoie un trou noir au contact du poisson
	int newNumNiveau;
	
	//<!largeur de la zone de jeu dans laquelle évolue le poisson
	int largeurJeu;
	
	//<!hauteur de la zone de jeu dans laquelle évolue le poisson
	int hauteurJeu;
	
	//<!booléen permettant de savoir si un niveau est en cours de jeu ou non
	boolean enJeu=false;
	
	//<!booléen permettant de savoir si le poisson a touché un requin
	boolean toucheRequin=false; 

	//<!timer gérant l'évolution du jeu et servant de base temporelle
	private Timer mt;
	
	//<!variable permettant de compter le temps qui passe
	private double temps;
	
	/**
   * \fn FenetreJeu(poisson r, boolean b) : constructeur FenetreJeu
   * @param poisson r : poisson avec lequel l'utilisateur a choisi de jouer
   */ 
	public FenetreJeu(Poisson r) throws IOException{
						
		//Initialisation de la fenêtre principale
	
		this.setTitle("Fenetre Jeu");
		this.setSize(1500,1000);
		this.setLocation(250,25);
		this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		
		mt = new Timer(1,this); //timer très rapide pour donner un effet de mouvement fluide
		
		//Initialisation et placement de tous les composants statiques
		
		Start = new JButton("LAUNCH");
        Start.setLayout(null);
        Start.setBounds((int)(5.0/99.0*1500),
        (int)(13.0/21.0*1000),(int)(40.0/297.0*1500),(int)(2.0/21.0*1000));
        Start.setBackground(new Color(242,121,121));
        Start.setForeground(new Color(52,62,162));
        Start.setFont(new Font("Agency FB",Font.BOLD,60));
        
        Restart = new JButton("RESTART");
        Restart.setLayout(null);
        Restart.setBounds((int)(5.0/99.0*1500),
        (int)(10/21.0*1000),(int)(40.0/297.0*1500),(int)(2.0/21.0*1000));
        Restart.setForeground(new Color(52,62,162));
        Restart.setBackground(new Color(249,200,93));
        Restart.setFont(new Font("Agency FB",Font.BOLD,40));
        
                
        Titre = new JLabel("Nemo | Niveau "+ numNiveau);
        Titre.setLayout(null);
        Titre.setBounds((int)(60.0/99.0*1500),
        (int)(1.5/105.0*1000),(int)(800.0/297.0*1500),(int)(13.0/210.0*1000));
        Titre.setForeground(new Color(249,200,93));
        Titre.setFont(new Font("Agency FB",Font.BOLD,65));
              
        Vitesse = new JSlider(0,100,50);
        vI=Vitesse.getValue();
        Vitesse.setLayout(null);
        Vitesse.setBounds((int)(5.0/99.0*1500),
        (int)(3.5/21.0*1000),(int)(40.0/297.0*1500),(int)(2.0/21.0*1000));
        Vitesse.setMajorTickSpacing(25);
        Vitesse.setPaintTicks(true);
        Vitesse.setPaintLabels(true);
        Vitesse.setBackground(new Color(52,62,162));
        Vitesse.setForeground(new Color(249,200,93));
                
        VitesseI = new JLabel("Vitesse initiale : "+vI+" km/h");
        VitesseI.setLayout(null);
        VitesseI.setBounds((int)(5.0/99.0*1500),
        (int)(2.5/21.0*1000),(int)(40.0/297.0*1500),(int)(1.5/21.0*1000));
        VitesseI.setForeground(new Color(249,200,93));
        VitesseI.setFont(new Font("Agency FB",Font.BOLD, 19));
               
        Angle = new JSlider(0,90,45);
        aI=Angle.getValue();
        Angle.setLayout(null);
        Angle.setBounds((int)(5.0/99.0*1500),
        (int)(7.0/21.0*1000),(int)(40.0/297.0*1500),(int)(2.0/21.0*1000));
        Angle.setMajorTickSpacing(15);
        Angle.setPaintTicks(true);
        Angle.setPaintLabels(true);
        Angle.setBackground(new Color(52,62,162));
        Angle.setForeground(new Color(249,200,93));
                
                
        AngleI = new JLabel("Angle initial : "+aI+" \u00b0");
        AngleI.setLayout(null);
        AngleI.setBounds((int)(5.0/99.0*1500),
        (int)(6.0/21.0*1000),(int)(40.0/297.0*1500),(int)(1.5/21.0*1000));
        AngleI.setForeground(new Color(249,200,93));
        AngleI.setFont(new Font("Agency FB",Font.BOLD, 19));
        
                
        CourantX = new JLabel();
        CourantX.setLayout(null);
        CourantX.setBounds((int)(5.0/99.0*1500),
        (int)(0.50/21.0*1000)+20,(int)(40.0/297.0*1500),(int)(1.5/21.0*1000));
        CourantX.setForeground(new Color(249,200,93));
        CourantX.setFont(new Font("Agency FB",Font.BOLD,35));

		CourantY = new JLabel();
        CourantY.setLayout(null);
        CourantY.setBounds((int)(5.0/99.0*1500),
        (int)(0.50/21.0*1000)-20,(int)(40.0/297.0*1500),(int)(1.5/21.0*1000));
        CourantY.setForeground(new Color(249,200,93));
        CourantY.setFont(new Font("Agency FB",Font.BOLD,35));


        Viscosite = new JLabel();
        Viscosite.setLayout(null);
        Viscosite.setBounds((int)(5.0/99.0*1500),
        (int)(0.50/21.0*1000)-60,(int)(40.0/297.0*1500),(int)(1.5/21.0*1000));
        Viscosite.setForeground(new Color(249,200,93));
        Viscosite.setFont(new Font("Agency FB",Font.BOLD,35));
               
        Nourri = new JLabel("Nourriture : "+(int)(pNourriture)+"%");
        Nourri.setLayout(null);
        Nourri.setBounds((int)(5.0/99.0*1500),
        (int)(16.0/21.0*1000),(int)(50.0/297.0*1500),(int)(1.5/21.0*1000));
        Nourri.setForeground(new Color(249,200,93));
        Nourri.setFont(new Font("Agency FB",Font.BOLD,35));
	
		currentNourriture = new JProgressBar(0,100);
		currentNourriture.setBounds((int)(5.0/99.0*1500),
		(int)(18.0/21.0*1000),(int)(40.0/297.0*1500),(int)(0.75/21.0*1000));
		currentNourriture.setBorderPainted(true);
		currentNourriture.setValue((int)(pNourriture));
		currentNourriture.setForeground(new Color(249,200,93));
		
		largeurJeu = (int)(20.0/27.0*1500);
		hauteurJeu = (int)(5.0/6.0*1000);
		
		Contour = new JPanel();
		Contour.setLayout(null);
		Contour.setBounds((int)(70.0/297.0*1500)-10,(int)(2.0/21.0*1000)-10,(int)(20.0/27.0*1500)+20,(int)(5.0/6.0*1000)+20);
		Contour.setBackground(new Color(249,200,93));
		
		//Initialisation et placement initial des composants graphiques
		Nemo = r;
		AffJeu = new AffichageJeu(this);
		AffJeu.setLayout(null);
		AffJeu.setBounds((int)(70.0/297.0*1500),(int)(2.0/21.0*1000),
		(int)(20.0/27.0*1500),(int)(5.0/6.0*1000));
		AffJeu.repaint();
		
		Aide = new JButton("Aide");
		Aide.setSize(200,50);
		Aide.setLocation(350,20);
		Aide.setFont(new Font("Agency FB",Font.BOLD,35));
		Aide.setBackground(new Color(52,62,162));
		Aide.setForeground(Color.white);
		
		
		//ajout de tous ces composants au JPanel principal		
		
		JPanel panneauGlobal = new JPanel();
		panneauGlobal.setBackground(new Color(52,62,162));
		panneauGlobal.setLayout(null);
		panneauGlobal.add(Start);
		panneauGlobal.add(Restart);
		panneauGlobal.add(Titre);
		panneauGlobal.add(Aide);
		panneauGlobal.add(AffJeu);
		panneauGlobal.add(Contour);
		panneauGlobal.add(Vitesse);
		panneauGlobal.add(VitesseI);
		panneauGlobal.add(Angle);
		panneauGlobal.add(AngleI);
		panneauGlobal.add(CourantX);
		panneauGlobal.add(CourantY);
        panneauGlobal.add(Viscosite);
		panneauGlobal.add(Nourri);
		panneauGlobal.add(currentNourriture);
		
		//ajouts d'écouteurs sur les éléments utilisant l'IHM
		
		Start.addActionListener(this);
		Restart.addActionListener(this);
		Angle.addChangeListener(this);
		Vitesse.addChangeListener(this);
		Aide.addActionListener(this);
		
		//ajout du JPanel principal à la fenêtre + affichage de celle-ci
		
		this.add(panneauGlobal);
		this.setVisible(true);
		
		//le jeu peut commencer
		
		 debutJeu();
	}

	/**
   * \fn void debutJeu() : méthode de lancement du niveau 1
   */ 
	public void debutJeu(){
		numNiveau = 1;
		niveau(numNiveau); //affichage du niveau 1
	}
	
	/**
   * \fn void niveau(int i) : méthode affectant à chaque niveau ses caractéristiques propres et actualisant l'affichage en conséquence
   * 
   * @param int i : numéro du niveau à traiter
   */ 
	public void niveau(int i){
		
		//boucles contenant toutes les caractéristiques physiques propres à chaque niveau et mettant à jour l'affichage de la fenêtre pour chacun d'entre eux
		if(i==1){
			viscosite = 2.0;
			cX =(int) (1+2*Math.random()-1);
			cY =(int) (1+2*Math.random()-1);
		}else if(i==2){
			viscosite = 2.25;
			cX =(int)(1+4*Math.random()-2);
			cY =(int) (1+2*Math.random()-2);
		}else if(i==3){
			viscosite = 2.75;
			cX =(int)(1+6*Math.random()-3);
			cY =(int) (1+2*Math.random()-3);
		}else if(i==4){
			viscosite = 3.0;
			cX =(int)(1+8*Math.random()-4);
			cY =(int) (1+2*Math.random()-4);
		}else if(i==5){
			viscosite= 3.25;
			cX =(int)(1+10*Math.random()-5);
			cY =(int) (1+2*Math.random()-5);
		}


		if(cX>=0){
			CourantX.setText("Courant en X : "+"+"+cX);
		}else if(cX<0){
			CourantX.setText("Courant en X : "+cX);
		}

		if(cY>=0){
			CourantY.setText("Courant en Y : "+"+"+cY);
		}else if(cY<0){
			CourantY.setText("Courant en Y : "+cY);
		}

        Viscosite.setText("Viscosité : "+viscosite);
		Titre.setText("NEMO Niveau "+ numNiveau);
	}
	
	/**
   * \fn void actionPerformed(ActionEvent e) : méthode permettant de gérer la partie graphique du jeu grâce au timer et de gérer l'action des différents boutons
   * 
   * @param ActionEvent e : événement associé
   */ 
	public void actionPerformed (ActionEvent e){
		if(e.getSource() == Aide){ //appui sur le bouton aide
			String T = readFile("texte/aide.txt");
			JOptionPane.showMessageDialog(this,T); //message rappelant les règles du jeu
		}
		if(e.getSource()==Start){ //appui sur le bouton Start
			if(!enJeu){ //si l'utilisateur n'est pas déjà en train de jouer
				enJeu = true; //alors il commence à jouer
				timerStart();	//on indique le début du mouvement par le début du timer
			}		
		}
		if(e.getSource()==mt){ //toutes les millisecondes si le timer est en route (= si l'utilisateur est en jeu)
			
			//mise à jour de la position du poisson et de ses caractéristiques
			
			pNourriture =pNourriture-0.01;
			currentNourriture.setValue((int)(pNourriture));
			Nourri.setText("Nourriture : "+(int)(pNourriture)+"%");
			Nemo.ChangePosition(cX, cY, vI,aI,temps/15,viscosite);
			temps++;
			AffJeu.repaint();
			niveauTermine();
		}
		if(e.getSource()==Restart){ //appui sur le bouton restart
			if(!enJeu){
				restart(); //on relance le jeu
			}
		}
	}
	
	/**
   * \fn void timerStart() : méthode de lancement du timer au temps 0
   */ 
	public void timerStart(){
		temps = 0 ;
		mt.start();
	}
	
	/**
   * \fn void timerStart() : méthode d'arrêt du timer
   */ 
	public void timerStop(){
		mt.stop();
	}
	
	/**
   * \fn void actionChanged(ChangeEvent e) : méthode permettant de gérer l'action des différents curseurs de défilement
   * 
   * @param ActionEvent e : événement associé
   */ 
	public void stateChanged(ChangeEvent e){
		if(!enJeu){ //l'utilisateur ne peut modifier aucunes caractéristiques si le niveau est en cours
			if(e.getSource()==Vitesse){  
				
				//mise à jour + affichage changement vitesse
				
				vI = Vitesse.getValue();
				VitesseI.setText("Vitesse initiale : "+vI+" km/h");
			}else if(e.getSource()==Angle){
				
				//mise à jour + affichage changement angle
				
				aI = Angle.getValue();
				AngleI.setText("Angle initial : "+aI+" \u00b0");
			}
		}
		AffJeu.repaint();	
	}
	
	/**
   * \fn void niveauTermine() : méthode permettant de tester si le niveau est terminé ou non et d'agir en conséquence
   */ 
    public void niveauTermine() {
		
		if((int)(Nemo.origine.y)<=(int)(4.5/5.0*hauteurJeu-Nemo.hauteur+10) &&
		(int)(Nemo.origine.y)>=(int)(4.5/5.0*hauteurJeu-Nemo.hauteur)
		 && (Nemo.origine.x>=3.5/5.0*largeurJeu-Nemo.largeur) 
		 && Nemo.origine.x<=3.5/5.0*largeurJeu+1.5/5.0*largeurJeu){ //si le niveau a été réussit

			//passage et lancement du niveau suivant
			 
			numNiveau++;
			niveau(numNiveau);
            if(numNiveau!=6){
				try{
					new FenetreChangementNiveau(numNiveau,this); //fenêtre de transition entre niveaux
				}catch(IOException Exception){
				}
            }
			
			//arrêt du timer et indication que l'utilisateur n'est plus en jeu
			
			timerStop();
			enJeu = false;
			
			//réinitialisation des attributs d'affichage graphique + replacement du poisson et des composants
			
			AffJeu.toucherNourriture=false;
			AffJeu.affiche=true;
			Nemo.origine = new APoint(0,833-100);
			Angle.setValue(45);
			Vitesse.setValue(50);
			aI = Angle.getValue();
			AngleI.setText("Angle initial : "+aI+" \u00b0");
			vI = Vitesse.getValue();
			VitesseI.setText("Vitesse initiale : "+vI+" km/h");
			
			
		}else if((Nemo.origine.y>hauteurJeu-Nemo.hauteur) || (Nemo.origine.x+Nemo.largeur>=largeurJeu)
		 || ((int)(pNourriture)==0) || (((int)(Nemo.origine.x+Nemo.largeur)>=(int)(3.5/5.0*largeurJeu))&&((int)(Nemo.origine.y+Nemo.hauteur)>=(int)(4.5/5.0*hauteurJeu)))
		 || toucheRequin ){ //si le niveau n'a pas été réussi ou qu'on a touché un requin
			
			
			//lancement du niveau (le même que précedamment
			
			niveau(numNiveau);
			
			//arrêt du timer
			
			timerStop();
			
			//malus : baisse du nourriture et affichage de cette baisse
			  
			pNourriture=pNourriture-15.0;
			if(pNourriture<0){
				pNourriture=0;
			}
			currentNourriture.setValue((int)(pNourriture));
			Nourri.setText("Nourriture : "+(int)(pNourriture)+"%");
			
			if(!toucheRequin && (int)(pNourriture)!=0){ //si la raison de l'échec de l'utilisateur est la sortie des limites du jeu
				try{
					new FenetreChangementNiveau(numNiveau,this); //fenêtre de transition entre niveaux (lancée à un autre moment si nous ne sommes pas dans cette boucle)
				}catch(IOException Exception){
				}
            }
            
            //réinitialisation des attributs d'affichage graphique + replacement du poisson et des composants
			
			AffJeu.toucherNourriture=false;
			AffJeu.affiche=true;
			Nemo.origine = new APoint(0,833-100);
			Angle.setValue(45);
		    Vitesse.setValue(50);
		    aI = Angle.getValue();
			AngleI.setText("Angle initial : "+aI+" \u00b0");
			vI = Vitesse.getValue();
			VitesseI.setText("Vitesse initiale : "+vI+" km/h");
			toucheRequin=false;			
		}
		
		//test afin de savoir si l'utilisateur a gagné ou perdu
		
		WinOrLose();
	}
	
	/**
   * \fn void WinOrLose() : méthode permettant de tester si l'utilisateur a terminé sa partie en gagnant ou perdant et d'agir en conséquence
   */
	public void WinOrLose(){
		if(numNiveau==6){//si l'utilisateur a réussi le niveau 5, il a gagné
			try{
				new FenetreWin(this); //création de la fenêtre de victoire
				setVisible(false);
			}catch(IOException Exception){
			}
				
		}else if((int)(pNourriture)<=0){ //si l'utilisateur n'a plus de nourriture, il a perdu
			try{
				new FenetreLose(this); //création de la fenêtre game over
				setVisible(false);
			}catch(IOException Exception){
			}
		}
	}
   
   /**
   * \fn void restart() : méthode permettant de relancer le jeu et de recommencer au début
   */
	public void restart(){
		mt.stop();
		setVisible(false);
		new FenetreStart();
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