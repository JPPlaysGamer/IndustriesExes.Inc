package com.ieireference.system.drawing.swing;

import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.plaf.metal.DefaultMetalTheme;
import javax.swing.plaf.metal.MetalTheme;
import javax.swing.plaf.metal.OceanTheme;

/**
 * Represents a class ID for LookAndFeel for Java Swing. Each String have the javadoc of classes LookAndFeel (LAF) without author.
 * */
public final class SwingLAFID {

	/**
     * Returns the name of the <code>LookAndFeel</code> class that implements
     * the native system look and feel if there is one, otherwise
     * the name of the default cross platform <code>LookAndFeel</code>
     * class. This value can be overridden by setting the
     * <code>swing.systemlaf</code> system property.
     *
     *
     */
	public static final String System = UIManager.getSystemLookAndFeelClassName();
	/**
	 * Implements the Windows95/98/NT/2000 Look and Feel.
	 * UI classes not implemented specifically for Windows will
	 * default to those implemented in Basic.
	 * <p>
	 * <strong>Warning:</strong>
	 * Serialized objects of this class will not be compatible with
	 * future Swing releases.  The current serialization support is appropriate
	 * for short term storage or RMI between applications running the same
	 * version of Swing.  A future release of Swing will provide support for
	 * long term persistence.
	 *
	 *
	 */
	public static final String Windows = "com.sun.java.swing.plaf.windows.WindowsLookAndFeel";
	/**
	 * Implements the Windows95/98/ME/NT/2000 Look and Feel.
	 * <p>
	 * <strong>Warning:</strong>
	 * Serialized objects of this class will not be compatible with
	 * future Swing releases.  The current serialization support is appropriate
	 * for short term storage or RMI between applications running the same
	 * version of Swing.  A future release of Swing will provide support for
	 * long term persistence.
	 *
	 * @since 1.5
	 */
	public static final String WindowsClassic = "com.sun.java.swing.plaf.windows.WindowsClassicLookAndFeel";
	/**
	 * The Java Look and Feel, otherwise known as Metal.
	 * <p>
	 * Each of the {@code ComponentUI}s provided by {@code
	 * MetalLookAndFeel} derives its behavior from the defaults
	 * table. Unless otherwise noted each of the {@code ComponentUI}
	 * implementations in this package document the set of defaults they
	 * use. Unless otherwise noted the defaults are installed at the time
	 * {@code installUI} is invoked, and follow the recommendations
	 * outlined in {@code LookAndFeel} for installing defaults.
	 * <p>
	 * {@code MetalLookAndFeel} derives it's color palette and fonts from
	 * {@code MetalTheme}. The default theme is {@code OceanTheme}. The theme
	 * can be changed using the {@code setCurrentTheme} method, refer to it
	 * for details on changing the theme. Prior to 1.5 the default
	 * theme was {@code DefaultMetalTheme}. The system property
	 * {@code "swing.metalTheme"} can be set to {@code "steel"} to indicate
	 * the default should be {@code DefaultMetalTheme}.
	 * <p>
	 * <strong>Warning:</strong>
	 * Serialized objects of this class will not be compatible with
	 * future Swing releases. The current serialization support is
	 * appropriate for short term storage or RMI between applications running
	 * the same version of Swing.  As of 1.4, support for long term storage
	 * of all JavaBeans&trade;
	 * has been added to the <code>java.beans</code> package.
	 * Please see {@link java.beans.XMLEncoder}.
	 *
	 * @see MetalTheme
	 * @see DefaultMetalTheme
	 * @see OceanTheme
	 *
	 */
	public static final String Metal = "javax.swing.plaf.metal.MetalLookAndFeel";
	/**
	 * Nimbus LookAndFeel (your javadoc more detailed not found)...
	 * 
	 * */
	public static final String Nimbus = "javax.swing.plaf.nimbus.NimbusLookAndFeel";
	/**
	 * Implements the Motif Look and Feel.
	 * UI classes not implemented specifically for Motif will
	 * default to those implemented in Basic.
	 * <p>
	 * <strong>Warning:</strong>
	 * Serialized objects of this class will not be compatible with
	 * future Swing releases.  The current serialization support is appropriate
	 * for short term storage or RMI between applications running the same
	 * version of Swing.  A future release of Swing will provide support for
	 * long term persistence.
	 *
	 */
	public static final String CDE_Motif = "com.sun.java.swing.plaf.motif.MotifLookAndFeel";
	
	/**
	 * You can apply LAF here.
	 * 
	 * @param laf Use the IDs of class.
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
     */
	public static void applyLAF(String laf) throws ClassNotFoundException, InstantiationException, IllegalAccessException, UnsupportedLookAndFeelException {
		UIManager.setLookAndFeel(laf);
	}
}
