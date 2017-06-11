package swing;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTree;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreeSelectionModel;

// TODO : implémenter un modèle MVC pour la gestions des events
public class Ihm extends JFrame implements ActionListener, TreeSelectionListener
{
	private JTree treeMenu;
	private JMenuBar menubar;
	private JMenu addMenu;
	private Database db;
	private JLabel infoName;
	private JLabel infoFirstName;
	private JLabel infoSexe;
	private JLabel infoBdate;
	private Service [] services;
	private Container infoPanel;
	
	
	public Ihm()
	{
		super("Neurolytic Platform");
		this.db = new Database();
		
		setBounds(10, 20, 300, 300);
		setDefaultCloseOperation(EXIT_ON_CLOSE);;
		Container pane = getContentPane();
		pane.setLayout(new BorderLayout());
		pane.add(buildMenuTree(), BorderLayout.WEST);	
		buildMenuBar();
	    this.treeMenu.getSelectionModel().setSelectionMode(TreeSelectionModel.SINGLE_TREE_SELECTION);
		this.treeMenu.addTreeSelectionListener(this);
		this.infoName = new JLabel("");
		this.infoFirstName = new JLabel("");
		this.infoSexe = new JLabel("");
		this.infoBdate = new JLabel("");
	}
	
	private void buildInfoPanel(Employee selectedEmployee)
	{
	    JPanel container = new JPanel();
	    container.setBorder(BorderFactory.createTitledBorder("INFORMATION EMPLOYE"));
	    container.setLayout(new BoxLayout(container, BoxLayout.Y_AXIS));
		this.infoPanel = container;
		this.infoPanel.add(Box.createRigidArea(new Dimension(0,50)));
		this.infoPanel.setBackground(Color.white);
		this.infoPanel.setPreferredSize(new Dimension(200, 50));
		
		JLabel image = new JLabel(new ImageIcon("user.png"));
//		image.setPreferredSize(new Dimension(5, 5));
		this.infoPanel.add(image);
		this.infoPanel.add(Box.createRigidArea(new Dimension(0,20)));
		image.setAlignmentX(Component.CENTER_ALIGNMENT);
		
    	this.infoName.setText(selectedEmployee.getName());
    	this.infoName.setAlignmentX(0.5f);
    	this.infoName.setAlignmentY(Component.CENTER_ALIGNMENT);
    	this.infoPanel.add(infoName);
    	this.infoPanel.add(Box.createRigidArea(new Dimension(0,10)));
    	
//    	container.add(Box.createVerticalGlue());
    	
    	this.infoFirstName.setText(selectedEmployee.getFirstname());
    	this.infoFirstName.setAlignmentX(0.5f);
    	this.infoFirstName.setAlignmentY(Component.CENTER_ALIGNMENT);
    	this.infoPanel.add(infoFirstName);
    	this.infoPanel.add(Box.createRigidArea(new Dimension(0,10)));
//    	container.add(Box.createVerticalGlue());

    	
    	this.infoSexe.setText(selectedEmployee.getSexe());
    	this.infoSexe.setAlignmentX(0.5f);
    	this.infoSexe.setAlignmentY(Component.CENTER_ALIGNMENT);
    	this.infoPanel.add(infoSexe);
    	this.infoPanel.add(Box.createRigidArea(new Dimension(0,10)));
//    	container.add(Box.createVerticalGlue());

    	
    	this.infoBdate.setText(selectedEmployee.getBirthdate());
    	this.infoBdate.setAlignmentX(0.5f);
    	this.infoBdate.setAlignmentY(Component.CENTER_ALIGNMENT);
    	this.infoPanel.add(infoBdate);
    	this.infoPanel.add(Box.createRigidArea(new Dimension(0,10)));
//    	container.add(Box.createVerticalGlue());

		Container pane = getContentPane();
		pane.add(infoPanel, BorderLayout.EAST);
//		validate();
    	
	}
//	  private static Container makeIt(String title, float alignment) 
//	  {
////		    JPanel container = new JPanel();
////		    container.setBorder(BorderFactory.createTitledBorder(title));
////		    BoxLayout layout = new BoxLayout(container, BoxLayout.X_AXIS);
////		    container.setLayout(layout);
//
//		    for (int i = 0, n = labels.length; i < n; i++) {
//		      JButton button = new JButton(labels[i]);
//		      button.setAlignmentY(alignment);
//		      container.add(button);
//		    }
//		    return container;
//		  }
	private JScrollPane buildMenuTree()
	{
		// tree menu on the left
		// racine du TreeMenu
		DefaultMutableTreeNode root = new DefaultMutableTreeNode("Mes équipes");
		
		int rowsNumber = this.db.getServicesCount();
		String[] servicesNames = this.db.getServices(rowsNumber);
		this.services = new Service[rowsNumber];
		int i = 0;
		// on récupère tous les services dans un tablea
		for(String team : servicesNames)
		{
			Service s = new Service();
			s = this.db.buildService(team);
			this.services[i] = s;
			// ATTENTION : le constructeur va afficher le toString de l'objet passé en param
			// il faut donc veiller à bien l'overrider !
			DefaultMutableTreeNode node = new DefaultMutableTreeNode(s);
			for(Employee e : s.getTeam())
			{
				DefaultMutableTreeNode nodefils = new DefaultMutableTreeNode(e);
				node.add(nodefils);
			}
			root.add(node);
			i++;
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

	public void actionPerformed(ActionEvent e) 
	{
		// TODO Auto-generated method stub
		
	}

	/**
	 * When the user clicks on an item in the treeMenu displayed on the left of the pane
	 */
	public void valueChanged(TreeSelectionEvent e) 
	{
	    DefaultMutableTreeNode node = (DefaultMutableTreeNode)
                this.treeMenu.getLastSelectedPathComponent();
	   
	    if(node == null)
	    	return;
	    
	    Object nodeObject = node.getUserObject();
	    if(nodeObject instanceof Employee)
	    {
	    	Employee selectedEmployee = (Employee)nodeObject; 
	    	buildInfoPanel(selectedEmployee);
	    }
	    validate();
	}
}
