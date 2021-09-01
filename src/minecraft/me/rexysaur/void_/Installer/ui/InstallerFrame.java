package me.rexysaur.void_.Installer.ui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import me.rexysaur.void_.Installer.Main;

public class InstallerFrame {
	
	public JFrame frame;
	
	public JPanel title;
	public JPanel launch;
	
	public InstallerFrame()
	{
		initialize();
	}
	
	private void initialize() {

		URL fontUrl = null;
		try {
			fontUrl = new URL("https://github.com/darkboat/launcher/raw/main/TitleWaveRegular-l9ve.ttf");
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		Font font = null;
		try {
			font = Font.createFont(Font.TRUETYPE_FONT, fontUrl.openStream()).deriveFont(Font.BOLD, 35);
		} catch (FontFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		Font fontSmall = null;
		try {
			fontSmall = Font.createFont(Font.TRUETYPE_FONT, fontUrl.openStream()).deriveFont(Font.BOLD, 18);
		} catch (FontFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		ImagePanel background = new ImagePanel(new ImageIcon("background.jpg").getImage());
		
		frame = new JFrame("Void Launcher");
		try {
			frame.setIconImage(new ImageIcon(ImageIO.read(new File("image.jpg"))).getImage());
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		// Frame
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setBounds(760, 415, 800, 575);
		frame.setVisible(true);
		
		// Title
		title = new JPanel();
		title.setOpaque(false);
		title.setBounds(100, 20, 600, 100);
		title.repaint();
		title.setVisible(true);
		
		JLabel label = new JLabel();
		label.setText("Void Client");
		label.setForeground(Color.white);
		label.setBounds(3, 3, 100, 100);
		label.setVisible(true);
		label.setFont(font);
		
		// Launch Button
		launch = new JPanel();
		launch.setOpaque(true);
		launch.setBackground(Color.white);
		launch.setBounds(295, 400, 200, 50);
		launch.repaint();
		launch.setVisible(true);
		
		JButton button = new JButton();
		button.setFont(fontSmall);
		button.setText("Install Void");
		
	    Dimension size = button.getPreferredSize();
	    button.setBounds(300, 180, size.width, size.height);
		button.setBackground(new Color(114, 137, 218));
		button.setForeground(Color.black);
		button.setBorderPainted(false);
		button.setFocusPainted(false);
		button.setContentAreaFilled(false);
		button.setVisible(true);
		
		// onClick listener
		button.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				new Thread(() -> {Main.install();}).start();
			}
			
		});
		
		// add components to panels
		title.add(label);
		launch.add(button);
		
		// add panels to frame
		frame.getContentPane().add(title);
		frame.getContentPane().add(launch);
		
		// set frame background
		frame.getContentPane().add(background);
	}
	
	class ImagePanel extends JPanel {

		  private Image img;

		  public ImagePanel(String img) {
		    this(new ImageIcon(img).getImage());
		  }

		  public ImagePanel(Image image) {
		    this.img = image;
		    Dimension size = new Dimension(800, 600);
		    setPreferredSize(size);
		    setMinimumSize(size);
		    setMaximumSize(size);
		    setSize(size);
		    setLayout(null);
		  }

		  public void paintComponent(Graphics g) {
		    g.drawImage(img, 0, 0, null);
		  }
	}
}
