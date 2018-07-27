/*	
 * MultiVaLCA
 */

package de.unistuttgart.iwb.multivalca;

import java.util.LinkedHashMap;

/**
 * Dieses Interface muss von all jenen Klassen implementiert werden,
 * zu deren Objekten jeweils ein Wirkungsvektor angegeben oder 
 * berechnet werden kann, so dass diese Objekte als Komponenten 
 * innerhalb einer Produktkomposition auftreten k�nnen.
 * 
 * @author Dr.-Ing. Joachim Schwarte
 * @version 0.554
 */

public interface ImpactValueMaps {
	
	public LinkedHashMap<ImpactCategory, LinkedHashMap<ValueType, Double>> getImpactValueMap(LCIAMethod bm);
	public String getName();
	public void setName(String string);

}
