package ascend.access;

import ascend.Game;
import ascend.Tile;

public class SimpleGameRepresentation {
	String[] field;
	int heroHP;
	public SimpleGameRepresentation(Game game){
		//build the row strings to send
		int rowCount = 0;
		field = new String[game.field.length];
		for(Tile[] row : game.field){
			StringBuilder sb = new StringBuilder();
			for(Tile tile : row){
				if(tile.checkOccupied()){
					sb.append(tile.unit.attribute);
				} else {
					sb.append(tile.attribute);
				}
			}
			field[rowCount] = sb.toString();
			rowCount++;
		}
		heroHP = game.hero.hp;
	}
}
