/*	
 * MultiVaLCA
 */

package de.unistuttgart.iwb.multivalca;
import java.util.HashMap;

/**
 * Diese Klasse dient zur Erzeugung von Prozessmodulen.
 * 
 * @author Dr.-Ing. Joachim Schwarte
 * @version 0.24
 */

public class ProcessModule 
implements FlowValueMaps {
	
	// Klassenvariable:
	
	private static HashMap<String, ProcessModule> allInstances = new HashMap<String, ProcessModule>();
	
	// Instanzvariablen:
	
	private String name;
	private HashMap<Flow, HashMap<FlowValueType, Double>> efv = 
			new HashMap<Flow, HashMap<FlowValueType, Double>>(); //Elementarfl�sse
	private HashMap<Flow, HashMap<FlowValueType, Double>> pfv = 
			new HashMap<Flow, HashMap<FlowValueType, Double>>(); //Produktfl�sse
	
	// Konstruktor:
			
	private ProcessModule(String name) {
		super();
		setName(name);
		NameCheck.getInstance().addFVName(getName());
		NameCheck.getInstance().addWVName(getName());
		allInstances.put(getName(), this);
	}
	
	// Methoden:
	
	/**
	 * L�scht alle Klassenvariablen
	 */
	
	public static void clear() {
		allInstances.clear();
	}
	
	/**
	 * �berpr�ft, ob bereits ein Prozessmodul
	 * des genannten Namens existiert.
	 * @param string
	 * ist der zu pr�fende Name
	 * @return
	 * ... den Wahrheitswert, den die �berpr�fung liefert
	 */
	
	public static boolean containsName(String string) {
		return allInstances.containsKey(string);
	}
	
	/**
	 * @return
	 * ... alle vorhandenen Prozessmodule
	 */
	
	public static HashMap<String, ProcessModule> getAllInstances() {
		return allInstances;
	}
	
	/**
	 * Liefert ein Prozessmodul
	 * @param string
	 * Name des Prozessmoduls
	 * @return
	 * ... das gesuchte Prozessmodul
	 */
	
	public static ProcessModule getInstance(String string) {
		return allInstances.get(string);		
	}
	
	/**
	 * Erzeugt ein leeres benanntes Prozessmodul durch Aufruf des
	 * privaten Konstruktors sofern noch kein Prozessmodul gleichem
	 * Namens existiert. Ansonsten wird das existierende Prozessmodul
	 * zur�ckgegeben.
	 * @param name
	 * Der Name des Prozessmoduls
	 * @return
	 * ... das Prozessmodul
	 */
	
	public static ProcessModule instance(String name) {
		if (allInstances.containsKey(name) == false) {
			new ProcessModule(name);
		}
		return allInstances.get(name);
	}
	
	/**
	 * L�scht ein Prozessmodul
	 * @param string
	 * Name des zu l�schenden Prozessmoduls
	 */
	
	public static void removeInstance(String string) {
		allInstances.remove(string);
		NameCheck.removeFVName(string);
		NameCheck.removeWVName(string);
	}
	
	/**
	 * F�gt dem Prozessmodul einen quantifizierten Fluss hinzu.
	 * Die Methode kann f�r alle Flusstypen verwendet werden.
	 * @param fluss
	 * Der Fluss, der dem Prozessmodul hinzugef�gt werden soll
	 * @param wert
	 * Die Menge, die in das Prozessmodul hinein (negatives
	 * Vorzeichen) oder aus dem Prozessmodul hinaus (positives 
	 * Vorzeichen) flie�t.
	 */
	
	public void addFluss(Flow fluss, FlowValueType fvt, Double wert) {	
		if (fluss.getType() == FlowType.Elementary) {
			HashMap<FlowValueType, Double> valueMap = efv.get(fluss);
			if (valueMap == null) {
				valueMap = new HashMap<FlowValueType, Double>();
			}
			valueMap.put(fvt, wert);
			efv.put(fluss, valueMap);
		} else { 
			HashMap<FlowValueType, Double> valueMap = pfv.get(fluss);
			if (valueMap == null) {
				valueMap = new HashMap<FlowValueType, Double>();
			}
			valueMap.put(fvt, wert);
			pfv.put(fluss, valueMap);
		}
	}
	
	@Override
	public HashMap<Flow, HashMap<FlowValueType, Double>> getElementarflussvektor() {
		return efv; 
	}	
	
	@Override
	public String getName() {
		return name;
	}
	
	@Override
	public HashMap<Flow, HashMap<FlowValueType, Double>> getProduktflussvektor() {
		return pfv; 
	}
	

	
	/**
	 * Entfernt einen Fluss aus dem betroffenen Flussvektor.
	 * 
	 * @param fluss
	 * der zu entfernende Flu�
	 */
	
	public void removeFluss(Flow fluss) {
		efv.remove(fluss);
		pfv.remove(fluss);
	}

	public void setName(String string) {
		NameCheck.removeFVName(this.name);
		NameCheck.removeWVName(this.name);
		NameCheck.getInstance().addFVName(string);
		NameCheck.getInstance().addWVName(string);
		this.name = string;	
	}
}

