package ascend;

import ascend.Tile.TileType;

public abstract class Enemy extends Actor {
	
	public Enemy(Game game) {
		super(game);
		attribute = 'E';
	}
	
	public void act(){
		//move this Enemy to a random neighbour tile. Never before has an AI so advanced seen daylight.
		Tile[] possibleMoves = game.getAllNeighbours(currentPosition);
		Tile targetTile = possibleMoves[rng.nextInt(4)];
		move(targetTile);
	}
	
	//Overrides move from Actor to move even if the move is invalid.
	@Override
	public boolean move(Tile newLocation){
		if(!newLocation.checkOccupied() && newLocation.attribute != Tile.typeAttributes.get(TileType.WALL_TILE)){
			currentPosition.pullUnit();
			this.currentPosition = newLocation;
			this.currentPosition.pushUnit(this);
			return true;
		} else {
			this.act();
		}
		return false;
	}
	
}
