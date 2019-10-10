package SedProject;
import java.awt.EventQueue;
import java.awt.FlowLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import net.miginfocom.swing.MigLayout;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JButton;
import javax.swing.JRadioButton;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Toolkit;
import javax.swing.JToggleButton;

import org.jfree.chart.ChartPanel;

import processing.core.PApplet;
import processing.core.PFont;
import processing.core.PGraphics;
import javax.swing.UIManager;
import java.awt.SystemColor;
import javax.swing.JCheckBox;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JTabbedPane;

public class Frame {


	DrawHistogramFalse dhf = new DrawHistogramFalse();
	DrawHistogramTrue dht = new DrawHistogramTrue();
	DrawForce df = new DrawForce();
	CommunityForce cf = new CommunityForce();
	
	JFrame frmFacebookdataanalysis;
	/**
	 * Launch the application.
	 */

	/**
	 * Create the application.
	 */
	
	public Frame() {
		
		initialize();
		
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		PApplet parent = null;
		
		frmFacebookdataanalysis = new JFrame();
		frmFacebookdataanalysis.setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\\u9648\u6749\\eclipse-workspace\\BH\\src\\f.ico"));
		frmFacebookdataanalysis.setTitle("FacebookDataAnalysis");
		frmFacebookdataanalysis.setBounds(100, 100, 908, 654);
		frmFacebookdataanalysis.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		frmFacebookdataanalysis.setExtendedState(JFrame.MAXIMIZED_BOTH);//窗口默认最大化
		
		JPanel panel = new JPanel();
		panel.setBackground(SystemColor.window);
		frmFacebookdataanalysis.getContentPane().add(panel, BorderLayout.CENTER);
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[]{406, 539, 70, 0, 0};
		gbl_panel.rowHeights = new int[]{39, 22, 270, 0, 185, 0};
		gbl_panel.columnWeights = new double[]{1.0, 1.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_panel.rowWeights = new double[]{0.0, 0.0, 1.0, 1.0, 1.0, Double.MIN_VALUE};
		panel.setLayout(gbl_panel);
		
		JPanel panelInformation = new JPanel();
		panelInformation.setBackground(SystemColor.menu);
		GridBagConstraints gbc_panelInformation = new GridBagConstraints();
		gbc_panelInformation.gridheight = 2;
		gbc_panelInformation.anchor = GridBagConstraints.NORTH;
		
		gbc_panelInformation.insets = new Insets(0, 0, 5, 5);
		gbc_panelInformation.fill = GridBagConstraints.HORIZONTAL;
		gbc_panelInformation.gridx = 0;
		gbc_panelInformation.gridy = 0;
		panel.add(panelInformation, gbc_panelInformation);
		panelInformation.setLayout(new MigLayout("", "[grow]", "[15.00][29.00]"));
		
		JButton btnChoose = new JButton("Data Selected");
		btnChoose.setBackground(SystemColor.window);
		btnChoose.setFont(new Font("宋体", Font.PLAIN, 20));
		panelInformation.add(btnChoose, "cell 0 0,alignx left,aligny center");
		
		JLabel lblInformation = new JLabel("");
		lblInformation.setFont(new Font("宋体", Font.PLAIN, 20));
		lblInformation.setOpaque(true);//设置组件JLabel不透明，只有设置为不透明，设置背景色才有效  
		lblInformation.setBackground(SystemColor.window);
		panelInformation.add(lblInformation, "cell 0 1,grow");
		
		//btnChoose的鼠标点击事件
		btnChoose.addActionListener(new ActionListener() {
			private File FILE;
			public void actionPerformed(ActionEvent e) {
				JFileChooser jfc = new JFileChooser();
				jfc.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
				jfc.showDialog(new JLabel(), "选择数据文件");
				File file = jfc.getSelectedFile();
				if(file.isFile()) {
					FILE = file.getAbsoluteFile();
					try {
				        BufferedReader br = null;
						System.out.println("试图打开数据文件");
						br = new BufferedReader(new InputStreamReader(
				                    new FileInputStream(FILE)));
				            String line = null;
				            while ((line = br.readLine()) != null) {
				            	lblInformation.setText(line);
				            }
						System.out.println("数据打开成功");
				        
					}//获取文件不存在异常
					catch(FileNotFoundException e1) {
						System.out.println("文件不存在");
						e1.printStackTrace();
					} //获取文件转码异常
					catch (UnsupportedEncodingException e1) {
						e1.printStackTrace();
					}//获取 文件读取异常 
					catch (IOException e1) {
						e1.printStackTrace();
					}
				}
				else {
					JOptionPane.showMessageDialog(null, "数据类型选择错误，请重新选择。", "alert", JOptionPane.ERROR_MESSAGE); 
				}
			}
		});
		
		//================力导图=================
		JLabel lblNewLabel = new JLabel("Force Diagram");
		lblNewLabel.setOpaque(true);//设置组件JLabel不透明，只有设置为不透明，设置背景色才有效  
		lblNewLabel.setBackground(SystemColor.menu);
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 28));
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.gridwidth = 3;
		gbc_lblNewLabel.insets = new Insets(0, 0, 5, 0);
		gbc_lblNewLabel.gridx = 1;
		gbc_lblNewLabel.gridy = 0;
		panel.add(lblNewLabel, gbc_lblNewLabel);
		
		JPanel panelForce = new JPanel();
		panelForce.setBackground(Color.WHITE);
		
		GridBagConstraints gbc_panelForce = new GridBagConstraints();
		gbc_panelForce.gridwidth = 3;
		gbc_panelForce.fill = GridBagConstraints.BOTH;
		gbc_panelForce.gridheight = 4;
		gbc_panelForce.gridx = 1;
		gbc_panelForce.gridy = 1;
		panel.add(panelForce, gbc_panelForce);
		panelForce.setLayout(new MigLayout("", "[]", "[]"));
		panelForce.add(df);
		df.setBounds(0,0,df.width,df.height);
		df.init();
		df.start();
		
		//================社区呈现====================
		JPanel panelSoc = new JPanel();
		panelSoc.setBackground(Color.WHITE);
		GridBagConstraints gbc_panelSoc = new GridBagConstraints();
		gbc_panelSoc.insets = new Insets(0, 0, 5, 5);
		gbc_panelSoc.fill = GridBagConstraints.BOTH;
		gbc_panelSoc.gridx = 0;
		gbc_panelSoc.gridy = 2;
		panel.add(panelSoc, gbc_panelSoc);
		panelSoc.setLayout(new MigLayout("", "[172.00,grow]", "[][][][][][][][][]"));
		
		JLabel lblCommunityForceDiagram = new JLabel("Community Force Diagram");
		lblCommunityForceDiagram.setOpaque(true);
		lblCommunityForceDiagram.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblCommunityForceDiagram.setBackground(SystemColor.menu);
		panelSoc.add(lblCommunityForceDiagram, "cell 0 0,alignx center,aligny center");
		
		JPanel panelCommunityForce = new JPanel();
		panelCommunityForce.setBackground(Color.WHITE);
		panelSoc.add(panelCommunityForce, "cell 0 1 1 8,growx,aligny top");
		
		cf.start();
		cf.init();
		panelCommunityForce.add(cf);
		
		//===================直方图===============
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		GridBagConstraints gbc_tabbedPane = new GridBagConstraints();
		gbc_tabbedPane.gridheight = 2;
		gbc_tabbedPane.insets = new Insets(0, 0, 5, 5);
		gbc_tabbedPane.fill = GridBagConstraints.BOTH;
		gbc_tabbedPane.gridx = 0;
		gbc_tabbedPane.gridy = 3;
		
		JPanel p1 = new JPanel();
		p1.setBackground(Color.WHITE);
		dhf.setLocation(0,800);
		p1.add(dhf);
		dhf.init();
		dhf.start();
        JPanel p2 = new JPanel();
        dht.setLocation(0,800);
		p2.add(dht);
		dht.init();
		dht.start();
        p2.setBackground(Color.WHITE); p2.setBounds(10, 150, 300, 60);
        tabbedPane.add(p1);
        tabbedPane.add(p2);
        tabbedPane.setTitleAt(0, "Node"); tabbedPane.setTitleAt(1, "Edge"); // 设置tab的标题  

		panel.add(tabbedPane, gbc_tabbedPane);
		
		//===================直方图===============
//		JPanel panelHIstogram = new JPanel();
//		GridBagConstraints gbc_panelHIstogram = new GridBagConstraints();
//		gbc_panelHIstogram.fill = GridBagConstraints.BOTH;
//		gbc_panelHIstogram.insets = new Insets(0, 0, 0, 5);
//		gbc_panelHIstogram.gridx = 0;
//		gbc_panelHIstogram.gridy = 4;
//		panel.add(panelHIstogram, gbc_panelHIstogram);
//		panelHIstogram.setBackground(Color.WHITE);
//		dh.setLocation(0,800);
//		panelHIstogram.add(dh);
//		dh.init();
//		dh.start();

	}

}
