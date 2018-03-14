/*	
 * MultiVaLCA
 */

package de.unistuttgart.iwb.multivalca;

import java.util.HashMap;

/**
 * @author Dr.-Ing. Joachim Schwarte
 * @version 0.47
 */

public class LCIAMethod {
	
	// Klassenvariable:
	
	private static HashMap<String, LCIAMethod> allLMs = new HashMap<String, LCIAMethod>();
	
	// Instanzvariablen:
	
	private String name;
	private HashMap<String, CharacFactor> faktorSet = 
			new HashMap<String, CharacFactor>();
	private HashMap<String, ImpactCategory> wkl = 
			new HashMap<String, ImpactCategory>();

	// Konstruktor:
	
	private LCIAMethod(String name) {
		super();
		this.name = name;
		allLMs.put(name, this);
	}
	
	// Methoden:
	
	/**
	 * L�scht alle Klassenvariablen
	 */
	
	public static void clear() {
		allLMs.clear();
	}
	
	/**
	 * �berpr�ft, ob bereits eine Bewertungsmethode
	 * des genannten Namens existiert.
	 * @param string
	 * ist der zu pr�fende Name
	 * @return
	 * ... den Wahrheitswert, den die �berpr�fung liefert
	 */
	
	public static boolean containsName(String string) {
		return allLMs.containsKey(string);
	}
	
	/**
	 * @return
	 * ... alle vorhandenen Bewertungsmethoden
	 */
	 
	public static HashMap<String, LCIAMethod> getAllLMs() {
		return allLMs;
	}
	
	/**
	 * Die instance-Methode erzeugt unter Verwendung des
	 * privaten Konstruktors eine neue Bewertungsmethode
	 * oder gibt eine bereits existierende Bewertungsmethode
	 * zur�ck.
	 * @param name
	 * ist der Name der Bewertungsmethode.
	 * @return
	 * ... neue oder bereits zuvor existierende
	 * Bewertungsmethode
	 */
	
	public static LCIAMethod instance(String name) {
		if (allLMs.containsKey(name) == false) {
			new LCIAMethod(name);
		} 
		return allLMs.get(name);
	}
	
	/**
	 * F�gt der Bewertungsmethode einen weiteren
	 * Charakterisierungsfaktor hinzu.
	 * @param cv
	 * Der Charakterisierungsfaktor, der der Bewertungsmethode 
	 * hinzugef�gt werden soll
	 */
	
	public void addFactor(CharacFactor cv) {
		faktorSet.put(cv.getName(), cv);
		wkl.put(cv.getWirkung().getName(), cv.getWirkung());	
	}

	/**
	 * F�gt der Liste der Wirkungskategorien einen Eintrag
	 * hinzu.
	 * @param wk
	 * Die Wirkungskategorie, die der Liste hinzugef�gt werden 
	 * soll.
	 */
	
	public void addCategory(ImpactCategory wk) {
		wkl.put(wk.getName(), wk);
	}
	
	/**
	 * @return
	 * ... Liste aller vorhandenen Charakterisierungsfaktoren
	 */
	
	public HashMap<String, CharacFactor> getFactorSet() {
		return faktorSet;
	}
	
	/**
	 * @return
	 * ... den Namen der Bewertugsmethode
	 */
	
	public String getName() {
		return name;
	}
		
	/**
	 * @return
	 * ... Liste derjenigen Wirkungskategorien, denen durch die
	 * vorhandenen Charakterisierungsfaktoren Fl�sse quantifiziert
	 * zugeordnet sind oder die durch Verwendung der Methode
	 * addWK(...) explizit der Liste hinzugef�gt wurden.
	 */
	
	public HashMap<String, ImpactCategory> categoryList() {	
		return wkl;
	}
	
		

}
