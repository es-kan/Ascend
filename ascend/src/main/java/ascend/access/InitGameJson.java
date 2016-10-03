package ascend.access;

import ascend.Game;
import ascend.Tile;

/*This class is basically useless now that simple game representations are being sent to the front instead.*/

public class InitGameJson {
	Tile[] tiles;
	ActorJson[] actors;
	
	public InitGameJson(Game game){
		this.tiles = game.allTiles.toArray(new Tile[game.allTiles.size()]);
		this.actors = new ActorJson[game.actors.size()];
		
		for(int i=0; i<game.actors.size(); i++){
			actors[i] = new ActorJson(game.actors.get(i));
		}
	}
}
