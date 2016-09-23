package ascend;

import java.util.ArrayList;

//this is the tile object. It has a x and a y.
public class Tile {
	int x, y;
	char attribute;
	/*
	  	there can be multiple units on a tile
		but only one actor
		maybe a boolean occupied?
	*/
	ArrayList<Unit> units = new ArrayList<Unit>();
	
	public Tile(int x, int y){
		this.x=x;
		this.y=y;
		attribute = '#';
	}
}
