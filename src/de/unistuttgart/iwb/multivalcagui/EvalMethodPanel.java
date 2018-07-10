/*	
 * MultiVaLCA
 */

package de.unistuttgart.iwb.multivalcagui;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Locale;
import java.util.ResourceBundle;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;

import de.unistuttgart.iwb.multivalca.CharacFactor;
import de.unistuttgart.iwb.multivalca.ImpactCategory;
import de.unistuttgart.iwb.multivalca.LCIAMethod;
import net.miginfocom.swing.MigLayout;

/**
 * @author HH, JS
 * @version 0.537
 */

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
	private JButton btnP14n2 = new JButton(); 			// Wirkungskategorie "hinzufügen"
	private JButton btnP14n3 = new JButton(); 			// Charakterisierungsfaktor "hinzufügen"
	private JButton btnP14n4 = new JButton(); 			// "fertig"
	private Language l = GUILanguage.getChosenLanguage();
	private Locale locale = MultiVaLCA.LANGUAGES.get(l);
	private String baseName = "de.unistuttgart.iwb.multivalcagui.messages";
	private ResourceBundle bundle = ResourceBundle.getBundle(baseName, locale);

	public EvalMethodPanel(String key) {
		super(key);
		initUI();
	}

	private void initUI() {
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
				if ("".equals(nameILCA)) {
					lblP14n5.setText(bundle.getString("stat02"));
					inputOK = false;					
				}
				if (LCIAMethod.containsName(nameILCA)) {
					lblP14n5.setText(bundle.getString("stat03"));
					inputOK = false;
				}
				if (inputOK) {
					LCIAMethod.instance(nameILCA);
					txtP14n1.setEnabled(false);
					txtP14n2.setEnabled(true);
					txtP14n3.setEnabled(true);
					btnP14n1.setEnabled(false);
					btnP14n2.setEnabled(true);
					btnP14n3.setEnabled(true);
					lblP14n5.setText(bundle.getString("stat28") + 
							LCIAMethod.instance(nameILCA).categoryList().size() +
							bundle.getString("stat29") +
							LCIAMethod.instance(nameILCA).getFactorSet().size() +
							bundle.getString("stat05"));
				}			
			}			
		});

		btnP14n2.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String nameILCA = txtP14n1.getText();
				String nameWK = txtP14n2.getText();
				boolean inputOK = true;
				if (!ImpactCategory.containsName(nameWK)) {
					lblP14n5.setText(bundle.getString("stat27"));
					inputOK = false;					
				}
				if (inputOK) {
					LCIAMethod.instance(nameILCA).addCategory(ImpactCategory.getInstance(nameWK));
					btnP14n4.setEnabled(true);
					lblP14n5.setText(bundle.getString("stat28") + 
							LCIAMethod.instance(nameILCA).categoryList().size() +
							bundle.getString("stat29") +
							LCIAMethod.instance(nameILCA).getFactorSet().size() +
							bundle.getString("stat05"));
				}			
			}
			
		});
		btnP14n3.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String nameILCA = txtP14n1.getText();
				String nameCF = txtP14n3.getText();
				boolean inputOK = true;
				if (!CharacFactor.containsName(nameCF)) {
					lblP14n5.setText(bundle.getString("stat30"));
					inputOK = false;					
				}
				if (inputOK) {
					LCIAMethod.instance(nameILCA).addFactor(CharacFactor.getInstance(nameCF));
					btnP14n4.setEnabled(true);				
					lblP14n5.setText(bundle.getString("stat28") + 
							LCIAMethod.instance(nameILCA).categoryList().size() +
							bundle.getString("stat29") +
							LCIAMethod.instance(nameILCA).getFactorSet().size() +
							bundle.getString("stat05"));
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
				lblP14n5.setText(bundle.getString("stat01"));
			}
		});	
	}

	@Override
	public void showSelf() {
		l = GUILanguage.getChosenLanguage();
		locale = MultiVaLCA.LANGUAGES.get(l);
		baseName = "de.unistuttgart.iwb.multivalcagui.messages";
		bundle = ResourceBundle.getBundle(baseName, locale);
		lblP14n1.setText(bundle.getString("p14n1"));
		lblP14n2.setText(bundle.getString("p14n2"));
		lblP14n3.setText(bundle.getString("mp14"));
		lblP14n4.setText(bundle.getString("mp15"));
		lblP14n5.setText(bundle.getString("stat01"));			
		btnP14n1.setText(bundle.getString("bt01"));
		btnP14n2.setText(bundle.getString("bt11"));	
		btnP14n3.setText(bundle.getString("bt11"));	
		btnP14n4.setText(bundle.getString("bt04"));	
		
	}

}

