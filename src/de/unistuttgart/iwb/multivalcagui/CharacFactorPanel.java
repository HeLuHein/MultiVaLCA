package de.unistuttgart.iwb.multivalcagui;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;

import de.unistuttgart.iwb.multivalca.CharacFactor;
import de.unistuttgart.iwb.multivalca.Flow;
import de.unistuttgart.iwb.multivalca.ImpactCategory;
import net.miginfocom.swing.MigLayout;

public class CharacFactorPanel extends MCAPanel{
	
	private JTextField txtP12n1 = new JTextField();		// Eingabefeld CF-Name
	private JTextField txtP12n2 = new JTextField();		// Eingabefeld Fluss-Name
	private JTextField txtP12n3 = new JTextField();		// Eingabefeld Wirkungskategorie
	private JTextField txtP12n4 = new JTextField();		// Eingabefeld Faktor
	private JTextField txtP12n5 = new JTextField();		// Eingabefeld Untergrenze
	private JTextField txtP12n6 = new JTextField();		// Eingabefeld Obergrenze
	private JLabel lblP12n1 = new JLabel(); 			// "Neuer Charakterisierungsfaktor"
	private JLabel lblP12n2 = new JLabel(); 			// "Name des Charakterisierungsfaktors"
	private JLabel lblP12n3 = new JLabel(); 			// "Name des Flusses"
	private JLabel lblP12n4 = new JLabel(); 			// "Name der Wirkungskategorie"
	private JLabel lblP12n5 = new JLabel(); 			// "Faktor"
	private JLabel lblP12n6 = new JLabel(); 			// "Untergrenze"
	private JLabel lblP12n7 = new JLabel(); 			// "Obergrenze"
	private JLabel lblP12n8 = new JLabel(); 			// Status
	private JButton btnP12n1 = new JButton(); 			// "speichern"
	private JButton btnP12n2 = new JButton(); 			// "Grenzwerte best�tigen"

	protected CharacFactorPanel(String key) {
		super(key);
		initUI();
	}

	private void initUI() {
		Language l = GUILanguage.getChosenLanguage();
		setLayout(new MigLayout("", "[108px,grow][108px][108px][108px,grow]", 
				"[20px][20px][20px][20px][20px][20px][20px][20px][20px][20px][20px][20px,grow]"));		
		lblP12n1.setFont(new Font("Tahoma", Font.BOLD, 14));
		add(lblP12n1, "flowy,cell 1 0 2 1,alignx center,growy");	
		add(lblP12n2, "cell 1 1,grow");		
		txtP12n1.setText("");
		add(txtP12n1, "cell 2 1,grow");
		txtP12n1.setColumns(10);					
		add(lblP12n3, "cell 1 2,grow");	
		txtP12n2.setText("");
		add(txtP12n2, "cell 2 2,grow");
		txtP12n2.setColumns(10);		
		add(lblP12n4, "cell 1 3,grow");
		txtP12n3.setText("");
		add(txtP12n3, "cell 2 3,grow");
		txtP12n3.setColumns(10);		
		add(lblP12n5, "cell 1 4,grow");
		txtP12n4.setText("");
		add(txtP12n4, "cell 2 4,grow");
		txtP12n4.setColumns(10);	
		add(btnP12n1, "cell 1 5 2 1,alignx center");		
		add(lblP12n6, "cell 1 6,grow");	
		txtP12n5.setText("");
		add(txtP12n5, "cell 2 6,grow");
		txtP12n5.setColumns(10);
		txtP12n5.setEnabled(false);			
		add(lblP12n7, "cell 1 7,grow");
		txtP12n6.setText("");
		add(txtP12n6, "cell 2 7,grow");
		txtP12n6.setColumns(10);		
		txtP12n6.setEnabled(false);			
		btnP12n2.setEnabled(false);
		add(btnP12n2, "cell 1 8 2 1,alignx center");
		add(lblP12n8, "cell 0 9 4 1,alignx center");	
		
		btnP12n1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				String nameCF = txtP12n1.getText();
				String nameFl = txtP12n2.getText();
				String nameWK = txtP12n3.getText();
				String facText = txtP12n4.getText();
				boolean inputOK = true;
				if (nameCF.equals("") || nameFl.equals("") || nameWK.equals("") || facText.equals("")) {
					lblP12n8.setText(GuiStrings.getGS("stat07",l));
					inputOK = false;					
				}
				Double facVal = 0.0;
				try {
					facVal = Double.parseDouble(facText);
				} catch (NumberFormatException e){
					facVal = 0.0;
				}
				if (facVal <= 0.0) {
					lblP12n8.setText(GuiStrings.getGS("stat26",l));
					inputOK = false;	
				}
				if (ImpactCategory.containsName(nameWK) == false) {
					lblP12n8.setText(GuiStrings.getGS("stat27",l));
					inputOK = false;
				}
				if (Flow.containsName(nameFl) == false) {
					lblP12n8.setText(GuiStrings.getGS("stat11",l));
					inputOK = false;
				}
				if (CharacFactor.containsName(nameCF)) {
					lblP12n8.setText(GuiStrings.getGS("stat03",l));
					inputOK = false;
				}
				if (inputOK == true) {
					CharacFactor.instance(nameCF, Flow.getInstance(nameFl), ImpactCategory.getInstance(nameWK), facVal);
					txtP12n1.setEnabled(false);
					txtP12n2.setEnabled(false);
					txtP12n3.setEnabled(false);
					txtP12n4.setEnabled(false);
					txtP12n5.setText(facText);
					txtP12n5.setEnabled(true);
					txtP12n6.setText(facText);
					txtP12n6.setEnabled(true);
					btnP12n1.setEnabled(false);
					btnP12n2.setEnabled(true);
				}				
			}			
		});
		
		btnP12n2.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				String lbText = txtP12n5.getText();
				String obText = txtP12n6.getText();
				Double lbv;
				Double obv;
				try {
					lbv = Double.parseDouble(lbText);
				} catch (NumberFormatException e){
					lbv = 0.0;
				}
				try {
					obv = Double.parseDouble(obText);
				} catch (NumberFormatException e){
					obv = 0.0;
				}
				String facText = txtP12n4.getText();
				Double facVal = Double.parseDouble(facText);
				if ((lbv > facVal) || (obv < facVal) || (lbv < 0)) {
					txtP12n5.setText(txtP12n4.getText());
					txtP12n6.setText(txtP12n4.getText());
					lblP12n8.setText(GuiStrings.getGS("stat21", l));
				} else {
					String nameCF = txtP12n1.getText();
					CharacFactor.setLowerBound(nameCF, lbv);
					CharacFactor.setUpperBound(nameCF, obv);
					txtP12n1.setText("");
					txtP12n2.setText("");
					txtP12n3.setText("");
					txtP12n4.setText("");
					txtP12n5.setText("");
					txtP12n6.setText("");
					txtP12n1.setEnabled(true);
					txtP12n2.setEnabled(true);
					txtP12n3.setEnabled(true);
					txtP12n4.setEnabled(true);
					txtP12n5.setEnabled(false);
					txtP12n6.setEnabled(false);
					btnP12n1.setEnabled(true);
					btnP12n2.setEnabled(false);		
					lblP12n8.setText(GuiStrings.getGS("stat01", l));
				}
				
			}
			
		});		
		
	}

	@Override
	public void showSelf() {
		Language l = GUILanguage.getChosenLanguage();
		lblP12n1.setText(GuiStrings.getGS("p12n1", l));
		lblP12n2.setText(GuiStrings.getGS("p12n2", l));
		lblP12n3.setText(GuiStrings.getGS("p01n2", l));
		lblP12n4.setText(GuiStrings.getGS("p10n2", l));
		lblP12n5.setText(GuiStrings.getGS("p12n3", l));
		lblP12n6.setText(GuiStrings.getGS("p02n5", l));
		lblP12n7.setText(GuiStrings.getGS("p02n6", l));
		lblP12n8.setText(GuiStrings.getGS("stat01", l));
		btnP12n1.setText(GuiStrings.getGS("bt01", l));
		btnP12n2.setText(GuiStrings.getGS("bt10", l));
		
	}

}