package com.ieireference.wizards;

import java.awt.EventQueue;
import java.awt.GraphicsEnvironment;
import java.lang.reflect.InvocationTargetException;
import java.net.URISyntaxException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.List;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import javax.swing.UnsupportedLookAndFeelException;
import com.ieireference.system.drawing.swing.SwingLAFID;
import com.ieireference.system.io.JarUtils;
import com.ieireference.system.io.OSTemp;

import net.lingala.zip4j.ZipFile;
import net.lingala.zip4j.util.InternalZipConstants;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Rectangle;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;
import javax.swing.JProgressBar;
import javax.swing.JSeparator;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.awt.event.ActionEvent;
import java.awt.Toolkit;
import java.awt.Color;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;

/**
 * A <code>{@link CreateFXInternalFrame}</code> is a class for setup a InternalFrame for JavaFX The class use LAF Nimbus.
 * */
@SuppressWarnings("serial")
public final class CreateFXInternalFrame extends JFrame{
	
	private ZipFile file;
	private int stage = 0;
	
	@SuppressWarnings("unused")
	private int[] GraphicDeviceBounds = {
		GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice().getDisplayMode().getWidth(),
		GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice().getDisplayMode().getHeight(),
		
	};
	
	private CreateFXInternalFrame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setIconImage(Toolkit.getDefaultToolkit().getImage(CreateFXInternalFrame.class.getResource("/com/ieireference/resources/WizardDesignerIco.png")));
		setTitle("IEI Reference FXInternalFrame Wizard");
		setResizable(false);
		setDefaultLookAndFeelDecorated(true);
		setSize(627, 455);
        setLocationRelativeTo(null);
        getContentPane().setLayout(null);
        
        page1 = new JPanel();
        page1.setVisible(false);
        
        page3 = new JPanel();
        page3.setVisible(false);
        
        page4 = new JPanel();
        page4.setVisible(false);
        page4.setBounds(0, 0, 621, 303);
        getContentPane().add(page4);
        page4.setLayout(null);
        
        lblOutput = new JLabel("Output");
        lblOutput.setFont(new Font("SansSerif", Font.PLAIN, 18));
        lblOutput.setBounds(6, 6, 54, 24);
        page4.add(lblOutput);
        
        lblNowSetThe = new JLabel("Now, set the output of generated Class Frame, Class Controller and FXML:");
        lblNowSetThe.setBounds(5, 53, 408, 16);
        page4.add(lblNowSetThe);
        
        JButton btnSelect = new JButton("Select");
        btnSelect.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		
        		chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        		chooser.setAcceptAllFileFilterUsed(false);
        		if(chooser.showSaveDialog(null) == JFileChooser.APPROVE_OPTION) {
        			btnView.setEnabled(true);
        			btnNext.setEnabled(true);
        		}
        		
        	}
        });
        btnSelect.setBounds(6, 81, 90, 28);
        page4.add(btnSelect);
        
        btnView = new JButton("View");
        btnView.setEnabled(false);
        btnView.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		
        		JOptionPane.showMessageDialog(null, chooser.getSelectedFile());
        		
        	}
        });
        btnView.setBounds(108, 81, 90, 28);
        page4.add(btnView);
        page3.setBounds(0, 0, 621, 303);
        getContentPane().add(page3);
        page3.setLayout(null);
        
        lblLookAndFeel = new JLabel("Look And Feel");
        lblLookAndFeel.setFont(new Font("SansSerif", Font.PLAIN, 18));
        lblLookAndFeel.setBounds(6, 6, 115, 24);
        page3.add(lblLookAndFeel);
        
        lblIsNecessaryApply = new JLabel("Is necessary apply a Look And Feel for the Frame. Example below:");
        lblIsNecessaryApply.setBounds(6, 61, 362, 16);
        page3.add(lblIsNecessaryApply);
        
        lblLaf = new JLabel("LAF:");
        lblLaf.setBounds(6, 129, 55, 16);
        page3.add(lblLaf);
        
        JComboBox<String> comboBox = new JComboBox<String>();
        comboBox.setModel(new DefaultComboBoxModel<String>(new String[] {"System (default)", "CDE/Motif", "Metal", "Nimbus", "Windows", "Windows Classic"}));
        comboBox.setBounds(73, 124, 525, 26);
        page3.add(comboBox);
        
        page2 = new JPanel();
        page2.setBounds(0, 0, 621, 303);
        getContentPane().add(page2);
        page2.setLayout(null);
        
        lblControllerClassAnd = new JLabel("Controller Class and FXML");
        lblControllerClassAnd.setFont(new Font("SansSerif", Font.PLAIN, 18));
        lblControllerClassAnd.setBounds(6, 6, 215, 24);
        page2.add(lblControllerClassAnd);
        
        lblYouNeedAdd = new JLabel("You need add a Controller and a FXML to load. Example below:");
        lblYouNeedAdd.setBounds(6, 53, 343, 16);
        page2.add(lblYouNeedAdd);
        
        lblPackageclass_1 = new JLabel("Package.Class:");
        lblPackageclass_1.setBounds(6, 99, 87, 16);
        page2.add(lblPackageclass_1);
        
        txtController = new JTextField();
        txtController.setText("com.example.ControllerExample");
        txtController.setBounds(105, 93, 510, 28);
        page2.add(txtController);
        txtController.setColumns(10);
        
        lblClasspathfxml = new JLabel("Classpath.FXML:");
        lblClasspathfxml.setBounds(6, 154, 93, 16);
        page2.add(lblClasspathfxml);
        
        txtFXML = new JTextField();
        txtFXML.setText("/com/example/assets/FXMLExample.fxml");
        txtFXML.setBounds(105, 148, 510, 28);
        page2.add(txtFXML);
        txtFXML.setColumns(10);
        page2.setVisible(false);
        page1.setBounds(0, 0, 621, 303);
        getContentPane().add(page1);
        page1.setLayout(null);
        
        JLabel lblFrameClass = new JLabel("Frame Class");
        lblFrameClass.setFont(new Font("SansSerif", Font.PLAIN, 18));
        lblFrameClass.setBounds(6, 6, 101, 24);
        page1.add(lblFrameClass);
        
        JLabel lblPackageclass = new JLabel("Package.Class:");
        lblPackageclass.setBounds(6, 146, 101, 16);
        page1.add(lblPackageclass);
        
        txtFrame = new JTextField();
        txtFrame.setText("com.example.FrameExample");
        txtFrame.setBounds(119, 140, 496, 28);
        page1.add(txtFrame);
        txtFrame.setColumns(10);
        
        lblFirstYouNeed = new JLabel("First, You need of a class to extends the internal frame. Example below:");
        lblFirstYouNeed.setBounds(6, 43, 389, 16);
        page1.add(lblFirstYouNeed);
        
        JPanel panel = new JPanel();
        panel.setBounds(new Rectangle(0, 315, 615, 112));
        getContentPane().add(panel);
        panel.setLayout(null);
        
        lblProgress = new JLabel("");
        lblProgress.setHorizontalAlignment(SwingConstants.CENTER);
        lblProgress.setIcon(new ImageIcon(CreateFXInternalFrame.class.getResource("/com/ieireference/resources/IEIRF FXIF Progress.png")));
        lblProgress.setBounds(162, 6, 256, 64);
        panel.add(lblProgress);
        
        progressBar = new JProgressBar();
        progressBar.setOpaque(true);
        progressBar.setForeground(Color.DARK_GRAY);
        progressBar.setIndeterminate(true);
        progressBar.setBounds(6, 82, 603, 19);
        panel.add(progressBar);
        
        btnNext = new JButton("Next >");
        btnNext.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		if(stage == 0) {
        			stage++;
        			lblInit.setVisible(false);
        			lblProgress.setIcon(new ImageIcon(CreateFXInternalFrame.class.getResource("/com/ieireference/resources/IEIRF FXIF Progress1.png")));
        			progressBar.setIndeterminate(false);
        			progressBar.setValue(25);
        			page1.setVisible(true);
        		}
        		else if(stage == 1) {
        			stage++;
        			lblProgress.setIcon(new ImageIcon(CreateFXInternalFrame.class.getResource("/com/ieireference/resources/IEIRF FXIF Progress2.png")));
        			progressBar.setValue(50);
        			page1.setVisible(false);
        			page2.setVisible(true);
        			page3.setVisible(false);
        			page4.setVisible(false);
        			btnBack.setEnabled(true);
        		}
        		else if(stage == 2) {
        			stage++;
        			lblProgress.setIcon(new ImageIcon(CreateFXInternalFrame.class.getResource("/com/ieireference/resources/IEIRF FXIF Progress3.png")));
        			progressBar.setValue(75);
        			page1.setVisible(false);
        			page2.setVisible(false);
        			page3.setVisible(true);
        			page4.setVisible(false);
        		}
        		else if(stage == 3) {
        			stage++;
        			lblProgress.setIcon(new ImageIcon(CreateFXInternalFrame.class.getResource("/com/ieireference/resources/IEIRF FXIF Progress4.png")));
        			progressBar.setValue(100);
        			page1.setVisible(false);
        			page2.setVisible(false);
        			page3.setVisible(false);
        			page4.setVisible(true);
        			if(chooser.getSelectedFile() == null) {
        				btnNext.setEnabled(false);
        			} else btnNext.setEnabled(true);
        			
        			btnNext.setText("Finish");
        		}
        		else if(stage == 4) {
        			setEnabled(false);
        			setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        			JOptionPane.showMessageDialog(null, "Please wait.");
        			try {
						mountFile(txtFrame.getText(), txtFXML.getText(), txtController.getText(), (String) comboBox.getSelectedItem(), chooser.getSelectedFile().getAbsolutePath());
					} catch (IOException | URISyntaxException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
        		}
        	}
        });
        btnNext.setBounds(430, 42, 90, 28);
        panel.add(btnNext);
        
        btnBack = new JButton("< Back");
        btnBack.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		if(stage == 2) {
        			stage--;
        			lblProgress.setIcon(new ImageIcon(CreateFXInternalFrame.class.getResource("/com/ieireference/resources/IEIRF FXIF Progress1.png")));
        			progressBar.setValue(25);
        			page1.setVisible(true);
        			page2.setVisible(false);
        			btnBack.setEnabled(false);
        		}
        		else if(stage == 3) {
        			stage--;
        			lblProgress.setIcon(new ImageIcon(CreateFXInternalFrame.class.getResource("/com/ieireference/resources/IEIRF FXIF Progress2.png")));
        			progressBar.setValue(50);
        			page1.setVisible(false);
        			page2.setVisible(true);
        			page3.setVisible(false);
        		}
        		else if(stage == 4) {
        			stage--;
        			lblProgress.setIcon(new ImageIcon(CreateFXInternalFrame.class.getResource("/com/ieireference/resources/IEIRF FXIF Progress3.png")));
        			progressBar.setValue(75);
        			page1.setVisible(false);
        			page2.setVisible(false);
        			page3.setVisible(true);
        			page4.setVisible(false);
        			btnNext.setText("Next >");
        			btnNext.setEnabled(true);
        		}
        	}
        });
        btnBack.setEnabled(false);
        btnBack.setBounds(60, 42, 90, 28);
        panel.add(btnBack);
        
        JSeparator separator = new JSeparator();
        separator.setBounds(47, 315, 1, 2);
        getContentPane().add(separator);
        
        lblInit = new JLabel("<html>Do you have questions and problems for make a FXInternalFrame? then this wizard will solve them.<br/><br/>To make a FXInternalFrame need:<br/><br/>*Set Class Frame<br/>*Set Controller and FXML<br/>*Set the LookAndFeel.<br/><br/>Easy, is not?<br/><br/>Please do not enter any value that is not suitable, follow the examples during the process.<br/>Ready? Let's go!");
        lblInit.setVerticalAlignment(SwingConstants.TOP);
        lblInit.setBounds(0, 0, 621, 303);
        getContentPane().add(lblInit);
        
	}
	
	private JFileChooser chooser = new JFileChooser();
	private JButton btnBack;
	private JButton btnNext;
	private JProgressBar progressBar;
	private JLabel lblProgress;
	private JLabel lblInit;
	private JPanel page1;
	private JTextField txtFrame;
	private JLabel lblFirstYouNeed;
	private JPanel page2;
	private JLabel lblControllerClassAnd;
	private JLabel lblYouNeedAdd;
	private JLabel lblPackageclass_1;
	private JTextField txtController;
	private JLabel lblClasspathfxml;
	private JTextField txtFXML;
	private JPanel page3;
	private JLabel lblLookAndFeel;
	private JLabel lblIsNecessaryApply;
	private JLabel lblLaf;
	private JPanel page4;
	private JLabel lblOutput;
	private JLabel lblNowSetThe;
	private JButton btnView;
	
	public static void Wizard() throws InvocationTargetException, InterruptedException {
		
		
		EventQueue.invokeLater(new Runnable() {
			
			@Override
			public void run() {
				try {
					SwingLAFID.applyLAF(SwingLAFID.Nimbus);
				} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
						| UnsupportedLookAndFeelException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				CreateFXInternalFrame frame = new CreateFXInternalFrame();
				frame.setVisible(true);
				
				SwingUtilities.updateComponentTreeUI(frame);
			}
		});
		
	}
	
	private void mountFile(String frame, String fxml, String controller, String laf, String out) throws IOException, URISyntaxException {
		
		if(new File(out + "\\FXIntenalFrame.zip").exists()) {
			new File(out + "\\FXIntenalFrame.zip").delete();
		}
		
		file = new ZipFile(new File(out + "\\FXIntenalFrame.zip"));
		
		List<String> template1 = JarUtils.ReadAllLinesClassPath(CreateFXInternalFrame.class.getResourceAsStream("/com/ieireference/resources/template/ClassFrame.txt"), Charset.defaultCharset());
		List<String> template2 = JarUtils.ReadAllLinesClassPath(CreateFXInternalFrame.class.getResourceAsStream("/com/ieireference/resources/template/Controller.txt"), Charset.defaultCharset());
		List<String> template3 = JarUtils.ReadAllLinesClassPath(CreateFXInternalFrame.class.getResourceAsStream("/com/ieireference/resources/template/FXML.fxml"), Charset.defaultCharset());
		
		File temp = OSTemp.createTempDirectory("IEIRF", "Temp", false);
		
		String[] className = frame.split("\\."); 
		String[] controlName = controller.split("\\.");
		String[] FXName = fxml.split("\\/");
		
		BufferedWriter writer = Files.newBufferedWriter(Paths.get(temp.getAbsolutePath() + "\\" + className[className.length - 1] + ".java"), Charset.defaultCharset(), StandardOpenOption.CREATE);
		
		for(String item : template1) {
			if(item.contains("{0}")) {
				item = item.replace("{0};", "");
				for(int i = 0; i < className.length - 1; i++) {
					
					if(i != className.length - 2) {
						item += className[i] + ".";
					} else item += className[i]; 
					
				}
				item += ";"; 
				writer.write(item);
			} 
			else if(item.contains("{1}")) {
				item = item.replace("{1};", "");
				for(int i = 0; i < controlName.length; i++) {
					
					if(i != controlName.length - 1) {
						item += controlName[i] + ".";
					} else item += controlName[i]; 
					
				}
				item += ";"; 
				writer.write(item);
			}
			else if(item.contains("{2}")) {
				item = item.replace("{2}", className[className.length - 1]);
				writer.write(item);
			}
			else if(item.contains("{3}") && item.contains("{6}")) {
				item = item.replace("{6}", className[className.length - 1]);
				item = item.replace("{3}", fxml);
				writer.write(item);
			}
			else if(item.contains("{4}") && item.contains("{5}")) {
				if(laf == "System (default)") {
					item = item.replace("{4}", "System");
					item = item.replace("{5}", controlName[controlName.length - 1]);
					writer.write(item);
				}
				else if(laf == "CDE/Motif") {
					item = item.replace("{4}", "CDE_Motif");
					item = item.replace("{5}", controlName[controlName.length - 1]);
					writer.write(item);
				}
				else if(laf == "Metal") {
					item = item.replace("{4}", "Metal");
					item = item.replace("{5}", controlName[controlName.length - 1]);
					writer.write(item);
				}
				else if(laf == "Nimbus") {
					item = item.replace("{4}", "Nimbus");
					item = item.replace("{5}", controlName[controlName.length - 1]);
					writer.write(item);
				}
				else if(laf == "Windows") {
					item = item.replace("{4}", "Windows");
					item = item.replace("{5}", controlName[controlName.length - 1]);
					writer.write(item);
				}
				else if(laf == "Windows Classic") {
					item = item.replace("{4}", "WindowsClassic");
					item = item.replace("{5}", controlName[controlName.length - 1]);
					writer.write(item);
				}
			}
			else writer.write(item);
			writer.newLine();
		}
		
		writer.close();
		
		writer = Files.newBufferedWriter(Paths.get(temp.getAbsolutePath() + "\\" + controlName[controlName.length - 1] + ".java"), Charset.defaultCharset(), StandardOpenOption.CREATE);
		
		for(String item2 : template2) {
			if(item2.contains("{0}")) {
				item2 = item2.replace("{0};", "");
				for(int i = 0; i < controlName.length - 1; i++) {
					
					if(i != controlName.length - 2) {
						item2 += controlName[i] + ".";
					} else item2 += controlName[i]; 
					
				}
				item2 += ";"; 
				writer.write(item2);
			} 
			else if(item2.contains("{1}")) {
				item2 = item2.replace("{1}", controlName[controlName.length - 1]);
				writer.write(item2);
			} else writer.write(item2);
			writer.newLine();
		}
		
		writer.close();
		
		writer = Files.newBufferedWriter(Paths.get(temp.getAbsolutePath() + "\\" + FXName[FXName.length - 1]), Charset.defaultCharset(), StandardOpenOption.CREATE);
			
		for(String item3 : template3) {
				writer.write(item3);
				writer.newLine();
		}
			
		writer.close();
		
		file.addFile(temp.getAbsolutePath() + "\\" + className[className.length - 1] + ".java");
		file.addFile(temp.getAbsolutePath() + "\\" + controlName[controlName.length - 1] + ".java");
		file.addFile(temp.getAbsolutePath() + "\\" + FXName[FXName.length - 1]);
		
		file.setCharset(Charset.defaultCharset());
		file.setBufferSize(InternalZipConstants.MIN_BUFF_SIZE);
		file.setComment("IEI Reference - FXInternalFrame Template");
		file.close();
		
		JOptionPane.showMessageDialog(null, "Your Frame has created with successful. Is located in '" + chooser.getSelectedFile().getAbsolutePath() + "'.\n You can put your files now in your project, if You use Eclipse with WindowBuilder or other designer and IDE.\n Is possible open the file in the designer and change configurations of frame and the FXML in SceneBuilder.");
		
		dispose();
		System.exit(0);
		
	}
}
