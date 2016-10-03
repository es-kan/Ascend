package ascend;

public class Hero extends Actor {
	public Hero(Game game) {
		super(game);
		attribute = 'H';
		hp = 50;
	}
	
	public boolean move(String direction){
		Tile targetTile = game.getNeighbour(currentPosition, direction);
		return move(targetTile);
	}
	
	public void act(){
		//update variables like hunger, hp, whatever. Things that haven't been implemented yet.
	}

	
	
}
