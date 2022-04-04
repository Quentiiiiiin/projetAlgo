import javax.swing.*; //graphique
import java.awt.*; //couleurs
import java.io.*; //File
import javax.imageio.*; //images
import java.awt.image.*; //bufferedImage

/**
 * \class AffichageJeu : zone de jeu graphique
 */ 
public class AffichageJeu extends JPanel{
	
	//<!fenêtre principale correspondante
	private FenetreJeu f;
	
	//<!tableau contenant les objets du niveau 1
	private ElementMarin[] o1 = new ElementMarin[3];
	
	//<!tableau contenant les objets du niveau 2
	private ElementMarin[] o2 = new ElementMarin[3];
	
	//<!tableau contenant les objets du niveau 3
	private ElementMarin[] o3 = new ElementMarin[4];
	
	//<!tableau contenant les objets du niveau 4
	private ElementMarin[] o4 = new ElementMarin[5];
	
	//<!tableau contenant les objets du niveau 5
	private ElementMarin[] o5 = new ElementMarin[6];
	
	//<!booléen permettant de savoir si une Nourriture a été touché dans le niveau en cours
	boolean toucherNourriture=false;
	
	//<!booléen gérant l'affichage ou non de la nourriture 
	boolean affiche=true;
	
	//<!image de fond de la zone de jeu
	private BufferedImage fondMarin;
	/**
   * \fn AffichageJeu(FenetreJeu f) : constructeur AffichageJeu
   * 
   * @param FenetreJeu f : objet désignant la fenêtre principale de jeu
   */ 
	public AffichageJeu(FenetreJeu f) throws IOException{
		
		this.f=f;
		fondMarin= ImageIO.read(new File("images/eauPolluée.jpg")); //l'image de fond ne peut pas être créée dans la méthode paint (trop de ralentissement créé par la boucle try catch
		
		//initialisation des tableaux d'objets
		
		o1[0] = new Nourriture(1,f);
		o2[0] = new Nourriture(2,f);
		o3[0] = new Nourriture(3,f);
		o4[0] = new Nourriture(1,f);
		o5[0] = new Nourriture(1,f);
		
		o1[1] = new Requin(f);
		o2[1] = new Requin(f);
		o3[1] = new Requin(f);
		o4[1] = new Requin(f);
		o5[1] = new Requin(f);
		o1[2] = new Requin(f);
		o2[2] = new Requin(f);
		o3[2] = new Requin(f);
		o4[2] = new Requin(f);
		o5[2] = new Requin(f);
        o3[3] = new Requin(f);
        o4[3] = new Requin(f);
        o4[4] = new Requin(f);
        o5[3] = new Requin(f);
        o5[4] = new Requin(f);
		o5[5] = new Requin(f);
	}
	
	/**
   * \fn void paintComponent(Graphics g) : méthode gérant l'affichage des composants du jeu
   * 
   * @param Graphics g : objet graphique d'affichage
   */ 
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		
		//dessin de l'image du poisson, de la plateforme et du fond
		
		g.drawImage(fondMarin,0,0,(int)(20.0/27.0*1500),(int)(5.0/6.0*1000),null);
		g.setColor(new Color(0,127,255));
		g.fillRect((int)(3.5/5.0*f.largeurJeu),(int)(4.5/5.0*f.hauteurJeu),(int)(1.5/5.0*f.largeurJeu),(int)(0.2/5.0*f.hauteurJeu));
		g.drawImage(f.Nemo.image,(int)(f.Nemo.origine.x),(int)(f.Nemo.origine.y),f.Nemo.largeur,f.Nemo.hauteur,null);
		
		if(!f.enJeu){ //si l'utilisateur n'est pas en jeu
		
		//dessin des points donnant à l'utilisateur une idée de la courbe suivie par le poisson (cette courbe ne prend pas le courant en compte volontairement)
		
			g.setColor(new Color(255,0,0));
			for(int i=0;i<10;i++){
				g.drawOval((int)(f.vI*Math.cos(Math.toRadians(f.aI))*(i+1)+f.Nemo.largeur),
				(int)(f.viscosite*Math.pow(i+1,2)-f.vI*Math.sin(Math.toRadians(f.aI))*(i+1)+450-f.Nemo.hauteur),
				5,5);
			}
		}
		//dessins propres à chaque f.numNiveau
		
		if(f.numNiveau==1){
			o1[0].origine = new APoint(500.0,550.0);
			o1[1].origine = new APoint(200.0,300.0);
			o1[2].origine = new APoint(800.0,200.0);

			for(int i=0;i<o1.length;i++){
				//dessin des ElementMarins
				if(o1[i] instanceof Nourriture){ //si l'objet est une Nourriture
					if(affiche){ //il vaut vérifier si on l'affiche ou non
						g.drawImage(o1[i].image,(int)(o1[i].origine.x),(int)(o1[i].origine.y),100,100,null);
					}
				}else{ //sinon on l'affiche dans tous les cas
					g.drawImage(o1[i].image,(int)(o1[i].origine.x),(int)(o1[i].origine.y),100,100,null);
				}
			}
			toucheElementMarin(o1);
		}else if(f.numNiveau==2){
			o2[0].origine = new APoint(400.0,300.0);
			o2[1].origine = new APoint(300.0,550.0);
			o2[2].origine = new APoint(600.0,500.0);

			for(int i=0;i<o2.length;i++){
				if(o2[i] instanceof Nourriture){
					if(affiche){ 
						g.drawImage(o2[i].image,(int)(o2[i].origine.x),(int)(o2[i].origine.y),100,100,null);
					}
				}else{
					g.drawImage(o2[i].image,(int)(o2[i].origine.x),(int)(o2[i].origine.y),100,100,null);
					}
			}
			toucheElementMarin(o2);
		}else if(f.numNiveau==3){
			o3[0].origine = new APoint(500.0,300.0);
			o3[1].origine = new APoint(300.0,350.0);
			o3[2].origine = new APoint(450.0,600.0);
            o3[3].origine = new APoint(700.0,100.0);
			for(int i=0;i<o3.length;i++){
				if(o3[i] instanceof Nourriture){
					if(affiche){ 
						g.drawImage(o3[i].image,(int)(o3[i].origine.x),(int)(o3[i].origine.y),100,100,null);
					}
				}else{
					g.drawImage(o3[i].image,(int)(o3[i].origine.x),(int)(o3[i].origine.y),100,100,null);
					}
			}
			toucheElementMarin(o3);
		}else if(f.numNiveau==4){
			o4[0].origine = new APoint(450.0,400.0);
			o4[1].origine = new APoint(350.0,200.0);
			o4[2].origine = new APoint(600.0,350.0);
            o4[3].origine = new APoint(800.0,500.0);
            o4[4].origine = new APoint(350.0,600.0);
			for(int i=0;i<o4.length;i++){
				if(o4[i] instanceof Nourriture){
					if(affiche){ 
						g.drawImage(o4[i].image,(int)(o4[i].origine.x),(int)(o4[i].origine.y),100,100,null);
					}
				}else{
					g.drawImage(o4[i].image,(int)(o4[i].origine.x),(int)(o4[i].origine.y),100,100,null);
				}
			}
			toucheElementMarin(o4);
		}else if(f.numNiveau==5){
			o5[0].origine = new APoint(600.0,325.0);
			o5[1].origine = new APoint(300.0,650.0);
			o5[2].origine = new APoint(100.0,350.0);
            o5[3].origine = new APoint(450.0,500.0);
            o5[4].origine = new APoint(500.0,125.0);
            o5[5].origine = new APoint(875.0,450.0);
			for(int i=0;i<o5.length;i++){
				if(o5[i] instanceof Nourriture){
					if(affiche){ 
						g.drawImage(o5[i].image,(int)(o5[i].origine.x),(int)(o5[i].origine.y),100,100,null);
					}
				}else{
					g.drawImage(o5[i].image,(int)(o5[i].origine.x),(int)(o5[i].origine.y),100,100,null);
				}
			}
			toucheElementMarin(o5);
		}
	}
	
	/**
   * \fn void toucheElementMarin(ElementMarin[] o) : méthode gérant le contact entre le poisson et un élément marin
   * 
   * @param ElementMarin[] o : tableau contenant les objets marins du niveau en cours
   */ 
	public void toucheElementMarin (ElementMarin[] o){
        
        		for(int i=0;i<o.length;i++){
				
				//si le poisson touche l'élément marin i
				
				if(
				//coin en haut à gauche
				((int)(f.Nemo.origine.x+30)>=(int)(o[i].origine.x) && (int)(f.Nemo.origine.x+30)<=(int)(o[i].origine.x)+100 &&
				(int)(f.Nemo.origine.y+30)>=(int)(o[i].origine.y) && (int)(f.Nemo.origine.y+30)<=(int)(o[i].origine.y)+100) || 
				//coin en haut à droite
				((int)(f.Nemo.origine.x+f.Nemo.largeur-30)>=(int)(o[i].origine.x) && (int)(f.Nemo.origine.x+f.Nemo.largeur-30)<=(int)(o[i].origine.x)+100 && 
				(int)(f.Nemo.origine.y+30)>=(int)(o[i].origine.y) && (int)(f.Nemo.origine.y+30)<=(int)(o[i].origine.y)+100) ||
				//coin en bas à droite
				((int)(f.Nemo.origine.x+f.Nemo.largeur-30)>=(int)(o[i].origine.x) && (int)(f.Nemo.origine.x+f.Nemo.largeur-30)<=(int)(o[i].origine.x)+100 && 
				(int)(f.Nemo.origine.y+f.Nemo.hauteur-30)>=(int)(o[i].origine.y) && (int)(f.Nemo.origine.y+f.Nemo.hauteur-30)<=(int)(o[i].origine.y)+100) ||
				//coin en bas à gauche
				((int)(f.Nemo.origine.x+30)>=(int)(o[i].origine.x) && (int)(f.Nemo.origine.x+30)<=(int)(o[i].origine.x)+100 && 
				(int)(f.Nemo.origine.y+f.Nemo.hauteur-30)>=(int)(o[i].origine.y) && (int)(f.Nemo.origine.y+f.Nemo.hauteur-30)<=(int)(o[i].origine.y)+100)){
					
					//on lance la méthode action de cet objet, 1 seule fois par objet
					if(o[i] instanceof Nourriture){
						if(toucherNourriture==false){
							o[i].action();
							toucherNourriture=true;
						}
					}else{
						o[i].action();
					}
				}
			}
    }
}
