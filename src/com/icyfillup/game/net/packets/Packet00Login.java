package com.icyfillup.game.net.packets;

import com.icyfillup.game.net.GameClient;
import com.icyfillup.game.net.GameServer;

public class Packet00Login extends Packet {
	private String	username;
	private int		x, y;
	
	public Packet00Login(byte[] data)
	{
		super(00);
		String[] dataArray = readData(data).split(",");
		this.username = dataArray[0];
		this.x = Integer.parseInt(dataArray[1]);
		this.y = Integer.parseInt(dataArray[2]);
	}
	
	public Packet00Login(String username, int x, int y)
	{
		super(00);
		this.username = username;
		this.x = x;
		this.y = y;
	}
	
	public void writeData(GameClient client)
	{
		client.sendData(getData());
	}
	
	public void writeData(GameServer server)
	{
		server.sendDataToAllClients(getData());
	}
	
	public byte[] getData()
	{
		return ("00" + this.username + "," + this.x + "," + this.y).getBytes();
	}
	
	public String getUsername()
	{
		return username;
	}
	
	public int getX()
	{
		return this.x;
	}
	
	public int getY()
	{
		return this.y;
	}
}
