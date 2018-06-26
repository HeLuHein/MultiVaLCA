package de.unistuttgart.iwb.multivalcagui;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;

import de.unistuttgart.iwb.multivalca.CharacFactor;
import de.unistuttgart.iwb.multivalca.ImpactCategory;
import de.unistuttgart.iwb.multivalca.LCIAMethod;
import net.miginfocom.swing.MigLayout;

public class EvalMethodPanel extends MCAPanel{
	
	private JTextField txtP14n1 = new JTextField();		// Eingabefeld LCIA-Name
	private JTextField txtP14n2 = new JTextField();		// Eingabefeld Kategorie
	private JTextField txtP14n3 = new JTextField();		// Eingabefeld CF-Name
	private JLabel lblP14n1 = new JLabel(); 			// "Neue Bewertungsmethode"
	private JLabel lblP14n2 = new JLabel(); 			// "Name der Bewertungsmethode"
	private JLabel lblP14n3 = new JLabel(); 			// "Wirkungskategorie"
	private JLabel lblP14n4 = new JLabel(); 			// "Charakterisierungsfaktor"
	private JLabel lblP14n5 = new JLabel(); 			// Status
	private JButton btnP14n1 = new JButton(); 			// "neue Bewertungsmethode anlegen"
	private JButton btnP14n2 = new JButton(); 			// Wirkungskategorie "hinzuf�gen"
	private JButton btnP14n3 = new JButton(); 			// Charakterisierungsfaktor "hinzuf�gen"
	private JButton btnP14n4 = new JButton(); 			// "fertig"

	public EvalMethodPanel(String key) {
		super(key);
		initUI();
	}

	private void initUI() {
		Language l = GUILanguage.getChosenLanguage(); //nun zweimal initiiert
		setLayout(new MigLayout("", "[108px,grow][108px][108px][108px,grow]", 
				"[20px][20px][20px][20px][20px][20px][20px][20px][20px][20px][20px][20px,grow]"));		
		lblP14n1.setFont(new Font("Tahoma", Font.BOLD, 14));
		add(lblP14n1, "flowy,cell 1 0 2 1,alignx center,growy");		
		add(lblP14n2, "cell 1 1,grow");		
		txtP14n1.setText("");
		add(txtP14n1, "cell 2 1,grow");
		txtP14n1.setColumns(10);			
		btnP14n1.setEnabled(true);
		add(btnP14n1, "cell 1 2 2 1,alignx center");			
		add(lblP14n3, "cell 1 3,grow");		
		txtP14n2.setText("");
		add(txtP14n2, "cell 2 3,grow");
		txtP14n2.setColumns(10);	
		txtP14n2.setEnabled(false);		
		btnP14n2.setEnabled(false);
		add(btnP14n2, "cell 1 4 2 1,alignx center");				
		add(lblP14n4, "cell 1 5,grow");		
		txtP14n3.setText("");
		add(txtP14n3, "cell 2 5,grow");
		txtP14n3.setColumns(10);	
		txtP14n3.setEnabled(false);		
		btnP14n3.setEnabled(false);
		add(btnP14n3, "cell 1 6,alignx center");		
		btnP14n4.setEnabled(false);
		add(btnP14n4, "cell 2 6,alignx center");			
		add(lblP14n5, "cell 0 7 4 1,alignx center");	
		
		btnP14n1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				String nameILCA = txtP14n1.getText();
				boolean inputOK = true;
				if (nameILCA.equals("")) {
					lblP14n5.setText(GuiStrings.getGS("stat02",l));
					inputOK = false;					
				}
				if (LCIAMethod.containsName(nameILCA)) {
					lblP14n5.setText(GuiStrings.getGS("stat03",l));
					inputOK = false;
				}
				if (inputOK == true) {
					LCIAMethod.instance(nameILCA);
					txtP14n1.setEnabled(false);
					txtP14n2.setEnabled(true);
					txtP14n3.setEnabled(true);
					btnP14n1.setEnabled(false);
					btnP14n2.setEnabled(true);
					btnP14n3.setEnabled(true);
					lblP14n5.setText(GuiStrings.getGS("stat28",l) + 
							LCIAMethod.instance(nameILCA).categoryList().size() +
							GuiStrings.getGS("stat29",l) +
							LCIAMethod.instance(nameILCA).getFactorSet().size() +
							GuiStrings.getGS("stat05",l));
				}			
			}			
		});

		btnP14n2.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String nameILCA = txtP14n1.getText();
				String nameWK = txtP14n2.getText();
				boolean inputOK = true;
				if (ImpactCategory.containsName(nameWK) == false) {
					lblP14n5.setText(GuiStrings.getGS("stat27",l));
					inputOK = false;					
				}
				if (inputOK == true) {
					LCIAMethod.instance(nameILCA).addCategory(ImpactCategory.getInstance(nameWK));
					btnP14n4.setEnabled(true);
					lblP14n5.setText(GuiStrings.getGS("stat28",l) + 
							LCIAMethod.instance(nameILCA).categoryList().size() +
							GuiStrings.getGS("stat29",l) +
							LCIAMethod.instance(nameILCA).getFactorSet().size() +
							GuiStrings.getGS("stat05",l));
				}			
			}
			
		});
		btnP14n3.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String nameILCA = txtP14n1.getText();
				String nameCF = txtP14n3.getText();
				boolean inputOK = true;
				if (CharacFactor.containsName(nameCF) == false) {
					lblP14n5.setText(GuiStrings.getGS("stat30",l));
					inputOK = false;					
				}
				if (inputOK == true) {
					LCIAMethod.instance(nameILCA).addFactor(CharacFactor.getInstance(nameCF));
					btnP14n4.setEnabled(true);				
					lblP14n5.setText(GuiStrings.getGS("stat28",l) + 
							LCIAMethod.instance(nameILCA).categoryList().size() +
							GuiStrings.getGS("stat29",l) +
							LCIAMethod.instance(nameILCA).getFactorSet().size() +
							GuiStrings.getGS("stat05",l));
				}
			}
		});
		
		btnP14n4.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				btnP14n1.setEnabled(true);
				btnP14n2.setEnabled(false);
				btnP14n3.setEnabled(false);
				btnP14n4.setEnabled(false);
				txtP14n1.setEnabled(true);
				txtP14n2.setEnabled(false);
				txtP14n3.setEnabled(false);
				txtP14n1.setText("");
				txtP14n2.setText("");
				txtP14n3.setText("");
				lblP14n5.setText(GuiStrings.getGS("stat01", l));
			}
		});	
	}

	@Override
	public void showSelf() {
		Language l = GUILanguage.getChosenLanguage();
		lblP14n1.setText(GuiStrings.getGS("p14n1", l));
		lblP14n2.setText(GuiStrings.getGS("p14n2", l));
		lblP14n3.setText(GuiStrings.getGS("mp14", l));
		lblP14n4.setText(GuiStrings.getGS("mp15", l));
		lblP14n5.setText(GuiStrings.getGS("stat01", l));			
		btnP14n1.setText(GuiStrings.getGS("bt01", l));
		btnP14n2.setText(GuiStrings.getGS("bt11", l));	
		btnP14n3.setText(GuiStrings.getGS("bt11", l));	
		btnP14n4.setText(GuiStrings.getGS("bt04", l));	
		
	}

}
