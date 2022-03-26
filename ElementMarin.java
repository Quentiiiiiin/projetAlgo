import java.awt.image.*; //BufferedImage

/**
 * \abstract class ElementMarin : classe abstraite permettant de gérer tous les éléments marins interagissant avec le poisson (nourriture ou requin)
 */
public abstract class ElementMarin{
	
	//<!image de l'élément marin
	protected BufferedImage image;
	
	//<!point (x,y) déterminant l'origine de l'élément marin
	protected APoint origine;
	
	//<!fenêtre principale de jeu
	protected FenetreJeu f;
	
	/**
   * \fn ElementMarin(FenetreJeu f) : constructeur ElementMarin
   * 
   * @param FenetreJeu f : objet désignant la fenêtre principale de jeu
   */ 
	public ElementMarin(FenetreJeu f){
		this.f=f;
	}
	
	/**
   * \fn abstract void action() : méthode abstraite propre à chaque élément marin et gérant l'action à effectuer au contact du poisson
   */ 
	public abstract void action();
}
