import javax.swing.*; //fenêtre
import java.awt.*; //couleur
import java.io.*; //image

/**
 * \class FenetreInfo : fenêtre affichant plus d'informations sur les eaux polluées et la vie sous marine
 */
public class FenetreInfo extends JFrame{
	
	/**
   * \fn FenetreInfo() : constructeur FenetreInfo
   */
	public FenetreInfo(){
		
		//initialisation de la fenêtre
		
		this.setTitle("Localisation des endroits les plus pollués du monde");
		this.setSize(1000,830);
		this.setLocation(400,20);
		this.setResizable(false);
		
		//initialisation des composants de la fenêtre
		
		JPanel Fond = new JPanel();
        Fond.setLayout(null);
        Fond.setBounds(0,0,1000,809);
        Fond.setBackground(Color.black);
        this.add(Fond);
        
        JLabel Image = new JLabel(new ImageIcon("images/pollution.jpg"));
        Image.setBounds(0,0,1000,809);
        Fond.add(Image);
        this.setVisible(true);
        
	}
}
