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
	private JMenuBar menubar;
	private JMenu addMenu;
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
		pane.add(buildMenuTree(), BorderLayout.WEST);	
		buildMenuBar();
	}
	
	private JScrollPane buildMenuTree()
	{
		// tree menu on the left
		DefaultMutableTreeNode root = new DefaultMutableTreeNode("Mes équipes");
		
		// TODO : replace with real values later on
		for(String team : menu)
		{
			DefaultMutableTreeNode node = new DefaultMutableTreeNode(team);
			root.add(node);
		}
		treeMenu = new JTree(root);
		//scroll
		JScrollPane scrollpane = new JScrollPane();
		scrollpane.setViewportView(this.treeMenu);

		return scrollpane;
	}
	
	private void buildMenuBar()
	{
		//menubar
		menubar = new JMenuBar();
		setJMenuBar(menubar);
		
		addMenu = new JMenu("Ajouter");
		
		JMenuItem team = new JMenuItem("Ajouter un service");
		team.setActionCommand("team");
		team.addActionListener(this);
		addMenu.add(team);
		JMenuItem worker = new JMenuItem("Ajouter un salarié");
		team.setActionCommand("worker");
		team.addActionListener(this);
		addMenu.add(worker);
		menubar.add(addMenu);
	
	}
	public void display() 
	{
		this.setVisible(true);
	}

	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
}
