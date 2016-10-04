package ascend;

import ascend.Tile.TileType;

public abstract class Actor extends Unit {
	//ATT: only one per tile.
	//actor = all enemies + hero
	public int hp;
	int attackPower = 1;
	
	Actor(Game game){
		super(game);
		comparisonInteger = 1000;
	}
	
	public abstract void act();
	
	boolean move(Tile newLocation){
		if(!newLocation.checkOccupied() && newLocation.attribute != Tile.typeAttributes.get(TileType.WALL_TILE)){
			currentPosition.pullUnit();
			this.currentPosition = newLocation;
			this.currentPosition.pushUnit(this);
			return true;
		} else {
			return false;
			//movement failed!
		}
		
	}
	
	Tile nextTileToGetTo(Tile targetTile){
		int x = currentPosition.x;
		int y = currentPosition.y;
		int tx = targetTile.x;
		int ty = targetTile.y;
		if(x > tx){
			x -= 1;
		} else if(x < tx){
			x += 1;
		}
		if(y > ty){
			y -= 1;
		} else if(y < ty){
			y += 1;
		}
		return game.field[y][x];
	}
	
	void attack(Actor target) {
		target.receiveAttack(attackPower);
	}
	
	void receiveAttack(int attackPower) {
		hp = hp > attackPower ? hp - attackPower : 0;
	}

	void die() {
		currentPosition.pullUnit();
	}
}


