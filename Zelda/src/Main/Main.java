package Main;

import javax.swing.JFrame;

public class Main {

	public static void main(String[] args) {
		JFrame window= new JFrame();
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setResizable(false);
		window.setTitle("Zelda");
		window.setLocationRelativeTo(null);
		
		GamePanel panel=new GamePanel();
		window.add(panel);
		window.pack();
		window.setVisible(true);
		panel.startGameThread();
		System.out.println("ciao sono qui");
		
	}

}
