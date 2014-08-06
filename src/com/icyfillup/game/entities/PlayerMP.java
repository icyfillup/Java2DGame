package com.icyfillup.game.entities;

import java.net.InetAddress;

import com.icyfillup.game.InputHandler;
import com.icyfillup.game.level.Level;

public class PlayerMP extends Player {
	public InetAddress	ipAddress;
	public int			port;
	
	public PlayerMP(Level level, int x, int y, InputHandler input, String userName, InetAddress ipAddress, int port)
	{
		super(level, x, y, input, userName);
		this.ipAddress = ipAddress;
		this.port = port;
	}
	
	public PlayerMP(Level level, int x, int y, String userName, InetAddress ipAddress, int port)
	{
		super(level, x, y, null, userName);
		this.ipAddress = ipAddress;
		this.port = port;
	}
	
	public void tick()
	{
		super.tick();
	}
}
