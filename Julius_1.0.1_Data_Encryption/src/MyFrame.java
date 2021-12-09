import java.awt.Color;
import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JButton;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MyFrame extends JFrame implements ActionListener{
	
	JButton en_button;
	JButton de_button;
	ImageIcon image;
	
	
	private static final long serialVersionUID = 1L;

	MyFrame()
	{
		
		
		en_button = new JButton();
		en_button.setBounds(300, 370, 250, 100);
		en_button.addActionListener(this);
		en_button.setText("ENCRYPT");
		en_button.setFocusable(false);
		en_button.setHorizontalTextPosition(JButton.CENTER);
		en_button.setVerticalTextPosition(JButton.BOTTOM);
		en_button.setFont(new Font("Agency FB",Font.BOLD, 25));
		
		
		de_button = new JButton();
		de_button.setBounds(600, 370, 250, 100);
		de_button.addActionListener(this);
		de_button.setText("DECRYPT");
		de_button.setFocusable(false);
		de_button.setHorizontalTextPosition(JButton.CENTER);
		de_button.setVerticalTextPosition(JButton.BOTTOM);
		de_button.setFont(new Font("Agency FB",Font.BOLD, 25));
		
		
		this.setTitle("Julius 1.0.1");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false);
		this.setSize(1200, 900);
		this.setLocationRelativeTo(null);
		this.setVisible(true);
		
		image = new ImageIcon("icons/favicon.png");
		this.setIconImage(image.getImage());
		this.getContentPane().setBackground(new Color(40, 40, 40));
		this.add(en_button);
		this.add(de_button);

		
	}

	public void FileSelector()
	{
		this.setVisible(false);
		JFrame Selector_Frame = new JFrame();
		Selector_Frame.setTitle("Select_Files");
		Selector_Frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Selector_Frame.setResizable(false);
		Selector_Frame.setSize(1200, 900);
		Selector_Frame.setLocationRelativeTo(null); 
		Selector_Frame.setVisible(true);
		Selector_Frame.setIconImage(image.getImage());
		Selector_Frame.getContentPane().setBackground(new Color(40, 40, 40));
		
	}
	
	
	
	@Override
	public void actionPerformed(ActionEvent e)
	{
		if(e.getSource()==en_button)
			FileSelector();
		if(e.getSource()==de_button)
			System.out.println("Decrypt Button");
	}
	


}
