package com.test;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.ieireference.system.web.*;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JProgressBar;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.FileAlreadyExistsException;
import java.text.DecimalFormat;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

@SuppressWarnings("serial")
public class DownloadFileFrame extends JFrame {

	private JPanel contentPane;
	private WebClient client = new WebClient(new DownloadListener() {
		
		@Override
		public void DownloadFileCompleted(DownloadEvent e) {
			// TODO Auto-generated method stub
			if(e.Completed) {
				JOptionPane.showMessageDialog(null, "Downloaded!");
			}
			else {
				JOptionPane.showMessageDialog(null, "Downloaded!");
			}
		}
		
		@Override
		public void DownloadFileChanged(DownloadEvent e) {
			// TODO Auto-generated method stub
			double total = (double)e.BytesTotal / 1024 / 1024; double in = (double)e.BytesIn / 1024 / 1024;
			
			lblMb.setText(new DecimalFormat("#.##").format(in) + "/" + new DecimalFormat("#.##").format(total) + " MB");
			progressBar.setValue(e.Percentage);
		}
	});
	private JLabel lblMb;
	private JProgressBar progressBar;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DownloadFileFrame frame = new DownloadFileFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public DownloadFileFrame() {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				
				if(client.IsThreadAlive()) client.ThreadStop();
				
				
			}
		});
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 127);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		lblMb = new JLabel("0/0 MB");
		lblMb.setHorizontalAlignment(SwingConstants.CENTER);
		lblMb.setBounds(304, 11, 120, 14);
		contentPane.add(lblMb);
		
		progressBar = new JProgressBar();
		progressBar.setStringPainted(true);
		progressBar.setForeground(Color.DARK_GRAY);
		progressBar.setBounds(10, 36, 414, 14);
		contentPane.add(progressBar);
		
		JButton btnStart = new JButton("Start");
		btnStart.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				btnStart.setEnabled(false);
				
				try {
					client.DownloadFileAsync(new URL("https://bits.avcdn.net/productfamily_ANTIVIRUS/insttype_FREE/platform_WIN/installertype_FULL/build_RELEASE/cookie_mmm_ava_998_999_000_m"), 
							".\\avast_free_antivirus_setup_offline.exe"); //Using avast file offline like example.
				} catch (IllegalStateException | MalformedURLException | FileAlreadyExistsException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
		});
		btnStart.setBounds(335, 55, 89, 23);
		contentPane.add(btnStart);
	}
}
