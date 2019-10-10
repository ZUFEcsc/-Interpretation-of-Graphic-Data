package SedProject;
import java.awt.EventQueue;
import java.io.IOException;

import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

import processing.core.PApplet;

public class FormMain {
	static Data fb = new Data();
	static DrawHistogramFalse dh = new DrawHistogramFalse();
	
	public static void main(String[] args) throws IOException {
		//´î¿ò¼Ü
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Frame window = new Frame();
					UIManager.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
					SwingUtilities.updateComponentTreeUI(window.frmFacebookdataanalysis);
					window.frmFacebookdataanalysis.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

}
