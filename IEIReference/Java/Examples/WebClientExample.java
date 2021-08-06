package test;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.ieireference.system.CharSequenceNotFoundException;
import com.ieireference.system.drawing.SwingDrawsUtility;
import com.ieireference.system.io.BufferType;
import com.ieireference.system.web.DownloadEvent;
import com.ieireference.system.web.DownloadListener;
import com.ieireference.system.web.WebClient;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.JProgressBar;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.FileAlreadyExistsException;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;

@SuppressWarnings("serial")
public class DownloadExample extends JFrame {

	private JPanel contentPane;
	
	private WebClient client = new WebClient(new DownloadListener() {
		
		@Override
		public void DownloadFileCompleted(DownloadEvent e) {
			// TODO Auto-generated method stub
			if(e.Completed()) {
				JOptionPane.showMessageDialog(null, "Completed!");
			}else {
				JOptionPane.showMessageDialog(null, "Error!");
			}
		}
		
		@Override
		public void DownloadFileChanged(DownloadEvent e) {
			// TODO Auto-generated method stub
			try {
				SwingDrawsUtility.AutoDrawLabelAndProgressBar(lblMb, progressBar, "{0}/{1} MB", "#.##", BufferType.Megabytes, true, e);
			} catch (NullPointerException | CharSequenceNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	});
	private JProgressBar progressBar;
	private JLabel lblMb;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DownloadExample frame = new DownloadExample();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * @throws UnsupportedLookAndFeelException 
	 * @throws IllegalAccessException 
	 * @throws InstantiationException 
	 * @throws ClassNotFoundException 
	 */
	public DownloadExample() throws ClassNotFoundException, InstantiationException, IllegalAccessException, UnsupportedLookAndFeelException {
		addWindowListener(new WindowAdapter() {
			@SuppressWarnings("deprecation")
			@Override
			public void windowClosing(WindowEvent e) {
				
				if(client.IsThreadAlive()) client.DownloadThreadStop();
				
			}
		});
		UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsClassicLookAndFeel");
		
		SwingUtilities.updateComponentTreeUI(this);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 130);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		lblMb = new JLabel("0/0 MB");
		lblMb.setHorizontalAlignment(SwingConstants.CENTER);
		lblMb.setBounds(309, 11, 115, 14);
		contentPane.add(lblMb);
		
		progressBar = new JProgressBar();
		progressBar.setBounds(10, 36, 414, 14);
		contentPane.add(progressBar);
		
		JButton btnStart = new JButton("Start");
		btnStart.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				btnStart.setEnabled(false);
				
				new File(".\\avast_free_antivirus_setup_offline.exe").delete();
				
				try {
					client.DownloadFileAsync(new URL("https://bits.avcdn.net/productfamily_ANTIVIRUS/insttype_FREE/platform_WIN/installertype_FULL/build_RELEASE/cookie_mmm_ava_998_999_000_m"), 
							".\\avast_free_antivirus_setup_offline.exe"); //Using avast offline installer as example
				} catch (FileAlreadyExistsException | IllegalStateException | MalformedURLException e1) {
					e1.printStackTrace();
				}
			}
		});
		btnStart.setBounds(335, 61, 89, 23);
		contentPane.add(btnStart);
	}
}

