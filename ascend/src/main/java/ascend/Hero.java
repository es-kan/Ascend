package ascend;

import java.util.ArrayList;

public class Hero extends Actor {
	public int slayingCount;
	public Hero(Game game) {
		super(game);
		attribute = 'H';
		hp = 50;
		
	}
	
	private boolean move(String direction){
		Tile targetTile = game.getNeighbour(currentPosition, direction);
		return move(targetTile);
	}
	
	public void act(){
		//update variables like hunger, hp, whatever. Things that haven't been implemented yet.
	}

	public boolean act(String action) {
		if(action.equals("ACT")){
			//find a dude to slap
			ArrayList<Enemy> targets = new ArrayList<Enemy>();
			for(Tile tile : game.getSurroundingSquare(currentPosition)){
				if(tile.checkOccupied() && tile.unit instanceof Enemy){
					targets.add((Enemy) tile.unit);
				}
			}
			if(!targets.isEmpty()){
				attack(targets.get(rng.nextInt(targets.size())));
			}
		} else {
			return(move(action));
		}
		//invalid action!
		return false;
	}

	
	
}
