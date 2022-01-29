import java.awt.BorderLayout;
import java.awt.Color;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JRadioButton;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import com.sun.tools.javac.Main;
public class CheckClass extends JFrame
{
	
	private static final long serialVersionUID = 1L;
	
	ImageIcon frame_icon;
	JRadioButton aes, des, rsa;
	CheckClass()
	{
		ComponentCreator();
		try {
			UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
		}catch(ClassNotFoundException ex)
		{
			Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
		}catch(InstantiationException ex)
		{
			Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
		}catch(IllegalAccessException ex)
		{
			Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
		}catch(UnsupportedLookAndFeelException ex)
		{
			Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
		}
		SwingUtilities.updateComponentTreeUI(this);
		
		this.setTitle("Julius 1.0.1");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false);
		this.setSize(1200, 900);
		this.setLayout(new BorderLayout());
		this.setLocationRelativeTo(null);
		frame_icon = new ImageIcon("icons/Favicon.png");
		this.setIconImage(frame_icon.getImage());
		this.getContentPane().setBackground(new Color(40, 40, 40));
		RadioSelection();
		this.setVisible(true);		
	}
	
	
	public void ComponentCreator()
	{
		aes = new JRadioButton("AES");
		des = new JRadioButton("DES");
		rsa = new JRadioButton("RSA");
		aes.setBounds(0,0,100,100);
		des.setBounds(0,200,100,100);
		rsa.setBounds(0,400,100,100);
	}
	
	public void RadioSelection()
	{
		this.add(aes);
		this.add(des);
		this.add(rsa);
	}
	
	public static void main(String args[])
	{
		new CheckClass();
	}
}
