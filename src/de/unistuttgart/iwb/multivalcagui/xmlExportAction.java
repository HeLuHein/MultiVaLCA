/*	
 * MultiVaLCA
 */
package de.unistuttgart.iwb.multivalcagui;

import java.awt.event.ActionEvent;
import java.io.File;

import javax.swing.AbstractAction;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.apache.commons.io.FilenameUtils;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import de.unistuttgart.iwb.multivalca.*;

/**
 * @author Dr.-Ing. Joachim Schwarte
 * @version 0.31
 */

class xmlExportAction extends AbstractAction {

	private static final long serialVersionUID = -4213564766954115318L;
	public xmlExportAction(Language l) {
		putValue(NAME, GuiStrings.getGS("mp61", l));
		putValue(SHORT_DESCRIPTION, GuiStrings.getGS("mp61e", l));
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document document = builder.newDocument();
        
            Element root = document.createElement("XML");
            document.appendChild(root);
            
            Element allefluesse = document.createElement("Flows");
            root.appendChild(allefluesse);
            
            for(String pfname : Flow.getAllInstances().keySet()) {
            	Flow pf = Flow.getInstance(pfname); 
            	Element fluss = document.createElement("Flow");
            	allefluesse.appendChild(fluss);
            	Element name = document.createElement("FlowName");
	            fluss.appendChild(name);
	            name.appendChild(document.createTextNode(pf.getName()));
	            Element typ = document.createElement("FlowType");
	            fluss.appendChild(typ);
	            typ.appendChild(document.createTextNode(pf.getType().toString()));
	            Element einheit = document.createElement("FlowUnit");
	            fluss.appendChild(einheit);
	            einheit.appendChild(document.createTextNode(pf.getEinheit().toString()));
			}
            
         // JFileChooser-Objekt erstellen
	        JFileChooser chooser = new JFileChooser();
	        FileFilter filter = new FileNameExtensionFilter("XML-Dateien (*.xml)", "xml");
	        chooser.setFileFilter(filter);

	        // Dialog zum Speichern von Dateien anzeigen
	        int rueckgabeWert = chooser.showSaveDialog(null);
	        if(rueckgabeWert == JFileChooser.APPROVE_OPTION) {	        	
	        	DOMSource domSource = new DOMSource(document);
	        	File fileOutput = chooser.getSelectedFile();
	        	if (FilenameUtils.getExtension(fileOutput.getName()).equalsIgnoreCase("xml")) {
	        	    // filename is OK as-is
	        	} else {
	        		fileOutput = new File(fileOutput.toString() + ".xml");  // append .xml if "foo.jpg.xml" is OK
	        	}
	        	
	        	StreamResult streamResult = new StreamResult(fileOutput);
	            TransformerFactory tf = TransformerFactory.newInstance();

	            Transformer serializer = tf.newTransformer();
	            serializer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
	            serializer.setOutputProperty(OutputKeys.INDENT, "yes");
	            serializer.transform(domSource, streamResult);
	        }        
            
		} catch(ParserConfigurationException e) {
            e.printStackTrace();
        } catch(Throwable e) {
            e.printStackTrace();
        } 

		
	}

}