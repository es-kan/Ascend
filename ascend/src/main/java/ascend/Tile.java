package ascend;

import java.util.ArrayList;

//Tile object contains x and y coordinates, a char representation, and a list of Units occupying it.
public class Tile {
	int x, y;
	char attribute;
	boolean occupied;
	
	ArrayList<Unit> units = new ArrayList<Unit>();
	
	public Tile(int x, int y){
		this.x=x;
		this.y=y;
		attribute = '#';
	}
	
	@Override
	public String toString(){
		//check for tile contents
		if(units.size() > 0){
			for(Unit u : units){
				//huilen met de pet op maar ok for now
				return Character.toString(u.attribute);
			}
		}
		return Character.toString(attribute);
	}
	
	public void pushUnit(Unit u) {
		this.units.add(u);
		occupied = checkOccupied();
	}
	
	public boolean checkOccupied(){
		for(Unit u : units){
			if(u instanceof Actor){
				return true;
			}
		}
		return false;
	}
}
