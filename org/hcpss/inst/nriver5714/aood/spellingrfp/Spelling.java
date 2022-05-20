package org.hcpss.inst.nriver5714.aood.spellingrfp;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

//Temporary setup for Spelling class
public class Spelling extends JFrame implements ActionListener {

	CardLayout layout;
	Container contentPane;
	User currentUser;
	String tempUserName;
	LoginPage login;
	AgePrompt age;
	HomePage home;
	Practice practice;
	CorrectAnswer correct;
	IncorrectAnswer incorrect;
	GiveUp giveUp;
	SettingsPage settings;
	LevelModificationPage levelmodification;
	Word word;
	String guess;
	CompletePractice complete;
	int score;

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
			txtName.addActionListener(this);
			txtName.setActionCommand("login");
			this.add(txtName);
			this.add(Box.createVerticalStrut(10));

			btnLogin = new JButton("Login");
			btnLogin.setAlignmentX(Component.CENTER_ALIGNMENT);
			btnLogin.addActionListener(this);
			btnLogin.setActionCommand("login");
			this.add(btnLogin);
		}

		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub

			String name = txtName.getText().strip();
			if (name.isEmpty())
				return;
			if (Configuration.userExists(name)) {
				currentUser = Configuration.getUser(name).orElseThrow();
				initializeUserDependentPages();
				layout.show(contentPane, "home");
			} else {
				layout.show(contentPane, "age");
			}

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
			txtAge.addActionListener(this);
			txtAge.setActionCommand("submit");
			this.add(txtAge);
			this.add(Box.createVerticalStrut(10));

			btnSubmit = new JButton("Submit");
			btnSubmit.setAlignmentX(Component.CENTER_ALIGNMENT);
			btnSubmit.addActionListener(this);
			btnSubmit.setActionCommand("submit");
			this.add(btnSubmit);
		}

		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			String age = txtAge.getText().strip();
			if (age.isEmpty())
				return;
			try {
				currentUser = Configuration.createUser(tempUserName, Integer.parseInt(age));
				initializeUserDependentPages();
				layout.show(contentPane, "home");
			} catch (NumberFormatException ignored) {

			}
		}
	}

	class Practice extends JPanel implements ActionListener {
		private static final long serialVersionUID = 1L;
		JLabel user, spellWord, sentence;
		JButton btnQuit, btnGiveUp, btnSubmit, btnSound;
		JTextField enterSpelling;
		JPanel topPanel, midPanel, btmPanel;

		public Practice() {
			topPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));

			btnQuit = new JButton("Quit Practice");
			btnQuit.setEnabled(false);
			topPanel.add(btnQuit);
			topPanel.add(Box.createHorizontalStrut(50));

			// Retrieve username and level from Configuration class
			user = new JLabel(currentUser.getName() + ", Level " + (currentUser.getLevelIndex() + 1));
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
			btnSound.addActionListener(this);
			btnSound.setActionCommand("sound");
			midPanel.add(btnSound);

			midPanel.add(Box.createVerticalStrut(10));
			sentence = new JLabel();
			sentence.setAlignmentX(Component.CENTER_ALIGNMENT);
			midPanel.add(sentence);

			midPanel.add(Box.createVerticalStrut(10));
			spellWord = new JLabel("Spell the Word");
			spellWord.setAlignmentX(Component.CENTER_ALIGNMENT);
			spellWord.setFont(new Font("Comfortaa", Font.PLAIN, 20));
			midPanel.add(spellWord);

			enterSpelling = new JTextField();
			enterSpelling.setAlignmentX(Component.CENTER_ALIGNMENT);
			enterSpelling.setHorizontalAlignment(JTextField.CENTER);
			enterSpelling.addActionListener(this);
			enterSpelling.setActionCommand("submit");
			midPanel.add(enterSpelling);
			this.add(midPanel);

			btmPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
			btmPanel.add(Box.createVerticalStrut(65));
			btnGiveUp = new JButton("Give Up");
			btnGiveUp.addActionListener(this);
			btnGiveUp.setActionCommand("give up");
			btmPanel.add(btnGiveUp);

			btmPanel.add(Box.createHorizontalStrut(70));
			btnSubmit = new JButton("Submit");
			btnSubmit.addActionListener(this);
			btnSubmit.setActionCommand("submit");
			btmPanel.add(btnSubmit);
			this.add(btmPanel);
		}

		public void showNextWord() {
			Level level = currentUser.getLevel();
			word = level.getNextWord();
			user.setText(currentUser.getName() + ", Level " + (currentUser.getLevelIndex() + 1));
			sentence.setText(word.getSentence());
			enterSpelling.setText("");
		}

		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			switch (e.getActionCommand()) {
			case "sound":
				try {
					String soundName = "assets/audio/pronunciations/man.wav";
					AudioInputStream audioInputStream = AudioSystem
							.getAudioInputStream(new File(soundName).getAbsoluteFile());
					Clip clip = AudioSystem.getClip();
					clip.open(audioInputStream);
					clip.start();
				} catch (Exception ex) {
					ex.printStackTrace();
				}
				break;
			case "submit":
				guess = enterSpelling.getText().strip().toLowerCase();
				if (!guess.isEmpty())
					guess(guess);
				break;
			case "give up":
				score -= 1;
				giveUp.refresh();
				layout.show(contentPane, "give up");
			}
		}
	}

	class CorrectAnswer extends JPanel implements ActionListener {

		private static final long serialVersionUID = -1972509954050125796L;
		private final String[] CONGRATZ_MESSAGES = { "Good Job!", "Well done!", "Impressive!", "Outstanding!",
				"Keep it up!" };
		JLabel user, lblCongratz;
		JButton btnQuit, btnCont;
		JPanel topPanel, btmPanel;

		CorrectAnswer() {
			topPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));

			btnQuit = new JButton("Quit Practice");
			btnQuit.addActionListener(this);
			btnQuit.setActionCommand("quit");
			topPanel.add(btnQuit);
			topPanel.add(Box.createHorizontalStrut(50));

			// Retrieve username and level from Configuration class
			user = new JLabel(currentUser.getName() + ", Level: " + (currentUser.getLevelIndex() + 1));
			topPanel.add(user);
			this.add(topPanel);

			btmPanel = new JPanel();
			btmPanel.setLayout(new BoxLayout(btmPanel, BoxLayout.Y_AXIS));
			btmPanel.add(Box.createVerticalStrut(50));

			lblCongratz = new JLabel(CONGRATZ_MESSAGES[(int) (Math.random() * CONGRATZ_MESSAGES.length)]);
			lblCongratz.setAlignmentX(Component.CENTER_ALIGNMENT);
			lblCongratz.setFont(new Font("Comfortaa", Font.PLAIN, 30));
			btmPanel.add(lblCongratz);
			btmPanel.add(Box.createVerticalStrut(80));

			btnCont = new JButton("Continue");
			btnCont.addActionListener(this);
			btnCont.setActionCommand("continue");
			btnCont.setMaximumSize(new Dimension(250, 100));
			btnCont.setAlignmentX(Component.CENTER_ALIGNMENT);
			btmPanel.add(btnCont);
			this.add(btmPanel);

		}

		public void refresh() {
			lblCongratz.setText(CONGRATZ_MESSAGES[(int) (Math.random() * CONGRATZ_MESSAGES.length)]);
		}

		public void actionPerformed(ActionEvent e) {
			switch (e.getActionCommand()) {
			case "quit":
				complete.updateScore();
				layout.show(contentPane, "complete");
				break;

			case "continue":
				practice.showNextWord();
				layout.show(contentPane, "practice");
				break;
			}
		}
	}

	class IncorrectAnswer extends JPanel implements ActionListener {
		JLabel user, notQuite, spellingAttempt, sentence;
		JButton btnQuit, btnGiveUp, btnSubmit, btnSound;
		JTextField enterSpelling;
		JPanel topPanel, midPanel, btmPanel, spellingAttemptPanel;

		public IncorrectAnswer() {
			topPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));

			btnQuit = new JButton("Quit Practice");
			btnQuit.addActionListener(this);
			btnQuit.setActionCommand("quit");
			topPanel.add(btnQuit);
			topPanel.add(Box.createHorizontalStrut(50));

			// Retrieve username and level from Configuration class
			user = new JLabel(currentUser.getName() + ", Level: " + (currentUser.getLevelIndex() + 1));
			topPanel.add(user);
			this.add(topPanel);

			midPanel = new JPanel();
			midPanel.setLayout(new BoxLayout(midPanel, BoxLayout.Y_AXIS));
			notQuite = new JLabel("Not Quite.");
			notQuite.setAlignmentX(Component.CENTER_ALIGNMENT);
			notQuite.setFont(new Font("Comfortaa", Font.BOLD, 15));
			midPanel.add(notQuite);

			// TODO align spellingAttempt
			spellingAttemptPanel = new JPanel();
			spellingAttempt = new JLabel();
			spellingAttemptPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
			spellingAttemptPanel.add(spellingAttempt);
			midPanel.add(spellingAttemptPanel);
			// midPanel.add(Box.createVerticalStrut(5));

			ImageIcon imageIcon = new ImageIcon("assets/sound-off-icon-40944.png");
			Image image = imageIcon.getImage();
			image = image.getScaledInstance(100, 100, java.awt.Image.SCALE_SMOOTH);
			Icon icon = new ImageIcon(image);
			btnSound = new JButton(icon);
			// btnSound.setOpaque(false);
			btnSound.setAlignmentX(Component.CENTER_ALIGNMENT);
			midPanel.add(btnSound);

			midPanel.add(Box.createVerticalStrut(10));
			sentence = new JLabel();
			sentence.setAlignmentX(Component.CENTER_ALIGNMENT);
			midPanel.add(sentence);

			midPanel.add(Box.createVerticalStrut(15));
			enterSpelling = new JTextField();
			enterSpelling.setAlignmentX(Component.CENTER_ALIGNMENT);
			enterSpelling.setHorizontalAlignment(JTextField.CENTER);
			enterSpelling.addActionListener(this);
			enterSpelling.setActionCommand("submit");
			midPanel.add(enterSpelling);
			this.add(midPanel);

			btmPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
			btmPanel.add(Box.createVerticalStrut(65));
			btnGiveUp = new JButton("Give Up");
			btnGiveUp.addActionListener(this);
			btnGiveUp.setActionCommand("give up");
			btmPanel.add(btnGiveUp);

			btmPanel.add(Box.createHorizontalStrut(70));
			btnSubmit = new JButton("Submit");
			btnSubmit.addActionListener(this);
			btnSubmit.setActionCommand("submit");
			btmPanel.add(btnSubmit);
			this.add(btmPanel);
		}

		public void refresh() {
			SubstringRange incorrectPortion = word.getIncorrectPortion(guess);
			if (incorrectPortion.isEmpty())
				spellingAttempt.setText(guess);
			else
				spellingAttempt.setText(
						"<html>" + guess.substring(0, incorrectPortion.getBeginIndex()) + "<u style=\"color: red\">"
								+ guess.substring(incorrectPortion.getBeginIndex(), incorrectPortion.getEndIndex())
								+ "</u>" + guess.substring(incorrectPortion.getEndIndex()) + "</html>");
			sentence.setText(word.getSentence());
			user.setText(currentUser.getName() + ", Level: " + (currentUser.getLevelIndex() + 1));
			enterSpelling.setText("");
		}

		public void actionPerformed(ActionEvent e) {
			switch (e.getActionCommand()) {
			case "quit":
				complete.updateScore();
				layout.show(contentPane, "complete");
				break;
			case "submit":
				guess = enterSpelling.getText().strip().toLowerCase();
				if (!guess.isEmpty())
					guess(guess);
				break;
			case "give up":
				giveUp.refresh();
				layout.show(contentPane, "give up");
				break;
			}

		}
	}

	class GiveUp extends JPanel implements ActionListener {

		JLabel user, lblReveal;
		JButton btnQuit, btnCont;
		JPanel topPanel, btmPanel;

		GiveUp() {
			topPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));

			btnQuit = new JButton("Quit Practice");
			btnQuit.addActionListener(this);
			btnQuit.setActionCommand("quit");
			topPanel.add(btnQuit);
			topPanel.add(Box.createHorizontalStrut(50));

			// Retrieve username and level from Configuration class
			user = new JLabel(currentUser.getName() + ", Level " + (currentUser.getLevelIndex() + 1));
			topPanel.add(user);
			this.add(topPanel);

			btmPanel = new JPanel();
			btmPanel.setLayout(new BoxLayout(btmPanel, BoxLayout.Y_AXIS));
			btmPanel.add(Box.createVerticalStrut(50));

			lblReveal = new JLabel();
			lblReveal.setAlignmentX(Component.CENTER_ALIGNMENT);
			lblReveal.setFont(new Font("Comfortaa", Font.PLAIN, 30));
			btmPanel.add(lblReveal);
			btmPanel.add(Box.createVerticalStrut(80));

			btnCont = new JButton("Continue");
			btnCont.addActionListener(this);
			btnCont.setActionCommand("continue");
			btnCont.setMaximumSize(new Dimension(250, 100));
			btnCont.setAlignmentX(Component.CENTER_ALIGNMENT);
			btmPanel.add(btnCont);
			this.add(btmPanel);

		}

		public void refresh() {
			lblReveal.setText("The word was \"" + word.getWord() + "\"");
		}

		public void actionPerformed(ActionEvent e) {
			switch (e.getActionCommand()) {
			case "quit":
				complete.updateScore();
				layout.show(contentPane, "complete");
				break;

			case "continue":
				practice.showNextWord();
				layout.show(contentPane, "practice");
				break;
			}
		}
	}

	class CompletePractice extends JPanel implements ActionListener {
		JLabel user, completePractice, scoreLabel;
		JButton btnHome;

		CompletePractice() {
			BoxLayout boxlayout = new BoxLayout(this, BoxLayout.Y_AXIS);
			this.setLayout(boxlayout);

			// retrieve username and level from Configuration class
			user = new JLabel(currentUser.getName() + ", Level " + (currentUser.getLevelIndex() + 1));
			user.setAlignmentX(Component.CENTER_ALIGNMENT);
			user.setFont(new Font("Comfortaa", Font.PLAIN, 15));
			this.add(user);
			this.add(Box.createVerticalStrut(70));

			completePractice = new JLabel("Practice Complete!");
			completePractice.setFont(new Font("Comfortaa", Font.BOLD, 20));
			completePractice.setAlignmentX(Component.CENTER_ALIGNMENT);
			this.add(completePractice);
			this.add(Box.createVerticalStrut(10));

			// calculate score
			scoreLabel = new JLabel("Score: " + score);
			scoreLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
			this.add(scoreLabel);
			this.add(Box.createVerticalStrut(80));

			btnHome = new JButton("Back to Home");

			btnHome = new JButton("Back to Home");
			btnHome.addActionListener(this);
			btnHome.setActionCommand("home");
			btnHome.setMaximumSize(new Dimension(200, 50));
			btnHome.setAlignmentX(Component.CENTER_ALIGNMENT);
			this.add(btnHome);
		}

		public void updateScore() {
			scoreLabel.setText("Score: " + score);
		}

		public void actionPerformed(ActionEvent e) {
			layout.show(contentPane, "home");
		}
	}

	class HomePage extends JPanel implements ActionListener {
		JLabel lblWelcome, lblLevel;
		JButton btnPractice, btnSettings;

		public HomePage() {
			BoxLayout boxlayout = new BoxLayout(this, BoxLayout.Y_AXIS);
			this.setLayout(boxlayout);

			lblWelcome = new JLabel("Welcome, " + currentUser.getName());
			lblWelcome.setFont(new Font("Comfortaa", Font.PLAIN, 15));
			lblWelcome.setAlignmentX(Component.CENTER_ALIGNMENT);
			this.add(lblWelcome);
			this.add(Box.createVerticalStrut(10));

			lblLevel = new JLabel("Level: " + (currentUser.getLevelIndex() + 1));
			lblLevel.setAlignmentX(Component.CENTER_ALIGNMENT);
			this.add(lblLevel);
			this.add(Box.createVerticalStrut(100));

			btnPractice = new JButton("Start Practicing");
			btnPractice.addActionListener(this);
			btnPractice.setActionCommand("practice");
			btnPractice.setAlignmentX(Component.CENTER_ALIGNMENT);
			btnPractice.setFont(new Font("Comfortaa", Font.PLAIN, 20));
			btnPractice.setMaximumSize(new Dimension(250, 100));
			this.add(btnPractice);
			this.add(Box.createVerticalStrut(25));

			btnSettings = new JButton("Settings");
			btnSettings.addActionListener(this);
			btnSettings.setActionCommand("settings");
			btnSettings.setAlignmentX(Component.CENTER_ALIGNMENT);
			btnSettings.setMaximumSize(new Dimension(150, 40));
			this.add(btnSettings);
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			switch (e.getActionCommand()) {
			case "practice":
				score = 0;
				practice.showNextWord();
				layout.show(contentPane, "practice");
				break;

			case "settings":
				layout.show(contentPane, "settings");
				break;
			}

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
			home.addActionListener(this);
			home.setActionCommand("home");

			topPanel.add(home);
			topPanel.add(Box.createHorizontalStrut(30));

			// Use Configuration class to retrieve name and level
			user = new JLabel(currentUser.getName() + ", Level " + (currentUser.getLevelIndex() + 1));
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
			switch (e.getActionCommand()) {
			case "home":
				// TODO Auto-generated method stub
				layout.show(contentPane, "home");
			}
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

			user = new JLabel(currentUser.getName() + ", Level " + (currentUser.getLevelIndex() + 1));
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
		login = new LoginPage();
		contentPane.add(login, "login");
		age = new AgePrompt();
		contentPane.add(age, "age");

		layout.show(contentPane, "login");

	}

	private void initializeUserDependentPages() {
		home = new HomePage();
		contentPane.add(home, "home");
		practice = new Practice();
		contentPane.add(practice, "practice");
		correct = new CorrectAnswer();
		contentPane.add(correct, "correct");
		incorrect = new IncorrectAnswer();
		contentPane.add(incorrect, "incorrect");
		giveUp = new GiveUp();
		contentPane.add(giveUp, "give up");
		settings = new SettingsPage();
		contentPane.add(settings, "settings");
		levelmodification = new LevelModificationPage();
		contentPane.add(levelmodification, "levelmodification");
		complete = new CompletePractice();
		contentPane.add(complete, "complete");
	}

	private void guess(String guess) {
		if (guess.equalsIgnoreCase(word.getWord())) {
			score += 2;
			correct.refresh();
			layout.show(contentPane, "correct");
		} else {
			score -= 1;
			incorrect.refresh();
			layout.show(contentPane, "incorrect");
		}
	}

	public static void runGUI() {
		Spelling cl = new Spelling();
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
