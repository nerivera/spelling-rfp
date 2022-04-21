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
		public static final String NAME = "Login Page";
		private JButton b;
		private JLabel l;
		private JTextField text;

		public LoginPage() {
			l = new JLabel("Please Enter In Your Name");
			l.setAlignmentX(JLabel.CENTER_ALIGNMENT);
			l.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
			this.add(l);
			text = new JTextField();
			this.add(text);
			b = new JButton("Login");
			b.setAlignmentX(JButton.CENTER_ALIGNMENT);
			b.addActionListener(this);
			this.add(b);
		}

		public void actionPerformed(ActionEvent e) {

			String name = text.getText().strip();
			if (Configuration.userExists(name)) {
				currentUser = Configuration.getUser(name).orElseThrow();
			}

			// layout.next(contentPane);

		}

	}

	class AgePrompt extends JPanel implements ActionListener {
		private JButton b;
		private JLabel l;
		private JTextField text;

		public AgePrompt() {
			l = new JLabel("Please Enter Your Age");
			l.setAlignmentX(JLabel.CENTER_ALIGNMENT);
			l.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
			this.add(l);
			text = new JTextField();
			this.add(text);
			b = new JButton("Submit");
			b.setAlignmentX(JButton.CENTER_ALIGNMENT);
			b.addActionListener(this);
			this.add(b);
		}

		public void actionPerformed(ActionEvent e) {
			String age = text.getText().strip();

		}
	}

	class Practice extends JPanel implements ActionListener {
		private static final long serialVersionUID = 1L;
		private JButton b1, b2, b3;
		private JLabel l, soundIcon;
		private JTextField text;

		public Practice() {
			b1 = new JButton("Quit Practice");
			b1.setAlignmentX(LEFT_ALIGNMENT);
			b1.addActionListener(this);
			this.add(b1);
			l = new JLabel("User, level");
			l.setAlignmentX(JLabel.CENTER_ALIGNMENT);
			l.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
			this.add(l);
			soundIcon = new JLabel(new ImageIcon("src/soundIcon"));
			soundIcon.setAlignmentX(CENTER_ALIGNMENT);
			this.add(soundIcon);
			text = new JTextField();
			this.add(text);
			b2 = new JButton("Give Up");
			b2.setAlignmentX(JButton.LEFT_ALIGNMENT);
			b2.setAlignmentY(JButton.BOTTOM_ALIGNMENT);
			b2.addActionListener(this);
			this.add(b2);
			b3 = new JButton("Submit");
			b3.setAlignmentX(RIGHT_ALIGNMENT);
			b3.setAlignmentY(BOTTOM_ALIGNMENT);
			b3.addActionListener(this);
			this.add(b3);
		}

		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub

		}
	}

	class CorrectAnswer extends JPanel implements ActionListener {

		private static final long serialVersionUID = -1972509954050125796L;
		private JButton b1, b2;
		private JLabel l, background;
		private JTextField text;

		public CorrectAnswer() {
			b1 = new JButton("Quit Practice");
			b1.setAlignmentX(LEFT_ALIGNMENT);
			b1.addActionListener(this);
			this.add(b1);
			l = new JLabel("User, level");
			l.setAlignmentX(JLabel.CENTER_ALIGNMENT);
			l.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
			this.add(l);
			background = new JLabel(new ImageIcon("src/confetti"));
			background.setAlignmentX(CENTER_ALIGNMENT);
			this.add(background);
			text = new JTextField();
			this.add(text);
			b2 = new JButton("Continue");
			b2.setAlignmentX(CENTER_ALIGNMENT);
			b2.addActionListener(this);
			this.add(b2);
		}

		public void actionPerformed(ActionEvent e) {

		}
	}

	class IncorrectAnswer extends JPanel implements ActionListener {

		private static final long serialVersionUID = 5574221960635661726L;

		private JButton b1, b2, b3;
		private JLabel l1, l2, l3, soundIcon;
		private JTextField text;

		public IncorrectAnswer() {
			b1 = new JButton("Quit Practice");
			b1.setAlignmentX(LEFT_ALIGNMENT);
			b1.addActionListener(this);
			this.add(b1);
			l1 = new JLabel("User, level");
			l1.setAlignmentX(JLabel.CENTER_ALIGNMENT);
			l1.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
			this.add(l1);
			l2 = new JLabel("Not Quite");
			l2.setAlignmentX(CENTER_ALIGNMENT);
			this.add(l2);
			soundIcon = new JLabel(new ImageIcon("src/soundIcon"));
			soundIcon.setAlignmentX(CENTER_ALIGNMENT);
			this.add(soundIcon);
			text = new JTextField();
			this.add(text);
			l3 = new JLabel("Try Again");
			l3.setAlignmentX(JLabel.CENTER_ALIGNMENT);
			this.add(l3);
			b2 = new JButton("Give Up");
			b2.setAlignmentX(JButton.LEFT_ALIGNMENT);
			b2.setAlignmentY(JButton.BOTTOM_ALIGNMENT);
			b2.addActionListener(this);
			this.add(b2);
			b3 = new JButton("Submit");
			b3.setAlignmentX(RIGHT_ALIGNMENT);
			b3.setAlignmentY(BOTTOM_ALIGNMENT);
			b3.addActionListener(this);
			this.add(b3);
		}

		public void actionPerformed(ActionEvent e) {

		}

	}

	class HomePage extends JPanel implements ActionListener {
		JLabel lblWelcome, lblLevel;
		JButton btnPractice, btnSettings;
		JPanel helperPanel = new JPanel();

		public HomePage() {

			this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
			// Temporary holder for user's name config.getUser("name")
			lblWelcome = new JLabel("Welcome, User");
			lblWelcome.setPreferredSize(new Dimension(100, 50));
			this.add(lblWelcome);
			this.add(Box.createRigidArea(new Dimension(0, 20)));

			// Temporary holder for user's name config.getLevel(0)
			lblLevel = new JLabel("Level: 1");
			lblWelcome.setPreferredSize(new Dimension(50, 50));
			this.add(lblLevel);
			this.add(Box.createRigidArea(new Dimension(0, 70)));

			btnPractice = new JButton("Start Practicing");
			btnPractice.setPreferredSize(new Dimension(100, 200));
			this.add(btnPractice);
			this.add(Box.createRigidArea(new Dimension(0, 10)));

			btnSettings = new JButton("Settings");
			btnSettings.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {

				}
			});
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
			user = new JLabel("John, Level 6");
			topPanel.add(user);
			topPanel.add(Box.createHorizontalStrut(30));
			this.add(topPanel);
			this.add(Box.createRigidArea(new Dimension(0, 70)));

			middlePanel = new JPanel();
			middlePanel.setLayout(new BoxLayout(middlePanel, BoxLayout.Y_AXIS));
			chooseLevel = new JLabel("Choose a level: ");
			middlePanel.add(chooseLevel);
			// Initialize array with available levels
			Level l[] = { null };
			levels = new JComboBox(l);
			middlePanel.add(levels);
			this.add(middlePanel);
			this.add(Box.createRigidArea(new Dimension(0, 70)));

			bottomPanel = new JPanel(new GridLayout(2, 2, 10, 10));
			modifyLevel = new JButton("Modify Level");
			modifyLevel.setPreferredSize(new Dimension(40, 40));
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
		JPanel topPanel, middlePanel, bottomPanel;
		JButton home, modifyWord, removeWord, changeLevel, previewSound, btnChoose, addWord;
		JLabel user, level;
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
			this.add(Box.createRigidArea(new Dimension(0, 70)));
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
		home = new SettingsPage();
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
		Spelling cl = new Spelling();
		cl.setSize(370, 400);
		cl.setVisible(true);
		cl.setDefaultCloseOperation(EXIT_ON_CLOSE);
		cl.setResizable(false);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		layout.next(contentPane);

	}

}
