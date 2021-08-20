import java.awt.EventQueue;
import java.lang.reflect.InvocationTargetException;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingUtilities;
import javax.swing.UnsupportedLookAndFeelException;

import com.ieireference.system.drawing.swing.SwingLAFID;

import java.awt.Font;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Toolkit;

@SuppressWarnings("serial")
public class Main extends JFrame{
	public Main() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setIconImage(Toolkit.getDefaultToolkit().getImage(Main.class.getResource("/com/ieireference/resources/WBIco.png")));
		setResizable(false);
		setSize(450, 300);
		setLocationRelativeTo(null);
		setTitle("Choose Exec");
		getContentPane().setLayout(null);
		
		JLabel lblApp = new JLabel("App");
		lblApp.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblApp.setBounds(10, 11, 58, 22);
		getContentPane().add(lblApp);
		
		JButton btnFxinternalframeWizard = new JButton("FXInternalFrame Wizard");
		btnFxinternalframeWizard.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				try {
					com.ieireference.wizards.CreateFXInternalFrame.Wizard();
				} catch (InvocationTargetException | InterruptedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
		});
		btnFxinternalframeWizard.setIcon(new ImageIcon(Main.class.getResource("/com/ieireference/resources/WizardDesignerIco.png")));
		btnFxinternalframeWizard.setBounds(10, 44, 208, 60);
		getContentPane().add(btnFxinternalframeWizard);
	}

	public static void main(String[] args) throws InvocationTargetException, InterruptedException {
		// TODO Auto-generated method stub
		EventQueue.invokeLater(new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				try {
					SwingLAFID.applyLAF(SwingLAFID.CDE_Motif);
				} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
						| UnsupportedLookAndFeelException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				Main main = new Main();
				main.setVisible(true);
				
				SwingUtilities.updateComponentTreeUI(main);
			}
		});
	}
}
