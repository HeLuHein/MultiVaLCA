/*	
 * MultiVaLCA
 */

package de.unistuttgart.iwb.multivalcagui;

import java.awt.BorderLayout;
import java.awt.CardLayout;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.LinkedList;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

import de.unistuttgart.iwb.multivalca.*;
import net.miginfocom.swing.MigLayout;

/**
 * @author Dr.-Ing. Joachim Schwarte
 * @version 0.31
 */

public class MultiVaLCA {
	
	Language l = Language.Deutsch;
	
	private JFrame frame = new JFrame();;
	private JPanel panel = new JPanel();
	private CardLayout cl = new CardLayout(0, 0);
	private final Action newFlowAction 		= new newFlowAction();
	private final Action newModuleAction 	= new newModuleAction();
	private final Action newProductAction 	= new newProductAction();
	private final Action aboutAction 		= new aboutAction();
	private final Action prefsAction 		= new prefsAction();
	private final Action listFlowAction 	= new listFlowAction();
	private final Action listModuleAction 	= new listModuleAction();
	private final Action listProductAction 	= new listProductAction();
	private final Action calculateAction 	= new calculateAction();	
	private final Action xmlExportAction 	= new xmlExportAction(l);
	private final Action xmlImportAction 	= new xmlImportAction(l);
	
	//
	// Panel 1; Neuer Fluss
	//
	private JPanel panel_01 = new JPanel();
	private JTextField txtP01n1 = new JTextField(); 	// Eingabefeld Flussname
	private JLabel lblP01n1 = new JLabel();				// "Neuer Fluss"
	private JLabel lblP01n2 = new JLabel();				// "Name des Flusses"
	private JLabel lblP01n3 = new JLabel();				// "Typ"
	private JLabel lblP01n4 = new JLabel();				// "Einheit"
	private JLabel lblP01n5 = new JLabel();				// Status
	private JButton btnP01n1 = new JButton();			// "speichern" 
	private JComboBox<String> comboBox = new JComboBox<String>();
	//
	// Panel 2; Neues Prozessmodul
	//
	private JPanel panel_02 = new JPanel();
	private JTextField txtP02n1 = new JTextField();		// Eingabefeld Modulname
	private JTextField txtP02n2 = new JTextField();		// Eingabefeld Flussname  
	private JTextField txtP02n3 = new JTextField();		// Eingabefeld Menge
	private JTextField txtP02n4 = new JTextField();		// Eingabefeld Untergrenze
	private JTextField txtP02n5 = new JTextField();		// Eingabefeld Obergrenze
	private JLabel lblP02n1 = new JLabel(); 			// "Neues Prozessmodul"
	private JLabel lblP02n2 = new JLabel();				// "Name des Prozessmoduls"
	private JLabel lblP02n3 = new JLabel();				// "Name des Flusses"
	private JLabel lblP02n4 = new JLabel();				// "Menge"
	private JLabel lblP02n5 = new JLabel();				// Status
	private JLabel lblP02n6 = new JLabel();				// "Untergrenze"
	private JLabel lblP02n7 = new JLabel();				// "Obergrenze"
	private JButton btnP02n1 = new JButton(); 			// "neues Prozessmodul anlegen"
	private JButton btnP02n2 = new JButton();			// "Grenzwerte best�tigen"
	private JButton btnP02n3 = new JButton();			// "fertig"
	private JButton btnP02n4 = new JButton();			// "Fluss hinzuf�gen"
	//
	// Panel 3; Neues Produktsystem
	//
	private JPanel panel_3 = new JPanel();
	private JTextField txtP03n1 = new JTextField();		// Eingabefeld Name des Systems
	private JTextField txtP03n2 = new JTextField();		// Eingabefeld Modulname
	private JTextField txtP03n3 = new JTextField();		// Eingabefeld Produkt im Bedarfsvektor
	private JTextField txtP03n4 = new JTextField();		// Eingabefeld Menge des Produkts im Bedarfsvektor
	private JTextField txtP03n5 = new JTextField();		// Eingabefeld Vor- und Koppelpr.
	private JLabel lblP03n1 = new JLabel();				// "Neues Produktsystem"
	private JLabel lblP03n2 = new JLabel();				// "Name des Produktsystems"
	private JLabel lblP03n3 = new JLabel();				// "Prozessmodul/Subsystem"
	private JLabel lblP03n4 = new JLabel();				// "Produkt im Bedarfsvektor"
	private JLabel lblP03n5 = new JLabel();				// "Menge"
	private JLabel lblP03n6 = new JLabel();				// "Vor- oder Koppelprodukt"
	private JLabel lblP03n7 = new JLabel(); 			// Status
	private JButton btnP03n1 = new JButton();			// "neues Produktsystem anlegen"
	private JButton btnP03n2 = new JButton();			// "Modul/System hinzuf�gen"
	private JButton btnP03n3 = new JButton();			// "weiter"
	private JButton btnP03n4 = new JButton();			// "Bedarfsvektor erg�nzen"
	private JButton btnP03n5 = new JButton();			// "weiter"
	private JButton btnP03n6 = new JButton();			// "VK-Flow hinzuf�gen"
	private JButton btnP03n7 = new JButton();			// "fertig"
	//
	// Panel 4; Info
	//
	private JPanel panel_4 = new JPanel();
	private JLabel lblInfo1 = new JLabel(); 	
	private JLabel lblInfo2 = new JLabel(); 	
	private JLabel lblInfo3 = new JLabel();		
	private JLabel lblInfo4 = new JLabel();		
	JLabel lblInfo5 = new JLabel();		
	//
	// Panel 5; Sprachauswahl
	//
	private JPanel panel_5 = new JPanel();
	private JLabel lblP05n1 = new JLabel();				// "Sprachauswahl"
	private JLabel lblP05n2 = new JLabel();				// "Sprache"
	private JButton btn05n1 = new JButton();			// "speichern"
	private JComboBox<Language> comboBox2 = new JComboBox<Language>();
	//
	// Panel 6; Flussliste
	//
	private JPanel panel_6 = new JPanel();
	private JLabel lblP06n1 = new JLabel();
	private JTable flowsTable 		= new JTable();
	DefaultTableModel flowsTableModel 		= new DefaultTableModel(0,3);
	//
	// Panel 7; Prozessmodulliste
	//
	private JPanel panel_7 = new JPanel();
	private JLabel lblP07n1 = new JLabel();
	private JTable pmTable 		= new JTable();
	DefaultTableModel pmTableModel 		= new DefaultTableModel(0,4);
	//
	// Panel 8; Produktsystemliste
	//
	private JPanel panel_8 = new JPanel();
	private JLabel lblP08n1 = new JLabel();
	private JTable psTable 		= new JTable();
	DefaultTableModel psTableModel 		= new DefaultTableModel(0,3);
	//
	// Panel 9; LCI Berechnung
	//
	private JPanel panel_9 = new JPanel();
	private JLabel lblP09n1 = new JLabel();
	private JTable lciTable 		= new JTable();
	DefaultTableModel lciTableModel 		= new DefaultTableModel(0,4);

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MultiVaLCA window = new MultiVaLCA();
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
	public MultiVaLCA() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame.setTitle(GuiStrings.getGS("head1",l)+"   "+GuiStrings.getGS("head2",l));
		frame.setBounds(100, 100, 600, 480);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);				
		frame.getContentPane().add(panel, BorderLayout.CENTER);		
		panel.setLayout(cl);
		
		/*
		 * Gestaltung der Panels
		 */
		
		//
		// Panel 1; Neuer Fluss
		//	
		panel.add(panel_01, "neuFluss");
		panel_01.setLayout(new MigLayout("", "[108px,grow][108px][108px][108px,grow]", 
				"[20px][20px][20px][20px][20px][20px][20px,grow]"));		
		lblP01n1.setFont(new Font("Tahoma", Font.BOLD, 14));
		panel_01.add(lblP01n1, "flowy,cell 1 0 2 1,alignx center,growy");			
		panel_01.add(lblP01n2, "cell 1 1,grow");		
		txtP01n1.setText("");
		panel_01.add(txtP01n1, "cell 2 1,grow");
		txtP01n1.setColumns(10);	
		panel_01.add(lblP01n3, "cell 1 2,grow");	
		panel_01.add(comboBox, "cell 2 2,grow");	
		panel_01.add(lblP01n4, "cell 1 3,grow");	
		JComboBox<FlowUnit> comboBox_1 = new JComboBox<FlowUnit>();
		comboBox_1.setModel(new DefaultComboBoxModel<FlowUnit>(FlowUnit.values()));
		panel_01.add(comboBox_1, "cell 2 3,grow");			
		panel_01.add(btnP01n1, "cell 1 4 2 1,alignx center");
		panel_01.add(lblP01n5, "cell 0 5 4 1,alignx center");		
		//
		// Panel 2; Neues Prozessmodul
		//
		panel.add(panel_02, "neuModul");
		panel_02.setLayout(new MigLayout("", "[108px,grow][108px][108px][108px,grow]", 
				"[20px][20px][20px][20px][20px][20px][20px][20px][20px][20px][20px][20px,grow]"));		
		lblP02n1.setFont(new Font("Tahoma", Font.BOLD, 14));
		panel_02.add(lblP02n1, "flowy,cell 1 0 2 1,alignx center,growy");	
		panel_02.add(lblP02n2, "cell 1 1,grow");		
		txtP02n1.setText("");
		panel_02.add(txtP02n1, "cell 2 1,grow");
		txtP02n1.setColumns(10);		
		panel_02.add(btnP02n1, "cell 1 2 2 1,alignx center");		
		panel_02.add(lblP02n3, "cell 1 3,grow");	
		txtP02n2.setText("");
		panel_02.add(txtP02n2, "cell 2 3,grow");
		txtP02n2.setColumns(10);
		txtP02n2.setEnabled(false);	
		panel_02.add(lblP02n4, "cell 1 4,grow");
		txtP02n3.setText("");
		panel_02.add(txtP02n3, "cell 2 4,grow");
		txtP02n3.setColumns(10);
		txtP02n3.setEnabled(false);		
		btnP02n2.setEnabled(false);
		panel_02.add(btnP02n2, "cell 1 5 2 1,alignx center");	
		panel_02.add(lblP02n6, "cell 1 6,grow");	
		txtP02n4.setText("");
		panel_02.add(txtP02n4, "cell 2 6,grow");
		txtP02n4.setColumns(10);
		txtP02n4.setEnabled(false);	
		panel_02.add(lblP02n7, "cell 1 7,grow");
		txtP02n5.setText("");
		panel_02.add(txtP02n5, "cell 2 7,grow");
		txtP02n5.setColumns(10);
		txtP02n5.setEnabled(false);		
		btnP02n4.setEnabled(false);
		panel_02.add(btnP02n4, "cell 1 8,alignx center");
		btnP02n3.setEnabled(false);
		panel_02.add(btnP02n3, "cell 2 8,alignx center");
		panel_02.add(lblP02n5, "cell 0 9 4 1,alignx center");		
		//
		// Panel 3; Neues Produktsystem
		//		
		panel.add(panel_3, "neuProdukt");	
		panel_3.setLayout(new MigLayout("", "[108px,grow][108px][108px][108px,grow]", 
				"[20px][20px][20px][20px][20px][20px][20px][20px][20px][20px][20px][20px,grow]"));		
		lblP03n1.setFont(new Font("Tahoma", Font.BOLD, 14));
		panel_3.add(lblP03n1, "flowy,cell 1 0 2 1,alignx center,growy");	
		panel_3.add(lblP03n2, "cell 1 1,grow");		
		txtP03n1.setText("");
		panel_3.add(txtP03n1, "cell 2 1,grow");
		txtP03n1.setColumns(10);		
		panel_3.add(btnP03n1, "cell 1 2 2 1,alignx center");
		panel_3.add(lblP03n3, "cell 1 3,grow");
		txtP03n2.setText("");
		panel_3.add(txtP03n2, "cell 2 3,grow");
		txtP03n2.setColumns(10);
		txtP03n2.setEnabled(false);		
		btnP03n2.setEnabled(false);
		panel_3.add(btnP03n2, "cell 1 4,alignx center");		
		btnP03n3.setEnabled(false);
		panel_3.add(btnP03n3, "cell 2 4,alignx center");	
		panel_3.add(lblP03n4, "cell 1 5,grow");
		txtP03n3.setText("");
		panel_3.add(txtP03n3, "cell 2 5,grow");
		txtP03n3.setColumns(10);
		txtP03n3.setEnabled(false);		
		panel_3.add(lblP03n5, "cell 1 6,grow");
		txtP03n4.setText("");
		panel_3.add(txtP03n4, "cell 2 6,grow");
		txtP03n4.setColumns(10);
		txtP03n4.setEnabled(false);		
		btnP03n4.setEnabled(false);
		panel_3.add(btnP03n4, "cell 1 7,alignx center");		
		btnP03n5.setEnabled(false);
		panel_3.add(btnP03n5, "cell 2 7,alignx center");		
		panel_3.add(lblP03n6, "cell 1 8,grow");
		txtP03n5.setText("");
		panel_3.add(txtP03n5, "cell 2 8,grow");
		txtP03n5.setColumns(10);
		txtP03n5.setEnabled(false);		
		btnP03n6.setEnabled(false);
		panel_3.add(btnP03n6, "cell 1 9,alignx center");	
		btnP03n7.setEnabled(false);
		panel_3.add(btnP03n7, "cell 2 9,alignx center");		
		panel_3.add(lblP03n7, "cell 0 10 4 1,alignx center");
		//
		// Panel 4; Info
		//
		panel.add(panel_4, "leer");
		panel_4.setLayout(new MigLayout("", "[108px,grow][200px][108px,grow]", 
				"[20px][20px][40px][20px][20px][20px][20px,grow][20px]"));		
		lblInfo1.setFont(new Font("Tahoma", Font.BOLD, 26));
		panel_4.add(lblInfo1, "cell 1 2,alignx center,aligny top");		
		lblInfo2.setFont(new Font("Tahoma", Font.BOLD, 14));
		panel_4.add(lblInfo2, "cell 1 3,alignx center,aligny top");		
		lblInfo3.setFont(new Font("Tahoma", Font.BOLD, 14));
		panel_4.add(lblInfo3, "cell 1 4,alignx center,aligny top");		
		lblInfo4.setFont(new Font("Tahoma", Font.BOLD, 14));
		panel_4.add(lblInfo4, "cell 1 5,alignx center,aligny top");		
		lblInfo5.setFont(new Font("Tahoma", Font.PLAIN, 14));
		panel_4.add(lblInfo5, "cell 1 7,alignx center,aligny top");
		//
		// Panel 5; Sprachauswahl
		//
		panel.add(panel_5, "lang");
		panel_5.setLayout(new MigLayout("", "[108px,grow][108px][108px][108px,grow]", 
				"[20px][20px][20px][20px][20px][20px][20px,grow]"));		
		lblP05n1.setFont(new Font("Tahoma", Font.BOLD, 14));
		panel_5.add(lblP05n1, "flowy,cell 1 0 2 1,alignx center,growy");		
		panel_5.add(lblP05n2, "cell 1 1,grow");		
		comboBox2.setModel(new DefaultComboBoxModel<Language>(Language.values()));
		panel_5.add(comboBox2, "cell 2 1,grow");	
		panel_5.add(btn05n1, "cell 1 2 2 1,alignx center");		
		lblInfo1.setText(GuiStrings.getGS("head1", l));
		lblInfo2.setText(GuiStrings.getGS("info1", l));
		lblInfo3.setText(GuiStrings.getGS("info2", l));
		lblInfo4.setText(GuiStrings.getGS("info3", l));
		lblInfo5.setText(GuiStrings.getGS("head2", l)+"     "+GuiStrings.getGS("date", l));		
		//
		// Panel 6; Flussliste
		//
		panel.add(panel_6, "listeFluss");		
		panel_6.setLayout(new MigLayout("", "[74px,grow]", "[14px][grow]"));			
		lblP06n1.setFont(new Font("Tahoma", Font.BOLD, 14));
		panel_6.add(lblP06n1, "cell 0 0,alignx center,aligny top");		
		panel_6.add(new JScrollPane(flowsTable), "cell 0 1,alignx center,aligny top");
		//
		// Panel 7; Prozessmodulliste
		//
		panel.add(panel_7, "listePM");		
		panel_7.setLayout(new MigLayout("", "[74px,grow]", "[14px][grow]"));		
		lblP07n1.setFont(new Font("Tahoma", Font.BOLD, 14));
		panel_7.add(lblP07n1, "cell 0 0,alignx center,aligny top");		
		panel_7.add(new JScrollPane(pmTable), "cell 0 1,alignx center,aligny top");	
		//
		// Panel 8; Produktsystemliste
		//
		panel.add(panel_8, "listePS");		
		panel_8.setLayout(new MigLayout("", "[74px,grow]", "[14px][grow]"));		
		lblP08n1.setFont(new Font("Tahoma", Font.BOLD, 14));
		panel_8.add(lblP08n1, "cell 0 0,alignx center,aligny top");		
		panel_8.add(new JScrollPane(psTable), "cell 0 1,alignx center,aligny top");	
		//
		// Panel 9; LCI Berechnung
		//
		panel.add(panel_9, "berechnen");		
		panel_9.setLayout(new MigLayout("", "[74px,grow]", "[14px][grow]"));		
		lblP09n1.setFont(new Font("Tahoma", Font.BOLD, 14));
		panel_9.add(lblP09n1, "cell 0 0,alignx center,aligny top");		
		panel_9.add(new JScrollPane(lciTable), "cell 0 1,alignx center,aligny top");	
		
		cl.show(panel, "leer");
	
		/*
		 * Organisation der Menuleiste
		 */
		
		JMenuBar menuBar = new JMenuBar();
		frame.setJMenuBar(menuBar);
		
		JMenu mnDatei = new JMenu(GuiStrings.getGS("mp6",l));
		menuBar.add(mnDatei);
		
		JMenuItem mntmNewMenuItem_4 = new JMenuItem();
		mntmNewMenuItem_4.setAction(xmlExportAction);
		mnDatei.add(mntmNewMenuItem_4);	
		
		JMenuItem mntmNewMenuItem_5 = new JMenuItem();
		mntmNewMenuItem_5.setAction(xmlImportAction);
		mnDatei.add(mntmNewMenuItem_5);
		
		JMenu mnNew = new JMenu(GuiStrings.getGS("mp1",l));
		menuBar.add(mnNew);
		
		JMenuItem mntmFlow = new JMenuItem();
		mntmFlow.setAction(newFlowAction);
		mnNew.add(mntmFlow);
		
		JMenuItem mntmProcessModule = new JMenuItem();
		mntmProcessModule.setAction(newModuleAction);
		mnNew.add(mntmProcessModule);
		
		JMenuItem mntmProductSystem = new JMenuItem();
		mntmProductSystem.setAction(newProductAction);
		mnNew.add(mntmProductSystem);
		
		JMenu mnListe = new JMenu(GuiStrings.getGS("mp4", l));
		menuBar.add(mnListe);
		
		JMenuItem mntmFlsse = new JMenuItem();
		mntmFlsse.setAction(listFlowAction);
		mnListe.add(mntmFlsse);
		
		JMenuItem mntmProzessmodule = new JMenuItem();
		mntmProzessmodule.setAction(listModuleAction);
		mnListe.add(mntmProzessmodule);
		
		JMenuItem mntmProduktsysteme = new JMenuItem();
		mntmProduktsysteme.setAction(listProductAction);
		mnListe.add(mntmProduktsysteme);
		
		JMenu mnBerechnen = new JMenu(GuiStrings.getGS("mp5", l));
		menuBar.add(mnBerechnen);
		
		JMenuItem mntmLci = new JMenuItem();
		mntmLci.setAction(calculateAction);
		mnBerechnen.add(mntmLci);
			
		JMenu mnPrefs = new JMenu(GuiStrings.getGS("mp3", l));
		menuBar.add(mnPrefs);
		
		JMenuItem mntmNewMenuItem_3 = new JMenuItem();
		mntmNewMenuItem_3.setAction(prefsAction);
		mnPrefs.add(mntmNewMenuItem_3);
		
		JMenu mnHilfe = new JMenu(GuiStrings.getGS("mp2", l));
		menuBar.add(mnHilfe);
		
		JMenuItem mntmNewMenuItem_2 = new JMenuItem();
		mntmNewMenuItem_2.setAction(aboutAction);
		mnHilfe.add(mntmNewMenuItem_2);

		
		/*
		 * Aktivit�ten der Schaltfl�chen
		 */
		
		/*
		 * neuer Fluss
		 */
		
		btnP01n1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				String name = txtP01n1.getText();
				HashMap<FlowType, String> ft = FlowTypeStrings.getFTS(l);
				String typs = comboBox.getItemAt(comboBox.getSelectedIndex());
				FlowType typ = FlowType.Elementary;
				for (FlowType fte : ft.keySet()) {
					if (ft.get(fte) == typs) {
						typ = fte;
					}
				}

				FlowUnit einheit = comboBox_1.getItemAt(comboBox_1.getSelectedIndex());
				boolean nameOk = true;
				if (name.equals("")) {
					nameOk = false;
					lblP01n5.setText(GuiStrings.getGS("stat02", l));
				}
				if (name != name.replaceAll(" ","")) {
					nameOk = false;
					lblP01n5.setText(GuiStrings.getGS("stat20", l));
					txtP01n1.setText("");
				}								
				if (nameOk == true) {
					if (Flow.containsName(name)) {
						lblP01n5.setText(GuiStrings.getGS("stat03", l));
					} else {
						Flow.instance(name, typ, einheit);
						lblP01n5.setText(GuiStrings.getGS("stat04", l) + 
								Flow.getAllNames().size() + GuiStrings.getGS("stat05", l));
						txtP01n1.setText("");
						comboBox.setSelectedIndex(0);
						comboBox_1.setSelectedIndex(0);
					}	
				} 		
			}
		});

		/*
		 * neues Prozessmodul
		 */
		
		btnP02n1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				String name = txtP02n1.getText();	
				boolean nameOk = true;
				if (name.equals("")) {
					nameOk = false;
					lblP02n5.setText(GuiStrings.getGS("stat02", l));
				} 
				if (name != name.replaceAll(" ","")) {
					nameOk = false;
					lblP02n5.setText(GuiStrings.getGS("stat20", l));
					txtP02n1.setText("");
				}
				if (NameCheck.containsFVName(name)) {
					nameOk = false;
					lblP02n5.setText(GuiStrings.getGS("stat03", l));
					txtP02n1.setText("");
				}			
				if (nameOk == true) {
					ProcessModule.instance(name);
					lblP02n5.setText(GuiStrings.getGS("stat06", l) + 
							ProcessModule.getAllInstances().size() + GuiStrings.getGS("stat05", l));
					btnP02n1.setEnabled(false);
					txtP02n1.setEnabled(false);
					btnP02n2.setEnabled(true);
					txtP02n2.setEnabled(true);
					txtP02n3.setEnabled(true);	
				} 		
			}
		});
		
		btnP02n2.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				String fname = txtP02n2.getText();
				String fmenge = txtP02n3.getText();
				Double menge;
				try {
					menge = Double.parseDouble(fmenge);
				} catch (NumberFormatException e){
					menge = 0.0;
				}
				if (fname.equals("") || (menge == 0.0)) {
					lblP02n5.setText(GuiStrings.getGS("stat07", l));
				} else {
					if (Flow.containsName(fname)) {
						Flow akFlow = Flow.getInstance(fname);
						String mname = txtP02n1.getText();
						ProcessModule.getInstance(mname).addFluss(akFlow, FlowValueType.MeanValue, menge);
						ProcessModule.getInstance(mname).addFluss(akFlow, FlowValueType.LowerBound, menge);
						ProcessModule.getInstance(mname).addFluss(akFlow, FlowValueType.UpperBound, menge);
						txtP02n2.setEnabled(false);
						txtP02n3.setEnabled(false);
						btnP02n2.setEnabled(false);
						txtP02n4.setText(txtP02n3.getText());
						txtP02n4.setEnabled(true);
						txtP02n5.setText(txtP02n3.getText());
						txtP02n5.setEnabled(true);
						btnP02n4.setEnabled(true);
						btnP02n3.setEnabled(false);
						txtP02n4.setEnabled(true);
						int anzPFlow = ProcessModule.getInstance(mname).getProduktflussvektor().size();
						int anzEFlow = ProcessModule.getInstance(mname).getElementarflussvektor().size();
						int anzGesamt = anzPFlow + anzEFlow;
						lblP02n5.setText(GuiStrings.getGS("stat08", l) + mname + GuiStrings.getGS("stat09", l) +
								anzGesamt + GuiStrings.getGS("stat10", l));
						
					} else {
						lblP02n5.setText(GuiStrings.getGS("stat11", l));
					}					
				}
			}
		});
		
		btnP02n4.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				String fug = txtP02n4.getText();
				String fog = txtP02n5.getText();
				Double fugv;
				Double fogv;
				try {
					fugv = Double.parseDouble(fug);
				} catch (NumberFormatException e){
					fugv = 0.0;
				}
				try {
					fogv = Double.parseDouble(fog);
				} catch (NumberFormatException e){
					fogv = 0.0;
				}
				String fmenge = txtP02n3.getText();
				Double menge;
				try {
					menge = Double.parseDouble(fmenge);
				} catch (NumberFormatException e){
					menge = 0.0;
				}
				if ((fugv > menge) || (fogv < menge)) {
					txtP02n4.setText(txtP02n3.getText());
					txtP02n5.setText(txtP02n3.getText());
					lblP02n5.setText(GuiStrings.getGS("stat21", l));
				} else {
					String mname = txtP02n1.getText();
					String fname = txtP02n2.getText();
					Flow akFlow = Flow.getInstance(fname);
					ProcessModule.getInstance(mname).addFluss(akFlow, FlowValueType.LowerBound, fugv);
					ProcessModule.getInstance(mname).addFluss(akFlow, FlowValueType.UpperBound, fogv);
					txtP02n2.setText("");
					txtP02n3.setText("");
					txtP02n4.setText("");
					txtP02n5.setText("");
					txtP02n2.setEnabled(true);
					txtP02n3.setEnabled(true);
					txtP02n4.setEnabled(false);
					txtP02n5.setEnabled(false);
					btnP02n2.setEnabled(true);
					btnP02n3.setEnabled(true);
					btnP02n4.setEnabled(false);
					lblP02n5.setText(GuiStrings.getGS("stat01", l));
					
				}
			}			
		});
		
		btnP02n3.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				btnP02n1.setEnabled(true);
				txtP02n2.setText("");
				txtP02n3.setText("");
				txtP02n1.setText("");
				txtP02n4.setText("");
				txtP02n5.setText("");
				btnP02n2.setEnabled(false);
				btnP02n3.setEnabled(false);
				btnP02n4.setEnabled(false);
				txtP02n1.setEnabled(true);
				txtP02n2.setEnabled(false);
				txtP02n3.setEnabled(false);
				txtP02n4.setEnabled(false);
				txtP02n5.setEnabled(false);
				lblP02n5.setText(GuiStrings.getGS("stat01", l));
			}
		});

		/*
		 * neues Produktsystem
		 */
		
		btnP03n1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				String name = txtP03n1.getText();	
				boolean nameOk = true;
				if (name.equals("")) {
					nameOk = false;
					lblP03n7.setText(GuiStrings.getGS("stat02", l));
				} 
				if (name != name.replaceAll(" ","")) {
					nameOk = false;
					lblP03n7.setText(GuiStrings.getGS("stat20", l));
					txtP03n1.setText("");
				}	
				if (nameOk == true) {
					boolean nameVorhanden = false;
					
					for(String mod : ProcessModule.getAllInstances().keySet()) {
						if (name.equals(mod)) {
							nameVorhanden = true;
						}
					}
					for(String mod : ProductSystem.getAllInstances().keySet()) {
						if (name.equals(mod)) {
							nameVorhanden = true;
						}
					}
					if (nameVorhanden == true) {
						lblP03n7.setText(GuiStrings.getGS("stat03", l));
					} else {
						ProductSystem.instance(name, new HashMap<Flow, Double>(), 
								new LinkedList<Flow>());
						lblP03n7.setText(GuiStrings.getGS("stat12", l) + 
								ProductSystem.getAllInstances().size() + GuiStrings.getGS("stat05", l));

						btnP03n1.setEnabled(false);
						txtP03n1.setEnabled(false);
						btnP03n2.setEnabled(true);
						txtP03n2.setEnabled(true);
					}	
				} 		
			}
		});
		
		btnP03n2.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				String modname = txtP03n2.getText();
				if (modname.equals("") || modname.equals(txtP03n1.getText())) {
					if (modname.equals("")) {
						lblP03n7.setText(GuiStrings.getGS("stat07", l));
					}
					if (modname.equals(txtP03n1.getText())) {
						lblP03n7.setText(GuiStrings.getGS("stat13", l));
					}
				} else {
					boolean nameVorhanden = false;
					boolean typmod = false;
					for(String modn2 : ProcessModule.getAllInstances().keySet()) {
						if (modname.equals(modn2)) {
							nameVorhanden = true;
							typmod = true;
						}
					}
					if (nameVorhanden == false) {
						for(String modn3 : ProductSystem.getAllInstances().keySet()) {
							if (modname.equals(modn3)) {
								nameVorhanden = true;
								typmod = false;
							}
						}
					}
					if (nameVorhanden == true) {
						if (typmod == true){
							ProductSystem.getAllInstances().get(txtP03n1.getText()).addProzessmodul(ProcessModule.getInstance(modname));
						} else {
							ProductSystem.getAllInstances().get(txtP03n1.getText()).addProzessmodul(ProductSystem.getAllInstances().get(modname));
						}
						lblP03n7.setText(GuiStrings.getGS("stat14", l) + txtP03n1.getText() +
								GuiStrings.getGS("stat15", l) + ProductSystem.getAllInstances().get(txtP03n1.getText()).getModulAnzahl() 
								+ GuiStrings.getGS("stat16", l));
						txtP03n2.setText("");
						btnP03n3.setEnabled(true);
					} else {
						lblP03n7.setText(GuiStrings.getGS("stat17", l));
					}					
				}
			}
		});
		
		btnP03n3.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				txtP03n2.setEnabled(false);
				txtP03n2.setText("");
				btnP03n2.setEnabled(false);
				btnP03n3.setEnabled(false);
				txtP03n3.setEnabled(true);
				txtP03n4.setEnabled(true);
				btnP03n4.setEnabled(true);
				lblP03n7.setText(GuiStrings.getGS("stat01", l));
			}
		});
		
		btnP03n4.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				String fname = txtP03n3.getText();
				String fmenge = txtP03n4.getText();
				Double menge;
				try {
					menge = Double.parseDouble(fmenge);
				} catch (NumberFormatException e){
					menge = 0.0;
				}
				if (fname.equals("") || (menge == 0.0)) {
					lblP03n7.setText(GuiStrings.getGS("stat07", l));
				} else {
					if (Flow.containsName(fname)) {
						Flow akFlow = Flow.getInstance(fname);
						ProductSystem.getInstance(txtP03n1.getText()).addBedarf(akFlow, Double.parseDouble(txtP03n4.getText()));
						lblP03n7.setText(GuiStrings.getGS("stat18", l) + 
								ProductSystem.getInstance(txtP03n1.getText()).getBedarfsvektor().size() + GuiStrings.getGS("stat10", l));
						btnP03n5.setEnabled(true);	
						txtP03n3.setText("");
						txtP03n4.setText("");
					} else {
						lblP03n7.setText(GuiStrings.getGS("stat11", l));
					}					
				}
			}
		});
		
		btnP03n5.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				txtP03n2.setEnabled(false);
				txtP03n2.setText("");
				btnP03n2.setEnabled(false);
				btnP03n3.setEnabled(false);
				txtP03n3.setEnabled(false);
				txtP03n4.setEnabled(false);
				btnP03n5.setEnabled(false);
				txtP03n3.setText("");
				txtP03n4.setText("");
				btnP03n4.setEnabled(false);
				txtP03n5.setEnabled(true);
				btnP03n6.setEnabled(true);
				btnP03n7.setEnabled(true);
				
				lblP03n7.setText(GuiStrings.getGS("stat01", l));
			}
		});
		
		btnP03n6.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				String vkname = txtP03n5.getText();
				if (vkname.equals("")) {
					lblP03n7.setText(GuiStrings.getGS("stat07", l));
				} else {					
					if (Flow.containsName(vkname)) {
						Flow akFlow = Flow.getInstance(vkname);
						ProductSystem.getInstance(txtP03n1.getText()).addVuK(akFlow);
						txtP03n5.setText("");
						lblP03n7.setText(GuiStrings.getGS("stat19", l) + 
								ProductSystem.getInstance(txtP03n1.getText()).getVorUndKoppelprodukte().size() + GuiStrings.getGS("stat10", l));										
					} else {
						lblP03n7.setText(GuiStrings.getGS("stat11", l));
					}					
				}
			}
		});

		btnP03n7.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				txtP03n1.setEnabled(true);
				txtP03n1.setText("");
				btnP03n1.setEnabled(true);
				txtP03n2.setEnabled(false);
				txtP03n2.setText("");
				btnP03n2.setEnabled(false);
				btnP03n3.setEnabled(false);
				txtP03n3.setEnabled(false);
				txtP03n4.setEnabled(false);
				btnP03n5.setEnabled(false);
				txtP03n3.setText("");
				txtP03n4.setText("");
				btnP03n4.setEnabled(false);
				txtP03n5.setEnabled(false);
				txtP03n5.setText("");
				btnP03n6.setEnabled(false);
				btnP03n7.setEnabled(false);
				
				lblP03n7.setText(GuiStrings.getGS("stat01", l));
			}
			
		});
		
		/*
		 * Sprachauswahl
		 */
		
		btn05n1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				l = comboBox2.getItemAt(comboBox2.getSelectedIndex());
				frame.setTitle(GuiStrings.getGS("head1",l)+"   "+GuiStrings.getGS("head2",l));
				mnDatei.setText(GuiStrings.getGS("mp6",l));
				mnNew.setText(GuiStrings.getGS("mp1",l));
				mnListe.setText(GuiStrings.getGS("mp4",l));
				mnBerechnen.setText(GuiStrings.getGS("mp5",l));
				mnPrefs.setText(GuiStrings.getGS("mp3",l));
				mnHilfe.setText(GuiStrings.getGS("mp2",l));
				mntmFlow.setText(GuiStrings.getGS("mp11",l));
				mntmFlow.setToolTipText(GuiStrings.getGS("mp11e",l));
				mntmProcessModule.setText(GuiStrings.getGS("mp12",l));
				mntmProcessModule.setToolTipText(GuiStrings.getGS("mp12e",l));
				mntmProductSystem.setText(GuiStrings.getGS("mp13",l));
				mntmProductSystem.setToolTipText(GuiStrings.getGS("mp13e",l));
				mntmNewMenuItem_3.setText(GuiStrings.getGS("mp31",l));
				mntmNewMenuItem_3.setToolTipText(GuiStrings.getGS("mp31e",l));
				mntmFlsse.setText(GuiStrings.getGS("mp41",l));
				mntmFlsse.setToolTipText(GuiStrings.getGS("mp41e",l));
				mntmProzessmodule.setText(GuiStrings.getGS("mp42",l));
				mntmProzessmodule.setToolTipText(GuiStrings.getGS("mp42e",l));
				mntmProduktsysteme.setText(GuiStrings.getGS("mp43",l));
				mntmProduktsysteme.setToolTipText(GuiStrings.getGS("mp43e",l));
				mntmLci.setText(GuiStrings.getGS("mp51",l));
				mntmLci.setToolTipText(GuiStrings.getGS("mp51e",l));
				mntmNewMenuItem_2.setText(GuiStrings.getGS("mp21",l));
				mntmNewMenuItem_2.setToolTipText(GuiStrings.getGS("mp21e",l));
				mntmNewMenuItem_4.setText(GuiStrings.getGS("mp61",l));
				mntmNewMenuItem_4.setToolTipText(GuiStrings.getGS("mp61e",l));
				mntmNewMenuItem_5.setText(GuiStrings.getGS("mp62",l));
				mntmNewMenuItem_5.setToolTipText(GuiStrings.getGS("mp62e",l));
				lblP05n1.setText(GuiStrings.getGS("mp31e", l));
				lblP05n2.setText(GuiStrings.getGS("mp31", l));
				btn05n1.setText(GuiStrings.getGS("bt01", l));
			}
		});

	}


	
	/*
	 * Actions der Menupunkte
	 */

	private class newFlowAction extends AbstractAction {
		private static final long serialVersionUID = 3159283296670804375L;
		public newFlowAction() {
			putValue(NAME, GuiStrings.getGS("mp11", l));
			putValue(SHORT_DESCRIPTION, GuiStrings.getGS("mp11e", l));
		}
		public void actionPerformed(ActionEvent e) {			
			lblP01n1.setText(GuiStrings.getGS("p01n1", l));
			lblP01n2.setText(GuiStrings.getGS("p01n2", l));
			lblP01n3.setText(GuiStrings.getGS("p01n3", l));
			lblP01n4.setText(GuiStrings.getGS("p01n4", l));
			lblP01n5.setText(GuiStrings.getGS("stat01", l));
			btnP01n1.setText(GuiStrings.getGS("bt01", l));
			HashMap<FlowType, String> ft = FlowTypeStrings.getFTS(l);
			String[] fta = new String[ft.size()];
			int i=0;
			for (FlowType f : FlowType.values()) {
				String ftl = ft.get(f);
				fta[i] = ftl;
				i++;
			}
			comboBox.setModel(new DefaultComboBoxModel<String>(fta));
			cl.show(panel, "neuFluss");
						
		}
	}
	private class newModuleAction extends AbstractAction {
		private static final long serialVersionUID = 6190606710625748526L;
		public newModuleAction() {
			putValue(NAME, GuiStrings.getGS("mp12", l));
			putValue(SHORT_DESCRIPTION, GuiStrings.getGS("mp12e", l));
		}
		public void actionPerformed(ActionEvent e) {
			lblP02n1.setText(GuiStrings.getGS("p02n1", l));
			lblP02n2.setText(GuiStrings.getGS("p02n2", l));
			lblP02n3.setText(GuiStrings.getGS("p01n2", l));
			lblP02n4.setText(GuiStrings.getGS("p02n4", l));
			lblP02n5.setText(GuiStrings.getGS("stat01", l));
			lblP02n6.setText(GuiStrings.getGS("p02n5", l));
			lblP02n7.setText(GuiStrings.getGS("p02n6", l));
			btnP02n1.setText(GuiStrings.getGS("bt02", l));
			btnP02n2.setText(GuiStrings.getGS("bt03", l));
			btnP02n3.setText(GuiStrings.getGS("bt04", l));
			btnP02n4.setText(GuiStrings.getGS("bt10", l));
			cl.show(panel, "neuModul");
		}
	}
	private class newProductAction extends AbstractAction {
		private static final long serialVersionUID = -7757652453649226474L;
		public newProductAction() {
			putValue(NAME, GuiStrings.getGS("mp13", l));
			putValue(SHORT_DESCRIPTION, GuiStrings.getGS("mp13e", l));
		}
		public void actionPerformed(ActionEvent e) {
			lblP03n1.setText(GuiStrings.getGS("p03n1", l));
			lblP03n2.setText(GuiStrings.getGS("p03n2", l));
			lblP03n3.setText(GuiStrings.getGS("p03n3", l));
			lblP03n4.setText(GuiStrings.getGS("p03n4", l));
			lblP03n5.setText(GuiStrings.getGS("p02n4", l));
			lblP03n6.setText(GuiStrings.getGS("p03n6", l));
			lblP03n7.setText(GuiStrings.getGS("stat01", l));
			btnP03n1.setText(GuiStrings.getGS("bt05", l));
			btnP03n2.setText(GuiStrings.getGS("bt06", l));
			btnP03n3.setText(GuiStrings.getGS("bt07", l));
			btnP03n4.setText(GuiStrings.getGS("bt08", l));
			btnP03n5.setText(GuiStrings.getGS("bt07", l));
			btnP03n6.setText(GuiStrings.getGS("bt09", l));
			btnP03n7.setText(GuiStrings.getGS("bt04", l));
			cl.show(panel, "neuProdukt");
		}
	}
	
	private class aboutAction extends AbstractAction {
		private static final long serialVersionUID = 8545097902506476895L;
		public aboutAction() {
			putValue(NAME, GuiStrings.getGS("mp21", l));
			putValue(SHORT_DESCRIPTION, GuiStrings.getGS("mp21e", l));
		}
		public void actionPerformed(ActionEvent e) {
			lblInfo1.setText(GuiStrings.getGS("head1", l));
			lblInfo2.setText(GuiStrings.getGS("info1", l));
			lblInfo3.setText(GuiStrings.getGS("info2", l));
			lblInfo4.setText(GuiStrings.getGS("info3", l));
			lblInfo5.setText(GuiStrings.getGS("head2", l)+"     "+GuiStrings.getGS("date", l));			
			cl.show(panel, "leer");
		}
	}
	
	private class prefsAction extends AbstractAction {
		private static final long serialVersionUID = 8545097902306476895L;
		public prefsAction() {
			putValue(NAME, GuiStrings.getGS("mp31", l));
			putValue(SHORT_DESCRIPTION, GuiStrings.getGS("mp31e", l));
		}
		public void actionPerformed(ActionEvent e) {
			lblP05n1.setText(GuiStrings.getGS("mp31e", l));
			lblP05n2.setText(GuiStrings.getGS("mp31", l));
			btn05n1.setText(GuiStrings.getGS("bt01", l));			
			cl.show(panel, "lang");
		}
	}
	
	private class listFlowAction extends AbstractAction {
		private static final long serialVersionUID = 8545097902306456895L;
		public listFlowAction() {
			putValue(NAME, GuiStrings.getGS("mp41", l));
			putValue(SHORT_DESCRIPTION, GuiStrings.getGS("mp41e", l));
		}
		public void actionPerformed(ActionEvent e) {
			lblP06n1.setText(GuiStrings.getGS("mp41e", l));
			flowsTableModel.setRowCount(0);
			flowsTable.setModel(flowsTableModel);
			TableColumnModel tcm = flowsTable.getColumnModel();
			tcm.getColumn(0).setHeaderValue(GuiStrings.getGS("p06n1", l));
			tcm.getColumn(1).setHeaderValue(GuiStrings.getGS("p01n3", l));
			tcm.getColumn(2).setHeaderValue(GuiStrings.getGS("p01n4", l));
			for(String flussname : Flow.getAllInstances().keySet()) {
				Flow fluss = Flow.getInstance(flussname);			
				flowsTableModel.addRow(new Object[] {fluss.getName(), 
						FlowTypeStrings.getFTS(l).get(fluss.getType()), fluss.getEinheit()});			
			}			
			cl.show(panel, "listeFluss");
		}
	}
	
	private class listModuleAction extends AbstractAction {
		private static final long serialVersionUID = 8545097602306456895L;
		public listModuleAction() {
			putValue(NAME, GuiStrings.getGS("mp42", l));
			putValue(SHORT_DESCRIPTION, GuiStrings.getGS("mp42e", l));
		}
		public void actionPerformed(ActionEvent e) {
			lblP07n1.setText(GuiStrings.getGS("mp42e", l));
			pmTableModel.setRowCount(0);
			pmTable.setModel(pmTableModel);
			TableColumnModel tcm = pmTable.getColumnModel();
			tcm.getColumn(0).setHeaderValue(GuiStrings.getGS("mp12", l));
			tcm.getColumn(1).setHeaderValue(GuiStrings.getGS("mp11", l));
			tcm.getColumn(2).setHeaderValue(GuiStrings.getGS("p01n3", l));
			tcm.getColumn(3).setHeaderValue(GuiStrings.getGS("p02n4", l));
			for(String mn : ProcessModule.getAllInstances().keySet()) {
				ProcessModule akModul = ProcessModule.getInstance(mn);
				pmTableModel.addRow(new Object[] {mn, "", "", ""});
				for(Flow pf : akModul.getElementarflussvektor().keySet()){
					for (FlowValueType vt : akModul.getElementarflussvektor().get(pf).keySet()) {
						pmTableModel.addRow(new Object[] {"", pf.getName(), 
								FlowValueTypeStrings.getFVTS(l).get(vt),
								akModul.getElementarflussvektor().get(pf).get(vt)});
					}
				}						
				for(Flow pf : akModul.getProduktflussvektor().keySet()){
					for (FlowValueType vt : akModul.getProduktflussvektor().get(pf).keySet()) {
						pmTableModel.addRow(new Object[] {"", pf.getName(), 
								FlowValueTypeStrings.getFVTS(l).get(vt),
								akModul.getProduktflussvektor().get(pf).get(vt)});							
					}							
				}
			}	
			cl.show(panel, "listePM");
		}
	}
	
	private class listProductAction extends AbstractAction {
		private static final long serialVersionUID = 8545197602306456895L;
		public listProductAction() {
			putValue(NAME, GuiStrings.getGS("mp43", l));
			putValue(SHORT_DESCRIPTION, GuiStrings.getGS("mp43e", l));
		}
		public void actionPerformed(ActionEvent e) {	
			lblP08n1.setText(GuiStrings.getGS("mp43e", l));
			psTableModel.setRowCount(0);
			psTable.setModel(psTableModel);
			TableColumnModel tcm = psTable.getColumnModel();
			tcm.getColumn(0).setHeaderValue(GuiStrings.getGS("mp13", l));
			tcm.getColumn(1).setHeaderValue(GuiStrings.getGS("p08n1", l));
			tcm.getColumn(2).setHeaderValue(GuiStrings.getGS("p08n2", l));
			for(String psn : ProductSystem.getAllInstances().keySet()) {
				psTableModel.addRow(new Object[] {psn, "", ""});
				for (FlowValueMaps mnif : ProductSystem.getInstance(psn).getModulliste()){
					String mni = mnif.getName();
					boolean typmod = false;
					for(String modn2 : ProcessModule.getAllInstances().keySet()) {
						if (mni.equals(modn2)) {
							typmod = true;
						}
					}
					if (typmod == true){
						psTableModel.addRow(new Object[] {"",GuiStrings.getGS("mp12", l), mni});							
					} else {
						psTableModel.addRow(new Object[] {"",GuiStrings.getGS("p08n3", l), mni});	
					}					
				}
				for (Flow bvf : ProductSystem.getInstance(psn).getBedarfsvektor().keySet()) {
					psTableModel.addRow(new Object[] {"" ,GuiStrings.getGS("p08n4", l) 
							,"" + bvf.getName() + " (" + 
									ProductSystem.getInstance(psn).getBedarfsvektor().get(bvf) + 
							" " + bvf.getEinheit()+")"});
				}
				for (Flow vk : ProductSystem.getInstance(psn).getVorUndKoppelprodukte()) {
					psTableModel.addRow(new Object[] {"" ,GuiStrings.getGS("p03n6", l) 
							,vk.getName() });		
				}
			}
			cl.show(panel, "listePS");
		}
	}
	
	private class calculateAction extends AbstractAction {
		private static final long serialVersionUID = 8545197602406456695L;
		public calculateAction() {
			putValue(NAME, GuiStrings.getGS("mp51", l));
			putValue(SHORT_DESCRIPTION, GuiStrings.getGS("mp51e", l));
		}
		public void actionPerformed(ActionEvent e) {
			lblP09n1.setText(GuiStrings.getGS("mp51e", l));
			lciTableModel.setRowCount(0);
			lciTable.setModel(lciTableModel);
			TableColumnModel tcm = lciTable.getColumnModel();
			tcm.getColumn(0).setHeaderValue(GuiStrings.getGS("mp13", l));
			tcm.getColumn(1).setHeaderValue(GuiStrings.getGS("mp11", l));
			tcm.getColumn(2).setHeaderValue(GuiStrings.getGS("p01n3", l));
			tcm.getColumn(3).setHeaderValue(GuiStrings.getGS("p02n4", l));
			HashMap<Flow, HashMap<FlowValueType, Double>> sysErgebnis = new HashMap<Flow, HashMap<FlowValueType, Double>>();
			if (ProductSystem.getAllInstances().size() > 0) {
				for(String sysName : ProductSystem.getAllInstances().keySet()) {
					lciTableModel.addRow(new Object[] {sysName,"","",""});
					ProductSystem sysAktuell = ProductSystem.getAllInstances().get(sysName);
					try {
						if (sysAktuell.getElementarflussvektor().size() > 0) {
							sysErgebnis = sysAktuell.getElementarflussvektor();
							for(Flow sysFluss : sysErgebnis.keySet()){
								for (FlowValueType vt : sysAktuell.getElementarflussvektor().get(sysFluss).keySet()) {
									lciTableModel.addRow(new Object[] {"",sysFluss.getName(),"" + 
											FlowValueTypeStrings.getFVTS(l).get(vt),								
											sysErgebnis.get(sysFluss).get(vt) + " " + sysFluss.getEinheit() + ""});
								}
							}
						}
					} catch (ArithmeticException vz) {
							lciTableModel.addRow(new Object[] 
									{"",vz.getMessage(),"",""});					
					}					 
				}
			}
			cl.show(panel, "berechnen");
		}		
	}
}