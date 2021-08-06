package com.ieireference.system.drawing;

import java.text.DecimalFormat;

import javax.swing.JLabel;
import javax.swing.JProgressBar;

import com.ieireference.system.CharSequenceNotFoundException;
import com.ieireference.system.StringUtil;
import com.ieireference.system.io.BufferType;
import com.ieireference.system.web.DownloadEvent;

/**
 * Represents a class for Swing use in Draw auto components.
 * 
 * */
public final class SwingDrawsUtility {

	/**
	 * Draw Download Label and ProgressBar in Swing. If you try use WebClient and need of a progress bar and a label, here is a solution.
	 * 
	 * <br>In labelmodel need put {0} and {1} for replace in bytesIn and bytesTotal<br><br><code>
	 * 		String text = "{0}/{1} then put other thing for decorate";
	 * 		SwingDrawsUtility.AutoDrawLabelAndProgressBar(..., text, ...);
	 * </code>
	 * 
	 * @param label The label for draw
	 * @param bar The ProgressBar for draw
	 * @param labelmodel The label model for draw like "{0}/{1} MB or KB or other" just needs of {0} and {1}.
	 * @param decimalFormat The decimal format to use in {0} and {1} 
	 * @param type The type of byte for format. This is used for format the text without leave the number too long.
	 * @param drawStringPercentage Enable or not bar.setStringPainted();
	 * @param params If you use WebClient put DownloadEvent here.
	 * 
	 * @throws NullPointerException If some parameter is null.
	 * @throws CharSequenceNotFoundException If {0} and {1} has not found. 
	 * @throws IndexOutOfBoundsException If labelmodel have more than 1 {0} or {1}.
	 * */
	public static void AutoDrawLabelAndProgressBar(JLabel label, JProgressBar bar, String labelmodel, String decimalFormat, BufferType type, boolean drawStringPercentage, DownloadEvent params) throws NullPointerException, CharSequenceNotFoundException, IndexOutOfBoundsException {
		
		if(label == null || bar == null || (labelmodel == null || labelmodel.isEmpty()) || params == null) {
			throw new NullPointerException("The method parameters can't be null");
		}
		
		if(labelmodel.contains("{0}") && labelmodel.contains("{1}")) {
			
			double in = 0;
			double to = 0;
			if(type == BufferType.Kilobytes) {
				in = (double)params.BytesIn() / 1024;
				to = (double)params.BytesTotal() / 1024;
			}
			if(type == BufferType.Megabytes) {
				in = (double)params.BytesIn() / 1024 / 1024;
				to = (double)params.BytesTotal() / 1024 / 1024;
			}
			if(type == BufferType.Gigabytes) {
				in = (double)params.BytesIn() / 1024 / 1024 / 1024; 
				to = (double)params.BytesTotal() / 1024 / 1024 / 1024;
			}
			
			if(StringUtil.countMatches(labelmodel, "{0}") > 1 || StringUtil.countMatches(labelmodel, "{1}") > 1) {
				throw new IndexOutOfBoundsException("labelmodel can't get more than 1 {0} or {1}");
			}
			
			String text = labelmodel.replaceFirst("1", new DecimalFormat(decimalFormat).format(to));
			text = text.replaceFirst("0", new DecimalFormat(decimalFormat).format(in));
			text = text.replaceAll("\\{", "");
			text = text.replaceAll("\\}", "");
			
			label.setText(text);
			
			label.setEnabled(true);
			label.setVisible(true);
			
			
		} else throw new CharSequenceNotFoundException("labelmodel not contains {0} or {1} or all");
		
		bar.setStringPainted(drawStringPercentage);
			
		bar.setMinimum(0);
		bar.setMaximum(100);
			
		bar.setValue(params.Percentage());
		
		bar.setEnabled(true);
		bar.setVisible(true);
		
	}
	
	
	
	
}
