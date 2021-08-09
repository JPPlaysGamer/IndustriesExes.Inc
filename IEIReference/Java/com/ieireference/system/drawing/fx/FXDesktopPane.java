package com.ieireference.system.drawing.fx;

import javax.swing.JDesktopPane;
import javax.swing.SwingUtilities;
import javax.swing.UnsupportedLookAndFeelException;

import com.ieireference.system.drawing.swing.SwingLAFID;

import javafx.embed.swing.SwingNode;

/**
 * Represents a class component to use in JavaFX, a FXDesktopPane supports JavaFX and contains a internal SwingNode to links the FX and Swing.
 * */
public class FXDesktopPane extends JDesktopPane {

	private SwingNode component = new SwingNode();
	private boolean hasInitialized = false;
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 4373378949628792326L;

	public FXDesktopPane() {}
	
	/**
	 * Constructs a new instance of FXDesktopPane and add a LookAndFeel for load this JComponent. Is very important for use in JavaFX when add JDesktopPane and JInternalFrame.
	 * 
	 * @param LookAndFeel the LAF to apply.
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
     	 * @exception IllegalStateException If You try add a component not FXInternalFrame or JInternalFrame
	 * */
	public FXDesktopPane(String LookAndFeel) throws ClassNotFoundException, InstantiationException, IllegalAccessException, UnsupportedLookAndFeelException, IllegalStateException {
		SwingLAFID.applyLAF(LookAndFeel);
		
		SwingUtilities.updateComponentTreeUI(this);
		
		component.setContent(this);
		
		SwingUtilities.updateComponentTreeUI(this);
		
		hasInitialized = true;
	}

	/**
	 * @return If the construction of instance has succeeded true else false.
	 * */
	public boolean HasInitializaed() {
		return hasInitialized;
	}
	
	/**
	 * Get the JDesktopPane as a SwingNode. But need create a instance before of use.
	 * 
	 * @return The node for use in JavaFX.
	 * */
	public SwingNode getThisComponentNode() {
		return HasInitializaed() ? component : null;
	}
	
	
}
