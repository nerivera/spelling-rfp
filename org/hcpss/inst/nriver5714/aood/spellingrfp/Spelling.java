package org.hcpss.inst.nriver5714.aood.spellingrfp;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

//Temporary setup for Spelling class
public class Spelling extends JFrame implements ActionListener{
	
	CardLayout layout;
	Container c;
	//JButton b1, b2, b3;
	
	public Spelling() {
		
		c = getContentPane();
		layout = new CardLayout(40, 30);
		
		c.setLayout(layout);
		/*
		b1 = new JButton("Login Page");
		b2 = new JButton("Practice");
		b3 = new JButton("Settings");
		
		b1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				card.next(c);
			}
		});
		
		b2.addActionListener(this);
		b3.addActionListener(this);
		
		 c.add("a",b1);c.add("b",b2);c.add("c",b3);    
		 */
		
	}
	
    public static void main(String[] args) {    
        Spelling cl=new Spelling();    
        cl.setSize(400,400);    
        cl.setVisible(true);    
        cl.setDefaultCloseOperation(EXIT_ON_CLOSE); 
        cl.setResizable(false);
    }

	@Override
	public void actionPerformed(ActionEvent e) {
		layout.next(c);
		
	}


}
