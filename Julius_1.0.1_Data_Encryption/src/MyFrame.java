import javax.crypto.Cipher;
import javax.crypto.NoSuchPaddingException;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
// import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JProgressBar;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.JButton;
import javax.swing.JFileChooser;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

import java.io.File;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.UserDefinedFileAttributeView;

import com.sun.tools.javac.Main;
import des.DES;
import java.util.logging.Logger;
import java.util.logging.Level;

public class MyFrame extends JFrame implements ActionListener {

	private static final long serialVersionUID = 1L;

	String str_key, str_add, encAlgo, mode;
	int err;
	ImageIcon frame_icon, title_icon, copyright_icon;
	JButton en_button, de_button, ok_button, cancel_button, browse_button, submit_button;
	File en_file, src_file, dec_file;
	JLabel title, copyright, keyLabel1, keyLabel2, keyError, addLabel, detectLabel, algoLabel;
	JPanel TopPanel, BottomPanel, LeftPanel, RightPanel, CenterPanel;
	JFileChooser fileChooser, destChooser;
	JRadioButton DESButton, AESButton, RSAButton, VignereButton, HashButton, CeasarButton;
	ButtonGroup algoButtonG;
	JPasswordField keyField1, keyField2;
	JTextField addBar;
	JProgressBar progBar;
	Path path, filename;

	MyFrame() {

		err = 0;
		str_key = "password";
		str_add = "D:\\";
		ComponentCreator();

		try {
			UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
		} catch (ClassNotFoundException ex) {
			Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
		} catch (InstantiationException ex) {
			Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
		} catch (IllegalAccessException ex) {
			Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
		} catch (UnsupportedLookAndFeelException ex) {
			Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
		}
		SwingUtilities.updateComponentTreeUI(this);

		this.setTitle("Julius 1.0.1");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false);
		this.setSize(1200, 900);
		this.setLocationRelativeTo(null);
		frame_icon = new ImageIcon("icons/Favicon.png");
		this.setIconImage(frame_icon.getImage());
		this.setContentPane(new JLabel(new ImageIcon("icons/Julius_GUI_Background_0114.png")));
		this.getContentPane().setBackground(new Color(40, 40, 40));
		this.setLayout(new BorderLayout());
		TaskSelection();

		this.setVisible(true);
	}

	public void ComponentCreator() {
		en_button = new JButton();
		en_button.setPreferredSize(new Dimension(250, 100));
		en_button.setBackground(new Color(60, 60, 60));
		en_button.setForeground(new Color(255, 255, 255));
		en_button.addActionListener(this);
		en_button.setText("ENCRYPT");
		en_button.setFocusable(false);
		en_button.setToolTipText(
				"<html>Convert your files into a secret code using different <br>encrypting algorithms like AES, RSA, Vigenère Cipher etc.");
		en_button.setHorizontalTextPosition(JButton.CENTER);
		en_button.setVerticalTextPosition(JButton.CENTER);
		en_button.setFont(new Font("Metropolis", Font.BOLD, 25));

		de_button = new JButton();
		de_button.setPreferredSize(new Dimension(250, 100));
		de_button.setBackground(new Color(60, 60, 60));
		de_button.setForeground(new Color(255, 255, 255));
		de_button.addActionListener(this);
		de_button.setText("DECRYPT");
		de_button.setFocusable(false);
		de_button.setToolTipText("<html>Convert your secret coded files to get the plain text.");
		de_button.setHorizontalTextPosition(JButton.CENTER);
		de_button.setVerticalTextPosition(JButton.BOTTOM);
		de_button.setFont(new Font("Metropolis", Font.BOLD, 25));

		ok_button = new JButton();
		ok_button.setBounds(400, 550, 70, 30);
		ok_button.setBackground(new Color(80, 80, 80));
		ok_button.setForeground(new Color(255, 255, 255));
		ok_button.addActionListener(this);
		ok_button.setText("OK");
		ok_button.setFocusable(false);
		ok_button.setHorizontalTextPosition(JButton.CENTER);
		ok_button.setVerticalTextPosition(JButton.BOTTOM);
		ok_button.setFont(new Font("Metropolis", Font.BOLD, 16));

		cancel_button = new JButton();
		cancel_button.setBounds(502, 550, 120, 30);
		cancel_button.setBackground(new Color(80, 80, 80));
		cancel_button.setForeground(new Color(255, 255, 255));
		cancel_button.addActionListener(this);
		cancel_button.setText("CANCEL");
		cancel_button.setFocusable(false);
		cancel_button.setHorizontalTextPosition(JButton.CENTER);
		cancel_button.setVerticalTextPosition(JButton.BOTTOM);
		cancel_button.setFont(new Font("Metropolis", Font.BOLD, 16));

		submit_button = new JButton();
		submit_button.setBounds(392, 550, 90, 30);
		submit_button.setBackground(new Color(80, 80, 80));
		submit_button.setForeground(new Color(255, 255, 255));
		submit_button.addActionListener(this);
		submit_button.setText("Submit");
		submit_button.setFocusable(false);
		submit_button.setHorizontalTextPosition(JButton.CENTER);
		submit_button.setVerticalTextPosition(JButton.BOTTOM);
		submit_button.setFont(new Font("Metropolis", Font.BOLD, 16));

		title_icon = new ImageIcon("icons/Logo_Icon.png");
		title = new JLabel(title_icon);

		copyright_icon = new ImageIcon("icons/Copyright_Logo.png");
		copyright = new JLabel(copyright_icon);

		LeftPanel = new JPanel();
		RightPanel = new JPanel();
		TopPanel = new JPanel();
		BottomPanel = new JPanel();
		CenterPanel = new JPanel();

		// LeftPanel.setBackground(new Color(40, 40, 40));
		// RightPanel.setBackground(new Color(40, 40, 40));
		// TopPanel.setBackground(new Color(40, 40, 40));
		// BottomPanel.setBackground(new Color(40, 40, 40));
		// CenterPanel.setBackground(new Color(40, 40, 40));

		LeftPanel.setBackground(Color.blue);
		RightPanel.setBackground(Color.green);
		TopPanel.setBackground(Color.red);
		BottomPanel.setBackground(Color.yellow);
		CenterPanel.setBackground(Color.black);

		LeftPanel.setOpaque(false);
		RightPanel.setOpaque(false);
		CenterPanel.setOpaque(false);
		TopPanel.setOpaque(false);
		BottomPanel.setOpaque(false);

		LeftPanel.setPreferredSize(new Dimension(600, 0));
		RightPanel.setPreferredSize(new Dimension(600, 0));
		TopPanel.setPreferredSize(new Dimension(0, 370));
		BottomPanel.setPreferredSize(new Dimension(0, 50));
		CenterPanel.setBounds(100, 200, 1000, 600);

		AESButton = new JRadioButton("Advanced Encryption Standard (AES)");
		AESButton.setOpaque(false);
		// AESButton.setBackground(new Color(80,80,80));
		AESButton.setForeground(new Color(255, 255, 255));
		AESButton.setFocusable(false);
		AESButton.setBounds(100, 50, 350, 50);
		AESButton.addActionListener(this);
		AESButton.setFont(new Font("Metropolis", Font.TRUETYPE_FONT, 14));

		DESButton = new JRadioButton("Data Encryption Standard (DES)");
		DESButton.setOpaque(false);
		// DESButton.setBackground(new Color(40,40,40));
		DESButton.setForeground(new Color(255, 255, 255));
		DESButton.setFocusable(false);
		DESButton.setBounds(100, 230, 250, 50);
		DESButton.addActionListener(this);
		DESButton.setFont(new Font("Metropolis", Font.TRUETYPE_FONT, 14));

		RSAButton = new JRadioButton("RSA");
		RSAButton.setOpaque(false);
		// RSAButton.setBackground(new Color(40,40,40));
		RSAButton.setForeground(new Color(255, 255, 255));
		RSAButton.setFocusable(false);
		RSAButton.setBounds(100, 410, 250, 50);
		RSAButton.addActionListener(this);
		RSAButton.setFont(new Font("Metropolis", Font.TRUETYPE_FONT, 14));

		VignereButton = new JRadioButton("Vigenère cipher");
		VignereButton.setOpaque(false);
		// VignereButton.setBackground(new Color(40,40,40));
		VignereButton.setForeground(new Color(255, 255, 255));
		VignereButton.setFocusable(false);
		VignereButton.setBounds(650, 50, 250, 50);
		VignereButton.addActionListener(this);
		VignereButton.setFont(new Font("Metropolis", Font.TRUETYPE_FONT, 14));

		HashButton = new JRadioButton("Hash File");
		HashButton.setOpaque(false);
		// HashButton.setBackground(new Color(40,40,40));
		HashButton.setForeground(new Color(255, 255, 255));
		HashButton.setFocusable(false);
		HashButton.setBounds(650, 230, 250, 50);
		HashButton.addActionListener(this);
		HashButton.setFont(new Font("Metropolis", Font.TRUETYPE_FONT, 14));

		CeasarButton = new JRadioButton("Ceasar Cipher");
		CeasarButton.setOpaque(false);
		// CeasarButton.setBackground(new Color(40,40,40));
		CeasarButton.setForeground(new Color(255, 255, 255));
		CeasarButton.setFocusable(false);
		CeasarButton.setBounds(650, 410, 250, 50);
		CeasarButton.addActionListener(this);
		CeasarButton.setFont(new Font("Metropolis", Font.TRUETYPE_FONT, 14));

		algoButtonG = new ButtonGroup();
		algoButtonG.add(AESButton);
		algoButtonG.add(DESButton);
		algoButtonG.add(RSAButton);
		algoButtonG.add(VignereButton);
		algoButtonG.add(HashButton);
		algoButtonG.add(CeasarButton);

		JRadioButton_Look();

		keyLabel1 = new JLabel("Enter Key:");
		keyLabel1.setBounds(292, 20, 200, 25);
		keyLabel1.setForeground(new Color(255, 255, 255));
		keyLabel1.setFont(new Font("Metropolis", Font.TRUETYPE_FONT, 14));

		keyField1 = new JPasswordField();
		keyField1.setBounds(292, 50, 400, 25);

		keyLabel2 = new JLabel("Re-Enter Key:");
		keyLabel2.setBounds(292, 130, 200, 25);
		keyLabel2.setForeground(new Color(255, 255, 255));
		keyLabel2.setFont(new Font("Metropolis", Font.TRUETYPE_FONT, 14));
		;

		keyField2 = new JPasswordField();
		keyField2.setBounds(292, 160, 400, 25);

		addLabel = new JLabel("File Destination Path:");
		addLabel.setBounds(292, 240, 200, 25);
		addLabel.setForeground(new Color(255, 255, 255));
		addLabel.setFont(new Font("Metropolis", Font.TRUETYPE_FONT, 14));

		keyError = new JLabel("Keys Do Not Match");
		keyError.setBounds(292, 200, 200, 25);
		keyError.setForeground(new Color(255, 0, 0));
		keyError.setFont(new Font("Metropolis", Font.TRUETYPE_FONT, 14));

		addBar = new JTextField("D:\\");
		addBar.setBounds(292, 270, 400, 25);

		browse_button = new JButton();
		browse_button.setBounds(292, 310, 90, 25);
		browse_button.setBackground(new Color(80, 80, 80));
		browse_button.setForeground(new Color(255, 255, 255));
		browse_button.addActionListener(this);
		browse_button.setText("Browse");
		browse_button.setFocusable(false);
		browse_button.setHorizontalTextPosition(JButton.CENTER);
		browse_button.setVerticalTextPosition(JButton.BOTTOM);
		browse_button.setFont(new Font("Metropolis", Font.BOLD, 12));

		progBar = new JProgressBar();
		progBar.setValue(0);
		progBar.setBounds(300, 500, 400, 20);
		progBar.setStringPainted(true);
		progBar.setFont(new Font("Metropolis", Font.BOLD, 12));
		progBar.setOpaque(false);
		progBar.setForeground(new Color(80, 80, 80));

		detectLabel = new JLabel("Detected Algorithm...", SwingConstants.CENTER);
		detectLabel.setBounds(340, 20, 300, 30);
		detectLabel.setForeground(new Color(255, 255, 255));
		detectLabel.setFont(new Font("Metropolis", Font.TRUETYPE_FONT, 28));

		algoLabel = new JLabel("", SwingConstants.CENTER);
		algoLabel.setBounds(340, 70, 300, 30);
		algoLabel.setForeground(new Color(255, 255, 255));
		algoLabel.setFont(new Font("Metropolis", Font.TRUETYPE_FONT, 28));

	}

	public void TaskSelection() {
		this.getContentPane().removeAll();
		this.repaint();
		LeftPanel.add(en_button);
		RightPanel.add(de_button);
		TopPanel.add(title);
		BottomPanel.add(copyright);
		this.add(LeftPanel, BorderLayout.WEST);
		this.add(RightPanel, BorderLayout.EAST);
		this.add(TopPanel, BorderLayout.NORTH);
		this.add(BottomPanel, BorderLayout.SOUTH);
	}

	public void SrcFileSelector()// Opens JFileChooser to select files to encrypt
	{
		fileChooser = new JFileChooser();

		fileChooser.setCurrentDirectory(new File("D:\\"));

		fileChooser.setDialogTitle("Select Files to Encrypt");

		int response = fileChooser.showOpenDialog(this); // Select Files to open

		if (response == JFileChooser.APPROVE_OPTION) {
			src_file = new File(fileChooser.getSelectedFile().getAbsolutePath());
			AlgorithmChooser();
		} else
			TaskSelection();

	}

	public void EnFileSelector()// Opens JFileChooser to select files to decrypt
	{
		fileChooser = new JFileChooser();

		fileChooser.setCurrentDirectory(new File("D:\\"));

		fileChooser.setDialogTitle("Select Files to Decrypt");
		int response = fileChooser.showOpenDialog(getParent()); // Select Files to open

		if (response == JFileChooser.APPROVE_OPTION) {
			en_file = new File(fileChooser.getSelectedFile().getAbsolutePath());
			try {
				this.getContentPane().removeAll();
				this.repaint();
				AlgorithmDetector();
			} catch (IOException e) {
				e.printStackTrace();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else
			TaskSelection();
	}

	public void AlgorithmChooser() {
		CenterPanel.setLayout(null);
		CenterPanel.add(AESButton);
		CenterPanel.add(DESButton);
		CenterPanel.add(RSAButton);
		CenterPanel.add(VignereButton);
		CenterPanel.add(HashButton);
		CenterPanel.add(CeasarButton);
		CenterPanel.add(ok_button);
		CenterPanel.add(cancel_button);
		this.getContentPane().removeAll();
		this.repaint();
		this.add(CenterPanel, BorderLayout.CENTER);
		this.add(TopPanel, BorderLayout.NORTH);
		this.add(BottomPanel, BorderLayout.SOUTH);
	}

	public void AlgorithmDetector() throws IOException, InterruptedException {
		Path enFilePath = Paths.get(en_file.getAbsolutePath());
		UserDefinedFileAttributeView view = Files.getFileAttributeView(enFilePath, UserDefinedFileAttributeView.class);
		String name = "user.encryption";
		ByteBuffer buf = ByteBuffer.allocate(view.size(name));
		view.read(name, buf);
		buf.flip();
		encAlgo = Charset.defaultCharset().decode(buf).toString();

		System.out.println(encAlgo);
		keyLabel2.setText("Enter Key:");
		algoLabel.setText(encAlgo);
		CenterPanel.setLayout(null);

		CenterPanel.add(detectLabel);
		CenterPanel.add(algoLabel);
		CenterPanel.add(keyLabel2);
		CenterPanel.add(keyField2);
		CenterPanel.add(addLabel);
		CenterPanel.add(addBar);
		CenterPanel.add(browse_button);
		CenterPanel.add(submit_button);
		CenterPanel.add(cancel_button);
		this.getContentPane().removeAll();
		this.repaint();
		this.add(CenterPanel, BorderLayout.CENTER);
		this.add(TopPanel, BorderLayout.NORTH);
		this.add(BottomPanel, BorderLayout.SOUTH);

		if (encAlgo.equalsIgnoreCase("AES")) {
			AESButton.setSelected(true);
		}
		if (encAlgo.equalsIgnoreCase("DES")) {
			DESButton.setSelected(true);
		}
		if (encAlgo.equalsIgnoreCase("RSA")) {
			RSAButton.setSelected(true);
		}
		if (encAlgo.equalsIgnoreCase("Vignere")) {
			VignereButton.setSelected(true);
		}
		if (encAlgo.equalsIgnoreCase("Hash")) {
			HashButton.setSelected(true);
		}
		if (encAlgo.equalsIgnoreCase("Ceasar")) {
			CeasarButton.setSelected(true);
		}
	}

	public void KeyAddInput() {
		CenterPanel.setLayout(null);
		CenterPanel.add(keyLabel1);
		CenterPanel.add(keyField1);
		CenterPanel.add(keyLabel2);
		CenterPanel.add(keyField2);
		CenterPanel.add(addLabel);
		if (err != 0) {
			CenterPanel.add(keyError);
		}
		CenterPanel.add(addBar);
		CenterPanel.add(browse_button);
		CenterPanel.add(submit_button);
		CenterPanel.add(cancel_button);

		this.add(CenterPanel, BorderLayout.CENTER);
		this.add(TopPanel, BorderLayout.NORTH);
		this.add(BottomPanel, BorderLayout.SOUTH);
	}

	public void DestSelector()// Opens JFileChooser to select output destination
	{
		destChooser = new JFileChooser();

		destChooser.setCurrentDirectory(new File("D:\\"));
		destChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);

		destChooser.setDialogTitle("Select Destination");
		int response = destChooser.showOpenDialog(getParent()); // Select Files to open

		if (response == JFileChooser.APPROVE_OPTION) {
			File destAdd = destChooser.getSelectedFile();
			str_add = destAdd.getAbsolutePath();
			if (str_add.charAt(str_add.length() - 1) != '\\') {
				str_add = str_add + "\\";
			}
			addBar.setText(str_add);
		}
	}

	public void AESciph() {
		this.getContentPane().removeAll();
		this.repaint();
		CenterPanel.removeAll();
		System.out.println("AES Success.");
	}

	public void DESciph() {
		this.getContentPane().removeAll();
		this.repaint();
		CenterPanel.removeAll();
		if (mode.equalsIgnoreCase("ENCRYPT")) {
			EncFileCreator();
			new DES();
			try {
				DES.encryptDecrypt(str_key, Cipher.ENCRYPT_MODE, src_file, en_file);
			} catch (InvalidKeyException | NoSuchAlgorithmException | InvalidKeySpecException | NoSuchPaddingException
					| InvalidAlgorithmParameterException | IOException e) {
				e.printStackTrace();
			}
			System.out.println("DES Encryption Successful...");
			MetaData("DES");
		} else if (mode.equalsIgnoreCase("DECRYPT")) {
			DecFileCreator();
			new DES();
			try {
				DES.encryptDecrypt(str_key, Cipher.DECRYPT_MODE, en_file, dec_file);
			} catch (InvalidKeyException | NoSuchAlgorithmException | InvalidKeySpecException | NoSuchPaddingException
					| InvalidAlgorithmParameterException | IOException e) {
				e.printStackTrace();
			}
			System.out.println("DES Decryption Successful...");
		}
	}

	public void RSAciph() {
		this.getContentPane().removeAll();
		this.repaint();
		CenterPanel.removeAll();
		System.out.println("RSA Success.");
	}

	public void Vignereciph() {
		this.getContentPane().removeAll();
		this.repaint();
		CenterPanel.removeAll();
		System.out.println("Vignere Success.");
	}

	public void Hashciph() {
		this.getContentPane().removeAll();
		this.repaint();
		CenterPanel.removeAll();
		System.out.println("Hash Success.");
	}

	public void Ceasarciph() {
		this.getContentPane().removeAll();
		this.repaint();
		CenterPanel.removeAll();
		System.out.println("Ceasar Success.");
	}

	public void EncFileCreator() {
		path = Paths.get(src_file.getAbsolutePath());
		filename = path.getFileName();
		en_file = new File(str_add + ("Enc_" + filename.toString()));
	}

	public void DecFileCreator() {
		path = Paths.get(en_file.getAbsolutePath());
		filename = path.getFileName();
		if (filename.toString().substring(0, 4).equalsIgnoreCase("Enc_")) {
			dec_file = new File(str_add + ("Dec_" + filename.toString().substring(4)));
		}
		else
		{
			dec_file = new File(str_add + ("Dec_" + filename.toString()));
		}
	}

	public void MetaData(String enc) {
		Path enFilePath = Paths.get(en_file.getAbsolutePath());

		UserDefinedFileAttributeView view = Files.getFileAttributeView(enFilePath, UserDefinedFileAttributeView.class);
		try {
			view.write("user.encryption", Charset.defaultCharset().encode(enc));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void JRadioButton_Look() {
		try {
			ImageIcon btnico = new ImageIcon("icons//JBtn//JBtn1.png");
			AESButton.setIcon(btnico);
			DESButton.setIcon(btnico);
			RSAButton.setIcon(btnico);
			VignereButton.setIcon(btnico);
			HashButton.setIcon(btnico);
			CeasarButton.setIcon(btnico);

			ImageIcon btnicofocus = new ImageIcon("icons//JBtn//JBtn1_Focus.png");
			AESButton.setRolloverIcon(btnicofocus);
			DESButton.setRolloverIcon(btnicofocus);
			RSAButton.setRolloverIcon(btnicofocus);
			VignereButton.setRolloverIcon(btnicofocus);
			HashButton.setRolloverIcon(btnicofocus);
			CeasarButton.setRolloverIcon(btnicofocus);

			ImageIcon btnicoselect = new ImageIcon("icons//JBtn//JBtn1_select.png");
			AESButton.setSelectedIcon(btnicoselect);
			DESButton.setSelectedIcon(btnicoselect);
			RSAButton.setSelectedIcon(btnicoselect);
			VignereButton.setSelectedIcon(btnicoselect);
			HashButton.setSelectedIcon(btnicoselect);
			CeasarButton.setSelectedIcon(btnicoselect);

		} catch (Exception ex1) {
		}

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == en_button) {
			mode = "ENCRYPT";
			SrcFileSelector();
		}

		if (e.getSource() == de_button) {
			mode = "DECRYPT";
			EnFileSelector();
		}

		if (AESButton.isSelected() || DESButton.isSelected() || RSAButton.isSelected() || VignereButton.isSelected()
				|| HashButton.isSelected() || CeasarButton.isSelected()) {
			if (e.getSource() == ok_button) {
				this.getContentPane().removeAll();
				this.repaint();
				CenterPanel.removeAll();
				KeyAddInput();
			}
		}

		if (e.getSource() == browse_button) {
			DestSelector();
		}

		if (e.getSource() == submit_button) {
			char keyCh1[] = keyField1.getPassword();
			char keyCh2[] = keyField2.getPassword();

			if (mode.equalsIgnoreCase("DECRYPT")) {
				err = 0;
			} else {
				err = 0;
				for (int i = 0; i < keyCh1.length; i++) // Check if keys are equal
				{
					if (keyCh1[i] == keyCh2[i])
						continue;

					else {
						err++;
					}
				}
			}

			if (err == 0) {
				str_key = new String(keyCh2);
				System.out.println(str_key);
				System.out.println(str_add);

				if (AESButton.isSelected() == true) {
					System.out.println("AES Chosen.");
					AESciph();
				}
				if (DESButton.isSelected() == true) {
					System.out.println("DES Chosen.");
					DESciph();
				}
				if (RSAButton.isSelected() == true) {
					System.out.println("RSA Chosen.");
					RSAciph();
				}
				if (VignereButton.isSelected() == true) {
					System.out.println("Vignere Chosen.");
					Vignereciph();
				}
				if (HashButton.isSelected() == true) {
					System.out.println("Hash Chosen.");
					Hashciph();
				}
				if (CeasarButton.isSelected() == true) {
					System.out.println("Ceasar Chosen.");
					Ceasarciph();
				}
			} else {
				System.out.println("Keys do not match.");
				keyField1.setText("");
				keyField2.setText("");
				this.getContentPane().removeAll();
				this.repaint();
				CenterPanel.removeAll();
				KeyAddInput();
			}
		}
		if (e.getSource() == cancel_button) {
			algoButtonG.clearSelection();
			TaskSelection();
		}
	}

}
