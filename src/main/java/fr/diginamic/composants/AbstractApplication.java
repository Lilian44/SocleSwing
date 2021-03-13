package fr.diginamic.composants;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import javax.swing.event.HyperlinkEvent;
import javax.swing.event.HyperlinkListener;
import javax.swing.text.html.HTMLDocument;
import javax.swing.text.html.HTMLEditorKit;
import javax.swing.text.html.StyleSheet;

import fr.diginamic.composants.error.ErrorManager;

/**
 * Application m�re de type SWING.
 * 
 * @author RichardBONNAMY
 *
 */
public abstract class AbstractApplication extends JFrame {

	/** serialVersionUID */
	private static final long serialVersionUID = -5774337273803383053L;
	/** width */
	private int width = 1400;
	/** height */
	private int height = 718;

	/** Cat�gories principales de menu */
	private Map<Integer, JMenu> categories = new HashMap<>();

	/** Barre de menu */
	protected JMenuBar menuBar = new JMenuBar();

	/**
	 * Pour l'ex�cution des use case (i.e. classes de services associ�es � une
	 * option de menu)
	 */
	private static ExecutorService threadService = Executors.newFixedThreadPool(3);

	/** Classe de service en cours de traitement */
	public static MenuService currentMenuService;

	/**
	 * Constructeur
	 * 
	 * @param title nom de l'application qui s'affiche dans le bandeau
	 */
	public AbstractApplication(String title) {
		setTitle(title);

		// Taille de la fen�tre
		setSize(width, height);

		// Op�ration � effectuer lorsque l'utilisateur clique sur la croix de fermeture:
		// EXIT_ON_CLOSE signifie arr�t de l'application
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// calcule de la position X de la fen�tre dans l'environnement windows
		int x = Console.getX(this);

		// calcule de la position Y de la fen�tre dans l'environnement windows
		int y = Console.getY(this);

		// Mise � jour des coordonn�es de l'application dans l'environnement windows.
		// Le but recherch� est que l'application soit centr�e sur le PC au d�marrage
		// Sinon par d�faut l'application apparait en haut � gauche.
		setLocation(x, y);
	}

	/**
	 * Ajoute une cat�gorie de menu � la barre de menu
	 * 
	 * @param id           identifiant
	 * @param categoryName nom de la cat�gorie
	 */
	public void addMenu(Integer id, String categoryName) {
		JMenu menuCateg = new JMenu(categoryName);
		menuBar.add(menuCateg);

		categories.put(id, menuCateg);
	}

	/**
	 * Ajoute une option de menu � une cat�gorie dont l'id est pass� en param�tre.
	 * L'option de menu est obligatoirement associ�e � une classe de service qui
	 * h�rite de {@link MenuService}
	 * 
	 * @param id          id
	 * @param name        name
	 * @param menuService classe de service
	 */
	public void addMenuOption(Integer id, String name, MenuService menuService) {

		menuService.setApplication(this);

		JMenu menuCateg = categories.get(id);
		if (menuCateg != null) {
			JMenuItem menuItem = new JMenuItem(name);
			menuCateg.add(menuItem);
			menuItem.addMouseListener(new MouseListener() {

				@Override
				public void mouseReleased(MouseEvent e) {
					currentMenuService = menuService;

					TraitementMenu tt = new TraitementMenu(menuService);
					threadService.submit(tt);
				}

				@Override
				public void mousePressed(MouseEvent e) {

				}

				@Override
				public void mouseExited(MouseEvent e) {
				}

				@Override
				public void mouseEntered(MouseEvent e) {
				}

				@Override
				public void mouseClicked(MouseEvent e) {
				}
			});
		}
	}

	/**
	 * Construit l'interface graphique principale avec:<br>
	 * - un menu<br>
	 * - des styles CSS<br>
	 * - une JTextPane (la console) qui va permettre d'afficher tout ce qu'on a �
	 * afficher et �galement d'activer des formulaires.
	 * 
	 */
	public void buildInterfaceGraphique() {
		this.setLayout(null);

		HTMLEditorKit htmlEditorKit = new HTMLEditorKit();

		HTMLDocument htmlDocument = (HTMLDocument) htmlEditorKit.createDefaultDocument();
		StyleSheet style = htmlDocument.getStyleSheet();
		htmlEditorKit.setStyleSheet(style);
		style.addRule("body { font-family:'Arial'; color:#000000;}");

		style.addRule(".table { border: solid 0px black;border-spacing:0px;border-margin:0px;}");
		style.addRule(
				".table td { border: solid 1px black; padding-top:0px; padding-bottom:0px; padding-left:4px;padding-right:4px;}");

		style.addRule(".bg-dark-blue { color: #FFFFFF; background: #0C0F46; font-weight:bold; }");
		style.addRule(".bg-blue { color: #FFFFFF; background: #007bff; font-weight:bold; }");
		style.addRule(".bg-red { color: #FFFFFF; background: #dc3545; font-weight:bold;  }");
		style.addRule(".bg-green { color: #FFFFFF; background: #28a745; font-weight:bold;  }");
		style.addRule(".bg-grey { color: #FFFFFF; background: #6c757d; font-weight:bold;  }");
		style.addRule(".bg-orange { color: #FFFFFF; background: #ffc107; font-weight:bold;  }");
		style.addRule(".bg-turquoise { color: #FFFFFF; background: #17a2b8; font-weight:bold;  }");
		style.addRule(".bg-yellow { color: #333333; background: #FFFF00; font-weight:bold;  }");

		style.addRule(".btn-blue { color: #FFFFFF; background: #007bff; }");
		style.addRule(".btn-red { color: #FFFFFF; background: #dc3545; }");
		style.addRule(".btn-green { color: #FFFFFF; background: #28a745;  text-decoration: none; }");
		style.addRule(".btn-grey { color: #FFFFFF; background: #6c757d;  text-decoration: none; }");
		style.addRule(".btn-orange { color: #FFFFFF; background: #ffc107;  text-decoration: none; }");
		style.addRule(".btn-turquoise { color: #FFFFFF; background: #17a2b8;  text-decoration: none; }");
		style.addRule(".btn-yellow { color: #000000; background: #FFFF00;  text-decoration: none; }");

		// La mise � NULL du Layout permet d'afficher tous les �l�ments de l'interface
		// graphique en coordonn�es X, Y

		// Cr�ation de l'afficheur qui sera ici un JTextPane.
		JTextPane afficheur = new JTextPane();
		afficheur.setBounds(10, 200, width - 40, 390);
		afficheur.setFont(Console.FONT_18);
		afficheur.setEditable(false);
		afficheur.setContentType("text/html");
		afficheur.setEditorKit(htmlEditorKit);
		afficheur.setDocument(htmlDocument);
		afficheur.addHyperlinkListener(new HyperlinkListener() {
			@Override
			public void hyperlinkUpdate(HyperlinkEvent e) {
				if (e.getEventType() == HyperlinkEvent.EventType.ACTIVATED) {
					TraitementInvocation tt = new TraitementInvocation(e.getDescription());
					threadService.submit(tt);
				}
			}
		});

		Console.afficheur = afficheur;

		// Cr�ation d'un JScrollPane qui va permettre d'ajouter des barres de d�filement
		// horizontale et verticale avec curseurs � notre afficheur.
		JScrollPane sp = new JScrollPane(afficheur);
		sp.setBounds(10, 20, width - 40, 620);

		// On ajoute le JScrollPane � l'interface graphique.
		this.add(sp);

		// Ajout de la barre de menu
		this.setJMenuBar(menuBar);

		this.main();

		// On rend l'interface graphique visible par d�faut, ce qui est pr�f�rable.
		this.setVisible(true);
	}

	/**
	 * Les classes filles doivent impl�menter cette m�thode, qui va permettre
	 * notamment d'effectuer les traitements de d�marrage et de cr�er le menu
	 */
	public abstract void main();
}