package com.ieireference.system.drawing.fx;

import java.io.IOException;
import java.net.URL;

import javax.swing.JInternalFrame;
import javax.swing.SwingUtilities;
import javax.swing.UnsupportedLookAndFeelException;

import com.ieireference.system.drawing.swing.SwingLAFID;

import javafx.embed.swing.JFXPanel;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Region;

/**
 * Like the FXDesktopPane, have the FXInternalFrame with the same function of FXDesktopPane, but contains a internal JFXPanel and is necessary load a FXML to the panel, can use a internal frame with a FX panel or totally FX.
 * */
public class FXInternalFrame extends JInternalFrame {

	private JFXPanel panel = new JFXPanel();
	private Region toPanel;
	private boolean hasInitialized = false;
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 8183975301784538970L;
	
	/**
	 * Constructs a new instance of FXDesktopPane and add a LookAndFeel and a fxml (The panel) for load this JComponent. Is very important for use in JavaFX when is added in JDesktopPane and FXDesktopPane.
	 * 
	 * @param LookAndFeel The LAF to apply.
	 * @param fxml The FXML to add the panel.
	 * 
	 * @exception ClassNotFoundException if the <code>LookAndFeel</code>
     	 *           class could not be found
     	 * @exception InstantiationException if a new instance of the class
     	 *          couldn't be created
     	 * @exception IllegalAccessException if the class or initializer isn't accessible
     	 * @exception UnsupportedLookAndFeelException if
     	 *          <code>lnf.isSupportedLookAndFeel()</code> is false
     	 * @throws ClassCastException if {@code className} does not identify
     	 *         a class that extends {@code LookAndFeel}
     	 * 
	 * */
	public FXInternalFrame(String LookAndFeel, URL fxml, Object controller) throws ClassNotFoundException, InstantiationException, IllegalAccessException, UnsupportedLookAndFeelException {
		SwingLAFID.applyLAF(LookAndFeel);
		
		SwingUtilities.updateComponentTreeUI(this);
		
		setContentPane(panel);
        
        	FXMLLoader loader = new FXMLLoader(fxml);
        	loader.setController(this);
        	try {
            		toPanel = loader.load();
            		loader.setController(controller);
            		panel.setScene(new Scene(toPanel));
        	} catch (IOException ex) {
        		ex.printStackTrace();
        	}
        
        	hasInitialized = true;
	}
	
	/**
	 * @return If the construction of instance has succeeded true else false.
	 * */
	public boolean HasInitializaed() {
		return hasInitialized;
	}

}
