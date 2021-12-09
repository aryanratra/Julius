/* This class is used to make the splash screen.
 * The loading bar is just for decoration and serves no real purpose.
 * The Cover Design is a .png file and is called 'SplashScreen_01.png'.
 * Another version of the 'SplashScreen_01.png', which does not use the
 *  circuit lines, is called 'SplashScreen_02.png'.
 * To change it to the latter version, simply replace 'SplashScreen_01.png'
 * to 'SplashScreen_02.png' in line - 'Icon splash_img= new ImageIcon("icons/SplashScreen_02.png");'.
 * To overlay the JProgressBar over the JLabel, I have used the JLayeredPane and added both the
 * components using the .add() function.
 * The resolution of the SplashScreen is 1000 x 700 and can't be changed.
 * To remove the minimize, maximize and close panel, I have used the function .setUndecorated(true).
 * TO change the color of the Progress Bar, simply input the RGB values in
 * line - 'bar.setForeground(new Color(80, 80, 80));'.
 * To change the duration of the SplashScreen you need to alter the 
 * values inside the 'Thread.sleep()' method inside the function fill().
*/

import java.awt.*;
import javax.swing.*;


public class SplashScreen extends JWindow 
{

	private static final long serialVersionUID = 1L;
	JFrame frame = new JFrame();
	JProgressBar bar = new JProgressBar();
	JLayeredPane layeredPane = new JLayeredPane();
	
public SplashScreen()
  {
	
	Icon splash_img= new ImageIcon("icons/SplashScreen_02.png");
    JLabel label = new JLabel(splash_img);
    label.setBounds(0, 0, 1000, 700);
	
    layeredPane.setBounds(0,0,1000,700);
    
    layeredPane.add(bar, JLayeredPane.DRAG_LAYER);
    layeredPane.add(label, JLayeredPane.DEFAULT_LAYER);
    
    
	bar.setValue(0);
	bar.setBounds(300,500,400,20);
	bar.setStringPainted(true);
	bar.setFont(new Font("Metropolis", Font.BOLD, 12));
	bar.setOpaque(false);
	bar.setForeground(new Color(80, 80, 80));
	

	frame.add(layeredPane);
	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	frame.setSize(1000, 700);
	frame.setUndecorated(true);
	frame.setLayout(null);
    frame.setLocationRelativeTo(null);
	frame.setVisible(true);
	
    fill();

    frame.setVisible(false);

  }

	public void fill() {
		int counter=0;
		while(counter<=100)
		{
			bar.setValue(counter);
			try
		    {
		        Thread.sleep(2);
		    }
		    catch(InterruptedException e)
		    {
		        e.printStackTrace();
		    }
			counter+=1;
		}
		bar.setString("Loading...");
		try
	    {
	        Thread.sleep(500);
	    }
	    catch(InterruptedException e)
	    {
	        e.printStackTrace();
	    }
		bar.setString("Creating Window...");
		try
	    {
	        Thread.sleep(700);
	    }
	    catch(InterruptedException e)
	    {
	        e.printStackTrace();
	    }
	}
}