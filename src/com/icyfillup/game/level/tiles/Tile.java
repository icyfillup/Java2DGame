package com.icyfillup.game.level.tiles;

import com.icyfillup.game.gfx.Colours;
import com.icyfillup.game.gfx.Screen;
import com.icyfillup.game.level.Level;

public abstract class Tile {
	
	public static final Tile[]	tiles	= new Tile[256];
	
	public static final Tile	VOID	= new BasicSolidTile(0, 0, 0,
												Colours.get(000, -1, -1, -1),
												0xFF000000);
	public static final Tile	STONE	= new BasicSolidTile(1, 1, 0,
												Colours.get(-1, 333, -1, -1),
												0xFF555555);
	public static final Tile	GRASS	= new BasicTile(2, 2, 0, Colours.get(
												-1, 131, 141, -1), 0xFF00FF00);
	public static final Tile	WATER	= new AnimatedTile(3, new int[][] {
			{ 0, 5 }, { 1, 5 }, { 2, 5 }, { 1, 5 } }, Colours.get(-1, 004, 115,
												-1), 0xFF0000FF, 1000);
	
	protected byte				ID;
	protected boolean			solid;
	protected boolean			emiter;
	private int					levelColour;
	
	public Tile(int id, boolean isSolid, boolean isEmiter, int levelColour)
	{
		this.ID = (byte) id;
		if (tiles[id] != null) throw new RuntimeException(
				"Duplicate tile id on " + id);
		this.solid = isSolid;
		this.emiter = isEmiter;
		this.levelColour = levelColour;
		tiles[id] = this;
	}
	
	public byte getID()
	{
		return ID;
	}
	
	public boolean isSolid()
	{
		return solid;
	}
	
	public boolean isEmiter()
	{
		return emiter;
	}
	
	public int getLevelColour()
	{
		return levelColour;
	}
	
	public abstract void tick();
	
	public abstract void render(Screen screen, Level level, int x, int y);
	
}
