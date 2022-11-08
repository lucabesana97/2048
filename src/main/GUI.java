package main;
import java.awt.*;
import javax.swing.*;
import java.util.Hashtable;
import java.util.Map;
import java.net.URL;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class GUI {

	Game game = new Game();

	private final int boardSize = 64 * game.getSize() + 8 * (game.getSize() + 1);
	private final int gameWidth = boardSize + 32;
	private final int gameHeight = boardSize + 128;
	private final int marginSize = 16;
	private final int topHeight = 112;
	private final Color frame_col = new Color(209, 202, 235);
	private final Color board_col = new Color(201, 196, 181);
	private int topScore = 0;

	Map tilesNum;
	JLabel scoreLab;
	JLabel topLab;

	JFrame window;
	public GUI() {
		window = new JFrame();
		window.setFocusable(true);
		window.addKeyListener(new MyFrame());
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		loadTiles();

		GamePanel gp = new GamePanel();
		//gp.setFocusable(true);

		// Setting the top panel with scores
		JPanel topPanel = new JPanel();
		topPanel.setLayout(new GridLayout());
		topPanel.setPreferredSize(new Dimension(gameWidth, topHeight));

		JLabel label = new JLabel();
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setText("2048");
		label.setFont(new Font("Arial", Font.BOLD, 40));

		topPanel.add(label);
		scoreLab = new JLabel("<html>Score:<br/>" + game.getScore() + "</html>", SwingConstants.CENTER);
		scoreLab.setFont(new Font("Arial", Font.BOLD, 20));
		topLab = new JLabel("<html>Top Score:<br/>" + topScore + "</html>", SwingConstants.CENTER);
		topLab.setFont(new Font("Arial", Font.BOLD, 20));
		topPanel.add(topLab);
		topPanel.add(scoreLab);
		topPanel.setBackground(frame_col);

		//Setting the three remaining panels as empty spaces around the game
		JPanel leftPanel = new JPanel();
		leftPanel.setPreferredSize(new Dimension(marginSize, boardSize));
		leftPanel.setBackground(frame_col);

		JPanel rightPanel = new JPanel();
		rightPanel.setPreferredSize(new Dimension(marginSize, boardSize));
		rightPanel.setBackground(frame_col);

		JPanel botPanel = new JPanel();
		botPanel.setPreferredSize(new Dimension(gameWidth + 2*marginSize, marginSize));
		botPanel.setBackground(frame_col);

		//Adding the panels to the JFrame
		window.getContentPane().add(topPanel, BorderLayout.NORTH);
		window.getContentPane().add(leftPanel, BorderLayout.WEST);
		window.getContentPane().add(rightPanel, BorderLayout.EAST);
		window.getContentPane().add(botPanel, BorderLayout.SOUTH);
		window.getContentPane().add(gp, BorderLayout.CENTER);

		window.getContentPane().setPreferredSize(new Dimension(gameWidth, gameHeight));
		window.pack();
		window.setVisible(true);
	}

	private int getTopScore() {
		if (game.getScore() > topScore) {
			topScore = game.getScore();
		}
		return topScore;
	}
	private void updateLabs() {
		scoreLab.setText("<html>Score:<br/>" + game.getScore() + "</html>");
		topLab.setText("<html>Top Score:<br/>" + getTopScore() + "</html>");
	}
	private void loadTiles() {
		tilesNum = new Hashtable();
		ClassLoader cldr = this.getClass().getClassLoader();
		URL url0000 = cldr.getResource("images/tile_0.png");
		URL url0002 = cldr.getResource("images/tile_2.png");
		URL url0004 = cldr.getResource("images/tile_4.png");
		URL url0008 = cldr.getResource("images/tile_8.png");
		URL url0016 = cldr.getResource("images/tile_16.png");
		URL url0032 = cldr.getResource("images/tile_32.png");
		URL url0064 = cldr.getResource("images/tile_64.png");
		URL url0128 = cldr.getResource("images/tile_128.png");
		URL url0256 = cldr.getResource("images/tile_256.png");
		URL url0512 = cldr.getResource("images/tile_512.png");
		URL url1024 = cldr.getResource("images/tile_1024.png");
		URL url2048 = cldr.getResource("images/tile_2048.png");
		tilesNum.put(0, new ImageIcon(url0000));
		tilesNum.put(2, new ImageIcon(url0002));
		tilesNum.put(4, new ImageIcon(url0004));
		tilesNum.put(8, new ImageIcon(url0008));
		tilesNum.put(16, new ImageIcon(url0016));
		tilesNum.put(32, new ImageIcon(url0032));
		tilesNum.put(64, new ImageIcon(url0064));
		tilesNum.put(128, new ImageIcon(url0128));
		tilesNum.put(256, new ImageIcon(url0256));
		tilesNum.put(512, new ImageIcon(url0512));
		tilesNum.put(1024, new ImageIcon(url1024));
		tilesNum.put(2048, new ImageIcon(url2048));

	}

	class GamePanel extends JPanel{
		@Override
		protected void paintComponent(Graphics graph) {
			graph.setColor(board_col);
			graph.fillRect(0, 0, this.getWidth(), this.getHeight());
			int[][] test = game.getGameBoard();
			for(int i = 1; i < game.getSize() + 1; ++i) {
				for(int j = 1; j < game.getSize() + 1; ++j) {
					int x_elem = 8 * i + 64 * (i - 1);
					int y_elem = 8 * j + 64 * (j - 1);

					int temp = test[j - 1][i - 1];
					if (tilesNum.containsKey(temp));{
						ImageIcon tileTemp = (ImageIcon)tilesNum.get(temp);
						tileTemp.paintIcon(this, graph, x_elem, y_elem);
					}
				}
			}
			if(game.getGO() == true) {
				graph.setColor(Color.MAGENTA);
				graph.fillRoundRect(20, 20, boardSize - 40, boardSize - 40, 50, 50);
				String over_1 = "GAME";
				String over_2 = "OVER";
				String over_3 = "Press enter";
				String over_4 = "To try again";
				String over_5 = "Top";
				String over_6 = "Score";
				String over_7 = ": " + String.valueOf(game.getScore());
				graph.setColor(Color.black);
				graph.setFont(new Font("Monospaced", Font.BOLD, 90));
				graph.drawString(over_1, 40, 80);
				graph.drawString(over_2, 40, 150);
				graph.setFont(new Font("Monospaced", Font.BOLD, 30));
				graph.drawString(over_3, 40, 190);
				graph.drawString(over_4, 40, 210);
				graph.setFont(new Font("Monospaced", Font.BOLD, 20));
				graph.drawString(over_5, 70, 235);
				graph.drawString(over_6, 70, 250);
				graph.drawString(over_7, 140, 243);
			}
			else if (game.getVic() == true){
				graph.setColor(Color.black);
				graph.fillRoundRect(20, 20, boardSize - 40, boardSize - 40, 50, 50);
				String over_1 = "YOU";
				String over_2 = "WIN!";
				String over_3 = "Press enter";
				String over_4 = "To try again";
				String over_5 = "Top";
				String over_6 = "Score";
				String over_7 = ": " + String.valueOf(game.getScore());
				graph.setColor(new Color(212, 175, 55));
				graph.setFont(new Font("Monospaced", Font.BOLD, 90));
				graph.drawString(over_1, 65, 80);
				graph.drawString(over_2, 65, 150);
				graph.setFont(new Font("Monospaced", Font.BOLD, 30));
				graph.drawString(over_3, 40, 190);
				graph.drawString(over_4, 40, 210);
				graph.setFont(new Font("Monospaced", Font.BOLD, 20));
				graph.drawString(over_5, 70, 235);
				graph.drawString(over_6, 70, 250);
				graph.drawString(over_7, 140, 243);
			}
		}
	}

	class MyFrame extends KeyAdapter{
		@Override
		public void keyPressed(KeyEvent e) {
			if (game.getVic() == true || game.getGO() == true) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					game = new Game();
					//System.out.println(game.getVic());
					window.repaint();
				}
			}
			else {
				if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
					//System.out.println("Right key pressed");
					game.right();
					game.generate();
					game.printBoard();
					window.repaint();
					updateLabs();

				}

				if (e.getKeyCode() == KeyEvent.VK_LEFT) {
					//System.out.println("Left key pressed");
					game.left();
					game.generate();
					game.printBoard();
					window.repaint();
					updateLabs();

				}

				if (e.getKeyCode() == KeyEvent.VK_UP) {
					//System.out.println("Up key pressed");
					game.up();
					game.generate();
					game.printBoard();
					window.repaint();
					updateLabs();

				}

				if (e.getKeyCode() == KeyEvent.VK_DOWN) {
					//System.out.println("Down key pressed");
					game.down();
					game.generate();
					game.printBoard();
					window.repaint();
					updateLabs();

				}
			}
		}
	}
}
