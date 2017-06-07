package swing;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;

// TODO : implémenter un modèle MVC pour la gestions des events
public class Ihm extends JFrame implements ActionListener
{
	private JTree treeMenu;
	// TODO : 
	// phase 1 : importer la liste des services dans un fichier
	// phase 2 : récupérer la liste des services dans la DB
	final static private String [] menu = {"Team IT", "Team RH", "Team Com", "Team Marketing"};
	
	public Ihm()
	{
		super("Neurolytic Platform");
		
		setBounds(10, 20, 300, 300);
		setDefaultCloseOperation(EXIT_ON_CLOSE);;
		Container pane = getContentPane();
		pane.setLayout(new BorderLayout());
		
		// tree menu on the left with scrolling
		DefaultMutableTreeNode root = new DefaultMutableTreeNode("Mes équipes");
		for(String team : menu)
		{
			DefaultMutableTreeNode node = new DefaultMutableTreeNode(team);
			root.add(node);
		}
		treeMenu = new JTree(root);
		JScrollPane scrollpane = new JScrollPane();
		scrollpane.setViewportView(this.treeMenu);
		pane.add(scrollpane, BorderLayout.WEST);	

	}
	
	public void display() 
	{
		this.setVisible(true);
	}

	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
}
