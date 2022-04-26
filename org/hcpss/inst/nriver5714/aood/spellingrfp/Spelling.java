package org.hcpss.inst.nriver5714.aood.spellingrfp;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

//Temporary setup for Spelling class
public class Spelling extends JFrame implements ActionListener {

	CardLayout layout;
	Container contentPane;
	User currentUser;
	JPanel home;

	// JButton b1, b2, b3;
	class LoginPage extends JPanel implements ActionListener {
		private static final long serialVersionUID = 3242879932173395679L;
		JLabel lblName;
		JTextField txtName;
		JButton btnLogin;

		public LoginPage() {
			BoxLayout boxlayout = new BoxLayout(this, BoxLayout.Y_AXIS);
			this.setLayout(boxlayout);
			this.add(Box.createRigidArea(new Dimension(0, 120)));

			lblName = new JLabel("Please Enter in Your Name");
			lblName.setAlignmentX(Component.CENTER_ALIGNMENT);
			this.add(lblName);
			this.add(Box.createVerticalStrut(10));

			txtName = new JTextField();
			txtName.setMaximumSize(new Dimension(200, txtName.getPreferredSize().height));
			txtName.setHorizontalAlignment(JTextField.CENTER);
			this.add(txtName);
			this.add(Box.createVerticalStrut(10));

			btnLogin = new JButton("Login");
			btnLogin.setAlignmentX(Component.CENTER_ALIGNMENT);
			this.add(btnLogin);
		}

		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			/*
			 * String name = text.getText().strip(); if (Configuration.userExists(name)) {
			 * currentUser = Configuration.getUser(name).orElseThrow(); }
			 */
		}
	}

	class AgePrompt extends JPanel implements ActionListener {
		private static final long serialVersionUID = 3242879932173395679L;
		JLabel lblAge;
		JTextField txtAge;
		JButton btnSubmit;

		public AgePrompt() {
			BoxLayout boxlayout = new BoxLayout(this, BoxLayout.Y_AXIS);
			this.setLayout(boxlayout);
			this.add(Box.createRigidArea(new Dimension(0, 120)));

			lblAge = new JLabel("Please Enter in Your Age");
			lblAge.setAlignmentX(Component.CENTER_ALIGNMENT);
			this.add(lblAge);
			this.add(Box.createVerticalStrut(10));

			txtAge = new JTextField();
			txtAge.setMaximumSize(new Dimension(200, txtAge.getPreferredSize().height));
			txtAge.setHorizontalAlignment(JTextField.CENTER);
			this.add(txtAge);
			this.add(Box.createVerticalStrut(10));

			btnSubmit = new JButton("Submit");
			btnSubmit.setAlignmentX(Component.CENTER_ALIGNMENT);
			this.add(btnSubmit);
		}

		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			// String age = text.getText().strip();

		}
	}

	class Practice extends JPanel implements ActionListener {
		private static final long serialVersionUID = 1L;
		JLabel user, spellWord;
		JButton btnQuit, btnGiveUp, btnSubmit, btnSound;
		JTextField enterSpelling;
		JPanel topPanel, midPanel, btmPanel;

		public Practice() {
			topPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));

			btnQuit = new JButton("Quit Practice");
			topPanel.add(btnQuit);
			topPanel.add(Box.createHorizontalStrut(50));

			// Retrieve username and level from Configuration class
			user = new JLabel("TEMPORARY, Level: Temp");
			topPanel.add(user);
			this.add(topPanel);

			midPanel = new JPanel();
			midPanel.add(Box.createVerticalStrut(25));
			midPanel.setLayout(new BoxLayout(midPanel, BoxLayout.Y_AXIS));
			ImageIcon imageIcon = new ImageIcon("assets/sound-off-icon-40944.png");
			Image image = imageIcon.getImage();
			image = image.getScaledInstance(100, 100, java.awt.Image.SCALE_SMOOTH);
			Icon icon = new ImageIcon(image);
			btnSound = new JButton(icon);
			// btnSound.setOpaque(false);
			btnSound.setAlignmentX(Component.CENTER_ALIGNMENT);
			midPanel.add(btnSound);

			midPanel.add(Box.createVerticalStrut(20));
			spellWord = new JLabel("Spell the Word");
			spellWord.setAlignmentX(Component.CENTER_ALIGNMENT);
			spellWord.setFont(new Font("Comfortaa", Font.PLAIN, 20));
			midPanel.add(spellWord);

			enterSpelling = new JTextField();
			enterSpelling.setAlignmentX(Component.CENTER_ALIGNMENT);
			enterSpelling.setHorizontalAlignment(JTextField.CENTER);
			midPanel.add(enterSpelling);
			this.add(midPanel);

			btmPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
			btmPanel.add(Box.createVerticalStrut(65));
			btnGiveUp = new JButton("Give Up");
			btmPanel.add(btnGiveUp);

			btmPanel.add(Box.createHorizontalStrut(70));
			btnSubmit = new JButton("Submit");
			btmPanel.add(btnSubmit);
			this.add(btmPanel);
		}

		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub

		}
	}

	class CorrectAnswer extends JPanel implements ActionListener {

		private static final long serialVersionUID = -1972509954050125796L;
		JLabel user, lblCongratz;
		JButton btnQuit, btnCont;
		JPanel topPanel, btmPanel;

		CorrectAnswer() {
			topPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));

			btnQuit = new JButton("Quit Practice");
			topPanel.add(btnQuit);
			topPanel.add(Box.createHorizontalStrut(50));

			// Retrieve username and level from Configuration class
			user = new JLabel("TEMPORARY, Level: Temp");
			topPanel.add(user);
			this.add(topPanel);

			btmPanel = new JPanel();
			btmPanel.setLayout(new BoxLayout(btmPanel, BoxLayout.Y_AXIS));
			btmPanel.add(Box.createVerticalStrut(50));

			lblCongratz = new JLabel("Good Job!");
			lblCongratz.setAlignmentX(Component.CENTER_ALIGNMENT);
			lblCongratz.setFont(new Font("Comfortaa", Font.PLAIN, 30));
			btmPanel.add(lblCongratz);
			btmPanel.add(Box.createVerticalStrut(80));

			btnCont = new JButton("Continue");
			btnCont.setMaximumSize(new Dimension(250, 100));
			btnCont.setAlignmentX(Component.CENTER_ALIGNMENT);
			btmPanel.add(btnCont);
			this.add(btmPanel);

		}

		public void actionPerformed(ActionEvent e) {

		}
	}

	class IncorrectAnswer extends JPanel implements ActionListener {
		JLabel user, wrong, notQuite, spellingAttempt;
		JButton btnQuit, btnGiveUp, btnSubmit, btnSound;
		JTextField enterSpelling;
		JPanel topPanel, midPanel, btmPanel;

		public IncorrectAnswer() {
			System.out.println("Entered IncorrectAnswer constructor");
			topPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));

			btnQuit = new JButton("Quit Practice");
			topPanel.add(btnQuit);
			topPanel.add(Box.createHorizontalStrut(50));

			// Retrieve username and level from Configuration class
			user = new JLabel("TEMPORARY, Level: Temp");
			topPanel.add(user);
			this.add(topPanel);

			midPanel = new JPanel();
			midPanel.setLayout(new BoxLayout(midPanel, BoxLayout.Y_AXIS));
			notQuite = new JLabel("Not Quite.");
			notQuite.setAlignmentX(Component.CENTER_ALIGNMENT);
			notQuite.setFont(new Font("Comfortaa", Font.BOLD, 15));
			midPanel.add(notQuite);

			// Retrieve the text from textfield and display errors
			spellingAttempt = new JLabel("TEMPORARY");
			spellingAttempt.setForeground(Color.RED);
			spellingAttempt.setAlignmentX(Component.CENTER_ALIGNMENT);
			midPanel.add(spellingAttempt);
			midPanel.add(Box.createVerticalStrut(5));

			ImageIcon imageIcon = new ImageIcon("assets/sound-off-icon-40944.png");
			Image image = imageIcon.getImage();
			image = image.getScaledInstance(100, 100, java.awt.Image.SCALE_SMOOTH);
			Icon icon = new ImageIcon(image);
			btnSound = new JButton(icon);
			// btnSound.setOpaque(false);
			btnSound.setAlignmentX(Component.CENTER_ALIGNMENT);
			midPanel.add(btnSound);

			midPanel.add(Box.createVerticalStrut(20));
			wrong = new JLabel("Try Again!");
			wrong.setAlignmentX(Component.CENTER_ALIGNMENT);
			wrong.setFont(new Font("Comfortaa", Font.PLAIN, 20));
			midPanel.add(wrong);

			enterSpelling = new JTextField();
			enterSpelling.setAlignmentX(Component.CENTER_ALIGNMENT);
			enterSpelling.setHorizontalAlignment(JTextField.CENTER);
			midPanel.add(enterSpelling);
			this.add(midPanel);

			btmPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
			btmPanel.add(Box.createVerticalStrut(65));
			btnGiveUp = new JButton("Give Up");
			btmPanel.add(btnGiveUp);

			btmPanel.add(Box.createHorizontalStrut(70));
			btnSubmit = new JButton("Submit");
			btmPanel.add(btnSubmit);
			this.add(btmPanel);
		}

		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub

		}
	}
	
	class CompletePractice extends JPanel implements ActionListener {
	    JLabel user, completePractice, score;
	    JButton btnHome;
	    
	    CompletePractice() {
	      BoxLayout boxlayout = new BoxLayout(this, BoxLayout.Y_AXIS);
	      this.setLayout(boxlayout);

	      //retrieve username and level from Configuration class
	      user = new JLabel("TEMPORARY, Level: Temp");
	      user.setAlignmentX(Component.CENTER_ALIGNMENT);
	      user.setFont(new Font("Comfortaa", Font.PLAIN, 15));
	      this.add(user);
	      this.add(Box.createVerticalStrut(70));

	      completePractice = new JLabel("Practice Complete!");
	      completePractice.setFont(new Font("Comfortaa", Font.BOLD, 20));
	      completePractice.setAlignmentX(Component.CENTER_ALIGNMENT);
	      this.add(completePractice);
	      this.add(Box.createVerticalStrut(10));

	      //calculate score
	      score = new JLabel("Score: temp");
	      score.setAlignmentX(Component.CENTER_ALIGNMENT);
	      this.add(score);
	      this.add(Box.createVerticalStrut(80));

	      
	      btnHome = new JButton("Back to Home");
	      btnHome.setMaximumSize(new Dimension(200, 50));
	      btnHome.setAlignmentX(Component.CENTER_ALIGNMENT);
	      this.add(btnHome);
	    }

	    public void actionPerformed(ActionEvent e) {

	    }
	  }

	class HomePage extends JPanel implements ActionListener {
		JLabel lblWelcome, lblLevel;
		JButton btnPractice, btnSettings;

		public HomePage() {
			BoxLayout boxlayout = new BoxLayout(this, BoxLayout.Y_AXIS);
			this.setLayout(boxlayout);

			// Retrieve user name from Configuration class
			lblWelcome = new JLabel("Welcome, PLACEHOLDER");
			lblWelcome.setFont(new Font("Comfortaa", Font.PLAIN, 15));
			lblWelcome.setAlignmentX(Component.CENTER_ALIGNMENT);
			this.add(lblWelcome);
			this.add(Box.createVerticalStrut(10));

			// Retrieve user level from Configuration class
			lblLevel = new JLabel("Level: TEMP");
			lblLevel.setAlignmentX(Component.CENTER_ALIGNMENT);
			this.add(lblLevel);
			this.add(Box.createVerticalStrut(100));

			btnPractice = new JButton("Start Practicing");
			btnPractice.setAlignmentX(Component.CENTER_ALIGNMENT);
			btnPractice.setFont(new Font("Comfortaa", Font.PLAIN, 20));
			btnPractice.setMaximumSize(new Dimension(250, 100));
			this.add(btnPractice);
			this.add(Box.createVerticalStrut(25));

			btnSettings = new JButton("Settings");
			btnSettings.setAlignmentX(Component.CENTER_ALIGNMENT);
			btnSettings.setMaximumSize(new Dimension(150, 40));
			this.add(btnSettings);
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			/*
			 * layout.show(contentPane, indexOfPanel) indexOfPanel refers to the panel at
			 * the position of the specified index
			 */

		}

	}

	class SettingsPage extends JPanel implements ActionListener {
		// top - FlowLayout, middle - BoxLayout, bottom - GridLayout;
		JPanel topPanel, middlePanel, bottomPanel;
		JButton home, modifyLevel, removeLevel, insertLevelBefore, AddLevelAfter;
		JLabel user, chooseLevel;
		JComboBox levels;

		public SettingsPage() {
			topPanel = new JPanel();
			topPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
			home = new JButton("Home");

			topPanel.add(home);
			topPanel.add(Box.createHorizontalStrut(30));

			// Use Configuration class to retrieve name and level
			user = new JLabel("TEMPORARY, Level Temp");
			topPanel.add(user);
			topPanel.add(Box.createHorizontalStrut(50));
			this.add(topPanel);
	
			middlePanel = new JPanel();
			this.add(Box.createHorizontalStrut(30));
			middlePanel.setLayout(new BoxLayout(middlePanel, BoxLayout.Y_AXIS));
			chooseLevel = new JLabel("Choose a level: ");
			chooseLevel.setAlignmentX(Component.CENTER_ALIGNMENT);
			middlePanel.add(chooseLevel);
			// Initialize array with available levels
			Level l[] = { null };
			levels = new JComboBox(l);
			middlePanel.add(levels);
			this.add(middlePanel);
			this.add(Box.createRigidArea(new Dimension(0, 90)));

			bottomPanel = new JPanel(new GridLayout(2, 2, 10, 10));
			modifyLevel = new JButton("Modify Level");
			modifyLevel.setPreferredSize(new Dimension(50, 50));
			bottomPanel.add(modifyLevel);
			removeLevel = new JButton("Remove Level");
			bottomPanel.add(removeLevel);
			insertLevelBefore = new JButton("Insert Level Before");
			bottomPanel.add(insertLevelBefore);
			AddLevelAfter = new JButton("Add Level After");
			bottomPanel.add(AddLevelAfter);
			this.add(bottomPanel);

		}

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub

		}

	}

	class LevelModificationPage extends JPanel implements ActionListener {
		JPanel topPanel, middlePanel, mid2Panel, bottomPanel, btm2Panel;
		JButton home, modifyWord, removeWord, changeLevel, previewSound, btnChoose, addWord;
		JLabel user, level, chooseWord, soundFile;
		JTextField newWord;
		JComboBox words;

		public LevelModificationPage() {
			topPanel = new JPanel();
			topPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
			home = new JButton("Home");

			topPanel.add(home);
			topPanel.add(Box.createHorizontalStrut(30));
			// Use Configuration class to retrieve name and level
			user = new JLabel("John, Level 6");
			topPanel.add(user);
			topPanel.add(Box.createHorizontalStrut(30));
			this.add(topPanel);
			this.add(Box.createHorizontalStrut(30));

			middlePanel = new JPanel();
			middlePanel.setLayout(new BoxLayout(middlePanel, BoxLayout.Y_AXIS));
			chooseWord = new JLabel("Choose a word: ");
			middlePanel.add(chooseWord);
			// Initialize array with available levels
			Word w[] = { null };
			words = new JComboBox(w);
			middlePanel.add(words);
			this.add(middlePanel);
			this.add(Box.createRigidArea(new Dimension(0, 70)));

			mid2Panel = new JPanel(new GridLayout(2, 2, 10, 10));
			modifyWord = new JButton("Modify Word");
			modifyWord.setPreferredSize(new Dimension(40, 40));

			modifyWord.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(java.awt.event.ActionEvent evt) {
					JTextField newSpelling = new JTextField();
					// Replace Temporary with selected Word
					JOptionPane.showConfirmDialog(contentPane, newSpelling,
							"Choose a new spelling for " + words.getSelectedItem().toString(), 2,
							JOptionPane.INFORMATION_MESSAGE);

				}

			});

			mid2Panel.add(modifyWord);
			removeWord = new JButton("Remove Word");
			mid2Panel.add(removeWord);
			changeLevel = new JButton("Change Level");

			changeLevel.addActionListener(new ActionListener() {

				public void actionPerformed(java.awt.event.ActionEvent evt) {
					// Set array with current levels, change later
					Object[] l = { 1, 2, 3, 4, 5, 6, 7 };
					JComboBox levels = new JComboBox(l);
					// Replace Temporary with selected Word
					JOptionPane.showMessageDialog(contentPane, levels,
							"Choose a new level for " + words.getSelectedItem().toString(),
							JOptionPane.INFORMATION_MESSAGE);

				}
			});

			mid2Panel.add(changeLevel);
			previewSound = new JButton("Preview Sound");
			mid2Panel.add(previewSound);
			this.add(mid2Panel);

			bottomPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
			newWord = new JTextField("Enter New Word Here");
			newWord.setPreferredSize(new Dimension(300, 30));
			bottomPanel.add(newWord);
			this.add(bottomPanel);

			btm2Panel = new JPanel(new FlowLayout(FlowLayout.CENTER));
			soundFile = new JLabel("Sound File: ");
			btm2Panel.add(soundFile);
			btnChoose = new JButton("Choose");
			btm2Panel.add(btnChoose);
			addWord = new JButton("Add Word");
			btm2Panel.add(addWord);
			this.add(btm2Panel);

		}

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub

		}

	}

	public Spelling() {

		contentPane = getContentPane();
		layout = new CardLayout(10, 20);

		contentPane.setLayout(layout);
		home = new CompletePractice();
		System.out.println("Finished IncorrectAnswer constructor");
		contentPane.add(home, "1");
		/*
		 * b1 = new JButton("Login Page"); b2 = new JButton("Practice"); b3 = new
		 * JButton("Settings");
		 * 
		 * b1.addActionListener(new ActionListener() { public void
		 * actionPerformed(ActionEvent e) { card.next(c); } });
		 * 
		 * b2.addActionListener(this); b3.addActionListener(this);
		 * 
		 * c.add("a",b1);c.add("b",b2);c.add("c",b3);
		 */

	}

	public static void runGUI() {
		System.out.println("Enter runGUI");
		Spelling cl = new Spelling();
		System.out.println("Finished Spelling constructor");
		cl.setSize(400, 400);
		cl.setVisible(true);
		cl.setDefaultCloseOperation(EXIT_ON_CLOSE);
		cl.setResizable(false);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		layout.next(contentPane);

	}

}
