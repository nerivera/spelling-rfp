package org.hcpss.inst.nriver5714.aood.spellingrfp;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

//Temporary setup for Spelling class
public class Spelling extends JFrame implements ActionListener {

	CardLayout layout;
	Container c;

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

		}

	}

	public Spelling() {

		c = getContentPane();
		layout = new CardLayout(40, 30);

		c.setLayout(layout);
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
		cl.setSize(400, 400);
		cl.setVisible(true);
		cl.setDefaultCloseOperation(EXIT_ON_CLOSE);
		cl.setResizable(false);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		layout.next(c);

	}

}
