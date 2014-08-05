package com.icyfillup.game.level.tiles;

import com.icyfillup.game.gfx.Colours;
import com.icyfillup.game.gfx.Screen;
import com.icyfillup.game.level.Level;

public abstract class Tile {
	
	public static final Tile[]	tiles	= new Tile[256];
	
	public static final Tile	VOID	= new BasicSolidTile(0, 0, 0, Colours.get(000, -1, -1, -1));
	public static final Tile	STONE	= new BasicSolidTile(1, 1, 0, Colours.get(-1, 333, -1, -1));
	public static final Tile	GRASS	= new BasicTile(2, 2, 0, Colours.get(-1, 131, 141, -1));
	
	protected byte				ID;
	protected boolean			solid;
	protected boolean			emiter;
	
	public Tile(int id, boolean isSolid, boolean isEmiter)
	{
		this.ID = (byte) id;
		if (tiles[id] != null) throw new RuntimeException(
				"Duplicate tile id on " + id);
		this.solid = isSolid;
		this.emiter = isEmiter;
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
	
	public abstract void render(Screen screen, Level level, int x, int y);
	
}
