package com.icyfillup.game.entities;

import com.icyfillup.game.InputHandler;
import com.icyfillup.game.gfx.Colours;
import com.icyfillup.game.gfx.Screen;
import com.icyfillup.game.level.Level;

public class Player extends Mob {
	
	private InputHandler	input;
	private int				colour	= Colours.get(-1, 111, 145, 543);
	private int				scale	= 1;
	
	public Player(Level level, int x, int y, InputHandler input)
	{
		super(level, "Player", x, y, 1);
		this.input = input;
	}
	
	public void tick()
	{
		int xa = 0;
		int ya = 0;
		
		if (input.up.isPressed()) ya--;
		if (input.down.isPressed()) ya++;
		if (input.left.isPressed()) xa--;
		if (input.right.isPressed()) xa++;
		
		if (xa != 0 || ya != 0)
		{
			move(xa, ya);
			isMoving = true;
		}
		else isMoving = false;
		
		this.scale = 1;
	}
	
	public void render(Screen screen)
	{
		int xTile = 0;
		int yTile = 28;
		int walkingSpeed = 2;
		int flipTop = (numSteps >> walkingSpeed) & 1;
		int flipBottom = (numSteps >> walkingSpeed) & 1;
		
		if(movingDir == 1)
		{
			xTile += 2;
		}
		else if(movingDir > 1)
		{
			xTile += 4 + ((numSteps >> walkingSpeed) & 1) * 2;
			flipTop = (movingDir - 1) % 2;
		}
		
		int modtifier = 8 * scale;
		int xOffset = x - modtifier / 2;
		int yOffset = y - modtifier / 2 - 4;
		
		screen.render(xOffset + (modtifier * flipTop), yOffset, xTile + yTile * 32, colour, flipTop, scale);
		screen.render(xOffset + modtifier - (modtifier * flipTop), yOffset, (xTile + 1) + yTile * 32,
				colour, flipTop, scale);
		screen.render(xOffset + (modtifier * flipBottom), yOffset + modtifier, xTile + ((yTile + 1) * 32),
				colour, flipBottom, scale);
		screen.render(xOffset + modtifier - (modtifier * flipBottom), yOffset + modtifier, (xTile + 1)
				+ ((yTile + 1) * 32), colour, flipBottom, scale);
		
	}
	
	public boolean hasCollided(int xa, int ya)
	{
		return false;
	}
	
}
