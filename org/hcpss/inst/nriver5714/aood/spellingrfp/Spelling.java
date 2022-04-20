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
			/*
			if(Configuration.userExists(name) {
				currentUser = Configuration.getUser(name);
				layout.next(contentPane);
			}
			*/
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
	
	class HomePage extends JPanel implements ActionListener {
		JLabel lblWelcome, lblLevel;
		JButton btnPractice, btnSettings;
		JPanel helperPanel = new JPanel();
		
		public HomePage() {
		
			this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
			//Temporary holder for user's name config.getUser("name")
			lblWelcome = new JLabel("Welcome, User");
			lblWelcome.setPreferredSize(new Dimension(100, 50));
			this.add(lblWelcome);
			this.add(Box.createRigidArea(new Dimension(0, 20)));
			
			//Temporary holder for user's name config.getLevel(0)
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
			 * layout.show(contentPane, indexOfPanel)
			 * indexOfPanel refers to the panel at the position of the specified index
			 */
			
		}

	}

	class SettingsPage extends JPanel implements ActionListener {
		//top - FlowLayout, middle - BoxLayout, bottom - GridLayout;
		JPanel topPanel, middlePanel, bottomPanel;
		JButton home, modifyLevel, removeLevel, insertLevelBefore, AddLevelAfter;
		JLabel user, chooseLevel;
		JComboBox levels;
		
		public SettingsPage() {
			topPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
			home = new JButton("Home");
			topPanel.add(home);
			topPanel.add(Box.createHorizontalStrut(30));
			//Use Configuration class to retrieve name and level
			user = new JLabel("John, Level 6");
			topPanel.add(user);
			this.add(topPanel);
			this.add(Box.createRigidArea(new Dimension(0, 70)));
			
			middlePanel = new JPanel();
			middlePanel.setLayout(new BoxLayout(middlePanel, BoxLayout.Y_AXIS));
			chooseLevel = new JLabel("Choose a level");
			middlePanel.add(chooseLevel);
			//Initialize array with words from the chosen level
			Word words[] = {null};
			levels = new JComboBox(words);
			middlePanel.add(levels);
			this.add(middlePanel);
			this.add(Box.createRigidArea(new Dimension(0, 70)));
			
			bottomPanel = new JPanel(new GridLayout(2, 2, 10, 10));
			modifyLevel = new JButton("Modify Level");
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
	
	public Spelling() {

		contentPane = getContentPane();
		layout = new CardLayout(100, 20);

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

	public static void main(String[] args) {
		Spelling cl = new Spelling();
		cl.setSize(350, 450);
		cl.setVisible(true);
		cl.setDefaultCloseOperation(EXIT_ON_CLOSE);
		//cl.setResizable(false);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		layout.next(contentPane);

	}

}
