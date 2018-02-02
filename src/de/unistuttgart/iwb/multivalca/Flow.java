/*	
 * MultiVaLCA
 */

package de.unistuttgart.iwb.multivalca;

import java.util.HashMap;
import java.util.Set;

/**
 * Diese Klasse dient zur Erzeugung von Flussobjekten.
 * Es stehen Zugriffsmethoden zur Abfrage der privaten
 * Instanzvariablen zur Verf�gung.
 * 
 * @author Dr.-Ing. Joachim Schwarte
 * @version 0.1
 */

public class Flow {	
	
	// Klassenvariable:
	
	private static HashMap<String,Flow> allInstances = new HashMap<String,Flow>();
	
	// Instanzvariablen:	

	private String name;
	private FlowType typ; 
	private FlowUnit einheit;
	
	// Konstruktor:
	
	private Flow(String name, FlowType typ, FlowUnit einheit) {
		this.name = name;
		this.typ = typ;
		this.einheit = einheit;
		allInstances.put(name, this);
	}
	
	// Methoden:
	
	/**
	 * L�scht alle Klassenvariablen
	 */
	
	public static void clear() {
		allInstances.clear();
	}
	
	/**
	 * �berpr�ft, ob bereits ein Fluss-Objekt des gegebenen
	 * Namens existiert.
	 * @param string
	 * ist der zu pr�fende Name
	 * @return
	 * ... den Wahrheitswert, den die �berpr�fung liefert
	 */
	
	public static boolean containsName(String string) {
		return allInstances.keySet().contains(string);
	}
	
	/**
	 * @return
	 * ... alle vorhandenen Flussobjekte
	 */
	
	public static HashMap<String,Flow> getAllInstances() {
		return allInstances;
	}
	
	/**
	 * @return
	 * ... die Namen aller vorhandenen Flussobjekte
	 */
	
	public static Set<String> getAllNames() {
		return allInstances.keySet();
	}
	
	/**
	 * @param name
	 * Name des gesuchten Flusses
	 * @return
	 * ... ein Flussobjekt
	 */
	
	public static Flow getInstance(String name) {
		return allInstances.get(name);
	}
	
	/**
	 * Erzeugt ein vollst�ndiges Flussobjekt durch Aufruf des
	 * privaten Konstruktors sofern noch kein Fluss gleichem
	 * Namens existiert. Ansonsten wird der existierende Fluss
	 * zur�ckgegeben.
	 * @param name
	 * kann frei gew�hlt werden.
	 * Auf Anwendungsebene ist Namenseindeutigkeit anzustreben. 
	 * @param typ
	 * dient vorrangig zur Unterscheidung von Elementar- 
	 * und Produktfl�ssen.
	 * @param einheit
	 * legt die physikalische Einheit fest, in der der
	 * Fluss quantifiziert wird.
	 * @return
	 * ... das Flussobjekt
	 */
	
	public static Flow instance(String name, FlowType typ, FlowUnit einheit) {
		if (allInstances.containsKey(name) == false) {
			new Flow(name, typ, einheit);
		}
		return allInstances.get(name);
	}
	
	/**
	 * @return
	 * ... die physikalische Einheit, in der die zugeh�rigen
	 * Fl�sse quantifiziert werden.
	 */
	
	public FlowUnit getEinheit() {
		return einheit;
	}
	
	/**
	 * @return
	 * ... den Namen des Flussobjektes.
	 */
	
	public String getName() {
		return name;
	}
	
	/**
	 * @return
	 * ... den Typ des Flussobjektes.
	 */
	
	public FlowType getTyp() {
		return typ;
	}
}
