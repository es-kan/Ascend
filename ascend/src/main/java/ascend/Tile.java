package ascend;

import java.util.HashMap;

//Tile object contains x and y coordinates, a char representation, and a Unit occupying it, if any.
public class Tile {

	int x, y;
	char attribute;
	boolean occupied;
	Unit unit;
	
	//init TileTypes and map them to chars.
	enum TileType{WALL_TILE, FLOOR_TILE};
	static HashMap<TileType, Character> typeAttributes = new HashMap<TileType, Character>();
	static{	typeAttributes.put(TileType.WALL_TILE, '#');
			typeAttributes.put(TileType.FLOOR_TILE, '.');
	}
	
	public Tile(int x, int y){
		this.x=x;
		this.y=y;
		setAttribute(TileType.WALL_TILE);
	}
	
	@Override
	public String toString(){
		//check for tile contents
		if(unit != null){
			return Character.toString(unit.attribute);
		}
		return Character.toString(attribute);
	}
	
	public void pushUnit(Unit u) {
		unit = u;
		occupied = checkOccupied();
	}
	
	public boolean checkOccupied(){
		if(unit instanceof Actor){
			return true;
		}
		return false;
	}
	
	public void setAttribute(TileType tt){
		this.attribute = typeAttributes.get(tt);
	}
}
