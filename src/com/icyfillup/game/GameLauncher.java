package com.icyfillup.game;

import java.applet.Applet;
import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JFrame;

@SuppressWarnings("serial")
public class GameLauncher extends Applet
{
	private static Game game = new Game();
	public static final boolean DEBUG = false;
	
	public void init() 
	{
		setLayout(new BorderLayout());
		add(game, BorderLayout.CENTER);
		setMinimumSize(Game.DIMENSION);
		setMaximumSize(Game.DIMENSION);
		setPreferredSize(Game.DIMENSION);
		game.debug = DEBUG;
		game.isApplet = true;
	}
	
	public void start() 
	{
		game.start();
	}
	
	public void stop() 
	{
		game.stop();
	}
	
	public static void main(String[] args)
	{
		game.setMinimumSize(Game.DIMENSION);
		game.setMaximumSize(Game.DIMENSION);
		game.setPreferredSize(Game.DIMENSION);
		
		game.frame = new JFrame(Game.NAME);
		
		game.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		game.frame.setLayout(new BorderLayout());
		
		game.frame.add(game, BorderLayout.CENTER);
		game.frame.pack();
		
		game.frame.setResizable(false);
		game.frame.setLocationRelativeTo(null);
		game.frame.setVisible(true);

		game.windowHandler = new WindowHandler(game);
		
		game.start();
	}
	
}
