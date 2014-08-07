package com.icyfillup.game.net;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;

import com.icyfillup.game.Game;
import com.icyfillup.game.entities.PlayerMP;
import com.icyfillup.game.net.packets.Packet;
import com.icyfillup.game.net.packets.Packet.PacketTypes;
import com.icyfillup.game.net.packets.Packet00Login;
import com.icyfillup.game.net.packets.Packet01Disconnect;
import com.icyfillup.game.net.packets.Packet02Move;

public class GameClient extends Thread {
	private InetAddress		ipAddress;
	private DatagramSocket	socket;
	private Game			game;
	
	public GameClient(Game game, String ipAddress)
	{
		this.game = game;
		try
		{
			this.socket = new DatagramSocket();
			this.ipAddress = InetAddress.getByName(ipAddress);
		}
		catch (SocketException e)
		{
			e.printStackTrace();
		}
		catch (UnknownHostException e)
		{
			e.printStackTrace();
		}
	}
	
	public void run()
	{
		while(true)
		{
			byte[] data = new byte[1024];
			DatagramPacket packet = new DatagramPacket(data, data.length);
			try
			{
				socket.receive(packet);
			}
			catch (IOException e)
			{
				e.printStackTrace();
			}
			
			this.parsePacket(packet.getData(), packet.getAddress(), packet.getPort());
//			String message = new String(packet.getData());
//			System.out.println("SERVER > " + message);
		}
	}
	
	private void parsePacket(byte[] data, InetAddress address, int port)
	{
		String message = new String(data).trim();
		PacketTypes type =  Packet.lookupPacket(message.substring(0, 2));
		Packet packet = null;
		
		
		switch(type)
		{
			default:
			case INVALID:
				break;
			case LOGIN:
				packet = new Packet00Login(data);
				System.out.println("[" + address.getHostAddress() + ": " + port + "]" + ((Packet00Login) packet).getUsername() + " has join the game...");
				PlayerMP player = player = new PlayerMP(game.level, 100, 100, ((Packet00Login) packet).getUsername(), address, port);	
				
				game.level.addEntity(player);
				break;
			case DISCONNECT:
				packet = new Packet01Disconnect(data);
				System.out.println("[" + address.getHostAddress() + ": " + port + "]" + ((Packet01Disconnect) packet).getUsername() + " has left the world...");
				game.level.removePlayerMP(((Packet01Disconnect)packet).getUsername());
			case MOVE:
				packet = new Packet02Move(data);
				handlePacket(((Packet02Move) packet));
				break;
		}
	}

	public void sendData(byte[] data)
	{
		DatagramPacket packet = new DatagramPacket(data, data.length, ipAddress, 1331);
		try
		{
			socket.send(packet);
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}
	
	
	private void handlePacket(Packet02Move packet)
	{
		this.game.level.movePlayer(packet.getUsername(), packet.getX(), packet.getY());
	}
}
