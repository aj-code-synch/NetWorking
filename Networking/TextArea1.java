package Networking;

import java.awt.event.*;
import java.awt.*;


import javax.swing.*;

public class TextArea1 implements ActionListener {

	JTextArea text;
	public void go() {
		JFrame frame = new JFrame();
		JPanel panel = new JPanel () ;
		JButton button = new JButton("Just Click It");
		button. addActionListener (this) ;
		text = new JTextArea(40,60);
		text.setLineWrap(true);



		JScrollPane scroller = new JScrollPane(text);
		scroller.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scroller.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		panel. add (scroller) ;
		frame.getContentPane().add(BorderLayout.CENTER, panel);
		frame.getContentPane().add(BorderLayout.SOUTH, button);
		frame.setSize(350,300);
		frame.setVisible(true);

	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		text.append("Button ");

	}


	public static void main(String[] args) {
		// TODO Auto-generated method stub
		TextArea1 gui = new TextArea1();
		gui.go();
	}

}
