package blah.game.main;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

import blah.game.main.gfx.ImageLoader;



public class GameMenu extends JFrame implements ActionListener{
	
	public static final int WIDTH = 600, HEIGHT = 300, SCALE = 2, TILESIZE = 16;
	
	private JPanel contentPane;
	private JPanel gamePane;
	private JPanel menuPane;
	private JPanel endPane;
	
	private JButton startButton;
	private JButton scoresButton;
	private JButton pauseButton;
	private JButton quitButton;
	private JButton submitButton;
	
	private ImageIcon startIcon;
	private ImageIcon startIconMouseOver;
	private BufferedImage startButtonImage;
	private BufferedImage startButtonImageMouseOver;
	
	private ImageIcon scoresIcon;
	private ImageIcon scoresIconMouseOver;
	private BufferedImage scoresButtonImage;
	private BufferedImage scoresButtonImageMouseOver;
	
	private boolean gameStart = true;
	
	private StartMenu menu;
	private static Game game;
	
	public static Timer timer;
	
	public GameMenu()
	{
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(WIDTH*SCALE,HEIGHT*SCALE);
		setResizable(false);
		setLocationRelativeTo(null);
		
		ImageLoader loader = new ImageLoader();
		
		contentPane = new JPanel();
		contentPane.setLayout(new CardLayout());
		setContentPane(contentPane);
		
		gamePane = new JPanel();
		contentPane.add(gamePane);
		gamePane.setVisible(false);
		gamePane.setLayout(null);
		gamePane.setBounds(0, 0, WIDTH*SCALE, HEIGHT*SCALE);
		
		menuPane = new JPanel();
		contentPane.add(menuPane);
		menuPane.setVisible(true);
		menuPane.setLayout(null);
		menuPane.setBounds(0, 0, WIDTH*SCALE, HEIGHT*SCALE);
		
		startButtonImage = loader.load("/playButton.png");
		startButtonImageMouseOver = loader.load("/playButtonMouseOver.png");
		startIcon = new ImageIcon(startButtonImage);
		startIconMouseOver = new ImageIcon(startButtonImageMouseOver);
		startButton = new JButton();
		startButton.setIcon(startIcon);
		menuPane.add(startButton);
		startButton.setVisible(true);
		startButton.setBorderPainted(false);
		startButton.setBounds(384,222,172,102);
		startButton.addActionListener(this);
		startButton.addMouseListener(new MouseAdapter()
		{
			public void mouseEntered(MouseEvent e) 
			{
				startButton.setIcon(startIconMouseOver);	
			}
			public void mouseExited(MouseEvent e) 
			{
				startButton.setIcon(startIcon);	
			}
														});
		
		scoresButtonImage = loader.load("/scoresButton.png");
		scoresButtonImageMouseOver = loader.load("/scoresButtonMouseOver.png");
		scoresIcon = new ImageIcon(scoresButtonImage);
		scoresIconMouseOver = new ImageIcon(scoresButtonImageMouseOver);
		scoresButton = new JButton();
		scoresButton.setIcon(scoresIcon);
		menuPane.add(scoresButton);
		scoresButton.setVisible(true);
		scoresButton.setBorderPainted(false);
		scoresButton.setBounds(656,222,254,102);
		scoresButton.addActionListener(this);
		
		scoresButton.addMouseListener(new MouseAdapter()
		{
			public void mouseEntered(MouseEvent e) 
			{
				scoresButton.setIcon(scoresIconMouseOver);	
			}
			public void mouseExited(MouseEvent e) 
			{
				scoresButton.setIcon(scoresIcon);	
			}
														});
		
		endPane = new JPanel();
		gamePane.add(endPane);
		endPane.setVisible(false);
		endPane.setLayout(null);
		endPane.setBounds(450,200,300,200);
		endPane.setBackground(Color.GRAY);
		
		submitButton = new JButton("Main Menu");
		endPane.add(submitButton);
		submitButton.setBounds(125,85,100,30);
		submitButton.addActionListener(this);

		timer = new Timer(5,this);
		timer.start();
	}
	
	@Override
	public void actionPerformed(ActionEvent e) 
	{
		Object src = e.getSource();
		//Initialize Game Menu
		if(gameStart)
		{
			timer.stop();
			menu = new StartMenu(WIDTH,HEIGHT,SCALE,TILESIZE);
			menu.setPreferredSize(new Dimension (WIDTH * SCALE, HEIGHT * SCALE));
			menu.setMaximumSize(new Dimension (WIDTH * SCALE, HEIGHT * SCALE));
			menu.setMinimumSize(new Dimension (WIDTH * SCALE, HEIGHT * SCALE));
			menu.setBounds(0, 0, WIDTH*SCALE, HEIGHT*SCALE);
			menuPane.add(menu);
			menu.start();
			gameStart = false;
		}
		
		if(src == startButton)
		{
			menu.stop();
			gamePane.setVisible(true);
			menuPane.setVisible(false);
			game = new Game(WIDTH,HEIGHT,SCALE,TILESIZE);
			game.setPreferredSize(new Dimension (WIDTH * SCALE, HEIGHT * SCALE));
			game.setMaximumSize(new Dimension (WIDTH * SCALE, HEIGHT * SCALE));
			game.setMinimumSize(new Dimension (WIDTH * SCALE, HEIGHT * SCALE));
			game.setBounds(0, 0, WIDTH*SCALE, HEIGHT*SCALE);
			gamePane.add(game);
			game.start();
		}
		if(Game.endGame)
		{
			System.out.println("Game null");
			endPane.setVisible(true);
			Game.endGame = false;
			timer.stop();
			game = null;
			game = new Game(WIDTH,HEIGHT,SCALE,TILESIZE);
		}
		
		if(src == submitButton)
		{
			gamePane.setVisible(false);
			endPane.setVisible(false);
			menuPane.setVisible(true); 
			menu.start();
		}	
	}
	
	public static void main(String[] args) 
	{
		GameMenu menu = new GameMenu();
		Thread menuThread = new Thread(game);
		menu.setVisible(true);
	}
}
