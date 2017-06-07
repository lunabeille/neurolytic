package ihm;

import javax.swing.*;
import java.awt.*;
import java.util.*;

public class FirstTest {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FirstTest window = new FirstTest();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public FirstTest() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		//initialisation de la fenetre
		frame = new JFrame();
		//attribution du nom à la fenetre 
		frame.setTitle("Neurolytic Platform");
		//positionner la fenetre par rapport à l'ecran
		frame.setBounds(100, 100, 1017, 623);
		//fermer le programme lorsqu'on clic sur la croix rouge de la fenetre
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		//segmenter ma fenetre en 2 partie grace BoxLayout.X_AXIS
		frame.getContentPane().setLayout(new BoxLayout(frame.getContentPane(), BoxLayout.X_AXIS));

		//création de mes panel correspondant
		  JPanel PaneGauche = new JPanel();
		  JPanel PaneDroit = new JPanel();
		  //ici on rajoute nos composant dans le conteneur de la fenetre qui getContentPane
		  frame.getContentPane().add(PaneGauche);
		  //PaneGauche.setLayout(new BorderLayout());
		  //PaneGauche.setBounds(0,0, 20, 30);
		  
		  frame.getContentPane().add(PaneDroit);
		  
		  //dans le panel PaneGauche on va rajouter un tree menu 
		  
		  Vector oneVector = new NamedVector("Service IT");
		    Vector twoVector = new NamedVector("Service RH", new String[] { "Mercury",
		        "Venus", "Mars" });
		    Vector threeVector = new NamedVector("Service R&D");
		    threeVector.add(System.getProperties());
		    threeVector.add(twoVector);
		    Object rootNodes[] = { oneVector, twoVector, threeVector };
		    Vector rootVector = new NamedVector("Root", rootNodes);
		    JTree tree = new JTree(rootVector);

		    JScrollPane scrollPane = new JScrollPane(tree);
		    //PaneGauche.add(scrollPane, BorderLayout.NORTH);
		    // composant des boutons
		    JPanel PanelAjoutServiceSalarie = new JPanel();
		    JButton service = new JButton("Ajouter un Service");
		    JButton salarie = new JButton("Ajouter un Salarie");
		    PanelAjoutServiceSalarie.add(service);
		    PanelAjoutServiceSalarie.add(salarie);
		    //PaneGauche.add(PanelAjoutServiceSalarie, BorderLayout.SOUTH);
		    scrollPane.setLayout(new BoxLayout(scrollPane, BoxLayout.LINE_AXIS));
		    PanelAjoutServiceSalarie.setLayout(new BoxLayout(PanelAjoutServiceSalarie, BoxLayout.LINE_AXIS));
		    
		    //On positionne maintenant ces 2 lignes en colonne
		    PaneGauche.setLayout(new BoxLayout(PaneGauche, BoxLayout.PAGE_AXIS));
		    PaneGauche.add(scrollPane);
		    PaneGauche.add(PanelAjoutServiceSalarie);
		    
		    
		    
		    
		    
		  
		  
		  
	}

	

}
